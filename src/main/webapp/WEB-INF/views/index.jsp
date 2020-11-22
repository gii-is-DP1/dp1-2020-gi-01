<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="favicon.ico" rel="icon" type="image/x-icon" />	
	 <!--   <link href="/css/tailwind.min.css" rel="stylesheet">	-->
	<link href="/css/index.css" rel="stylesheet">
	<link href="mystyle.css" rel="stylesheet">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://kit.fontawesome.com/a076d05399.js"></script>
	<!--Get your own code at fontawesome.com-->
	<title>Inicio - Tabernas Sevilla</title>
</head>

<body>

<div class="flex-container">
	<div><i class="fas fa-sign-in-alt" style="font-size:30px; padding-left:10px"></i></div>
	<div> <a href="login">Login | Register</a></div>
	<div> <a href="#"> Tabernas Sevilla</a></div>
        	
</div>
        	
 		<!--   
        <button id="menuBtn" class="hamburger block sm:hidden focus:outline-none" type="button" onclick="navToggle();">
            <span class="hamburger__top-bun"></span>
            <span class="hamburger__bottom-bun"></span>
        </button>
			-->

<div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <a href="#">Home</a>
  <a href="#">Taverns</a>
  <!-- 
   	<a href="#">    Porvenir</a>
    <a href="#">    Torneo</a>
    <a href="#">    Nervión</a>
    <a href="#">    Arenal</a>  -->
  <a href="#">Menu</a>
  <a href="#">Booking</a>
  <a href="#">Contact</a>
</div>


<span class="derecha" style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; </span>

<script>
function openNav() {
  document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
}
</script>


</body>
</html>