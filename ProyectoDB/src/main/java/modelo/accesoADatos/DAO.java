package modelo.accesoADatos;

import java.util.List;

/**
 * Interfaz para la capa de acceso a datos (DAO).
 * 
 * @param <T> El tipo de objeto que maneja el DAO.
 */
public interface DAO<T> {
    
    /**
     * Obtiene todos los objetos del tipo T.
     * 
     * @return Una lista de todos los objetos del tipo T.
     */    
    List<T> obtenerTodos();
    
    /**
     * Obtiene un objeto del tipo T por su ID.
     * 
     * @param id El ID del objeto a obtener.
     * 
     * @return El objeto del tipo T correspondiente al ID especificado.
     */    
    T obtenerPorId(Integer id);
    
    /**
     * Inserta un objeto del tipo T en la base de datos.
     * 
     * @param objeto El objeto del tipo T a insertar.
     */    
    void insertar(T objeto);
    
    /**
     * Actualiza un objeto del tipo T en la base de datos.
     * 
     * @param objeto El objeto del tipo T a actualizar.
     */    
    void actualizar(T objeto);
    
    /**
     * Elimina un objeto del tipo T de la base de datos por su ID.
     * 
     * @param id El ID del objeto a eliminar.
     */    
    void eliminar(Integer id);
}
