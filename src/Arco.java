/**
* Subtipo del TAD {@link Lado} que representa los Lados que componen
* al TAD {@link GrafoDirigido}.
*/

public class Arco<E> extends Lado<E> {

	/** Vertice inicial del Arco**/
	private Vertice<E> vi;
	/** Vertice final del Arco**/
	private Vertice<E> vf;

	/** Constructor del TAD Arco: 
	* 		@param id 	representa el identificador
	* 		@param dato representa el dato a almacenar
	* 		@param p 	representa el peso del {@link Arco}
	* 		@param vi 	{@link Vertice} inicial
	* 		@param vf 	{@link Vertice} final
	*/
	public Arco(String id, E dato, double p, Vertice<E> vi, Vertice<E> vf) {
		super(id, dato, p);
		this.vi = vi;
		this.vf = vf;
	}

	/** Obtiene el {@link Vertice} que es el extremo inicial del arco
	* 		@param a 	Arco
	* 		@return 	Vertice inicial
	*/
	public Vertice<E> getExtremoInicial(Arco<E> a) {
		return a.vi;
	}

	/** Obtiene el {@link Vertice} que es el extremo final del arco
	* 		@param a 	Arco
	* 		@return 	Vertice final
	*/
	public Vertice<E> getExtremoFinal(Arco<E> a) {
		return a.vf;
	}

	/** {@inheritDoc} **/
	@Override
	public String toString(Lado l) {
		return "Arco: \"" + l.getId(l) + "\":\n" + 
			"Tipo de dato:	" + l.getDato(l).getClass().getSimpleName() + "\n" +
			"Dato:	" + l.getDato(l) + "\n" +
			"Peso:	" + l.getPeso(l) + "\n" +
			"Vertice inicial:	" + vi.toString(vi) + "\n" +
			"Vertice final:	" + vf.toString(vf) + "\n";
	}
}