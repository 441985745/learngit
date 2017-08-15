<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="easywin" style="display: none;" >
    <label class="sr-only" for="inputFile" style="margin-left: 5px;">选择文件</label>
    <input type="file" accept=".xlsx" id="inputFile" name="inputFile" style="margin: 5px 0px;" />
    <br/>
    <input type="button" id="downloadBtn" value="下载模版" class="btn" style="margin: 5px 5px;" />
  
    <input type="button" id="confirmImport" value="确定导入" class="btn" />
</div>

<script type="text/javascript">
  	//document.domain="opple.com";
	function parseXML(text) {
	    var doc;
	    if(window.DOMParser) {
	        var parser = new DOMParser();
	        doc = parser.parseFromString(text, "text/xml");
	    }
	    else if(window.ActiveXObject) {
	        doc = new ActiveXObject("Microsoft.XMLDOM");
	        doc.async = "false";
	        doc.loadXML(text);
	    }
	    else {
	        throw new Error("Cannot parse XML");
	    }
	    return doc;
	}

    function EasyWindow() {
        var _title = "";
        var _type = "";
        var _uploadUrl = "";
        var _downloadUrl = "";

        var initEasyWindow = function(title, type, uploadUrl,callback) {
            this._title = title;
            this._type = type;
            this._uploadUrl = uploadUrl;

            var fileName="";
            var that = this;
            var argumentsCnt = initEasyWindow.arguments.length;
		
            // bind download event
            var $downloadBtn = $('#downloadBtn');
            $downloadBtn.unbind('click');
            $downloadBtn.click(function() {
                switch (that._type) {
                    case "新增" :
                        fileName="预算调整-新增导入模板.xlsx";
                        break;
                    default :
                        fileName="预算调整导入模板.xlsx";
                }
                var downloadUrl = "${dynamicURL}/download/downLoadFile?downloadFileName=" + encodeURIComponent(fileName);
                var w = window.open(downloadUrl, '_blank');
                w.location.href = downloadUrl;
                return false;
            });

            // bind import event
            var $confirmImport = $('#confirmImport');
            $confirmImport.unbind('click');
            $confirmImport.click(function() {

                $('#confirmImport').val("导入ing");
                $('#confirmImport').attr("disabled",true);
                var file = $('#inputFile').val();
                var fileType = file.substring(file.lastIndexOf(".") + 1);
                if (fileType != "xlsx") {
                    alert("上传文件格式错误！！");
                    $('#confirmImport').val("确定导入");
                    $('#confirmImport').attr('disabled',false);
                    return;
                } else {
                    // begin uploading...
                    $(".loding_anim").show();
                }

                $.ajaxFileUpload({
                    url: that._uploadUrl,
                    secureuri: false,
                    fileElementId: 'inputFile',
                    dataType: 'text/html',
                    data: {type : that._type},
                    success: function (data) {

                        var result = [];
                        result = data.split("<");
                        data = JSON.parse(result[0]);
                        if (data.success) {
                            alert("数据全部导入成功!");
                            $('#confirmImport').val("确定导入");
                            $('#confirmImport').attr('disabled',false);
                            callback(data);
                        } else {
                            alert(data.errorNull);
                            $('#confirmImport').val("确定导入");
                            $('#confirmImport').attr('disabled',false);
                        }

                        /*var dataObj = eval('(' + data + ')');
                        if (dataObj.success) {
                            alert("数据全部导入成功!");
                            $('#confirmImport').attr('disabled',false);
                            callback(dataObj);
                        } else {
                            alert(dataObj.errorNull);
                            $('#confirmImport').attr('disabled',false);
                        }*/

                        $(".loding_anim").hide();
                        $("#easywin").window('close');
                    },
                    error: function (data, status, e) {
                        var xml = parseXML(data.responseText);
                        var jsonObj = JSON.parse($(xml).find('pre').text());
                        alert(jsonObj.errorNull);
                        $('#confirmImport').val("确定导入");
                        $('#confirmImport').attr('disabled',false);
                    }
                });
            });

        };

        var showEasyWindow = function() {
            var $easywin = $('#easywin');

            $easywin.show();
            $easywin.window({
                modal: true,
                title: this._title,
                width: 400,
                height: 120,
                collapsible: false,
                minimizable: false,
                maximizable: false
            });

        };

        return {
            initEasyWindow: initEasyWindow,
            showEasyWindow: showEasyWindow
        };
    }

</script>
