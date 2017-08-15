<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<title>欧普</title>
		<link rel="stylesheet" href="${staticURL}/css/bootstrap.min.css" />
		<style>
			.my-body {
				width: 100%;
				height: auto;
			}
			
			.my-text {
				width: 260px;
			}
			
			.my-con {
				margin-top: 8px;
				width: 100%;
			}
			
			.my-but {
				width: 60px;
				height: 30px;
				border: 1px #758897 solid;
				margin-left: 1.5%;
				background: #FFFFFF;
				background: -moz-linear-gradient(top, #FFFFFF 10%, #E2EAF5 50%);
				background: -webkit-gradient(linear, left top, left bottom, color-stop(10%, #FFFFFF), color-stop(50%, #E2EAF5));
				background: -webkit-linear-gradient(top, #FFFFFF 10%, #E2EAF5 50%);
				background: -o-linear-gradient(top, #FFFFFF 10%, #E2EAF5 50%);
				background: -ms-linear-gradient(top, #FFFFFF 10%, #E2EAF5 50%);
				background: linear-gradient(to bottom, #FFFFFF 10%, #E2EAF5 50%);
				filter: progid: DXImageTransform.Microsoft.gradient( startColorstr='#FFFFFF', endColorstr='#E2EAF5', GradientType=0);
			}
			
			:root .my-but {
				filter: none;
			}
			
			.my-but2 {
				width: 60px;
				height: 30px;
				border: 1px #758897 solid;
				margin-left: 0.2%;
				background: #FFFFFF;
				background: -moz-linear-gradient(top, #FFFFFF 10%, #E2EAF5 50%);
				background: -webkit-gradient(linear, left top, left bottom, color-stop(10%, #FFFFFF), color-stop(50%, #E2EAF5));
				background: -webkit-linear-gradient(top, #FFFFFF 10%, #E2EAF5 50%);
				background: -o-linear-gradient(top, #FFFFFF 10%, #E2EAF5 50%);
				background: -ms-linear-gradient(top, #FFFFFF 10%, #E2EAF5 50%);
				background: linear-gradient(to bottom, #FFFFFF 10%, #E2EAF5 50%);
				filter: progid: DXImageTransform.Microsoft.gradient( startColorstr='#FFFFFF', endColorstr='#E2EAF5', GradientType=0);
			}
			
			th {
				background: #E6E6E6;
				border-color: #FFFFFF;
				background: -moz-linear-gradient(top, #FFFFFF 10%, #DCDCDC 60%);
				background: -webkit-gradient(linear, left top, left bottom, color-stop(10%, #FFFFFF), color-stop(60%, #DCDCDC));
				background: -webkit-linear-gradient(top, #FFFFFF 10%, #DCDCDC 60%);
				background: -o-linear-gradient(top, #FFFFFF 10%, #DCDCDC 60%);
				background: -ms-linear-gradient(top, #FFFFFF 10%, #DCDCDC 60%);
				background: linear-gradient(to bottom, #FFFFFF 10%, #DCDCDC 60%);
				filter: progid: DXImageTransform.Microsoft.gradient( startColorstr='#FFFFFF', endColorstr='#DCDCDC', GradientType=0);
			}
			
			.dl {
				width: 45%;
				text-align: right;
				float: left;
				margin-top: 8px;
			}
			
			.dr {
				width: 45%;
				text-align: left;
				float: left;
				margin-top: 8px;
			}
			.table th,.table td
			{
			
word-break: keep-all;white-space:nowrap;			}
		</style>
	</head>

	<body>
		<div class="my-body">
			<center>
				<div style="width: 95%; height: 15px; text-align: left;">
					<h3>报废变卖</h3>
				</div>
				<div style="width: 95%; height: 55px;text-align: center;border:1px #fff solid;">
					<div style="text-align: left;margin-top: 20px;">
						<div style="float: left;">
							<h5>查询条件</h5>
						</div>
						<div style="float: right;width: 94%;">
							<hr />
						</div>
					</div>
				</div>
				<div style="width: 95%;height: 215px;text-align: center;background: #FAFAFA;">
					<form id="formId" >
						<table  class="table table-bordered table-striped" style="margin-left: +30px;width: 95%;">
						<tr >
								<td >单据号：</td>
								<td ><input type="text" class="my-text"  name="applyPeople"  id="applyPeople" /></td>
								<td >申请日期：</td>
								<td ><input type="text" style="width: 260px;height: 28px;" class="Wdate"  name="" id="applicationDate" /></td>
						</tr>
						<tr >
								<td >申请人：</td>
								<td ><input type="text" class="my-text"  name="originalCompany" id="originalCompany" /></td>
								<td >所属部门：</td>
								<td ><input type="text" class="my-text"  id="postAllocationPeople" name="postAllocationPeople" /></td>
						</tr>
						<tr >
								<td >归属部门：</td>
								<td ><select class="my-text" id="isExpenseAssets" name="isExpenseAssets"><option value="" >请选择</option> <option value="1">是</option> <option value="0">否</option></select></td>
								<td >办公地点：</td>
								<td ><input type="text" class="my-text" id="postAllocationDepartment"  name="postAllocationDepartment" /></td>
						</tr>
						<tr >
								<td >资产类型：</td>
								<td ><select class="my-text"  name="assetType"  id="assetType"><option value="" >请选择</option> <option value="0">IT资产(电脑类)</option> <option value="1">IT资产(非电脑类)</option> <option value="3">计量器具</option> <option value="4">机器设备</option> <option value="2">行政资产</option> </select></td>
								<td >会计年度：</td>
								<td ><input type="text" class="my-text" name="postAllocationCostCenter" id="postAllocationCostCenter" /></td>
						</tr>
						<tr >
								<td >是否为草稿：</td>
								<td ><input type="text" class="my-text" id="workshopType" name="workshopType" /></td>
								<td >审批状态：</td>
								<td ><input type="text" class="my-text" id="postAllocationCompany" name="postAllocationCompany"  /></td>
						</tr>
					</table>
					</form>
				</div>
				
				<div style="width: 95%; height: 30px;text-align: center;margin-top: 30px;">
					<div style="width: 100%; height:33px;text-align: left;background: #F2F2F2;">
						<input type="button" name="opInquiry" value="查询" id="opInquiry" class="my-but2">
						<button class="my-but2" onclick="opApplication('1','数据申请');" style="cursor:pointer;">申请</button>
						<button class="my-but2">审批</button>
						<button class="my-but2">删除</button>
						<button class="my-but2">导出</button>
						<button class="my-but2">打印</button>
						<button class="my-but2">关闭</button>
					</div>
				</div>
				
				<div style="overflow:scroll">
							<table  class="table table-bordered table-striped" style="margin-left: +30px;width: 1900px; ">
								<thead>
									<tr align="center">
										<th style="width:20px;"><input type="checkbox"  id="checkAll"/></th>
										<th>操作</th>
										<th>操作</th>
										<th>打印</th>
										<th>单据号</th>
										<th>申请人</th>
										<th>申请日期</th>
										<th>归属公司</th>
										<th>归属部门</th>
										<th>资产类型</th>
										<th>办公地点</th>
										<th>会计年度</th>
										<th>原值合计</th>
										<th>净值合计</th>
										<th>维护人</th>
										<th>维护时间</th>
										<th>审批状态</th>
										<th>是否为草稿</th>
										<th>是否打印</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="" var="assetAllocation" varStatus="status">
                                <tr align="center">
                               		<td><input type="checkbox"  name="radio" id="radio" /></td>
									<td><a href="javascript:void(0)">修改</a></td>
									<td><a onclick="opView('${assetAllocation.id}','数据查看');" style="cursor:pointer;">查看</a></td>
									<td><a href="javascript:void(0)">打印</a></td>
                                    <td>${assetAllocation.documentNumber}</td>
                                    <td>${assetAllocation.applyPeople}</td>
                                    <td><fmt:formatDate value="${assetAllocation.applicationDate}"  pattern="yyyy-MM-dd"/></td>
                                    <td>${assetAllocation.originalCompany}</td>
                                    <td>${assetAllocation.postAllocationStoragePlace}</td>
                                    <td>${assetAllocation.allocationMethod}</td>
                                    <td>${assetAllocation.assetType}</td>
                                    <td>${assetAllocation.postAllocationPeople}</td>
                                    <td><fmt:formatDate value="${assetAllocation.updateDate}"  pattern="yyyy-MM-dd"/></td>
                                    <td>
	                                    <c:if test="${assetAllocation.approveState=='0'}">
	                                    	未审批
	                                    </c:if>
	                                    <c:if test="${assetAllocation.approveState=='1'}">
	                                    	审批中
	                                    </c:if>
	                                    <c:if test="${assetAllocation.approveState=='3'}">
	                                    	审批完成
	                                    </c:if>
                                    </td>
                                    
                                    <td>
                                    	<c:if test="${assetAllocation.isDraft=='0'}">
	                                    	是
	                                    </c:if>
	                                    <c:if test="${assetAllocation.isDraft=='1'}">
	                                    	否
	                                    </c:if>
                                    </td>
                                    <td>否</td>
                                </tr>
                            </c:forEach>																	
								</tbody>
							</table>
						<tag:paginate offset="${assetAllocationPager.offset}" total="${assetAllocationPager.total}" steps="${assetAllocationPager.pageSize}" uri="${assetAllocationPager.uri}" formId="formId" />
				</div>
			</center>
		</div>
		<script src="${staticURL}/js/jquery-1.11.3.min.js"></script>
		<script src="${staticURL}/js/jquery.tableCheckbox.js"></script>
		<script src="${staticURL}/js/browser.js"></script>
		<script src="${staticURL}/Scripts/JSBase.js" type="text/javascript"></script>
		<script type="text/javascript" src="${staticURL}/js/ajaxfileupload.js"></script>
		<script src="${staticURL}/Scripts/DatePicker/WdatePicker.js" type="text/javascript"></script>
		<script src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="${staticURL}/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
		<script src="${staticURL}/Scripts/Purchase/DemandOrder/DemandOrder.js" type="text/javascript"></script>
		
		<script type="text/javascript">
			
			//全选
			$("#checkAll").click(function(){
				$("input[name='radio']").prop("checked", $(this).prop("checked"));
			});
			
			//查询
			$("#opInquiry").click(function() {
				window.location.href = '${dynamicURL}/assetAllocation/searchAssetAllocations?' + $('#formId').serialize();
			});
			//申请
			function opApplication(id,title) {
				var lurl = "${dynamicURL}/assetAllocation/shenqing?id="+id;
	            var dlg = new $.dialog({ id: 'add', title: title, cover: true, page:lurl, rang: true, width: 1000, height: 500, btnBar: false, resize: true, maxBtn: true }).ShowDialog(); 
	        }
			
			//查看
			function opView(id,title) {
				var lurl = "${dynamicURL}/assetAllocation/assetAllocationView?id="+id;
	            var dlg = new $.dialog({ id: 'add', title: title, cover: true, page:lurl, rang: true, width: 1000, height: 500, btnBar: false, resize: true, maxBtn: true }).ShowDialog();
	        }
		</script>
				
	</body>

</html>