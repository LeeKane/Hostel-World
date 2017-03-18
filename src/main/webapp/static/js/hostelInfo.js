/**
 * Created by LeeKane on 17/3/18.
 */
var app= angular.module('hostelInfoApp',[]);
app.controller('hostelInfoController',function ($scope) {
    var priceElement=angular.element('.hostelPrice')[0];
    $scope.price=0+priceElement.innerText;
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
        return $scope.days* $scope.price;
    }
});