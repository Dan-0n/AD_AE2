package controller;

import model.Pasajero;
import java.util.List;

public interface PasajeroBBDD {
	

	/**
	 * Añade un pasajero a la base de datos.
	 * @param pasajero - Representa al pasajero a dar de alta
	 * @return true - Se ha añadido correctamente.
	 *         false - En caso contrario.
	 */
	public boolean añadirPasajero(Pasajero pasajero);
	
	/**
	 * Elimina un pasajero de la base de datos mediante su ID.
	 * @param id - Id del pasajero a eliminar
	 * @return true - Se ha eliminado correctamente.
	 *         false - En caso contrario.
	 */
	public boolean borrarPasajero(int id);
	
	/**
	 * Obtiene un pasajero mediante su ID. 
	 * @param id - Id del pasajero a buscar
	 * @return Pasajero - Pasajero encontrado.
	 *         Null - No encontrado.
	 */
	public Pasajero consultarPasajero(int id);
	
	/**
	 * Devuelve una lista con todos los pasajeros existentes en la base de datos.
	 * @return Lista de Pasajeros
	 */
	public List<Pasajero> listarPasajeros();
	
	/**
	 * Asigna un coche a un pasajero.
	 * @param idPasajero - Id del pasajero a asignar al coche
	 * @param idCoche - Id del coche al que asignar al pasajero
	 * @return true - Se ha asignado correctamente.
	 *         false - En caso contrario.
	 */
	public boolean asignarCoche(int idPasajero, int idCoche);
	
	/**
	 * Desasigna un pasajero del coche al que pertenece.
	 * @param idPasajero - Id del pasajero a desasignar del coche
	 * @return true - Se ha desasignado correctamente.
	 *         false - En caso contrario.
	 */
	public boolean desasignarCoche(int idPasajero);
	
	/**
	 * Devuelve una lista con los pasajeros de un coche.
	 * @param idCoche - Id del coche del que desea mostrar sus pasajeros
	 * @return Lista de pasajeros del coche
	 */
	public List<Pasajero> listarPasajerosCoche(int idCoche);
	
	/**
	 * Realiza una consulta a la base de datos que devuelve todos los coches con los 
	 * pasajeros asignados a ellos.
	 */
	public void obtenerCochesYPasajeros();
	
}

