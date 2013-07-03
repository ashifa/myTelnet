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
	<jsp:include page="HeadMenu.jsp" />

	<s:div cssClass="dashline">

		<s:actionerror />
		<s:form action="onlineQuery">

			<s:select list="CMDMap" label="Predefined CMD" name="SelectedCMD"
				multiple="true" listKey="key" listValue="key" value="SelectedCMD"></s:select>
			<s:textfield name="customizedCMD" label="Customized CMD"
				cssStyle="width:350px" />
			<s:checkboxlist name="selectedTargetRegion" label="Regions"
				list="{'BJ','Hino','MKE'}" value="selectedTargetRegion" />
			<s:submit label="submit" />

		</s:form>
	</s:div>

	<s:div cssStyle="margin: 5px;	margin-top: 0px;	padding: 5px;">
		<table border="1" class="queryResults">
			<tr>
				<th>Target/Bay Name</th>
				<th>IP</th>
				<s:if test="#request.isOnline">
					<th>On/Off</th>
					<s:iterator value="selectedCMD">
						<th><s:property /></th>
					</s:iterator>
				</s:if>
				<s:else>
					<th>Software Version</th>
				</s:else>
			</tr>
			<s:iterator value="tblist" var="line" status="rowstatus">
				<s:if test="#rowstatus.odd == true">
					<tr class="even">
						<s:iterator value="line" var="td">
							<td><s:property value="td" default="nothing" /></td>
						</s:iterator>
					</tr>
				</s:if>
				<s:else>
					<tr>
						<s:iterator value="line" var="td">
							<td><s:property value="td" default="nothing" /></td>
						</s:iterator>
					</tr>
				</s:else>
			</s:iterator>
		</table>
	</s:div>
</body>
</html>