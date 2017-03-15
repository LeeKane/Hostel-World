/**
 * Created by LeeKane on 17/3/14.
 */
var app = angular.module("hostelLoginApp", []);
app.controller("hostelLoginController", function($scope, $http) {
    $scope.active='signin';


    $scope.hostelSignup = function(){
        var cityElement=angular.element('#cityChoice')[0];
        var city=cityElement.textContent;
        $http({
            method: "POST",
            url: "/HostelWorld/hostelSignup.do",
            data: {name:$scope.name,password:$scope.password,city:city,address:$scope.address,info:$scope.info,license:$scope.License,application:0}
        }).success(function (data, status){
            Materialize.toast('申请成功!', 6000);
            $scope.active='signupSuccess';
            $scope.new=data;
            $scope.id=data.id;
        }).error(function(data, status){
            $scope.signupError='申请失败';
        })
    };

});