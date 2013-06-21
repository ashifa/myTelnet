<%@ taglib uri="/struts-tags" prefix="s" %>

<s:div cssStyle="border: 1px dashed #3c78b5;margin: 10px; margin-top: 0px;">
<p>
    <a href="<s:url action="telnet"  includeParams="none" />">telnet</a>
    <a href="<s:url action="graphicalLog"  includeParams="none" />">graphicalLog</a>
    <img alt="aaa" height=50 src=<s:url value="logo.jpg"/> />
</p>
</s:div>
<hr/>