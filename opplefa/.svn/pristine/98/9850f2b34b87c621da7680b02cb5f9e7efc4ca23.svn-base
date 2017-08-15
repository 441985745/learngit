document.domain='opple.com';
function downLoadFile(fileUId){    
   	var url="${dynamicURL}/fileUpload/downloadModel?fineUId="+fileUId;
	window.location.href=url;
}
function RetainedDecimalPlaces (num, del, o) {//值：num 小数位：del o为true返回值为正数,否则为负数
    try {
        num += "";
        num = parseFloat(num).toFixed(del); //保留小数并四舍五入
        var str = "";
        if (!o) {
            if (num.substring(0, 1) == "-") str = "-";
        }
        //清除字符串中的非数字 非.字符
        num = num.replace(/[^0-9|\.]/g, "");
        //清除字符串开头的0
        if (/^0+/) num = num.replace(/^0+/, "");
        //为整数字符串在末尾添加.0000
        if (!/\./.test(num)) num += ".0000";
        //字符以.开头时，在开头添加0
        if (/^\./.test(num)) num = "0" + num; num += "0000"; //在字符串末尾补零
        if (del == 2) num = num.match(/\d+\.\d{2}/)[0];
        if (del == 4) num = num.match(/\d+\.\d{4}/)[0];
        //千位符
        while (/\d{4}(\.|,)/.test(num)) //符合条件则进行替换
            num = num.replace(/(\d)(\d{3}(\.|,))/, "$1,$2"); //每隔3位添加一个
        return str + num;
    } catch (e) {
        alert(e);
    }
};

