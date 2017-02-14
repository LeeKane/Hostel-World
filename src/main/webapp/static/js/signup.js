/**
 * Created by mac on 16/10/7.
 */
var app = angular.module("loginApp", []);
app.controller("loginController", function($scope, $http) {
    $scope.active='signin';
    $scope.signup = function(){
            Materialize.toast('注册成功!', 6000);
            $http({
                method: "POST",
                url: "/HostelWorld/signup.do",
                data: {password:$scope.password,username:$scope.username}
            }).success(function (data, status){
                // handle success
                $scope.active='signupSuccess';
            })
    };
});
