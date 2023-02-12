package controller;

import model.Coche;
import java.util.List;

public interface CocheBBDD {
	
	/**
	 * Agrega un coche a la base de datos
	 * @param c - Coche a agregar
	 * @return true - se ha agregado correctamente<p>false - en caso contrario</p>
	 */
	public boolean añadirCoche(Coche coche);
	
	/**
	 * Elimina un coche de la base de datos a partir de su id
	 * @param id - Id del coche a eliminar
	 * @return true - se ha eliminado correctamente<p>false - en caso contrario</p>
	 */
	public boolean borrarCoche(int id);
	
	/**
	 * Obtiene un coche a partir de su id
	 * @param id - Id del coche a obtener
	 * @return Coche - en caso de encontrarlo<p>Null - en caso contrario</p>
	 */
	public Coche consultarCoche(int id);
	
	/**
	 * Modifica un coche en la base de datos a partir de su id y los datos proporcionados
	 * @param c - Coche con la id del coche deseado y demás datos a modificar
	 * @return true - se ha modificado correctamente<p>false - en caso contrario</p>
	 */
	public boolean modificarCoche(Coche c);
	
	/**
	 * Devuelve una lista de todos los coches en la base de datos
	 * @return Lista de coches
	 */
	public List<Coche> listarCoche();



}
