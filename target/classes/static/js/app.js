var app = angular.module('app', ['ngRoute','ngResource']);
app.constant('BASE_URL', 'http://localhost:8080/');
app.config(function($routeProvider){
    $routeProvider
        .when('/', {
            templateUrl : 'views/home.html',
            controller: 'homeController'
        })
        .when('/creation',{
            templateUrl: 'views/creation.html',
            controller: 'creationController'
        })
        .when('/superheroes',{
            templateUrl: 'views/superheroes.html',
            controller: 'superheroesController'
        })
        .when('/detail',{
                    templateUrl: 'views/detail.html',
                    controller: 'detailController'
                })
        .otherwise(
            { redirectTo: '/'}
        );
});