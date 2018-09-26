var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});


router.get('/youtube', function(req, res, next) {
  var apiKey = "AIzaSyCp8h92fiNWvPHlnG3u7jHsghZsR9ukLu4"
  var query = "Rick Astley - Never Gonna Give You Up";

  var url= "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=1&q="+query+"&key="+ apiKey;
  request(url).pipe(res);

});


router.get('/realyoutube', function(req, res, next) {
  var apiKey = "AIzaSyCp8h92fiNWvPHlnG3u7jHsghZsR9ukLu4"
  var query = req.query.q;
  var url= "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=1&q="+query+"&key="+ apiKey;
   request(url).pipe(res);
});

module.exports = router;
