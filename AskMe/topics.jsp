<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>AskMe-Topics</title>
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

<div class="container layout">
    <!-- Left: questions in selected topic -->
    <main class="main-content">
        <c:choose>
            <c:when test="${not empty selectedTopic}">
                <h2>${selectedTopic.title}</h2>
                <p class="breadcrumb">
                    <a href="subjects">Subjects</a> &rsaquo;
                    <a href="topic?subjectId=${subject.subjectId}">${subject.name}</a> &rsaquo;
                    ${selectedTopic.title}
                </p>

                <!-- Ask a question (only if logged in) -->
                <div class="add-box">
					<form action="question" method="post">
						<input type="hidden" name="subjectId" value="${subject.subjectId}">
						<input type="hidden" name="topicId" value="${selectedTopic.topicId}">
						<textarea name="content" placeholder="Ask your question..." rows="3" required></textarea>
						<button type="submit" class="btn-primary">Post Question</button>
						</form>
				</div>

                <c:choose>
                    <c:when test="${empty questions}">
                        <p class="empty">No questions in this topic yet.</p>
                    </c:when>
                    <c:otherwise>
                        <ul class="question-list">
                            <c:forEach var="q" items="${questions}">
                                <li class="question-card">
                                    <a href="question?questionId=${q.questionId}" class="q-title">${q.content}</a>
                                    <div class="q-meta">
                                        <span>by <strong>${q.username}</strong></span>
                                        <span>${q.replyCount} replies</span>
                                        <span>${q.createdAt}</span>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <h2>${subject.name}</h2>
                <p class="breadcrumb">
                    <a href="subjects">Subjects</a> &rsaquo; ${subject.name}
                </p>
                <p class="empty">Select a topic from the right to view its questions.</p>
            </c:otherwise>
        </c:choose>
    </main>

    <!-- Right: topics list + add topic form -->
    <aside class="sidebar">
        <h3>Topics in ${subject.name}</h3>

        <c:choose>
            <c:when test="${empty topics}">
                <p class="empty">No topics yet.</p>
            </c:when>
            <c:otherwise>
                <ul class="subject-list">
                    <c:forEach var="t" items="${topics}">
                        <li>
                            <a href="topic?subjectId=${subject.subjectId}&topicId=${t.topicId}"
                               class="${t.topicId == selectedTopic.topicId ? 'active' : ''}">
                                ${t.title}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>

        <div class="add-topic-box">
			<h4>Add Topic</h4>
				<form action="topic" method="post">
					<input type="hidden" name="subjectId" value="${subject.subjectId}">
					<input type="text" name="title" placeholder="Topic title" required>
					<button type="submit">Add</button>
				</form>
		</div>
    </aside>
</div>


</body>
</html>
