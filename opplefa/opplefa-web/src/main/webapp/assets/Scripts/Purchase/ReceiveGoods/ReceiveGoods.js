document.domain = 'opple.com';
//新增申请单 
 function opAdddlg(param, title) {
	
	var lurl = "/receiveGoods/addReceiveGoods?goods="+param;debugger
	//dg.curWin.$.dialog
	var dlg = new dg.curWin.$.dialog({id: 'add', title: title, cover: true, page: lurl, rang: true, width: 900, height: 600, btnBar: false, resize: true, maxBtn: true }).ShowDialog();
/*	debugger;for(var a=1;a<param.length;a++){
		$("#receiveGoodsAdd").append("<tr><td>"+"ee"+"</td></tr>")
	}*/
}
//新增申请单  
function opAddRowdlg(id, supplier,assetType,title) {
	var lurl = "/receiveGoods/searchPurchaseOrderDetailList?purchaseOrderId="+id+"&supplierName="+supplier+"&assetType="+assetType;
	var dlgRow = new $.dialog({ id: 'addRow', title: title, cover: true, page: lurl, rang: true, width: 900, height: 500, btnBar: false, resize: true, maxBtn: true,stack :true }).ShowDialog();
	
}

