<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.servlet.DispatcherServlet" %>
<%--
  Created by IntelliJ IDEA.
  User: steve
  Date: 4/10/16
  Time: 11:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<script>


</script>
<%
  WebApplicationContext context1 = (WebApplicationContext)request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
  out.println(context1);
//    ((AbstractRefreshableWebApplicationContext)context1).refresh();
%>
<form class="form-horizontal">
  <fieldset>

    <!-- Form Name -->
    <legend>Employee Information</legend>

    <!-- Text input-->
    <div class="form-group">
      <label class="col-md-4 control-label" for="textinput">Text Input</label>
      <div class="col-md-4">
        <input id="textinput" name="First" type="text" placeholder="placeholder" class="form-control input-md">
        <span class="help-block">description${description}</span>
      </div>
    </div>

    <!-- Text input-->
    <div class="form-group">
      <label class="col-md-4 control-label" for="textinput">Text Input</label>
      <div class="col-md-4">
        <input id="textinput" name="textinput" type="text" placeholder="placeholder" class="form-control input-md">
        <span class="help-block">description${description}</span>
      </div>
    </div>

    <!-- Button (Double) -->
    <div class="form-group">
      <label class="col-md-4 control-label" for="button1id"></label>
      <div class="col-md-8">
        <button id="button1id" name="button1id" class="btn btn-success">OK</button>
        <button id="button2id" name="button2id" class="btn btn-danger">Cancel</button>
      </div>
    </div>

  </fieldset>
</form>

</body>
</html>
