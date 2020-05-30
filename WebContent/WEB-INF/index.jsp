<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Events System</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
  </head>
  <body>
    
  <jsp:include page="fragments/navbar_search.jspf"/>
  
  <p> <p> <br/>
  <img class="centered" src="logo.png">
        
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
        		
		<c:if test="${(not empty sessionScope.user) && (event.user.userId != sessionScope.user.userId)}">
		 <c:if test="${not empty requestScope.userRegistrations}">
			<c:forEach var="registration" items="${requestScope.userRegistrations}">		
			    	<c:if test="${(event.eventId == registration.eventId && registration.userId == sessionScope.user.userId) && (event.attendees == event.maxAttendees)}">
						<a href="${pageContext.request.contextPath}/event_unregistration?event_id=${event.eventId}" class="btn btn-primary btn-primary">Unregister</a>	
						<c:set var="full" value="true"/>																							
			    	</c:if>
			    	<c:if test="${(event.eventId == registration.eventId && registration.userId == sessionScope.user.userId) && (event.attendees != event.maxAttendees)}">
						<a href="${pageContext.request.contextPath}/event_unregistration?event_id=${event.eventId}" class="btn btn-primary btn-primary">Unregister</a>	
						<c:set var="register" value="true"/>																							
			    	</c:if>			    	    																				
			</c:forEach>	
				<c:choose>				    		
			    	<c:when test="${(event.attendees == event.maxAttendees) && !full}">																			
						<a href="#" class="btn btn-primary btn-primary">Fully booked</a>
			    	</c:when>   
			    	<c:when test="${(event.attendees != event.maxAttendees) && !register}">																			
						<a href="${pageContext.request.contextPath}/event_registration?event_id=${event.eventId}" class="btn btn-primary btn-primary">Register</a>
			    	</c:when>
				</c:choose>
			<c:remove var="full"/>
			<c:remove var="register"/>			  
		</c:if>
 
		<c:if test="${empty requestScope.userRegistrations}">
		  <c:choose>
		   <c:when test="${(event.attendees == event.maxAttendees)}">
		    <a href="#" class="btn btn-primary btn-primary">Fully booked</a>
		   </c:when>
		   <c:otherwise>
			  <a href="${pageContext.request.contextPath}/event_registration?event_id=${event.eventId}" class="btn btn-primary btn-primary">Register</a>
		   </c:otherwise> 
		  </c:choose>
		</c:if>  		  		  
	   </c:if>

		 <c:if test="${empty sessionScope.user}">
		  <c:choose>
		   <c:when test="${(event.attendees == event.maxAttendees)}">
		    <a href="#" class="btn btn-primary btn-primary">Fully booked</a>
		   </c:when>
		   <c:otherwise>
			  <a href="${pageContext.request.contextPath}/event_registration?event_id=${event.eventId}" class="btn btn-primary btn-primary">Register</a>
		   </c:otherwise> 
		  </c:choose>
		 </c:if>	
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
