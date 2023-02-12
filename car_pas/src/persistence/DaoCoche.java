package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.CocheBBDD;
import model.Coche;

public class DaoCoche implements CocheBBDD{
	
	private Connection connection;
	
	public boolean openConnection(){
		String url = "jdbc:mysql://localhost:3306/Concesionario_BBDD";
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
	public boolean añadirCoche(Coche c) {
		if(!openConnection()){
			return false;
		}
		boolean entry = true;
		
		String query = "insert into COCHES (MARCA, MODELO, MATRICULA, COLOR)"
				+ " values(?,?,?,?)";
		try {
			
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, c.getMarca());
			ps.setString(2, c.getModelo());
			ps.setString(3, c.getMatricula());
			ps.setString(4, c.getColor());
			
			
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
	public boolean borrarCoche(int id) {
		if(!openConnection()){
			return false;
		}
		
		boolean flag = true;
		String query = "DELETE FROM COCHES WHERE ID = ?";
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
	public Coche consultarCoche(int id) {
		if(!openConnection()){
			return null;
		}		
		Coche coche = null;
		
		String query = "select ID,MARCA,MODELO,MATRICULA,COLOR from COCHES "
				+ "where ID = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				coche = new Coche();
				coche.setId(rs.getInt(1));
				coche.setMarca(rs.getString(2));
				coche.setModelo(rs.getString(3));
				coche.setMatricula(rs.getString(4));
				coche.setColor(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return coche;
	}


	@Override
	public boolean modificarCoche(Coche c) {
		if(!openConnection()){
			return false;
		}
				boolean flag = true;
				String query = "update COCHES set MARCA=?, MODELO=?, MATRICULA=?, COLOR=? "
						+ "WHERE ID=?";
				try {
					PreparedStatement ps = connection.prepareStatement(query);
					ps.setString(1, c.getMarca());
					ps.setString(2, c.getModelo());
					ps.setString(3, c.getMatricula());
					ps.setString(4, c.getColor());
					ps.setInt(5, c.getId());
					
					
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
	public List<Coche> listarCoche() {
		if(!openConnection()){
			return null;
		}
		List<Coche> lista = new ArrayList<>();
				
				String query = "SELECT ID,MARCA,MODELO,MATRICULA,COLOR FROM COCHES";
				try {
					PreparedStatement ps = connection.prepareStatement(query);
					
					ResultSet rs = ps.executeQuery();
					
					while(rs.next()){
						Coche coche = new Coche();
						coche.setId(rs.getInt(1));
						coche.setMarca(rs.getString(2));
						coche.setModelo(rs.getString(3));
						coche.setMatricula(rs.getString(4));
						coche.setColor(rs.getString(5));
						
						
						
						lista.add(coche);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					closeConnection();
				}
				
				return lista;
				
		}
}
