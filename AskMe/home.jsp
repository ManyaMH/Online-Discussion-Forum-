<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>AskMe-Home</title>
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

	<!-- Main Layout  -->
<div class="container layout">
    	
	<!-- left subjects sidebar -->
    <aside class="sidebar">
        <h3>Subjects</h3>
        <ul class="subject-list">
            <c:forEach var="s" items="${subjects}">
                <li>
                    <a href="topic?subjectId=${s.subjectId}">${s.name}</a>
                </li>
            </c:forEach>
        </ul>
		
		
		<div class="add-topic-box">
			<h4>Add Topic</h4>

				<c:choose>
					<c:when test="${not empty user}">
						<form action="topic" method="post">
							<input type="hidden" name="subjectId" value="1">
							<!--<input type="text" name="title" placeholder="Topic title" required>-->
							<button type="submit">Add Topic</button>
						</form>
					</c:when>

					<c:otherwise>
						<form action="login" method="get">
						<!--<input type="text" placeholder="Topic title" disabled>-->
						<button type="submit">Add Topic</button>
						</form>
						<p class="hint">Please log in to add a topic.</p>
					</c:otherwise>
				</c:choose>

			<p class="hint">Pick a subject first on the Subjects page to add a topic to it.</p>
		</div>
    </aside>
	
	
	<!-- right random questions feed -->
    <main class="main-content">
        <h2>Recent Questions</h2>

        <c:choose>
            <c:when test="${empty questions}">
                <p class="empty">No questions yet. Be the first to ask!</p>
            </c:when>
            <c:otherwise>
                <ul class="question-list">
                    <c:forEach var="q" items="${questions}">
                        <li class="question-card">
                            <a href="question?questionId=${q.questionId}" class="q-title">${q.content}</a>
                            <div class="q-meta">
                                <span>by <strong>${q.username}</strong></span>
                                <span>in <a href="topic?subjectId=${q.topicId}">${q.topicTitle}</a></span>
                                <span>${q.replyCount} replies</span>
                                <span>${q.createdAt}</span>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>
    </main>
	
</div>

</body>
</html>