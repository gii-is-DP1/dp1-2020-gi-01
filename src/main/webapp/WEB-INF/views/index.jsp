<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="favicon.ico" rel="icon" type="image/x-icon" />	
	<link href="/css/tailwind.min.css" rel="stylesheet">	
	<link href="/css/index.css" rel="stylesheet">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Inicio - Tabernas Sevilla</title>
</head>

<header id="top" class="w-full flex flex-col fixed sm:relative bg-black pin-t pin-r pin-l">
<nav id="site-menu" class="flex flex-col sm:flex-row w-full justify-between items-center px-4 sm:px-20 py-1 bg-black shadow sm:shadow-none border-t-4 border-blue-900">
    <div class="w-full sm:w-auto self-start sm:self-center flex flex-row sm:flex-none flex-no-wrap justify-between items-center">
        <a href="/" class="no-underline py-1">
            <h1 class="font-bold text-lg tracking-widest ">Tabernas Sevilla</h1>
        </a>
        <button id="menuBtn" class="hamburger block sm:hidden focus:outline-none" type="button" onclick="navToggle();">
            <span class="hamburger__top-bun"></span>
            <span class="hamburger__bottom-bun"></span>
        </button>
    </div>
   
</nav>
</header>
<body>

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