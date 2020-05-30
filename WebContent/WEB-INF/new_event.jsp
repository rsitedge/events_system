<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <title>New event</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
  </head>
  <body>
    
  <jsp:include page="fragments/navbar.jspf"/>

  <p> <p> <br/>
  <img class="centered" src="logo.png">

    <div class="container">
		<div class="col-md-8 col-md-offset-2">
			<form class="form-new_event" method="post" action="new_event">
				<h2 class="form-new_event-heading">Add event</h2>
				<input name="inputName" type="text" class="form-control" placeholder="subject" required autofocus />
				<textarea name="inputDescription" rows="5" class="form-control" placeholder="description" required></textarea>
				<input name="inputLocation" type="text" class="form-control" placeholder="location" required/>
				<div class="col-md-4 col-md-offset-4">
				  <input name="inputDate" type="date" class="form-control" placeholder="date" required/>	 
				  <input name="inputTime" type="time" class="form-control" placeholder="time" required/>
				  <input name="inputMaxAttendees" type="number" min="1" class="form-control" placeholder="max attendees" required/>
				  <input class="btn btn-lg btn-primary btn-block" type="submit" value="Add event"/>
				</div>
			</form>
		</div>
    </div>
    
    <p> <p> <br/>
	<jsp:include page="fragments/footer.jspf"/>
     
    <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
  </body>
</html>