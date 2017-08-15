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
		<table id="table_content" class="table_content"  align="center" style="width: 100%" >
			<tr style="text-align: center;">
				<th colspan="6" class="font_class">采购申请单</th>
			</tr>
			<tr>
				<td style="text-align: right">单据号:</td>
				<td>
				    ${applyOrder.cdocument}
				</td>
				<td style="text-align: right">所属公司:</td>
				<td>
				   ${applyOrder.companyName}
				</td>
               <td style="text-align: right">资产类型:</td>
               <td>
                  ${applyOrder.assetType}
               </td>
            </tr>
            <tr>
           	   <td style="text-align: right">申请人:</td>
               <td>
                   ${applyOrder.applyUser}
               </td>
				<td style="text-align: right;">办公地点:</td>
			   <td class="tdtext">
					${applyOrder.officeLocation}
			   </td>
				<td id="tdAssemble" style="text-align: right;">是否组装:</td>
				<td id="tdAssembles">
					${applyOrder.assemble=='Y'?'是':''}${applyOrder.assemble=='N'?'否':''}
				</td>
             </tr>
             <tr>
				<td style="text-align: right">申请日期:</td>
				<td>
				    <fmt:formatDate value='${applyOrder.applyDate}'  pattern='yyyy-MM-dd'/>
				</td>
				<td style="text-align: right">平台:</td>
				<td class="tdtext">
					${applyOrder.platform}
				</td>
				<td id="tdBuilding" style="text-align: right;">是否房屋建筑物:</td>
				<td id="tdBuildings">
					${applyOrder.building=='Y'?'是':''}${applyOrder.building=='N'?'否':''}
				</td>
              </tr>
              <tr>
					<td style="text-align: right">申请人部门:</td>
					<td>
					     ${applyOrder.applyDepartment}
					</td>
					<td style="text-align: right">区域:</td>
					<td class="tdtext">
						${applyOrder.region}
					</td>
					<td id="tdWorkShopType" style="text-align: right;">车间类型:</td>
					<td id="tdWorkShopTypes">
			               ${applyOrder.workShopType}
					</td>
               </tr>
               <tr>
					<td style="text-align: right">归口/预算部门:</td>
					<td>
					    ${applyOrder.budgetDepartmentName}
					</td>
					<td style="text-align: right">成本中心:</td>
					<td>
					    ${applyOrder.costCenter}
					</td>
                     <td style="text-align: right">一次性费用化资产:</td>
				   <td>
				      ${applyOrder.expensing=='N'?'否':'是'}
				   </td>
                </tr>
                <tr>
              		<td style="text-align: right">采购员:</td>
					<td class="tdtext">
						${applyOrder.buyer}
					</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
               </tr>
		</table>
		<table class="table_list" style="margin-top: 20px;">
			<tr>
				<td>合并号</td>
				<td>资产名称</td>
				<td>规格参数</td>
				<td>功能</td>
				<td>验收标准</td>
				<td>选配件</td>
				<td>本次申请数量</td>
				<td>单位</td>
				<td>询价单价</td>
				<td>询价总价</td>
				<!-- 10 -->
				<td>要求到位日期</td>
				<!-- <td>采购单价</td>
				<td>采购总价</td>
				<td>采购回复交期</td> -->
				<td>资产分类</td>
				<td>折旧年限</td>
				<td>编码方式</td>
				<td>主编码</td>
				<td>子编码</td>
				<td>总账科目</td>
				<!-- 20 -->
				<td>成本中心</td>
			</tr>
			<c:forEach items="${applyOrderDetailes}" var="applyOrderDetaile" varStatus="i">
			<tr>
				<td class='tdpadding' align="left">${applyOrderDetaile.mergeNumber}</td>
				<td class='tdpadding' align="left">${applyOrderDetaile.assetsName}</td>
				<td class='tdpadding' align="left">${applyOrderDetaile.specificationParameter}</td>
				<td class='tdpadding' align="left">${applyOrderDetaile.functions}</td>
				<td class='tdpadding' align="left">${applyOrderDetaile.acceptanceCriteria}</td>
				<td class='tdpadding' align="left">${applyOrderDetaile.optionalAccessories}</td>
				<td class='tdpadding' align="right">${applyOrderDetaile.demandCount}</td>
				<td class='tdpadding' align="left">${applyOrderDetaile.units}</td>
				<td class='tdpadding' name="tdOrderMoney" isMoney='Y' align="right">${applyOrderDetaile.inquiryUnitPrice}</td>
				<td class='tdpadding' name="tdOrderMoney" isMoney='Y' align="right">${applyOrderDetaile.inquiryPrice}</td><!-- 10 -->
				<td class='tdpadding' align="center"><fmt:formatDate value="${applyOrderDetaile.requirementsDate}"  pattern="yyyy-MM-dd"/></td>
				<%-- <td class='tdpadding'>${applyOrderDetaile.purchaseUnitPrice}</td>
				<td class='tdpadding'>${applyOrderDetaile.purchasePrice}</td>
				<td class='tdpadding'>${applyOrderDetaile.procurementReturnPeriod}</td> --%>
				<td class='tdpadding' align="left">${applyOrderDetaile.assetClassification}</td>
				<td class='tdpadding' align="center">${applyOrderDetaile.depreciation}</td>
				<td class='tdpadding' align="left">${applyOrderDetaile.codingMode}</td>
				<td class='tdpadding' align="left">${applyOrderDetaile.mainAssetCode}</td>
				<td class='tdpadding' align="left">${applyOrderDetaile.secondary}</td>
				<td class='tdpadding' align="left">${applyOrderDetaile.ledgerAccount}</td><!-- 20 -->
				<td class='tdpadding' align="left">${applyOrderDetaile.costCenter}</td>
			</tr>
			</c:forEach>
		</table>
		
		<table class="table_list" style="margin-top: 20px;">
			<tr style="white-space:nowrap;">
				<td>合并号</td>
				<td>资产名称</td>
				<td>规格参数</td>
				<td>功能</td>
				<td>验收标准</td>
				<td>选配件</td>
				<td>本次申请数量</td>
				<td>单位</td>
				<td>询价单价</td>
				<!-- 10 -->
				<td>要求到位日期</td>
			</tr>
			<c:forEach items="${applyDemandMappingModels}" var="applyDemandMappingModel" varStatus="i">
			<tr style="white-space:nowrap;">
				<td class='tdpadding' align="left">${applyDemandMappingModel.mergeNumber}</td>
				<td class='tdpadding' align="left">${applyDemandMappingModel.assetsName}</td>
				<td class='tdpadding' align="left">${applyDemandMappingModel.specificationParameter}</td>
				<td class='tdpadding' align="left">${applyDemandMappingModel.functions}</td>
				<td class='tdpadding' align="left">${applyDemandMappingModel.acceptanceCriteria}</td>
				<td class='tdpadding' align="left">${applyDemandMappingModel.optionalAccessories}</td>
				<td class='tdpadding' align="right">${applyDemandMappingModel.demandCount}</td>
				<td class='tdpadding' align="left">${applyDemandMappingModel.units}</td>
				<td class='tdpadding' name="tdOrderMoney" isMoney='Y' align="right">${applyDemandMappingModel.inquiryUnitPrice}</td>
				<td class='tdpadding' align="center"><fmt:formatDate value="${applyDemandMappingModel.requirementsDate}"  pattern="yyyy-MM-dd"/></td>
			</tr>
			</c:forEach>
		</table>
        <table class="table_list" style="margin-top: 20px;">
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