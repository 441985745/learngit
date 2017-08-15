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
        objImage.src = "/assets/opple/images/expand.gif";
        document.getElementById("hfQueryStatus").value = "hidden";
    }
    else {
        //objTable.style.display = 'block'; 2012-01-31 杨成注释 因火狐下表格宽度发生变化 bug 533
        objTable.style.visibility = 'visible';
        var heightFieldSet = parseInt(objFieldSet.style.height.substr(0, objFieldSet.style.height.length - 2));
        var heightTable = parseInt(objTable.offsetHeight);
        objFieldSet.style.height = heightFieldSet + heightTable + "px";
        objImage.src = "/assets/opple/images/constringency.gif";
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
        objImage.src = "/assets/opple/images/expand.gif";
    }
    else if (stauts == 'show') {
        objTable.style.visibility = 'visible';
        var heightFieldSet = 22;
        var heightTable = parseInt(objTable.offsetHeight);
        objFieldSet.style.height = heightFieldSet + heightTable + "px";
        objImage.src = "/assets/opple/images/constringency.gif";
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