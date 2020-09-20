package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

public class Appointment {

	public Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/appointment", "root", "");

			// System.out.println("Connection Successful");
		} catch (Exception e) {
			// System.out.println("Connection unsuccessful");
			System.out.println("" + e);
		}

		return con;
	}

	public String insertAppointment(String date, String venue, String docName, String patientId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting appoinment details.";
			}

			String insert = " insert into appointment (`app_Date`,`app_Venue`,`app_Doctor_Id`,`app_Patient_Id`) values (?,?,?,?)";
			PreparedStatement preparedStatement = con.prepareStatement(insert);

			preparedStatement.setString(1, date);
			preparedStatement.setString(2, venue);
			preparedStatement.setString(3, docName);
			preparedStatement.setString(4, patientId);

			preparedStatement.execute();

			String newAppointment = readAppoinment();
			output = "{\"status\":\"success\", \"data\": \"" + newAppointment + "\"}";

		} catch (Exception e) {

			output = "{\"status\":\"error\", \"data\": \"Error while creating appointment.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readAppoinment() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading appoinment details.";
			}else {
			output = "<table border=\"1\" width=\"100%\"><tr><th>Appointment Id</th><th>Doctor Assign</th><th>Patient Id</th>"
					+ "<th>Appointment Date</th><th>Appointment Venue</th><th colspan=\"2\">Actions</th>";
			String query = "select * from appointment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String appId = Integer.toString(rs.getInt("app_Id"));
				String appDate = rs.getString("app_Date");
				String appVenue = rs.getString("app_Venue");
				String docId = Integer.toString(rs.getInt("app_Doctor_Id"));
				String patientId = Integer.toString(rs.getInt("app_Patient_Id"));

				output += "<tr><td><input id='hidUpdate' name='hidUpdate' type='hidden' value='" + appId + "'>" + appId
						+ "</td>";
				output += "<td>" + docId + "</td>";
				output += "<td>" + patientId + "</td>";
				output += "<td>" + appDate + "</td>";
				output += "<td>" + appVenue + "</td>";

				output += "<td><input name='btnUpdate' type='button'value='Update' class='btnUpdate btn btn-secondary'>"
						+ "<input name='btnRemove' type='button' value='Delete' class='btnRemove btn btn-danger' data-appointmentid='"
						+ appId + "'>" + "</td></tr>";
			}
			output += "</table>";
			}
		} catch (Exception e) {
			output = "Error while reading the Appoinment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateAppoinment(String appId, String date, String venue, String docName, String patientId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating patient details.";
			}
			String update = "UPDATE appointment SET app_Date=?, app_Venue=?, app_Doctor_Id=?, app_Patient_Id=? WHERE app_Id=?";
			PreparedStatement preparedStatement = con.prepareStatement(update);

			preparedStatement.setString(1, date);
			preparedStatement.setString(2, venue);
			preparedStatement.setString(3, docName);
			preparedStatement.setString(4, patientId);
			preparedStatement.setInt(5, Integer.parseInt(appId));

			preparedStatement.execute();

			// There is a Error when updating an appointment. Use only date when updating
			// otherwise it prints the "Data truncation" error in
			// the console (yyyy/mm//dd) format

			String newAppointment = readAppoinment();
			output = "{\"status\":\"success\", \"data\": \"" + newAppointment + "\"}";
		} catch (Exception e) {
			output = "Error while updating the Appoinment";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteAppoinment(String appId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting patient details.";
			}
			String delete = "delete from appointment where app_Id=?";
			PreparedStatement preparedStatement = con.prepareStatement(delete);

			preparedStatement.setInt(1, Integer.parseInt(appId));

			preparedStatement.execute();
			output = "Deleted successfully";

			String newAppointment = readAppoinment();
			output = "{\"status\":\"success\", \"data\": \"" + newAppointment + "\"}";

		} catch (Exception e) {
			output = "Error while deleting the appoinment";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
