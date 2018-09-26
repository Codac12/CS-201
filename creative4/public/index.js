$("#search").click(function(e){
  e.preventDefault();
  var everything = "";
  //var query = "";
  var apiKey = "AIzaSyCp8h92fiNWvPHlnG3u7jHsghZsR9ukLu4"
  var query = $("#feelingForm :selected").val();

<<<<<<< HEAD
   var myurl= "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=40&q="+query+"&key="+ apiKey;
=======
   var myurl= "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=50&q="+query+"&key="+ apiKey;
>>>>>>> b77cd5ebb3cf4e7fa568a7df4fe4438e0323116e

   everything = "<ul>";

   $.getJSON(myurl, function(data){ 
    var items = data.items;
    items = shuffle(items);
    sliceditems = items.slice(0, 10);


    $.each(sliceditems, function (index, item) {
        everything += "<li>";
        console.log( item.id.videoId);
        var videoID = item.id.videoId;
        var title = item.snippet.title;
        var thumbnailUrl = item.snippet.thumbnails.medium.url;

        everything += "<h3>"+title+"</h3><br/>";
        everything += '<iframe width="560" height="315" src="https://www.youtube.com/embed/'+videoID+'" frameborder="0" allowfullscreen></iframe>';
         everything += "</li>";
         everything += "<hr/>";
      });
    everything +="</ul>"
    $("#embedVideo").html(everything);

})
 });

$("#learn").click(function(e){
  e.preventDefault();
  var everything = "";
  //var query = "";
  var apiKey = "AIzaSyCp8h92fiNWvPHlnG3u7jHsghZsR9ukLu4"
  var query = "how to" + $("#learnForm :selected").val();

<<<<<<< HEAD
   var myurl= "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=10&q="+query+"&key="+ apiKey;
=======
   var myurl= "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=50&q="+query+"&key="+ apiKey;
>>>>>>> b77cd5ebb3cf4e7fa568a7df4fe4438e0323116e

   everything = "<ul>";

   $.getJSON(myurl, function(data){ 
    var items = data.items;
    items = shuffle(items);
    sliceditems = items.slice(0, 10);


    $.each(sliceditems, function (index, item) {
        everything += "<li>";
         console.log( item.id.videoId);
        var videoID = item.id.videoId;
        var title = item.snippet.title;
        var thumbnailUrl = item.snippet.thumbnails.medium.url;

        everything += "<h3>"+title+"</h3><br/>";
        everything += '<iframe width="560" height="315" src="https://www.youtube.com/embed/'+videoID+'" frameborder="0" allowfullscreen></iframe>';
         everything += "</li>";
         everything += "<hr/>";
      });
    everything +="</ul>"
    $("#embedVideo").html(everything);

})
 });

function shuffle(array) {
  var currentIndex = array.length, temporaryValue, randomIndex;

  // While there remain elements to shuffle...
  while (0 !== currentIndex) {

    // Pick a remaining element...
    randomIndex = Math.floor(Math.random() * currentIndex);
    currentIndex -= 1;

    // And swap it with the current element.
    temporaryValue = array[currentIndex];
    array[currentIndex] = array[randomIndex];
    array[randomIndex] = temporaryValue;
  }

  return array;
}

