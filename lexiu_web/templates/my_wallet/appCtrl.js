angular.module("myapp")
    .controller("walletCtrl",function($scope,$stateParams,$http,$state, $ionicPopup){
        $scope.id=$stateParams.id;
        $scope.back=function () {
            $state.go("tabs.person") ;
        };
        //ajax
        $.ajax({
            url:"https://www.lerlex.com/lexiu/api/mine/mywallet",
            type: "get",
            dataType: "jsonp",
            jsonp:"jsonpcallback",
            success: function (data) {
                console.log(data);
                var Obj1 = eval('(' + data[1].value + ')');
                var Obj2 = eval('(' + data[2].value + ')');
                console.log(Obj1);
                console.log(Obj2);
                if(data.message == null || data.message == "成功") {
                    $scope.number = data[1].value;
                    var Obj3 = eval('(' + data[2].value + ')');
					console.log(Obj3);
					var a = new Array(Obj3.length);
					for(i = 0; i < Obj3.length; i++) {
						a[i] = Obj3[i].formattime;
					}
					//alert(a.unique3());
					var b = a.unique3();
					//alert(b)
					for(var i = 0; i < b.length; i++) {
						var txt1 = "<dt>" + b[i] + "</dt>";
						$('#wallet_jl').append(txt1);
						for(var j = 0; j < Obj3.length; j++) {
							if(b[i] == Obj3[j].formattime) {
								if(Obj3[j].type == 0){
									var tet3 = $("<dd><span class='wall_span'>-￥" + Obj3[j].money + "</span><span>" + Obj3[j].detail + "</span></dd>");
									$('#wallet_jl').append(tet3);
								}else if(Obj3[j].type == 1){
									var tet3 = $("<dd><span class='wall_span'>+￥" + Obj3[j].money + "</span><span>" + Obj3[j].detail + "</span></dd>");
									$('#wallet_jl').append(tet3);
								}else if(Obj3[j].type == 2){
									var tet3 = $("<dd><span class='wall_span'>-￥" + Obj3[j].money + "</span><span>" + Obj3[j].detail + "</span></dd>");
									$('#wallet_jl').append(tet3);
								}
							}
						}
					}
                }else {
                    $ionicPopup.alert({
                        template: '登录超时', // String (可选)。放在弹窗body内的html模板。
                        okText: '确定', // String (默认: 'OK')。OK按钮的文字。
                    });
                }
                $scope.$apply();
            }

        })
        
		Array.prototype.unique3 = function() {
			var res = [];
			var json = {};
			for(var i = 0; i < this.length; i++) {
				if(!json[this[i]]) {
					res.push(this[i]);
					json[this[i]] = 1;
				}
			}
			return res;
		}
    })