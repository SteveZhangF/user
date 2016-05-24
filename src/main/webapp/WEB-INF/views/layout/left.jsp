<%@ page import="com.fasterxml.jackson.databind.JsonNode" %>
<%@ page import="java.util.Iterator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>


<div id="left_menu_content_id" class="easyui-accordion" border="true" fit="false">

    <div title="Entity" data-options="iconCls:'icon-man'" style="padding:10px;">

        <%
            JsonNode root1 = (JsonNode) request.getAttribute("root");

            Iterator<JsonNode> ro1 = root1.elements();
            while (ro1.hasNext()) {


                Iterator<JsonNode> entities = ro1.next().get("entities").elements();
                while (entities.hasNext()) {
                    JsonNode entity = entities.next();
        %>
        <ul>
            <li>
                <div><a href="javascript:void(0)" class="easyui-linkbutton" plain="true"
                        onclick=addTab(1,<%out.print(entity.get("name"));%>,'entity_list?entity=<%out.print(entity.get("tableName").asText());%>')><img
                        src="resources/resources/images/left-menu/user_48.png"/><%out.print(entity.get("name").asText());%>
                </a>
                </div>

            </li>
        </ul>
        <%
            }}

        %>

    </div>

    <div title="Reports" data-options="iconCls:'icon-print'" style="padding:10px;">

        <ul id="tt" class="easyui-tree">

            <%
                JsonNode root = (JsonNode) request.getAttribute("root");

                Iterator<JsonNode> ro = root.elements();
                while (ro.hasNext()) {


                    Iterator<JsonNode> folders = ro.next().get("folders").elements();
                    while (folders.hasNext()) {
                        JsonNode folder = folders.next();
            %>

            <li>
                <span><%out.print(folder.get("name").asText());%></span>
                <ul>
                    <%
                        Iterator<JsonNode> reports = folder.get("reports").elements();
                        while (reports.hasNext()) {
                            JsonNode report = reports.next();
                    %>
                    <li>
                            <span><a href="javascript:void(0)" class="easyui-linkbutton" plain="true"
                                     onclick="addTab(1,<%out.print("'"+report.get("name").asText()+"'");%>,'report_list?report=<%out.print(report.get("tableName").asText());%>')">
                                <%out.print(report.get("name").asText());%>
                            </a>

                            </span>
                    </li>
                    <%
                        }
                    %>
                </ul>
            </li>

            <%
                    }

                }
            %>


        </ul>
    </div>
    <%--<c:forEach var="group" items="${groups}">--%>
    <%--<div title="${group.title}" data-options="iconCls:'${group.iconCls}'" style="padding:10px;">--%>
    <%--<c:forEach var="data" items="${group.data}">--%>
    <%--<ul>--%>
    <%--<li>--%>
    <%--<div><a href="javascript:void(0)" class="easyui-linkbutton" plain="true"--%>
    <%--onclick="javascript:addTab(${data.type},'${data.title}','${data.url}');return false;"--%>
    <%--><img src="${data.icon}"/>${data.title}</a></div>--%>
    <%--</li>--%>
    <%--</ul>--%>
    <%--</c:forEach>--%>
    <%--</div>--%>
    <%--</c:forEach>--%>
</div>
