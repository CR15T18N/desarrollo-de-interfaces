package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import pojo.Temporada;
import util.DatabaseConnection;

public class TemporadaDao implements Dao<Temporada>{

	
	private static Connection connection;
	
	public TemporadaDao() {
		
	}
	
	@Override
	public ArrayList<Temporada> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Temporada buscarPorId(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Temporada t) {
		// TODO Auto-generated method stub
		connection = openConnection();
		
		String query = "insert into temporadas (num_temporada, titulo,"
				+ " serie_id) values(?, ?, ?)";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, temporada.getNum_temporadas());
			ps.setInt(2, serie.getEdad());
			ps.setString(3, serie.getPlataforma());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		closeConnection();
	}

	@Override
	public void modificar(Temporada t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrar(Temporada t) {
		// TODO Auto-generated method stub
		
	}
	
	private static Connection openConnection() {
		DatabaseConnection dbConnection=new DatabaseConnection();
		connection =dbConnection.getConnection();
		return connection;
	}
	
	private static void closeConnection() {
		
		try {
			connection.close();
			connection=null;
		}catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

}
