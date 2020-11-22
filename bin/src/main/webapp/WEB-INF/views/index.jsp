<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="favicon.ico" rel="icon" type="image/x-icon" />	
	<link href="/css/tailwind.min.css" rel="stylesheet">	
	<link href="/css/main.css" rel="stylesheet">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
  font-family: "Lato", sans-serif;
  text-align: right;
}

h1{
color: #FFFFFF;
}

.sidenav {
  height: 100%;
  width: 0;
  position: fixed;
  z-index: 1;
  top: 0;
  right: 0;
  background-color: #111;
  overflow-x: hidden;
  transition: 0.5s;
  padding-top: 60px;
}

.sidenav a {
  padding: 8px 32px 8px 8px; /* top right bottom left */
  text-decoration: none;
  font-size: 25px;
  color: #818181;
  display: block;
  transition: 0.3s;
}

.sidenav a:hover {
  color: #f1f1f1;
}

.sidenav .closebtn {
  position: absolute;
  top: 0;
  left: 25px;
  font-size: 36px;
  margin-right: 1em;
}

.derecha {
	margin-right: 1em;
}

@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}
</style>
	
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