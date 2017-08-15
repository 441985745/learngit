document.domain = 'opple.com';
var dg = frameElement.lhgDG;//定义dg 关闭需要
GridViewColor("gvDataList", "#F7F7F7", "#fff", "#EBEBEB", "#fd6");
QueryFieldSetStauts('t_ShipInportInfo', 'fset_ShipInportInfo', 'img_ShipInportInfo', document.getElementById("hfQueryStatus").value);

function autoSize() {
    $("#gridview").width($(window).width());
    $("#gridview").height(
        $(window).height() - $("#Query").height()
        - $("#Button").height()
        - $("#AspNetPager").height());
}

autoSize();
window.onresize = autoSize;
//点击单据号跳转审批页面

//预算所属部门 开始
$('#btntxtYsssbmShow').click(function(){
    var top = "70px";//$(this).offset().top - 350;
    var left = "180px";//$(this).offset().left-180;
    $('#ysssbmWindow').window('open').window('resize',{width:'450px',height:'300px',top: top,left:left});

    $("#tree1").tree({
        url: _dynamicURL+'/department/searchDepartmentTreeInit',
        lines: true
    });
});
$("#ysssbmAdd").click(function(){
    $("#txtCompanyCode option").remove();
    $("#spanCostCenter input").remove();
    var ckscode = $("#tree1").tree('getSelected').id;
    var cksname = $("#tree1").tree('getSelected').text;
    $("#txtYsssbm").val(ckscode);
    $("#txtBudgetDepartment").val(cksname);

    $('#ysssbmWindow').window('close');
    //预算所属部门 结束
});
//清理预算体部门
function clearDep(){
    $("#txtYsssbm").val("");
    $("#txtBudgetDepartment").val("");
}
//点击查询按钮
$("#btnQuery").click(function() {
/*    var txtBeginDate = $("#beginDate").val();
    var txtEndDate = $("#endDate").val();
    if(txtBeginDate>txtEndDate){
        alert("开始时间不能大于结束时间");
        return false;
    }*/
    window.location.href = _dynamicURL+'/myTasks/searchMyTasksList?' + $('#purchaseApplyOrderForm').serialize();
});