package com.gmbdesign.cfrm.dao;

import com.gmbdesign.cfrm.dto.UsuarioDTO;
import com.gmbdesign.cfrm.dto.UsuarioDataDTO;
import java.util.List;

/**
 *
 * @author ggamboa
 */
public interface UsuarioDAO {
    
    /**
     * Método que recupera un listado de todos los usuario creados en la DataBase
     * 
     * @return List<Usuario>
     */
    List<UsuarioDTO> buscarTodos();
    
    /**
     * Método que recupera un listado de los usuario coincidentes con una serie de ids.
     * 
     * @param ids
     * @return 
     */
    List<UsuarioDTO> buscarPorId(int[] ids);
    
    /**
     * Método que recupera un listado de los usuario coincidentes con una cadena de caracteres dada.
     * 
     * @param nombre
     * @return 
     */
    List<UsuarioDTO> buscarPorNombre(String nombre);
    
    /**
     * Método que recupera un listado de los usuario coincidentes con el role buscado.
     * 
     * @param role
     * @return 
     */
    List<UsuarioDTO> buscarPorRole(String role);
    
    /**
     * Método que devolverá un listado de usuarios cuyo estado 'activo' coincida con el
     * parámetro pasado como entrada.
     * 
     * true or false -> estado de activación buscada
     * 
     * @param esActivo
     * @return List
     */
    List<UsuarioDTO> buscarActivos(boolean esActivo);
    
    /**
     * Metodo para comprobar el acceso de un usuario validado
     * 
     * @param usuario
     * @return 
     */
    UsuarioDTO login(UsuarioDTO usuario);
    
    /**
     * Insertar un nuevo usuario en la DataBase
     * 
     * @param usuario
     * @return 
     */
    int[] agregarUsuario(UsuarioDTO usuario);
    
    /**
     * Editar los datos asignados a un usuario concreto
     * 
     * @param usuario
     * @return 
     */
    boolean editarDatosUsuario(UsuarioDTO usuario);
    
    /**
     * Eliminar un usuario concreto de la base de datos.
     * 
     * @param usuario
     * @return 
     */
    boolean eliminarUsuario(UsuarioDTO usuario);
    
    
}
