package tarea1;

import java.util.ArrayList;

public interface RedNeuronal {

	/**
	 * Construye la red neuronal.
	 */
	void build();

	/**
	 * Entrena le red con los primero 206 datos.
	 */
	void training();

	/**
	 * Testea el aprendizaje de la red con los otros 100 dataos.
	 * @return : Aciertos de 100 intentos.
	 */
	int testing();

	/**
	 * Carga las estadisticas para luego gráficar
	 * @param cycles : cantidad de ciclos de entrenamiento a hacer
	 */
	void loadstatics(int cycles);

	/**
	 * Devuelve el arreglo con los aciertos 
	 * @return Arreglo que contiene los aciertos
	 */
	ArrayList<Number> getHits();

	/**
	 * Devuelce el arreglo con la cantidad de datos de
	 * entranimientos.
	 * @return Arreglo que contiene la cantidad de datos procesados. 
	 */
	ArrayList<Number> getdatos();

}