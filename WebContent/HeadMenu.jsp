<%@ taglib uri="/struts-tags" prefix="s"%>

<s:div
	cssStyle="border: 1px dashed #3c78b5;margin: 10px; margin-top: 0px; ">

	<a href="<s:url action="telnet"  includeParams="none" />">telnet</a>
	<a href="<s:url action="graphicalLog"  includeParams="none" />">graphicalLog</a>
	<img alt="aaa" height=50 src=<s:url value="logo.jpg"/> />

	<s:div cssStyle="float: right; margin-top: 10px; ">Presented by ZHAO Jian (305020571)</s:div>
</s:div>
