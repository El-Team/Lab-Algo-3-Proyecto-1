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
	* 		@return 	Vertice inicial
	*/
	public Vertice<E> getExtremoInicial() {
		return this.vi;
	}

	/** Obtiene el {@link Vertice} que es el extremo final del arco
	* 		@return 	Vertice final
	*/
	public Vertice<E> getExtremoFinal() {
		return this.vf;
	}

	/** {@inheritDoc} **/
	@Override
	public String toString() {
		return "Arco: \"" + this.id + "\":\n" + 
			"Tipo de dato:	" + this.dato.getClass().getSimpleName() + "\n" +
			"Dato:	" + this.dato + "\n" +
			"Peso:	" + this.peso + "\n" +
			"Vertice inicial:	" + this.vi.toString() + "\n" +
			"Vertice final:	" + this.vf.toString() + "\n";
	}
}