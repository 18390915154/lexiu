/**
 * Created by my on 2017/4/24.
 */
$(document).ready(function () {
    var id = $.getUrlParam('id');
    $.ajax({
        url: "https://www.lerlex.com/lexiu/manage/banner/myfeedback",
        data: ({}),
        type: "post",
        dataType: "json",
        success: function (data) {
            console.log(data);
            for(var i in data.feedback){
                if(data.feedback[i].type==0){
                    data.feedback[i].type="订单问题";
                }else if(data.feedback[i].type==1){
                    data.feedback[i].type="服务问题";
                }else if(data.feedback[i].type==2){
                    data.feedback[i].type="APP相关问题";
                }else if(data.feedback[i].type==3){
                    data.feedback[i].type="其他问题";
                }
            }
            var newData = data.feedback;
            console.log(newData);
            $('#generalForm').dataTable({
                data: newData,
                columns: [
                    {data: 'content'},
                    {data: 'type'},
                    {data: 'name'}

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

        },
        error: function (res) {
            console.log(res);
        }
    });
});