/**
 * Created by LeeKane on 17/3/15.
 */
var app=angular.module("managerApp",[]);
app.controller('managerController',function ($scope,$http) {
    $scope.login=false;
    $scope.active='tab1';
    $scope.managerLogin= function () {
        $http({
            method: "POST",
            url: "/HostelWorld/managerLogin.do",
            data: {password:$scope.password,username:$scope.name}
        }).success(function (data, status){
            var user= data.user;
            if(user==null)
            {
                $scope.result=data.result;
            }
            else
            {
                $scope.user=data.user;
                Materialize.toast("登陆成功!", 1000);
                $scope.login=true;
            }

        }).error(function(data, status){

        })
    }
});


app.directive("managerPanel", ['$http', function ($http) {
    var obj = {
        restrict: "AE",
        replace: true,
        scope: true,
        transclude: true,
        link: function ($scope, scope, element, attrs) {
            $http({
                method: "POST",
                url: "/HostelWorld/getApplication.do",
            }).success(function (data, status){
                    $scope.applications=data.applications;

            }).error(function(data, status){

            })
        },
        controller: function ($scope, $http) {
            $scope.pass=function (application) {
                var name=application.name;
                $http({
                    method: "POST",
                    url: "/HostelWorld/pass.do",
                    data:{name:name}
                }).success(function (data, status){
                    $scope.applications=data.applications;
                    Materialize.toast("审批成功!", 1000);
                }).error(function(data, status){

                })
            }

        },
        template: '<div><div class="container" ng-show= active=="tab1"><p>tab1</p></div>'
        +'<div class="container" ng-show= active=="tab2"><ul class="collapsible badgecol" data-collapsible="accordion">'
        +'<li ng-repeat= "application in applications">'
        +'<div class="collapsible-header"><span ng-show= {{application.read}}==0 class="new badge">新申请</span><i ng-show= {{application.read}}==0 class="material-icons">info_outline</i><i ng-show= {{application.read}}==1 class="material-icons">done</i>{{application.name}}</div>'
        +'<div class="collapsible-body"><p>客栈名: {{application.name}}</p><p>申请时间: {{application.date}}</p><p>城市: {{application.city}}</p><p>地址: {{application.address}}</p><p>简介: {{application.info}}</p><p>营业执照: {{application.license}}</p><div ng-show= {{application.read}}==0 class="applicationbutton"><button  ng-click="pass(application)" class="applicationbuttonleft waves-effect waves-light btn">'
        +'通过</button><button  class="waves-effect waves-light btn">拒绝</button></div>'
        +'</li>'
        +'</ul></div>'
        +'<div class="container" ng-show= active=="tab3"><p>tab3</p></div></div>'
    }
    return obj;
}]);