<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>

    <link rel="shortcut icon" href="data:image/x-icon;," type="image/x-icon" />
    <link rel="stylesheet" type="text/css" href="${staticURL}/Scripts/ExtJs/resources/css/ext-all.css" />
    <link rel="stylesheet" type="text/css" href="${staticURL}/Styles/bgstretcher.css" />
    <link rel="stylesheet" type="text/css" href="${staticURL}/Styles/main.css" />
    <title>市场资源管理系统-用户登录</title>
</head>
<body>

<div style="width: 1200px; height: 66px; margin: 0 auto;">
    <img src="${staticURL}/Images/top.jpg" alt="" width="588" height="66"/>
</div>
<div class="demoo">
    <br/>
        <div class="demofont" id="tid">
            <table width="320" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td colspan="3" height="10"></td>
                </tr>
                <tr>
                    <td height="57" colspan="3" align="center" valign="middle">
                        <img src="${staticURL}/Images/t2.png" alt="" width="179" height="31"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="3" height="15"></td>
                </tr>
                <form name="loginForm">
                    <tr>
                        <td height="30" align="right">账号：</td>
                        <td align="left">
                            <input name="username" id="username" type="text" class="Ninput" value=""/>
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="3" height="15"></td>
                    </tr>
                    <tr>
                        <td height="30" align="right">密码：</td>
                        <td align="left">
                            <input name="password" id="password" type="password" maxlength="20" class="Pinput" value=""/></td>
                        <td>&nbsp;</td>
                    </tr>
                </form>
                <tr>
                    <td colspan="3" height="30">
                        <span id="errorMessage" style="display: none;color:#FF359A">账户或密码错误！请重新输入</span>
                    </td>
                </tr>
                <tr>
                    <td colspan="3" align="center">
                        <input id="loginbutton" value="登录" onclick="submitFunction();" type="image" name="ImageButton1" src="${staticURL}/Images/Login/log_03.jpg" style="border-style: None;"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="3" height="15"></td>
                </tr>
                <tr>
                    <td colspan="3" style="font-size: 12px;" align="left">
                        &nbsp;&nbsp;<font color="#FFFF66" style="font-weight: bold">温馨提示：</font><br/>
                        &nbsp;&nbsp;&nbsp;&nbsp;请使用公司工号登录系统，登录系统默认密码“123”<br/>
                        &nbsp;&nbsp;&nbsp;&nbsp;登录后自动关闭问题，在浏览器将本网址加入可信站点<br/>
                        &nbsp;&nbsp;&nbsp;&nbsp;系统运维电话：0512-63828888 5141/7320
                    </td>
                </tr>
                <tr>
                    <td colspan="3" height="15"></td>
                </tr>
            </table>
        </div>
</div>
<div style="width: 1200px; height: 30px; line-height: 30px; margin: 0 auto; text-align: center; padding-top: 10px;">
    MRMS&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="http://www.miitbeian.gov.cn/publish/query/indexFirst.action" target="_blank">备案号:苏ICP备13004515号</a>
    &nbsp;&nbsp;&nbsp;&nbsp;青岛远航高新科技有限公司版权所有
    <a href="javascript:legalnotice()">法律声明</a> | <a href="javascript:privacypolicy()">隐私保护</a>
</div>
<script type="text/javascript" src="${staticURL}/Scripts/ExtJs/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${staticURL}/Scripts/ExtJs/ext-all.js"></script>
<script type="text/javascript" src="${staticURL}/Scripts/JSBase.js"></script>
<script type="text/javascript" src="${staticURL}/Scripts/JQuery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${staticURL}/Scripts/bgstretcher.js"></script>
<script type="text/javascript" src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="${staticURL}/Scripts/JSON-js-master/json2.js"></script>

<script type="text/javascript">

    (function () {
        if (navigator.userAgent.indexOf("MSIE 8.0") > 0) {
            $("#tid").toggleClass("demofont2");
        }
        document.onkeydown=function(event) {
            var e = event ? event :(window.event ? window.event : null);
            if(e.keyCode==13){
                submitFunction();
            }
        }
        $('.demoo').bgStretcher({
            images: ['${staticURL}/Images/07.jpg',
                '${staticURL}/Images/06.jpg',
                '${staticURL}/Images/05.jpg'],
            imageWidth: 1200,
            imageHeight: 506,
            slideDirection: 'N',
            slideShowSpeed: 2000,
            transitionEffect: 'fade',
            sequenceMode: 'normal'
        });

    })();

    //关于系统
    function legalnotice() {
        Ext.MessageBox.show({
            title: "法律声明",
            msg: "<div><span><textarea style=\"WIDTH: 560px; HEIGHT: 300px; FONT-SIZE:12px;\" readOnly scroll=\"yes\">"
            + "版权所有（C）YHGX。保留所有权利。\n"
            + "警告：本计算机程序受著作权法保护，未经书面授权许可不得擅自复制或散布本程序的部分或全部。</textarea><br/><br/>版本号：YHGX-NET 1.00<br/> "
            + "<a style=\"FONT-FAMILY: verdana\" href=\"http://www.yuanh.com.cn\" target=_blank>Powered by yhgx, wdhy Co. Ltd.</a></span></div>",
            buttons: {
                "ok": "确定"
            },
            fn: function (e) {
            },
            width: 600,
            animEl: "imgabout",
            //multiline:true,
            closable: true
        });
    }

    function privacypolicy() {
        Ext.MessageBox.show({
            title: "隐私保护",
            msg: "<div><span><textarea style=\"WIDTH: 560px; HEIGHT: 300px; FONT-SIZE:12px;\" readOnly scroll=\"yes\">"
            + "版权所有（C）YHGX。保留所有权利。\n"
            + "警告：本计算机程序受著作权法保护，未经书面授权许可不得擅自复制或散布本程序的部分或全部。</textarea><br/><br/>版本号：YHGX-NET 1.00<br/> "
            + "<a style=\"FONT-FAMILY: verdana\" href=\"http://www.yuanh.com.cn\" target=_blank>Powered by yhgx, wdhy Co. Ltd.</a></span></div>",
            buttons: {
                "ok": "确定"
            },
            fn: function (e) {
            },
            width: 600,
            animEl: "imgabout",
            //multiline:true,
            closable: true
        });
    }

    function clear() {
        document.getElementById("txtUserID").value = "";
        document.getElementById("txtPwd").value = "";
        document.getElementById("txtVCode").value = "";
    }

    function openDefaultAlert(day) {
        alert('您的密码将在' + day + '天后过期，登录后，请及时修改密码！');
        openDefault();
    }

    function queryreload() {
        document.getElementById("txtPwd").value = "";
        document.getElementById("txtVCode").value = "";
    }

    function submitFunction() {
        $.ajax({
            type: "POST",
            url: "${dynamicURL}/login",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({
                userCode: $("#username").val(),
                pwd: $("#password").val()
            }),
            success: function(data) {
                if (data.result == 'true') {
                    window.location.href = data.targetUrl;
                }else{
                    $("#password").val("");
                    document.getElementById("errorMessage").style.display="block";
                }
            }
        });
    };

</script>

</body>

</html>
