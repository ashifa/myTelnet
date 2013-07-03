<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<link href="<s:url value="/mystyle.css"/>" rel="stylesheet"
	type="text/css" />
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="HeadMenu.jsp" />

	<s:div
		cssStyle="margin: 10px; margin-top: 0px; padding: 10px;  float: left ">
		<s:form >
			<s:textfield key="user" cssStyle="width:350px" />
			<s:textfield key="password" cssStyle="width:350px" />
			<s:submit label="submit" />
		</s:form>
	</s:div>


</body>
</html>