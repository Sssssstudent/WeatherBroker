<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>Download forecasts for selected city</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>
<h1> Enter the city</h1>
<c:if test="${not empty exception.message}">
    <p style="color: red; font-weight: bold;">
        <c:out value="${exception.message}"/>
    </p>
</c:if>

<form id="city" action="update" method="POST">
    <table>
        <tr>
            <td style="text-align: right;"><label for="city">City:</label>
            </td>
            <td><input type="text" id="city" name="city"
                       value="${city.city}" placeholder="Moscow or New York"/></td>
            <td><label style="color: red; width: 100%;text-align: left;">${errorMessageName}</label></td>
        </tr>
        <tr>
            <td style="text-align: right;"><label for="region">Region:</label>
            </td>

            <td><input type="text" id="region" name="region"
                       value="${city.region}" placeholder="Moscow Oblast or NY"/>
            </td>
            <td><label style="color: red; width: 100%;text-align: left;">${errorMessageRegion}</label></td>
        </tr>
    </table>
    <p>
        <input id="submit" type="submit" value="Submit"/>
    </p>
</form>
</body>
</html>
