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
		<s:div
			cssStyle="margin: 10px; margin-top: 0px; padding: 10px;float:left">
			<table border="1" class="adminList">
				<tr>
					<th>CMD name</th>
					<th>CMD value</th>
					<th>Remove</th>
					<th>Edit</th>
				</tr>
				<s:iterator value="CMDMap" status="rowstatus">
					<s:url var="removeUrl" action="remove">
						<s:param name="editKey" value="key" />
					</s:url>
					<s:url var="editUrl" action="edit">
						<s:param name="editKey" value="key" />
						<s:param name="editValue" value="value" />
						<s:param name="editFlag" value="true" />
					</s:url>
					<s:if test="#rowstatus.odd == true">
						<tr class="even">
							<td><s:property value="key" /></td>
							<td><s:property value="value" /></td>

							<td><a href="${removeUrl}">Remove</a></td>
							<td><a href="${editUrl}">Edit</a></td>
						</tr>
					</s:if>
					<s:else>
						<tr>
							<td><s:property value="key" /></td>
							<td><s:property value="value" /></td>
							<td><a href="${removeUrl}">Remove</a></td>
							<td><a href="${editUrl}">Edit</a></td>
						</tr>
					</s:else>

				</s:iterator>
				<tr>
					<th>Target/Bay name</th>
					<th>IP</th>
					<th>Remove</th>
					<th>Edit</th>
				</tr>
				<s:iterator value="TargetMap" status="rowstatus">
					<s:url var="removeUrl" action="remove">
						<s:param name="editKey" value="key" />
					</s:url>
					<s:url var="editUrl" action="edit">
						<s:param name="editKey" value="key" />
						<s:param name="editValue" value="value" />
						<s:param name="editFlag" value="true" />
					</s:url>
					<s:if test="#rowstatus.odd == true">
						<tr class="even">
							<td><s:property value="key" /></td>
							<td><s:property value="value" /></td>

							<td><a href="${removeUrl}">Remove</a></td>
							<td><a href="${editUrl}">Edit</a></td>
						</tr>
					</s:if>
					<s:else>
						<tr>
							<td><s:property value="key" /></td>
							<td><s:property value="value" /></td>
							<td><a href="${removeUrl}">Remove</a></td>
							<td><a href="${editUrl}">Edit</a></td>
						</tr>
					</s:else>

				</s:iterator>
			</table>
		</s:div>
		<s:div
			cssStyle="margin: 10px; margin-top: 0px; padding: 10px;  float: left ">
			<s:form action="edit">
				<s:textfield name="editKey" label="key" value="%{editKey}"  cssStyle="width:350px"/>
				<s:textfield name="editValue" label="value" value="%{editValue}"  cssStyle="width:350px" />
				<s:submit label="submit" />
			</s:form>
		</s:div>
		<s:div cssStyle="clear:both;"></s:div>
	</s:div>

</body>
</html>