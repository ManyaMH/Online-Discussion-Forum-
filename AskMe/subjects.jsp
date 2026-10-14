<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>AskMe-Subjects</title>
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

<div class="container">
	<h2>All Subjects</h2>
	
	<!--Add subject only if logged in -->
	<div class="add-box">
    <form action="subjects" method="post">
        <input type="text" name="name" placeholder="New subject name" required>
        <button type="submit" class="btn-primary">Add Subject</button>
    </form>
    </div>
	
	<div class="card-grid">
		<c:forEach var="s" items="${subjects}">
			<a href="topic?subjectId=${s.subjectId}" class="subject-card">
				<h3>${s.name}</h3>
				<c:if test="${not empty s.createdByUsername}">
					<span class="card-meta">by ${s.createdByUsername}</span>
				</c:if>
			</a>
		</c:forEach>
	</div>
</div>
</body>
</html>