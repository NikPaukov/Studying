<%@ page import="com.example.lab3.CookiesService" %>
<%@ page import="com.example.lab3.ParamSerivce" %>
<%@ page import="java.util.Arrays" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="style.css">
    <%! double[] params;
        double[] res;
        ParamSerivce paramSerivce;
        public void init() throws ServletException {
            params = new double[]{0.1, 0.5, 0.1, -2.5, -2, 0.1, 1.65, 2.05, 0.1, 3, 3.6, 0.2};
        }
    %>
    <%
        paramSerivce = new ParamSerivce((String[]) request.getAttribute("paramNames"));
        if (request.getAttribute("params")!=null) {
            {
                params = paramSerivce.setParamsFromRequest(request);
            }
        } else if(session.getAttribute("params") != null) {
            params = paramSerivce.setParamsFromSession(session);
        } else{
            params = new double[]{0.1, 0.5, 0.1, -2.5, -2, 0.1, 1.65, 2.05, 0.1, 3, 3.6, 0.2};
        }
        res = (double[]) request.getAttribute("res");

    %>
</head>
<body>
<img src="imgs/func.png">
<br/>
<form method="post" action="count">
    A:
    <label for="aFrom">from</label>
    <input name="aFrom" id="aFrom" type="number" step="any" required="required"
           value="<%=params[0]%>">

    <label for="aTo">To</label>
    <input name="aTo" id="aTo" type="number" step="any" value="<%=params[1]%>">

    <label for="aStep">Step</label>
    <input name="aStep" id="aStep" type="number" step="any" value="<%=params[2]%>"><br>
    B:
    <label for="bFrom">From</label>
    <input name="bFrom" id="bFrom" type="number" step="any" value="<%=params[3]%>">

    <label for="bTo">To</label>
    <input name="bTo" id="bTo" type="number" step="any" value="<%=params[4]%>">

    <label for="bStep">Step</label>
    <input name="bStep" id="bStep" type="number" step="any" value="<%=params[5]%>"><br>
    C:
    <label for="cFrom">From</label>
    <input name="cFrom" id="cFrom" type="number" step="any" value="<%=params[6]%>">

    <label for="cTo">To</label>
    <input name="cTo" id="cTo" type="number" step="any" value="<%=params[7]%>">

    <label for="cStep">Step</label>
    <input name="cStep" id="cStep" type="number" step="any" value="<%=params[8]%>"><br>
    D:
    <label for="dFrom">From</label>
    <input name="dFrom" id="dFrom" type="number" step="any" value="<%=params[9]%>">

    <label for="dTo">To</label>
    <input name="dTo" id="dTo" type="number" step="any" value="<%=params[10]%>">

    <label for="dStep">Step</label>
    <input name="dStep" id="dStep" type="number" step="any" value="<%=params[11]%>"><br>
    <input type="submit" value="Підтвердити">
</form>


<% if (res != null) {%>
<table>
    <tr>
        <td>a</td>
        <td>b</td>
        <td>c</td>
        <td>d</td>
        <td>Результат</td>
    </tr>
    <%
        int iter = 0;
        for (double i = params[0]; i < params[1]; i += params[2]) {
            for (double j = params[3]; j < params[4]; j += params[5]) {
                for (double k = params[6]; k < params[7]; k += params[8]) {
                    for (double l = params[9]; l < params[10]; l += params[11]) {%>
    <tr>
        <td><%=String.format("%.3f", i)%>
        </td>
        <td><%=String.format("%.3f", j)%>
        </td>
        <td><%=String.format("%.3f", k)%>
        </td>
        <td><%=String.format("%.3f", l)%>
        </td>
        <td>
            <%=String.format("%.3f", res[iter])%>
        </td>
    </tr>

    <% iter++;
    }
    }
    }
    }
    }
        params = new double[]{0.1, 0.5, 0.1, -2.5, -2, 0.1, 1.65, 2.05, 0.1, 3, 3.6, 0.2};
        res = null;
    %>
</table>
</body>
</html>

