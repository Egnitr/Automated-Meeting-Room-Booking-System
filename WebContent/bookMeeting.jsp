<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@page import="com.hsbc.system.util.MeetingFetchData,java.sql.Date"%>
<!DOCTYPE html>
<html lang="en" >

<head>
  <meta charset="UTF-8">
  <title>Book Meeting Room</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="css/style.css">
  <link rel="shortcut icon" href="favicon.png">
</head>
<body style="height: 100% ; " >
  <div class="signup__container" style="height: 90%;">
  <div class="container__child signup__thumbnail" style="background-image: url(img/back1.jpg);">
    <div class="thumbnail__logo">
      <h1 class="logo__text" >&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspAutomated Meeting Room Booking System</h1>
    </div>
    <div class="thumbnail__content text-center">
      <h1 class="heading--primary" align="center" style="margin-top: -65%;background-color: cyan;border-radius: 9px">&nbsp&nbspOrganise a Meeting&nbsp&nbsp</h1><br><br><br><br>
    </div>
    <div class="signup__overlay"></div>
  </div>
  <div class="container__child signup__form"style="background-color: grey">
    <form class="login-form" action="meetingDataController" method="post" style="margin-top: 20%">
          <label for="room">Title</label>&nbsp&nbsp&nbsp&nbsp<input class="form-control" type="text" id="title" name="title" autocomplete="on" required style="width: 255px; height: 25px; margin-top: 10px;margin-bottom: 20px;border-color: cyan"><br>
          <label for="info">Meeting Info</label>&nbsp&nbsp&nbsp&nbsp<input class="form-control" type="text" id="info" name="info" autocomplete="on" required style="width: 205px; height: 25px; margin-top: 10px;margin-bottom: 30px;border-color: cyan"><br>
          Meeting Type
              <select  name="type" style="width: 255px; height: 30px; margin-top: 10px;margin-bottom: 20px" required="">
                <option hidden disabled selected value> -- Select a Category -- </option>
                <option name="type" value="Classroom Training">Classroom Training</option>
                <option name="type" value="Online Training">Online Training</option>
                <option name="type" value="Conference Call">Conference Call</option>
                <option name="type" value="Business">Business</option>
              </select><br>
          <p>Meeting Date</p>
          <input type="date" id="date" name="date" style="margin-bottom: 20%" required min="2010-10-05" onclick="checkdate()"><br><br>
          <div class="m-t-lg" align="center">
                <input class="btn btn--form" id="submit" name="submit" type="submit" value="Proceed Next" />
          </div>
  
    </form> 
  </div>
</div>
</body>
</html>
<script>

function checkdate()
{
  var today = new Date();
  var dd = today.getDate();
  var mm = today.getMonth()+1;
  var yyyy = today.getFullYear();
   if(dd<10){
          dd='0'+dd
      } 
      if(mm<10){
          mm='0'+mm
      } 

  today = yyyy+'-'+mm+'-'+dd;
  document.getElementById("date").setAttribute("min", today);
}
  
</script>