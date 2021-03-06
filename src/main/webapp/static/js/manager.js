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

            });
            $http({
                method: "POST",
                url: "/HostelWorld/getAllBusiness.do",
                data:{name:"sss"}
            }).success(function (data, status){
                $scope.business=data.business;
                $scope.books=data.books;
                $scope.checkins=data.checkins;
                $scope.checkouts=data.checkouts;

            }).error(function(data, status){

            });
            var myChart = echarts.init(document.getElementById('main'));

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '交易总额'
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['交易总额']
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis : [
                    {
                        type : 'category',
                        boundaryGap : false,
                        data : ['3-14','3-15','3-16','3-17','3-18','3-19','3-20']
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'搜索引擎',
                        type:'line',
                        stack: '总量',
                        label: {
                            normal: {
                                show: true,
                                position: 'top'
                            }
                        },
                        areaStyle: {normal: {}},
                        data:[1820, 1932, 2901, 2934, 3290, 3330, 4320]
                    }
                ]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);

            var myChart1 = echarts.init(document.getElementById('main1'));

            // 指定图表的配置项和数据
            var option1 = {
                title: {
                    text: '会员数量'
                },
                tooltip: {},
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                legend: {
                    data:['数量']
                },
                xAxis: {
                    data: ["3-14","3-15","3-16","3-17","3-18","3-19","3-20"]
                },
                yAxis: {},
                series: [{
                    name: '销量',
                    type: 'bar',
                    data: [5, 6, 10, 20, 15, 10,1]
                }]
            };


            // 使用刚指定的配置项和数据显示图表。
            myChart1.setOption(option1);
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
            $scope.settlement=function (checkout) {
                var checkout=checkout;
                $http({
                    method: "POST",
                    url: "/HostelWorld/settlement.do",
                    data:{cost:checkout.cost,userId:checkout.userId,id:checkout.id}
                }).success(function (data, status){
                    Materialize.toast("结算成功!", 1000);
                    $scope.business=data.business;
                    $scope.books=data.books;
                    $scope.checkins=data.checkins;
                    $scope.checkouts=data.checkouts;
                }).error(function(data, status){

                })
            }

        },
        template: '<div><div class="container" ng-show= active=="tab1">'
        +'<div class="row ">'
        +'<div class="col s6">'
        +'<div class="col s12">'
        +'<div class="caption-container-main">'
        +'<div class="caption-text-container">入住情况</div>'
        +'<div class="caption-bg "></div>'
        +'</div>'
        +'<div>' +
        '<ul class="collapsible badgecol" data-collapsible="accordion">'
        +'<li ng-repeat= "checkout in checkins">'
        +'<div class="collapsible-header"><i  class="material-icons text-lighten-2 materialize-red-text">done</i>{{checkout.hostelName}} ({{checkout.hostelId}})</div>'
        +'<div class="collapsible-body"><p>客栈名: {{checkout.hostelName}}</p><p>会员名: {{checkout.userName}}</p><p>开始时间: {{checkout.startData}}</p><p>结束时间: {{checkout.overData}}</p><p>每天价格: ¥{{checkout.price}}</p><p>总计房费: ¥{{checkout.cost}}</p>'
        +'</li>'
        +'</ul>'
        +'</div>'
        +'</div>'
        +'</div>'
        +'<div class="col s6">'
        +'<div class="caption-container-main">'
        +'<div class="caption-text-container">预定情况</div>'
        +'<div class="caption-bg "></div>'
        +'</div>'
        +'<div class="container"><ul class="collapsible badgecol" data-collapsible="accordion">'
        +'<li ng-repeat= "bus in books">'
        +'<div class="collapsible-header"><i class="material-icons text-lighten-2 materialize-red-text">info_outline</i>{{bus.userName}} ({{bus.userId}})</div>'
        +'<div class="collapsible-body"><p>预定单号: {{bus.id}}</p><p>会员账号: {{bus.userName}}</p><p>会员卡号:{{bus.userId}}</p><p>开始时间: {{bus.startData}}</p><p>结束时间: {{bus.overData}}</p><p>每天价格: ¥{{bus.price}}</p><p>总计房费: ¥{{bus.cost}}</p></div>'
        +'</li>'
        +'</ul></div>'
        +'</div>'
        +'</div>'
        +'<div class="col s12">'
        +'<div class="caption-container-main">'
        +'<div class="caption-text-container">财务情况</div>'
        +'<div class="caption-bg "></div></div>'
        +'<div><div id="main" style="margin-top:50px; width: 1000px;height:400px;"></div>'
        +'<div><div id="main1" style="margin-top:50px; width: 1000px;height:400px;"></div>'
        +'</div>'
        +'</div>'
        +'</div>'
        +'</div>'
        +'<div class="container" ng-show= active=="tab2"><ul class="collapsible badgecol" data-collapsible="accordion">'
        +'<li ng-repeat= "application in applications">'
        +'<div class="collapsible-header"><span ng-show= {{application.read}}==0 class="new badge">新申请</span><i ng-show= {{application.read}}==0 class="material-icons text-lighten-2 materialize-red-text">info_outline</i><i ng-show= {{application.read}}==1 class="material-icons text-lighten-2 materialize-red-text">done</i>{{application.name}}</div>'
        +'<div class="collapsible-body"><p>客栈名: {{application.name}}</p><p>申请时间: {{application.date}}</p><p>城市: {{application.city}}</p><p>地址: {{application.address}}</p><p>简介: {{application.info}}</p><p>营业执照: {{application.license}}</p><div ng-show= {{application.read}}==0 class="applicationbutton"><button  ng-click="pass(application)" class="applicationbuttonleft waves-effect waves-light btn">'
        +'通过</button><button  class="waves-effect waves-light btn">拒绝</button>' +
        '</div>'
        +'</li>'
        +'</ul></div>'
        +'<div class="container" ng-show= active=="tab3">'
        +'<div class="row ">'
        +'<div class="col s8">'
        +'<div class="col s12">'
        +'<div class="caption-container-main">'
        +'<div class="caption-text-container">等待结算</div>'
        +'<div class="caption-bg "></div>'
        +'</div>'
        +'<div>' +
        '<ul class="collapsible badgecol" data-collapsible="accordion">'
        +'<li ng-repeat= "checkout in checkouts">'
        +'<div class="collapsible-header"><i  class="material-icons text-lighten-2 materialize-red-text">info_outline</i>{{checkout.hostelName}} ({{checkout.hostelId}})</div>'
        +'<div class="collapsible-body"><p>客栈名: {{checkout.hostelName}}</p><p>会员名: {{checkout.userName}}</p><p>开始时间: {{checkout.startData}}</p><p>结束时间: {{checkout.overData}}</p><p>每天价格: ¥{{checkout.price}}</p><p>总计房费: ¥{{checkout.cost}}</p><button class="waves-effect waves-light btn"  ng-click="settlement(checkout)" style="margin: 15px; margin-left: 42%">结算</button>'
        +'</li>'
        +'</ul>'
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
        +'</div>'
        +'</div>'
        +'</div></div>'
    }
    return obj;
}]);