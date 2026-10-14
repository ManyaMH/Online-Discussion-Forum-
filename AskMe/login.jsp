<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>AskMe - Login</title>
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
			<a href ="register" class="btn-signup">Sign up</a>
		</nav>
    </div>
</header>	

<div class="container form-page">
	<div class="form-card">
		<h2>Login IN</h2>
		
		
		
		<c:if test="${not empty error}">
			<div class="msg error">${error}</div>
		</c:if>
		
		<c:if test="${not empty error}">
			<div class="msg success">${error}</div>
		</c:if>
		
		<form action="login" method="post">
			<label>Username</label>
			<input type="text" name="username" required autofocus>
		
			<label>Password</label>
			<input type="password" name="password" required>
		
			<button type="submit" class="btn-primary">Login</button>
		</form>
		
		<p class="form-foot">NO account?<a href="register.jsp">Sign up here</a>  &nbsp;|&nbsp; 
		<a href="changePassword.jsp" class="link-btn">Change Password</a>
		</p>
		
	<div>
<div>

</body>
</html>