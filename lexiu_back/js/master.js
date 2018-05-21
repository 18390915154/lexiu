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
        url: "https://www.lerlex.com/lexiu/manage/worker/seachworker",
        data: ({
        }),
        type: "post",
        dataType: "json",
        success: function (data) {
            console.log(data);
            arr=data.workerlist;
            //遍历转换------------------------------------------------
            for(var i in data.workerlist){
                if(data.workerlist[i].isok==1){
                    data.workerlist[i].isok="已审核";
                }
                if(data.workerlist[i].isok==0){
                    data.workerlist[i].isok="未审核";
                }
                if(data.workerlist[i].typeid==0){
                    data.workerlist[i].typeid="普通电工";
                }
                if(data.workerlist[i].typeid==1){
                    data.workerlist[i].typeid="普通电工";
                }
                if(data.workerlist[i].typeid==2){
                    data.workerlist[i].typeid="资深电工";
                }
                if(data.workerlist[i].typeid==3){
                    data.workerlist[i].typeid="管道工";
                }
                if(data.workerlist[i].typeid==4){
                    data.workerlist[i].typeid="空调工";
                }
            }
            var newData=data.workerlist;
            console.log(newData);
            $('#generalForm').dataTable( {
                data: newData,
                columns: [
                    { data: 'nickname' },
                    { data: 'phone' },
                    { data: 'companiesTel' },
                    { data: 'companyname' },
                    { data: 'typeid' },
                    { data: 'skill' },
                    { data: 'money' },
                    { data: 'isok' }

                ],
                "columnDefs": [{
                    // 定义操作列--------------------------------
                    "targets": 8,//操作按钮目标列
                    "data": null,
                    "render": function (data, type, row) {
                        var index=$(this).index();
                        var id = '"' + row.id + '"';
                        console.log(row);
                        if(row.isok == "已审核"){
                            var html = "<i class='fa fa-arrow-up'>已审核</i>";
                        }else{
                            var html = "<a href='javascript:;' class='reviwe'><i class='fa fa-arrow-up'></i>审核</a>";
                        }
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
            //单击行-----------------------------------------
            $('#generalForm tbody').on('click', '.reviwe', function () {
                var index = $(this).index();
                var phone = $(this).parent().siblings().eq(1).text();
                window.location.replace("masterReview.html?phone=" + phone + "");
            });



        },
        error: function (res) {
            console.log(res);
        }
    });




});
