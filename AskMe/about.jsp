<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>AskMe-About</title>
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
			<a href ="about.jsp">About</a>
			
			<!-- Lets the logged-in user view their own questions/posts. -->
	        <!-- user != null, If the user is logged in -->
			<c:choose>
				<c:when test="${not empty user}">
					<a href="myposts">My Posts</a>
					<a href="logout" class="btn-logout">Logout</a>
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

<div class="container">
	<div class ="about-page">
		<h2>About AskMe</h2>
		<p>
			<strong>AskMe</strong> is a simple web forum where people can ask questions and share answers organized by subjectsa amd topics. When you want to ask a question or post a reply, just create a free account and log in. 
		</p>
		
		<h3>How it works</h3>
        <ol>
            <li><strong>Subjects</strong> are the big categories like Mathematics, Science, or History.</li>
            <li><strong>Topics</strong> are smaller discussions inside a subject, like "Algebra" inside Mathematics.</li>
            <li><strong>Questions</strong> are posted inside a topic by any logged-in user.</li>
            <li><strong>Replies</strong> are answers posted to any question by any logged-in user.</li>
        </ol>
		
		<h3>Built with</h3>
        <p>Java Servlets, JSP, Apache Tomcat 9, MySQL, JDBC, and BCrypt password hashing.</p>
    </div>
</div>

</body>
</html>