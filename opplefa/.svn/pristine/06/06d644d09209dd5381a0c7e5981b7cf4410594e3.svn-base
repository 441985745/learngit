<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="m" uri="http://mrms.opple.com/menu"%>
<html>
<head>
<title>欧普照明</title>
<meta http-equiv=Cache-Control content=no-cache />
<%@ include file="/WEB-INF/layouts/common.jsp" %>
<!----------------------- 样式引用 开始 -------------------------- -->
<link rel="Stylesheet" type="text/css" href="${staticURL}/Scripts/ExtJs/resources/css/ext-all.css">
<link rel="stylesheet" type="text/css" title="blue" href="${staticURL}/Scripts/ExtJs/resources/css/xtheme-blue.css">
<link rel="stylesheet" type="text/css" title="gray" href="${staticURL}/Scripts/ExtJs/resources/css/xtheme-gray.css">
<link rel="stylesheet" type="text/css" title="access" href="${staticURL}/Scripts/ExtJs/resources/css/xtheme-access.css">
<link href="${staticURL}/Styles/Default.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${staticURL}/Styles/menu.css">
<link rel="Stylesheet" type="text/css" href="${staticURL}/Styles/AlertDialog.css">

<!------------------------------ -------------------样式引用结束----------------------------- -->
<!------------------------------ -------------------样式引用结束----------------------------- -->
</head>


<body>

  <form id="mainform" >
        <div id="loading-mask" style="">
        </div>
        <div id="loading">
            <div class="loading-indicator">
            
                <img src="${staticURL}/Scripts/ExtJs/resources/images/extanim32.gif" alt="loading" width="32"
                    height="32" style="margin-right: 8px;" align="absmiddle" />Loading...
            </div>
        </div>
        <div id="top" style="background: url(${staticURL}/Images/back_light.png) repeat">
            <table width="730" height="58" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td style="vertical-align: middle;">
                        <img src="${staticURL}/Images/logo.png" style="margin: 0px; padding: 0px;" />
                        <img src="${staticURL}/Images/logo.gif" style="margin: 0px; padding: 0px;" />
                    </td>
                    <%--<td style="color: #fff" valign="middle" width="284">&nbsp;<p align="left" style="padding-top: 5px; font-size: 14px;">Marketing Resource Management System</p>
                    </td>--%>
                </tr>
            </table>
        </div>
        <div style="position: absolute; z-index: 20; right: 5px; top: 7px; color: white;">
            <table border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td>
                        <img src="${staticURL}/Images/head.png" id="imghead" alt="用户" style="border: 0px;" /></td>
                    <td>&nbsp;&nbsp;用户[${sessionScope._user_nick_name}]&nbsp;&nbsp;</td>
                    <td><a href="javascript:void(0);" onclick="AppDesktop();" style="text-decoration: none; color: white;">
                        <img src="${staticURL}/Images/home.png" id="imghome" alt="主页" style="border: 0px; cursor: pointer;" /></a></td>
                    <td><a href="javascript:void(0);" onclick="ExitApp();" style="color: white; text-decoration: none;">
                        <img src="${staticURL}/Images/quit.png" id="imgexit" alt="退出" style="border: 0px; cursor: pointer;" /></a></td>
                </tr>
            </table>
        </div>
        <div id="west">
            <m:menu userCode="${sessionScope._user_name}" />
            <%--<div id="wrap" class="wrap" style="display: block;">
                <div class="all-sort-list">
                    
                    <div class="item">
                        <h3><span></span>固定资产<span style="float:right;padding-top:5px;"><img src="${staticURL}/Images/arrow_right.png"></span></h3>
                        <div class="item-list clearfix" style="top: 206px; display: none;">
                            <div class="subitem">
                                <dl><dt>待办</dt>
                                    <dd><em><a onclick="addtb('tab-424','审批待办','myTasks/searchMyTasksList');">审批待办</a></em>
                                    	<em><a onclick="addtb('tab-411','申请待办','myTasks/searchApplyMyTasksList');">申请待办</a></em>
                                    </dd>
                               		
                                </dl>
                                 <dl><dt>基础数据</dt>
                                    <dd>
                                    	<em><a onclick="addtb('tab-443','办公地点','config/workAddress/searchPagerList');">办公地点</a></em>
                                    	<em><a onclick="addtb('tab-442','存放位置/地区','config/position/searchPagerList');">存放位置</a></em>
                                    	<em><a onclick="addtb('tab-447','区域','area/searchAreasList');">区域</a></em>
                                        <em><a onclick="addtb('tab-441','工厂','config/factory/searchPagerList');">工厂</a></em>
                                        <em><a onclick="addtb('tab-445','资产分类','assetClassification/searchAssetClassificationList');">资产分类</a></em>
                                        <em><a onclick="addtb('tab-449','预算资产类型配置','config/budgetAssetstypeConfig/searchPagerList');">预算资产类型配置</a></em>
                                        <em><a onclick="addtb('tab-446','归口/负责人配置','attchConfig/searchAttachConfigList');">归口/负责人配置</a></em>
                                        <em><a onclick="addtb('tab-448','单位','config/assetUnit/searchPagerList');">单位</a></em>
                                        <em><a onclick="addtb('tab-444','标准电脑配置','config/standCompConf/searchPagerList');">标准电脑配置</a></em>
                                    </dd>
                                </dl>
                                <dl><dt>预算</dt>
                                    <dd><em><a onclick="addtb('tab-425','预算导入','assetsBudget/searchAssetsBudgets');">预算导入</a></em>
                                    <em><a onclick="addtb('tab-445','预算调整','budgetAdjustment/getBudgetAdjustmentList');">预算调整</a></em></dd>
                                </dl>
                                <dl><dt>资产采购</dt>
                                    <dd><em><a onclick="addtb('tab-428','资产需求','demand/searchDemand');">资产需求</a></em><em><a onclick="addtb('tab-427','采购申请','apply/searchApply');">采购申请</a></em><em><a onclick="addtb('tab-429','采购订单','purchase/searchPurchase');">采购订单</a></em><em><a onclick="addtb('tab-430','采购收货','receiveGoods/searchReceiveGoodsMapping');">采购收货</a></em><em><a onclick="addtb('tab-431','采购验收','checkAccept/searchCheckAcceptMapping');">采购验收</a></em></dd>
                                </dl>
                                <dl><dt>付款核销</dt>
                                    <dd><em><a onclick="addtb('tab-433','付款管理','payment/searchPayment');">付款管理</a></em></dd>
                                </dl>
                                <dl><dt>资产管理</dt>
                                    <dd><em><a onclick="assetCard('tab-426','资产卡片','assetCard/searchAssetCard');">资产卡片</a></em>
                                    	<em><a onclick="addtb('tab-434','资产调拨','/assetAllocation/searchAssetAllocations');">资产调拨</a></em>
                                    	<em><a onclick="addtb('tab-436','报废变卖','discardSell/searchDiscardSell');">报废变卖</a></em>
                                    	<!-- <em><a onclick="addtb('tab-435','资产盘点','check/assetCheck');">资产盘点</a></em>
                                    	<em><a onclick="addtb('tab-437','盘点差异','check/InventoryDifference');">盘点差异</a></em>
                                    	<em><a onclick="addtb('tab-438','流程类资产','processAssets/searchProcessAssets');">流程类资产</a></em> -->
                                    </dd>
                                </dl>
                            </div>
                        </div>
                    </div>
            
                </div>
            </div>--%>
        </div>
        </div>
        <div id="center"></div>

      <div id="south" class="menu south" style="background: #e1e0e0">
            <table style="width: 100%; height: 25px; line-height: 25px; font-size: 12px;">
                <tr>
                    <td align="center" style="width: 100%">
                        <span id="lblSystem" style="color:#333399;">市场资源管理系统 v1.2&nbsp;&nbsp; 2016</span>
                    </td>
                </tr>
            </table>
        </div>
    </form>

    <!--------------------------- 下面是js的引用 开始-------------------------- -->
	<script type="text/javascript" src="${staticURL}/Scripts/ExtJs/adapter/ext/ext-base.js"></script>
    <script type="text/javascript" src="${staticURL}/Scripts/ExtJs/ext-all.js"></script> 
    
    <%-- <script language="javascript" type="text/javascript" src="${staticURL}/Scripts/JQuery/jquery-1.4.4.min.js"></script> --%>
    <script type="text/javascript" src="${staticURL}/Scripts/Default.js"></script>
    <script type="text/javascript" src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.js"></script>	
    <%-- <script type="text/javascript" src="${staticURL}/Scripts/JQuery/lhgdialog/lhgdialog.min.js"></script> --%>	
    <script type="text/javascript" src="${staticURL}/Scripts/JSAlertDialog.js"></script>
  
    <script src="${staticURL}/Scripts/WebResource.js" type="text/javascript"></script>
	<script src="${staticURL}/Scripts/ScriptResource_002.js" type="text/javascript"></script>
	<script src="${staticURL}/Scripts/ScriptResource.js" type="text/javascript"></script>
	 <script type="text/javascript" src="${staticURL}/Scripts/ExtJs/styleswitcher.js"></script> 
  <!--------------------------- 下面是js的引用结束-------------------------- -->
	
<script type="text/javascript">
        var west, treePanel, MainBar;
        //var treeLoader = new Ext.tree.TreeLoader({ dataUrl: 'TreeMenuHandler.aspx?code=m000000156' });
        var root = new Ext.tree.AsyncTreeNode();

        Ext.onReady(function () {
            Ext.QuickTips.init(); //支持tips提示
            Ext.BLANK_IMAGE_URL = "Scripts/ExtJs/resources/images/default/s.gif";

            var CenterPanel = new Ext.TabPanel({
                id: "CenterPanel",
                region: "center",
                border: false,
                bodyStyle: "background: transparent;border:0px",
                bodyBorder: false,
                //默认选中第一个
                activeItem: 0,
                //如果Tab过多会出现滚动条
                enableTabScroll: true,
                //加载时渲染所有
                deferredRender: false,
                layoutOnTabChange: true,
                items: [{
                    xtype: "panel",
                    autoScroll: true,
                    id: "index",
                    iconCls: "index-ico",
                    title: "我的桌面",
                    html: "<iframe src='' scrolling='auto' frameborder='0' width='100%' height='100%'></iframe>"
                }]
                , plugins: new Ext.ux.TabCloseMenu()
            });

            var top = new Ext.BoxComponent({
                el: "top",
                height: 58
            });

            var northtop = new Ext.Panel({
                id: 'northtop',
                border: false,
                region: "north",
                //        collapsible: true,			
                //        collapseMode:'mini',
                height: 58,
                items: [top]
            });

            var viewport = new Ext.Viewport({
                id: 'mainviewport',
                layout: 'border',
                items: [northtop,
                  {
                      region: 'west',
                      id: 'west',
                      //el:'panelWest',
                      title: '菜单导航',
                      split: true,
                      width: 200,
                      minSize: 200,
                      maxSize: 400,
                      collapsible: true,
                      border: false,
                      margins: '1 0 2 2',
                      cmargins: '1 2 2 2',
                      layout: 'fit',
                      layoutConfig: { activeontop: true },
                      defaults: { bodyStyle: 'margin:0;padding:0;' },
                      //iconCls:'nav',
                      listeners: {
                          'collapse': function (node, e) {
                              $("#wrap").hide();
                          },
                          'expand': function (node, e) {
                              //todo 展开的操作 
                              $("#wrap").show();
                          }
                      }
                   
                  }, {
                      region: 'center',
                      layout: 'fit',
                      deferredRender: false,
                      margins: '1 0 2 0',
                      items: CenterPanel,
                      autoScroll: false
                  }
                  ,
            {
                region: 'south',
                contentEl: 'south',
                margins: '0 0 0 2',
                height: 25,
                //split: true,
                border: false
            }
                ]
            });

            setTimeout(function () {
                Ext.get('loading').remove();
                Ext.get('loading-mask').fadeOut({ remove: true });
            }, 250)

            function synb(node, e) {
                return node.isLeaf();
            }

            //change的实现
            function changeTab(tab, newtab) {
                //如果存在相应树节点，就选中,否则就清空选择状态
                var nodeId = newtab.id.replace('tab-', '');
                var node = this.menuTree.getNodeById(nodeId);
                if (node) {
                    this.menuTree.getSelectionModel().select(node);
                } else {
                    this.menuTree.getSelectionModel().clearSelections();
                }
            }
        });

        function addtb(tabId, text, url) {
            top.Ext.getCmp("CenterPanel").add({//得到主窗体CenterPanel元素
                'id': tabId,
                'title': text,
                'tabTip': url,
                closable: true,  //通过html载入目标页   
                html: "<iframe scrolling='auto' frameborder='0' width='100%' height='100%' src='" + url + "'></iframe>"
            });
            top.Ext.getCmp("CenterPanel").setActiveTab(tabId);
        }
        function assetCard(tabId, text, url) {
        	
            top.Ext.getCmp("CenterPanel").add({//得到主窗体CenterPanel元素
                'id': tabId,
                'title': text,
                'tabTip': url,
                closable: true,  //通过html载入目标页   
                html: "<iframe scrolling='auto' frameborder='0' width='100%' height='100%' src='" + url + "'></iframe>"
            });
            top.Ext.getCmp("CenterPanel").setActiveTab(tabId);
        }
    </script>




  <script type="text/javascript">
      $('.all-sort-list > .item').hover(function () {
          var eq = $('.all-sort-list > .item').index(this),				//获取当前滑过是第几个元素
                  h = $('.all-sort-list').offset().top,						//获取当前下拉菜单距离窗口多少像素
                  s = $(window).scrollTop(),									//获取游览器滚动了多少高度
                  i = $(this).offset().top,									//当前元素滑过距离窗口多少像素
                  item = $(this).children('.item-list').height(),				//下拉菜单子类内容容器的高度
                  sort = $('.all-sort-list').height();						//父类分类列表容器的高度

          if (item < sort) {												//如果子类的高度小于父类的高度
              if (eq == 0) {
                  $(this).children('.item-list').css('top', (i - h));
              } else {
                  $(this).children('.item-list').css('top', (i - h) + 1);
              }
          } else {
              if (s > h) {												//判断子类的显示位置，如果滚动的高度大于所有分类列表容器的高度
                  if (i - s > 0) {											//则 继续判断当前滑过容器的位置 是否有一半超出窗口一半在窗口内显示的Bug,
                      $(this).children('.item-list').css('top', (s - h) + 2);
                  } else {
                      $(this).children('.item-list').css('top', (s - h) - (-(i - s)) + 2);
                  }
              } else {
                  $(this).children('.item-list').css('top', 3);
              }
          }

          $(this).addClass('hover');
          $(this).children('.item-list').css('display', 'block');
      }, function () {
          $(this).removeClass('hover');
          $(this).children('.item-list').css('display', 'none');
      });

      $('.item > .item-list > .close').click(function () {
          $(this).parent().parent().removeClass('hover');
          $(this).parent().hide();
      });
      //注销系统
      function ExitApp() {
          Ext.MessageBox.show({
              title: "欧普照明",
              msg: "是否确认注销本次登录！",
              buttons: { "ok": "确定", "cancel": "取消" },
              fn: function (e) {
                  //alert(e);
                  if (e == "ok") {

                      location.replace("${dynamicURL}/exit")
                  }
              },
              animEl: "imgexit",
              width: 250,
              icon: Ext.MessageBox.WARNING,
              closable: false
          });
      }

  </script>

</body>

</html>