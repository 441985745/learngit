document.domain='opple.com';
// 根据资产类型和公司查询 车间类型（注意：以为目前资产类型的code没有值，先用资产类型）；
/*$('#txtAssetType1').change(function(){
	$("#txtWorkShopType option").remove();
	var companyCode =  $('#txtCompanyCode').val();
	var departType = $(this).children('option:selected').val();
	
	var depcoms = [companyCode, departType];
	$.ajax({
		type : "POST",
		async : false,
		url : "/userInfo/api/assetWork",
		data : "depcoms=" + depcoms,
		success : function(worklist) {
			//$("#txtWorkShopType").append("<option  value='' selected='selected'>--请选择--</option>");
			if(worklist.length>0){
		    	$("#txtWorkShopType").css("background-color","");
			} else{
				$("#txtWorkShopType").css("background-color","#CCCCCC");
			}
			for ( var i = 0; i < worklist.length; i++) {
				$("#txtWorkShopType").append("<option value='" + worklist[i].departType + "'>"+ worklist[i].departType + "</option>");
			};
		
		}
		
	});
});*/

// 根据办公地点选择查询采购员
/*function selectAddress(){
	$("#txtBuyer option").remove();
	
	var address = $('#txtOfficeLocation').val();
	var companyCode =  $('#txtCompanyCode').val();
	
	var addresscom = [companyCode, address];
	$.ajax({
		type : "POST",
		async:false,
		url :  _dynamicURL+"/userInfo/api/addressPurchase",
		data : "addresscom=" + addresscom,
		success : function(addresslist){
			//$("#txtBuyer").append("<option  value='' selected='selected'>--请选择--</option>");
			for ( var i = 0; i < addresslist.length; i++) {
				$("#txtBuyer").append("<option value='" + addresslist[i].purchaseEngineerCode + "'>"+ addresslist[i].purchaseEngineer + "</option>");
			};
		}
	});
};

// 根据币种查询汇率
function selectCurrency(){
	var currency = $('#currencyCode').val();
	$.ajax({
		type : "POST",
		nsync : false,
		url :  _dynamicURL+"/userInfo/api/currencyRate",
		data : "currency="+currency,
		success : function(userInfo){
			//console.log(userInfo);
			$("#txtExchangeRate").val(userInfo.iexchangerate);
		}
	});
	sumAll();
}*/
//js 判断 必须先选预算所属部门
function ysssbmCom(){
	
	if (!($('#txtYsssbm').val())) {
	alert("请先选择预算所属部门");
}
}

//js 判断 区域 不能先选
function selectPlat(){
	if (!($('#txtPlatform').val())) {
		alert("请先选择平台");
	}
}

//js 判断 车间类型 不能先选
function selectAssetWork(){
	if ($('#txtAssetType1').val() == null) {
		alert("请先资产类型");
	}
}

//js 判断 成本中心 不能先选
function selectCom(){
	if (!($('#txtCompanyCode').val())) {
		alert("请先选择所属公司");
	}
}



//js 判段 采购员 不能先选
function selectPurchase(){
	if (!($('#txtOfficeLocation').val())) {
		alert("请先选择办公地点");
	}
}


