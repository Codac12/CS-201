var express = require('express');
var router = express.Router();
var mongoose = require('mongoose');
var Comment = mongoose.model('Comment');
var request = require('request');


router.get('/comments', function(req, res, next) {
  Comment.find(function(err, comments){
    if(err){ return next(err); }
    res.json(comments);
  });
});

router.post('/comments', function(req, res, next) {
  var comment = new Comment(req.body);
  console.log(comment);
  comment.save(function(err, comment){
    if(err){ return next(err); }
    console.log(comment);
    res.json(comment);
  });
});

router.param('comment', function(req, res, next, id) {
  var query = Comment.findById(id);
  query.exec(function (err, comment){
    if (err) { return next(err); }
    if (!comment) { return next(new Error("can't find comment")); }
    req.comment = comment;
    return next();
  });
});

router.get('/comments/:comment', function(req, res) {
  res.json(req.comment);
});

router.put('/comments/:comment/upvote', function(req, res, next) {
  req.comment.upvote(function(err, comment){
    if (err) { return next(err); }
    res.json(comment);
  });
});

router.delete('/comments/:comment', function(req, res) {
  console.log("in Delete");
  req.comment.remove();
  res.json(req.comment);
});

router.get('/youtube', function(req, res, next) {
  var apiKey = "AIzaSyCm_gcAsiirdPiVdJ_-5X-c7zi4H3N77JM";
  var query = req.query.q;
 
  console.log(query);

  var url= "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=1&q="+query+"&key="+ apiKey;
  console.log(url);
  request(url).pipe(res);
});

module.exports = router;
