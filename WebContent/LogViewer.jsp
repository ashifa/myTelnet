<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<link href="<s:url value="/mystyle.css"/>" rel="stylesheet"
	type="text/css" />
<title>Log Viewer</title>
</head>
<body>
<jsp:include page="HeadMenu.jsp"/>
	<table border="1">
		<s:iterator value="list" begin="0" end="100" var="logRecord">
			<tr>
				<td><s:property value="date" /></td>
				<td><s:property value="content" /></td>
			</tr>
		</s:iterator>


	</table>



</body>
</html>