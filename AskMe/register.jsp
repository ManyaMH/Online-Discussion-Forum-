<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>AskMe -Sign Up</title>
	<link rel ="stylesheet" href="css/style.css">
</head>
<body>
	
<header class ="navbar">
	<div class="container nav-inner">
		<a href="home" class="logo">AskMe</a>
		<nav class="nav-links">
			<a href ="home">Home</a>
			<a href ="subjects">Subjects</a>
			<a href ="about.jsp">About</a>
			<a href ="login">Login</a>
		</nav>
    </div>
</header>	

<div class="container form-page">
	<div class="form-card">
		<h2>Create Your Account</h2>
		
		
		
		<c:if test="${not empty error}">
			<div class="msg error">${error}</div>
		</c:if>
		
		<form action="register" method="post">
			<label>Username</label>
			<input type="text" name="username" required autofocus>
		
			<label>Email</label>
			<input type="email" name="email" required>
		
			<label>Password</label>
			<input type="password" name="password" required>
		
			<button type="submit" class="btn-primary">Sing UP</button>
		</form>
		
		<p class="form-foot">Already have an accoutn?<a href="login">Login here</a></p>
	<div>
<div>

</body>
</html>
		
		
		
		
		