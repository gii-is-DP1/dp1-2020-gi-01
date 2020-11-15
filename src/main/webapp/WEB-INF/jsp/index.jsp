<!--  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="/favicon/favicon.ico" rel="icon" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>Me vale</title>
</head>
<body>
<h2 align="center"> Hello ${name}!</h2>
</body>
</html>
   -->
 
 
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tabernas" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<tabernas:layout pageName="home">
    <h2><fmt:message key="welcome"/></h2>
    <div class="row">
        <div class="col-md-12">
            <h2 align="center"> Hello ${name}!</h2>
        </div>
    </div>
</tabernas:layout>