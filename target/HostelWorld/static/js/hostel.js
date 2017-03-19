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
                    $scope.plans=data.plans;
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
        +'<li ng-repeat="plan in plans"> <div class="collapsible-header"><i class="material-icons">today</i>{{plan.startData}} 至 {{plan.overData}}</div> <div class="collapsible-body row planInfo" style=" margin:10px 10px 10px 8px"><span class="col s3">开始时间:{{plan.startData}}</span><span class="col s3">结束时间:{{plan.overData}}</span><span class="col s3">房间数:{{plan.roomNum}}</span><span class="col s3">房价:{{plan.price}}</span></div> </li>'
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

app.directive('registrationPanel',function ($http) {
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
                url: "/HostelWorld/getHostelUserStatistic.do",
                data: {hostelId:$scope.hostelId},
            }).success(function (data, status) {
                $scope.business=data.business;
                $scope.books=data.books;
                $scope.checkins=data.checkins;
                $scope.checkouts=data.checkouts;
            }).error(function (data, status) {

            })

        },
        controller:function($scope,$rootScope,$http)
        {
            $scope.Ractive='checkin';
            $scope.checkin=function () {
                $http({
                    method: "POST",
                    url: "/HostelWorld/checkin.do",
                    data: {busId:$scope.busId,hostelId:$scope.hostelId},
                }).success(function (data, status) {
                    $scope.business=data.business;
                    $scope.books=data.books;
                    $scope.checkins=data.checkins;
                    $scope.checkouts=data.checkouts;
                    Materialize.toast('登记成功!', 3000);
                }).error(function (data, status) {

                })
            }
            $scope.checkout=function () {
                $http({
                    method: "POST",
                    url: "/HostelWorld/checkout.do",
                    data: {busId:$scope.busId,hostelId:$scope.hostelId},
                }).success(function (data, status) {
                    $scope.business=data.business;
                    $scope.books=data.books;
                    $scope.checkins=data.checkins;
                    $scope.checkouts=data.checkouts;
                    Materialize.toast('登记成功!', 3000);
                }).error(function (data, status) {

                })
            }
        },
        template:
        '<div class="row container">'
        +'<div class="col s8">'
        +'<div class="col s12">'
        +'<div class="caption-container-main">'
        +'<div class="caption-text-container">登记</div>'
        +'<div class="caption-bg "></div>'
        +'</div><div style="margin-top: 25px">  '
        +'<ul class="tabs" >'
        +'<li class="tab col s6 m2" ><a ng-click= Ractive="checkin" class="active">入店登记</a></li>'
        +'<li class="tab col s6 m2" ><a ng-click= Ractive="checkout" >离店登记</a></li>'
        +'</ul>'
        +'</div>'
        +'<div ng-show= Ractive=="checkin" >' +
        '<form class="col s12">'+
        '<div class="row">'+
        '<div class="input-field col s6">'+
        '<input  id="first_name" type="text" class="validate">'+
        '<label for="first_name">游客姓名</label>'+
        '</div>'+
        '<div class="input-field col s6">'+
        '<input id="last_name" type="text" class="validate">'+
        '<label for="last_name">游客id</label>'+
        '</div>'+
        '</div>'+
        '<div class="row">'+
        '<div class="input-field col s6">'+
        '<input  id="first_name" type="text" class="validate">'+
        '<label for="first_name">房费</label>'+
        '</div>'+
        '<div class="input-field col s6">'+
        '<input id="last_name" type="text" class="validate">'+
        '<label for="last_name">入住人数</label>'+
        '</div>'+
        '</div>'+
        '<div class="row">'+
        '<div class="input-field col s12">'+
        '<input id="password" type="text" class="validate" ng-model="busId">'+
        '<label for="password">订单号</label>'+
        '</div>'+
        '</div>'+
        '<div class="row">'+
        '<div class="input-field col s12">'+
        '<input id="email" type="text" class="validate">'+
        '<label for="email">备注</label>'+
        '</div>'+
        '</div>'
        +'<button  class="waves-effect waves-light btn" ng-click="checkin()">入店登记</button>'
        +'</form>'
        +'</div>'
        +'<div ng-show= Ractive=="checkout">' +
        '<form class="col s12">'+
        '<div class="row">'+
        '<div class="input-field col s6">'+
        '<input  id="first_name" type="text" class="validate">'+
        '<label for="first_name">会员姓名</label>'+
        '</div>'+
        '<div class="input-field col s6">'+
        '<input id="last_name" type="text" class="validate">'+
        '<label for="last_name">会员卡号</label>'+
        '</div>'+
        '</div>'+
        '<div class="row">'+
        '<div class="input-field col s6">'+
        '<input  id="first_name" type="text" class="validate">'+
        '<label for="first_name">离店时间</label>'+
        '</div>'+
        '<div class="input-field col s6">'+
        '<input id="last_name" type="text" class="validate">'+
        '<label for="last_name">押金退还</label>'+
        '</div>'+
        '</div>'+
        '<div class="row">'+
        '<div class="input-field col s12">'+
        '<input id="password" type="text" class="validate" ng-model="busId" >'+
        '<label for="password">订单号</label>'+
        '</div>'+
        '</div>'+
        '<div class="row">'+
        '<div class="input-field col s12">'+
        '<input id="email" type="text" class="validate">'+
        '<label for="email">备注</label>'+
        '</div>'+
        '</div>'
        +'<button  class="waves-effect waves-light btn" ng-click="checkout()">离店登记</button>'
        +'</form>'
        +'</div>'
        +'</div>'
        +'</div>'
        +'<div class="col s4">'
        +'<div class="caption-container-main">'
        +'<div class="caption-text-container">当前预定</div>'
        +'<div class="caption-bg "></div>'
        +'</div>'
        +'<div class="container"><ul class="collapsible badgecol" data-collapsible="accordion">'
        +'<li ng-repeat= "bus in books">'
        +'<div class="collapsible-header"><i class="material-icons text-lighten-2 materialize-red-text">info_outline</i>{{bus.userName}} ({{bus.userId}})</div>'
        +'<div class="collapsible-body"><p>预定单号: {{bus.id}}</p><p>会员账号: {{bus.userName}}</p><p>会员卡号:{{bus.userId}}</p><p>开始时间: {{bus.startData}}</p><p>结束时间: {{bus.overData}}</p><p>每天价格: ¥{{bus.price}}</p><p>总计房费: ¥{{bus.cost}}</p></div>'
        +'</li>'
        +'</ul></div>'
        +'<div class="caption-container-main">'
        +'<div class="caption-text-container">当前入住</div>'
        +'<div class="caption-bg "></div>'
        +'</div>'
        +'<div class="container"><ul class="collapsible badgecol" data-collapsible="accordion">'
        +'<li ng-repeat= "bus in checkins">'
        +'<div class="collapsible-header"><i class="material-icons text-lighten-2 materialize-red-text">done</i>{{bus.userName}} ({{bus.userId}})</div>'
        +'<div class="collapsible-body"><p>预定单号: {{bus.id}}</p><p>会员账号: {{bus.userName}}</p><p>会员卡号:{{bus.userId}}</p><p>开始时间: {{bus.startData}}</p><p>结束时间: {{bus.overData}}</p><p>每天价格: ¥{{bus.price}}</p><p>总计房费: ¥{{bus.cost}}</p></div>'
        +'</li>'
        +'</ul></div>'
        +'<div>'
        +'</div>'
        +'</div>'
        +'</div>'

    };
    return obj;
})

app.directive('financePanel',function ($http) {
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
                url: "/HostelWorld/getHostelUserStatistic.do",
                data: {hostelId:$scope.hostelId},
            }).success(function (data, status) {
                $scope.business=data.business;
                $scope.books=data.books;
                $scope.checkins=data.checkins;
                $scope.checkouts=data.checkouts;
                $scope.checkovers=data.checkovers;

                for(var i=0;i<$scope.checkovers.length;i++)
                {
                    $scope.totalIncome+=$scope.checkovers[i]["cost"];
                }
            }).error(function (data, status) {

            })
        },
        controller:function($scope,$rootScope,$http)
        {
            $scope.totalIncome=0;
        },
        template:
        '<div class="row container">'
        +'<div class="col s8">'
        +'<div class="caption-container-main">'
        +'<div class="caption-text-container">收入</div>'
        +'<div class="caption-bg "></div>'
        +'</div>'
        +'<div class="container"><ul class="collapsible badgecol" data-collapsible="accordion">'
        +'<li ng-repeat= "bus in checkovers">'
        +'<div class="collapsible-header"><i class="material-icons text-lighten-2 materialize-red-text">done</i>{{bus.userName}} ({{bus.userId}}) <span style="float: right" class="materialize-red-text text-lighten-2">¥{{bus.cost}} </span></div>'
        +'<div class="collapsible-body"><p>预定单号: {{bus.id}}</p><p>会员账号: {{bus.userName}}</p><p>会员卡号:{{bus.userId}}</p><p>开始时间: {{bus.startData}}</p><p>结束时间: {{bus.overData}}</p><p>每天价格: ¥{{bus.price}}</p><p>总计房费: ¥{{bus.cost}}</p></div>'
        +'</li>'
        +'</ul>'
        +'<div class="materialize-red-text text-lighten-2" style="float: right">总计: ¥{{totalIncome}}</div>'
        +'</div>'
        +'</div>'
        +'<div class="col s4">'
        +'<div class="caption-container-main">'
        +'<div class="caption-text-container">支出</div>'
        +'<div class="caption-bg "></div>'
        +'</div>'
        +'</div></div>'

    };
    return obj;
})

app.filter("book",function(){
    return function(input){
        var out = [];
        for(var i=0 ; i<input.length; i++){
            if(input[i].book==1)
                out.push(input[i]);
        }

        return out;
    }
});