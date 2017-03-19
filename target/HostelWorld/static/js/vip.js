/**
 * Created by LeeKane on 17/2/20.
 */
var app=angular.module("vipApp",[])
    app.controller("vipController",function($scope, $http) {
    $scope.active='vip';
    $scope.nameModify='normal';
    $scope.modifyActi='normal';
    $scope.cancelVip='normal';
        var userIdElement=angular.element('#cardId')[0];
        $scope.userId=userIdElement.innerText;
    $scope.modifyName=function () {
        $http({
            method: "POST",
            url: "/HostelWorld/modifyName.do",
            data: {username:$scope.username},
        }).success(function (data, status){
            window.location.href = "vip";
            Materialize.toast('修改成功!', 6000);
            $scope.nameModify='normal';
        }).error(function(data, status){
            Materialize.toast('修改失败!', 6000);
        })
    }
    $scope.actived=function () {
        if($scope.income>1000||$scope.income==1000) {
            $http({
                method: "POST",
                url: "/HostelWorld/activitied",
                data: {income: $scope.income},
            }).success(function (data, status) {
                Materialize.toast('激活成功!', 6000);
                window.location.href = "vip";
            }).error(function (data, status) {
                Materialize.toast('修改失败!', 6000);
            })
        }
        else
        {
            Materialize.toast('需要充值1000元以上来激活!', 6000);
        }
    }
    $scope.cancel=function () {
        $http({
            method: "POST",
            url: "/HostelWorld/cancel",
        }).success(function (data, status) {
            Materialize.toast('取消成功!', 6000);
            window.location.href = "login";
        }).error(function (data, status) {
            Materialize.toast('修改失败!', 6000);
        })
    }


});
app.directive('infoPanel',function ($http) {
    var obj={
        restrict: "AE",
        replace: true,
        scope: false,
        link: function ($scope,$http) {


        },
        controller:function ($scope,$http) {
            $http({
                method: "POST",
                url: "/HostelWorld/getUserStatistic.do",
                data: {userId:$scope.userId},
            }).success(function (data, status) {
                $scope.business=data.business;
                $scope.books=data.books;
                $scope.checkins=data.checkins;
                $scope.checkouts=data.checkouts;
            }).error(function (data, status) {

            })
        },
        template:
        '<div class="row">'
        +'<div class="col s12">'
        +'<div class="caption-container-main">'
        +'<div class="plan-text-container">我的预定</div>'
        +'<div class="plan-caption-bg "></div>'
        +'</div>'
        +'<div ng-repeat="bus in books" class="plan-Filed">{{bus.hostelName}}<span style="padding-left: 60px; padding-right: 60px"> {{bus.startData}} 至 {{bus.overData}} </span >¥{{bus.cost}}</div>'
        +'<div class="caption-container-main">'
        +'<div class="plan-text-container">正在进行</div>'
        +'<div class="plan-caption-bg "></div>'
        +'</div>'
        +'<div ng-repeat="bus in checkins" class="plan-Filed">{{bus.hostelName}}<span style="padding-left: 60px; padding-right: 60px"> {{bus.startData}} 至 {{bus.overData}} </span >¥{{bus.cost}}</div>'
        +'<div class="caption-container-main">'
        +'<div class="plan-text-container">已经完成</div>'
        +'<div class="plan-caption-bg "></div>'
        +'</div>'
        +'<div ng-repeat="bus in checkouts" class="plan-Filed">{{bus.hostelName}}<span style="padding-left: 60px; padding-right: 60px"> {{bus.startData}} 至 {{bus.overData}} </span >¥{{bus.cost}}</div>'
        +'</div>'
        +'</div>'
    };
    return obj;

});