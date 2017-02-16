var app=angular.module('wordsLearner',['ngRoute']);
app.config(function($routeProvider){
    $routeProvider.when("/home", {
        controller:"homeController",
        templateUrl: "views/home.html"
    });
    $routeProvider.when("/about", {
        controller:"aboutController",
        templateUrl: "views/about.html"
    });
    $routeProvider.when("/play", {
        controller:"playController",
        templateUrl: "views/play.html"
    });
    $routeProvider.when("/learn", {
        controller:"learnController",
        templateUrl: "views/learn.html"
    });
    $routeProvider.when("/repeat", {
        controller:"repeatController",
        templateUrl: "views/repeat.html"
    });
    $routeProvider.otherwise({redirectTo: "/home"});
});
