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
	<s:div cssClass="dashline ">
		<s:div cssClass="insider">
			<table border="1" class="adminList">
				<tr>
					<th>CMD name</th>
					<th>CMD value</th>
					<th>Remove</th>
					<th>Edit</th>
				</tr>
				<s:iterator value="CMDMap" status="rowstatus">
					<s:url var="removeUrl" action="telnetAdminActionRemove">
						<s:param name="editKey" value="key" />
					</s:url>
					<s:url var="editUrl" action="telnetAdminActionEdit">
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
					<s:url var="removeUrl" action="telnetAdminActionRemove">
						<s:param name="editKey" value="key" />
					</s:url>
					<s:url var="editUrl" action="telnetAdminActionEdit">
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
		<s:div cssClass="insider">
			<s:form action="telnetAdminActionEdit">
				<s:textfield name="editKey" label="key" value="%{editKey}"
					cssStyle="width:350px" />
				<s:textfield name="editValue" label="value" value="%{editValue}"
					cssStyle="width:350px" />
				<s:submit label="submit" />
			</s:form>
		</s:div>
		<s:div cssStyle="clear:both;"></s:div>
	</s:div>

</body>
</html>