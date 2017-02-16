app.controller('repeatController', function ($scope, $location, $http) {
    $scope.result = '0';
    $scope.repeatResult = 'Ожидание';
    $scope.getWordForRepeat = function () {
            $scope.result = '0';
            $scope.repeatResult = 'Ожидание';
            $http.post('/repeat/next', {wordLanguageId: 2}).then(function successCallback(response) {
                $scope.curWord = response.data;
                $scope.translations = $scope.curWord.translations;
            }, function errorCallback(response) {
                console.log("error");
            });
    };

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

    $scope.updateStatisticks = function (word, rightAnswersCount) {
        var transferObj = {
            data: word.data,
            id: word.id,
            wordLanguageId: word.languageId,
            rightAnswersCount: rightAnswersCount,
            userId: word.userId
        };
        $http.post('/repeat/update', transferObj).then(function successCallback(response) {
            $scope.curWord = response.data;
        }, function errorCallback(response) {
            console.log("error");
        });
    };


    $scope.getTranslation = function () {
        var word = $scope.curWord;
        $scope.translation = '';
        word.translations.forEach(function (entry) {
            $scope.translation += entry.data + ", "
        });
        $scope.translation.trim(',');
    };

    $scope.getWordForRepeat;
});