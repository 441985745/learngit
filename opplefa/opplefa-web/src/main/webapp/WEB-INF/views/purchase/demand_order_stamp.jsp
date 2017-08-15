<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="app" uri="http://mrms.opple.com/approvalProcess"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>打印</title>
	<%@ include file="/WEB-INF/layouts/common.jsp" %>
	<link rel="Stylesheet" type="text/css" href="${staticURL}/discardsell/discardsell.css">
	<style media="print" type="text/css">
		.noprintbutton {
			display: none;
		}

		.noprint {
			display: none;
		}
	</style>
</head>
<body>
<div align="center">
	<input value="打印" type="button"  class="noprintbutton"  onclick="javascript:window.print()" />
	<OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height="0" id="wb" name="wb" width="0">
	</OBJECT>
	<input type=button name=button_print class="noprintbutton"  style="display:none;" value="打印本单据" onclick="javascript:printit()">
	<!-- <input type=button name=button_setup value="打印页面设置" onclick="javascript:printsetup();"> -->
	<input type=button name=button_show  class="noprintbutton"  value="打印预览" onclick="javascript:printpreview();">
	<input type=button name=button_fh  class="noprintbutton"  value="关闭" onclick="javascript:window.close();">
</div>
	<div  style="width:100%;height:100%;">
		<div>
			<img src="${staticURL}/assetcard/img/logo1.png" style="height: 80px;">
		</div>
		<table id="table_content" class="table_content"  align="center" style="width: 200mm;" >
			<tr style="text-align: center;">
				<th colspan="6" class="font_class">资产需求单</th>
			</tr>
			<tr>
				<td style="text-align: right">单据号:</td>
				<td>
					${demandOrder.cdocument}
				</td>
				<td style="text-align: right">所属公司:</td>
				<td>
				${demandOrder.companyName}
				</td>
				<td style="text-align: right">资产类型:</td>
				<td>
					${demandOrder.assetType}
				</td>
			</tr>
			<tr>
				<td style="text-align: right">申请人:</td>
				<td>
				${demandOrder.applyUser}
				</td>
				<td style="text-align: right;">办公地点:</td>
				<td class="tdtext">
				${demandOrder.officeLocation}
				</td>
				<td style="text-align: right">是否组装:</td>
				<td>
				${demandOrder.assemble=='N'?'否':''}${demandOrder.assemble=='Y'?'是':''}
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">申请日期:</td>
				<td class="tdtext">
				<fmt:formatDate value="${demandOrder.applyDate}"  pattern="yyyy-MM-dd"/>
				</td>
				<td style="text-align: right">平台:</td>
				<td class="tdtext">
				${demandOrder.platform}
				</td>
				<td style="text-align: right">是否房屋建筑物:</td>
				<td>
				${demandOrder.building=='N'?'否':''}${demandOrder.building=='Y'?'是':''}
				</td>
			</tr>
			<tr>
				<td style="text-align: right">申请部门:</td>
				<td class="tdtext">
				${demandOrder.applyDepartment}
				</td>
				<td style="text-align: right">区域:</td>
				<td class="tdtext">
				${demandOrder.region}
				</td>
				
				<td style="text-align: right;">车间类型:</td>
				<td class="tdtext">
				${demandOrder.workShopType}
				</td>
				
				
			</tr>
			<tr>
				<td style="text-align: right">预算所属部门:</td>
				<td>
				${demandOrder.budgetDepartmentName}
				</td>
				<td style="text-align: right">成本中心:</td>
				<td class="tdtext">
				${demandOrder.costCenter}
				</td>
			</tr>
		</table>
		<table class="table_list" style="margin-top: 20px; width: 200mm;">
			<tr>
				<td>序号</td>
				<td>项目编码</td>
				<td>预算资产名称</td>
				<td>资产名称</td>
				<td>规格参数</td>
				<td>功能</td>
				<td>选配件</td>
				<td>验收标准</td>
				<td>单位</td>
				<td>预算年度</td>
				<!-- 10 -->
				<td>需求数量</td>
				<td>已申请数量</td>
				<td>未申请数量</td>
				<td>调拨数量</td>
				<td>参考单价</td>
				<td>参考总价</td>
				<td>询价单价</td>
				<td>询价总价</td>
				<td>要求到位日期</td>
				<td>用途说明</td>
			</tr>
			<c:forEach items="${demandOrderDetailes}" var="demandOrderDetaile" varStatus="i">
			<tr>
				<td align="left">${i.index+1}</td>
				<td align="left">${demandOrderDetaile.projectCode}</td>
				<td align="left">${demandOrderDetaile.budgetAssetsName}</td>
				<td align="left">${demandOrderDetaile.assetsName}</td>
				<td align="left">${demandOrderDetaile.specificationParameter}</td>
				<td align="left">${demandOrderDetaile.functions}</td>
				<td align="left">${demandOrderDetaile.optionalAccessories}</td>
				<td align="left">${demandOrderDetaile.acceptanceCriteria}</td>
				<td align="left">${demandOrderDetaile.units}</td>
				<td align="center">${demandOrderDetaile.budgetYear}</td>
				<!-- 10 -->
				<td align="right">${demandOrderDetaile.demandCount}</td>
				<td align="right">${demandOrderDetaile.applyCount}</td>
				<td align="right">${demandOrderDetaile.notApplyCount}</td>
				<td align="right">${demandOrderDetaile.allocationCount}</td>
				<td isMoney='Y' name="tdOrderMoney" align="right">${demandOrderDetaile.referenceUnitPrice}</td>
				<td isMoney='Y' name="tdOrderMoney" align="right">${demandOrderDetaile.referencePrice}</td>
				<td isMoney='Y' name="tdOrderMoney" align="right">${demandOrderDetaile.inquiryUnitPrice}</td>
				<td isMoney='Y' name="tdOrderMoney" align="right">${demandOrderDetaile.inquiryPrice}</td>
				<td align="center"><fmt:formatDate value="${demandOrderDetaile.requirementsDate}"  pattern="yyyy-MM-dd"/></td>
				<td align="left">${demandOrderDetaile.useDescription}</td>
			</tr>
			</c:forEach>
		</table>
		
		<table class="table_list" style="margin-top: 20px; width: 200mm;">
			<tbody>
			<tr>
				<td class="titlefontsize" align="left" colspan="6">
				<div class="imgtitle"></div>
				立项书  &nbsp;&nbsp;
						<font color="red">*</font><font color="black">是否立项：
						<input type="radio" disabled  id="txtProjectProposal" name="txtProjectProposal" value="Y" ${demandOrder.projectProposal=="Y"?"checked='checked'" : ""}>是
						<input type="radio" disabled id="txtProjectProposal" name="txtProjectProposal" value="N" ${demandOrder.projectProposal=="N"?"checked='checked'" : ""}>否
						</font>
					</font>
				</td>
			</tr>
			<tr>
				<td style="text-align: right">项目名称:</td>
				<td align="left">
					${demandOrder.entryName}
				</td>
				<td style="text-align: right">立项金额:</td>
				<td name="tdOrderMoney" align="right">
					${demandOrder.projectMoney==0?'':demandOrder.projectMoney}
				</td>
				<td style="text-align: right">项目背景:</td>
				<td align="left">
					${demandOrder.projectBackground}
				</td>
			</tr>
			<tr>
				<td style="text-align: right">收益核算方式:</td>
				<td align="left">
					${demandOrder.incomeAccountingMethod}
				</td>
				<td style="text-align: right">年收益金额:</td>
				<td name="tdOrderMoney" name="tdOrderMoney" align="right">
					${demandOrder.annualIncomeAmount==0?'':demandOrder.annualIncomeAmount}
				</td>
				<td style="text-align: right">投资回收期:</td>
				<td align="center">
					${demandOrder.paybackPeriodOfInvestment}
				</td>
			</tr>
			<tr>
				<td style="text-align: right"><font color="red">*</font>项目类别:</td>
				<td colspan="5">
					<input disabled type="checkbox" value="外发转自制" name="txtProjectType">外发转自制
					<input disabled type="checkbox" value="产能提升" name="txtProjectType">产能提升
					<input disabled type="checkbox" value="替代人力" name="txtProjectType">替代人力
					<input disabled type="checkbox" value="效率提升" name="txtProjectType">效率提升
					<input disabled type="checkbox" value="能力建设" name="txtProjectType">能力建设
					<input disabled type="checkbox" value="旧设备替换" name="txtProjectType">旧设备替换
					<input disabled type="checkbox" value="基建" name="txtProjectType">基建
				</td>
			</tr>
			<tr>
				<td style="text-align: right"></td>
				<td colspan="5">
					<input disabled type="checkbox" value="其他" name="txtProjectType">其他
					<!-- <input readonly id="txtProjectTypeOther" type="text" value="" name="txtProjectType" size="70" maxlength="100"> -->
					<span id='txtProjectTypeOther'></span>
				</td>
			</tr>

			</tbody>
		</table>
		<table class="table_list" style="margin-top: 20px; width: 200mm;">
			<tbody>
			<tr>
				<td colspan="6">
					审批历史
				</td>

			</tr>

			<tr style="white-space: nowrap;">
				<td>审批人
				</td>
				<td>审批结果
				</td>
				<td colspan="2" border="0px;">审批意见
				</td>

				<td>签字
				</td>
			</tr>
			<tr style="white-space: nowrap;">
				<td>申请人
				</td>
				<td>同意
				</td>
				<td style="word-wrap : break-word ;" width="200px;" colspan="2" border="0px;">
					我承诺以上业务真实，否则视为本人为违反公司规定，愿意接受公司相关规定处罚
				</td>

				<td><span>申请人签字:${checkAcceptance.applyCheckUserName }</span></br>
					<span>时间:${checkAcceptance.dateStr}</span>
				</td>
			</tr>


			<c:forEach items="${appHistoryPager}" var="appHistory" varStatus="status">

				<tr style="white-space: nowrap;">
					<td class="tdleft" align="left">${appHistory.cuserrole}</td>
					<td class="tdleft" align="left">${appHistory.cstate}</td>
					<td class="tdleft" colspan="2" align="left">${appHistory.csyslog}</td>
					<td class="tdleft" align="left"><span>${appHistory.cuserrole}签字：${appHistory.cuser}</span></br>
						<span>时间：<fmt:formatDate value='${appHistory.ddate}' pattern='yyyy-MM-dd HH:mm:ss'/></span>
					</td>

				</tr>
			</c:forEach>
			</tbody>

		</table>
	</div>
	<script type="text/javascript">
		//给立项书多选框赋值
		var strProjectType = "${demandOrder.projectType}";
		var strs = strProjectType.split("-*-*-"); //字符分割 
		$.each(strs,function(i,item){
			if ($("input[name='txtProjectType'][value="+item+"]").length > 0) {
				$("input[name='txtProjectType'][value="+item+"]").attr("checked","checked");
			} else {
				$("#txtProjectTypeOther").html($("#txtProjectTypeOther").html()+item+" ");
			}
		});
		function printsetup(){
		  // 打印页面设置
		  wb.execwb(8,1);  
		}
		function printpreview(){
		  // 打印页面预览
		  wb.execwb(7,1);
		}
		function printit(){
		 if (confirm('确定打印吗？')){
		  wb.ExecWB(6,1)
		  //wb.execwb(1,1)//打开
		  //swb.ExecWB(2,1);//关闭现在所有的IE窗口，并打开一个新窗口
		  //wb.ExecWB(4,1)//;保存网页
		  //wb.ExecWB(6,1)//打印
		  //wb.ExecWB(7,1)//打印预览
		  //wb.ExecWB(8,1)//打印页面设置
		  //wb.ExecWB(10,1)//查看页面属性
		  //wb.ExecWB(15,1)//好像是撤销，有待确认
		  //wb.ExecWB(17,1)//全选
		  //wb.ExecWB(22,1)//刷新
		 /* wb.ExecWB(45,1)//关闭窗体无提示 */
		 }
		}
        $(function(){
            $("td[name='tdOrderMoney']").each(function(){
                if($(this).html()!=""){
                    $(this).html(fmoney($(this).html(),2));
                };
            });
        })
        /**
         * 数字格式转换成千分位
         */
        function fmoney(s, n)

        {
            n = n > 0 && n <= 20 ? n : 2;
            s = parseFloat((s + '').replace(/[^\d\.-]/g, '')).toFixed(n) + '';
            var l = s.split('.') [0].split('').reverse(),
                r = s.split('.') [1];
            var  t = '';
            for (var i = 0; i < l.length; i++)
            {
                t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? ',' : '');
            }
            return t.split('').reverse().join('') + '.' + r;

        }
		
	</script>
</body>
</html>