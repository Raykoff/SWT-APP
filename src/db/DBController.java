package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gui.Usuario;

public class DBController {
	
	private static final String TABLE_NAME = "usuarios";
	private static final String COL_ID = "id";
	private static final String COL_NOMBRE = "nombre";
	private static final String COL_APELLIDO = "apellido";
	private static final String COL_EDAD = "edad";

	public List<Usuario> getUsuarios() {
		List<Usuario> lista = new ArrayList<Usuario>();

		MSQLConnect con = new MSQLConnect();
		String query = "SELECT * FROM " + TABLE_NAME;

		try {
			PreparedStatement statement = con.connect().prepareStatement(query);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				Usuario user = new Usuario(res.getInt(COL_ID),res.getString(COL_NOMBRE), res.getString(COL_APELLIDO), res.getInt(COL_EDAD));
				lista.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.disconnect();
		}
		return lista;
	}
	
	public void addUsuario(Usuario user) {
		
		MSQLConnect con = new MSQLConnect();
		String query = "INSERT INTO `usuarios` (`id`, `nombre`, `apellido`, `edad`) VALUES "
				+ "(NULL, '" + user.getNombre() +"', '" + user.getApellido() + "', '" 
				+  user.getEdad()  +"');";

		try {
			PreparedStatement statement = con.connect().prepareStatement(query);
			boolean res = statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.disconnect();
		}
	}
	
	public void deleteUser(Usuario user) {
		MSQLConnect con = new MSQLConnect();
		String query = "DELETE FROM `usuarios` WHERE `usuarios`.`id` = " + user.getId() + "";

		try {
			PreparedStatement statement = con.connect().prepareStatement(query);
			boolean res = statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.disconnect();
		}
	}

}
