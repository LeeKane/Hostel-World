/**
 * Created by LeeKane on 17/3/16.
 */
var app= angular.module('hostelApp',['ngAnimate']);
app.controller('hostelMainController',function ($scope) {
    $scope.active='tab0';
    $scope.add=false;
})
app.directive('planePanel',function ($http) {
    var obj={
        restrict:'AE',
        scope:
        {
            hostelId:'@hostelId'
        },
        repalce:true,
        link: function($scope)
        {
            $http({
                method: "POST",
                url: "/HostelWorld/getPlans.do",
                data: {hostelId:$scope.hostelId}
            }).success(function (data, status){
                $scope.plans=data.plans;

            }).error(function(data, status){

            })
            $('.collapsible').collapsible();
            $scope.add=false;
            var nowTemp = new Date();
            var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
            var checkin = $('#dpd1').fdatepicker({
                onRender: function (date) {
                    return date.valueOf() < now.valueOf() ? 'disabled' : '';
                }
            }).on('changeDate', function (ev) {
                if (ev.date.valueOf() > checkout.date.valueOf()) {
                    var newDate = new Date(ev.date)
                    newDate.setDate(newDate.getDate() + 1);
                    checkout.update(newDate);
                }
                checkin.hide();
                $('#dpd2')[0].focus();
            }).data('datepicker');
            var checkout = $('#dpd2').fdatepicker({
                onRender: function (date) {
                    return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
                }
            }).on('changeDate', function (ev) {
                checkout.hide();
            }).data('datepicker');
        },
        controller:function($scope,$rootScope,$http)
        {
            $scope.addPlane=function()
            {
                $scope.add=!$scope.add;
                $scope.startData=null;
                $scope.overData=null;
                $scope.roomNum=null;
                $scope.price=null;
            }
            $scope.addTo=function ()
            {
                $http({
                    method: "POST",
                    url: "/HostelWorld/addPlan.do",
                    data: {hostelId:$scope.hostelId,startData:$scope.startData,overData:$scope.overData,roomNum:$scope.roomNum,price:$scope.price}
                }).success(function (data, status){
                    Materialize.toast('添加成功!', 6000);

                }).error(function(data, status){

                })
            }
        },
        template:
        '<div class="row container">'
        +'<div class="col s8">'
        +'<div class="caption-container-main">'
        +'<div class="caption-text-container">我的计划</div>'
        +'<div class="caption-bg "></div>'
        +'</div>'
        +'  <ul class="collapsible popout" data-collapsible="accordion">'
        +'<li ng-repeat="plan in plans"> <div class="collapsible-header"><i class="material-icons">today</i>{{plan.startData}} 至 {{plan.overData}}</div> <div class="collapsible-body row planInfo"><span class="col s3">开始时间:{{plan.startData}}</span><span class="col s3">结束时间:{{plan.overData}}</span><span class="col s3">房间数:{{plan.roomNum}}</span><span class="col s3">房价:{{plan.price}}</span></div> </li>'
        +'</ul>'
        +'<div>'
        +'<a class="btn-floating btn-large waves-effect waves-light materialize-red-text text-lighten-2" ng-click="addPlane()"><i class="material-icons">add</i></a>'
        +'<div class=" card planeadd" ng-hide="add==false">'
        +'<span class="card-title planTitle">添加计划</span>'
        +'<div class=" planContainer"> <div class="input-field col s6">'
        +'<input  id="dpd1" type="text" class="validate span2" ng-model="startData" required>'
        +'<label for="dpd1">开始时间</label></div>'
        +'<div class="input-field col s6">'
        +'<input id="dpd2" type="text" class="validate span2" ng-model="overData" required>'
        +'<label for=dpd2>结束时间</label> </div> </div>'
        +'<div class=" planContainer"> <div class="input-field col s6">'
        +'<input  id="roomNum" type="text" class="validate" ng-model="roomNum" required>'
        +'<label for="roomNum">房间数</label></div>'
        +'<div class="input-field col s6">'
        +'<input id="price" type="text" class="validate" ng-model="price" required>'
        +'<label for="price">房价</label> </div> </div>'
        +'<div  class="applicationbutton"><button  ng-click="addTo()" ng-if="roomNum!=null && price!=null && startData!=null && overData!=null " class="plan-button waves-effect waves-light btn">'
        +'添加</button><button  class="waves-effect waves-light btn" ng-click="addPlane()">取消</button></div>'
        +'</div></div>'
        +'</div>'
        +'<div class="col s4">'
        +'<div class="caption-container-main">'
        +'<div class="caption-text-container">正在进行</div>'
        +'<div class="caption-bg "></div>'
        +'</div>'
        +'<div class="caption-container-main">'
        +'<div class="caption-text-container">即将进行</div>'
        +'<div class="caption-bg "></div>'
        +'</div>'
        +'</div>'
        
    };
    return obj;
})