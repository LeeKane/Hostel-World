/**
 * Created by LeeKane on 17/3/18.
 */
var app= angular.module('hostelInfoApp',[]);
app.controller('hostelInfoController',function ($scope,$http) {
    var priceElement=angular.element('.hostelPrice')[0];
    $scope.price=0+priceElement.innerText;
    var levelElement=angular.element('#level')[0];
    $scope.level=0+levelElement.innerText;
    $scope.days=7;
    var  startDate  =  new  Date  ();
    var  intValue  =  0;
    var  endDate  =  null;
    intValue  =  startDate.getTime();
    intValue  +=  7  *  (24  *  3600  *  1000);
    endDate  =  new  Date  (intValue);
    $scope.startData=(startDate.getMonth()+1)+"/"+ startDate.getDate()+"/"+startDate.getFullYear();
    $scope.overData=(endDate.getMonth()+1)+"/"+ endDate.getDate()+"/"+endDate.getFullYear();

    $scope.total=function()
    {
        var start=$scope.startData.split("/");
        oDate1  =  new  Date(start[0]  +  '-'  +  start[1]  +  '-'  +  start[2]);
        var over=$scope.overData.split("/");
        oDate2  =  new  Date(over[0]  +  '-'  +  over[1]  +  '-'  +  over[2]);
        $scope.days  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24)  ;
        $scope.cost=$scope.days* $scope.price;
        if($scope.level==1)
        {
            $scope.cost=$scope.cost*0.9;
        }
        if($scope.level==2)
        {
            $scope.cost=$scope.cost*0.8;
        }
        if($scope.level==3)
        {
            $scope.cost=$scope.cost*0.7;
        }
        $scope.cost=Math.round($scope.cost*100)/100
        return $scope.cost;
    }
    $scope.addBook=function () {
        var hostelIdElement=angular.element('#hostelId')[0];
        $scope.hostelId=hostelIdElement.innerText;
        var hostelNameElement=angular.element('#hostelName')[0];
        $scope.hostelName=hostelNameElement.innerText;
        var userIdElement=angular.element('#userId')[0];
        $scope.userId=userIdElement.innerText;
        var userNameElement=angular.element('#userName')[0];
        $scope.userName=userNameElement.innerText;
        $http({
            method: "POST",
            url: "/HostelWorld/addBookBusniess.do",
            data: {userId:$scope.userId,userName:$scope.userName,hostelId:$scope.hostelId,hostelName:$scope.hostelName,startData:$scope.startData,overData:$scope.overData,price:$scope.price,cost:$scope.cost}
        }).success(function (data, status){
            Materialize.toast('预定成功!您可以在您的预定中查看预定信息', 6000);
        }).error(function(data, status){

        })
    }
});