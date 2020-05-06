$(document).ready(function()
{
	$("#alertSuccess").hide();
	
	$("#alertError").hide();
});

//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
		{
			// Clear alerts---------------------
			$("#alertSuccess").text("");
			$("#alertSuccess").hide();
			$("#alertError").text("");
			$("#alertError").hide(); 
			
			//Form validation-------------------
			var status = validateItemForm();
			if (status != true)
			{
				$("#alertError").text(status);
				$("#alertError").show();
				return;
			}
	

	// If valid-------------------------
	
			var type = ($("#hid").val() == "") ? "POST" : "PUT";
			
			$.ajax(
					{
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
			if (status == "success")
				{
					var resultSet = JSON.parse(response);
					
					if (resultSet.status.trim() == "success")
						{
						$("#alertSuccess").text("Successfully saved.");
						$("#alertSuccess").show();
						
						$("#divItemGrid").html(resultSet.data);
						}else if (resultSet.status.trim() == "error")
							{
							$("#alertError").text(resultSet.data);
							$("#alertError").show();
							
							}
				}else if (status == "error")
				{
					$("#alertError").text("Error while saving.");
					$("#alertError").show();
				}else
					{
					$("#alertError").text("UnKnown error while saving..");
					$("#alertError").show();
					}
			
				$("#hid").val("");
				$("#formAppointment")[0].reset();
			
		}

//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hid").val($(this).closest("tr").find('#hidUpdate').val());
	$("#assignDoctor").val($(this).closest("tr").find('td:eq(1)').text());
	$("#assignPatient").val($(this).closest("tr").find('td:eq(2)').text());
	$("#appointmentDate").val($(this).closest("tr").find('td:eq(3)').text());
	$("#appointmentVenue").val($(this).closest("tr").find('td:eq(4)').text());
});




// REMOVE=========================================================
$(document).on("click", ".btnRemove", function(event)
		{
			$.ajax(
					{
						url : "AppointmentsAPI",
						type : "DELETE",
						data : "appointmentId=" + $(this).data("appointmentid"),
						dataType : "text",
						complete : function(response, status)
						{
							appointmentDeleteComplete(response.responseText, status);
						}
					});
		});

function appointmentDeleteComplete(response, status) {
	if (status == "success")
		{
			var resultSet = JSON.parse(response);
			
			if (resultSet.status.trim() == "success")
				{
				$("#alertSuccess").text("Successfully Delete.");
				$("#alertSuccess").show();
				
				$("#divAppointmentsGrid").html(resultSet.data);
				
				
				
				}else if (resultSet.status.trim() == "error")
					{
					$("#alertError").text(resultSet.data);
					$("#alertError").show();
					
					}
		}else if (status == "error")
		{
			$("#alertError").text("Error while deleting.");
			$("#alertError").show();
		}else
			{
			$("#alertError").text("UnKnown error while deleting..");
			$("#alertError").show();
			}
	
		$("#formAppointment")[0].reset();
	
}

//CLIENT-MODEL================================================================
function validateItemForm()
{
	/*if ($("#appointmentId").val().trim() == "")
	{
		return "Enter Appointment ID";
	}*/
	
	if ($("#assignDoctor").val().trim() == "")
	{
		return "Enter Doctor Id";
	}
	
	if ($("#assignPatient").val().trim() == "")
	{
		return "Enter Patient Id";
	}
	
	/*var tmpPrice = $("#itemPrice").val().trim();
	if (!$.isNumeric(tmpPrice))
	{
		return "Insert a numerical value for Item Price.";
	}*/
	
//	if ($("#appointmentDate").val().trim() == "")
//	{
//		return "Enter Appointment Date";
//	}

	if ($("#appointmentVenue").val().trim() == "")
	{
		return "Enter Appointment Venue";
	}
	return true;
	}



