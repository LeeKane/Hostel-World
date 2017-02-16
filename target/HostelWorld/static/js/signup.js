/**
 * Created by mac on 16/10/7.
 */
var app = angular.module("loginApp", []);
app.controller("loginController", function($scope, $http) {
    $scope.active='signin';

    $scope.signup = function(){
            $http({
                method: "POST",
                url: "/HostelWorld/signup.do",
                data: {password:$scope.password,username:$scope.username}
            }).success(function (data, status){
                Materialize.toast('注册成功!', 6000);
                $scope.active='signupSuccess';
                $scope.new=data;
                $scope.cardId=data.cardId;
            }).error(function(data, status){
                $scope.signupError='用户名已存在';
            })
    };
   
});
