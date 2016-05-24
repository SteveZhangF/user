<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Oshaippa</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">
    <link rel="shortcut icon" href="resources/resources/favicon.ico">
    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <link id="easyui_theme_id" rel="stylesheet" type="text/css"
          href="resources/resources/lib/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="resources/resources/lib/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="resources/resources/lib/themes/color.css">
    <link rel="stylesheet" type="text/css" href="resources/resources/style/main.css">
    <script type="text/javascript" src="resources/resources/lib/jquery.min.js"></script>
    <script type="text/javascript" src="resources/resources/lib/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="resources/resources/script/main.js"></script>
    <script type="text/javascript" src="resources/resources/script/msg.js"></script>
</head>
<body class="easyui-layout">
<!--顶部区域-->
<div class="top-layout" data-options="region:'north',border:false">

    <div class="top-left">

        <a href="/index.jsp">
            <img class="logo" src="resources/resources/images/logo.png" alt="logo"/>
        </a>

        <a href="/index.jsp" class="easyui-linkbutton" data-options="plain:true"><h1 class="logo-text"> Oshippa Client</h1>
        </a>
    </div>

    <div class="top-right">
        <div class="top-menu">
            <a id="person_detail_id" class="easyui-menubutton" plain="true" menu="#person_menu_id"> </a>
            <a id="msg_id" href="javascript:void(0);" class="easyui-linkbutton" plain="true"
               onclick="javascript:addTabContent('Message','app/msg/index.html' );return false;"> Message</a>
            <a id="theme_id" href="javascript:void(0);" class="easyui-linkbutton" plain="true"> Themes</a>
            <a href="javascript:void(0);" class="easyui-linkbutton" target="_blank" plain="true"
               onclick="javascript:addTabHref('Help', 'app/help/index.html');return false;">Help</a>
        </div>

    </div>

    <div id="person_menu_id" style="width:90px;">
        <div onclick="javascript:addTabHref('Setting','app/personal/index.html' );return false;"> Setting</div>
        <div onclick="javascript:logout();return false;">Log Out</div>
    </div>

</div>

<!--左侧导航-->
<div class="left-layout" data-options="region:'west',split:false,title:'Menu'" id="left_menu">
    <jsp:include page="menu/left"></jsp:include>
</div>

<!--主内容区-->
<div data-options="region:'center'" border="true">
    <div id="tabs" class="easyui-tabs" fit="true" border="false">
    </div>

</div>

<!--底部信息-->
<div class="buttom-layout" data-options="region:'south',border:false">
    <div class="buttom-left">
        <a href="javascript:void(0);" class="easyui-linkbutton" target="_blank" plain="true"
           onclick="javascript:window.open( 'http://www.oncore.com');return false;"> www.oncore.com</a>&nbsp &nbsp
        &nbsp
    </div>

    <div class="buttom-right">
        <div id="dateime_id"></div>
    </div>

</div>

<div id="tab_memu_id" class="easyui-menu" style="width:150px;">
    <div id="mm-tabupdate">Refresh</div>
    <div class="menu-sep"></div>
    <div id="mm-tabclose">Close</div>
    <div id="mm-tabcloseall">Close All</div>
    <div id="mm-tabcloseother">Close Others</div>
    <div class="menu-sep"></div>
    <div id="mm-tabcloseleft">Close left</div>
    <div id="mm-tabcloseright">Close Right</div>
</div>
<div id="login_win" class="easyui-window" title="Modal Window"
     data-options="modal:true,closable:false,draggable:false,collapsible:false,minimizable:false,maximizable:false,resizable:false,iconCls:'icon-save'"
     style="width:500px;height:200px;padding:10px;">
    <form id="login_form">
        <div>
            <label>Email
                <input id="login_email" class="easyui-validatebox" type="email" name="email" required="true"
                       validType="email"/>
            </label>
        </div>
        <div>
            <label>Password
                <input id="login_password" class="easyui-validatebox" type="password" name="password"
                       required="true"/>
            </label>
        </div>
        <div>
            <input onclick="login()" type="button" value="Login">
        </div>
    </form>
</div>
</body>
</html>
