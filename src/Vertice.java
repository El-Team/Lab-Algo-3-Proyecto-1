/**
* Clase <code>Vertice</code> representa un tipo de dato que posee
* el {@link Grafo}. Se identifica con {@link id} y su informacion
* principal se guarda en {@link dato}. Tambien tienen un {@link peso}
* asociado a ellos
*/

public class Vertice<E> {

	/** Identificador asociado al vertice **/
	private String id;
	/** Dato que se quiere almacenar en el vertice **/
	private E dato;
	/** Peso asociado al vertice **/
	private double peso;

	/** Constructor del TAD Vertice: 
	* 		@param id 	representa el identificador
	* 		@param dato representa el dato a almacenar
	* 		@param p 	representa el peso del {@link Vertice}
	*/
	public Vertice(String id, E dato, double p) {
		this.id = id;
		this.dato = dato;
		this.peso = p;
	}

	/** Funcion para obtener el peso del vertice
	* 		@return 	Peso asociado al vertice
	*/
	public double getPeso() {
		return this.peso;
	}

	/** Funcion para obtener el identificador del vertice
	* 		@return 	Identificador del vertice
	*/
	public String getId() {
		return this.id;
	}

	/** Funcion para obtener el dato del vertice
	* 		@return 	dato almacenado en el vertice
	*/
	public E getDato() {
		return this.dato;
	}

	/** Funcion para obtener toda la informacion del vertice
	* 		@return 	<code>String</code> con toda la informacion del vertice
	*/
	public String toString() {
		return "Vertice \"" + this.id + "\":\n" +
			"	Tipo de dato:		" + this.dato.getClass().getSimpleName() + "\n" +
			"	Dato:		" + this.dato + "\n" +
			"	Peso:		" + this.peso + "\n";
	}
}