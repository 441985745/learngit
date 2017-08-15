<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/WEB-INF/layouts/common.jsp" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <title>归口/负责人配置查看</title>
    <meta name="modulePath" content="basic/"/>
    <link href="${staticURL}/Styles/FormPanel.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-1.3.2/themes/icon.css">

    <style type="text/css">
        #page-selection ul li {
            display: inline;
        }
        span {
            color: red;
        }
        #format {
            width: 100%;
            height:100%;
            border-right:1px solid lightgrey;
            border-bottom:1px solid lightgrey;
        }
        #format td {
            border-left:1px solid lightgrey;
            border-top:1px solid lightgrey;
        }
        .tdtitle {
            width: 20%;
            text-align: right;
        }
    </style>
</head>
<body>
<div>
    <table cellspacing="0" cellpadding="0" style="width: 100%; height: 98%;" border="0">
        <tbody>
        <tr id="Button">
            <td class="buttonbar">
                &nbsp;&nbsp;
                <input type="button" value="返回" id="btnBack" class="btn" onclick="dg.cancel();">
            </td>
        </tr>
        <tr>
            <td valign="top">
                <div>
                    <form id = "assetClassificationShowForm">
                    <table border="0" cellspacing="0" cellpadding="0" id="format">
                        <tbody>
                        <tr>
                            <td class="tdtitle"><span>*</span>公司:</td>
                            <td>
                                <input name="companyCode" type="hidden" class="companyCode code"  id="companyCode"/>
                                <input name="companyName" value="${attachConfig.companyName}" class="colorblur name" id="companyName" disabled style="width: 200px;"/>
                            </td>
                            <td class="tdtitle"><span>*</span>成本中心:</td>
                            <td>
                                <input name="costcenterCode" type="hidden" id="costcenterCode" class="costcenterCode code" />
                                <input name="costcenterName" value="${attachConfig.costcenterName}" id="costcenterName" class="colorblur name" disabled style="width: 200px;"/>
                            </td>
                        </tr>
                        <tr>
                        </tr>
                        <tr>
                            <td class="tdtitle"><span>*</span>资产类型:</td>
                            <td>
                                <select class="colorblur" id="assetType" name="assetType" style="width: 202px;"  disabled>
                                    <option ${assetType=='IT资产' ? "selected" : ""} value="IT资产">IT资产</option>
                                    <option ${assetType=='行政资产' ? "selected" : ""} value="行政资产">行政资产</option>
                                    <option ${assetType=='计量器具' ? "selected" : ""} value="计量器具">计量器具</option>
                                    <option ${assetType=='机器设备' ? "selected" : ""} value="机器设备">机器设备</option>
                                </select>
                            </td>
                            <td class="tdtitle">车间类型:</td>
                            <td>
                                <input name="workShopType" type="text" disabled value="${attachConfig.workShopType}" class="colorblur" style="width: 200px"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">归口部门管理员:</td>
                            <td>
                                <input type="hidden" name="attachDepartAdminCode" value="${attachConfig.attachDepartAdminCode}" id="attachDepartAdminCode" class="code"/>
                                <input type="text" name="attachDepartAdminName" value="${attachConfig.attachDepartAdminName}" id="attachDepartAdminName" class="colorblur name" disabled style="width: 200px;">
                            </td>
                            <td class="tdtitle">归口部门经理 :</td>
                            <td>
                                <input type="hidden" name="attachDepartManagerCode" value="${attachConfig.attachDepartManagerCode}" id="attachDepartManagerCode" class="code"/>
                                <input type="text" name="attachDepartManagerName" value="${attachConfig.attachDepartManagerName}" id="attachDepartManagerName" class="colorblur name" disabled style="width: 200px;">
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">归口部门总监:</td>
                            <td>
                                <input type="hidden" name="attachDepartMajordomoCode" value="${attachConfig.attachDepartMajordomoCode}" id="attachDepartMajordomoCode" class="code"/>
                                <input type="text" name="attachDepartMajordomoName" value="${attachConfig.attachDepartMajordomoName}" id="attachDepartMajordomoName" class="colorblur name" disabled style="width: 200px;">
                            </td>
                            <td class="tdtitle">归口部门副总裁 :</td>
                            <td>
                                <input type="hidden" name="attachDepartVpresidentCode" value="${attachConfig.attachDepartVpresidentCode}" id="attachDepartVpresidentCode" class="code"/>
                                <input type="text" name="attachDepartVpresidentName" value="${attachConfig.attachDepartVpresidentName}" id="attachDepartVpresidentName" class="colorblur name" disabled style="width: 200px;">
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">采购员名称1 :</td>
                            <td>
                                <input type="hidden" name="buyerCodea" id="buyerCodea" value="${attachConfig.buyerCodea}" class="code"/>
                                <input type="text" name="buyerNamea" id="buyerNamea" value="${attachConfig.buyerNamea}" class="colorblur name" disabled style="width: 200px;">
                            </td>
                            <td class="tdtitle">采购员名称2 :</td>
                            <td>
                                <input type="hidden" name="buyerCodeb" id="buyerCodeb" value="${attachConfig.buyerCodeb}" class="code"/>
                                <input type="text" name="buyerNameb" id="buyerNameb" value="${attachConfig.buyerNameb}" class="colorblur name" disabled style="width: 200px;">
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">采购员名称3 :</td>
                            <td>
                                <input type="hidden" name="buyerCodec" id="buyerCodec" value="${attachConfig.buyerCodec}" class="code"/>
                                <input type="text" name="buyerNamec" id="buyerNamec" value="${attachConfig.buyerNamec}" class="colorblur name" disabled style="width: 200px;">
                            </td>
                            <td class="tdtitle">采购员名称4 :</td>
                            <td>
                                <input type="hidden" name="buyerCoded" id="buyerCoded" value="${attachConfig.buyerCoded}" class="code"/>
                                <input type="text" name="buyerNamed" id="buyerNamed" value="${attachConfig.buyerNamed}" class="colorblur name" disabled style="width: 200px;">
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">采购员名称5 :</td>
                            <td>
                                <input type="hidden" value="buyerNamee" class="mark"/>
                                <input type="hidden" name="buyerCodee" id="buyerCodee" value="${attachConfig.buyerCodee}" class="code"/>
                                <input type="text" name="buyerNamee" id="buyerNamee" value="${attachConfig.buyerNamee}" class="colorblur name" disabled style="width: 200px;">
                            </td>
                            <td class="tdtitle">采购员名称6 :</td>
                            <td>
                                <input type="hidden" value="buyerNamef" class="mark"/>
                                <input type="hidden" name="buyerCodef" id="buyerCodef" value="${attachConfig.buyerCodef}" class="code"/>
                                <input type="text" name="buyerNamef" id="buyerNamef" value="${attachConfig.buyerNamef}" class="colorblur name" disabled style="width: 200px;">
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">采购员名称7 :</td>
                            <td>
                                <input type="hidden" value="buyerNameg" class="mark"/>
                                <input type="hidden" name="buyerCodeg" id="buyerCodeg" value="${attachConfig.buyerCodeg}" class="code"/>
                                <input type="text" name="buyerNameg" id="buyerNameg" value="${attachConfig.buyerNameg}" class="colorblur name" disabled style="width: 200px;">
                            </td>
                            <td class="tdtitle">采购员名称8 :</td>
                            <td>
                                <input type="hidden" value="buyerNameh" class="mark"/>
                                <input type="hidden" name="buyerCodeh" id="buyerCodeh" value="${attachConfig.buyerCodeh}" class="code"/>
                                <input type="text" name="buyerNameh" id="buyerNameh" value="${attachConfig.buyerNameh}" class="colorblur name" disabled style="width: 200px;">
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">采购经理 :</td>
                            <td>
                                <input type="hidden" name="purchasingManagerCode" value="${attachConfig.purchasingManagerCode}" id="purchasingManagerCode" class="code"/>
                                <input type="text" name="purchasingManagerName" value="${attachConfig.purchasingManagerName}" id="purchasingManagerName" class="colorblur name" disabled style="width: 200px;">
                            </td>
                            <td class="tdtitle">二级部门负责人 :</td>
                            <td>
                                <input type="hidden" name="twoDepartmentHeadCode" value="${attachConfig.twoDepartmentHeadCode}" id="twoDepartmentHeadCode" class="code"/>
                                <input type="text" name="twoDepartmentHeadName" value="${attachConfig.twoDepartmentHeadName}" id="twoDepartmentHeadName" class="colorblur name" disabled style="width: 200px;">
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">平台:</td>
                            <td><input name="platform" type="text" disabled value="${attachConfig.platform}" class="colorblur" style="width: 200px"/></td>
                            <td class="tdtitle">数据状态:</td>
                            <td>
                                <select name="status" disabled>
                                    <option value="0" ${attachConfig.status == "0" ? "selected" : ""}>有效</option>
                                    <option value="1" ${attachConfig.status == "1" ? "selected" : ""}>无效</option>
                                </select>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    </form>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<script type="text/javascript">
    var dg = frameElement.lhgDG;

</script>
</body>
</html>