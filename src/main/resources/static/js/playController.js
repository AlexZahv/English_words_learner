app.controller('playController', function ($scope, $http) {
    var answersArr = [];
    $scope.answers = [];

    function getWordsForPlay() {
        $http.post('/play/next', {wordLanguageId: 2}).then(function successCallback(response) {
            $scope.wordsArr = response.data;
            for (var i = 0; i < $scope.wordsArr.length; i++)
                answersArr[i] = $scope.wordsArr[i].data;
        }, function errorCallback(response) {
            console.log("error");
        });
    }

   function getWordForRepeat() {
       getWordsForPlay();
        $http.post('/repeat/next', {wordLanguageId: 1}).then(function successCallback(response) {
            $scope.word = response.data;
            answersArr[getFreeIndex(answersArr)] = $scope.word.translations[Math.floor(Math.random() * $scope.word.translations.length)].data;
            $scope.answers  = shuffle(answersArr);

        }, function errorCallback(response) {
            console.log("error");
        });
    }
    function getFreeIndex(array){
        return array.length > 0 ? array.length : 0;
    }
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
    getWordForRepeat();

    $scope.results = {attempts: 11, right: 6, wrong: 5};
    $scope.getById = function (answer) {
        alert(answer);
    };
});