var app = angular.module('sapyo', []);


function request(url, data, get) {
    var method = 'POST';
    if (get)
        method = 'GET';
    return {
        method: method,
        url: url,
        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        transformRequest: function (obj) {
            var str = [];
            for (var p in obj)
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
            return str.join("&");
        },
        data: data
    }
}

app.controller('RegisterController', ['$scope', '$http', function ($scope, $http) {

    $scope.types = ["사원", "주임", "대리", "차장", "팀장", "부장", "사장"]

    $scope.submit = function () {
        $http(request('user/register.json', {user: JSON.stringify($scope.user)}))
            .success(function (result) {
                if (result.success) {
                    location.reload();
                } else {
                    warring(result.error);
                }
            });
    }

}]);


app.controller('LoginController', ['$scope', '$http', function ($scope, $http) {
    $scope.submit = function () {
        $http(request('user/login.json', {user: JSON.stringify($scope.user)}))
            .success(function (result) {
                if (result.success) {
                    location.reload();
                } else {
                    warring(result.error);
                }
            });
    }

}]);

app.controller('WheatherController', ['$scope', '$http', function ($scope, $http) {
    $http(request('sapyo/wheather.json', {}, true))
        .success(function (result) {
            $scope.wheather = result;
        });
}]);

app.controller('SapyoListController', ['$scope', '$http', function ($scope, $http) {
    $http(request('sapyo/getlist.json', {}, true))
        .success(function (result) {
            $scope.list = result;
        });
    
    $scope.submit = function(id, accept){
        var data = {id: id};
    	if(accept)
            data.decline = true;
    	$http(request('reason/submit.json', data))
        .success(function (result) {
            if (result.success) {
                location.reload();
            } else {
                warring(result.error);
            }
        });
    }
}]);

app.controller('MyListController', ['$scope', '$http', function ($scope, $http) {
    $http(request('sapyo/getmylist.json', {}, true))
        .success(function (result) {
            $scope.list = result;
        });
}]);


app.controller('SapyoController', ['$scope', '$http', function ($scope, $http) {
    $scope.submit = function () {
        var data = {reason: $scope.reason};
        if ($scope.reason == undefined)
            data = undefined;
        $http(request('sapyo/submit.json', data))
            .success(function (result) {
                if (result.success) {
                    location.reload();
                } else {
                    warring(result.error);
                }
            });
    }

}]);