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
<title>Inicio - Tabernas Sevilla</title>
</head>

<header id="top" class="w-full flex flex-col fixed sm:relative bg-white pin-t pin-r pin-l">
<nav id="site-menu" class="flex flex-col sm:flex-row w-full justify-between items-center px-4 sm:px-6 py-1 bg-white shadow sm:shadow-none border-t-4 border-blue-900">
    <div class="w-full sm:w-auto self-start sm:self-center flex flex-row sm:flex-none flex-no-wrap justify-between items-center">
        <a href="/" class="no-underline py-1">
            <h1 class="font-bold text-lg tracking-widest">Tabernas Sevilla</h1>
        </a>
        <button id="menuBtn" class="hamburger block sm:hidden focus:outline-none" type="button" onclick="navToggle();">
            <span class="hamburger__top-bun"></span>
            <span class="hamburger__bottom-bun"></span>
        </button>
    </div>
    <div id="menu" class="w-full sm:w-auto self-end sm:self-center sm:flex flex-col sm:flex-row items-center h-full py-1 pb-4 sm:py-0 sm:pb-0 hidden">
        <a class="text-dark font-bold hover:text-red text-lg w-full no-underline sm:w-auto sm:pr-4 py-2 sm:py-1 sm:pt-2" href="login" target="_blank">Login</a>
        <a class="text-dark font-bold hover:text-red text-lg w-full no-underline sm:w-auto sm:px-4 py-2 sm:py-1 sm:pt-2" href="register">Register</a>
    </div>
</nav>
</header>
<body>
<h2 align="center"> Hello ${name}!</h2>

<script>
function navToggle() {
    var btn = document.getElementById('menuBtn');
    var nav = document.getElementById('menu');

    btn.classList.toggle('open');
    nav.classList.toggle('flex');
    nav.classList.toggle('hidden');
}

</script>
<script type="text/javascript">

var nav = document.getElementById('site-menu');
var header = document.getElementById('top');

window.addEventListener('scroll', function() {
    if (window.scrollY >=400) { // adjust this value based on site structure and header image height
        nav.classList.add('nav-sticky');
        header.classList.add('pt-scroll');
    } else {
        nav.classList.remove('nav-sticky');
        header.classList.remove('pt-scroll');
    }
});
</script>
</body>
</html>