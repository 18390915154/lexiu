
$(document).ready(function () {
    var id=$.getUrlParam('id');
    var shopid=$.getUrlParam('shopid');
    console.log(shopid);
    $.ajax({
        url: "https://www.lerlex.com/lexiu/manage/shop/seachgoods",
        data: ({
            shopid:shopid
        }),
        type: "post",
        dataType: "json",
        success: function (data) {
            console.log(data);
            var newData = data.goodslist;
            console.log(newData);
            $('#generalForm').dataTable({
                data: newData,
                columns: [
                    {data: 'goodsid'},
                    {data: 'name'},
                    {data: 'score'},
                    {data: 'shopname'}
                ],
                "columnDefs": [{
                    // 定义操作列--------------------------------
                    "targets":4,//操作按钮目标列
                    "data": null,
                    "render": function (data, type, row) {
                        var id = '"' + row.id + '"';
                        var html = "<a href='javascript:;' class='delete'><i class='fa fa-arrow-up'></i>删除</a>"
                        html += "<a href='javascript:;'   class='edit'><i class='fa fa-arrow-down'></i> 编辑</a>"
                        /*html += "<a href='javascript:;'   class='add'><i class='fa fa-arrow-down'></i> 新增</a>"*/
                       /* html += "<a href='javascript:;'   class='back'><i class='fa fa-arrow-down'></i>返回</a>"*/
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
            //查看详情-----------------------------------------------------------
            //编辑-----------------------------------------------------------
            $('#generalForm tbody').on( 'click', '.edit', function () {
                var goodsid=$(this).parent().siblings().eq(0).text();
                window.location.replace("goodsedit.html?goodsid="+goodsid+"&shopid="+shopid+"");
            } );
            //删除--------------------------------------------------------
            $('#generalForm tbody').on( 'click', '.delete', function () {
                var goodsid=$(this).parent().siblings().eq(0).text();
                $.ajax({
                    url: "https://www.lerlex.com/lexiu/manage/shop/delgoods",
                    data: ({
                        goodsid:goodsid
                    }),
                    type: "post",
                    dataType: "json",
                    success: function (data) {
                        console.log(data);
                        if(data.message=="成功"){
                            alert(data.message);
                            window.location.reload();
                        }else {
                            alert(data.message);
                        }
                    },
                    error: function (res) {
                        console.log(res);
                    }
                });
            } );

        },
        error: function (res) {
            console.log(res);
        }
    });
    //返回--------------------------------------------------------
    $('.back').on( 'click', function () {
        window.location.replace("storeinformation.html");
    } );
    //新增--------------------------------------------------------
    $('.add').on( 'click', function () {
        window.location.replace("goodsadd.html?shopid="+shopid+"");
    } );
});