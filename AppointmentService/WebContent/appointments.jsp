<%@page import="model.Appointment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Appointments</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/appointment.js"></script>
<style>
body {
	color: white;
	background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)),
		url(back.jpg);
	margin: 0;
	padding: 0;
	background-size: cover;
	background-position: center;
	height: 100vh;
	font-family: sans-serif;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Appointment Management</h1>
				<br>
				<form id="formAppointment" name="formAppointment" action="view.jsp">
					Assign Doctor Id <input id="assignDoctor" name="assignDoctor"
						type="text" class="form-control form-control-sm"> <br>
					Assign Patient Id <input id="assignPatient" name="assignPatient"
						type="text" class="form-control form-control-sm"> <br>
					Appointment Date <input id="appointmentDate" name="appointmentDate"
						type="datetime-local" class="form-control form-control-sm">
					<br> Appointment Venue <input id="appointmentVenue"
						name="appointmentVenue" type="text"
						class="form-control form-control-sm"> <br> <br>
					<input id="btnSave" name="btnSave" type="button"
						value="Insert Appointment" class="btn btn-primary"> <input
						type="hidden" id="hid" name="hid" value=""> <input
						type="submit" value="View Appointment" class="btn btn-primary">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>

				<div id="alertError" class="alert alert-danger"></div>
				<br>
			</div>
		</div>
	</div>
</body>
</html>

<!-- There is a Error when updating an appointment. Use only date when updating otherwise it prints the "Data truncation" error in -->
<!-- the console (yyyy/mm//dd) format -->
