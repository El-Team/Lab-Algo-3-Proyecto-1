/** Subtipo del TAD {@link Lado} que representa los Lados que componen
* al TAD {@link GrafoNoDirigido}.
*/

public class Arista<E> extends Lado<E> {

	/** Vertice extremo 1 del Arista**/
	private Vertice<E> u;
	/** Vertice extremo 2 del Arista**/
	private Vertice<E> v;

	/** Constructor del TAD Arista: 
	* 		@param id 	representa el identificador
	* 		@param dato representa el dato a almacenar
	* 		@param p 	representa el peso del {@link Arista}
	* 		@param vi 	{@link Vertice} extremo 1
	* 		@param vf 	{@link Vertice} extremo 2
	*/
	public Arista(String id, E dato, double p, Vertice<E> u, Vertice<E> v) {
		super(id, dato, p);
		this.u = u;
		this.v = v;
	}

	/** Obtiene el {@link Vertice} del primer extremo del Arista
	* 		@param a 	Arista
	* 		@return 	Vertice del primer extremo
	*/
	public Vertice<E> getExtremo1(Arista<E> a) {
		return a.u;
	}

	/** Obtiene el {@link Vertice} del segundo extermo del Arista
	* 		@param a 	Arista
	* 		@return 	Vertice del segundo extremo
	*/
	public Vertice<E> getExtremo2(Arista<E> a) {
		return a.v;
	}

	/** {@inheritDoc} **/
	@Override
	public String toString(Lado l) {
		return "Informacion del Arista: \n" + 
		"Identificador:	" + l.getId(l) + "\n" +
		"Tipo de dato:	" + l.getDato(l).getClass().getSimpleName() + "\n" +
		"Dato:			" + l.getDato(l) + "\n" +
		"Peso: 			" + l.getPeso(l) + "\n" +
		"Vertice 1: 	" + u.toString(u) + "\n" +
		"Vertice 2: 	" + v.toString(v) + "\n";
	}
}