/**
 * Este TAD es un subtipo de la inerfaz {@link Grafo}. Como su nombre lo indica,
 * representa a un digrafo.
 */
import java.util.ArrayList;

public class GrafoDirigido<V, L> implements Grafo<V, L> {
	
	/**
	 * Crea un nuevo GrafoDirigido
	 */
	public <V,L>GrafoDirigido() {
		
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

	/**
	 * Agrega un nuevo arco al grafo si el identificador del arco no lo posee ningún arco en el grafo.
	 * Retorna true en caso en que la inserción se lleva a cabo, false en caso contrario.
	 */
	public boolean agregarArco(Grafo<V,L> g, Arco<L> a) {
		return false;
	}

	/**
	 * Si el identificador id no lo posee ningún arco en el grafo, crea un nuevo arco y lo agrega en el grafo.
	 * Retorna true en caso en que la inserción se lleva a cabo, false en contrario.
	 */
	public boolean agregarArco(Grafo<V,L> g, String id, L dato, double p, String vInicial, String vFinal) {
		return false;
	}

	/**
	 * Elimina el arco en el grafo que esté identificado con id. Se retorna true en caso que se haya eliminado
	 * el arco del grafo y false en caso que no exista un arco con ese identificador en el grafo.
	 */
	public boolean eliminarArco(Grafo<V,L> g, String id) {
		return false;
	}

	/**
	 * Devuelve el arco que tiene como identificador id. En caso de que no exista ningún arco con ese
	 * identificador, se lanza la excepción NoSuchElementException.
	 */
	public Arco obtenerArco(Grafo<V,L> g, String id) {
		Vertice v1 = new Vertice<>("", "", 0);
		Vertice v2 = new Vertice<>("", "", 0);
		return new Arco<>("", "", 0, v1, v2);
	}

	/**
	 * Calcula el grado interior del vértice identificado por id en el grafo. En caso de que no exista ningún
	 * vértice con ese identificador, se lanza la excepción NoSuchElementException.
	 */
	public int gradoInterior(Grafo<V,L> g, String id) {
		return 0;
	}

	/**
	 * Calcula el grado exterior del vértice identificado por id en el grafo. En caso de que no exista ningún
	 * vértice con ese identificador, se lanza la excepción NoSuchElementException.
	 */
	public int gradoExterior(Grafo<V,L> g, String id) {
		return 0;
	}

	/**
	 * Devuelve una lista con los vértices que sucesores del vértice con identificador id. En caso de que no
	 * exista ningún vértice con ese identificador, se lanza la excepción NoSuchElementException.
	 */
	public ArrayList<Vertice<V>> sucesores(Grafo<V,L> g, String id) {
		Vertice v = new Vertice<>("", "", 0);
		ArrayList<Vertice<V>> a = new ArrayList<Vertice<V>>();
		a.add(v);
		return a;
	}

	/**
	 * Devuelve una lista con los vértices predecesores del vértice con identificador id. En caso de que no
	 * exista ningún vértice con ese identificador, se lanza la excepción NoSuchElementException.
	 */
	public ArrayList<Vertice<V>> predecesores(Grafo<V,L> g, String id) {
		Vertice v = new Vertice<>("", "", 0);
		ArrayList<Vertice<V>> a = new ArrayList<Vertice<V>>();
		a.add(v);
		return a;
	}

}
