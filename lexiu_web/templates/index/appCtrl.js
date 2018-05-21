
angular.module("myapp")
	.controller("indexCtrl", function($interval) {
        //获取地理位置方法
        $interval(function(){
            startgps();
		},300000);
        startgps();
        function startgps(){
            //判断是否支持
            if (navigator.geolocation){
                navigator.geolocation.getCurrentPosition(showgps,//成功回调函数
                    function(error){ //失败回调函数
                        showError(error);
                    },
                    {enableHighAcuracy: true, timeout:5000,maximumAge: 0}); // 这里设置超时为5000毫秒，即1秒
            }
        };
        function showgps(position){
            var lng = position.coords.longitude;
            var lat = position.coords.latitude;
            var ggPoint = new BMap.Point(lng,lat);
            map.addControl(new BMap.NavigationControl());
            //坐标转换完之后的回调函数
            translateCallback = function (data){
                if(data.status === 0) {
                    var icon = new BMap.Icon("images/green.png", new BMap.Size(23, 25));
                    var marker = new BMap.Marker(data.points[0], {icon: icon});  // 创建标注'
                    map.addOverlay(marker);
                    //map.setCenter(data.points[0]);
                    map.centerAndZoom(data.points[0], 16);
                    this_lng=marker.point.lng;
                    this_lat=marker.point.lat ;
                    //返回后台经纬度
                    $.ajax({
                        url: "https://www.lerlex.com/lexiu/api/worker/updategps",
                        data:({
                            longitude:this_lng,
                            latitude:this_lat
                        }),
                        type: "get",
                        dataType: "jsonp",
                        jsonp: "jsonpcallback",
                        success: function(data) {
                            localStorage.setItem("longitude",this_lng);
                            localStorage.setItem("latitude",this_lat);
                            console.log(this_lng)
                            console.log(this_lat)
                        },
                        error: function(data) {
                            console.log(data)
                        }
                    })
                }
            };
            var convertor = new BMap.Convertor();
            var pointArr = [];
            pointArr.push(ggPoint);
            convertor.translate(pointArr, 1, 5, translateCallback)
        }
        function showError(error){
            switch(error.code) {
                case error.PERMISSION_DENIED:
                    alert("定位失败,用户拒绝请求地理定位");
                    break;
                /*case error.POSITION_UNAVAILABLE:
                    alert("定位失败,位置信息是不可用");
                    break;
                case error.TIMEOUT:
                    alert("定位失败,请求获取用户位置超时");
                    break;*/
                case error.UNKNOWN_ERROR:
                    alert("定位失败,定位系统失效");
                    break;
            }
        };
});