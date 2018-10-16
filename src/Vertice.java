/** Clase <code>Vertice</code> representa un tipo de dato que posee
* el {@link Grafo}. Se identifica con {@link id} y su informacion
* principal se guarda en {@link dato}. Tambien tienen un {@link peso}
* asociado a ellos
*/

public class Vertice<E> {

	/** Identificador asociado al vertice **/
	String id;
	/** Dato que se quiere almacenar en el vertice **/
	E dato;
	/** Peso asociado al vertice**/
	double peso;

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
	* 		@param v 	Vertice
	* 		@return 	Peso asociado al vertice
	*/
	public double getPeso(Vertice<E> v) {
		return v.peso;
	}

	/** Funcion para obtener el identificador del vertice
	* 		@param v 	Vertice
	* 		@return 	Identificador del vertice
	*/
	public String getId(Vertice<E> v) {
		return v.id;
	}

	/** Funcion para obtener el dato del vertice
	* 		@param v 	Vertice
	* 		@return 	dato almacenado en el vertice
	*/
	public E getDato(Vertice<E> v) {
		return v.dato;
	}

	/** Funcion para obtener toda la informacion del vertice
	* 		@param v 	Vertice
	* 		@return 	<code>String</code> con toda la informacion del vertice
	*/
	public String toString(Vertice<E> v) {
		return "Vertice \"" + v.getId(v) + "\":\n" +
			"	Tipo de dato:		" + v.getDato(v).getClass().getSimpleName() + "\n" +
			"	Dato:		" + v.getDato(v) + "\n" +
			"	Peso:		" + v.getPeso(v) + "\n";
	}
}