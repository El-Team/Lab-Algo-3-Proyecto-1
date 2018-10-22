/**
 * Contiene las operaciones asociadas a un grafo, sea dirigido o no dirigido.
 */

public interface Grafo<V, L> {

	/**
	 * Carga en un grafo la información almacenada en el archivo de texto cuya dirección, incluyendo el
	 * nombre del archivo, viene dada por archivo. El archivo dado tiene un formato determinado que se
	 * indicará más abajo. Se retorna true si los datos del archivo son cargados satisfactoriamente en el
	 * grafo, y false en caso contrario. Este método debe manejar los casos en los que haya problemas al
	 * abrir un archivo y el caso en el que el formato del archivo sea incorrecto.
	 */
	public boolean cargarGrafo(Grafo<V,L> g, String archivo);

	/**
	 * Indica el número de vértices que posee el grafo.
	 */
	public int numeroDeVertices(Grafo<V,L> g);

	/**
	 * Indica el número de Lados que posee el grafo.
	 */
	public int numeroDeLados(Grafo<V,L> g);

	/**
	 * Agrega el vértice v previamente creado al grafo g previamente creado. Si en el grafo no hay vértice
	 * con el mismo identificador que el vértice v, entonces lo agrega al grafo y retorna true, de lo
	 * contrario retorna false.
	 */
	public boolean agregarVertice(Grafo<V,L> g, Vértice<V> v);

	/**
	 * Crea un vértice con las características dadas y las agrega al grafo g previamente creado. Si en el grafo
	 * no hay vértice con el identificador id, entonces se crea un nuevo vértice y se agrega al grafo y se
	 * retorna true, de lo contrario retorna false.
	 */
	public boolean agregarVertice(Grafo<V,L> g, String id, V dato, double p);

	/**
	 * Retorna el vértice contenido en el grafo que posee el identificador id. En caso que en el grafo no
	 * contenga ningún vértice con el identificador id, se lanza la excepción
	 * NoSuchElementException.
	 */
	public Vertice<V> obtenerVertice(Grafo<V,L> g, String id);

	/**
	 * Se indica si un vértice con el identificador id, se encuentra o no en el grafo. Retorna true en caso de
	 * que el vértice pertenezca al grafo, false en caso contrario.
	 */
	public boolean pestaVertice(Grafo<V,L> g, String id);

	/**
	 * Determina si un lado pertenece a un grafo. La entrada son los identificadores de los vértices que son
	 * los extremos del lado. En caso de ser aplicada esta función con un grafo dirigido, se tiene que u
	 * corresponde al extermo inicial y v al extermo final.
	 */
	public boolean estaLado(Grafo<V,L> g, String u, String v);

	/**
	 * Elimina el vértice del grafo g. Si existe un vértice identificado con id y éste es eliminado exitosamente
	 * del grafo se retorna true, en caso contrario false.
	 */
	public boolean eliminarVertice(Grafo<V,L> g, String id);

	/**
	 * Retorna una lista con los vértices del grafo g.
	 */
	public List<Vertice<V>> vertices(Grafo<V,L> g);

	/**
	 * Retorna una lista con los lados del grafo g.
	 */
	public List<Lado<L>> lados(Grafo<V,L> g);

	/**
	 * Calcula el grado del vértice identificado por id en el grafo g. En caso que en el grafo no contenga
	 * ningún vértice con el identificador id, se lanza la excepción NoSuchElementException.
	 */
	public int grado(Grafo<V,L> g, String id);

	/**
	 * Obtiene los vértices adyacentes al vértice identicado por id en el grafo g y los retorna en una lista. En
	 * caso que en el grafo no contenga ningún vértice con el identificador id, se lanza la excepción
	 * NoSuchElementException.
	 */
	public List<Vertice<V>> adyacentes(Grafo<V,L> g, String id);

	/**
	 * Obtiene los lados incidentes al vértice identificado por id en el grafo g y los retorna en una lista. En
	 * caso que en el grafo no contenga ningún vértice con el identificador id, se lanza la excepción
	 * NoSuchElementException.
	 */
	public List<Lado<E>> incidentes(Grafo<V,L> g, String id);

	/**
	 * Retorna un nuevo grafo con la misma composición que el grafo de entrada.
	 */
	public Grafo clone(Grafo<V,L> g);

	/**
	 * Devuelve una representación del contenido del grafo como una cadena de caracteres.
	 */
	public String toString(Grafo<V,L> g);

}
