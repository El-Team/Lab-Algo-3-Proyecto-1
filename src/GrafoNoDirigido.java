/**
 * Este TAD es un subtipo de la inerfaz {@link Grafo}. Como su nombre lo indica,
 * representa a un grafo no dirigido.
 */

public class GrafoNoDirigido implements Grafo {
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

}
