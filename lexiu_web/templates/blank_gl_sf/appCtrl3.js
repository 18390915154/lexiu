angular.module("myapp")
    .controller("bank_manage_sfCtrl",function($scope,$ionicModal){
        /*设置返回按钮*/
        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
        });
        $scope.banks = [
            {img: "images/ny.png", type: "中国农业银行", card: "储蓄卡",card_number:"9520"},
            {img: "images/js.png", type: "中国建设银行", card: "储蓄卡",card_number:"9520"},
            {img: "images/gs.png", type: "中国工商银行", card: "储蓄卡",card_number:"9520"}
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
        $ionicModal.fromTemplateUrl('delete.html', {
            scope: $scope,
            animation: 'slide-in-up'
        }).then(function(modal) {
            $scope.modal = modal;
        });
        $scope.openModal = function() {
            $scope.modal.show();
        };
        $scope.closeModal = function() {
            $scope.modal.hide();
        };
    })