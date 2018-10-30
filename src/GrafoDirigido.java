/**
 * Este TAD es un subtipo de la inerfaz {@link Grafo}. Como su nombre lo indica,
 * representa a un digrafo.
 */
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.time.Instant;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.io.IOException;
import java.lang.Integer;
import java.util.NoSuchElementException;


public class GrafoDirigido<V, L> implements Grafo<V, L> {

	public int vertexCount;
	public int edgeCount;
	public LinkedHashMap<String, Vertice<V>> vertices;
	public LinkedHashMap<String, Lado<L>> edges;
	
	/**
	 * Crea un nuevo GrafoDirigido
	 */
	public <V,L>GrafoDirigido() {
		this.vertexCount = 0;
		this.edgeCount = 0;
	}

	/**
	 * Getters
	 */
	public int getVertexCount() {
		return this.vertexCount;
	}
	public int getEdgeCount() {
		return this.edgeCount;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean cargarGrafo(Grafo<V,L> g, String archivo) {

		try {
			if (!Utilidades.isValidPath(archivo)) {
				throw new FileNotFoundException();
			}
			else if (!Utilidades.documentHasValidFormat(archivo)) {
				throw new ParseException("", 0);
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("No fue posible importar el archivo, verifique que el nombre es el correcto");
			return false;
		}
		catch(ParseException e) {
			System.out.println("El documento no tiene el formato correcto");
			return false;
		}

		// Abrir archivo de texto
		List<String> lines = null;
		try {
			lines = Files.readAllLines(
				Paths.get(archivo),
				Charset.defaultCharset()
			);
		}
		catch(IOException e) {
			e.printStackTrace();
		}


		// Cargar datos

		this.vertexCount = Integer.parseInt(lines.get(3));
		this.edgeCount = Integer.parseInt(lines.get(4));

		this.vertices = new LinkedHashMap<String, Vertice<V>>();
		for (int i = 5; i < 5 + this.vertexCount; i++) {
			String[] vertexData = lines.get(i).split("\\s");
			Vertice<V> v = new Vertice<V>(
				"v" + Instant.now().toString(),
				(V)vertexData[1],
				Double.parseDouble(vertexData[2])
			);
		}

		this.edges = new LinkedHashMap<String, Lado<L>>();
		for (int i = 5 + this.vertexCount; i < lines.size() - 1; i++) {
			String[] edgeData = lines.get(i).split("\\s");
			Vertice<V> v = new Vertice<V>(
				"e" + Instant.now().toString(),
				(V)edgeData[1],
				Double.parseDouble(edgeData[2])
			);
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public int numeroDeVertices(Grafo<V,L> g) {
		GrafoDirigido<V,L> castedGraph = (GrafoDirigido<V,L>)g;
		return castedGraph.getVertexCount();
	}

	/**
	 * {@inheritDoc}
	 */
	public int numeroDeLados(Grafo<V,L> g) {
		GrafoDirigido<V,L> castedGraph = (GrafoDirigido<V,L>)g;
		return castedGraph.getEdgeCount();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean agregarVertice(Grafo<V,L> g, Vertice<V> v) {
		try {
			GrafoDirigido<V,L> castedGraph = (GrafoDirigido<V,L>)g;
			castedGraph.vertices.put(v.getId(), v);
		}
		catch(Error e) {
			return false;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean agregarVertice(Grafo<V,L> g, String id, V dato, double p) {
		try {
			GrafoDirigido<V,L> castedGraph = (GrafoDirigido<V,L>)g;
			Vertice<V> v = new Vertice<V>(id, dato, p);
			castedGraph.vertices.put(id, v);	
		}
		catch(Error e) {
			return false;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public Vertice<V> obtenerVertice(Grafo<V,L> g, String id) {
		GrafoDirigido<V,L> castedGraph = (GrafoDirigido<V,L>)g;
		Vertice<V> v = castedGraph.vertices.get(id);
		if (v == null) {
			throw new NoSuchElementException();
		}
		return v;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean estaVertice(Grafo<V,L> g, String id) {
		GrafoDirigido<V,L> castedGraph = (GrafoDirigido<V,L>)g;
		return castedGraph.vertices.containsKey(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean estaLado(Grafo<V,L> g, String u, String v) {
		GrafoDirigido<V,L> castedGraph = (GrafoDirigido<V,L>)g;
		for (String edgeId : castedGraph.edges.keySet()) {
			Arco<L> castedEdge = (Arco<L>)castedGraph.edges.get(edgeId);
			if (
				castedEdge.getExtremoInicial().getId() == u &&
				castedEdge.getExtremoFinal().getId() == v
			) {
				return true;
			}
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean eliminarVertice(Grafo<V,L> g, String id) {
		GrafoDirigido<V,L> castedGraph = (GrafoDirigido<V,L>)g;
		if (castedGraph.vertices.remove(id) != null) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public ArrayList<Vertice<V>> vertices(Grafo<V,L> g) {
		GrafoDirigido<V,L> castedGraph = (GrafoDirigido<V,L>)g;
		return new ArrayList(castedGraph.vertices.entrySet());
	}

	/**
	 * {@inheritDoc}
	 */
	public ArrayList<Lado<L>> lados(Grafo<V,L> g) {
		GrafoDirigido<V,L> castedGraph = (GrafoDirigido<V,L>)g;
		return new ArrayList(castedGraph.edges.entrySet());
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
