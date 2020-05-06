$(document).ready(function() {
	$("#alertSuccess").hide();

	$("#alertError").hide();
});

$(document).on("click", "#btnSave", function(event) {

	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	var type = ($("#hid").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "AppointmentsAPI",
		type : type,
		data : $("#formAppointment").serialize(),
		dataType : "text",
		complete : function(response, status) {
			saveAppointment(response.responseText, status);
		}
	});
});

function saveAppointment(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();

			$("#divItemGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();

		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("UnKnown error while saving..");
		$("#alertError").show();
	}

	$("#formAppointment")[0].reset();

}

$(document).on("click", ".btnUpdate", function(event) {
	$("#hid").val($(this).closest("tr").find('#hidUpdate').val());
	$("#assignDoctor").val($(this).closest("tr").find('td:eq(1)').text());
	$("#assignPatient").val($(this).closest("tr").find('td:eq(2)').text());
	$("#appointmentDate").val($(this).closest("tr").find('td:eq(3)').text());
	$("#appointmentVenue").val($(this).closest("tr").find('td:eq(4)').text());
});

$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "AppointmentsAPI",
		type : "DELETE",
		data : "appointmentId=" + $(this).data("appointmentid"),
		dataType : "text",
		complete : function(response, status) {
			appointmentDeleteComplete(response.responseText, status);
		}
	});
});

function appointmentDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully Delete.");
			$("#alertSuccess").show();

			$("#divAppointmentsGrid").html(resultSet.data);

		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();

		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("UnKnown error while deleting..");
		$("#alertError").show();
	}

	$("#formAppointment")[0].reset();

}
function validateItemForm() {
	
	if ($("#assignDoctor").val().trim() == "") {
		return "Enter Doctor Id";
	}

	if ($("#assignPatient").val().trim() == "") {
		return "Enter Patient Id";
	}

	if ($("#appointmentDate").val().trim() == "") {
		return "Enter Appointment Date";
	}

	if ($("#appointmentVenue").val().trim() == "") {
		return "Enter Appointment Venue";
	}
	return true;
}
