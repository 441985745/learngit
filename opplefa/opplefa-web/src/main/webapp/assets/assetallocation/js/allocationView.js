document.domain = 'opple.com';
var dg = frameElement.lhgDG;

	//返回关闭申请弹窗
	$("#fanHui").click(function(){
		//关闭窗口
	    dg.cancel();
	});
	
	//全选按钮 
	 $("#checkAll").click(function(){ 
		 if($(this).checked){
			 $("[name='radio']").removeAttr("checked");//取消全选 
		 }else{
			 $("input[name='radio']").prop("checked", $(this).prop("checked"));
		 }
		 
	});