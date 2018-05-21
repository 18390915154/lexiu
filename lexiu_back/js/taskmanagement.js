/**
 * Created by my on 2017/4/19.
 */
$(document).ready(function () {
    var id = $.getUrlParam('id');
    console.log(id);
    //导航切换
    $.ajax({
        url: "https://www.lerlex.com/lexiu/manage/order/seachorder",
        data: ({}),
        type: "post",
        dataType: "json",
        success: function (data) {
            console.log(data);
            console.log(data.orderlist.delflg);
            for(var i in data.orderlist){
                if(data.orderlist[i].delflg==0){
                    data.orderlist[i].delflg="正常";
                }else{
                    data.orderlist[i].delflg="已取消";
                }
            }
            var newData = data.orderlist;
            console.log(newData);
            $('#generalForm').dataTable({
                data: newData,
                columns: [
                    {data: 'ordernum'},
                    {data: 'workname'},
                    {data: 'username'},
                    {data: 'price'},
                    {data: 'worktypename'},
                    {data: 'typename'},
                    {data: 'delflg'},
                    {data: 'formatstart'},
                    {data: 'formatover'}

                ],
                "order": [[ 7, "desc" ]],
                "columnDefs": [{
                    // 定义操作列--------------------------------
                    "targets":9,//操作按钮目标列
                    "data": null,
                    "render": function (data, type, row) {
                        var id = '"' + row.id + '"';
                        var html = "<a href='javascript:;' class='edit'><i class='fa fa-arrow-up'></i> 编辑</a>"
                        html += "<a href='javascript:;'   class='appraise'><i class='fa fa-arrow-down'></i> 评价详情</a>"
                        html += "<a href='javascript:;'   class='delete'><i class='fa fa-arrow-down'></i> 删除</a>"
                        return html;
                    }

                }],
                //插件汉化--------------------------------
                "scrollY": "500px",
                "scrollCollapse": "true",
                "paging": "false",
                "oLanguage": {
                    "sLengthMenu": "每页显示 _MENU_ 条记录",
                    "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                    "sInfoEmpty": "没有数据",
                    "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
                    "oPaginate": {
                        "sFirst": "首页",
                        "sPrevious": "前一页",
                        "sNext": "后一页",
                        "sLast": "尾页"
                    },
                    "sSearch": "搜索",
                    "sZeroRecords": "没有检索到数据",
                    "bPaginate": true, //翻页功能
                    "bLengthChange": true, //改变每页显示数据数量
                    "bFilter": true, //过滤功能
                    "bSort": false, //排序功能
                    "bInfo": true,//页脚信息
                    "bAutoWidth": true//自动宽度

                }
            });
            //单击-----------------------------------------------------------
            //编辑-----------------------------------------------------------
            $('#generalForm tbody').on( 'click', '.edit', function () {
                //alert($(this).parent().siblings().eq(0).text());
                var ordernum=$(this).parent().siblings().eq(0).text();
                var type=$(this).parent().siblings().eq(5).text();
                if(type=="待开始订单"||type=="待报结订单"){
                    alert("此订单不可编辑");
                }else {
                    $("#editBox").show();
                    $("#taskBox").hide();
                    $("#ordernum").val(ordernum);
                    $.ajax({
                        url: "https://www.lerlex.com/lexiu/manage/order/orderdetail",
                        data: ({
                            ordernum:ordernum
                        }),
                        type: "post",
                        dataType: "json",
                        success: function (data) {
                            console.log(data);
                            $("#money").val(data.order.price);
                            $("#server").val(data.order.content);
                            //$("#workertype").val(data.order.worktypename);
                            $("#fault").val(data.order.fault);
                        },
                        error: function (res) {
                            console.log(res);
                        }
                    });
                }

            } );
            //评价详情-----------------------------------------------------------
            $('#generalForm tbody').on( 'click', '.appraise', function () {
                //alert($(this).parent().siblings().eq(0).text());
                var ordernum=$(this).parent().siblings().eq(0).text();
                var type=$(this).parent().siblings().eq(5).text();
                if(type!=="已完成订单"){
                    alert("此订单尚未完成");
                }else {
                    $("#appraiseBox").show();
                    $("#taskBox").hide();
                    $.ajax({
                        url: "https://www.lerlex.com/lexiu/manage/order/searchassess",
                        data: ({
                            ordernum:ordernum
                        }),
                        type: "post",
                        dataType: "json",
                        success: function (data) {
                            console.log(data);
                            $("#evaluation1").val(data.assess.grade);
                            $("#evaluation2").val(data.assess.content);
                            $("#evaluation3").val(data.assess.detail);                        },
                        error: function (res) {
                            console.log(res);
                        }
                    });
                }

            } );
            //删除--------------------------------------------------------
            $('#generalForm tbody').on( 'click', '.delete', function () {
                //alert($(this).parent().siblings().eq(0).text());
                var ordernum=$(this).parent().siblings().eq(0).text();
                $.ajax({
                    url: "https://www.lerlex.com/lexiu/manage/order/delorder",
                    data: ({
                        ordernum:ordernum
                    }),
                    type: "post",
                    dataType: "json",
                    success: function (data) {
                        console.log(data);
                        alert(data.message);
                        window.location.reload();

                    },
                    error: function (res) {
                        console.log(res);
                    }
                });
            } );
            //取消---------------------------------------------------------------
            $("#cancelBtn").on("click",function () {
                $("#editBox").hide();
                $("#taskBox").show();
            });
            $("#appraiseBtn").on("click",function () {
                $("#appraiseBox").hide();
                $("#taskBox").show();
            });
            //提交------------------------------------------------------------------
            $("#editBtn").on("click",function () {
                //alert($("#workertype").val());
                if($("#money").val()==""||$("#money").val()==null||$("#server").val()==""||$("#server").val()==null||$("#workertype").val()==""||$("#workertype").val()==null||$("#fault").val()==""||$("#fault").val()==null){
                    alert("请填写修改内容");
                }else{
                    $.ajax({
                        url: "https://www.lerlex.com/lexiu/manage/order/updateorder",
                        data: ({
                            ordernum:$("#ordernum").val(),
                            price:$("#money").val(),
                            content:$("#server").val(),
                            worktype:$("#workertype").val(),
                            fault:$("#fault").val()
                        }),
                        type: "post",
                        dataType: "json",
                        success: function (data) {
                            if(data.message=="成功"){
                                alert(data.message);
                                window.location.reload();
                            }

                        },
                        error: function (res) {
                            console.log(res);
                        }
                    });
                }

            })


        },
        error: function (res) {
            console.log(res);
        }
    });


});