function myservices($scope){
    $scope.services=[
        { title: 'Web Layout Development', price: 300},
        { title: 'Web Design', price: 250},
        { title: 'Photography', price: 150},
        { title: 'Prof. Conrad\'s Autograph', price: 1000000000},
        { title: 'Donuts', price: 10}];
    $scope.total=function(){
        var t = 0;
        angular.forEach($scope.services, function(s){
            if(s.selected)
                t+=s.price;
        });
        return t;
    };
}