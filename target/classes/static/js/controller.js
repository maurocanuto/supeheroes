
app.controller('superheroesController', function($scope, $http, BASE_URL) {
    $scope.headingTitle = "Team members";

    var superheroesController = this;
    $scope.superheroes = [];


    getSuperheroes().then(
         function(response) {
            $scope.superheroes = response.data;
            console.log("Res "+ response.data);
            if (response.data == ""){
                    alert('There are no superheroes');
                }
            },
         function() {
            console.log("Error in retrieving superheroes");
         });


    function getSuperheroes() {
                return $http({method: 'GET', url: BASE_URL + 'getAllSuperheroes'});
            }


    superheroesController.$inject = ['$scope', '$http', 'BASE_URL'];

});

app.controller('creationController', function($scope, $http, BASE_URL) {
    $scope.headingTitle = "Create a superhero";

    var creationController = this;

    $scope.hero = {};
    $scope.mySkills = [];
    $scope.myAllies = [];

    $scope.addSkill = function(skill){

        if($scope.mySkills.indexOf(skill) !== -1) {
          alert('Skill already present');
          return;
        }else{
            $scope.mySkills.push(skill);
            $scope.hero.skills = $scope.mySkills;
        }

    }

    $scope.addAlly= function(ally){

            if($scope.myAllies.indexOf(ally) !== -1) {
              alert('Ally already present');
              return;
            }else{
                $scope.myAllies.push(ally);
                $scope.hero.allies = $scope.myAllies;
            }

        }

    $scope.createHero = function(){

    var parameter = JSON.stringify($scope.hero);
    var BASE_URL = "http://localhost:8080/";
    var url = BASE_URL + 'addSuperhero'

    console.log(parameter);
        $http.post(url, parameter).
            success(function(data, status, headers, config) {
                $scope.hero = {};
                $scope.mySkills = [];
                $scope.myAllies = [];

                alert('Hero created with success!');
              }).
              error(function(data, status, headers, config) {
                console.log("Error in creating superhero");
              });



    }


    creationController.$inject = ['$scope', '$http','BASE_URL'];

});

app.controller('detailController', function($scope, $http, BASE_URL) {
    $scope.headingTitle = "Hero Details";
    $scope.superhero = {}
    var detailController = this;
    var BASE_URL = "http://localhost:8080/";
    $scope.hero = {}
    $scope.isShown = false;

    $scope.getDetails = function(name){

        $scope.isShown = false;
        var promise = $http({
            method: 'GET',
            url: BASE_URL + 'getHero/' + name
          });
          promise.success(function(data, status, headers, conf) {
            $scope.hero = data;
            $scope.isShown = true;
            return data;
          });
          promise.error(function(status) {
             alert('Error: Hero ' + name + ' is not present');
          });

          return promise;
    }

        $scope.deleteHero = function(name){

            $scope.isShown = false;
            var promise = $http({
                method: 'GET',
                url: BASE_URL + 'removeHero/' + name
              });
              promise.success(function(data, status, headers, conf) {
                alert('Hero ' + name + ' has been deleted');
              });
              promise.error(function(status) {
                 alert('Error: Hero ' + name + ' is not present');
              });

              return promise;
        }


    detailController.$inject = ['$scope', '$http', 'BASE_URL'];
});

app.controller('homeController', function($scope) {
    $scope.headingTitle = "Home";
});