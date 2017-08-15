document.domain='opple.com';
	var dg = frameElement.lhgDG;//定义dg 关闭需要
	
	$("#checkBar").click(function(){
		var cdocument = $("#cdocument").val();
		 $.ajax({
	           url: _dynamicURL+"/payment/api/checkBar",
	           type: "POST",
	          // contentType: "application/json",
	           data:{cdocument : cdocument},
	           success: function (data) {
	        	   if (data.success){
	        		   window.location.href=data.successMessage;
	        	   } else {
						alert(data.errorMessages.join());
					}

	           }
	       }); 
	});
	$("#btnAgree").click(function(){
		if($("#prepaymentDocumentNumber").val()==""){
        	alert("请先生成对应凭证！");
        	return;
        }
		if($("#receiptVoucherNumber").val()==""){
        	alert("请先生成对应凭证！");
        	return;
        }
		if($("#paymentVoucherNumber").val()==""){
        	alert("请先生成对应凭证！");
        	return;
        }
		var cdocument = $("#cdocument").val();
    	showAgreeDialog(cdocument,"77071");
    });
    $('#btnDeny').click(function () {
    	var cdocument = $("#cdocument").val();
        showDenyDialog(cdocument,"77071");
    });
    $('#btnDrop').click(function () {
    	var paymentType = $("#paymentType").val();
    	var capprovalstate = $("#capprovalstate").val();
    	if ((paymentType=="预付款"||paymentType=="验收款")&&capprovalstate=="已完成") {
    		alert("单据已经入账，无法弃审！");
    		return;
    	}
        showDropDialog();
    });
	
	function showAgreeDialog(document, typeId) {
        var url = _dynamicURL+"/workflow/showAgreeDialog?parentWindowId=examineDialog&document=" + document + "&typeId=" + typeId;

        var dlg = new dg.curWin.$.dialog({ id: 'agreeDialog', parent: dg, title: '邮件短信提醒', cover: true, page: url, width: 400, height: 210, btnBar: false, resize: false, maxBtn: false });
        dlg.ShowDialog();

        return dlg;
    }
    function showDenyDialog(document, typeId) {
        var url = _dynamicURL+"/workflow/showBarDenyDialog?parentWindowId=examineDialog&document=" + document + "&typeId=" + typeId;

        var dlg = new dg.curWin.$.dialog({ id: 'barDenyDialog', parent: dg, title: '邮件短信提醒', cover: true, page: url, width: 400, height: 210, btnBar: false, resize: false, maxBtn: false });
        dlg.ShowDialog();

        return dlg;
    }
    function showDropDialog() {
        var url = _dynamicURL+"/workflow/showDropDialog?parentWindowId=examineDialog";

        var dlg = new dg.curWin.$.dialog({ id: 'dropDialog', parent: dg, title: '邮件短信提醒', cover: true, page: url, width: 400, height: 210, btnBar: false, resize: false, maxBtn: false });
        dlg.ShowDialog();

        return dlg;
    }
    function commitAgree(isMail, isPhoneMessage, idea, activityId) {
        $('#btnAgree').val('提交...');
        
        var paymentOrderModel={};
        paymentOrderModel.isManualPrepaymentDocument = $("#isManualPrepaymentDocument").val();
        paymentOrderModel.prepaymentDocumentNumber = $("#prepaymentDocumentNumber").val();
    	paymentOrderModel.cdocument = $("#cdocument").val(); 
    	paymentOrderModel.idea = idea;
    	paymentOrderModel.isMail = isMail;
    	paymentOrderModel.isPhoneMessage = isPhoneMessage;
    	paymentOrderModel.activityId = activityId;
    	
    	$.ajax({
            type: "POST",
            dataType: "json",
            async: false,
            contentType: "application/json; charset=utf-8",
            url: _dynamicURL+"/payment/api/agreePayment",
            data: JSON.stringify(paymentOrderModel),
            success: function(data) {
            	if (data.success) {
                    alert("审批成功");
                    dg.curWin.location.reload();
                } else {
                    $('#btnAgree').val('同意');
                    alert(data.errorMessages.join());
                }
            }
        });
    }
    function commitDeny(isMail, isPhoneMessage, idea,isPortrait, activityId) {
        $('#btnDeny').val('提交...');
        
        var paymentOrderModel={};
    	paymentOrderModel.cdocument = $("#cdocument").val(); 
    	paymentOrderModel.idea = idea;
    	paymentOrderModel.isMail = isMail;
    	paymentOrderModel.isPhoneMessage = isPhoneMessage;
    	paymentOrderModel.isPortrait = isPortrait;
    	paymentOrderModel.activityId = activityId;
    	$.ajax({
            type: "POST",
            dataType: "json",
            async: false,
            contentType: "application/json; charset=utf-8",
            url: _dynamicURL+"/payment/api/denyPayment",
            data: JSON.stringify(paymentOrderModel),
            success: function(data) {
            	if (data.errorMessages.length > 0) {
            		$('#btnDeny').val('退回');
	                alert(data.errorMessages);
	                return;
	            }else{
	            	alert(data.successMessage);
	            	 //关闭窗口
		            dg.curWin.refresh();
		            dg.cancel();
		            return;
	            }
            }
        });
    }
    function commitDrop(isMail, isPhoneMessage, idea) {

        $('#btnDrop').val('提交...');

        var paymentOrderModel={};
    	paymentOrderModel.cdocument = $("#cdocument").val(); 
    	paymentOrderModel.idea = idea;
    	paymentOrderModel.isMail = isMail;
    	paymentOrderModel.isPhoneMessage = isPhoneMessage;
    	$.ajax({
            type: "POST",
            dataType: "json",
            async: false,
            contentType: "application/json; charset=utf-8",
            url: _dynamicURL+"/payment/api/dropPayment",
            data: JSON.stringify(paymentOrderModel),
            success: function(data) {
            	if (data.success) {
                    alert("审批成功");
                    dg.curWin.location.reload();
                } else {
                    $('#btnDrop').val('弃审');
                    alert(data.errorMessages.join());
                }
            }
        });
    }