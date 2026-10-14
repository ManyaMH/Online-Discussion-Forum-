<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>AskMe - MY Posts</title>
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
    <h2>My Posts</h2>
    <p class="search-info">
        Everything created by <strong>${user.username}</strong>
        &nbsp;|&nbsp;
        <a href="changePassword" class="link-btn">Change Password</a>
    </p>

    <!-- My Topics -->
    <h3 class="section-head">My Topics</h3>
    <c:choose>
        <c:when test="${empty myTopics}">
            <p class="empty">You have not created any topics yet.</p>
        </c:when>
        <c:otherwise>
            <ul class="question-list">
                <c:forEach var="t" items="${myTopics}">
                    <li class="question-card">
                        <a href="topic?subjectId=${t.subjectId}&topicId=${t.topicId}" class="q-title">${t.title}</a>
                        <div class="q-meta">
                            <span>in <a href="topic?subjectId=${t.subjectId}">${t.subjectName}</a></span>
                            <span>${t.createdAt}</span>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </c:otherwise>
    </c:choose>

    <!-- My Questions -->
    <h3 class="section-head">My Questions</h3>
    <c:choose>
        <c:when test="${empty myQuestions}">
            <p class="empty">You have not asked any questions yet.</p>
        </c:when>
        <c:otherwise>
            <ul class="question-list">
                <c:forEach var="q" items="${myQuestions}">
                    <li class="question-card">
                        <a href="question?questionId=${q.questionId}" class="q-title">${q.content}</a>
                        <div class="q-meta">
                            <span>in <a href="topic?subjectId=${q.topicId}">${q.topicTitle}</a></span>
                            <span>${q.replyCount} replies</span>
                            <span>${q.createdAt}</span>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>