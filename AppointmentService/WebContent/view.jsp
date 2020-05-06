<%@page import="model.Appointment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Appointment</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/appointment.js"></script>
<style type="text/css">
body {
	color: white;
	background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)),
		url(view.jpg);
	margin: 0;
	padding: 0;
	background-size: cover;
	background-position: center;
	font-family: sans-serif;
	height: 100vh;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Appointment Management</h1>
				<br>
				<form id="formAppointment" name="formAppointment">
					Assign Doctor Id<input id="assignDoctor" name="assignDoctor"
						type="text" class="form-control form-control-sm"> <br>
					Assign Patient Id<input id="assignPatient" name="assignPatient"
						type="text" class="form-control form-control-sm"> <br>
					Appointment Date <input id="appointmentDate" name="appointmentDate"
						type="text" class="form-control form-control-sm"> <br>
					Appointment Venue <input id="appointmentVenue"
						name="appointmentVenue" type="text"
						class="form-control form-control-sm"> <br> <br>
					<input id="btnSave" name="btnSave" type="button"
						value="Update Appointment" class="btn btn-primary"> <input
						type="hidden" id="hid" name="hid" value="">
				</form>
				<br>
			</div>
		</div>
	</div>

	<div id="divAppointmentsGrid">
		<%
			Appointment appointmentObj = new Appointment();
		out.print(appointmentObj.readAppoinment());
		%>
	</div>

</body>
</html>
