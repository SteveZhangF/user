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
<form class="form-horizontal">
    <fieldset>

        <!-- Form Name -->
        <legend>Form Name</legend>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="textinput">Text Input</label>

            <div class="col-md-4">
                <input id="textinput" name="textinput" type="text" placeholder="placeholder"
                       class="form-control input-md">
                <span class="help-block">help</span>
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
