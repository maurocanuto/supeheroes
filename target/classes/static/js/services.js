angular.module('app.services', []).

factory('SuperheroesService', function($scope) {

    function SuperheroesService($http, BASE_URL) {
        return {
            getSuperheroes: getSuperheroes
        };

        function getSuperheroes() {
            console("Getting from " + BASE_URL);
            return $http({method: 'GET', url: BASE_URL + 'superheroes'});
        }

    }
    SuperheroesService.$inject = ['$http', 'BASE_URL'];

})

.constant('BASE_URL', '/');