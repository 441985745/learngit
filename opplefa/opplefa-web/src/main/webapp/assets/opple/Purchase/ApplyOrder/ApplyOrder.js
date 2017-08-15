document.domain='opple.com';
function queryreload() {
    document.getElementById("btnRefresh").click();
    //alert("close");
}

//新增申请单  弹出层大小 统一为 800和600
function opAdddlg(id, title) {
    //var h = typeof (window.parent.parent.GetViewHeight()) == "undefined" ? 600 : window.parent.parent.GetViewHeight() - 20;
   
    if (id == -1) {//-1是新增窗口
    	var lurl = "/apply/addApplyOrder";
    	var dlg = new $.dialog({ id: 'add', title: title, cover: true, page: lurl, rang: true, width: 800, height: 600, btnBar: false, resize: true, maxBtn: true }).ShowDialog();
    }
    else {
    	var lurl = "/apply/showApplyOrder?id=" + id;
    	var dlg = new $.dialog({ id: 'add', title: title, cover: true, page: lurl, rang: true, width: 800, height: 600, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
    	}
}

//新增明细  弹出层大小 统一为  800和400
function opAdddlgDetail(title) {
	var lurl = "/apply/addApplyOrderDetail";
	var dlg = new $.dialog({ id: 'addDetail', title: title, cover: true, page: lurl, rang: true, width: 800, height: 400, btnBar: false, resize: true, maxBtn: true }).ShowDialog();
}

function showMailAndMessageDialog(operate) {

    var isstep = "Y";
    var hdmoney = document.getElementById("hdmoney").value;
    if (hdmoney != "" && hdmoney != $("#txtapplymoney")[0].value) {
        isstep = "N";
    }

    document.getElementById("isStep").value = isstep;

    var lurl = "Module/WorkFlow/MailAndMessageDialog.aspx?id=add&op=" + operate + "&isStep=" + isstep;

    lurl = lurl + "&billCode=" + document.getElementById("txtDocument").value + "&typeCode=88001";

    var dlg = new dg.curWin.$.dialog({ id: 'dialog', parent: dg, title: '邮件、短信对话框', cover: true, page: lurl, width: 400, height: 235, btnBar: false, resize: false, maxBtn: false });
    dlg.ShowDialog();
}

function setMailTextValue(backStepActivityId, isMail, isPhoneMessage, operate, idea, isBar) {
    var strIdea = idea;
    document.getElementById("hdnIsMail").value = isMail;
    document.getElementById("hdnIsPhoneMessage").value = isPhoneMessage;
    document.getElementById("hdnBackStepActivityId").value = backStepActivityId;
    document.getElementById("hdnIdea").value = strIdea;
    document.getElementById("hdnBar").value = isBar;

    if (operate != "save") {
        document.getElementById("txaApprovaIdea").value = strIdea;
    }

    if (operate == "back") {
        document.getElementById("btnBack").click();
        document.getElementById("btnFirstBack").value = "提交ing";
        document.getElementById("btnFirstBack").disabled = true;
    }
    else if (operate == "unapproval") {
        document.getElementById("btnFirstUnApproval").value = "提交ing";
        document.getElementById("btnFirstUnApproval").disabled = true;
        document.getElementById("btnUnApproval").click();
    }
    else if (operate == "save") {
        document.getElementById("btnFirstSave").value = "提交ing";
        document.getElementById("btnFirstSave").disabled = true;
        document.getElementById("btnsaveall").click();
    }
    else {
        document.getElementById("btnAgree").click();
        document.getElementById("btnFirstAgree").value = "提交ing";
        document.getElementById("btnFirstAgree").disabled = true;
    }
}

//弹出部门
function showBudgetDepDialog(order) {
    txtobj = document.getElementById("txtbudgetdep");
    hdobj = document.getElementById("hisbudgetdepcode");
    if (order == 1) {
        var lurl = "/demo/searchDepartmentTreeInit";
        var dlg = new $.dialog({ id: 'DepItemDialog', title: '部门', cover: true, page: lurl, width: 450, height: 550, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
    }
    else {
        var lurl = "/demo/searchDepartmentTreeInit";
        var dlg = new $.dialog({ id: 'DepItemDialog', title: '部门', cover: true, page: lurl, width: 450, height: 550, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
    }
}

function setTextValue(code, name, order) {
    if (order == 1) {
        document.getElementById("hdapplydepcode").value = code;
        document.getElementById("txtapplydep").value = name;
    }
    else if (order == 2) {
        document.getElementById("hdnBudgetDepCode").value = code;
        document.getElementById("txtBudgetDepName").value = name;
    }
}

//弹出部门
function showBudgetDepDialogs() {
    if ($("#drpFeeItemName").val() == "-1") {
        alert("请先选择费用科目！");
        changeSum();
        return;
    }
    txtobj = document.getElementById("txtbudgetdep");
    hdobj = document.getElementById("hisbudgetdepcode");
    var lurl = "Module/MainManagement/BudgetDep/SingleBudgetDepTreeNoRule.aspx?id=add";
    var dlg = new dg.curWin.$.dialog({ id: 'DepItemDialog', title: '部门', cover: true, page: lurl, width: 450, height: 550, parent: dg, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
    //dlg.ShowDialog();
}

function setTextValueNoRule(code, name) {
    document.getElementById("hisbudgetdepcode").value = code;
    document.getElementById("hisbudgetdepcode").value = code;
    document.getElementById("txtbudgetdep").value = name;
    document.getElementById("btnInitDep").click();
}
// 如果ID 为NONE 不检索父窗口
function showFeeItemDialog(order) {
    var lurl = "Module/MainManagement/FeeItem/OneFeeItemDialog.aspx?id=none&bdc=&fin=&pcn=Module_UnitedExpense_SpecialFee_FeeApply_List";
    var dlg = new $.dialog({ id: 'FeeItemDialog', title: '费用科目', cover: true, page: lurl, width: 650, height: 350, btnBar: false, resize: false, maxBtn: false });
    dlg.ShowDialog();
}

function setFeeItem(code, name) {
    document.getElementById("hdfeecode").value = code;
    document.getElementById("txtfee").value = name;
}

function imptScript(id) {
    var tc = document.getElementById(id + '_ImportDialog');
    var atable = tc.childNodes[0];
    atable.className = "impstyle";
}

function opApprovalLogdlg(id) {
    var lurl = "Module/WorkFlow/ApprovalLogDialog.aspx";
    lurl = lurl + "?id=" + id + "&typeCode=88001"
    dlg = new $.dialog({ id: 'ApprovalLogDialog', title: '审批历史', cover: true, page: lurl, rang: true, width: 1100, height: 400, btnBar: false, resize: false, maxBtn: false });
    dlg.ShowDialog();
}


var shoukuann;//收款名称
var shoukuanc;//收款人
var hdshoukuanname;//隐藏的收款人名称
function showShowKuan(id) {
    num = id.substring(id.lastIndexOf('_'));

    shoukuann = document.getElementById("gvdMeeting_txtShouKuan" + num);
    shoukuanc = document.getElementById("gvdMeeting_hdShouKuancode" + num);
    if ($("#hisbudgetdepcode").val() == "") {
        alert("请先选择预算归属部门!");
        return;
    }
    var drp = document.getElementById("gvdMeeting_dpbalanceway" + num);
    var ttype = drp.options[drp.selectedIndex].value;
    if (ttype == "-1")
    {
        alert("请选择结算方式");
        return;
    }
    var drop = document.getElementById("gvdMeeting_DropBussinType" + num);
    var btype = drop.options[drop.selectedIndex].value;

    var targettext = document.getElementById("gvdMeeting_txtShouKuan" + num).value;
    var type;
   
    var companyCode = $("#drpCompanyCode").val();

    if (ttype != "-1") {
        if (ttype == "PT00000003") {
            if (btype == "BU00000004") {
                type = "1";
            }
            else if (btype == "BU00000001") {
                type = "4";
            }
            else {
                type = "6";
            }
        }
        else {
            type = "5";
        }
        var lurl = "Module/Market/Dialog/PayeeUnitDialog.aspx?id=add&cuc=none&type=" + type + "&pun=" + targettext + "&cpc=" + companyCode;//?id=add&pcn=Module_ExpensesExecuteSystem_FeeApply_Add&bdc=" + document.getElementById("hdapplydepcode").value + "&cpc=" + document.getElementById("hdfeecode").value;// + "&order=" + order + "&ie=N";
        var dlg = new dg.curWin.$.dialog({ id: 'PayeeUnitDialog', title: '选择收款方', cover: true, page: lurl, width: 700, height: 400, parent: dg, btnBar: false, resize: false, maxBtn: false }).ShowDialog();
    }
    else {
        alert("请先选择结算方式!");
        return;
    }
}

function setPayeeUnit(code, name) {
    shoukuanc.value = code;
    shoukuann.value = name;
}

function ClearObj(id) {
    num = id.substring(id.lastIndexOf('_'));

    document.getElementById("gvdMeeting_txtShouKuan" + num).value = "";
    document.getElementById("gvdMeeting_hdShouKuancode" + num).value = "";
}

function changeDpbalanceway(id) {
    num = id.substring(id.lastIndexOf('_'));

    document.getElementById("gvdMeeting_txtShouKuan" + num).value = "";
    document.getElementById("gvdMeeting_hdShouKuancode" + num).value = "";
    var drp = document.getElementById("gvdMeeting_dpbalanceway" + num);
    var ttype = drp.options[drp.selectedIndex].value;
    if (ttype == "PT00000003") {
        $("#gvdMeeting_isyufu" + num).disabled = "disabled";
    }
}

//异步后台提示 所用部分
function NewAlert(abj) {
    if (typeof (abj) != "undefined") {
        if (abj.length > 0) {
            alert(abj);
        }
    }
}

//异步后台提示 所用部分
function HL(abj) {
    if (typeof (abj) != "undefined") {
        if (abj.length > 0) {
            document.getElementById("txtHL").value = abj;
        }
        else {
            document.getElementById("txtHL").value = "0.00000";
        }
    }
}

//计算金额
function changeSum() {
    var HL;
    var ApplySumMoneyyb = 0;
    var ApplySumMoneybb = 0;
    HL = document.getElementById("txtHL").value;
    if (document.getElementById("gvdMeeting").rows.length > 0) {
        for (var i = 0; i < document.getElementById("gvdMeeting").rows.length - 1; i++) {
            var moneyyb = 0;
            if (document.getElementById("gvdMeeting_txtapplymoney_" + i) != null) {
                if (!isNaN(document.getElementById("gvdMeeting_txtapplymoney_" + i).value) && (document.getElementById("gvdMeeting_txtapplymoney_" + i).value) != "") {

                    moneyyb = document.getElementById("gvdMeeting_txtapplymoney_" + i).value;
                    if (moneyyb == null || moneyyb == "") {
                        moneyyb = 0;
                    }
                    if (HL == null || HL == "") {
                        HL = 0;
                    }
                    document.getElementById("gvdMeeting_txtapplymoneybb_" + i).value = (parseFloat(moneyyb) * parseFloat(HL)).toFixed(2);
                    ApplySumMoneyyb = (parseFloat(ApplySumMoneyyb) + parseFloat(document.getElementById("gvdMeeting_txtapplymoney_" + i).value)).toFixed(2);
                    ApplySumMoneybb = (parseFloat(ApplySumMoneybb) + parseFloat(document.getElementById("gvdMeeting_txtapplymoneybb_" + i).value)).toFixed(2);

                }
                else {
                    document.getElementById("gvdMeeting_txtapplymoney_" + i).value = "0";
                    document.getElementById("gvdMeeting_txtapplymoneybb_" + i).value = "0";
                }
            }
        }
    }

    $("#gvdMeeting_txtapplymoneyHj").val(ApplySumMoneyyb);
    $("#gvdMeeting_txtapplymoneybbHj").val(ApplySumMoneybb);
    $("#txtapplymoney").val(ApplySumMoneyyb);
    $("#txtapplymoneyBB").val(ApplySumMoneybb);
}

//赋值汇率的js方法
function getRate(currencyCode) {
    var num = $("#txtDocument").value;
    var Rate = Module_UnitedExpense_SpecialFee_FeeApply_MeetingFee_Add.getExchangeRate(currencyCode, num).value;

    if ($("#txtHL") != null) {
        $("#txtHL").val(Rate);

        //重新计算合计金额
        changeSum();
    }
}
