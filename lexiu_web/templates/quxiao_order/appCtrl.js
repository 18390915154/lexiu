/**
 * Created by My on 2017/3/6.
 */
angular.module("myapp")
    .controller("cancelOrderCtrl",function($scope,$document,$state){
        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
        });
        var person=$document.find(".item")
        /*console.log("person.length")*/
        person.each(function(){
            $(this).on("click",function(){
                $(this).addClass("active").siblings().removeClass("active");
                if(this==1){
                    alert("ok")
                }
            })
        });
        $scope.reason1="临时有事，下次再说吧";
        $scope.reason2="等待时间过长";
        $scope.reason3="联系不上师傅";
        $scope.reason4="师傅以各种理由不来处理";
        $scope.reason5="师傅服务态度恶劣";
        $scope.reason6="价格太高";
        $scope.Map=function(){
            $state.go("map");
        }
    });