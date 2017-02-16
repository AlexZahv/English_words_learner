app.controller('playController', function ($scope, $http) {
    var answersArr = [];
    $scope.answers = [];

    $scope.result = '0';
    $scope.repeatResult = 'Ожидание';

    $scope.checkAnswer = function (translation) {
        for (var i = 0; i < $scope.translations.length; i++) {
            if ($scope.translations[i].data == translation) {
                $scope.result = '1';
                $scope.repeatResult = 'Ответ правильный';
                $scope.updateStatisticks($scope.curWord, $scope.curWord.rightAnswersCount + 1);
                return false;
            }
            else {
                $scope.result = '-1';
                $scope.repeatResult = 'Ответ не верен';
                $scope.updateStatisticks($scope.curWord, $scope.curWord.rightAnswersCount - 1);
            }
        }
    };
    $scope.getWordsForPlay = function () {
        $http.post('/play/next', {
            wordLanguageId: 1,
            translationLanguageId: 2
        }).then(function successCallback(response) {
            $scope.wordsArr = response.data;
            $scope.word = $scope.wordsArr[0];
            answersArr[0] = $scope.word.data;
            for (var i = 1; i < $scope.wordsArr.length; i++)
                answersArr[i] = $scope.wordsArr[i].data;
            answersArr[answersArr.length] = $scope.word.translations[getRandomIndex($scope.word.translations.length)].data;
            $scope.answers = shuffle(answersArr);
        }, function errorCallback(response) {
            console.log("error");
        });
    }

    function getRandomIndex(max) {
        return Math.floor(Math.random() * max)
    }

    function shuffle(array) {
        var currentIndex = array.length, temporaryValue, randomIndex;
        while (0 !== currentIndex) {
            randomIndex = Math.floor(Math.random() * currentIndex);
            currentIndex -= 1;
            temporaryValue = array[currentIndex];
            array[currentIndex] = array[randomIndex];
            array[randomIndex] = temporaryValue;
        }
        return array;
    }

    $scope.getWordsForPlay();

    $scope.results = {attempts: 11, right: 6, wrong: 5};
    $scope.getById = function (answer) {
        alert(answer);
    };
});