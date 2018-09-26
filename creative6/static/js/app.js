angular.module('comment', ['ngSanitize'])
.controller('MainCtrl', ['$scope','$http','$sce', '$filter',
  function($scope,$http,$sce,$filter){
    $scope.comments = [];
    $scope.addComment = function() {
      $scope.getVideo($scope.formContent);
      var newcomment = {name:$scope.formName,title:$scope.formContent,upvotes:0,url:$scope.embeds};
      $scope.formContent='';
      $scope.formNameContent='';
      
      $http.post('/comments', newcomment).success(function(data){
        $scope.comments.push(data);
        $scope.comments = $filter('orderBy')($scope.comments, '-upvotes');
        window.location.reload();
      });
    };

    $scope.upvote = function(comment) {
      return $http.put('/comments/' + comment._id + '/upvote')
        .success(function(data){
          console.log("upvote worked");
          comment.upvotes = data.upvotes;
          $scope.comments = $filter('orderBy')($scope.comments, '-upvotes');
        });
    };
	$scope.incrementUpvotes = function(comment) {
	     $scope.upvote(comment);
       console.log(comment);
    };

  $scope.getAll = function() {
      return $http.get('/comments').success(function(data){
        angular.copy(data, $scope.comments);
         $scope.getVideo();
         $scope.comments = $filter('orderBy')($scope.comments, '-upvotes');
      });
    };

    $scope.getAll();

  $scope.delete = function(comment) {
    $http.delete('/comments/' + comment._id )
      .success(function(data){
        console.log("delete worked");
      });
    $scope.getAll();
  };
    

  $scope.getVideo = function(){
  $scope.embeds = {};
  //console.log($scope.comments);
  angular.forEach($scope.comments, function(comment){
    $http.get('/youtube?q='+comment.title).success(function(data){
      var video = data.items[0];
      
      //$scope.embed = "https://www.youtube.com/embed/"+video.id.videoId+"?autoplay=1&rel=0";
      $scope.embeds[comment._id]=$sce.trustAsResourceUrl("https://www.youtube.com/embed/"+video.id.videoId+"?autoplay=0&rel=0");
      //console.log($scope.embeds);
      //$sce.trustAsHtml("http://www.youtube.com/");
      //angular.copy(embed, $scope.video);

        });
     });

    };

 

  }
]);
