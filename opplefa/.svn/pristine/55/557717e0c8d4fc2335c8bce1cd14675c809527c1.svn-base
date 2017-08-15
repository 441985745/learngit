document.domain = 'opple.com';
	var dg = frameElement.lhgDG;//定义dg 关闭需要
	
	var paymentType = $("#paymentType").val();//付款类型
	
	var ratioMoney;
	var realMoney;
	//提交表单
	function saveOrder(strDraft,isMail, isPhoneMessage, idea,isPortrait){
		var objDetail;
		var objDetailDetail;
		if(paymentType=="验收款"){
			objDetail= {
					paymentOrderId:"",
					purchaseDetailId:"",
					paymentType:"",
					payCount:"",
					payPrice:"",
					payPriceLocal:"",
					purchaseOrderId:"",
					nextPayment:"",
					finalPrice:"",
					mainAssetCode:"",
					advancePrice:"",
					payablePrice:"",
					assetsName:"",
					costCenter:"",
					costCenterCode:""
				};
			objDetailDetail= {
					paymentOrderId:"",
					cpassDocuments:"",
					pstngDate:"",
					compCode:"",
					iyear:"",
					imonth:"",
					caccountCode:"",
					csubjectCode:"",
					csubjectName:"",
					cspCode:"",
					iamount:"",
					cdepCode:"",
					payPrice:"",
					cdocumentsNo:""
				};
		}
		else{
			objDetail= {
					paymentOrderId:"",
					purchaseDetailId:"",
					paymentType:"",
					payCount:"",
					payPrice:"",
					payPriceLocal:"",
					purchaseOrderId:"",
					nextPayment:"",
					mainAssetCode:"",
					assetsName:"",
					costCenter:"",
					costCenterCode:""
				};
		}
		
		var objHeader = $("#paymentOrderFormHeader").serializeObject();
		objHeader.orderMoney = $("#sumpayPrice").val();
		objHeader.draft = strDraft;
		objHeader.idea = idea;
		objHeader.isMail = isMail;
		objHeader.isPhoneMessage = isPhoneMessage;
		objHeader.isPortrait = isPortrait;
		var paymentOrderDetail = constructObjectFromTable(objDetail, $("#tbody_detail .bodyTr"));
		var PaymentOrderWrapper = {};
		PaymentOrderWrapper.paymentOrder = objHeader;
		PaymentOrderWrapper.paymentOrderDetails = paymentOrderDetail;
		if(paymentType=="验收款"){
			var paymentPaymentMappingModels = constructObjectFromTable(objDetailDetail, $("#tbody_detail_detail .bodyTr"));
			PaymentOrderWrapper.paymentPaymentMappingModels = paymentPaymentMappingModels;
		}
		//console.log(PaymentOrderWrapper);
		$.ajax({
	        type: "POST",
	        dataType: "json",
	        contentType: "application/json; charset=utf-8",
	        url: _dynamicURL+"/payment/api/savePayment",
	        data: JSON.stringify(PaymentOrderWrapper),
	        success: function(data) {
	        	if (data.errorMessages.length > 0) {
	        		var str = data.errorMessages;
					alert(str);
					var reg = new RegExp("^.*添加成功.*$");
					if (reg.test(str)) {
						dg.curWin.location = dg.curWin.location;
					}
	                return;
	            }
	            alert(data.successMessage);
	            //关闭窗口
	            dg.curWin.refresh();
	            dg.cancel();
	            return;
	        }
	    });
	}
	function checkAll(){
		var mess="";
		var flag = true;
/*		if($("#invoice").val()=="是"){
			if($("#invoiceNumber").val().length==0){
				mess += "发票号不能为空;";
				flag = false;
			}
		}*/
		if($("#invoice").val()==""){
			alert("请选择是否有专票！");
			return;
		}
		if($("#invoice").val()=="Y"&&$("#invoiceNumber").val()==""){
			alert("请输入发票号码！");
			return;
		}
		if($("#applyFor").val()==""){
			alert("请输入申请事由！");
			return;
		}
		if($("#scanLocation").val()==""){
			alert("请选择扫描地！");
			return;
		}
		if($("#isLastTime").val()==""){
			alert("请选择是否最后一次付款！");
			return;
		}
		$("input[name='payCount']").each(function() {
			var payCount = $(this).val(); //本次申请数量
			var applyCount = $(this).parent().parent().find("#applyCount").val(); //订单数量
			var paidCount = $(this).parent().parent().find("#paidCount").val(); //已付数量
			var payPrice = $(this).parent().parent().find("#payPrice").val(); //本次付款数量
			if (payPrice == ""||isNaN(payPrice)) {
				mess += "本次付款金额不能为空;";
				flag = false;
				return true;
			}
			if (payCount == ""||isNaN(payCount)) {
				mess += "本次付款数量必须是整数;";
				flag = false;
				return true;
			}
			if(payCount-(applyCount-paidCount)>0){
				mess += "本次付款数量超过剩余可付数量;";
				flag = false;
				return true;
			}
			if($("#nper").val()!=1){
				var lastCount = $(this).parent().parent().find("#lastCount").val(); //上期付款数量
				if(payCount-lastCount>0){
					mess += "本次付款数量超过上期付款数量;";
					flag = false;
					return true;
				}
			}
			if(paymentType=="验收款"){
				var checkCount = $(this).parent().parent().find("#checkCount").val(); //已验收数量
				var lastCount = $(this).parent().parent().find("#lastCount").val(); //上期付款数量
				if(payCount-(checkCount-paidCount)>0){
					mess += "本次付款数量超过(已验收数量-已付数量);";
					flag = false;
					return true;
				}
				if(payCount-(lastCount-paidCount)>0&&$("#nper").val()!=1){
					mess += "本次付款数量超过(上期付款数量-已付数量);";
					flag = false;
					return true;
				}
			}
		});
		$("input[id='nextPayment']").each(function() {
			if ($(this).val() == "") {
				mess += "时间格式不正确;";
				flag = false;
				return false;
			}
		});
		var sumadvancePrice = $("#sumadvancePrice").val();
		var payPriceDetail = 0;
		var flag1=false;
		if(paymentType=="验收款"){
			$("#tbody_detail_detail").find('input[name^=gvdata]').each(function(){
				if($(this).attr('checked')){
					payPriceDetail += $(this).parent().parent().find("#payAmount").val()-0;
					if(payPriceDetail == ""||isNaN(payPriceDetail)){
						/*mess += "本次冲减金额格式不正确;";
						flag = false*/;
						flag1=true;
						return ;
					}
				}
				
			});
			if(flag1){
				mess += "本次冲减金额格式不正确;";
			}
			if(parseInt(sumadvancePrice)!=parseInt(payPriceDetail)){
				mess += "本次冲减金额与已付预付金额不相等;";
				flag = false;
			}
/*			alert(payPriceDetail);*/
		}
		
		if(mess.length>0){
			alert(mess);
		}
		return flag;
	}
	function commitAgree(isMail, isPhoneMessage, idea,isPortrait) {
		$("#btnSave").val("提交...");
		$("#btnSave").attr("disabled", "disabled");//添加disabled属性
		$("#btnSaveDraft").attr("disabled", "disabled");
		$("#btnCancel").attr("disabled", "disabled");
		saveOrder("N",isMail, isPhoneMessage, idea,isPortrait);
		$("#btnSave").val("提交");
		$("#btnSave").removeAttr("disabled"); //移除disabled属性
		$("#btnSaveDraft").removeAttr("disabled");
		$("#btnCancel").removeAttr("disabled");
    }
	$("#btnSave").click(function() {
		if(ratioMoney==realMoney){
			if(checkAll()){
				showAgreeDialog();
			}
		}
		else{
			if (window.confirm("付款金额与原比例金额不一致,确定付款?")) {
				if(checkAll()){
					showAgreeDialog();
				}
			}
		}
	});
	
	$("#btnSaveDraft").click(function() {//保存草稿
		if(ratioMoney==realMoney){
			if(checkAll()){
				$("#btnSave").attr("disabled", "disabled");//添加disabled属性
				$("#btnSaveDraft").attr("disabled", "disabled");
				$("#btnSaveDraft").val("保存草稿...");
				$("#btnCancel").attr("disabled", "disabled");
				saveOrder("Y","","","");
				$("#btnSaveDraft").val("保存草稿");
				$("#btnSave").removeAttr("disabled"); //移除disabled属性
				$("#btnSaveDraft").removeAttr("disabled");
				$("#btnCancel").removeAttr("disabled");
			}
		}
			else{
				if (window.confirm("付款金额与原比例金额不一致,确定付款?")) {
					if(checkAll()){
						$("#btnSave").attr("disabled", "disabled");//添加disabled属性
						$("#btnSaveDraft").attr("disabled", "disabled");
						$("#btnSaveDraft").val("保存草稿...");
						$("#btnCancel").attr("disabled", "disabled");
						saveOrder("Y","","","");
						$("#btnSaveDraft").val("保存草稿");
						$("#btnSave").removeAttr("disabled"); //移除disabled属性
						$("#btnSaveDraft").removeAttr("disabled");
						$("#btnCancel").removeAttr("disabled");
				}
			}
		}
		
	});
	function showAgreeDialog() {
        var url = _dynamicURL+"/workflow/showSubmitDialog?parentWindowId=add" ;

        var dlg = new dg.curWin.$.dialog({ id: 'submitDialog', parent: dg, title: '邮件短信提醒', cover: true, page: url, width: 400, height: 210, btnBar: false, resize: false, maxBtn: false });
        dlg.ShowDialog();

        return dlg;
    }
	
	//表单封装json
	function constructObjectFromTable(object, $object) {
		var javaObjArray = [];
		
		$object.each(function(i) {
			var $ck = $(this).find('input[name^=gvdata]');
			if($ck.length != 0){
				if(!$ck.attr('checked')){
					return true;
				}
			}
		     var javaObj = {};
		     
		     for (var p in object) {
		    	 var $val = $(this).find('input[name="'+p+'"]');
		    	 if ($val.length === 0) {
		    		 javaObj[p] = $(this).find('select[name="'+p+'"]').val();
		    	 } else {
		    		 javaObj[p] = $val.val();
		    	 }
		     }
		     
		     javaObjArray.push(javaObj);
		});
		
		return javaObjArray;
	}

	function sumAll(){
		var exchangeRate = $("#exchangeRate").val();//汇率
		var paymentRatio = $("#paymentRatio").val();//付款比例
		var lastPaymentRatio = $("#lastPaymentRatio").val();//预付比例总和
		
		
		//数量*单价
		$("input[id='payCount']").each(function(){
			var count = $(this).val();//本次申请数量
			var price = $(this).parent().parent().find("#purchaseUnitPrice").val();//采购单价
			
			$(this).parent().parent().find("#payablePrice").val(count*price-0);//应付金额
			if(paymentType=="验收款"){
				$(this).parent().parent().find("#advancePrice").val((count*price-0)*lastPaymentRatio/100);//已付预付款金额
				$(this).parent().parent().find("#finalPrice").val(((count*price-0)-((count*price-0)*lastPaymentRatio/100)-((count*price-0)*paymentRatio/100)).toFixed(2));//未付尾款金额    (应付金额-已付预付款金额-本次付款金额)
			}
			$(this).parent().parent().find("#payPrice").val((count*price-0)*paymentRatio/100);//本次付款金额
			$(this).parent().parent().find("#payPriceLocal").val((((count*price-0)*paymentRatio/100)*exchangeRate-0).toFixed(2));//本次付款金额(本币)
		});
		//合计订单总价
		var sumpurchasePrice=0;
		$("input[id='purchasePrice']").each(function(){
			sumpurchasePrice += $(this).val()-0;
		});
		$("#sumpurchasePrice").val(sumpurchasePrice);
		
		//合计应付金额
		var sumpayablePrice=0;
		$("input[id='payablePrice']").each(function(){
			sumpayablePrice += $(this).val()-0;
		});
		$("#sumpayablePrice").val(sumpayablePrice);
		
		//合计已付预付金额
		var sumadvancePrice=0;
		$("input[id='advancePrice']").each(function(){
			sumadvancePrice += $(this).val()-0;
		});
		$("#sumadvancePrice").val(sumadvancePrice);
		
		//未付尾款金额
		var sumfinalPrice=0;
		$("input[id='finalPrice']").each(function(){
			sumfinalPrice += $(this).val()-0;
		});
		$("#sumfinalPrice").val(sumfinalPrice.toFixed(2));
		
		//合计已付款金额
		var sumpaidMoney=0;
		$("input[id='paidMoney']").each(function(){
			sumpaidMoney += $(this).val()-0;
		});
		$("#sumpaidMoney").val(sumpaidMoney);
		
		//本次付款金额 
		var sumpayPrice=0;
		$("input[id='payPrice']").each(function(){
			sumpayPrice += $(this).val()-0;
		});
		$("#sumpayPrice").val(sumpayPrice);
		
		//本次付款金额本币
		var sumpayPriceLocal=0;
		$("input[id='payPriceLocal']").each(function(){
			sumpayPriceLocal += $(this).val()-0;
		});
		$("#sumpayPriceLocal").val(sumpayPriceLocal);
		ratioMoney = sumpayPrice;
		realMoney = sumpayPrice;
	}
	function updateMoney(){
		var exchangeRate = $("#exchangeRate").val();//汇率
		
		$("input[id='payPrice']").each(function(){
			var money = $(this).val();//本次付款金额
			var advancePrice = $(this).parent().parent().find("#advancePrice").val();//已付预付款金额
			var payablePrice = $(this).parent().parent().find("#payablePrice").val();//应付金额
			$(this).parent().parent().find("#payPriceLocal").val((money*exchangeRate-0).toFixed(2));//本次付款金额(本币)
			$(this).parent().parent().find("#finalPrice").val((payablePrice-advancePrice-money).toFixed(2));//未付尾款金额    (应付金额-已付预付款金额-本次付款金额)
		});
		//未付尾款金额
		var sumfinalPrice=0;
		$("input[id='finalPrice']").each(function(){
			sumfinalPrice += $(this).val()-0;
		});
		$("#sumfinalPrice").val(sumfinalPrice.toFixed(2));
		//本次付款金额 
		var sumpayPrice=0;
		$("input[id='payPrice']").each(function(){
			sumpayPrice += $(this).val()-0;
		});
		$("#sumpayPrice").val(sumpayPrice);
		
		//本次付款金额本币
		var sumpayPriceLocal=0;
		$("input[id='payPriceLocal']").each(function(){
			sumpayPriceLocal += $(this).val()-0;
		});
		$("#sumpayPriceLocal").val(sumpayPriceLocal.toFixed(2));
		realMoney = sumpayPrice;
	}
	//本次付款数量
	$("#table_detail").delegate("input[id='payCount']","keyup",function(){
		sumAll();
	});
	//本次付款金额
	$("#table_detail").delegate("input[id='payPrice']","keyup",function(){
		var payPrice = $(this).val();
		var payCount = $(this).parent().parent().find("#payCount").val();//本次付款数量
		var advancePrice = $(this).parent().parent().find("#advancePrice").val();//已付预付款金额
		var payablePrice = $(this).parent().parent().find("#payablePrice").val();//应付金额
		if (payCount==""||payCount==null){
			alert("请先输入付款数量！");
			$(this).val("");
			return;
		}
		if ((payPrice-(payablePrice-advancePrice))>0){
			alert("本次付款金额超出,请重新输入！");
			$(this).val("");
			return;
		}
		updateMoney();
	});
//	$("#chkBolDel").live("click",function(){
//		checkCount();
//	});
//	$("#payCount").live("keyup",function(){
//		checkCount();
//	});
	
	
	// 根据币种查询汇率
	function selectCurrency(){
		var currency = $('#currencyCode').val();
		$.ajax({
			type : "POST",
			nsync : true,
			url : _dynamicURL+"/userInfo/api/currencyRate",
			data : "currency="+currency,
			success : function(userInfo){
				//console.log(userInfo);
				$("#exchangeRate").val(userInfo.iexchangerate);
			}
		});
	}
	
	(function(){
		selectCurrency();
		/*if($("#invoice").val()=="是"){
			$("#invoiceNumber").removeAttr("readonly");
		}*/
	})();
	
	//保留两位小数     
    //功能：将浮点数四舍五入，取小数点后2位    
    function toDecimal(x) {    
        var f = parseFloat(x);    
        if (isNaN(f)) {    
            return;    
        }    
        f = Math.round(x*100)/100;    
        return f;    
    }    
    //制保留2位小数，如：2，会在2后面补上00.即2.00    
    function toDecimal2(x) {    
        var f = parseFloat(x);    
        if (isNaN(f)) {    
            return false;    
        }    
        var f = Math.round(x*100)/100;    
        var s = f.toString();    
        var rs = s.indexOf('.');    
        if (rs < 0) {    
            rs = s.length;    
            s += '.';    
        }    
        while (s.length <= rs + 2) {    
            s += '0';    
        }    
        return s;    
    }   
    