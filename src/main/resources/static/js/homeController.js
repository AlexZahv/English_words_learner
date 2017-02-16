app.controller('homeController', function ($scope, $location,$http) {
    $scope.fillCurrencies=function(){
        $http({
            method: 'GET',
            url: '/home/description'

        }).then(function successCallback(response) {
            $scope.description=response.data;
        }, function errorCallback(response) {
            console.log("error");
        });
    };
    $scope.fillCurrencies();
});