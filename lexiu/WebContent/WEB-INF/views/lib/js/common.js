/**
 * 
 */
var testMenu;
$(function(){
	//通过ajax获取菜单信息
	$.ajax({
		type: "POST",
        url: basePath+"urlController/selectUrl",
        dataType: "json",
        success: function(result){
          //返回提示信息
          if(!result.SUCCESS){
        	  alert(result.errorMsg);
        	  window.location=basePath+"accountUserController/toLoginPage";
          }
          testMenu=[];
          $.each(result.result,function(index,menu){
        	  var parentMenu={
        			  id:menu.url.fUrlId,
                      url:basePath+menu.url.fUrl,
                      name:menu.url.fUrlName, 
                      children:[]
        	  }
        	  $.each(menu.childrenUrl,function(index1,child){
        		  var child={
        				  id:child.fUrlId,
                          url:basePath+child.fUrl,
                          name:child.fUrlName, 
                          children:null 
        		  }
        		  parentMenu.children.push(child);
        	  })
        	  testMenu.push(parentMenu);
        	  console.info("菜单数据处理后",testMenu);
          })
      console.info("菜单数据处理后",testMenu);
      		initMenus();
        }
	})
	initDataTableConfig();
})
/**
 * dataTable默认配置
 */
function initDataTableConfig(){
    // 默认禁用搜索和排序
    $.extend( $.fn.dataTable.defaults, {
      searching: false,
      ordering:  false,
      lengthChange: false
    } );
}
/**
 * 初始化侧边菜单
 */
function initMenus(){
              var menuContainer=$("#menu-container").empty();
              $.each(testMenu,function(index,menu){
                if(menu.children==null){
                  $('<li><a href="#">'+menu.name+'</a></li>').appendTo(menuContainer).click(function(){
                    window.location.href=menu.url;
                  });
                }else{
                  menuContainer.append('<li class="admin-parent"> ' +
                    '<a class="am-cf" data-am-collapse="{target: \'#'+menu.id+'\'}">'+menu.name +
                    '<span class="am-icon-angle-right am-fr am-margin-right"></span></a>' +
                    '<ul class="am-list am-collapse admin-sidebar-sub" id="'+menu.id+'"></ul>'+
                    '</li>');
                  var parent=menuContainer.find("#"+menu.id);
                  $.each(menu.children,function(index1,menu1){
                    $('<li><a href="#">'+menu1.name+'</a></li>').appendTo(parent).click(function(){
                      window.location.href=menu1.url;
                    })
                  })
                }
              })
            }