package db;

import java.sql.*;
import java.util.Properties;

public class MSQLConnect {

	private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/java_bd";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final String MAX_POOL = "250"; // set your own limit

	private Connection connection;
	private Properties properties;

	// create properties
	private Properties getProperties() {
		if (properties == null) {
			properties = new Properties();
			properties.setProperty("user", USERNAME);
			properties.setProperty("password", PASSWORD);
			properties.setProperty("MaxPooledStatements", MAX_POOL);
		}
		return properties;
	}

	// connect database
	public Connection connect() {
		if (connection == null) {
			try {
				Class.forName(DATABASE_DRIVER);
				connection = DriverManager.getConnection(DATABASE_URL, getProperties());
			} catch (ClassNotFoundException | SQLException e) {
				// Java 7+
				e.printStackTrace();
			}
		}
		return connection;
	}
	
	// disconnect database
	public void disconnect() {
	    if (connection != null) {
	        try {
	            connection.close();
	            connection = null;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	// public void getData() {
	// try {
	// String query = "select * from usuarios";
	// rs = st.executeQuery(query);
	// System.out.println("Resultados");
	// while (rs.next()) {
	// String id = rs.getString("id");
	// String name = rs.getString("nombre");
	// String apellido = rs.getString("apellido");
	// System.out.println(id + " " + name + " " + apellido);
	// }
	//
	// } catch (Exception e) {
	// System.out.println(e);
	// }
	// }
}