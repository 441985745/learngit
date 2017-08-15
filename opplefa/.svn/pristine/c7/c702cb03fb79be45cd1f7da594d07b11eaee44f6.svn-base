
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
    <HEAD>
        <META http-equiv="Content-Type" content="text/html; utf-8">
        <META http-equiv="Content-Style-Type" content="text/css">
        <META http-equiv="Content-Script-Type" content="text/JavaScript">
        <TITLE>Checking if Acrobat Reader installed (IE4+)...</TITLE>
        <SCRIPT for="window" event="onload">
            document.all [
                         document.all.PDFNotKnown ? "IfNoAcrobat" : "IfAcrobat"
                         ] .style.display = "block";
        </SCRIPT>
    </HEAD>
    <BODY>
        <NOSCRIPT>
            Cannot determine if you have Acrobat Reader (or the full Acrobat)
            installed <FONT size="-1">(because JavaScript is unavailable or
            turned off)</FONT>.
        </NOSCRIPT>
        <DIV id="IfNoAcrobat" style="display:none">
    	     请点击<a href="http://get.adobe.com/cn/reader/download/?installer=Reader_11.0_Chinese_Simplified_for_Windows" target="_blank">这里</a>下载Adobe Reader
        </DIV>
        <OBJECT type="application/pdf" width=0 height=0 style="display:none">
            <DIV id="PDFNotKnown" style="display:none">&nbsp;</DIV>
        </OBJECT>
   <DIV id=showdiv
style="Z-INDEX: 0; LEFT:10px; WIDTH: 990px; POSITION: absolute; TOP: -8px; HEIGHT: 10px">
<object classid="clsid:CA8A9780-280D-11CF-A24D-444553540000" width="990" height="700" border="0" top="-10" name="pdf">
	<param name="toolbar" value="false">
	<param name="_Version" value="65539">
	
	<param name="_ExtentX" value="20108">
	<param name="_ExtentY" value="10866">
	<param name="_StockProps" value="0">
	<param name="SRC" value="image/${fileUpload.fileuuid}">
</object>
</DIV>
</BODY>
</HTML>