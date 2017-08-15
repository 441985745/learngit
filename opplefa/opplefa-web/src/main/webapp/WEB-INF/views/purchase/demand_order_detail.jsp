<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="app" uri="http://mrms.opple.com/approvalProcess"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>资产需求单</title>
	<%@ include file="/WEB-INF/layouts/common.jsp" %>
	<link href="${staticURL}/Styles/FormPanelNew.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">
</head>
<body>
	<div id="btndiv" style="margin-left: 0px; margin-top: 0px;">
		<div id="tabletitle" style="height: 25px; font-size: 20px; font-weight: bold; font-family: 宋体; text-align: center; vertical-align: middle">
		        资产需求单${demandOrder.draft=='Y'?"(草稿)":""}</div>
	</div>
	<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
		<input type="submit" name="btnCancel" value="返回" onclick="dg.cancel();" id="btnCancel" class="btn">
		<input  type="button" value="打印" class="btn" onclick="javascript:stamp('${demandOrder.cdocument}');"/>
	</div>
	<div id="Div2" style="margin-left: 0px; margin-top: 0px;">
		<app:approvalTag billCode="${demandOrder.cdocument}" typeId="77001"></app:approvalTag>
	</div>
	<div style="margin-top: 10px;">
	<div id="purchaseTab" class="easyui-tabs" >
		<div title="基本信息" id="tabsa" style="overflow:auto; padding:20px;min-width: 800px;" >
			<div style="min-width: 800px" width="100%">
			<table id="Table5" cellpadding="0px" cellspacing="0" class="tablenoborder" style="width:100%;">
				<tbody><tr>
					<td class="titlefontsize" align="left">
					<div class="imgtitle"></div>
					基本信息</td>
				</tr>
				<tr>
					<td>
						<table id="Table8" border="0" cellpadding="3px" cellspacing="0" width="100%">
							<tbody>
							<tr>
								<td style="text-align: right">单据号:</td>
								<td>
									<input class="searchinput" readonly="readonly" name="txtDemandOrderId" type="text" value="${demandOrder.cdocument}" id="txtDemandOrderId">
								</td>
								<td style="text-align: right">所属公司:</td>
								<td>
								<input class="searchinput" readonly="readonly" name="lblUser" type="text" value="${demandOrder.companyName}" id="lblUser">
								</td>
								<td style="text-align: right">资产类型:</td>
								<td>
									<input class="searchinput" readonly="readonly" name="lblPurCode" type="text" value="${demandOrder.assetType}" >
								</td>
							</tr>
							<tr>
								<td style="text-align: right">申请人:</td>
								<td>
								<input class="searchinput" readonly="readonly" name="txtApplyUser" type="text" value="${demandOrder.applyUser}" id="txtApplyUser">
								</td>
								<td style="text-align: right;">办公地点:</td>
								<td class="tdtext">
								<input class="searchinput" readonly="readonly" name="lblManager" type="text" value="${demandOrder.officeLocation}" id="lblManager">
								</td>
								<td style="text-align: right">是否组装:</td>
								<td>
								<input class="searchinput" readonly="readonly" name="lblUser" type="text" value="${demandOrder.assemble=='N'?'否':''}${demandOrder.assemble=='Y'?'是':''}" id="lblUser">
								</td>
							</tr>
							<tr>
								<td style="text-align: right;">申请日期:</td>
								<td class="tdtext">
								<input class="searchinput" readonly="readonly" name="txtApplyDate" type="text" value="<fmt:formatDate value="${demandOrder.applyDate}"  pattern="yyyy-MM-dd"/>" id="txtApplyDate">
								</td>
								<td style="text-align: right">平台:</td>
								<td class="tdtext">
								<input class="searchinput" readonly="readonly" name="lblCom" type="text" value="${demandOrder.platform}" id="lblCom">
								</td>
								<td style="text-align: right">是否房屋建筑物:</td>
								<td>
								<input class="searchinput" readonly="readonly" name="lblUser" type="text" value="${demandOrder.building=='N'?'否':''}${demandOrder.building=='Y'?'是':''}" id="lblUser">
								</td>
							</tr>
							<tr>
								<td style="text-align: right">申请部门:</td>
								<td class="tdtext">
								<input class="searchinput" readonly="readonly" name="lblCom" type="text" value="${demandOrder.applyDepartment}" id="lblCom">
								</td>
								<td style="text-align: right">区域:</td>
								<td class="tdtext">
								<input class="searchinput" readonly="readonly" name="lblCom" type="text" value="${demandOrder.region}" id="lblCom">
								</td>

								<td style="text-align: right;">车间类型:</td>
								<td class="tdtext">
								<input class="searchinput" readonly="readonly" name="lblManager" type="text" value="${demandOrder.workShopType}" id="lblManager">
								</td>


							</tr>
							<tr>
								<td style="text-align: right">预算所属部门:</td>
								<td>
									<input class="searchinput" readonly="readonly" name="lblPurCode" type="text" value="${demandOrder.budgetDepartmentName}" >
								</td>
								<td style="text-align: right">成本中心:</td>
								<td class="tdtext">
								<input class="searchinput" readonly="readonly" name="lblCom" type="text" value="${demandOrder.costCenter}" id="lblCom">
								</td>
							</tr>
							</tbody>
						</table>
					</td>
				</tr> </tbody>
			</table>
			</br>
			<table id="Table5" cellpadding="0px" cellspacing="0" class="tablenoborder" style="width:100%;">
				<tbody>
				<tr>
					<td class="titlefontsize" align="left">
					<div class="imgtitle"></div>
					申购资产需求</td>
				</tr>
				<tr>
					<td colspan="2">
						<table id="Table10" width="100%"  border="0" cellpadding="0" cellspacing="0" style="table-layout: fixed;">
							<tbody>
							<tr>
								<td class="tdtitle" style="text-align: right" colspan="2">
									<div id="div1" class="divGrid" style="overflow-x: scroll; overflow-y: visible;">
										<div>
											<table class="adminlist" cellspacing="0" rules="all" border="1" id="gridDataList" style="border-collapse:collapse;">
												<tbody>
												<tr style="white-space:nowrap;">
													<th scope="col" class="tdpadding">序号</th>
													<th scope="col" class="tdpadding">项目编码</th>
													<th scope="col" class="tdpadding">预算资产名称</th>
													<th scope="col" class="tdpadding">资产名称</th>
													<th scope="col" class="tdpadding">规格参数</th>
													<th scope="col" class="tdpadding">功能</th>
													<th scope="col" class="tdpadding">选配件</th>
													<th scope="col" class="tdpadding">验收标准</th>
													<th scope="col" class="tdpadding">单位</th>
													<th scope="col" class="tdpadding">预算年度</th>
													<!-- 10 -->
													<th scope="col" class="tdpadding">需求数量</th>
													<th scope="col" class="tdpadding">已申请数量</th>
													<th scope="col" class="tdpadding">未申请数量</th>
													<th scope="col" class="tdpadding">调拨数量</th>
													<th scope="col" class="tdpadding">参考单价</th>
													<th scope="col" class="tdpadding">参考总价</th>
													<th scope="col" class="tdpadding">询价单价</th>
													<th scope="col" class="tdpadding">询价总价</th>
													<th scope="col" class="tdpadding">要求到位日期</th>
													<th scope="col" class="tdpadding">用途说明</th>
												</tr>
												<c:forEach items="${demandOrderDetailes}" var="demandOrderDetaile" varStatus="i">
												<tr style="white-space:nowrap;">
													<td class='tdpadding'>${i.index+1}</td>
													<td class='tdpadding'>${demandOrderDetaile.projectCode}</td>
													<td class='tdpadding'>${demandOrderDetaile.budgetAssetsName}</td>
													<td class='tdpadding'>${demandOrderDetaile.assetsName}</td>
													<td class='tdpadding'>${demandOrderDetaile.specificationParameter}</td>
													<td class='tdpadding'>${demandOrderDetaile.functions}</td>
													<td class='tdpadding'>${demandOrderDetaile.optionalAccessories}</td>
													<td class='tdpadding'>${demandOrderDetaile.acceptanceCriteria}</td>
													<td class='tdpadding'>${demandOrderDetaile.units}</td>
													<td class='tdpadding'>${demandOrderDetaile.budgetYear}</td>
													<!-- 10 -->
													<td class='tdpadding' id="tdDemandCount" v='${demandOrderDetaile.demandCount}'>${demandOrderDetaile.demandCount}</td>
													<td class='tdpadding'>${demandOrderDetaile.applyCount}</td>
													<td class='tdpadding'>${demandOrderDetaile.notApplyCount}</td>
													<td class='tdpadding'>${demandOrderDetaile.allocationCount}</td>
													<td class='tdpadding' isMoney='Y'>${demandOrderDetaile.referenceUnitPrice}</td>
													<td class='tdpadding' isMoney='Y' id="tdReferencePrice" v='${demandOrderDetaile.referencePrice}'>${demandOrderDetaile.referencePrice}</td>
													<td class='tdpadding' isMoney='Y'>${demandOrderDetaile.inquiryUnitPrice}</td>
													<td class='tdpadding' isMoney='Y' id="tdInquiryPrice" v='${demandOrderDetaile.inquiryPrice}'>${demandOrderDetaile.inquiryPrice}</td>
													<td class='tdpadding'><fmt:formatDate value="${demandOrderDetaile.requirementsDate}"  pattern="yyyy-MM-dd"/></td>
													<td class='tdpadding'>${demandOrderDetaile.useDescription}</td>
												</tr>
												</c:forEach>

												</tbody>
												<tr style="white-space: nowrap;">
						                            <td>合计</td>
						                            <td></td>
						                            <td></td>
						                            <td></td>
						                            <td></td>
						                            <td></td>
						                            <td></td>
						                            <td></td>
						                            <td></td>
						                            <td></td>
						                            <td id="tdSumDemandCount"></td>
						                            <td></td>
						                            <td></td>
						                            <td></td>
						                            <td></td>
						                            <td id="tdSumReferencePrice"></td>
						                            <td></td>
						                            <td id="tdSumInquiryPrice"></td>
						                            <td></td>
						                            <td></td>
					                            </tr>
											</table>
										</div>
									</div>
								</td>
							</tr>
							</tbody>
						</table>
					</td>

                </tr>
				</tbody>
			</table>
			</br>
				<table cellpadding="0" cellspacing="0" border="0" style="width:100%">
					<tbody>
					<tr>
						<td class="titlefontsize" align="left" style="width: 150px">
							<div class="imgtitle"></div>
							附件
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="divGrid">
								<div>
									<table id="uploadTable" class="adminlist" cellspacing="0" rules="all" border="1" id="gvDataList" style="border-collapse:collapse;">
										<thead>
										<tr style="white-space:nowrap;">
											<th scope="col">
												<input  type="checkbox" name="gvDataList$ctl01$CheckAll" onclick="javascript:SelectAllCheckboxesByTableId(this, 'uploadTable');">
											</th>
											<th scope="col"> 文件</th>
											<th scope="col">上传人</th>
											<th scope="col">上传时间</th>
											<th scope="col">附件下载</th>
										</tr>
										</thead>
										<tbody>
										<tr class="noItems" align="center">
											<td colspan="5">没有记录！</td>
										</tr>
										</tbody>
									</table>
								</div>
							</div>
						</td>
					</tr>
					</tbody>
				</table>
				<table cellpadding="0" cellspacing="0" border="0" width="100%">
	                <tbody>
	                <tr>
	                    <td class="titlefontsize" align="left" style="width: 150px">
	                        <div class="imgtitle"></div>
	                        		审批历史
	                    </td>
	                    <td></td>
	                </tr>
	                <tr>
	                    <td colspan="2">
	                        <div class="divGrid">
	                            <div>
	                                <table class="adminlist" cellspacing="0" rules="all" border="1" style="border-collapse:collapse;">
	                                    <thead>
	                                    <tr style="white-space: nowrap;">
	                                        <th scope="col">审批人</th>
	                                        <th scope="col">审批日期</th>
	                                        <th scope="col">审批结果</th>
	                                        <!-- <th scope="col">审批金额</th> -->
	                                        <th scope="col">审批意见</th>
	                                    </tr>
	                                    </thead>
	                                    <tbody>
	                                    <c:forEach items="${appHistoryPager.records}" var="appHistory" varStatus="status">
	                                        <tr style="white-space: nowrap;">
	                                            <td class="tdleft">${appHistory.examineUser}</td>
	                                            <td class="tdleft"><fmt:formatDate value='${appHistory.examineDate}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
	                                            <td class="tdleft">${appHistory.examineResult}</td>
	                                            <%-- <td class="tdleft">${appHistory.examineMoney}</td> --%>
	                                            <td class="tdleft">${appHistory.examineAdvice}</td>
	                                        </tr>
	                                    </c:forEach>
	                                    </tbody>
	                                </table>
	                                <tag:paginate offset="${appHistoryPager.offset}" total="${appHistoryPager.total}"
	                                              steps="${appHistoryPager.pageSize}" uri="${appHistoryPager.uri}"
	                                              formId=""/>
	                            </div>
	                        </div>
	                    </td>
	                </tr>
                </tbody>
            </table>
			</div>
		</div>
		<div title="立项书" data-options="closable:false" style="overflow:auto;padding:20px;">
				<table id="Table5" cellpadding="0px" cellspacing="0" class="tablenoborder">
			<tbody>
			<tr>
				<td class="titlefontsize" align="left">
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
				<td>

					<table id="Table8" border="0" cellpadding="3px" cellspacing="0" width="100%">
						<tbody>
							<tr>
								<td style="text-align: right">项目名称:</td>
								<td>
									<input readonly name="txtEntryName" type="text" value="${demandOrder.entryName}" id="txtEntryName">
								</td>
								<td style="text-align: right">立项金额:</td>
								<td>
									<input readonly name="txtProjectMoney" type="text" value="${demandOrder.projectMoney==0?'':demandOrder.projectMoney}" id="txtProjectMoney">
								</td>
								<td style="text-align: right">项目背景:</td>
								<td>
									<input readonly name="txtProjectBackground" type="text" value="${demandOrder.projectBackground}" id="txtProjectBackground">
								</td>
							</tr>
							<tr>
								<td style="text-align: right">收益核算方式:</td>
								<td>
									<input readonly name="txtIncomeAccountingMethod" type="text" value="${demandOrder.incomeAccountingMethod}" id="txtIncomeAccountingMethod">
								</td>
								<td style="text-align: right">年收益金额:</td>
								<td>
									<input readonly name="txtAnnualIncomeAmount" type="text" value="${demandOrder.annualIncomeAmount==0?'':demandOrder.annualIncomeAmount}" id="txtAnnualIncomeAmount">
								</td>
								<td style="text-align: right">投资回收期:</td>
								<td>
									<input readonly name="txtPaybackPeriodOfInvestment" type="text" value="${demandOrder.paybackPeriodOfInvestment}" id="txtPaybackPeriodOfInvestment">
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
									<input readonly id="txtProjectTypeOther" type="text" value="" name="txtProjectType" size="70" maxlength="100">
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>

			</tbody>
		</table>
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
					<tbody>
					<tr>
						<td class="titlefontsize" align="left" style="width: 150px">
							<div class="imgtitle"></div>
							附件
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="divGrid">
								<div>
									<table id="uploadTable2" class="adminlist" cellspacing="0" rules="all" border="1" id="gvDataList2" style="border-collapse:collapse;">
										<thead>
										<tr style="white-space:nowrap;">
											<th scope="col">
												<input  type="checkbox" name="gvDataList$ctl01$CheckAll" onclick="javascript:SelectAllCheckboxesByTableId(this, 'uploadTable2');">
											</th>
											<th scope="col"> 文件</th>
											<th scope="col">上传人</th>
											<th scope="col">上传时间</th>
											<th scope="col">附件下载</th>
										</tr>
										</thead>
										<tbody>
										<tr class="noItems" align="center">
											<td colspan="5">没有记录！</td>
										</tr>
										</tbody>
									</table>
								</div>
							</div>
						</td>
					</tr>
					</tbody>
				</table>


		</div>

		</div>
	</div>
<%-- <script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
<script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>--%>
<script src="${staticURL}/Scripts/config/config.js" type="text/javascript"></script>
<script type="text/javascript">
	var dg = frameElement.lhgDG;//定义dg 关闭需要

	//给立项书多选框赋值
	var strProjectType = "${demandOrder.projectType}";
	var strs = strProjectType.split("-*-*-"); //字符分割
	$.each(strs,function(i,item){
		if ($("input[name='txtProjectType'][value="+item+"]").length > 0) {
			$("input[name='txtProjectType'][value="+item+"]").attr("checked","checked");
		} else {
			$("#txtProjectTypeOther").val($("#txtProjectTypeOther").val()+item+" ");
		}
	});
    $(function(){
        $.ajax({
            url:'${dynamicURL}/file/getFilesByDocument',
            data:{'document':$("#txtDemandOrderId").val()},
            dataType:'json',
            type:'POST',
            success:function(data){
                if(data.length > 0 ){
                    $('#uploadTable .noItems').remove();
                    $.each(data,function(i,item){
                        $('#uploadTable > tbody').append('<tr id="tr" class="'+item.fileuuid + '" style="white-space:nowrap;">'
                            + '<td style="text-align:center;"><input type="checkbox" uuid="' + item.fileuuid + '" name=documentId />'
                            + '</td><td scope="col">' + item.fileName
                            + '</td><td scope="col">' + item.createUserName
                            + '</td><td style="width:9%">' + item.createDate
                            + '</td><td align="center" style="width:10%"><a href="'+'${dynamicURL}/file/downloadFile?fileuuid=' + item.fileuuid + '">下载</a>'
                            + '</td></tr>');
                    });
                }

            }
        });
        var documentLX = $("#txtDemandOrderId").val() + 'LX' ;//立项书对应document
        $.ajax({
            url:'${dynamicURL}/file/getFilesByDocument',
            data:{'document':documentLX},
            dataType:'json',
            type:'POST',
            success:function(data){
                if(data.length > 0 ){
                    $('#uploadTable2 .noItems').remove();
                    $.each(data,function(i,item){
                        $('#uploadTable2 > tbody').append('<tr id="tr" class="'+item.fileuuid + '" style="white-space:nowrap;">'
                            + '<td style="text-align:center;"><input type="checkbox" uuid="' + item.fileuuid + '" name=documentId />'
                            + '</td><td scope="col">' + item.fileName
                            + '</td><td scope="col">' + item.createUserName
                            + '</td><td style="width:9%">' + item.createDate
                            + '</td><td align="center" style="width:10%"><a href="'+'${dynamicURL}/file/downloadFile?fileuuid=' + item.fileuuid + '">下载</a>'
                            + '</td></tr>');
                    });
                }

            }
        });
    });
    /**
     * 数字格式转换成千分位
     */
     function fmoney(s, n){
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
	    $(function(){
	        $("td[isMoney='Y']").each(function(){
	            if($(this).html()!=""){
	                $(this).html(fmoney($(this).html(),2));
	            };
	        });

	        var tdSumDemandCount = 0;
	        $("td[id='tdDemandCount']").each(function(){
	        	var tdDemandCount = $.trim($(this).attr("v"));
	        	tdSumDemandCount += tdDemandCount-0;
	        });
	        $("#tdSumDemandCount").html(tdSumDemandCount);

	        var tdSumReferencePrice = 0;
	        $("td[id='tdReferencePrice']").each(function(){
	        	var tdReferencePrice = $.trim($(this).attr("v"));
	        	tdSumReferencePrice += tdReferencePrice-0;
	        });
	        $("#tdSumReferencePrice").html(fmoney(tdSumReferencePrice,2));

	        var tdSumInquiryPrice = 0;
	        $("td[id='tdInquiryPrice']").each(function(){
	        	var tdInquiryPrice = $.trim($(this).attr("v"));
	        	tdSumInquiryPrice += tdInquiryPrice-0;
	        });
	        $("#tdSumInquiryPrice").html(fmoney(tdSumInquiryPrice,2));


	    });

    $(document).ready(function(){
        $(window).resize(function(){
            $('#purchaseTab').tabs('resize');
        });
    });
    function stamp(id){

        var lurl = "${dynamicURL}/demand/printOrder?id=" + id + "&typeId=77001";
        window.open(lurl);

    }
</script>
</body>
</html>