document.domain='opple.com';
var parentWindow;

if (navigator.appName == 'Microsoft Internet Explorer') {//如果是IE
    parentWindow = window.parent.dialogArguments;
}
else {
    parentWindow = window.opener;
}

var windowDialogWidth = 1000;
var windowDialogHeight = 600;

if (window.screen.availWidth < 1024)
    windowDialogWidth = window.screen.availWidth - 100;

if (window.screen.availHeight < 768)
    windowDialogHeight = window.screen.availHeight - 100;

/*
* 去前后空格
**/
function trim(str) {
    if (str != null) {
        var i;
        for (i = 0; i < str.length; i++) {
            if (str.charAt(i) != " ") {
                str = str.substring(i, str.length);
                break;
            }
        }
        for (i = str.length - 1; i >= 0; i--) {
            if (str.charAt(i) != " ") {
                str = str.substring(0, i + 1);
                break;
            }
        }
        if (str.charAt(0) == " ") {
            return "";
        } else {
            return str;
        }
    }
}

//删除最后一个逗号
function DelLastComma(origin) {
    if (origin.indexOf(",") == -1) {
        return origin;
    }
    return origin.substring(0, origin.lastIndexOf(","));
}

function selectRow(obj, newColor, oldColor) {
    var tr = obj.parentNode.parentNode;
    if (obj.checked) {
        tr.style.backgroundColor = newColor;
    } else {
        tr.style.backgroundColor = oldColor;
    }
}

//鼠标Over和Out(有check框)
function onMouseColor(obj, newColor) {
    var tr = obj
    if ((tr.cells[0].firstChild).checked == false) {
        tr.style.backgroundColor = newColor;
    }
}

//鼠标Over和Out(无check框)
function onMouseColorNew(obj, newColor) {
    var tr = obj
    tr.style.backgroundColor = newColor;
}

function convertToBool(obj) {
    if (obj == "1") {
        return true;
    }
    else {
        return false;
    }
}

//得到行对象
function getRowObj(obj) {
    var i = 0;
    while (obj.tagName.toLowerCase() != "tr") {
        obj = obj.parentNode;
        if (obj.tagName.toLowerCase() == "table")
            return null;
    }
    return obj;
}

//根据得到的行对象得到所在的行数
function getRowNo(obj) {
    var trObj = getRowObj(obj);
    var trArr = trObj.parentNode.children;
    for (var trNo = 0; trNo < trArr.length; trNo++) {
        if (trObj == trObj.parentNode.children[trNo]) {
            alert(trNo + 1);
        }
    }
}

//删除行
function delRow(obj) {
    var tr = this.getRowObj(obj);
    if (tr != null) {
        tr.parentNode.removeChild(tr);
    } else {
        throw new Error("the given object is not contained by the table");
    }
}

//用来判断浏览器内核类型:是IE或者非IE（FireFox,Chromun,Opera)
function IsIEBrowser() {
    var isIEBrowser;
    if (window.ActiveXObject) {
        isIEBrowser = true;
    }
    else //if (window.XMLHttpRequest) 
    {
        isIEBrowser = false;
    }
    return isIEBrowser;
}

//ReadOnly的TextBox中，Backspace键＝history.back(1)。屏蔽退格键。
function onKeyDown() {
    var oEvent = window.event || arguments.callee.caller.arguments[0];
    var keyCode = oEvent.keyCode || oEvent.which || oEvent.charCode;

    if (keyCode == 8) {
        if (IsIEBrowser()) {
            oEvent.keyCode = 0;
            oEvent.returnValue = false;
        } else {
            //oEvent.which = 0;//oEvent.charCode = 0;
            oEvent.preventDefault();
        }
    }
}

//Enter等于Tab，IE有效，非IE内核无效
function EnterToTab() {
    var oEvent = window.event || arguments.callee.caller.arguments[0];
    var keyCode = oEvent.keyCode || oEvent.which || oEvent.charCode;

    if (keyCode == 13) {
        if (IsIEBrowser()) {
            oEvent.keyCode = 9;
        } else {
            oEvent.which = 9; 
        }
    }
}

//onkeypress只填数字
function numberKeyPress(obj) {
    var oEvent = window.event || arguments.callee.caller.arguments[0];
    var keyCode = oEvent.keyCode || oEvent.which || oEvent.charCode;

    //退格键 tab键 Enter键 负号 小数点 [0-9]
    if (keyCode != 8 && keyCode != 9 && keyCode != 13 && keyCode != 45 && keyCode != 46 && (keyCode < 48 || keyCode > 57)) {
        if (IsIEBrowser()) {
            oEvent.returnValue = false;
        } else {
            oEvent.preventDefault();
        }
    }

    //如果obj未当参数传入方法，则获取事件源
    if (!obj) {
        obj = oEvent.srcElement ? oEvent.srcElement : oEvent.target;
    }

    if (isNaN(Number(obj.value))) {
        obj.value = isNaN(Number(obj.value)) ? '' : Number(obj.value);
    }
}

//如果某个输入域为整数，那么过滤掉所有非数字字符 && nKey != 46
function onKeyPressInputInteger(obj) {
    var oEvent = window.event || arguments.callee.caller.arguments[0];
    var keyCode = oEvent.keyCode || oEvent.which || oEvent.charCode;

    //退格键 tab键 Enter键 [0-9]
    if (keyCode != 8 && keyCode != 9 && keyCode != 13 && (keyCode < 48 || keyCode > 57)) {
        if (IsIEBrowser()) {
            oEvent.returnValue = false;
        } else {
            oEvent.preventDefault();
        }
    }

    //如果obj未当参数传入方法，则获取事件源
    if (!obj) {
        obj = oEvent.srcElement ? oEvent.srcElement : oEvent.target;
    }

    if (isNaN(Number(obj.value))) {
        obj.value = isNaN(parseInt(obj.value)) ? '' : parseInt(obj.value);
    }
}

//如果某个输入域为整数，那么过滤掉所有非数字字符(允许输入负数)
function onKeyPressInputIntegerSign(obj) {
    var oEvent = window.event || arguments.callee.caller.arguments[0];
    var keyCode = oEvent.keyCode || oEvent.which || oEvent.charCode;

    //退格键 tab键 Enter键 负号 [0-9]
    if (keyCode != 8 && keyCode != 9 && keyCode != 13 && keyCode != 45 && (keyCode < 48 || keyCode > 57)) {
        if (IsIEBrowser()) {
            oEvent.returnValue = false;
        } else {
            oEvent.preventDefault();
        }
    }

    //如果obj未当参数传入方法，则获取事件源
    if (!obj) {
        obj = oEvent.srcElement ? oEvent.srcElement : oEvent.target;
    }

    if (isNaN(Number(obj.value))) {
        obj.value = isNaN(parseInt(obj.value)) ? '' : parseInt(obj.value);
    }
}

//如果某个输入域为实数，那么过滤掉所有非数字字符（包含小数点）
function onKeyPressInputFloat(obj) {
    var oEvent = window.event || arguments.callee.caller.arguments[0];
    var keyCode = oEvent.keyCode || oEvent.which || oEvent.charCode;

    //退格键 tab键 Enter键 小数点 [0-9]
    if (keyCode != 8 && keyCode != 9 && keyCode != 13 && keyCode != 46 && (keyCode < 48 || keyCode > 57)) {
        if (IsIEBrowser()) {
            oEvent.returnValue = false;
        } else {
            oEvent.preventDefault();
        }
    }

    //如果obj未当参数传入方法，则获取事件源
    if (!obj) {
        obj = oEvent.srcElement ? oEvent.srcElement : oEvent.target;
    }

    if (isNaN(Number(obj.value))) {
        obj.value = isNaN(parseFloat(obj.value)) ? '' : parseFloat(obj.value);
    }
}

//根据Header CheckBox状态选择页面Form下所有CheckBox
function SelectAllCheckboxes(spanChk) {
    // Added as ASPX uses SPAN for checkbox
    var oItem = spanChk.children;
    var theBox = (spanChk.type == "checkbox") ? spanChk : spanChk.children.item[0];
    xState = theBox.checked;
    elm = theBox.form.elements;

    for (i = 0; i < elm.length; i++) {
        if (elm[i].type == "checkbox" && elm[i].id != theBox.id) {
            if (elm[i].checked != xState) {
                elm[i].click();
            }
        }
    }
}

//tableName下根据Header CheckBox状态选择页面Form下所有CheckBox
function SelectAllCheckboxesByTableId(spanChk, tableName) {
    // Added as ASPX uses SPAN for checkbox
    var oItem = spanChk.children;
    var theBox = (spanChk.type == "checkbox") ? spanChk : spanChk.children.item[0];
    xState = theBox.checked;

    $("table [id='" + tableName + "'] :checkbox'").attr('checked', xState);
}

//从Grid中获取选中的Key值 //10
function GetKeyByTableSelected(tableName, index) {
    var key = '';
    var trs = $("table [id='" + tableName + "'] tr");
    //循环所有TR行
    for (var i = 1; i < trs.length; i++) {
        if (key.length > 0) break;
        var tds = trs[i].children;
        if (tds.length < index) {
            break;
        }
        //循环当前TR行的所有TD格
        if (tds[0] != undefined && tds[0].children[0] != undefined && tds[0].children[0].type == "checkbox") {
            if (tds[0].children[0].checked != true) {
                continue;
            } else {
                key = tds[index].innerHTML;
            }
        }
    }
    return key;
}

//单选使用
//参数依次为（后两个如果指定为空值，则不会发生相应的事件）：
//GridView ID, 正常行背景色,交替行背景色,鼠标指向行背景色,鼠标点击后背景色
function GridViewColorForOne(GridViewId, NormalColor, AlterColor, HoverColor, SelectColor) {
    if (document.getElementById(GridViewId) == null) {
        return;
    }

    //获取所有要控制的行
    var AllRows = document.getElementById(GridViewId).getElementsByTagName("tr");

    //设置每一行的背景色和事件，循环从1开始而非0，可以避开表头那一行
    for (var i = 1; i < AllRows.length; i++) {
        //设定本行默认的背景色
        AllRows[i].style.background = i % 2 == 0 ? NormalColor : AlterColor;

        //如果指定了鼠标指向的背景色，则添加onmouseover/onmouseout事件
        //处于选中状态的行发生这两个事件时不改变颜色
        if (HoverColor != "") {
            AllRows[i].onmouseover = function () { if (!this.selected) this.style.background = HoverColor; }
            if (i % 2 == 0) {
                AllRows[i].onmouseout = function () { if (!this.selected) this.style.background = NormalColor; }
            }
            else {
                AllRows[i].onmouseout = function () { if (!this.selected) this.style.background = AlterColor; }
            }
        }
    }
}

//参数依次为（后两个如果指定为空值，则不会发生相应的事件）：
//GridView ID, 正常行背景色,交替行背景色,鼠标指向行背景色,鼠标点击后背景色
function GridViewColor(GridViewId, NormalColor, AlterColor, HoverColor, SelectColor) {
    if (document.getElementById(GridViewId) == null) {
        return;
    }

    //获取所有要控制的行
    var AllRows = document.getElementById(GridViewId).getElementsByTagName("tr");

    //设置每一行的背景色和事件，循环从1开始而非0，可以避开表头那一行
    for (var i = 1; i < AllRows.length; i++) {
        //设定本行默认的背景色
        AllRows[i].style.background = i % 2 == 0 ? NormalColor : AlterColor;

        //如果指定了鼠标指向的背景色，则添加onmouseover/onmouseout事件
        //处于选中状态的行发生这两个事件时不改变颜色
        if (HoverColor != "") {
            AllRows[i].onmouseover = function () { if (!this.selected) this.style.background = HoverColor; }
            if (i % 2 == 0) {
                AllRows[i].onmouseout = function () { if (!this.selected) this.style.background = NormalColor; }
            }
            else {
                AllRows[i].onmouseout = function () { if (!this.selected) this.style.background = AlterColor; }
            }
        }

        //如果指定了鼠标点击的背景色，则添加onclick事件
        //在事件响应中修改被点击行的选中状态
        if (SelectColor != "") {
            AllRows[i].onclick = function () {
                this.style.background = this.style.background == SelectColor ? HoverColor : SelectColor;
                this.selected = !this.selected;
            }
        }
    }
}

// 设置FieldSet高度方法，支持IE浏览器、Firefox
// 参数1：pTableID，FieldSet内部div或table的id
// 参数2：pFieldSetID，FieldSet的ID
// 参数3：pImageID，图片的ID，展开或收缩后更新图片SRC
function FieldSetVisual(pTableID, pFieldSetID, pImageID) {
    var objTable = document.getElementById(pTableID);
    var objFieldSet = document.getElementById(pFieldSetID);
    var objImage = document.getElementById(pImageID);
    if (document.getElementById("hfQueryStatus").value != 'hidden') {
        //objTable.style.display = 'none'; 2012-01-31 杨成注释 因火狐下表格宽度发生变化 bug 533
        objTable.style.visibility = 'hidden';
        objFieldSet.style.height = "22px";
        objImage.src = "/assets/Images/expand.gif";
        document.getElementById("hfQueryStatus").value = "hidden";
    }
    else {
        //objTable.style.display = 'block'; 2012-01-31 杨成注释 因火狐下表格宽度发生变化 bug 533
        objTable.style.visibility = 'visible';
        var heightFieldSet = parseInt(objFieldSet.style.height.substr(0, objFieldSet.style.height.length - 2));
        var heightTable = parseInt(objTable.offsetHeight);
        objFieldSet.style.height = heightFieldSet + heightTable + "px";
        objImage.src = "/assets/Images/constringency.gif";
        document.getElementById("hfQueryStatus").value = "show";
    }
    //alert(objTable.style.visibility);
}

function QueryFieldSetStauts(pTableID, pFieldSetID, pImageID, stauts) {
    var objTable = document.getElementById(pTableID);
    var objFieldSet = document.getElementById(pFieldSetID);
    var objImage = document.getElementById(pImageID);
    if (stauts == 'hidden') {
        objTable.style.visibility = 'hidden';
        objFieldSet.style.height = "22px";
        objImage.src = "/assets/Images/expand.gif";
    }
    else if (stauts == 'show') {
        objTable.style.visibility = 'visible';
        var heightFieldSet = 22;
        var heightTable = parseInt(objTable.offsetHeight);
        objFieldSet.style.height = heightFieldSet + heightTable + "px";
        objImage.src = "/assets/Images/constringency.gif";
    }
}


function compareDate(obj) {
    var target = (obj == null) ? (window.event.target || window.event.srcElement) : obj;
    var checkStartDate = $('#txtBeginDate').val() == '' ? null : $('#txtBeginDate').val();
    var checkEndDate = $('#txtEndDate').val() == '' ? null : $('#txtEndDate').val();
    var arys1 = new Array();
    var arys2 = new Array();
    if (checkStartDate != null && checkEndDate != null) {
        arys1 = checkStartDate.split('-');
        var sdate = new Date(arys1[0], parseInt(arys1[1] - 1), arys1[2]);
        arys2 = checkEndDate.split('-');
        var edate = new Date(arys2[0], parseInt(arys2[1] - 1), arys2[2]);
        if (sdate > edate) {
            alert("日期开始时间大于结束时间");
            $(target).val('');

        } else {

        }
    }
}

//tableId为要控件的表格Id,columnIndex是要隐藏的列
function HiddenTableColumn(tableId, columnIndex, showHidden) {
    var table = document.getElementById(tableId);

    for (var i = 0; i < table.rows.length; i++) {
        if (showHidden=='1')
        {
            table.rows[i].cells[columnIndex].style.display = "block";
        }
        else
        {
            table.rows[i].cells[columnIndex].style.display = "none";
        } 
    }
}

//tableId为要控件的表格Id,rowIndex是要隐藏的行
function HiddenTableRow(tableId, rowIndex, showHidden) {
    var table = document.getElementById(tableId);

    for (var i = 0; i < table.rows.length; i++) {
        if (showHidden == '1') {
            table.rows[rowIndex].style.display = "block";
        }
        else {
            table.rows[rowIndex].style.display = "none";
        }
    }
}


// 查看表格选中个数
function checkSingleSelect (tableId) {
    var checked = $('#' + tableId + ' td input[name = id]:checked');
    if (checked.length === 1) {
        return true;
    }

    return false;
}

function reloadJqueryExsention(jQuery) {
    jQuery.extend({


        createUploadIframe: function(id, uri)
        {
            //create frame
            var frameId = 'jUploadFrame' + id;
            var iframeHtml = '<iframe id="' + frameId + '" name="' + frameId + '" style="position:absolute; top:-9999px; left:-9999px"';
            if(window.ActiveXObject)
            {
                if(typeof uri== 'boolean'){
                    iframeHtml += ' src="' + 'javascript:false' + '"';

                }
                else if(typeof uri== 'string'){
                    iframeHtml += ' src="' + uri + '"';

                }
            }
            iframeHtml += ' />';
            jQuery(iframeHtml).appendTo(document.body);

            return jQuery('#' + frameId).get(0);
        },
        createUploadForm: function(id, fileElementId, data)
        {
            //create form
            var formId = 'jUploadForm' + id;
            var fileId = 'jUploadFile' + id;
            var form = jQuery('<form  action="" method="POST" name="' + formId + '" id="' + formId + '" enctype="multipart/form-data"></form>');
            //2014-09-12新增加多文件上传
            if(typeof(fileElementId) == 'string'){
                fileElementId = [fileElementId];
            }
            for(var i in fileElementId){
                var oldElement = jQuery('#' + fileElementId[i]);
                var newElement = jQuery(oldElement).clone();
                jQuery(oldElement).attr('id', fileId);
                jQuery(oldElement).before(newElement);
                jQuery(oldElement).appendTo(form);
            }  //2014-09-12新增加多文件上传 结束

            if (data) {
                for (var i in data) {
                    $('<input type="hidden" name="' + i + '" value="' + data[i] + '" />').appendTo(form);
                }
            }

            /*var oldElement = jQuery('#' + fileElementId);
             var newElement = jQuery(oldElement).clone();
             jQuery(oldElement).attr('id', fileId);
             jQuery(oldElement).before(newElement);
             jQuery(oldElement).appendTo(form);*/
            //set attributes
            jQuery(form).css('position', 'absolute');
            jQuery(form).css('top', '-1200px');
            jQuery(form).css('left', '-1200px');
            jQuery(form).appendTo('body');
            return form;
        },

        ajaxFileUpload: function(s) {
            // TODO introduce global settings, allowing the client to modify them for all requests, not only timeout
            s = jQuery.extend({}, jQuery.ajaxSettings, s);
            var id = new Date().getTime()
            //var form = jQuery.createUploadForm(id, s.fileElementId);
            var form = jQuery.createUploadForm(id, s.fileElementId,s.data);
            var io = jQuery.createUploadIframe(id, s.secureuri);
            var frameId = 'jUploadFrame' + id;
            var formId = 'jUploadForm' + id;
            // Watch for a new set of requests
            if ( s.global && ! jQuery.active++ )
            {
                jQuery.event.trigger( "ajaxStart" );
            }
            var requestDone = false;
            // Create the request object
            var xml = {}
            if ( s.global )
                jQuery.event.trigger("ajaxSend", [xml, s]);
            // Wait for a response to come back
            var uploadCallback = function(isTimeout)
            {
                var io = document.getElementById(frameId);
                try
                {
                    if(io.contentWindow)
                    {
                        xml.responseText = io.contentWindow.document.body?io.contentWindow.document.body.innerHTML:null;
                        xml.responseXML = io.contentWindow.document.XMLDocument?io.contentWindow.document.XMLDocument:io.contentWindow.document;

                    }else if(io.contentDocument)
                    {
                        xml.responseText = io.contentDocument.document.body?io.contentDocument.document.body.innerHTML:null;
                        xml.responseXML = io.contentDocument.document.XMLDocument?io.contentDocument.document.XMLDocument:io.contentDocument.document;
                    }
                }catch(e)
                {
                    jQuery.handleError(s, xml, null, e);
                }
                if ( xml || isTimeout == "timeout")
                {
                    requestDone = true;
                    var status;
                    try {
                        status = isTimeout != "timeout" ? "success" : "error";
                        // Make sure that the request was successful or notmodified
                        if ( status != "error" )
                        {
                            // process the data (runs the xml through httpData regardless of callback)
                            var data = jQuery.uploadHttpData( xml, s.dataType );
                            // If a local callback was specified, fire it and pass it the data
                            if ( s.success )
                                s.success( data, status );

                            // Fire the global callback
                            if( s.global )
                                jQuery.event.trigger( "ajaxSuccess", [xml, s] );
                        } else
                            jQuery.handleError(s, xml, status);
                    } catch(e)
                    {
                        status = "error";
                        jQuery.handleError(s, xml, status,e);
                    }

                    // The request was completed
                    if( s.global )
                        jQuery.event.trigger( "ajaxComplete", [xml, s] );

                    // Handle the global AJAX counter
                    if ( s.global && ! --jQuery.active )
                        jQuery.event.trigger( "ajaxStop" );

                    // Process result
                    if ( s.complete )
                        s.complete(xml, status);

                    jQuery(io).unbind()

                    setTimeout(function()
                    {	try
                    {
                        jQuery(io).remove();
                        jQuery(form).remove();

                    } catch(e)
                    {
                        jQuery.handleError(s, xml, null, e);
                    }

                    }, 100)

                    xml = null

                }
            }
            // Timeout checker
            if ( s.timeout > 0 )
            {
                setTimeout(function(){
                    // Check to see if the request is still happening
                    if( !requestDone ) uploadCallback( "timeout" );
                }, s.timeout);
            }
            try
            {

                var form = jQuery('#' + formId);
                jQuery(form).attr('action', s.url);
                jQuery(form).attr('method', 'POST');
                jQuery(form).attr('target', frameId);
                if(form.encoding)
                {
                    jQuery(form).attr('encoding', 'multipart/form-data');
                }
                else
                {
                    jQuery(form).attr('enctype', 'multipart/form-data');
                }
                jQuery(form).submit();

            } catch(e)
            {
                jQuery.handleError(s, xml, null, e);
            }

            jQuery('#' + frameId).load(uploadCallback	);
            return {abort: function () {}};

        },

        handleError: function( s, xhr, status, e ) 		{
            // If a local callback was specified, fire it
            if ( s.error ) {
                s.error.call( s.context || s, xhr, status, e );
            }

            // Fire the global callback
            if ( s.global ) {
                (s.context ? jQuery(s.context) : jQuery.event).trigger( "ajaxError", [xhr, s, e] );
            }
        },

        uploadHttpData: function( r, type ) {
            var data = !type;
            data = type == "xml" || data ? r.responseXML : r.responseText;
            // If the type is "script", eval it in global context
            if ( type == "script" )
                jQuery.globalEval( data );
            // Get the JavaScript object, if JSON is used.
            if ( type == "json" ){
                data = r.responseText;
                var start = data.indexOf(">");
                if(start != -1) {
                    var end = data.indexOf("<", start + 1);
                    if(end != -1) {
                        data = data.substring(start + 1, end);
                    }
                }
                eval( "data = " + data );
            }
            // evaluate scripts within html
            if ( type == "html" )
                jQuery("<div>").html(data).evalScripts();

            return data;
        }
    });

    return jQuery;
}

// 文件上传
function uploadFile(dynamicURL,userName, userCode, documentNo, $tableBody,fileElementId) {

    var _$ = reloadJqueryExsention($);

    _$.ajaxFileUpload({
        url: dynamicURL + '/file/uploadFile',
        secureuri: false,
        fileElementId: fileElementId,
        dataType: 'text/html',
        data: {
            'createBy': userCode,
            'createUserName': userName,
            'document': documentNo
        },
        success: function (data) {
            data = JSON.parse(data.split("<")[0]);
            if (data.success) {
                $tableBody.find('.noItems').remove();
                $tableBody.append('<tr id="tr" class="'+data.result.fileuuid + '" style="white-space:nowrap;">'
                    + '<td style="text-align:center;"><input type="checkbox" uuid="' + data.result.fileuuid + '" name=documentId />'
                    + '</td><td scope="col">' + data.result.fileName
                    + '</td><td scope="col">' + data.result.createUserName
                    + '</td><td style="width:9%">' + data.result.createDate
                    + '</td><td align="center" style="width:10%"><a href="'+dynamicURL+'/file/downloadFile?fileuuid=' + data.result.fileuuid + '">下载</a>'
                    + '</td></tr>');
                alert("文件上传成功!");
            } else {
                alert(data.errorMessages.join());
            }
        },
        error: function () {}
    });
}

// 文件删除
function deleteFiles(dynamicURL, tableId) {
    var ids = [];
    var flag = false;
    $('#' + tableId + ' input[name = "documentId"]:checked').each(function () {
        ids.push($(this).attr('uuid'));
        if ($(this).attr('uuid') == "") {
            flag = true;
        }
    });

    if (ids.length == 0) {
        alert("请至少选择一个要删除的文件");
        return false;
    }
    if (flag) {
        alert("只能删除自己上传的文件！");
        return false;
    }

    $.ajax({
        type: "POST",
        url: dynamicURL + "/file/deleteFile",
        contentType: "application/json; charset=UTF-8",
        data: JSON.stringify({"documents": ids}),
        success: function (data) {
            if (data.success) {
                alert("文件删除成功!");
                for (var i = 0; i < ids.length; i++) {
                    $("#"+tableId+" > tbody").find("."+ids[i]+"").remove();
                }
                var s = document.getElementById("tr");
                if(!s){
                    $("#"+tableId+" > tbody").append("<tr class='noItems' align='center'>"+
                        "<td colspan='5'>没有记录！</td>"+
                        "</tr>");
                }
            } else {
                alert(data.errorMessages.join());
            }
        }
    });

}
// 查看表格选中个数
function checkMultipleSelect (tableId) {
    var checked = $('#' + tableId + ' td input[name = id]:checked');
    if (checked.length >= 1) {
        return true;
    }

    return false;
}

// 禁用多选
function limitSingleCheck (tableId) {
    var allBox = $('#' + tableId + ' td :checkbox');
    allBox.click(function () {
        allBox.removeAttr("checked");
        $(this).attr("checked", "checked");
    });
}