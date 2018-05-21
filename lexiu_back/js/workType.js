
$(document).ready(function () {
    var id=$.getUrlParam('id');
    $.ajax({
        url: "https://www.lerlex.com/lexiu/manage/system/seachtype",
        data: ({
        }),
        type: "post",
        dataType: "json",
        success: function (data) {
            console.log(data);
            var newData = data.typelist;
            console.log(newData);
            $('#generalForm').dataTable({
                data: newData,
                columns: [
                    {data: 'typeid'},
                    {data: 'typenum'},
                    {data: 'name'}
                ],
                "columnDefs": [{
                    // 定义操作列--------------------------------
                    "targets":3,//操作按钮目标列
                    "data": null,
                    "render": function (data, type, row) {
                        var id = '"' + row.id + '"';
                        var html ="<a href='javascript:;'   class='delete'><i class='fa fa-arrow-down'></i>删除</a>"
                        return html;
                    }

                },
                {
                    "targets":0,
                    "visible": true,
                    "searchable": true
                }
                ],
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
            //删除--------------------------------------------------------
            $('#generalForm tbody').on( 'click', '.delete', function () {
                var workid=$(this).parent().siblings().eq(0).text();
                
                $.ajax({
                    url: "https://www.lerlex.com/lexiu/manage/system/deltype",
                    data: ({
                        typeid:workid
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
    //新增--------------------------------------------------------
    $('.add').on( 'click',function () {
        window.location.replace("workadd.html");
    } );
});
