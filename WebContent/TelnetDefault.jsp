<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link href="<s:url value="/mystyle.css"/>" rel="stylesheet"
	type="text/css" />
<title>bay Info</title>
</head>
<body>
<jsp:include page="HeadMenu.jsp"/>
	<s:div>

		<s:div cssClass="left">

			<s:actionerror />
			<s:form action="telnet">
				
				<s:select list="CMDMap" label="Predefined CMD" name="selectedValue"
					multiple="true" listKey="key" listValue="key" value="selectedValue"></s:select>

				<s:textfield name="customizedCMD" label="Customized CMD"
					cssStyle="width:350px" tooltip="cat /usr/g/service/log/spt.results" />

				<s:submit label="submit" />

			</s:form>
		</s:div>
	</s:div>


	<table border="1" class="queryResults">
		<tr>
			<th>Target/Bay Name</th>
			<th>IP</th>
			<th>Software Version</th>
		</tr>
		<s:iterator value="tblist" var="line">
			<tr>
				<s:iterator value="line" var="td">
					<td><s:property value="td" default="nothing" /></td>
				</s:iterator>
			</tr>
		</s:iterator>
	</table>

</body>
</html>