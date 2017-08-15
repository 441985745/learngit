<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="easywin" style="display: none;">
    <label class="sr-only" for="uploadFile" style="margin-left: 5px;">选择文件</label>
    <input type="file" accept=".xlsx" id="uploadFile" name="uploadFile" style="margin: 5px 0px;"/>
    <br/>
    <input type="button" id="downloadBtn" value="下载模版" class="btn" style="margin: 5px 5px;"/>

    <input type="button" id="confirmImport" value="确定导入" class="btn"/>
</div>

<script type="text/javascript">
    //document.domain="opple.com";
    function parseXML(text) {
        var doc;

        if (window.DOMParser) {
            var parser = new DOMParser();
            doc = parser.parseFromString(text, "text/xml");
        }
        else if (window.ActiveXObject) {
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
        var _uploadUrl = "";
        var _downloadUrl = "";

        var initEasyWindow = function (title, uploadUrl, callback) {
//            console.log(title);
            this._title = title;
            this._uploadUrl = uploadUrl;
            var fileName = "";

            var that = this;

            var argumentsCnt = initEasyWindow.arguments.length;

            // bind download event
            var $downloadBtn = $('#downloadBtn');
            $downloadBtn.unbind('click');
            $downloadBtn.click(function () {
                fileName = "预算体成本中心模板.xlsx";
                var downloadUrl = "${dynamicURL}/download/downLoadFile?downloadFileName=" + encodeURIComponent(fileName);
                var w = window.open(downloadUrl, '_blank');
                w.location.href = downloadUrl;
                return false;
            });

            // bind import event
            var $confirmImport = $('#confirmImport');
            $confirmImport.unbind('click');
            $confirmImport.click(function () {

                $('#confirmImport').val("导入ing");
                $('#confirmImport').attr("disabled", true);
                var file = $("input[name='uploadFile']").val();
                var fileType = file.substring(file.lastIndexOf(".") + 1);
                if (fileType != "xlsx") {
                    alert("上传文件格式错误！！");
                    $('#confirmImport').val("确定导入");
                    $('#confirmImport').attr('disabled', false);
                    return;
                } else {
                    // begin uploading...
                    $(".loding_anim").show();
                }

                $.ajaxFileUpload({
                    url: that._uploadUrl,
                    secureuri: false,
                    fileElementId: 'uploadFile',
                    dataType: 'text/html',
                    data: JSON.stringify({}),
                    success: function (data) {
                        var result = [];
                        result = data.split("<");
                        data = JSON.parse(result[0]);
                        if (data.success) {
                            alert("数据全部导入成功!");
                            $('#confirmImport').val("确定导入");
                            $('#confirmImport').attr('disabled', false);
                            document.location.reload();
                        } else {
                            alert(data.errorNull);
                            $('#confirmImport').val("确定导入");
                            $('#confirmImport').attr('disabled', false);
                        }

                        /*var jsonObj = eval('(' + data + ')');
                         //                        var xml = parseXML(data);
                         //                        var jsonObj = JSON.parse($(xml).find('pre').text());
                         if (jsonObj.success) {
                         alert("数据全部导入成功!")
                         document.location.reload();
                         } else {

                         alert(jsonObj.errorNull);
                         }*/

                        $(".loding_anim").hide();
                    },
                    error: function (data, status, e) {

                        var xml = parseXML(data.responseText);
                        var jsonObj = JSON.parse($(xml).find('pre').text());
                        alert(jsonObj.errorNull);
                        $('#confirmImport').val("确认导入");
                        $('#confirmImport').attr('disabled', false);
                    }
                });
            });

        };

        var showEasyWindow = function () {
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
