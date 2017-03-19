/**
 * Created by LeeKane on 17/3/17.
 */
var app=angular.module('homeApp',[]);
app.controller('homeController',function ($scope,$http) {
    $scope.searchFor=false;
    var userIdElement=angular.element('#userId')[0];
    $scope.userId=userIdElement.innerText;
    var userNameElement=angular.element('#userName')[0];
    $scope.userName=userNameElement.innerText;
    var acNameElement=angular.element('#activatied')[0];
    $scope.activatied=acNameElement.innerText;
  $scope.getRequiredHostel=function () {
      var cityElement=angular.element('#cityChoice')[0];
       $scope.city=cityElement.textContent;
      $http({
          method: "POST",
          url: "/HostelWorld/getRequiredHostel.do",
          data: {city:$scope.city,startData:$scope.startData}
      }).success(function (data, status){
          $scope.searchFor=true;
          $scope.hostels=data.hostels;
      }).error(function(data, status){
          $scope.signupError='申请失败';
      })
  };

})