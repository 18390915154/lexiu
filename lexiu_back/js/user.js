/**
 * Created by my on 2017/4/20.
 */
$(document).ready(function () {
    var id=$.getUrlParam('id');
    console.log(id);
    //导航切换
    showHide($("#userNavlist li"), "action");
    $.ajax({
        url: "https://www.lerlex.com/lexiu/manage/user/seachuser",
        data: ({
        }),
        type: "post",
        dataType: "json",
        success: function (data) {
            console.log(data);
            var newData=data.userlist;
            console.log(newData);
            $('#generalForm').dataTable( {
                data: newData,
                columns: [
                    { data: 'nickname' },
                    { data: 'address' },
                    { data: 'phone' },
                    { data: 'money' },
                    { data: 'age' },
                    { data: 'sex' }
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
            } )
        },
        error: function (res) {
            console.log(res);
        }
    });




});
