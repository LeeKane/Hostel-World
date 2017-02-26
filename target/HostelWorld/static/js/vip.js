/**
 * Created by LeeKane on 17/2/20.
 */
angular.module("vipApp",[]).controller("vipController",function($scope, $http) {
    $scope.active='vip';
    $scope.nameModify='normal';
    $scope.modifyActi='normal';
    $scope.cancelVip='normal';
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