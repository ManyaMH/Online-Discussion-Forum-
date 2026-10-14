<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>AskMe - Change Password</title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
   <!-- Header -->
<header class="navbar">
	<div class="container nav-inner">
		<a href="home" class ="logo">AskMe</a>
		
		<form action="search" method ="get" class="search-box">
			<input type="text" name="q" placeholder="Search topics or qustions..">
			<button type="submit">Search</button>
		</form>
		
		<nav class="nav-links">
			<a href ="home">Home</a>
			<a href ="subjects">Subjects</a>
			<a href ="about">About</a>
			
			<!-- Lets the logged-in user view their own questions/posts. -->
	        <!-- user != null, If the user is logged in -->
			<c:choose>
				<c:when test="${not empty user}">
					<a href="myposts">My Posts</a>
					<a href="logout" class="btn-signup">Logout</a>
				</c:when>
				<c:otherwise>
				    <a href="login">Login</a>
					<a href="register" class="btn-signup">Sign Up</a>
				</c:otherwise>
			</c:choose>
			<!--user == null ,meaning no one is logged in-->
					
		</nav>
	</div>	
</header>

<div class="container form-page">
    <div class="form-card">
        <h2>Change Password</h2>

        <c:if test="${not empty error}">
            <div class="msg error">${error}</div>
        </c:if>
        <c:if test="${not empty success}">
            <div class="msg success">${success}</div>
        </c:if>

        <form action="changePassword" method="post">
		
		    <label>username</label>
            <input type="text" name="username" required autofocus> 
            <!--<label>Current Password</label>
            <input type="password" name="currentPassword" required autofocus>-->

            <label>New Password</label>
            <input type="password" name="newPassword" required>

            <label>Confirm New Password</label>
            <input type="password" name="confirmPassword" required>

            <button type="submit" class="btn-primary">Change Password<button>
        </form>

        <!--<p class="form-foot"><a href="myposts">Back to My Posts</a></p>-->
    </div>
</div>

</body>
</html>
