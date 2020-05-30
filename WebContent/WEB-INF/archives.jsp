<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Archives</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
  </head>
  <body>
    
  <jsp:include page="fragments/navbar.jspf"/>
  
  <p> <p> <br/>
  <img class="centered" src="logo.png">
     
  <p> <p> <br/>
  <div class="text-center">
    <h4> Archival events </h4>
  </div>
      
 <c:if test="${not empty requestScope.events}">
  <c:forEach var="event" items="${requestScope.events}"> 
    <div class="container">
      <div class="row bs-callout bs-callout-primary">
      	<div class="col col-md-9 col-sm-8">
	      <h3 class="centered"><a href="#"><c:out value="${event.name}" /></a></h3>
	      <p> <p>
          <div>Description: <c:out value="${event.description}"/></div>
          <p> <p>
          <div>Organizer: <c:out value="${event.user.username}"/></div>
          <p> <p>
          <c:if test="${(not empty sessionScope.user) && (event.user.userId == sessionScope.user.userId)}">
        	<div>Attendees: <c:out value="${event.attendees}"/></div>
		  </c:if>
      	</div>
        <div class="col col-md-3 col-sm-4 text-right">
			<p> <p>
			<div>Date: <c:out value="${event.date}"/></div>
			<p> <p>
			<div>Time: <c:out value="${event.time}"/></div>
			<p> <p>
			<div>Location: <c:out value="${event.address}"/></div>
        </div>
      </div>
    </div>
  </c:forEach>
 </c:if>
     
  <jsp:include page="fragments/footer.jspf"/>
     
    <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
  </body>
</html>
