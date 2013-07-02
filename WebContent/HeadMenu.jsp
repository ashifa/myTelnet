<%@ taglib uri="/struts-tags" prefix="s"%>

<s:div
	cssStyle="border: 1px dashed #3c78b5;margin: 10px; margin-top: 0px; ">
	<s:div cssStyle="float: left;  ">
		<a href="<s:url action="telnet"  includeParams="none" />">telnet</a>
		<a href="<s:url action="graphicalLog"  includeParams="none" />">graphicalLog</a>
		<a href="<s:url action="test" includeParams="none" />">telnetAdmin</a>
		<img alt="aaa" height=50 src=<s:url value="logo.jpg"/> />
	</s:div>
	<s:div cssStyle="float: right;  ">Presented by ZHAO Jian (305020571)</s:div>
	<s:div cssStyle="clear:both;"></s:div>
</s:div>
