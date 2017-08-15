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
	<%--<style>
	@media print{
	　　.noprint{
	  　　display:none
	　　}
	}
	</style>--%>
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
		<table id="table_content" class="table_content"  align="left" style="width: 100%;font-size:13px;" >
			<tr style="text-align: center;">
				<th colspan="6" class="font_class">采购订单</th>
			</tr>
			<tr>
				<td style="text-align: left;">供方:&nbsp;${purchaseOrder.supplierCode=="1"||purchaseOrder.supplierCode=="2"?purchaseOrder.companySpecificName:purchaseOrder.supplierName}
				</td>
				<td style="text-align: left;">采购订单号:&nbsp;${purchaseOrder.purchaseOrderSapId}
				</td>
			</tr>
			<tr>
				<td style="text-align: left;">供应商地址:&nbsp;${purchaseOrder.companyAddress}
				</td>
				<td style="text-align: left">采购类别:&nbsp;${purchaseOrder.orderType=='ZAB'?'固定资产采购订单':'费用采购订单'}
				</td>
			</tr>
			<tr>
				<td style="text-align: left">联系电话:&nbsp;${purchaseOrder.telephone}
                </td>
				<td style="text-align: left">采购工程师:&nbsp;${purchaseOrder.buyerName}
				</td>
			</tr>
			<tr>
				<td style="text-align: left;">联系人:&nbsp;${purchaseOrder.contacts}
				</td>
				<td style="text-align: left;">订单日期:&nbsp;<fmt:formatDate value="${purchaseOrder.applyDate}"  pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			<tr>
				<td style="text-align: left">需方:&nbsp;${purchaseOrder.companyName}
                </td>
				<td style="text-align: left">打印日期:&nbsp;<c:set var="now" value="<%=new java.util.Date()%>" /><fmt:formatDate value="${now}"  pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			<tr>
				<td style="text-align: left;">送货地址:&nbsp;${purchaseOrder.deliveryAddress}
				</td>
				<td style="text-align: left" >税码/税率:&nbsp;<fmt:formatNumber type="number" value="${purchaseOrder.taxRate*100}" maxFractionDigits="0"/>%
				</td>
			</tr>
		</table>
		<table class="table_list" style="margin-top: 20px;">
			<tr>
				<td>序号</td>
                <td>主编码</td>
				<td>子编码</td>
				<td>资产名称</td>
				<td>单位</td>
				<td>采购数量</td>
				<td>采购单价(含税)</td>
				<td>采购总价(含税)</td>
				<td>约定交货日期</td>
				<td>型号规格/品牌</td>
				<td>采购申请人</td>
			</tr>
			<c:forEach items="${purchaseOrderDetailModels}" var="pod" varStatus="i">
			<tr style="white-space:nowrap;">
				<td align="left">${i.index+1}</td>
				<td align="left">${pod.mainAssetCode}</td>
				<td align="left">${pod.secondary}</td>
				<td align="left">${pod.assetsName}</td>
				<td align="left">${pod.units}</td>
				<td align="right">${pod.applyCount}</td>
				<td isMoney='Y' name="tdOrderMoney" align="right">${pod.purchaseUnitPrice}</td>
				<td isMoney='Y' name="tdOrderMoney" align="right">${pod.purchasePrice}</td>
				<td align="center"><fmt:formatDate value="${pod.requirementsDate}"  pattern="yyyy-MM-dd"/></td>
				<td align="left">${pod.brand}</td>
				<td align="left">${pod.applyUserForApplyOrder}</td>
			</tr>
			</c:forEach>
			<tr style="white-space:nowrap;">
				<td>合计:</td>
				<td align="left"></td>
				<td align="left"></td>
				<td align="left"></td>
				<td align="left"></td>
				<td align="right"></td>
				<td align="right" name="tdOrderMoney"></td>
				<td isMoney='Y' name="tdOrderMoney" align="right">${purchaseOrder.orderMoney}</td>
				<td align="center"></td>
				<td align="left"></td>
				<td align="left"></td>
			</tr>
			<tr style="white-space:nowrap;">
				<td colspan="11" align="left">抬头文本说明：${purchaseOrder.headerTextDescription}</td>
			</tr>
		</table>
		<table class="table_list" style="margin-top: 20px;">
			<tr >
				<td colspan="6">
					说明：1.供应商需要在4小时之内确认此订单，并签名盖章回传，如未在4小时之内确认订单或提出异意，视同于理解与接受订单所有条件，同意履行订单权利与义务。
					</br>2.逾期交货时，严格按采购协议的逾期交货处罚条款进行处罚，供方不得异议(经协商由我司同意的除外),贵司若无法按指定日期交货的我司保留取消本订单之权利。
					</br>3.贵司交货经检验若全部或部分不合格,依本公司指定期限内如数运交调换或退货.若不在指定期限退换的按逾期交货处理并加收取该批货款1-5%的保管费,若我方因</br>保管不善造成损失的由贵司自行承担.我方不承担任何责任。
					</br>4.提前交货或超订单交货或无订单交货的,我司将以贵司赠送的方式入库(我司要求提前的除外)。
					</br>5.所购材料规格、技术参数、质量以首次送样时之《材料确认单》为准。
					</br>6.本厂验货抽样标准和检验标准以首次通知为准。
					</br>7.如果由于供应商的产品品质问题、混料问题、延迟交货时间所导致我公司被经销商或者客户罚款的,所被罚款的金额将全部由供应商承担。
					</br>8.送货时请务必在送货单上注明采购订单号及物料代码，且物料名称与采购订单物料名称一致。否则收货处不予收货、财务不予入账。
				</td>
			</tr>
			<%--<tr style="white-space:nowrap;height: 100px;" align="center"  >--%>
				<%--<td style="width: 1%;">供应商</td>--%>
				<%--<td style="width: 15%;">经办人/时间</br>签章/时间</td>--%>
				<%--<td style="width: 38%;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>--%>
				<%--<td style="width: 1%;">欧普照明采购中心</td>--%>
				<%--<td style="width: 15%;">经办人/时间</br>签章/时间</td>--%>
				<%--<td style="width: 30%;" align="center"><c:set var="now" value="<%=new java.util.Date()%>" />${purchaseOrder.buyerName}/<fmt:formatDate value="${now}"  pattern="yyyy-MM-dd"/></td>--%>
			<%--</tr>--%>
		</table>
	</div>
	<script type="text/javascript">
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