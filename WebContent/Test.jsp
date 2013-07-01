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
		cssStyle="border: 1px dashed #3c78b5;margin: 10px; margin-top: 0px; padding: 10px; ">
		<table border="1" class="queryResults">
			<tr>
				<th>Key</th>
				<th>value</th>
				<th>Remove</th>
				<th>Edit</th>
			</tr>
			<s:iterator value="CMDMap" status="rowstatus">
				<s:url var="removeUrl" action="remove">
					<s:param name="mapKey" value="key" />
				</s:url>
				<s:if test="#rowstatus.odd == true">
					<tr class="even">
						<td><s:property value="key" /></td>
						<td><s:property value="value" /></td>

						<td><a href="${removeUrl}">Remove</a></td>
						<td>Edit</td>
					</tr>
				</s:if>
				<s:else>
					<tr>
						<td><s:property value="key" /></td>
						<td><s:property value="value" /></td>
						<td><a href="${removeUrl}">Remove</a></td>
						<td>Edit</td>
					</tr>
				</s:else>

			</s:iterator>
		</table>
	</s:div>
	<s:div
		cssStyle="border: 1px dashed #3c78b5;margin: 10px; margin-top: 0px; padding: 10px; ">
		<table border="1" class="queryResults">
			<tr>
				<th>Key</th>
				<th>value</th>
				<th>Remove</th>
				<th>Edit</th>
			</tr>
			<s:iterator value="targetMap" status="rowstatus">
				<s:url var="removeUrl" action="remove">
					<s:param name="mapKey" value="key" />
				</s:url>
				<s:if test="#rowstatus.odd == true">
					<tr class="even">
						<td><s:property value="key" /></td>
						<td><s:property value="value" /></td>

						<td><a href="${removeUrl}">Remove</a></td>
						<td>Edit</td>
					</tr>
				</s:if>
				<s:else>
					<tr>
						<td><s:property value="key" /></td>
						<td><s:property value="value" /></td>
						<td><a href="${removeUrl}">Remove</a></td>
						<td>Edit</td>
					</tr>
				</s:else>

			</s:iterator>
		</table>
	</s:div>
</body>
</html>