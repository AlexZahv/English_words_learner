app.controller('learnController', function ($scope, $location,$http) {
    $scope.saveWord=function(word,translations){
        var transferObj={data:word,translations:translations};
        $http.post('/learn/save', transferObj).then(function successCallback(response) {
            $scope.description=response.data;
        }, function errorCallback(response) {
            console.log("error");
        });

    };
});