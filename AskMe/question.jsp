<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>AskMe - Question</title>
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

<div class="container">
    <c:choose>
        <c:when test="${not empty question}">
            <p class="breadcrumb">
                <a href="subjects">Subjects</a> &rsaquo;
                <a href="topic?subjectId=${topic.subjectId}">${topic.subjectName}</a> &rsaquo;
                <a href="topic?subjectId=${topic.subjectId}&topicId=${topic.topicId}">${topic.title}</a>
            </p>

            <!-- The question -->
            <div class="question-detail">
                <h2>${question.content}</h2>
                <div class="q-meta">
                    <span>Asked by <strong>${question.username}</strong></span>
                    <span>${question.createdAt}</span>
                    <span>${question.replyCount} replies</span>
                </div>
            </div>

            <!-- Replies -->
            <h3 class="section-head">${question.replyCount} Replies</h3>

            <c:choose>
                <c:when test="${empty replies}">
                    <p class="empty">No replies yet. Be the first to answer!</p>
                </c:when>
                <c:otherwise>
                    <ul class="reply-list">
                        <c:forEach var="r" items="${replies}">
                            <li class="reply-card">
                                <div class="reply-head">
                                    <strong>${r.username}</strong>
                                    <span>${r.createdAt}</span>
                                </div>
                                <p>${r.content}</p>
                            </li>
                        </c:forEach>
                    </ul>
                </c:otherwise>
            </c:choose>

            <!-- Reply form (only if logged in) -->
            <c:choose>
                <c:when test="${not empty user}">
                    <div class="add-box">
                        <form action="reply" method="post">
                            <input type="hidden" name="questionId" value="${question.questionId}">
                            <textarea name="content" placeholder="Write your answer..." rows="3" required></textarea>
                            <button type="submit" class="btn-primary">Post Reply</button>
                        </form>
                    </div>
                </c:when>
                <c:otherwise>
                    <p class="login-prompt">
                        <a href="login">Login</a> to post a reply.
                    </p>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
            <p class="empty">Question not found.</p>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>