package model;

import java.io.IOException;import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AppointmentsAPI
 */
@WebServlet("/AppointmentsAPI")
public class AppointmentsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Appointment appointmentObj = new Appointment();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AppointmentsAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String output = appointmentObj.insertAppointment(request.getParameter("appointmentDate"),
				request.getParameter("appointmentVenue"), request.getParameter("assignDoctor"),
				request.getParameter("assignPatient"));

		response.getWriter().write(output);

	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = appointmentObj.deleteAppoinment(paras.get("appointmentId").toString());
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
		  protected void doPut(HttpServletRequest request, HttpServletResponse
		  response) throws ServletException, IOException { Map paras =
		  getParasMap(request);
		  
		  String output =
		  appointmentObj.updateAppoinment(
				  paras.get("hid").toString(),
		paras.get("appointmentDate").toString(),
		  paras.get("appointmentVenue").toString(),
		  paras.get("assignDoctor").toString(), paras.get("assignPatient").toString()
		 );
		  
		  response.getWriter().write(output); }
		 

	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {

				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
		}
		return map;
	}

}
