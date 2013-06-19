<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<s:url value="/mystyle.css"/>" rel="stylesheet"
	type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
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