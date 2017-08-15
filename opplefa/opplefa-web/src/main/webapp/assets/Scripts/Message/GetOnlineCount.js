document.domain = 'opple.com';
var currCount = 0;
var oldCount = 0;
var SessionId = null;
$(document).ready(function () {
    SessionId = $("#txt_session").val();
    var WorkerId = $("#txt_worker_id").val();
    var isIE = $.browser.msie;
    PageInint();
//    GetOnLine();
//    var comet = new CometClient(
//            "ChatChannelHandler.ashx",
//            WorkerId,
//            SessionId,
//            function (messages) {
//                var messFrom = messages.From;
//                if (messFrom == "System") {
//                    var currCount = messages.Message;

//                    if (currCount == oldCount) {
//                        //$("#messageBoxDIV").hide();
//                        alert('currCount == oldCount');
//                    }
//                    else {
//                        oldCount = currCount;
//                        UpdateUnRead(oldCount);
//                        $("#messageBoxDIV").show();
//                        $(document.body).stopTime('controlled');
//                        $("#message").html("<p style='color:blue;'>你有<span style='color:red;font-weight:bold;font-family:arial;'>  <a href=waits.aspx style='color:red;'>" + oldCount + "</a>  </span>条新信息</p>");
//                        $(document.body).everyTime(1000, 'controlled', function (i) {
//                            var f = i % 2;
//                            if (f == 0) {
//                                document.title = "有新的系统信息";
//                            }
//                            else {
//                                document.title = "消息中心";
//                            }
//                            if (i == 10) {
//                                $(document.body).stopTime('controlled');
//                                document.title = "消息中心";
//                            }
//                        });
//                    }
//                }
//                else if (messFrom == "ONLINE") {
//                    var OnLINECOUNT = messages.Message;
//                    $("#online_span").html(OnLINECOUNT);
//                    GetOnlineList();
//                }
//                else if (messFrom == "OFFLINE") {
//                    var offMessage = messages.Message;
//                    var index = messages.Message.indexOf("|");
//                    var OnLINECOUNT = offMessage.substr(0, index);
//                    var leaveWorkerId = offMessage.substr(index + 1);
//                    $("#online_span").html(OnLINECOUNT);

//                    GetOnlineList();
//                }
//                else {
//                    var date = new Date();
//                    hour = date.getHours();
//                    minute = date.getMinutes();
//                    second = date.getSeconds();
//                    if (parseFloat(hour) < 10) {
//                        hour = "0" + "" + hour;
//                    }
//                    if (parseFloat(minute) < 10) {
//                        minute = "0" + "" + minute;
//                    }
//                    if (parseFloat(second) < 10) {
//                        second = "0" + second;
//                    }
//                    var messFrom = messages.From;
//                    var ahtml = null;
//                    var adMessageContent = decodeURIComponent(messages.Message);
//                    ahtml = "<div style='color:blue;cursor:pointer;height:22px;padding-top:2px;overflow:hidden;margin:0px;border-top:1px dashed #336699;border-bottom:1px dashed #c0c0c0'  ><span style='float:left;padding-left:10px;'>" + messages.From + "</span><span style='float:right;color:red;padding-right:10px;'>" + hour + ":" + minute + ":" + second + "</span></div> <div style='padding:2px;text-align:left;margin:0px;'> " + adMessageContent + "  </div> ";

//                    //alert(parent.parent.document.title);

//                    if (messFrom != WorkerId) {
//                        $(document.body).stopTime('controlled');
//                        $(document.body).everyTime(1000, 'controlled', function (i) {
//                            var f = i % 2;
//                            if (f == 0) {
//                                document.title = "【新消息】";
//                                //$("#li_bottom_bar_" + messFrom).css({ color: "red" });


//                                //                                var chattabpanel = Ext.getCmp("chattab");
//                                //                                var tabl = chattabpanel.getComponent(messFrom);
//                                //                                tabl.title = '<span style="color:red">与' + messFrom + '1通话</span>';
//                                //alert(tabl.title);
//                            }
//                            else {
//                                document.title = "消息来自" + messFrom;
//                                //$("#li_bottom_bar_" + messFrom).css({ color: "blue" });

//                            }
//                            if (i == 10) {
//                                document.title = "YHGXBCC";
//                                $(document.body).stopTime('controlled');
//                                //$("#li_bottom_bar_" + messFrom).css({ color: "black" });

//                            }
//                        });

//                        var id = "table_user_onLINE_" + messFrom;
//                        var isStart = $("#" + id).attr("isStart");
//                        var leng = $("#realTimeBOXDIV" + messFrom).length;
//                        if (leng == 0) {
//                            //CreateTabChat(messFrom, id);
//                            $("#getMESSAGENOTICE" + messFrom).append(ahtml);
//                            $("#getMESSAGENOTICE" + messFrom).get(0).scrollTop = $("#getMESSAGENOTICE" + messFrom).get(0).scrollHeight;
//                            if (SendTo == "" || SendTo == null) {
//                                SendTo = messFrom;
//                            }
//                        }
//                        else {
//                            $("#getMESSAGENOTICE" + messFrom).append(ahtml);
//                            $("#getMESSAGENOTICE" + messFrom).get(0).scrollTop = $("#getMESSAGENOTICE" + messFrom).get(0).scrollHeight;
//                        }
//                    }
//                    else {
//                        $("#getMESSAGENOTICE" + SendTo).append(ahtml);
//                        //$("#getMESSAGENOTICE" + SendTo).get(0).scrollTop = $("#getMESSAGENOTICE" + SendTo).get(0).scrollHeight;
//                    }
//                }
//            },
//            function () {
//                // valid timeout.. who cares!
//                // system will reconnect
//            },
//            function () {

//                if (confirm("系统重新启动或者该帐号在其他地方登陆,是否重新登陆3")) {
//                    window.location.href = "Logout.aspx?returnurl=Login.aspx";
//                }
//                else {
//                    comet.startWaiting();
//                }

//            },
//            function () {
//                if (confirm("系统重新启动或者该帐号在其他地方登陆,是否重新登陆4")) {
//                    window.location.href = "Logout.aspx?returnurl=Login.aspx";
//                }
//                else {
//                    comet.startWaiting();
//                }

//            });

//    comet.startWaiting();
});
function PageInint() {
    var unReadCount = $("#txt_un_read").val();
    if (unReadCount != "") {
        if (!isNaN(unReadCount)) {

            unReadCount = parseFloat(unReadCount);
            if (unReadCount != 0) {
                oldCount = unReadCount;
                $("#messageBoxDIV").show();
                $("#message").html("<p style='color:blue;'>你有<span style='color:red;font-weight:bold;font-family:arial;'>  <a href=waits.aspx style='color:red;'>" + unReadCount + "</a>  </span>条新信息管理信息</p>");
            }
        }
        else {
            $("#messageBoxDIV").hide();
        }
    }
    GetOnlineList();

}

function GetOnlineList() {

    $.ajax({
        type: "POST",
        url: "Module/Message/backHandler.aspx",
        data: "vtype=GETONLINE",
        success: function (data) {
            var onLineCount = data.substr(0, data.indexOf("<"));
            $("#online_span").html(onLineCount);
            //alert('d_ll:' + data);
        }
    });
}

function GetOnLine() {
    $("#sendMessage").click(function () {

        $.ajax({
            type: "POST",
            url: "Module/Message/backHandler.aspx",
            data: "vtype=GETONLINE",
            success: function (data) {
                var onLineCount = data.substr(0, data.indexOf("<"));
                $("#onlineListDIV").html(data);
                //alert('d_l:' + data);
            }
        });
    })
}
/* backHandler中暂时未用
function SetPerson(workerId, obj) {
    SendTo = workerId;
    var isStart = $("#" + obj).attr("isStart");
    var leng = $("#realTimeBOXDIV" + SendTo).length;
    if (leng != 0) {
        $("#realTimeBOXDIV" + SendTo).show();
    }
    else {
        CreateTabChat(workerId, obj);
    }
}
*/
function UpdateUnRead(unReadCount) {
    $.ajax({
        type: "POST",
        url: "backdata.aspx",
        data: "ur=" + unReadCount,
        success: function (data) {

        }
    });
}
function ClearMessage() {
    $("#getMESSAGENOTICE").html("");
}
var SendTo = null;
function SendMessage() {
    if (SendTo != null) {
        var messageContent = $.trim($("#send_text" + SendTo).val());
        if (messageContent != "") {
            messageContent = messageContent.replace(/'/gi, "\"");

            messageContent = encodeURIComponent(messageContent);
            $.ajax(
             {
                 url: "ChatHandler.ashx",
                 type: "POST",
                 data:
              {
                  action: "send",
                  privateToken: SessionId,
                  sendto: SendTo,
                  message: messageContent
              },
                 success: function (a) {
                     $("#send_text" + SendTo).val("")
                 },
                 error: function () {
                     alert("发送失败,接收人已经离开");
                 }
             });
        }
    }
    else {
        alert("请指定接收人");
    }
}

function onKeyPress(e) {
    var keyCode = null;

    if (e.which)
        keyCode = e.which;
    else if (e.keyCode)
        keyCode = e.keyCode;

    if (keyCode == 13) {
        SendMessage();
        return false;
    }
    return true;
}

function CreateChatBox(WorkerId, obj) {

    var Description = "";
    $.ajax({ type: "POST", url: "Module/Message/backHandler.aspx", data: "vtype=GETDESCRIPTION&wid=" + WorkerId, success: function (data) {
        Description = data;
        $("#realTimeBOXDIVCONTENT" + WorkerId).html('与 ' + WorkerId + '  ' + Description + ' 通话中...');
    }
    });

    var newHTML = '<div style="border:1px solid #336699;width:240px; background:white;z-index:10000;POSITION:absolute;left:200px;top:200px; "'
    newHTML = newHTML + ' id="realTimeBOXDIV' + WorkerId + '" sendto="' + WorkerId + '"  >'
    /*
    newHTML = newHTML + '<div style="text-align:center;padding:3px;font-weight:bold;font-size:10pt;background:#336699;color:white;cursor:move;height:20px;"'
    newHTML = newHTML + 'moveid="realTimeBOXDIV' + WorkerId + '" id="realTimeBOXDIVTITLE' + WorkerId + '">'
    newHTML = newHTML + '<div style="width:210px;float:left;" id="realTimeBOXDIVCONTENT' + WorkerId + '" >与 ' + WorkerId + '  ' + Description + ' 通话中...</div>'
    newHTML = newHTML + '<div style="float:left;width:16px;height:15px; background-image:Url(../images/tools-sprites-trans.gif);background-repeat:no-repeat;background-position:-15 -15;cursor:pointer;background-image:Url(../images/tools-sprites-trans.gif)" onclick=\'HideChatMessagebox("' + WorkerId + '")\'></div>'
    newHTML = newHTML + '</div>'
    */
    newHTML = newHTML + '<div>'
    newHTML = newHTML + '<div style="border-bottom:0px solid black;">'
    newHTML = newHTML + '<div style="height:270px;z-index:999;">'
    newHTML = newHTML + '<div style="overflow-y:auto;height:220px;width:237px;text-align:left;word-break: break-all; word-wrap: break-word;overflow-x:auto;" id="getMESSAGENOTICE' + WorkerId + '">'
    newHTML = newHTML + '</div>'
    newHTML = newHTML + '<div style="padding:3px;text-align:center;border-top:1px solid #336699"><input type="text" id="send_text' + WorkerId + '" onkeypress="return onKeyPress(event)" maxlength="200" style="width:170px;height:20px;"/>&nbsp;&nbsp;'
    newHTML = newHTML + '<input type="button" id="send_btn' + WorkerId + '" value="Send" onclick="SendMessage()"  />&nbsp;&nbsp;'
    newHTML = newHTML + '</div>'
    newHTML = newHTML + '</div>'
    newHTML = newHTML + '</div>'
    newHTML = newHTML + '</div>'
    newHTML = newHTML + '</div>'
    $(document.body).append(newHTML);
    $("#" + obj).attr("isStart", "Y")
    $("#realTimeBOXDIVTITLE" + WorkerId).Drag();
    var liBar = '<li id="li_bottom_bar_' + WorkerId + '" onclick="HideChatMessagebox(\'' + WorkerId + '\')"  sendto="' + WorkerId + '" >';
    liBar = liBar + '<div style="cursor:pointer;border-left:1px solid #336699;padding-right:5px;width:auto;padding-left:5px ">';
    liBar = liBar + '与' + WorkerId + '通话';
    liBar = liBar + '</div>';
    liBar = liBar + '</li>';
    $("#bottomBAR_UL").append(liBar);
    var left = $("#li_bottom_bar_" + WorkerId).offset().left - 240;
    var top = $("#li_bottom_bar_" + WorkerId).offset().top - 360;
    $("#realTimeBOXDIV" + WorkerId).css({ left: left + "px", top: top + "px" });
    $("#realTimeBOXDIV" + WorkerId).focus(function () {
        SendTo = $(this).attr("sendto");
    });
    $("#realTimeBOXDIV" + WorkerId).click(function () {
        SendTo = $(this).attr("sendto");
    });
    $("#li_bottom_bar_" + WorkerId).click(function () {
        SendTo = $(this).attr("sendto");
    });

}

function HideChatMessagebox(workerId) {
    $("#realTimeBOXDIV" + workerId).toggle("slow");
}

function CreateTabChat(WorkerId, obj, WorkerName) {
    //alert(WorkerId + "|" + obj);

    var Description = "";
    $.ajax({ type: "POST",
        url: "Module/Message/backHandler.aspx",
        data: "vtype=GETDESCRIPTION&wid=" + WorkerId,
        success: function (data) {
            Description = data;
            $("#realTimeBOXDIVCONTENT" + WorkerId).html('与 ' + WorkerId + '  ' + WorkerName + ' 通话中...');
        }
    });
    //alert('d-CreateTabChat:' + WorkerId + "|" + obj + "|" + WorkerName);

    var newHTML = '<div style="border:1px solid #336699;width:auto;height:auto;background:white;"'
    newHTML = newHTML + ' id="realTimeBOXDIV' + WorkerId + '" sendto="' + WorkerId + '"  >'
    newHTML = newHTML + '<div style="text-align:center;padding:3px;font-weight:bold;font-size:10pt;background:#336699;color:white;cursor:move;height:20px;"'
    newHTML = newHTML + 'moveid="realTimeBOXDIV' + WorkerId + '" id="realTimeBOXDIVTITLE' + WorkerId + '">'
    newHTML = newHTML + '<div style="width:210px;float:left;" id="realTimeBOXDIVCONTENT' + WorkerId + '" >与 ' + WorkerId + '  ' + Description + ' 通话中...</div>'
    newHTML = newHTML + '<div style="float:left;width:16px;height:15px; background-image:Url(../images/tools-sprites-trans.gif);background-repeat:no-repeat;background-position:-15 -15;cursor:pointer;background-image:Url(../images/tools-sprites-trans.gif)" onclick=\'HideChatMessagebox("' + WorkerId + '")\'></div>'
    newHTML = newHTML + '</div>'
    newHTML = newHTML + '<div>'
    newHTML = newHTML + '<div style="border-bottom:0px solid black;">'
    newHTML = newHTML + '<div style="height:380px;z-index:999;">'
    newHTML = newHTML + '<div style="overflow-y:auto;height:380px;width:auto;text-align:left;word-break: break-all; word-wrap: break-word;overflow-x:auto;" id="getMESSAGENOTICE' + WorkerId + '">'
    newHTML = newHTML + '</div>'
    newHTML = newHTML + '<div style="padding:3px;text-align:left;border-top:1px solid #336699"><input type="text" id="send_text' + WorkerId + '" onkeypress="return onKeyPress(event)" maxlength="500" style="width:600px;height:20px;"/>&nbsp;&nbsp;'
    newHTML = newHTML + '<input type="button" id="send_btn' + WorkerId + '" value="发送" onclick="SendMessage()"  />&nbsp;&nbsp;'
    newHTML = newHTML + '</div>'
    newHTML = newHTML + '</div>'
    newHTML = newHTML + '</div>'
    newHTML = newHTML + '</div>'
    newHTML = newHTML + '</div>'
    $(document.body).append(newHTML);

    var chattabpanel = Ext.getCmp("chattab");
    var tabl = chattabpanel.getComponent(WorkerId);

    if (tabl) {
        chattabpanel.setActiveTab(tabl);
    } else {
        tabl = chattabpanel.add({
            'id': WorkerId,
            'title': '与' + WorkerName + '通话',
            closable: true,  //通过html载入目标页   
            html: newHTML
        });
        chattabpanel.setActiveTab(tabl);
    }


    $("#" + obj).attr("isStart", "Y");
    $("#realTimeBOXDIV" + WorkerId).focus(function () {
        SendTo = $(this).attr("sendto");
    });
    $("#realTimeBOXDIV" + WorkerId).click(function () {
        SendTo = $(this).attr("sendto");
    });

}