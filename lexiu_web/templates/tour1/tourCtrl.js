angular.module("myapp")
    .controller("tour1Ctrl",function($scope,$interval,$state){
        var number=2;
        var timer=$interval(function(){
            number--;
            if(number==0){
                $interval.cancel(timer);
                $state.go("tabs.home");
            }
            console.log(number)
        },1000);
        $scope.jump=function(){
            $state.go("tabs.home");
            $interval.cancel(timer);
        };
        $scope.clear=function(){
            $interval.cancel(timer);
        }
    });