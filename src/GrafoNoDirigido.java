/**
 * Este TAD es un subtipo de la inerfaz {@link Grafo}. Como su nombre lo indica,
 * representa a un grafo no dirigido.
 */
import java.util.ArrayList;

public class GrafoNoDirigido<V, L>implements Grafo<V, L> {

	/**
	 * Crea un nuevo GrafNoDirigido
	 */
	public <V,L>GrafoNoDirigido() {
		
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean cargarGrafo(Grafo<V,L> g, String archivo) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public int numeroDeVertices(Grafo<V,L> g) {
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	public int numeroDeLados(Grafo<V,L> g) {
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean agregarVertice(Grafo<V,L> g, Vertice<V> v) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean agregarVertice(Grafo<V,L> g, String id, V dato, double p) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public Vertice<V> obtenerVertice(Grafo<V,L> g, String id) {
		return new Vertice<>("", null, 0);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean estaVertice(Grafo<V,L> g, String id) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean estaLado(Grafo<V,L> g, String u, String v) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean eliminarVertice(Grafo<V,L> g, String id) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public ArrayList<Vertice<V>> vertices(Grafo<V,L> g) {
		Vertice v = new Vertice<>("", "", 0);
		ArrayList<Vertice<V>> a = new ArrayList<Vertice<V>>();
		a.add(v);
		return a;
	}

	/**
	 * {@inheritDoc}
	 */
	public ArrayList<Lado<L>> lados(Grafo<V,L> g) {
		Vertice v1 = new Vertice<>("", "", 0);
		Vertice v2 = new Vertice<>("", "", 0);
		Arco l = new Arco<>("", "", 0, v1, v2);
		ArrayList<Lado<L>> a = new ArrayList<Lado<L>>();
		a.add(l);
		return a;
	}

	/**
	 * {@inheritDoc}
	 */
	public int grado(Grafo<V,L> g, String id) {
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	public ArrayList<Vertice<V>> adyacentes(Grafo<V,L> g, String id) {
		Vertice v = new Vertice<>("", "", 0);
		ArrayList<Vertice<V>> a = new ArrayList<Vertice<V>>();
		a.add(v);
		return a;
	}

	/**
	 * {@inheritDoc}
	 */
	public ArrayList<Lado<L>> incidentes(Grafo<V,L> g, String id) {
		Vertice v1 = new Vertice<>("", "", 0);
		Vertice v2 = new Vertice<>("", "", 0);
		Arco l = new Arco<>("", "", 0, v1, v2);
		ArrayList<Lado<L>> a = new ArrayList<Lado<L>>();
		a.add(l);
		return a;
	}

	/**
	 * {@inheritDoc}
	 */
	public Grafo clone(Grafo<V,L> g) {
		return g;
	};

	/**
	 * {@inheritDoc}
	 */
	public String toString(Grafo<V,L> g) {
		return "";
	}

}
