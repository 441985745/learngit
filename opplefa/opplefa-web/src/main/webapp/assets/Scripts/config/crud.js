document.domain = 'opple.com';

//解决ie报错 对象没有trim属性或方法
String.prototype.trim = function () {
	return this .replace(/^\s\s*/, '' ).replace(/\s\s*$/, '' );
};

function downLoadFile(fileUId){    
   	var url="${dynamicURL}/fileUpload/downloadModel?fineUId="+fileUId;
	window.location.href=url;
}

function addReady(dId,url,title){
	
    var parentDG = new $.dialog({
        id: dId,
        page: url,
        cover: true,
        autoPos: {
            left: 'center',
            top: 'center'
        },
        width: 800,
        height: 400,
        title: title,
        iconTitle: false,
        btnBar: false
    });
    parentDG.ShowDialog();
}

function add (th,tableId,formId,url,boo) {//值：num 小数位：del o为true返回值为正数,否则为负数
	var curBut = $(th);
	curBut.attr("disabled","disabled");
	var a=true;
	 $("#"+tableId+" .require-red").each(function(){
		if(undefined==$(this).val().trim()||$(this).val().trim()===""){
			alert("请核对必填信息项");
			a=false;
			return false;
		}
	});
	if(a){
		$.ajax({
			cache: true,
			type: "POST",

			url:url,

			data:$('#'+formId).serialize(),// 你的formid

			async: false,

		    error: function(request) {
		        alert("抱歉，此次添加未成功，可继续添加");

		    },

		    success: function(data) {
		    	if(data.successMessage==="保存成功"){
		    		alert("保存成功");
					reloadPage();
		    	}
		    	else{
		    		alert(data.successMessage);
		    	}
		    }
		});
	}
	curBut.removeAttr("disabled");
}
function delAdd(tableId){
	$("#"+tableId+" input:checkbox").each(function(index,el){
		 if($(this).is(':checked'))
		 $(this).parent().parent().remove();
	});
}

function opUpdatedlg(dId, title, url) {

        var parentDG = new $.dialog({
            id: dId,
            page: url,
            cover: true,
            autoPos: {
                left: 'center',
                top: 'center'
            },
            width: 800,
            height: 400,
            title: title,
            iconTitle: false,
            btnBar: false
        });
        parentDG.ShowDialog();

    }

function update(th,tableId,formId,url){
	var curBut = $(th);
	curBut.attr("disabled","disabled");
	var a=true;
	 $("#"+tableId+" .require-red").each(function(){
		if(undefined==$(this).next().val().trim()||$(this).next().val().trim()==""){
			alert("请核对必填信息项");
			a=false;
			return false;
		}
	});
	if(a){
		$.ajax({
			cache: true,
			type: "POST",

			url:url,

			data:$('#'+formId).serialize(),// 你的formid

			async: false,

		    error: function(request) {
		        alert("抱歉，此次修改未成功");
		    },

		    success: function(data) {	
		    	if("修改成功"==data.successMessage){
		    		alert("修改成功");
					reloadPage();
		    	}
		    	else{
		    		alert(data.successMessage);
		    	}
		    }

		});
	}
	curBut.removeAttr("disabled");
}

function deletes(checkBoxName,url,codesParam){
	var assetsCodes=[];
	var data = {};
    $("input[name='"+checkBoxName+"']:checked").each(function () {
       var aCode = $(this).attr("value");
       assetsCodes.push(aCode);
    });
    data[codesParam]=assetsCodes;
    if (assetsCodes===[]) {
        alert("请选择要删除的数据。");
        return false;
    }
    $.ajax({
        type: "POST",
        dataType: "json",
        url : url,
        async:false,
        data: data,
        success: function(data) {
            alert(data.successMessage);

        },
        error: function(request) {
	        alert("抱歉，此次删除未成功");

	    },
    });
}

function importReady(url,dId,title){
    var parentDG = new $.dialog({
        id: dId,
        page: url,
        cover: true,
        autoPos: {
            left: 'center',
            top: 'center'
        },
        width: 450,
        height: 172,
        title: title,
        iconTitle: false,
        btnBar: false
    });
    parentDG.ShowDialog();
}

function importFile(th,fileId,url){
	var curBut = $(th);
	curBut.attr("disabled","disabled");
		var file = $("#"+fileId).val();
		if (file == null || file == '') {
			alert('上传excel文件不能为空！');
			return;
		}else{
			var filename = file.replace(/.*(\/|\\)/, "");
			var fileExt = filename.substring( filename.lastIndexOf(".") + 1 , filename.length );
			if (fileExt != 'xlsx') {
				alert("上传文件必须为后缀名为xlsx的excel文件！");
				return;
			}
		}
		
		$.ajaxFileUpload({
			url: url,
            secureuri: false,
            fileElementId:['fileToUpload'],
            dataType:'json', 
			success: function (data, status){
				if(data.success){
				alert("导入成功");
				reloadPage();
				} else{
					alert("操作失败:"+data.message);
				}
			},  
			error: function (data, status, e){
				alert("操作失败:"+e);
			}
        });
		curBut.removeAttr("disabled");
}

function searchData(url) {
    var beginDate = $("#updateDateBegin").val();
    var endDate = $("#updateDateEnd").val();
    if(beginDate && endDate){
	    var d1 = new Date(beginDate.replace(/\-/g, "\/"));
	    var d2 = new Date(endDate.replace(/\-/g, "\/"));

	    if (beginDate != "" && endDate != "" && d1 >= d2) {
	        alert("开始时间不能大于结束时间！");
	        return false;
	    }
    }
    window.location.href = url;
}

function reloadPage() {
    dg.curWin.location.reload();
}
