package com.gmbdesign.cfrm.bussiness;

public interface IAbstractDTO {

	/**
	 * Metodo que convierte el modelo de dato en un array
	 * @return
	 */
	default String[] toArray() {
		return null;
	}
	
	/**
	 * Metodo que convierte el modelo de dato en un array2D
	 * @return
	 */
	default String[][] toArray2D() {
		return null;
	}
}
