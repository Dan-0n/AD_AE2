package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.PasajeroBBDD;
import model.Pasajero;

public class DaoPasajero implements PasajeroBBDD {
	
	
	private Connection connection;
	
	public boolean openConnection(){
		String url = "jdbc:mysql://localhost:3306/Concesionario_BBDDS";
		String user = "root";
		String password = "";
		try {
			connection = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {			
			return false;
		}
		return true;
	}
	

	public boolean closeConnection(){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean añadirPasajero(Pasajero p) {
		if(!openConnection()){
			return false;
		}
		boolean entry = true;
		
		String query = "insert into PASAJEROS (NOMBRE,EDAD,PESO)"
				+ " values(?,?,?)";
		try {
			
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, p.getNombre());
			ps.setInt(2, p.getEdad());
			ps.setDouble(3, p.getPeso());
			
			
			int affectedRows = ps.executeUpdate();
			if(affectedRows == 0)
				entry = false;
		} catch (SQLException e) {
			entry = false;
			e.printStackTrace();
		} finally{
			closeConnection();
		}
		
		return entry;
	}
	@Override
	public boolean borrarPasajero(int id) {
		if(!openConnection()){
			return false;
		}
		boolean flag = true;
		String query = "DELETE FROM PASAJEROS WHERE ID = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setInt(1, id);
			
			int affectedRows = ps.executeUpdate();
			if(affectedRows == 0)
				flag = false;
		} catch (SQLException e) {
			flag = false;
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return flag; 
	}


	@Override
	public Pasajero consultarPasajero(int id) {
		if(!openConnection()){
			return null;
		}
		Pasajero pasajero = null;
		
		String query = "select ID,NOMBRE,EDAD,PESO from PASAJEROS "
				+ "where ID = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return pasajero;
	}

	@Override
	public List<Pasajero> listarPasajeros() {
		if(!openConnection()){
			return null;
		}
List<Pasajero> lista = new ArrayList<>();
		
		String query = "SELECT ID,NOMBRE,EDAD,PESO FROM PASAJEROS";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Pasajero pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getInt(4));
				
				lista.add(pasajero);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return lista;
	}

	@Override
	public boolean asignarCoche(int idPasajero, int idCoche) {
		if(!openConnection()){
			return false;
		}
		boolean entry = true;
		
		String query = "UPDATE PASAJEROS SET COCHE_ID=?"
				+ " WHERE ID=?";
		try {
			
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, idCoche);
			ps.setInt(2, idPasajero);
			
			
			int affectedRows = ps.executeUpdate();
			if(affectedRows == 0)
				entry = false;
		} catch (SQLException e) {
			entry = false;
			e.printStackTrace();
		} finally{
			closeConnection();
		}
		
		return entry;
	}

	@Override
	public boolean desasignarCoche(int idPasajero) {
		if(!openConnection()){
			return false;
		}

		boolean flag = true;
		String query = "UPDATE PASAJEROS SET COCHE_ID=NULL WHERE ID=?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setInt(1, idPasajero);			
			
			int affectedRows = ps.executeUpdate();
			if(affectedRows == 0)
				flag = false;
		} catch (SQLException e) {
			flag = false;
			e.printStackTrace();
		} finally{
			closeConnection();
		}
		
		return flag;
	}


	@Override
	public List<Pasajero> listarPasajerosCoche(int idCoche) {
		if(!openConnection()){
			return null;
		}		
		List<Pasajero> lista = new ArrayList<>();
		
		String query = "SELECT ID,NOMBRE FROM PASAJEROS WHERE COCHE_ID=?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setInt(1, idCoche);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Pasajero pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				
				
				lista.add(pasajero);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return lista;
	}

	@Override
	public void obtenerCochesYPasajeros() {
		if(!openConnection()){
			return;
		}	
			String query = "Select Coches.ID,coches.MATRICULA, GROUP_CONCAT(DISTINCT pasajeros.id)"
							+ " AS ID_Pasajeros from coches "
							+ "left join pasajeros on coches.id = pasajeros.coche_id "
							+ "WHERE coches.ID=pasajeros.COCHE_ID group by 1;";
			try {
				PreparedStatement ps = connection.prepareStatement(query);
						
						
						
				ResultSet rs = ps.executeQuery();
						
				while(rs.next()){
					System.out.println("ID Coche: " + rs.getInt(1)+" ---" 
				                        +" Matricula: " + rs.getString(2)+" ---" 
										+" ID Pasajero: " + rs.getString(3));
							
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						closeConnection();
					}
					
				}
		
	}


