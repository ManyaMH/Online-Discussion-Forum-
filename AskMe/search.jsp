<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>AskMe - Search</title>
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
    <h2>Search Results</h2>

    <!-- This checks whether the search box is empty.-->
    <c:choose>
        <c:when test="${empty keyword}">
            <p class="empty">Type something in the search box above.</p>
        </c:when>
        <c:otherwise>
            <p class="search-info">Showing results for "<strong>${keyword}</strong>"</p>
			
			<!-- Loop Through Topics -->
            <!-- Topics found -->
            <c:if test="${not empty topics}">
                <h3 class="section-head">Topics</h3>
                <ul class="question-list">
				    <c:forEach var="t" items="${topics}">
                        <li class="question-card">
			                <a href="topic?subjectId=${t.subjectId}&topicId=${t.topicId}" class="q-title">${t.title}</a>
                            <div class="q-meta">
                                <span>in <a href="topic?subjectId=${t.subjectId}">${t.subjectName}</a></span>
                                <span>by <strong>${t.createdByUsername}</strong></span>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </c:if>

            <!-- Questions found -->
            <c:if test="${not empty questions}">
                <h3 class="section-head">Questions</h3>
                <ul class="question-list">
                    <c:forEach var="q" items="${questions}">
                        <li class="question-card">
                            <a href="question?questionId=${q.questionId}" class="q-title">${q.content}</a>
                            <div class="q-meta">
                                <span>by <strong>${q.username}</strong></span>
                                <span>${q.replyCount} replies</span>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
			
			<!--No Results -->
            <c:if test="${empty topics and empty questions}">
                <p class="empty">No results found. Try different keywords.</p>
            </c:if>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>