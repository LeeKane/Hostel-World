/**
 * Created by LeeKane on 17/3/17.
 */
var app=angular.module('homeApp',[]);
app.controller('homeController',function ($scope,$http) {
    $scope.searchFor=false;
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