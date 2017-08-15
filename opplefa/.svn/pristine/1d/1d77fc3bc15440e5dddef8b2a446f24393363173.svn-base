<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" uri="http://mrms.opple.com/pagination" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/WEB-INF/layouts/common.jsp" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <title>区域配置修改</title>
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
                            <td class="tdtitle"><span>*</span>资产分类:</td>
                            <td><input name="assetClassification" type="text" value="${assetClassification.assetClassification}" class="colorblur" disabled/> </td>
                        </tr>
                        <tr>
                            <td class="tdtitle"><span>*</span>资产编码:</td>
                            <td><input name="assetsName" type="text" class="colorblur" value="${assetClassification.assetsName}" disabled/> </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">资产分类描述:</td>
                            <td>
                                <input name="assetstypeInfo" type="text" value="${assetClassification.assetstypeInfo}" class="colorblur" disabled/>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">折旧年限 (年):</td>
                            <td>
                                <input name="depreciationPeriod" type="text" value="${assetClassification.depreciationPeriod}" class="colorblur" disabled/>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">归口管理部门 :</td>
                            <td>
                                <input name="managementDepartment" disabled type="text" value="${assetClassification.managementDepartment}" class="colorblur"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">总账科目 :</td>
                            <td>
                                <input name="generalLedgerAccount" disabled type="text" value="${assetClassification.generalLedgerAccount}" class="colorblur"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">科目描述 :</td>
                            <td>
                                <input name="accountDescription" disabled type="text" value="${assetClassification.accountDescription}" class="colorblur"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">净残值率 :</td>
                            <td>
                                <input type="text" name="netResidualRate" value="${assetClassification.netResidualRate}" class="colorblur" disabled/>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">是否费用类别 :</td>
                            <td>
                                <c:choose>
                                    <c:when test="${assetClassification.assteEnable == 'ZAB'}">
                                        <input type="text" name="assteEnable" value="资本化" class="colorblur" disabled/>
                                    </c:when>
                                    <c:when test="${assetClassification.assteEnable == 'ZFB'}">
                                        <input type="text" name="assteEnable" value="费用化" class="colorblur" disabled/>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="text" name="assteEnable" value="${assetClassification.assteEnable}" class="colorblur" disabled/>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdtitle">数据状态:</td>
                            <td>
                                <c:choose>
                                    <c:when test="${assetClassification.status == '0'}">
                                        <input type="text" name="assteEnable" value="有效" class="colorblur" disabled/>
                                    </c:when>
                                    <c:when test="${assetClassification.status == '1'}">
                                        <input type="text" name="status" value="无效" class="colorblur" disabled/>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="text" name="status" value="${assetClassification.status}" class="colorblur" disabled/>
                                    </c:otherwise>
                                </c:choose>
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