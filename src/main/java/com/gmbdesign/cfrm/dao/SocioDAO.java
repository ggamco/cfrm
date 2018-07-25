package com.gmbdesign.cfrm.dao;

import com.gmbdesign.cfrm.bussiness.ICredentialCard;
import com.gmbdesign.cfrm.dto.SocioDTO;
import com.gmbdesign.cfrm.dto.SocioDataDTO;
import java.util.List;

/**
 *
 * @author ggamboa
 */
public interface SocioDAO {
    
    /**
     * Método que recupera un listado de todos los socio creados en la DataBase
     * 
     * @return
     */
    List<SocioDTO> buscarTodos();
    
    /**
     * Método que recupera un listado de los socio coincidentes con una serie de ids.
     * 
     * @param ids
     * @return 
     */
    List<SocioDTO> buscarPorId(int[] ids);
    
    /**
     * Método que recupera un listado de los socio coincidentes con una cadena de caracteres dada.
     * 
     * @param nombre
     * @return 
     */
    List<SocioDTO> buscarPorNombre(String nombre);
    
    /**
     * Método que recupera un listado de los socio coincidentes con el numeroSocio buscado.
     * 
     * @param numeroSocio
     * @return 
     */
    List<SocioDTO> buscarPorNumeroSocio(int numeroSocio);
    
    /**
     * Método que devolverá un listado de socios cuyo estado 'activo' coincida con el
     * parámetro pasado como entrada.
     * 
     * true or false -> estado de activación buscada
     * 
     * @param esActivo
     * @return List
     */
    List<SocioDTO> buscarActivos(boolean esActivo);
    
    /**
     * Insertar un nuevo socio en la DataBase
     * 
     * @param socio
     * @return 
     */
    int[] agregarSocio(SocioDTO socio);
    
    /**
     * Editar los datos asignados a un socio concreto
     * 
     * @param socioData
     * @return 
     */
    int[] editarDatosSocio(SocioDTO socio);
    
    /**
     * Eliminar un socio concreto de la base de datos.
     * 
     * @param socio
     * @return 
     */
    boolean eliminarSocio(SocioDTO socio);
    
    /**
     * Metodo que recupera la información de un socio usando su codigo de Sistema
     * 
     * @param codigo
     * @return 
     */
    SocioDTO buscarSocioByCodigo(String codigo);
    
    /**
     * Metodo que devuelve un lista de socios comprendidos dentro del rango propuesto
     * 
     * @param menor
     * @param mayor
     * @return 
     */
    List<SocioDTO> buscarPorRango(int menor, int mayor);
    
    /**
     * Metodo que modifica el estado de un carnet de socio entre activo o bloqueado.
     * 
     * @param socio
     * @param nuevoEstado
     * @return 
     */
    boolean updateActivado(SocioDTO socio, boolean nuevoEstado);
    
    /**
     * Metodo que modifica el estado de un carnet a 'Printed' cuando se llama al servicio de impresion.
     * 
     * @param listaSocios
     * @return 
     */
    boolean updateToPrinted(List<ICredentialCard> listaSocios);
    
    /**
     * Metodo que devuelve un listado de socios cuyo carnet no han sido impresos.
     * 
     * @return 
     */
    List<SocioDTO> buscarSociosNotPrinted();
    
}
