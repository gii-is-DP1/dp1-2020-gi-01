<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="favicon.ico" rel="icon" type="image/x-icon" />	
	<link href="/css/login.css" rel="stylesheet">
	<link href="/css/bootstrap/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="/js/bootstrap/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Inicio - Tabernas Sevilla</title>
</head>


<div class="wrapper fadeInDown">
  <div id="formContent">
    <!-- Tabs Titles -->

    <!-- Icon -->
    <div class="fadeIn first">
      <h2>Tabernas Sevilla</h2>
    </div>

    <!-- Login Form -->
    <form name="login" action="/login" method="POST">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>    
    <div class="form-group">
      <input type="text" id="username" class="fadeIn second form-control" name="username" placeholder="Login">
      </div>
      <div class="form-group">
      <input type="password" id="password" class="fadeIn third form-control" name="password" placeholder="Password">  
         </div>
    <p class="text-danger">${loginErr}</p>
      <input type="submit" class="fadeIn fourth" value="submit">
    </form>

    <!-- Remind Passowrd -->
    <div id="formFooter">
      <a class="underlineHover" href="#">Forgot Password?</a><br>
      <a class="underLineHove" href="/register">Register</a>
    </div>

  </div>
</div>
</body>
</html>