angular.module("myapp")
    .controller("bank_manageCtrl",function($scope){
        /*设置返回按钮*/
        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
        });
        $scope.banks = [
            {img: "images/bank.jpg", type: "中国农业银行", card: "储蓄卡",card_number:"9520"},
            {img: "images/bank.jpg", type: "中国建设银行", card: "储蓄卡",card_number:"9520"},
            {img: "images/bank.jpg", type: "中国邮政银行", card: "储蓄卡",card_number:"9520"}
        ];
        // 删除一个item
        $scope.delete = function(bank){
            // 先找到要删除对象在数组中的索引位置
            var index = $scope.banks.indexOf(bank);
            // 执行删除操作
            $scope.banks.splice(index,1);
        };
        // 重排
        $scope.reorder = function(bank,from,to){
            // 先在from索引处删除product
            $scope.banks.splice(from,1);
            // 在to索引处插入product
            $scope.banks.splice(to,0,bank);
        };
    })