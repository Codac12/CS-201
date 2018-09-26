var crypto = require('crypto');
var express = require('express');
var mongoose = require('mongoose');
var Comment = mongoose.model('Comment');
var request = require('request');
module.exports = function(app) {
  var users = require('./controllers/users_controller');
  app.use('/static', express.static( './static')).
      use('/lib', express.static( '../lib')
  );
  app.get('/', function(req, res){
    if (req.session.user) {
      res.render('index', {username: req.session.username,
                           msg:req.session.msg,
                           color:req.session.color});
    } else {
      req.session.msg = 'Access denied!';
      res.redirect('/login');
    }
  });
  app.get('/user', function(req, res){
    if (req.session.user) {
      res.render('user', {msg:req.session.msg});
    } else {
      req.session.msg = 'Access denied!';
      res.redirect('/login');
    }
  });
  app.get('/signup', function(req, res){
    if(req.session.user){
      res.redirect('/');
    }
    res.render('signup', {msg:req.session.msg});
  });
  app.get('/login',  function(req, res){
    if(req.session.user){
      res.redirect('/');
    }
    res.render('login', {msg:req.session.msg});
  });

  app.get('/logout', function(req, res){
    req.session.destroy(function(){
      res.redirect('/login');
    });
  });


app.get('/comments', function(req, res, next) {
  Comment.find(function(err, comments){
    if(err){ return next(err); }
    res.json(comments);
  });
});

app.post('/comments', function(req, res, next) {
  var comment = new Comment(req.body);
  console.log(comment);
  comment.save(function(err, comment){
    if(err){ return next(err); }
    console.log(comment);
    res.json(comment);
  });
});

app.param('comment', function(req, res, next, id) {
  var query = Comment.findById(id);
  query.exec(function (err, comment){
    if (err) { return next(err); }
    if (!comment) { return next(new Error("can't find comment")); }
    req.comment = comment;
    return next();
  });
});

app.get('/comments/:comment', function(req, res) {
  res.json(req.comment);
});

app.put('/comments/:comment/upvote', function(req, res, next) {
  req.comment.upvote(function(err, comment){
    if (err) { return next(err); }
    res.json(comment);
  });
});

app.delete('/comments/:comment', function(req, res) {
  console.log(req.session.username);
  console.log(req.comment.name);
  console.log("in Delete");
  if(req.session.username == req.comment.name)
  {
    req.comment.remove();
    res.json("Success fully deleted");
  }
  else
    res.json("Error, you cant delete someone else's video");
  
});

app.get('/youtube', function(req, res, next) {
  var apiKey = "AIzaSyCm_gcAsiirdPiVdJ_-5X-c7zi4H3N77JM";
  var query = req.query.q;
 
  console.log(query);

  var url= "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=1&q="+query+"&key="+ apiKey;
  console.log(url);
  request(url).pipe(res);
});

  app.post('/signup', users.signup);
  app.post('/user/update', users.updateUser);
  app.post('/user/delete', users.deleteUser);
  app.post('/login', users.login);
  app.get('/user/profile', users.getUserProfile);
}