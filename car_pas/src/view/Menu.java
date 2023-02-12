package view;

import java.util.InputMismatchException;
import java.util.Scanner;
import persistence.DaoBBDD;
import persistence.DaoCoche;
import persistence.DaoPasajero;
import controller.CocheBBDD;
import controller.PasajeroBBDD;
import model.Coche;
import model.Pasajero;

public class Menu {
	
	static CocheBBDD cb =  new DaoCoche();
	static PasajeroBBDD pb = new DaoPasajero();

	public static void main(String[] args) {
		
		DaoBBDD dao = new DaoBBDD();
		dao.openConnection();
		
		if (!dao.openConnection()) {
			dao.createTable(null);
			dao.checkTable();
		} else {
			System.out.println("Conectado a la base de datos");
			dao.checkTable();
		}
	}
	
	public static void menuCoche() {

		Scanner scanner = new Scanner(System.in);
	    boolean exit = false;
        
	    while (!exit) {
            System.out.println("GESTIÓN DE COCHES");
	        System.out.println("1. Añadir nuevo coche");
	        System.out.println("2. Borrar coche por ID");
	        System.out.println("3. Consultar coche por ID");
	        System.out.println("4. Modificar coche por ID");
	        System.out.println("5. Listado de coches");
	        System.out.println("6. Gestionar pasajeros");
	        System.out.println("7. Terminar el programa");
	        System.out.print("Seleccione una opción: ");
	            
	        try {
	        	Coche coche;
	        	int option = Integer.parseInt(scanner.nextLine());
	        	
	            switch(option) {
	                case 1: 
	                	coche = new Coche();
	                    System.out.println("Introduzca la marca del coche:");
	                    coche.setMarca(scanner.nextLine());
	                    System.out.println("Introduzca el modelo del coche:");
	                    coche.setModelo(scanner.nextLine());
	                    System.out.println("Introduzca la matrícula del coche:");
	                    coche.setMatricula(scanner.nextLine());
	                    System.out.println("Introduzca el color del coche:");
	                    coche.setColor(scanner.nextLine());
	                    cb.añadirCoche(coche);
	                    break;
	                    
	                case 2:
	                    System.out.println("Introduzca el ID del coche que desea borrar:");
	                    int idBorrar = Integer.parseInt(scanner.nextLine());
	                    cb.borrarCoche(idBorrar);
	                    break;
	                        
	                case 3:
	                	coche = new Coche();
	                    System.out.println("Introduzca el ID del coche que desea buscar:");
	                    int idConsultar = Integer.parseInt(scanner.nextLine());
	                    cb.consultarCoche(idConsultar);
	                    if (coche == null) {
	                        System.out.println("No se encontró un coche con ese ID.");
	                    } else {
	                        System.out.println(coche.toString());
	                    }
	                    break;
	                    
	                    
	                case 4: 
	                	coche = new Coche();
	                    System.out.println("Introduzca el ID del coche que desea modificar:");
	                    int idModificar = Integer.parseInt(scanner.nextLine());
	                    cb.modificarCoche(coche);
	                    if (coche == null) {
	                        System.out.println("No se encontró un coche con ese ID.");
	                    } else {
	                        System.out.println(coche.toString());
	                    }
	                    break;
	                        
	                case 5: 
	                    System.out.println("Listado de coches:");
	                    cb.listarCoche();
	                    break;
	                        
	                case 6: 
	                	menuPasajero();
	                	break;
              
	                case 7:
	                	System.out.println("Saliendo de la aplicación...");
	                	exit = true;
	                	
	                default:
	                    System.out.println("Introduzca un número del menú. Las opciones son entre 1 y 6.");
	                    break;
	            }
	        } catch(InputMismatchException e) {
	            System.out.println("Debes introducir un número entre 1 y 6.");
	            scanner.next();
	        } catch(NumberFormatException e) {
	        System.out.println("Debes introducir un número entero válido.");
	        }
	    }
	    
	}
	
	public static void menuPasajero() {

	    Scanner scanner = new Scanner(System.in);
	    boolean subExit = false;

	    while (!subExit) {
	        System.out.println("GESTIÓN DE PASAJEROS");
	        System.out.println("1. Añadir nuevo pasajero");
	        System.out.println("2. Borrar pasajero por ID");
	        System.out.println("3. Consulta pasajero por ID");
	        System.out.println("4. Listar todos los pasajeros");
	        System.out.println("5. Añadir pasajero a coche");
	        System.out.println("6. Eliminar pasajero de un coche");
	        System.out.println("7. Listar todos los pasajeros de un coche");
	        System.out.println("8. Volver al menú principal");
	        System.out.print("Seleccione una opción: ");

	        try {
	            Pasajero pasajero;
	            int subOption = Integer.parseInt(scanner.nextLine());

	            switch (subOption) {
	                case 1:
	                    pasajero = new Pasajero();
	                    System.out.println("Introduzca el ID del pasajero:");
	                    int id = Integer.parseInt(scanner.nextLine());
	                    pasajero.setId(id);
	                    System.out.println("Introduzca el nombre del pasajero:");
	                    String nombre = scanner.nextLine();
	                    pasajero.setNombre(nombre);
	                    System.out.println("Introduzca la edad del pasajero:");
	                    int edad = Integer.parseInt(scanner.nextLine());
	                    pasajero.setEdad(edad);
	                    System.out.println("Introduzca el peso del pasajero:");
	                    int peso = Integer.parseInt(scanner.nextLine());
	                    pasajero.setPeso(peso);
	                    pb.añadirPasajero(pasajero);
	                    break;

	                case 2:
	                    System.out.println("Introduzca el ID del pasajero que desea borrar:");
	                    System.out.println(pb.borrarPasajero(Integer.parseInt(scanner.nextLine())));
	                    break;

	                case 3:
	                    System.out.println("Introduzca el ID del pasajero que desea buscar:");
	                    System.out.println(pb.consultarPasajero(Integer.parseInt(scanner.nextLine())));
	                    break;

	                case 4:
	                    System.out.println("Listado de pasajeros:");
	                    pb.listarPasajeros();
	                    break;
                                        
	                case 5:
	                    System.out.println("Introduzca el ID del pasajero:");
	                    int idPasajero = Integer.parseInt(scanner.nextLine());
	                    System.out.println("Introduzca el ID del coche:");
	                    int idCoche = Integer.parseInt(scanner.nextLine());
	                    pb.asignarCoche(idPasajero, idCoche);
	                    break;

	                case 6:
	                    System.out.println("Lista de coches y pasajeros asociados:");
	                    pb.obtenerCochesYPasajeros();
	                    System.out.println("Introduzca el ID del pasajero a desasignar:");
	                    idPasajero = Integer.parseInt(scanner.nextLine());
	                    pb.desasignarCoche(idPasajero);
	                    break;

	                case 7:
	                    System.out.println("Introduzca el ID del coche:");
	                    idCoche = Integer.parseInt(scanner.nextLine());
	                    System.out.println(pb.listarPasajerosCoche(idCoche));
	                    break;

	                case 8:
	                    menuCoche();
	                    break;

	                default:
	                    System.out.println("Opción no válida.");
	                    break;
	                }
	            }catch(InputMismatchException e) {
	                System.out.println("Debes introducir un número entre 1 y 8.");
	                scanner.next();
	            } catch(NumberFormatException e) {
	                System.out.println("Debes introducir un número entero válido.");
	            }
	          }
	       }
	
	

}

