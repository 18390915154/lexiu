/**
 * Created by my on 2017/4/20.
 */
$(document).ready(function () {

    var id=$.getUrlParam('id');
    var arr=[];
    console.log(id);
    //导航切换
    showHide($("#userNavlist li"), "action");
    $.ajax({
        url: "https://www.lerlex.com/lexiu/manage/system/seachwithdraw",
        data: ({
        }),
        type: "post",
        dataType: "json",
        success: function (data) {
            console.log(data);
            arr=data.withdrawlist;
            //遍历转换------------------------------------------------
            for(var i in data.withdrawlist){
                if(data.withdrawlist[i].isok==1){
                    data.withdrawlist[i].isok="已审核";
                }
                if(data.withdrawlist[i].isok==0){
                    data.withdrawlist[i].isok="未审核";
                }
            }
            var newData=data.withdrawlist;
            console.log(newData);
            $('#generalForm').dataTable( {
                data: newData,
                columns: [
                    { data: 'withdrawid' },
                    { data: 'name' },
                    { data: 'monery' },
                    { data: 'account' },
                    { data: 'isok' }

                ],
                "columnDefs": [{
                    // 定义操作列--------------------------------
                    "targets": 5,//操作按钮目标列
                    "data": null,
                    "render": function (data, type, row) {
                        var index=$(this).index();
                        var id = '"' + row.id + '"';
                        //console.log(row);
                        if(row.isok == "已审核"){
                            var html = "<i class='fa fa-arrow-up'>已审核</i>";
                        }else{
                            var html = "<a href='javascript:;' class='reviwe'><i class='fa fa-arrow-up'></i>审核</a>";
                        }
                        return html;
                    }
                },
                {
                    "targets":[0],
                    "visible": true,
                    "searchable": true
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
            //单击行-----------------------------------------
            $('#generalForm tbody').on('click', '.reviwe', function () {
                var index = $(this).index();
                var withdrawid = $(this).parent().siblings().eq(0).text();
                console.log(withdrawid);
                $.ajax({
                    url: "https://www.lerlex.com/lexiu/manage/system/updatewithdraw",
                    data: ({
                        withdrawid:withdrawid
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
            });

        },
        error: function (res) {
            console.log(res);
        }
    });




});
