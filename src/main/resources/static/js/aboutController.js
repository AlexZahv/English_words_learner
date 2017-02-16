app.controller('aboutController', function ($scope, $location) {
    $scope.tutorials = [{title: 'title1', body: 'body1'}, {title: 'title2', body: 'body2'}];
    $scope.checkAnswer = function () {
        alert('ZAZAZA');
    };
});
