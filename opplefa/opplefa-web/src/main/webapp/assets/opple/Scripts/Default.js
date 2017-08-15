document.domain='opple.com';
//关闭TabPanel标签
Ext.ux.TabCloseMenu = function () {
    var tabs, menu, ctxItem;
    this.init = function (tp) {
        tabs = tp;
        tabs.on('contextmenu', onContextMenu);
    }

    function onContextMenu(ts, item, me) {
        if (!menu) { // create context menu on first right click
            menu = new Ext.menu.Menu([{
                id: tabs.id + '-close',
                text: '关闭当前标签',
                iconCls: "closetabone",
                handler: function () {
                    tabs.remove(ctxItem);
                }
            }, {
                id: tabs.id + '-close-others',
                text: '除此之外全部关闭',
                iconCls: "closetaball",
                handler: function () {
                    tabs.items.each(function (item) {
                        if (item.closable && item != ctxItem) {
                            tabs.remove(item);
                        }
                    });
                }
            }]);
        }
        ctxItem = item;
        var items = menu.items;
        items.get(tabs.id + '-close').setDisabled(!item.closable);
        var disableOthers = true;
        tabs.items.each(function () {
            if (this != item && this.closable) {
                disableOthers = false;
                return false;
            }
        });
        items.get(tabs.id + '-close-others').setDisabled(disableOthers);
        menu.showAt(me.getXY());
    }
};

//注销系统
function ExitApp() {
    Ext.MessageBox.show({
        title: "欧普照明",
        msg: "是否确认注销本次登录！",
        buttons: { "ok": "确定", "cancel": "取消" },
        fn: function (e) {
            //alert(e);
            if (e == "ok") {
                location.replace('Logout.aspx?returnurl=Login.aspx')
            }
        },
        animEl: "imgexit",
        width: 250,
        icon: Ext.MessageBox.WARNING,
        closable: false
    });
}

//关于系统
function AboutApp() {
    Ext.MessageBox.show({
        title: "关于",
        msg: "<div><span><textarea style=\"WIDTH: 360px; HEIGHT: 90px;FONT-SIZE:12px;\" readOnly scroll=\"yes\">" +
                        "版权所有（C）青岛远航高新科技有限公司。保留所有权利。\n" +
                        "警告：本计算机程序受著作权法保护，未经书面授权许可不得擅自复制或散布本程序的部分或全部。</textarea><br/><br/>版本号：YHGX-1.00<br/> " +
                        "授权给：欧普照明集团<br/序列号：BFF00-001D7-9813-9224-BFEBF-0006F<br/>注册码：4EA839D9-6268E857-CA611B02-CCA1F63E<br/><br>" +
                        "<a style=\"FONT-FAMILY: verdana\" href=\"#\" target=_blank>Powered by yhgx, wdhy Co. Ltd.</a></span><br/></div>",
        buttons: { "ok": "确定" },
        fn: function (e) {
            //alert(e);
        },
        width: 400,
        animEl: "imgabout",
        //multiline:true,
        closable: true
    });
}

//帮助
function AppHelp() {
    var CenterPanel = Ext.getCmp("CenterPanel");
    var tab = CenterPanel.getActiveTab(); //得到tab组建
    var url = "./Module/HelpSystem/index.htm"; //pn为拼接的类名

    if (tab.tabTip != undefined) {
        var url = "./Module/HelpSystem/index.htm?pn=" + tab.tabTip.replace('./', '').replace('.aspx', '').replace(new RegExp(/(\/)/g), '_'); //pn为拼接的类名
    }

    window.open(url, 'helpwindow', 'height=600px, width=900px, top=50px,left=100px, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no,status=no,modal=yes');
    //window.open('./Module/HelpSystem/index.htm?pn=NoHelpMessage', 'helpwindow', 'height=600px, width=900px, top=50px,left=100px, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no,status=no,modal=yes');
    //    showModelessDialog("./Module/HelpSystem/index.htm",window,"dialogWidth:900px;dialogHeight:600px;dialogLeft:100px;dialogTop:50px;help:no;status:no;scroll:no;resizable:yes");

    //    var url = "./Module/HelpSystem/index.htm";
    //      var win = new Ext.Window({
    //       title : '帮助系统',
    //       // maximizable : true,
    //       // maximized : true,
    //       width : 900,
    //       height : 600,
    //       // autoScroll : true,
    //       // bodyBorder : true,
    //       // draggable : true,
    //       isTopContainer : true,
    //       modal : true,
    //       resizable : false,
    //       contentEl : Ext.DomHelper.append(document.body, {
    //        tag : 'iframe',
    //        style : "border 0px none;scrollbar:true",
    //        src : url,
    //        height : "100%",
    //        width : "100%"
    //       })
    //      })
    //      win.show(); 

}

function AppHelpAll() {
    window.open('./Module/HelpSystem/index.htm', 'helpwindow', 'height=600px, width=900px, top=50px,left=100px, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no,status=no,modal=yes');
}

//修改密码
Ext.apply(Ext.form.VTypes, {
    password: function (val, field) {//val指这里的文本框值，field指这个文本框组件，大家要明白这个意思
        if (field.confirmTo) {//confirmTo是我们自定义的配置参数，一般用来保存另外的组件的id值
            var pwd = Ext.get(field.confirmTo); //取得confirmTo的那个id的值
            return (val == pwd.getValue());
        }
        return true;
    }
});

//var loginName = Ext.Ajax.request({
//    url: '/Module/SystemManage/ChangePwd/ChangePwd.aspx',
//    method: 'post',
//    success:
//function (response, options) {
//    var responseData = Ext.decode(response.responseText);
//    formpwd.getForm().setValues(responseData)[
//   { id: "user", value: responseData.data.maxid}];
//    //var results = Ext.util.JSON.decode(response.responseText);
//    //alert(results.datalist[0].title);
//},
//    failure:
//function () {
//    result = false;
//}
//});

var formpwd = new Ext.form.FormPanel({
    width: 336,
    frame: true,
    //title:"FormPanel",
    bodyStyle: "padding:5px 5px 0 ",
    defaults: { width: 200, xtype: "textfield" }, //*****简化****//
    items: [{
        fieldLabel: "用户名",
        xtype: "textfield",
        name: "user",
        id: "user",
        //value: loginName,
        readOnly: true,
        width: 200
    }, {
        fieldLabel: "旧密码",
        xtype: "textfield",
        name: "oldpass",
        id: "oldpass",
        inputType: "password",
        //allowBlank: false, //不允许为空
        //blankText: "不可为空，请填写", //错误提示信息，默认为This field is required!
        width: 200
    }, {
        fieldLabel: "新密码",
        xtype: "textfield",
        name: "newpass",
        id: "newpass",
        inputType: "password",
        //allowBlank: false, //不允许为空
        width: 200
    }, {
        fieldLabel: "确认新密码",
        xtype: "textfield",
        name: "surepass",
        id: "surepass",
        inputType: "password",
        //allowBlank: false, //不允许为空
        //vtype: "password", //自定义的验证类型
        //vtypeText: "两次密码不一致！",
        //confirmTo: "newpass", //要比较的另外一个的组件的id
        width: 200
    }]
});

function openChangePwd() {
//    var win = new Ext.Window({
//        title: '密码修改',
//        // maximizable : true,
//        // maximized : true,
//        width: 350,
//        height: 192,
//        iconCls: 'pwdwindow-ico',
//        // autoScroll : true,
//        // bodyBorder : true,
//        // draggable : true,
//        plain: true,
//        closeAction: 'hide',
//        closable: true,
//        isTopContainer: true,
//        modal: true,
//        resizable: false,
//        items: formpwd,
//        buttons: [
//                { text: "确定", handler: function () {
//                    if (formpwd.getForm().isValid()) {
//                        //弹出效果
//                        Ext.MessageBox.show
//                            (
//                                {
//                                    msg: '正在保存，请稍等...',
//                                    progressText: 'Saving...',
//                                    width: 300,
//                                    wait: true,
//                                    waitConfig: { interval: 200 },
//                                    icon: 'download',
//                                    animEl: 'saving'
//                                }
//                            );
//                        //setTimeout(function () { }, 10000);
//                        formpwd.getForm().submit({
//                            url: './Module/SystemManage/ChangePwd/ChangePwd.aspx',
//                            //url: './ChangePwd.jsp',
//                            success: function (f, action) {
//                                var flage = action.result.success;
//                                //alert(flage);
//                                //如果服务器端传过来的数据为true则表示登录成功
//                                if (flage == 'true') {
//                                    Ext.Msg.alert('恭喜', '密码修改成功！');
//                                    //win.hide();
//                                }
//                                else {
//                                    Ext.Msg.alert('错误', flage);
//                                }
//                            },
//                            failure: function () {
//                                Ext.Msg.alert('错误', "密码修改失败,可能是您的旧密码错误！");
//                            }
//                        });
//                        //return;
//                    }
//                }
//                },
//                { text: "重置", handler: function () {
//                    formpwd.getForm().findField("oldpass").setValue("");
//                    formpwd.getForm().findField("newpass").setValue("");
//                    formpwd.getForm().findField("surepass").setValue("");
//                }
//                }
//          ]
//    })

//    win.show();

//    //自动设置用户名为当前登录人名称////////////////////////////////////////////
//    Ext.Ajax.request({
//        url: './Module/SystemManage/ChangePwd/SetLoginName.aspx',
//        params: '',
//        method: 'GET',
//        success: function (response, options) {
//            var result = Ext.decode(response.responseText);
//            var name = result.data.name;
//            formpwd.getForm().findField("user").setValue(name);
//        },
//        failure: function (result, request) {
//            Ext.MessageBox.alert('错误', '数据获取失败！');
//        }
//    });
//    ////////////////////////////////////////////////

    var lurl = location.href.substring(0, location.href.lastIndexOf('/')) + "/ChangePwd.aspx";
    var dlg = new $.dialog({ id: 'add', title: '修改密码', cover: true, page: lurl, rang: true, width: 700, height: 300, btnBar: false, resize: false, maxBtn: true });
    dlg.ShowDialog();
}

function openChangePwdLogin() {
    var win = new Ext.Window({
        title: '密码修改',
        // maximizable : true,
        // maximized : true,
        width: 350,
        height: 192,
        iconCls: 'pwdwindow-ico',
        // autoScroll : true,
        // bodyBorder : true,
        // draggable : true,
        plain: true,
        closeAction: 'hide',
        closable: true,
        isTopContainer: true,
        modal: true,
        resizable: false,
        items: formpwd,
        buttons: [
                { text: "确定", handler: function () {
                    if (formpwd.getForm().isValid()) {
                        //弹出效果
                        Ext.MessageBox.show
                            (
                                {
                                    msg: '正在保存，请稍等...',
                                    progressText: 'Saving...',
                                    width: 300,
                                    wait: true,
                                    waitConfig: { interval: 200 },
                                    icon: 'download',
                                    animEl: 'saving'
                                }
                            );
                        //setTimeout(function () { }, 10000);
                        formpwd.getForm().submit({
                            url: './Module/SystemManage/ChangePwd/ChangePwd.aspx',
                            //url: './ChangePwd.jsp',
                            success: function (f, action) {
                                var flage = action.result.success;
                                //alert(flage);
                                //如果服务器端传过来的数据为true则表示登录成功
                                if (flage == 'true') {
                                    //Ext.Msg.alert('恭喜', '密码修改成功！');
                                    alert("密码修改成功！");
                                    //win.hide();
                                    openDefault();
                                }
                                else {
                                    Ext.Msg.alert('错误', flage);
                                }
                            },
                            failure: function () {
                                Ext.Msg.alert('错误', "密码修改失败,可能是您的旧密码错误！");
                            }
                        });
                        //return;
                    }
                }
                },
                { text: "重置", handler: function () {
                    formpwd.getForm().findField("oldpass").setValue("");
                    formpwd.getForm().findField("newpass").setValue("");
                    formpwd.getForm().findField("surepass").setValue("");
                }
                }
          ]
    })

        win.show();

        //自动设置用户名为当前登录人名称////////////////////////////////////////////
        Ext.Ajax.request({
            url: './Module/SystemManage/ChangePwd/SetLoginName.aspx',
            params: '',
            method: 'GET',
            success: function (response, options) {
                var result = Ext.decode(response.responseText);
                var name = result.data.name;
                formpwd.getForm().findField("user").setValue(name);
            },
            failure: function (result, request) {
                Ext.MessageBox.alert('错误', '数据获取失败！');
            }
        });
        ////////////////////////////////////////////////
}

//我的桌面
function AppDesktop() {
    var CenterPanel = Ext.getCmp("CenterPanel");
    CenterPanel.setActiveTab("index");
}

//个人中心
function UserCenter() {

    var CenterPanel = Ext.getCmp("CenterPanel");
    var tabl = CenterPanel.getComponent("UserCenter");

    if (tabl) {
        CenterPanel.setActiveTab(tabl);
    } else {
        tabl = CenterPanel.add({
            'id': 'UserCenter',
            'title': '用户中心',
            closable: true,  //通过html载入目标页   
            html: "<iframe scrolling='auto' frameborder='0' width='100%' height='100%' src='./Module/SystemManage/UsersCenter/List.aspx'></iframe>"
        });
        CenterPanel.setActiveTab(tabl);
    }
}

//消息中心
function MessageCenter() {
    var CenterPanel = Ext.getCmp("CenterPanel");
    var tabl = CenterPanel.getComponent("MessageCenter");

    if (tabl) {
        CenterPanel.setActiveTab(tabl);
    } else {
        tabl = CenterPanel.add({
            'id': 'MessageCenter',
            'title': '消息中心',
            closable: true,  //通过html载入目标页   
            html: "<iframe scrolling='auto' frameborder='0' width='100%' height='100%' src='./Module/Message/List.aspx'></iframe>"
        });
        CenterPanel.setActiveTab(tabl);
    }
}

var mytree = new Ext.tree.TreePanel({
    el: "container",
    animate: true,
    title: "简单Extjs动态树",
    collapsible: true,
    enableDD: true,
    enableDrag: true,
    rootVisible: true,
    autoScroll: true,
    autoHeight: true,
    width: 150,
    lines: true,
    loader: new Ext.tree.TreeLoader({
        dataUrl: "treedataHandler.ashx"
    })
});

function testApp() {
    Ext.getCmp('westtreepanel').removeAll(true);
    //var items=Ext.getCmp('westtreepanel').items
    //for(var i=0;i<items.length;i++){
    //    Ext.getCmp('westtreepanel').remove(items[i]);
    //  }

    //    var westtreepanel = Ext.getCmp("westtreepanel");
    //    var items = westtreepanel.items;
    //    alert(items[1]);
    //    westtreepanel.remove(items);
    //Ext.getCmp("westtreepanel").doLayout();
    alert(mytree);
    Ext.getCmp('westtreepanel').add(mytree).doLayout();

    //Ext.getCmp("mainviewport").doLayout();
    //alert(items.length);
}

function HeaderCollapse() {
    var expcoll = Ext.getCmp("expcoll");
    var expcollcls = expcoll.iconCls;

    var northtop = Ext.getCmp("northtop");

    if (expcollcls == 'nsCollapse') {
        document.getElementById("top").style.height = "0px";
        northtop.setHeight(25);
    }
    else {
        document.getElementById("top").style.height = "38px";
        northtop.setHeight(63);
    }

    expcoll.setIconClass(expcollcls == 'nsCollapse' ? 'nsExpand' : 'nsCollapse');
    //expcoll.setText("xxxx");

    Ext.getCmp("mainviewport").doLayout();

    //    var MainBar = Ext.getCmp("MainBar");
    //    var tb = MainBar.getTopToolbar();
    //    tb.doLayout();

    //expcoll.addClass("nsExpand");
    //alert(document.getElementById("north").style.height);
}

function openDefault() {
//    var sheight = screen.height - 70;
//    var swidth = screen.width - 10;
//    var winoption = "left=0,top=0,height=" + sheight + ",width=" + swidth + ",toolbar=no,menubar=no,location=no,status=yes,scrollbars=yes,resizable=yes";
//    window.open('Default.aspx', '', winoption);
//    window.opener = null;
//    window.open("", "_self");
//    window.close();
    top.location.href = "Default.aspx";
}