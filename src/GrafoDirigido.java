/**
 * Este TAD es un subtipo de la inerfaz {@link Grafo}. Como su nombre lo indica,
 * representa a un digrafo.
 */

public class GrafoDirigido implements Grafo {
		/**
	 * {@inheritDoc}
	 */
	public boolean cargarGrafo(Grafo<V,L> g, String archivo) {
		return ;
	}

	/**
	 * {@inheritDoc}
	 */
	public int numeroDeVertices(Grafo<V,L> g) {
		return ;
	}

	/**
	 * {@inheritDoc}
	 */
	public int numeroDeLados(Grafo<V,L> g) {
		return ;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean agregarVertice(Grafo<V,L> g, Vértice<V> v) {
		return ;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean agregarVertice(Grafo<V,L> g, String id, V dato, double p) {
		return ;
	}

	/**
	 * {@inheritDoc}
	 */
	public Vertice<V> obtenerVertice(Grafo<V,L> g, String id) {
		return ;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean pestaVertice(Grafo<V,L> g, String id) {
		return ;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean estaLado(Grafo<V,L> g, String u, String v) {
		return ;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean eliminarVertice(Grafo<V,L> g, String id) {
		return ;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Vertice<V>> vertices(Grafo<V,L> g) {
		return ;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Lado<L>> lados(Grafo<V,L> g) {
		return ;
	}

	/**
	 * {@inheritDoc}
	 */
	public int grado(Grafo<V,L> g, String id) {
		return ;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Vertice<V>> adyacentes(Grafo<V,L> g, String id) {
		return ;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Lado<E>> incidentes(Grafo<V,L> g, String id) {
		return ;
	}

	/**
	 * {@inheritDoc}
	 */
	public Grafo clone(Grafo<V,L> g) {
		return ;
	};

	/**
	 * {@inheritDoc}
	 */
	public String toString(Grafo<V,L> g) {
		return ;
	}

	/**
	 * Crea un nuevo GrafoDirigido
	 */
	public GrafoDirigido<V,L> crearGrafoDirigido<V,L>() {
		return ;
	}

	/**
	 * Agrega un nuevo arco al grafo si el identificador del arco no lo posee ningún arco en el grafo.
	 * Retorna true en caso en que la inserción se lleva a cabo, false en caso contrario.
	 */
	public boolean agregarArco(Grafo<V,L> g, Arco<L> a) {
		return ;
	}

	/**
	 * Si el identificador id no lo posee ningún arco en el grafo, crea un nuevo arco y lo agrega en el grafo.
	 * Retorna true en caso en que la inserción se lleva a cabo, false en contrario.
	 */
	public boolean agregarArco(Grafo<V,L> g, String id, L dato, double p, String vInicial, String vFinal) {
		return ;
	}

	/**
	 * Elimina el arco en el grafo que esté identificado con id. Se retorna true en caso que se haya eliminado
	 * el arco del grafo y false en caso que no exista un arco con ese identificador en el grafo.
	 */
	public boolean eliminarArco(Grafo<V,L> g, String id) {
		return ;
	}

	/**
	 * Devuelve el arco que tiene como identificador id. En caso de que no exista ningún arco con ese
	 * identificador, se lanza la excepción NoSuchElementException.
	 */
	public Arco obtenerArco(Grafo<V,L> g, String id) {
		return ;
	}

	/**
	 * Calcula el grado interior del vértice identificado por id en el grafo. En caso de que no exista ningún
	 * vértice con ese identificador, se lanza la excepción NoSuchElementException.
	 */
	public int gradoInterior(Grafo<V,L> g, String id) {
		return ;
	}

	/**
	 * Calcula el grado exterior del vértice identificado por id en el grafo. En caso de que no exista ningún
	 * vértice con ese identificador, se lanza la excepción NoSuchElementException.
	 */
	public int gradoExterior(Grafo<V,L> g, String id) {
		return ;
	}

	/**
	 * Devuelve una lista con los vértices que sucesores del vértice con identificador id. En caso de que no
	 * exista ningún vértice con ese identificador, se lanza la excepción NoSuchElementException.
	 */
	public List<Vertice<V>> sucesores(Grafo<V,L> g, String id) {
		return ;
	}

	/**
	 * Devuelve una lista con los vértices predecesores del vértice con identificador id. En caso de que no
	 * exista ningún vértice con ese identificador, se lanza la excepción NoSuchElementException.
	 */
	public List<Vertice<V>> predecesores(Grafo<V,L> g, String id) {
		return ;
	}

}
