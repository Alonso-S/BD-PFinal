package controlador;

import dao.*;
import modelo.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuFuncionalidades {
	Scanner s = new Scanner(System.in);
	
    
    
    public void menuUsuario() {
        while (true) {
            System.out.println("---- Menú de Usuario ----");
            System.out.println("1. Gestionar Equipos");
            System.out.println("2. Gestionar Jugadores");
            System.out.println("3. Gestionar Resultados de Fase 1");
            System.out.println("4. Gestionar Resultados de Fase 2");
            System.out.println("5. Gestionar Resultados de Fase 3");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = s.nextInt();
            s.nextLine(); 

            switch (opcion) {
	            case 1:
	                gestionarEquipos();
	                break;
	            case 2:
	            	gestionarJugadores();
	                break;
	            case 3:
	            	gestionarResultadosFase1();
	                break;
	            case 4:
	            	gestionarResultadosFase2();
                break;
                case 5:
                	gestionarResultadosFase3();
                break;
                case 6:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }
    
    
    private void gestionarEquipos() {

        while (true) {
            System.out.println("---- Gestionar Equipos ----");
            System.out.println("1. Ver todos los equipos");
            System.out.println("2. Insertar nuevo equipo");
            System.out.println("3. Actualizar equipo");
            System.out.println("4. Eliminar equipo");
            System.out.println("5. Buscar equipo por código");
            System.out.println("6. Volver al Menú de Usuario");
            System.out.print("Seleccione una opción: ");

            int opcion = s.nextInt();
            s.nextLine();  

            switch (opcion) {
                case 1:
                    verTodosEquipos();
                    break;
                case 2:
                    insertarNuevoEquipo();
                    break;
                case 3:
                    actualizarEquipo();
                    break;
                case 4:
                    eliminarEquipo();
                    break;
                case 5:
                    buscarEquipoPorCodigo();
                    break;
                case 6:
                    System.out.println("Volviendo al Menú de Usuario.");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }

    private void verTodosEquipos() {
        EquipoDao equipoDao = new EquipoDao();
        List<Equipo> equipos = equipoDao.obtenerTodosEquipos();

        System.out.println("---- Equipos ----");
        for (Equipo equipo : equipos) {
            System.out.println("Código: " + equipo.getCodigo());
            System.out.println("Nombre: " + equipo.getNombreEquipo());
            System.out.println("Región: " + equipo.getRegion());
            System.out.println("Locación: " + equipo.getLocacion());
            System.out.println("---------------------");
        }
    }
    

    private void insertarNuevoEquipo() {
        

        System.out.println("---- Insertar Nuevo Equipo ----");
        System.out.print("Nombre del equipo: ");
        String nombreEquipo = s.nextLine();

        System.out.print("Región: ");
        String region = s.nextLine();

        System.out.print("Locación: ");
        String locacion = s.nextLine();

        Equipo nuevoEquipo = new Equipo();
        nuevoEquipo.setNombreEquipo(nombreEquipo);
        nuevoEquipo.setRegion(region);
        nuevoEquipo.setLocacion(locacion);

        EquipoDao equipoDao = new EquipoDao();
        if (equipoDao.insertarEquipo(nuevoEquipo)) {
            System.out.println("Equipo insertado con éxito");
        } else {
        	System.out.println("Error al insertar el equipo. Inténtelo de nuevo.");
        	}
        }
    	private void actualizarEquipo() {
    		System.out.println("---- Actualizar Equipo ----");
    		System.out.print("Ingrese el código del equipo a actualizar: ");
    		int codigoEquipo = s.nextInt();
    		s.nextLine();  

    		EquipoDao equipoDao = new EquipoDao();
    		Equipo equipoExistente = equipoDao.buscarEquipoPorCodigo(codigoEquipo);

    		if (equipoExistente != null) {
    		    System.out.println("Equipo encontrado:");
    		    System.out.println("Nombre: " + equipoExistente.getNombreEquipo());
    		    System.out.println("Región: " + equipoExistente.getRegion());
    		    System.out.println("Locación: " + equipoExistente.getLocacion());

    		    System.out.print("Nuevo nombre del equipo (dejar en blanco si no se desea cambiar): ");
    		    String nuevoNombre = s.nextLine();

    		    System.out.print("Nueva región (dejar en blanco si no se desea cambiar): ");
    		    String nuevaRegion = s.nextLine();

    		    System.out.print("Nueva locación (dejar en blanco si no se desea cambiar): ");
    		    String nuevaLocacion = s.nextLine();

    		    if (!nuevoNombre.isEmpty()) {
    		        equipoExistente.setNombreEquipo(nuevoNombre);
    		    }
    		    if (!nuevaRegion.isEmpty()) {
    		        equipoExistente.setRegion(nuevaRegion);
    		    }
    		    if (!nuevaLocacion.isEmpty()) {
    		        equipoExistente.setLocacion(nuevaLocacion);
    		    }

    		    if (equipoDao.actualizarEquipo(equipoExistente)) {
    		        System.out.println("Equipo actualizado con éxito.");
    		    } else {
    		        System.out.println("Error al actualizar el equipo. Inténtelo de nuevo.");
    		    }
    		} else {
    		    System.out.println("Equipo no encontrado.");
    		}
    	}

    private void eliminarEquipo() {
    	System.out.println("---- Eliminar Equipo ----");
    	System.out.print("Ingrese el código del equipo a eliminar: ");
    	int codigoEquipo = s.nextInt();
    	s.nextLine(); 

    	EquipoDao equipoDao = new EquipoDao();
    	Equipo equipoExistente = equipoDao.buscarEquipoPorCodigo(codigoEquipo);

    	if (equipoExistente != null) {
    	    System.out.println("Equipo encontrado:");
    	    System.out.println("Nombre: " + equipoExistente.getNombreEquipo());
    	    System.out.println("Región: " + equipoExistente.getRegion());
    	    System.out.println("Locación: " + equipoExistente.getLocacion());

    	    System.out.print("¿Está seguro de que desea eliminar este equipo? (S/N): ");
    	    String confirmacion = s.nextLine().toUpperCase();

    	    if (confirmacion.equals("S")) {
    	        if (equipoDao.eliminarEquipo(codigoEquipo)) {
    	            System.out.println("Equipo eliminado con éxito.");
    	        } else {
    	            System.out.println("Error al eliminar el equipo. Inténtelo de nuevo.");
    	        }
    	    } else {
    	        System.out.println("Eliminación cancelada.");
    	    }
    	} else {
    	    System.out.println("Equipo no encontrado.");
    	    }
    	}
    private void buscarEquipoPorCodigo() {
    	System.out.println("---- Buscar Equipo por Código ----");
    	System.out.print("Ingrese el código del equipo: ");
    	int codigoEquipo = s.nextInt();
    	s.nextLine(); 

    	EquipoDao equipoDao = new EquipoDao();
    	Equipo equipoEncontrado = equipoDao.buscarEquipoPorCodigo(codigoEquipo);

    	if (equipoEncontrado != null) {
    	    System.out.println("Equipo encontrado:");
    	    System.out.println("Nombre: " + equipoEncontrado.getNombreEquipo());
    	    System.out.println("Región: " + equipoEncontrado.getRegion());
    	    System.out.println("Locación: " + equipoEncontrado.getLocacion());
    	} else {
    	    System.out.println("Equipo no encontrado.");
    	}
    }
    
    
    
    
    
    
    private void gestionarResultadosFase1() {
     
        

        while (true) {
            System.out.println("---- Gestionar Resultados de Fase 1 ----");
            System.out.println("1. Ver todos los resultados de Fase 1");
            System.out.println("2. Insertar nuevo resultado de Fase 1");
            System.out.println("3. Actualizar resultado de Fase 1");
            System.out.println("4. Eliminar resultado de Fase 1");
            System.out.println("5. Buscar resultado de Fase 1 por código");
            System.out.println("6. Volver al Menú de Usuario");
            System.out.print("Seleccione una opción: ");

            int opcion = s.nextInt();
            s.nextLine(); 

            switch (opcion) {
                case 1:
                    verTodosResultadosFase1();
                    break;
                case 2:
                    insertarNuevoResultadoFase1();
                    break;
                case 3:
                    actualizarResultadoFase1();
                    break;
                case 4:
                    eliminarResultadoFase1();
                    break;
                case 5:
                    buscarResultadoFase1PorCodigo();
                    break;
                case 6:
                    System.out.println("Volviendo al Menú de Usuario.");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }

    private void verTodosResultadosFase1() {
        Fase1Dao fase1Dao = new Fase1Dao();
        List<Fase1> resultadosFase1 = fase1Dao.obtenerTodosResultadosFase1();

        System.out.println("---- Resultados de Fase 1 ----");
        for (Fase1 resultadoFase1 : resultadosFase1) {
            System.out.println("Código: " + resultadoFase1.getCodigo());
            System.out.println("Código del Equipo: " + resultadoFase1.getCodigoEquipo());
            System.out.println("Puntaje: " + resultadoFase1.getPuntaje());
            System.out.println("Estado: " +resultadoFase1.getEstado());
            System.out.println("Grupo: " + resultadoFase1.getGrupo());
            System.out.println("---------------------");
        }
    }

    private void insertarNuevoResultadoFase1() {
        

        System.out.println("---- Insertar Nuevo Resultado de Fase 1 ----");
        System.out.print("Código del Equipo: ");
        int codigoEquipo = s.nextInt();
        s.nextLine();  

        System.out.print("Puntaje: ");
        String puntaje = s.nextLine();

        System.out.print("Estado: ");
        String estado = s.nextLine();

        System.out.print("Grupo: ");
        String grupo = s.nextLine();

        Fase1 nuevoResultadoFase1 = new Fase1();
        nuevoResultadoFase1.setCodigoEquipo(codigoEquipo);
        nuevoResultadoFase1.setPuntaje(puntaje);
        nuevoResultadoFase1.setEstado(estado);
        nuevoResultadoFase1.setGrupo(grupo);

        Fase1Dao fase1Dao = new Fase1Dao();
        if (fase1Dao.insertarResultadoFase1(nuevoResultadoFase1)) {
            System.out.println("Resultado de Fase 1 insertado con éxito.");
        } else {
            System.out.println("Error al insertar el resultado de Fase 1. Inténtelo de nuevo.");
        }
    }

    private void actualizarResultadoFase1() {
       

        System.out.println("---- Actualizar Resultado de Fase 1 ----");
        System.out.print("Ingrese el código del resultado de Fase 1 a actualizar: ");
        int codigoResultadoFase1 = s.nextInt();
        s.nextLine();
        Fase1Dao fase1Dao = new Fase1Dao();
        Fase1 resultadoFase1Existente = fase1Dao.buscarResultadoFase1PorCodigo(codigoResultadoFase1);

        if (resultadoFase1Existente != null) {
            System.out.println("Resultado de Fase 1 encontrado:");
            System.out.println("Código del Equipo: " + resultadoFase1Existente.getCodigoEquipo());
            System.out.println("Puntaje: " + resultadoFase1Existente.getPuntaje());
            System.out.println("Estado: " + resultadoFase1Existente.getEstado());
            System.out.println("Grupo: " + resultadoFase1Existente.getGrupo());

            System.out.print("Nuevo código del equipo (dejar en blanco si no se desea cambiar): ");
            String nuevoCodigoEquipo = s.nextLine();

            System.out.print("Nuevo puntaje (dejar en blanco si no se desea cambiar): ");
            String nuevoPuntaje = s.nextLine();

            System.out.print("Nuevo estado (dejar en blanco si no se desea cambiar): ");
            String nuevoEstado = s.nextLine();

            System.out.print("Nuevo grupo (dejar en blanco si no se desea cambiar): ");
            String nuevoGrupo = s.nextLine();

            if (!nuevoCodigoEquipo.isEmpty()) {
                resultadoFase1Existente.setCodigoEquipo(Integer.parseInt(nuevoCodigoEquipo));
            }
            if (!nuevoPuntaje.isEmpty()) {
                resultadoFase1Existente.setPuntaje(nuevoPuntaje);
            }
            if (!nuevoEstado.isEmpty()) {
                resultadoFase1Existente.setEstado(nuevoEstado);
            }
            if (!nuevoGrupo.isEmpty()) {
                resultadoFase1Existente.setGrupo(nuevoGrupo);
            }

            if (fase1Dao.actualizarResultadoFase1(resultadoFase1Existente)) {
                System.out.println("Resultado de Fase 1 actualizado con éxito.");
            } else {
                System.out.println("Error al actualizar el resultado de Fase 1. Inténtelo de nuevo.");
            }
        } else {
            System.out.println("Resultado de Fase 1 no encontrado.");
        }
    }
    
    private void eliminarResultadoFase1() {
        

        System.out.println("---- Eliminar Resultado de Fase 1 ----");
        System.out.print("Ingrese el código del resultado de Fase 1 a eliminar: ");
        int codigoResultadoFase1 = s.nextInt();
        s.nextLine(); 

        Fase1Dao fase1Dao = new Fase1Dao();
        Fase1 resultadoFase1Existente = fase1Dao.buscarResultadoFase1PorCodigo(codigoResultadoFase1);

        if (resultadoFase1Existente != null) {
            System.out.println("Resultado de Fase 1 encontrado:");
            System.out.println("Código del Equipo: " + resultadoFase1Existente.getCodigoEquipo());
            System.out.println("Puntaje: " + resultadoFase1Existente.getPuntaje());
            System.out.println("Estado: " + resultadoFase1Existente.getEstado());
            System.out.println("Grupo: " + resultadoFase1Existente.getGrupo());

            System.out.print("¿Está seguro de que desea eliminar este resultado de Fase 1? (S/N): ");
            String confirmacion = s.nextLine().toUpperCase();

            if (confirmacion.equals("S")) {
                if (fase1Dao.eliminarResultadoFase1(codigoResultadoFase1)) {
                    System.out.println("Resultado de Fase 1 eliminado con éxito.");
                } else {
                    System.out.println("Error al eliminar el resultado de Fase 1. Inténtelo de nuevo.");
                }
            } else {
                System.out.println("Eliminación cancelada.");
            }
        } else {
            System.out.println("Resultado de Fase 1 no encontrado.");
        }
    }

    private void buscarResultadoFase1PorCodigo() {
        

        System.out.println("---- Buscar Resultado de Fase 1 por Código ----");
        System.out.print("Ingrese el código del resultado de Fase 1: ");
        int codigoResultadoFase1 = s.nextInt();
        s.nextLine();  

        Fase1Dao fase1Dao = new Fase1Dao();
        Fase1 resultadoFase1Encontrado = fase1Dao.buscarResultadoFase1PorCodigo(codigoResultadoFase1);

        if (resultadoFase1Encontrado != null) {
            System.out.println("Resultado de Fase 1 encontrado:");
            System.out.println("Código del Equipo: " + resultadoFase1Encontrado.getCodigoEquipo());
            System.out.println("Puntaje: " + resultadoFase1Encontrado.getPuntaje());
            System.out.println("Estado: " + resultadoFase1Encontrado.getEstado());
            System.out.println("Grupo: " + resultadoFase1Encontrado.getGrupo());
        } else {
            System.out.println("Resultado de Fase 1 no encontrado.");
        }
    }
    
    
    
    
    
    private void gestionarResultadosFase2() {

        while (true) {
            System.out.println("---- Gestionar Resultados de Fase 2 ----");
            System.out.println("1. Ver todos los resultados de Fase 2");
            System.out.println("2. Insertar nuevo resultado de Fase 2");
            System.out.println("3. Actualizar resultado de Fase 2");
            System.out.println("4. Eliminar resultado de Fase 2");
            System.out.println("5. Buscar resultado de Fase 2 por código");
            System.out.println("6. Volver al Menú de Usuario");
            System.out.print("Seleccione una opción: ");

            int opcion = s.nextInt();
            s.nextLine();  // Consumir el salto de línea pendiente

            switch (opcion) {
                case 1:
                    verTodosResultadosFase2();
                    break;
                case 2:
                    insertarNuevoResultadoFase2();
                    break;
                case 3:
                    actualizarResultadoFase2();
                    break;
                case 4:
                    eliminarResultadoFase2();
                    break;
                case 5:
                    buscarResultadoFase2PorCodigo();
                    break;
                case 6:
                    System.out.println("Volviendo al Menú de Usuario.");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }

    private void verTodosResultadosFase2() {
        Fase2Dao fase2Dao = new Fase2Dao();
        List<Fase2> resultadosFase2 = fase2Dao.obtenerTodosResultadosFase2();

        System.out.println("---- Resultados de Fase 2 ----");
        for (Fase2 resultadoFase2 : resultadosFase2) {
            System.out.println("Código: " + resultadoFase2.getCodigo());
            System.out.println("Equipo 1: " + resultadoFase2.getEquipo1());
            System.out.println("Equipo 2: " + resultadoFase2.getEquipo2());
            System.out.println("Resultado Equipo 1: " + resultadoFase2.getResultadoEquipo1());
            System.out.println("Resultado Equipo 2: " + resultadoFase2.getResultadoEquipo2());
            System.out.println("---------------------");
        }
    }

    private void insertarNuevoResultadoFase2() {
        

        System.out.println("---- Insertar Nuevo Resultado de Fase 2 ----");
        System.out.print("Equipo 1: ");
        String equipo1 = s.nextLine();

        System.out.print("Equipo 2: ");
        String equipo2 = s.nextLine();

        System.out.print("Resultado Equipo 1: ");
        int resEquipo1 = s.nextInt();
        s.nextLine();  

        System.out.print("Resultado Equipo 2: ");
        int resEquipo2 = s.nextInt();
        s.nextLine();  

        Fase2 nuevoResultadoFase2 = new Fase2();
        nuevoResultadoFase2.setEquipo1(equipo1);
        nuevoResultadoFase2.setEquipo2(equipo2);
        nuevoResultadoFase2.setResultadoEquipo1(resEquipo1);
        nuevoResultadoFase2.setResultadoEquipo2(resEquipo2);

        Fase2Dao fase2Dao = new Fase2Dao();
        if (fase2Dao.insertarResultadoFase2(nuevoResultadoFase2)) {
            System.out.println("Resultado de Fase 2 insertado con éxito.");
        } else {
            System.out.println("Error al insertar el resultado de Fase 2. Inténtelo de nuevo.");
        }
    }

    private void actualizarResultadoFase2() {
        

        System.out.println("---- Actualizar Resultado de Fase 2 ----");
        System.out.print("Ingrese el código del resultado de Fase 2 a actualizar: ");
        int codigoResultadoFase2 = s.nextInt();
        s.nextLine();  

        Fase2Dao fase2Dao = new Fase2Dao();
        Fase2 resultadoFase2Existente = fase2Dao.buscarResultadoFase2PorCodigo(codigoResultadoFase2);

        if (resultadoFase2Existente != null) {
            System.out.println("Resultado de Fase 2 encontrado:");
            System.out.println("Equipo 1: " + resultadoFase2Existente.getEquipo1());
            System.out.println("Equipo 2: " + resultadoFase2Existente.getEquipo2());
            System.out.println("Resultado Equipo 1: " + resultadoFase2Existente.getResultadoEquipo1());
            System.out.println("Resultado Equipo 2: " + resultadoFase2Existente.getResultadoEquipo2());

            System.out.print("Nuevo equipo 1 (dejar en blanco si no se desea cambiar): ");
            String nuevoEquipo1 = s.nextLine();

            System.out.print("Nuevo equipo 2 (dejar en blanco si no se desea cambiar): ");
            String nuevoEquipo2 = s.nextLine();

            System.out.print("Nuevo resultado equipo 1 (dejar en blanco si no se desea cambiar): ");
            String nuevoResEquipo1 = s.nextLine();

            System.out.print("Nuevo resultado equipo 2 (dejar en blanco si no se desea cambiar): ");
            String nuevoResEquipo2 = s.nextLine();

            if (!nuevoEquipo1.isEmpty()) {
                resultadoFase2Existente.setEquipo1(nuevoEquipo1);
            }
            if (!nuevoEquipo2.isEmpty()) {
                resultadoFase2Existente.setEquipo2(nuevoEquipo2);
            }
            if (!nuevoResEquipo1.isEmpty()) {
                resultadoFase2Existente.setResultadoEquipo1(Integer.parseInt(nuevoResEquipo1));
            }
            if (!nuevoResEquipo2.isEmpty()) {
                resultadoFase2Existente.setResultadoEquipo2(Integer.parseInt(nuevoResEquipo2));
            }

            if (fase2Dao.actualizarResultadoFase2(resultadoFase2Existente)) {
                System.out.println("Resultado de Fase 2 actualizado con éxito.");
            } else {
                System.out.println("Error al actualizar el resultado de Fase 2. Inténtelo de nuevo.");
            }
        } else {
            System.out.println("Resultado de Fase 2 no encontrado.");
        }
    }

    private void eliminarResultadoFase2() {


        System.out.println("---- Eliminar Resultado de Fase 2 ----");
        System.out.print("Ingrese el código del resultado de Fase 2 a eliminar: ");
        int codigoResultadoFase2 = s.nextInt();
        s.nextLine();  

        Fase2Dao fase2Dao = new Fase2Dao();
        Fase2 resultadoFase2Existente = fase2Dao.buscarResultadoFase2PorCodigo(codigoResultadoFase2);

        if (resultadoFase2Existente != null) {
            System.out.println("Resultado de Fase 2 encontrado:");
            System.out.println("Equipo 1: " + resultadoFase2Existente.getEquipo1());
            System.out.println("Equipo 2: " + resultadoFase2Existente.getEquipo2());
            System.out.println("Resultado Equipo 1: " + resultadoFase2Existente.getResultadoEquipo1());
            System.out.println("Resultado Equipo 2: " + resultadoFase2Existente.getResultadoEquipo2());

            System.out.print("¿Está seguro de que desea eliminar este resultado de Fase 2? (S/N): ");
            String confirmacion = s.nextLine().toUpperCase();

            if (confirmacion.equals("S")) {
                if (fase2Dao.eliminarResultadoFase2(codigoResultadoFase2)) {
                    System.out.println("Resultado de Fase 2 eliminado con éxito.");
                } else {
                    System.out.println("Error al eliminar el resultado de Fase 2. Inténtelo de nuevo.");
                }
            } else {
                System.out.println("Eliminación cancelada.");
            }
        } else {
            System.out.println("Resultado de Fase 2 no encontrado.");
        }
    }

    private void buscarResultadoFase2PorCodigo() {
       
        System.out.println("---- Buscar Resultado de Fase 2 por Código ----");
        System.out.print("Ingrese el código del resultado de Fase 2: ");
        int codigoResultadoFase2 = s.nextInt();
        s.nextLine(); 

        Fase2Dao fase2Dao = new Fase2Dao();
        Fase2 resultadoFase2Encontrado = fase2Dao.buscarResultadoFase2PorCodigo(codigoResultadoFase2);

        if (resultadoFase2Encontrado != null) {
            System.out.println("Resultado de Fase 2 encontrado:");
            System.out.println("Código: " + resultadoFase2Encontrado.getCodigo());
            System.out.println("Equipo 1: " + resultadoFase2Encontrado.getEquipo1());
            System.out.println("Equipo 2: " + resultadoFase2Encontrado.getEquipo2());
            System.out.println("Resultado Equipo 1: " + resultadoFase2Encontrado.getResultadoEquipo1());
            System.out.println("Resultado Equipo 2: " + resultadoFase2Encontrado.getResultadoEquipo2());
        } else {
            System.out.println("Resultado de Fase 2 no encontrado.");
        }
    }
    
    
    
    
    
    
    


    private void gestionarJugadores() {
   

        while (true) {
            System.out.println("---- Gestionar Jugadores ----");
            System.out.println("1. Ver todos los jugadores");
            System.out.println("2. Insertar nuevo jugador");
            System.out.println("3. Actualizar jugador");
            System.out.println("4. Eliminar jugador");
            System.out.println("5. Buscar jugador por código");
            System.out.println("6. Volver al Menú de Usuario");
            System.out.print("Seleccione una opción: ");

            int opcion = s.nextInt();
            s.nextLine();  
            switch (opcion) {
                case 1:
                    verTodosJugadores();
                    break;
                case 2:
                    insertarNuevoJugador();
                    break;
                case 3:
                    actualizarJugador();
                    break;
                case 4:
                    eliminarJugador();
                    break;
                case 5:
                    buscarJugadorPorCodigo();
                    break;
                case 6:
                    System.out.println("Volviendo al Menú de Usuario.");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }

    private void verTodosJugadores() {
        JugadorDao jugadorDao = new JugadorDao();
        List<Jugador> jugadores = jugadorDao.obtenerTodosJugadores();

        System.out.println("---- Jugadores ----");
        for (Jugador jugador : jugadores) {
            System.out.println("Código: " + jugador.getCodigo());
            System.out.println("Nick: " + jugador.getNick());
            System.out.println("Nombre: " + jugador.getNombre());
            System.out.println("Posición: " + jugador.getPosicion());
            System.out.println("Fecha Ingreso Equipo: " + jugador.getFechaIngresoEquipo());
            System.out.println("Código del Equipo: " + jugador.getCodigoEquipo());
            System.out.println("---------------------");
        }
    }

    private void insertarNuevoJugador() {
    	JugadorDao jugadorDao = new JugadorDao();

        System.out.println("---- Insertar Nuevo Jugador ----");
        System.out.print("Nick: ");
        String nick = s.nextLine();

        System.out.print("Nombre: ");
        String nombre = s.nextLine();

        System.out.print("Posición: ");
        String posicion = s.nextLine();

        System.out.print("Año de Ingreso Equipo: ");
        int añoIngresoEquipo = s.nextInt();
        s.nextLine();  // Consumir el salto de línea pendiente

        System.out.print("Mes de Ingreso Equipo: ");
        int mesIngresoEquipo = s.nextInt();
        s.nextLine();

        System.out.print("Día de Ingreso Equipo: ");
        int diaIngresoEquipo = s.nextInt();
        s.nextLine();

        LocalDate fechaIngresoEquipo = LocalDate.of(añoIngresoEquipo, mesIngresoEquipo, diaIngresoEquipo);

        System.out.print("Código del Equipo: ");
        int codigoEquipo = s.nextInt();
        s.nextLine();  

        Jugador nuevoJugador = new Jugador();
        nuevoJugador.setNick(nick);
        nuevoJugador.setNombre(nombre);
        nuevoJugador.setPosicion(posicion);
        nuevoJugador.setFechaIngresoEquipo(fechaIngresoEquipo);
        nuevoJugador.setCodigoEquipo(codigoEquipo);

        if (jugadorDao.insertarJugador(nuevoJugador)) {
            System.out.println("Jugador insertado con éxito.");
        } else {
            System.out.println("Error al insertar el jugador. Inténtelo de nuevo.");
        }
    }

    private void actualizarJugador() {
        JugadorDao jugadorDao = new JugadorDao();

        System.out.println("---- Actualizar Jugador ----");
        System.out.print("Ingrese el código del jugador a actualizar: ");
        int codigoJugador = s.nextInt();
        s.nextLine();

        Jugador jugadorExistente = jugadorDao.buscarJugadorPorCodigo(codigoJugador);

        if (jugadorExistente != null) {
            System.out.println("Jugador encontrado:");
            System.out.println("Nick: " + jugadorExistente.getNick());
            System.out.println("Nombre: " + jugadorExistente.getNombre());
            System.out.println("Posición: " + jugadorExistente.getPosicion());
            System.out.println("Fecha Ingreso Equipo: " + jugadorExistente.getFechaIngresoEquipo());
            System.out.println("Código del Equipo: " + jugadorExistente.getCodigoEquipo());

            System.out.print("Nuevo nick (dejar en blanco si no se desea cambiar): ");
            String nuevoNick = s.nextLine();

            System.out.print("Nuevo nombre (dejar en blanco si no se desea cambiar): ");
            String nuevoNombre = s.nextLine();

            System.out.print("Nueva posición (dejar en blanco si no se desea cambiar): ");
            String nuevaPosicion = s.nextLine();
            
            System.out.print("Nuevo año de Ingreso Equipo (dejar en blanco si no se desea cambiar): ");
            int nuevoAño = s.nextInt();
            s.nextLine();  // Consumir el salto de línea pendiente

            System.out.print("Nuevo mes de Ingreso Equipo (dejar en blanco si no se desea cambiar): ");
            int nuevoMes = s.nextInt();
            s.nextLine();

            System.out.print("Nuevo día de Ingreso Equipo (dejar en blanco si no se desea cambiar): ");
            int nuevoDia = s.nextInt();
            s.nextLine();

            LocalDate nuevaFechaIngresoEquipo = LocalDate.of(nuevoAño, nuevoMes, nuevoDia);

            System.out.print("Nuevo código del Equipo (dejar en blanco si no se desea cambiar): ");
            String nuevoCodigoEquipoStr = s.nextLine();
            Integer nuevoCodigoEquipo = null;
            if (!nuevoCodigoEquipoStr.isEmpty()) {
                nuevoCodigoEquipo = Integer.parseInt(nuevoCodigoEquipoStr);
            }

            
            if (nuevoNick != null && !nuevoNick.isEmpty()) {
                jugadorExistente.setNick(nuevoNick);
            }
            if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
                jugadorExistente.setNombre(nuevoNombre);
            }
            if (nuevaPosicion != null && !nuevaPosicion.isEmpty()) {
                jugadorExistente.setPosicion(nuevaPosicion);
            }
            if (nuevaFechaIngresoEquipo != null) {
                jugadorExistente.setFechaIngresoEquipo(nuevaFechaIngresoEquipo);
            }
            if (nuevoCodigoEquipo != null) {
                jugadorExistente.setCodigoEquipo(nuevoCodigoEquipo);
            }

            if (jugadorDao.actualizarJugador(jugadorExistente)) {
                System.out.println("Jugador actualizado con éxito.");
            } else {
                System.out.println("Error al actualizar el jugador. Inténtelo de nuevo.");
            }
        } else {
            System.out.println("Jugador no encontrado.");
        }
    }

    private void eliminarJugador() {
    	JugadorDao jugadorDao = new JugadorDao();

        System.out.println("---- Eliminar Jugador ----");
        System.out.print("Ingrese el código del jugador a eliminar: ");
        int codigoJugador = s.nextInt();
        s.nextLine();

        Jugador jugadorExistente = jugadorDao.buscarJugadorPorCodigo(codigoJugador);

        if (jugadorExistente != null) {
            System.out.println("Jugador encontrado:");
            System.out.println("Nick: " + jugadorExistente.getNick());
            System.out.println("Nombre: " + jugadorExistente.getNombre());
            System.out.println("Posición: " + jugadorExistente.getPosicion());
            System.out.println("Fecha Ingreso Equipo: " + jugadorExistente.getFechaIngresoEquipo());
            System.out.println("Código del Equipo: " + jugadorExistente.getCodigoEquipo());

            System.out.print("¿Está seguro de que desea eliminar este jugador? (S/N): ");
            String confirmacion = s.nextLine().toUpperCase();

            if (confirmacion.equals("S")) {
                if (jugadorDao.eliminarJugador(codigoJugador)) {
                    System.out.println("Jugador eliminado con éxito.");
                } else {
                    System.out.println("Error al eliminar el jugador. Inténtelo de nuevo.");
                }
            } else {
                System.out.println("Eliminación cancelada.");
            }
        } else {
            System.out.println("Jugador no encontrado.");
        }
    }

    private void buscarJugadorPorCodigo() {
    	JugadorDao jugadorDao = new JugadorDao();

        System.out.println("---- Buscar Jugador por Código ----");
        System.out.print("Ingrese el código del jugador: ");
        int codigoJugador = s.nextInt();
        s.nextLine(); 

        Jugador jugadorEncontrado = jugadorDao.buscarJugadorPorCodigo(codigoJugador);

        if (jugadorEncontrado != null) {
            System.out.println("Jugador encontrado:");
            System.out.println("Código: " + jugadorEncontrado.getCodigo());
            System.out.println("Nick: " + jugadorEncontrado.getNick());
            System.out.println("Nombre: " + jugadorEncontrado.getNombre());
            System.out.println("Posición: " + jugadorEncontrado.getPosicion());
            System.out.println("Fecha Ingreso Equipo: " + jugadorEncontrado.getFechaIngresoEquipo());
            System.out.println("Código del Equipo: " + jugadorEncontrado.getCodigoEquipo());
        } else {
            System.out.println("Jugador no encontrado.");
        }
    }


    public void gestionarResultadosFase3() {
        

        while (true) {
            System.out.println("---- Gestión de Fase 3 ----");
            System.out.println("1. Ver todos los registros");
            System.out.println("2. Insertar nuevo registro");
            System.out.println("3. Actualizar registro existente");
            System.out.println("4. Eliminar registro");
            System.out.println("5. Buscar registro por ID");
            System.out.println("6. Volver al Menú Usuario");
            System.out.print("Seleccione una opción: ");

            int opcion = s.nextInt();
            s.nextLine(); 

            switch (opcion) {
	            case 1:
	                verTodosLosRegistros();
	                break;
                case 2:
                    insertarNuevoRegistro();
                    break;
                case 3:
                    actualizarRegistroExistente();
                    break;
                case 4:
                    eliminarRegistro();
                    break;
                case 5:
                    buscarRegistroPorId();
                    break;
                case 6:
                    System.out.println("Volviendo al Menú Usuario.");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }

    
    private void insertarNuevoRegistro() {
        Fase3Dao fase3Dao = new Fase3Dao();

        System.out.println("---- Insertar Nuevo Registro en Fase 3 ----");
        
        System.out.print("ID Fase 3: ");
        int idFase3 = s.nextInt();
        s.nextLine();  

        System.out.print("Equipo 1: ");
        String equipo1 = s.nextLine();

        System.out.print("Equipo 2: ");
        String equipo2 = s.nextLine();

        System.out.print("Resultado Equipo 1: ");
        int resEquipo1 = s.nextInt();
        s.nextLine(); 

        System.out.print("Resultado Equipo 2: ");
        int resEquipo2 = s.nextInt();
        s.nextLine(); 

        System.out.print("Etapa: ");
        int etapa = s.nextInt();
        s.nextLine();  

        System.out.print("Bracket: ");
        String bracket = s.nextLine();

     
        Fase3 nuevaFase3 = new Fase3(idFase3, equipo1, equipo2, resEquipo1, resEquipo2, etapa, bracket);

      
        if (fase3Dao.insertarFase3(nuevaFase3)) {
            System.out.println("Registro insertado con éxito.");
        } else {
            System.out.println("Error al insertar el registro. Inténtelo de nuevo.");
        }
    }

    private void actualizarRegistroExistente() {
       
        Fase3Dao fase3Dao = new Fase3Dao();

        System.out.println("---- Actualizar Registro en Fase 3 ----");
        
     
        System.out.print("Ingrese el ID de la Fase 3 a actualizar: ");
        int idFase3 = s.nextInt();
        s.nextLine();  
        Fase3 fase3Existente = fase3Dao.buscarFase3PorId(idFase3);
        if (fase3Existente == null) {
            System.out.println("Registro no encontrado.");
            return;
        }

    
        System.out.println("Detalles del Registro Actual:");
        System.out.println("ID Fase 3: " + fase3Existente.getIdFase3());
        System.out.println("Equipo 1: " + fase3Existente.getEquipo1());
        System.out.println("Equipo 2: " + fase3Existente.getEquipo2());
        System.out.println("Resultado Equipo 1: " + fase3Existente.getResEquipo1());
        System.out.println("Resultado Equipo 2: " + fase3Existente.getResEquipo2());
        System.out.println("Etapa: " + fase3Existente.getEtapa());
        System.out.println("Bracket: " + fase3Existente.getBracket());

        System.out.print("Nuevo Equipo 1 (dejar en blanco si no se desea cambiar): ");
        String nuevoEquipo1 = s.nextLine();

        System.out.print("Nuevo Equipo 2 (dejar en blanco si no se desea cambiar): ");
        String nuevoEquipo2 = s.nextLine();

        System.out.print("Nuevo Resultado Equipo 1 (dejar en blanco si no se desea cambiar): ");
        String nuevoResEquipo1Str = s.nextLine();
        Integer nuevoResEquipo1 = (!nuevoResEquipo1Str.isEmpty()) ? Integer.parseInt(nuevoResEquipo1Str) : null;

        System.out.print("Nuevo Resultado Equipo 2 (dejar en blanco si no se desea cambiar): ");
        String nuevoResEquipo2Str = s.nextLine();
        Integer nuevoResEquipo2 = (!nuevoResEquipo2Str.isEmpty()) ? Integer.parseInt(nuevoResEquipo2Str) : null;

        System.out.print("Nueva Etapa (dejar en blanco si no se desea cambiar): ");
        String nuevaEtapaStr = s.nextLine();
        Integer nuevaEtapa = (!nuevaEtapaStr.isEmpty()) ? Integer.parseInt(nuevaEtapaStr) : null;

        System.out.print("Nuevo Bracket (dejar en blanco si no se desea cambiar): ");
        String nuevoBracket = s.nextLine();

       
        if (actualizarFase3(fase3Existente, nuevoEquipo1, nuevoEquipo2, nuevoResEquipo1, nuevoResEquipo2, nuevaEtapa, nuevoBracket)) {
            System.out.println("Registro actualizado con éxito.");
        } else {
            System.out.println("Error al actualizar el registro. Inténtelo de nuevo.");
        }
    }
    private boolean actualizarFase3(Fase3 fase3Existente, String nuevoEquipo1, String nuevoEquipo2, Integer nuevoResEquipo1, Integer nuevoResEquipo2, Integer nuevaEtapa, String nuevoBracket) {
        Fase3Dao fase3Dao = new Fase3Dao();
    	
    	if (nuevoEquipo1 != null && !nuevoEquipo1.isEmpty()) {
            fase3Existente.setEquipo1(nuevoEquipo1);
        }
        if (nuevoEquipo2 != null && !nuevoEquipo2.isEmpty()) {
            fase3Existente.setEquipo2(nuevoEquipo2);
        }
        if (nuevoResEquipo1 != null) {
            fase3Existente.setResEquipo1(nuevoResEquipo1);
        }
        if (nuevoResEquipo2 != null) {
            fase3Existente.setResEquipo2(nuevoResEquipo2);
        }
        if (nuevaEtapa != null) {
            fase3Existente.setEtapa(nuevaEtapa);
        }
        if (nuevoBracket != null && !nuevoBracket.isEmpty()) {
            fase3Existente.setBracket(nuevoBracket);
        }

        return fase3Dao.actualizarFase3(fase3Existente);
    }


    private void eliminarRegistro() {
        Fase3Dao fase3Dao = new Fase3Dao();

        System.out.println("---- Eliminar Registro en Fase 3 ----");

        
        System.out.print("Ingrese el ID de la Fase 3 a eliminar: ");
        int idFase3 = s.nextInt();
        s.nextLine();  

        
        Fase3 fase3Existente = fase3Dao.buscarFase3PorId(idFase3);
        if (fase3Existente == null) {
            System.out.println("Registro no encontrado.");
            return;
        }

        
        System.out.println("Detalles del Registro a Eliminar:");
        System.out.println("ID Fase 3: " + fase3Existente.getIdFase3());
        System.out.println("Equipo 1: " + fase3Existente.getEquipo1());
        System.out.println("Equipo 2: " + fase3Existente.getEquipo2());
        System.out.println("Resultado Equipo 1: " + fase3Existente.getResEquipo1());
        System.out.println("Resultado Equipo 2: " + fase3Existente.getResEquipo2());
        System.out.println("Etapa: " + fase3Existente.getEtapa());
        System.out.println("Bracket: " + fase3Existente.getBracket());

        
        System.out.print("¿Está seguro de que desea eliminar este registro? (S/N): ");
        String confirmacion = s.nextLine();

        if (confirmacion.equalsIgnoreCase("S")) {
            
            if (fase3Dao.eliminarFase3PorId(idFase3)) {
                System.out.println("Registro eliminado con éxito.");
            } else {
                System.out.println("Error al eliminar el registro. Inténtelo de nuevo.");
            }
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }


    private void buscarRegistroPorId() {
        Fase3Dao fase3Dao = new Fase3Dao();

        System.out.println("---- Buscar Registro en Fase 3 por ID ----");

   
        System.out.print("Ingrese el ID de la Fase 3 a buscar: ");
        int idFase3 = s.nextInt();
        s.nextLine();  


        Fase3 fase3Encontrado = fase3Dao.buscarFase3PorId(idFase3);

        if (fase3Encontrado != null) {
            System.out.println("Registro Encontrado:");
            System.out.println("ID Fase 3: " + fase3Encontrado.getIdFase3());
            System.out.println("Equipo 1: " + fase3Encontrado.getEquipo1());
            System.out.println("Equipo 2: " + fase3Encontrado.getEquipo2());
            System.out.println("Resultado Equipo 1: " + fase3Encontrado.getResEquipo1());
            System.out.println("Resultado Equipo 2: " + fase3Encontrado.getResEquipo2());
            System.out.println("Etapa: " + fase3Encontrado.getEtapa());
            System.out.println("Bracket: " + fase3Encontrado.getBracket());
        } else {
            System.out.println("Registro no encontrado.");
        }
    }


    private void verTodosLosRegistros() {
        Fase3Dao fase3Dao = new Fase3Dao();

        System.out.println("---- Ver Todos los Registros en Fase 3 ----");

        
        List<Fase3> registrosFase3 = fase3Dao.obtenerTodos();

        if (!registrosFase3.isEmpty()) {
         
            for (Fase3 fase3 : registrosFase3) {
                System.out.println("ID Fase 3: " + fase3.getIdFase3());
                System.out.println("Equipo 1: " + fase3.getEquipo1());
                System.out.println("Equipo 2: " + fase3.getEquipo2());
                System.out.println("Resultado Equipo 1: " + fase3.getResEquipo1());
                System.out.println("Resultado Equipo 2: " + fase3.getResEquipo2());
                System.out.println("Etapa: " + fase3.getEtapa());
                System.out.println("Bracket: " + fase3.getBracket());
                System.out.println("------------------------");
            }
        } else {
            System.out.println("No hay registros en la tabla Fase 3.");
        }
    }

    

    
    
}
