/**
 * Este TAD es un subtipo de la inerfaz {@link Grafo}. Como su nombre lo indica,
 * representa a un digrafo.
 */
import java.util.ArrayList;
import java.util.LinkedHashMap;
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

	private int vertexCount;
	private int edgeCount;
	private LinkedHashMap<String, Vertice<V>> vertices;
	private LinkedHashMap<String, Lado<L>> edges;
	
	/**
	 * Crea un nuevo GrafoDirigido
	 */
	public <V,L>GrafoDirigido() {
		this.vertexCount = 0;
		this.edgeCount = 0;
	}

	/**
	 * Getters y setters
	 */
	public int getVertexCount() {
		return this.vertexCount;
	}
	public int getEdgeCount() {
		return this.edgeCount;
	}
	public LinkedHashMap<String, Vertice<V>> getVertices() {
		return this.vertices;
	}
	public LinkedHashMap<String, Lado<L>> getEdges() {
		return this.edges;
	}
	public void setVertexCount(int n) {
		this.vertexCount = n;
	}
	public void setEdgeCount(int m) {
		this.edgeCount = m;
	}
	public void setVertices(LinkedHashMap<String, Vertice<V>> vertices) {
		this.vertices = vertices;
	}
	public void setEdges(LinkedHashMap<String, Lado<L>> edges) {
		this.edges = edges;
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
			return false;
		}


		// Cargar datos

		this.setVertexCount(Integer.parseInt(lines.get(3)));
		this.setEdgeCount(Integer.parseInt(lines.get(4)));

		LinkedHashMap<String, Vertice<V>> importedVertices =
			new LinkedHashMap<String, Vertice<V>>();
		for (int i = 5; i < 5 + this.getVertexCount(); i++) {
			String[] vertexData = lines.get(i).split("\\s");
			Vertice<V> v = new Vertice<V>(
				vertexData[0],
				(V)vertexData[1],
				Double.parseDouble(vertexData[2])
			);
			importedVertices.put(vertexData[0], v);
		}
		this.setVertices(importedVertices);


		LinkedHashMap<String, Lado<L>> importedEdges =
			new LinkedHashMap<String, Lado<L>>();
		for (int i = 5 + this.getVertexCount(); i < lines.size() - 1; i++) {

			String[] edgeData = lines.get(i).split("\\s");
			Vertice<V> vi = this.obtenerVertice(this, edgeData[3]);
			Vertice<V> vf = this.obtenerVertice(this, edgeData[4]);

			Arco<L> e = new Arco<L>(
				edgeData[0],
				(L)edgeData[1],
				Double.parseDouble(edgeData[2]),
				vi,
				vf
			);
			importedEdges.put(edgeData[0], e);
		}
		this.setEdges(importedEdges);


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
			castedGraph.getVertices().put(v.getId(), v);
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
			castedGraph.getVertices().put(id, v);	
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
		Vertice<V> v = castedGraph.getVertices().get(id);
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
		return castedGraph.getVertices().containsKey(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean estaLado(Grafo<V,L> g, String u, String v) {
		GrafoDirigido<V,L> castedGraph = (GrafoDirigido<V,L>)g;
		for (String edgeId : castedGraph.getEdges().keySet()) {
			Arco<L> castedEdge = (Arco<L>)castedGraph.getEdges().get(edgeId);
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
		if (castedGraph.getVertices().remove(id) != null) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public ArrayList<Vertice<V>> vertices(Grafo<V,L> g) {
		GrafoDirigido<V,L> castedGraph = (GrafoDirigido<V,L>)g;
		ArrayList<Vertice<V>> vertices = new ArrayList();
		for (String vertexId : castedGraph.getVertices().keySet()) {
			vertices.add((Vertice<V>)(castedGraph.getVertices().get(vertexId)));
		}
		return vertices;
	}

	/**
	 * {@inheritDoc}
	 */
	public ArrayList<Lado<L>> lados(Grafo<V,L> g) {
		GrafoDirigido<V,L> castedGraph = (GrafoDirigido<V,L>)g;
		ArrayList<Lado<L>> lados = new ArrayList();
		for (String edgeId : castedGraph.getEdges().keySet()) {
			lados.add((Lado<L>)(castedGraph.getEdges().get(edgeId)));
		}
		return lados;
	}

	/**
	 * {@inheritDoc}
	 */
	public int grado(Grafo<V,L> g, String id) {
		GrafoDirigido<V,L> castedGraph = (GrafoDirigido<V,L>)g;
		int grado = 0;
		if (!g.estaVertice(this, id)) {
			throw new NoSuchElementException();
		}
		for (String edgeId : castedGraph.getEdges().keySet()) {
			Arco<L> castedEdge = (Arco<L>)castedGraph.getEdges().get(edgeId);
			if (
				castedEdge.getExtremoInicial().getId() == id ||
				castedEdge.getExtremoFinal().getId() == id
			) {
				grado++;
			}
		}
		return grado;
	}

	/**
	 * {@inheritDoc}
	 */
	public ArrayList<Vertice<V>> adyacentes(Grafo<V,L> g, String id) {
		GrafoDirigido<V,L> castedGraph = (GrafoDirigido<V,L>)g;
		ArrayList<Vertice<V>> adyacentes = new ArrayList();
		if (!g.estaVertice(this, id)) {
			throw new NoSuchElementException();
		}
		for (String edgeId : castedGraph.getEdges().keySet()) {
			Arco<L> castedEdge = (Arco<L>)castedGraph.getEdges().get(edgeId);
			if (castedEdge.getExtremoInicial().getId() == id) {
				adyacentes.add(castedEdge.getExtremoFinal());
			}
			if (castedEdge.getExtremoFinal().getId() == id) {
				adyacentes.add(castedEdge.getExtremoInicial());
			}
		}
		return adyacentes;
	}

	/**
	 * {@inheritDoc}
	 */
	public ArrayList<Lado<L>> incidentes(Grafo<V,L> g, String id) {
		GrafoDirigido<V,L> castedGraph = (GrafoDirigido<V,L>)g;
		ArrayList<Lado<L>> incidentes = new ArrayList();
		if (!g.estaVertice(this, id)) {
			throw new NoSuchElementException();
		}
		for (String edgeId : castedGraph.getEdges().keySet()) {
			Arco<L> castedEdge = (Arco<L>)castedGraph.getEdges().get(edgeId);
			if (
				castedEdge.getExtremoInicial().getId() == id ||
				castedEdge.getExtremoFinal().getId() == id
			) {
				incidentes.add(castedEdge);
			}
		}
		return incidentes;
	}

	/**
	 * {@inheritDoc}
	 */
	public Grafo clone(Grafo<V,L> g) {

		// Se preparan los datos de entrada

		GrafoDirigido<V,L> castedGraph = (GrafoDirigido<V,L>)g;

		ArrayList<Vertice<V>> vertices = castedGraph.vertices(this);
		LinkedHashMap<String, Vertice<V>> importedVertices =
			new LinkedHashMap<String, Vertice<V>>();
		for (Vertice<V> v : vertices) {
			importedVertices.put(v.getId(), v);
		}

		ArrayList<Lado<L>> lados = castedGraph.lados(this);
		LinkedHashMap<String, Lado<L>> importedEdges =
			new LinkedHashMap<String, Lado<L>>();
		for (Lado<L> l : lados) {
			importedEdges.put(l.getId(), l);
		}


		// Se asignan los datos al clon

		GrafoDirigido<V,L> newGraph = new GrafoDirigido<V,L>();
		newGraph.setVertexCount(castedGraph.numeroDeVertices(this));
		newGraph.setEdgeCount(castedGraph.numeroDeVertices(this));
		newGraph.setVertices(importedVertices);
		newGraph.setEdges(importedEdges);


		return newGraph;
	};

	/**
	 * {@inheritDoc}
	 */
	public String toString(Grafo<V,L> g) {
		GrafoDirigido<V,L> castedGraph = (GrafoDirigido<V,L>)g;
		String graphStr =
			"Número de vértices: " + castedGraph.numeroDeVertices(this) + "\n" +
			"Número de lados: " + castedGraph.numeroDeLados(this) + "\n" +
			"Vertices:\n" + castedGraph.vertices(this).toString() + "\n" +
			"Lados:\n" + castedGraph.vertices(this).toString();

		return graphStr;
	}

	/**
	 * Agrega un nuevo arco al grafo si el identificador del arco no lo posee ningún arco en el grafo.
	 * Retorna true en caso en que la inserción se lleva a cabo, false en caso contrario.
	 */
	public boolean agregarArco(Grafo<V,L> g, Arco<L> a) {
		try {
			GrafoDirigido<V,L> castedGraph = (GrafoDirigido<V,L>)g;
			castedGraph.getEdges().put(a.getId(), a);
		}
		catch(Error e) {
			return false;
		}
		return true;
	}

	/**
	 * Si el identificador id no lo posee ningún arco en el grafo, crea un nuevo arco y lo agrega en el grafo.
	 * Retorna true en caso en que la inserción se lleva a cabo, false en contrario.
	 */
	public boolean agregarArco(Grafo<V,L> g, String id, L dato, double p, String vInicial, String vFinal) {

		try {
			GrafoDirigido<V,L> castedGraph = (GrafoDirigido<V,L>)g;
			if (
				!g.estaVertice(this, vInicial) ||
				!g.estaVertice(this, vFinal)
			) {
				throw new NoSuchElementException();
			}
			Vertice<V> vi = g.obtenerVertice(this, vInicial);
			Vertice<V> vf = g.obtenerVertice(this, vFinal);
			Arco<L> a = new Arco(id, dato, p, vi, vf);
			castedGraph.getEdges().put(a.getId(), a);
		}
		catch(Error e) {
			return false;
		}
		return true;
	}

	/**
	 * Elimina el arco en el grafo que esté identificado con id. Se retorna true en caso que se haya eliminado
	 * el arco del grafo y false en caso que no exista un arco con ese identificador en el grafo.
	 */
	public boolean eliminarArco(Grafo<V,L> g, String id) {
		GrafoDirigido<V,L> castedGraph = (GrafoDirigido<V,L>)g;
		if (castedGraph.getEdges().remove(id) != null) {
			return true;
		}
		return false;
	}

	/**
	 * Devuelve el arco que tiene como identificador id. En caso de que no exista ningún arco con ese
	 * identificador, se lanza la excepción NoSuchElementException.
	 */
	public Arco obtenerArco(Grafo<V,L> g, String id) throws NoSuchFieldException {
		GrafoDirigido<V,L> castedGraph = (GrafoDirigido<V,L>)g;
		Arco<L> a = (Arco<L>)castedGraph.getEdges().get(id);
		if (a == null) {
			throw new NoSuchElementException();
		}
		return a;
	}

	/**
	 * Calcula el grado interior del vértice identificado por id en el grafo. En caso de que no exista ningún
	 * vértice con ese identificador, se lanza la excepción NoSuchElementException.
	 */
	public int gradoInterior(Grafo<V,L> g, String id) throws NoSuchElementException {
		GrafoDirigido<V,L> castedGraph = (GrafoDirigido<V,L>)g;

		try {
			if (!castedGraph.estaVertice(this, id)) {
				throw new NoSuchElementException();
			}
		}
		catch(NoSuchElementException e) {
			System.out.println("El vértice especificado no existe");
		}

		int grado = 0;
		ArrayList<Lado<L>> lados = castedGraph.lados(this);
		for (Lado<L> l : lados) {
			Arco<L> a = (Arco<L>)l;
			if (a.getExtremoFinal().getId() == id) {
				grado++;
			}
		}

		return grado;
	}

	/**
	 * Calcula el grado exterior del vértice identificado por id en el grafo. En caso de que no exista ningún
	 * vértice con ese identificador, se lanza la excepción NoSuchElementException.
	 */
	public int gradoExterior(Grafo<V,L> g, String id) throws NoSuchElementException {
		GrafoDirigido<V,L> castedGraph = (GrafoDirigido<V,L>)g;

		if (!castedGraph.estaVertice(this, id)) {
			throw new NoSuchElementException();
		}

		int grado = 0;
		ArrayList<Lado<L>> lados = castedGraph.lados(this);
		for (Lado<L> l : lados) {
			Arco<L> a = (Arco<L>)l;
			if (a.getExtremoFinal().getId() == id) {
				grado++;
			}
		}

		return grado;
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
