/**
 * Este TAD es un subtipo de la inerfaz {@link Grafo}. Como su nombre lo indica,
 * representa a un grafo no dirigido.
 */
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class GrafoNoDirigido<V, L> implements Grafo<V, L> {

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

	/**
	 * Agrega una nueva arista al grafo si el identificador de la arista no lo posee ninguna arista en el grafo.
	 * Retorna true en caso en que la inserción se lleve a cabo, false en contrario.
	 */
	public boolean agregarArista(Grafo<V,L> g, Arco<L> a) {
		GrafoDirigido<V,L> castedGraph = (GrafoDirigido<V,L>)g;
		for (Lado<L> l : castedGraph.lados(this)) {
			Arco<L> castedEdge = (Arco<L>)l;
			if (castedEdge.getId() == a.getId()) {
				return false;
			}
		}
		try {
			castedGraph.getEdges().put(a.getId(), a);
		}
		catch(Error e) {
			return false;
		}
		return true;
	}

	/**
	 * Si el identificador id no lo posee ninguna arista en el grafo, crea una nueva arista y la agrega en el
	 * grafo. Retorna true en caso en que la inserción se lleve a cabo, false en contrario.
	 */
	public boolean agregarArista(
			Grafo<V,L> g,
			String id,
			L dato,
			double p,
			String u,
			String v
		) {

		try {
			GrafoDirigido<V,L> castedGraph = (GrafoDirigido<V,L>)g;
			if (
				!g.estaVertice(this, u) ||
				!g.estaVertice(this, v)
			) {
				throw new NoSuchElementException();
			}
			Vertice<V> vi = g.obtenerVertice(this, u);
			Vertice<V> vf = g.obtenerVertice(this, v);
			Arco<L> a = new Arco(id, dato, p, vi, vf);
			castedGraph.getEdges().put(a.getId(), a);
		}
		catch(Error e) {
			return false;
		}
		return true;
	}

	/**
	 * Elimina la arista en el grafo que esté identificada con id. Se retorna true en caso que se haya
	 * eliminado la arista del grafo y false en caso de que no exista una arista con ese identificador en el
	 * grafo.
	 */
	public boolean eliminarArista(Grafo<V,L> g, String id) {
		GrafoDirigido<V,L> castedGraph = (GrafoDirigido<V,L>)g;
		if (castedGraph.getEdges().remove(id) != null) {
			return true;
		}
		return false;
	}

	/**
	 * Devuelve la arista que tiene como identificador id. En caso de que no exista ninguna arista con ese
	 * identificador, se lanza la excepción NoSuchElementException.
	 */
	public Arco obtenerArista(Grafo<V,L> g, String id) throws NoSuchElementException {
		GrafoDirigido<V,L> castedGraph = (GrafoDirigido<V,L>)g;
		Arco<L> a = (Arco<L>)castedGraph.getEdges().get(id);
		if (a == null) {
			throw new NoSuchElementException();
		}
		return a;
	}
}
