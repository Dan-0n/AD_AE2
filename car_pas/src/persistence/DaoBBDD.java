package persistence;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DaoBBDD {
	
	private Connection connection;
	
	public boolean createBBDD(){
		String url = "jdbc:mysql://localhost:3306/";
		String user = "root";
		String password = "";
		try {
			String sql = "CREATE DATABASE Concesionario_BBDD";
			connection = DriverManager.getConnection(url,user,password);
			Statement st = connection.createStatement();
			st.executeUpdate(sql);
			closeConnection();	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
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


	public void createTable(String table) {
		
		String pasajero = "CREATE TABLE PASAJEROS( "
				+ "ID INTEGER PRIMARY KEY AUTO_INCREMENT, "
				+ "NOMBRE VARCHAR(20) NOT NULL, "
				+ "EDAD INTEGER NOT NULL, "
				+ "PESO INTEGER NOT NULL, "
				+ "COCHE_ID INTEGER, "
				+ "FOREIGN KEY (COCHE_ID) REFERENCES COCHES(ID))";
		String coche = "CREATE TABLE COCHES( "
				+ "ID INTEGER PRIMARY KEY AUTO_INCREMENT, "
				+ "MARCA VARCHAR(20) NOT NULL), "
                + "MODELO VARCHAR(20) NOT NULL), "
                + "MATRICULA VARCHAR(7) NOT NULL, "
                + "COLOR VARCHAR(7) NOT NULL)";

		try{
			
			openConnection();
			Statement st = connection.createStatement();
			if(table.equals("PASAJEROS")) {
				st.executeUpdate(pasajero);
			}else if(table.equals("COCHES")) {
				st.executeUpdate(coche);
			}
			
		}catch (SQLException e) {
			System.out.println("checkTables()" + e.getMessage());
		}
		closeConnection();
		
	}
	
	public void checkTable() {
		
		try {
			openConnection();
			String[] tables = {"COCHES","PASAJEROS"};
			DatabaseMetaData metaData = connection.getMetaData();
			for (int i=0; i<tables.length; i++) {
				ResultSet rs = metaData.getTables(null,null,tables[i],null);
				if(!rs.next()) {
					createTable(tables[i]);
				}
			}
			
		}catch (SQLException e) {
			System.out.println("checkTables() =" + e.getMessage());
		}
		closeConnection();
	}
	
}
	
	