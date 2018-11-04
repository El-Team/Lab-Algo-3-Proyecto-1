/**
 * Programa cliente que permite hacer uso de las funcionalidades de las clases
 * que implementan la interfaz Grafo.
 */
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.IllegalArgumentException;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;
import java.util.ArrayList;
import java.nio.charset.Charset;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.lang.reflect.Method;
import java.lang.IllegalAccessException;
import java.lang.NoSuchMethodException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.lang.Character;
import java.lang.Double;
import java.lang.Boolean;

public class ClienteGrafo {

	/**
	 * Instancia de Grafo que será manejada a través del cliente
	 */
	private static Grafo g;
	/**
	 * Tipo de grafo (D para dirigido, N para no dirigido)
	 */
	private static String graphType;
	/**
	 * Tipo del dato del véritce (D para doble, B para booleano, S para cadena
	 * de texto)
	 */
	private static String vertexType;
	/**
	 * Tipo del dato del lado (D para doble, B para booleano, S para cadena de
	 * texto)
	 */
	private static String edgeType;
	/**
	 * Denota si la cónsola para comandos debe mostrarse
	 */
	private static Boolean sessionIsActive = true;
	/**
	 * Denota si la cónsola para especificar tipo de grafo debe mostrarse
	 */
	private static Boolean sessionForNoArgumentsIsActive = true;
	/**
	 * Lector para los comandos que introducirá el usuario
	 */
	private static Scanner reader = new Scanner(System.in);


	/**
	 * Imprime la documentación completa
	 */
	private static void printDocumentation() {
		System.out.println(
			"El cliente soporta los siguientes comandos:\n" +
			"    clone()\n" +
			"    numeroDeVertices()\n" +
			"    numeroDeLados()\n" +
			"    vertices()\n" +
			"    lados()\n" +
			"    toString()\n" +
			"    estaVertice(<id>)\n" +
			"    eliminarVertice(<id>)\n" +
			"    eliminarArco(<id>)\n" +
			"    eliminarArista(<id>)\n" +
			"    obtenerVertice(<id>)\n" +
			"    grado(<id>)\n" +
			"    gradoInterior(<id>)\n" +
			"    gradoExterior(<id>)\n" +
			"    adyacentes(<id>)\n" +
			"    incidentes(<id>)\n" +
			"    obtenerArco(<id>)\n" +
			"    obtenerArista(<id>)\n" +
			"    sucesores(<id>)\n" +
			"    predecesores(<id>)\n" +
			"    estaLado(<v1>, <v2>)\n" +
			"    agregarVertice(<id>, <dato>, <peso>)\n" +
			"    agregarArco(<id>, <dato>, <peso>, <vInicial>, <vFinal>)\n" +
			"    agregarArista(<id>, <dato>, <peso>, <v1>, <v2>)"
		);
	}

	/**
	 * Analiza la cadena de texto introducida por el usuario y de ser válida,
	 * devuelve una lista cuya primera entrada representa el método a ejecutar
	 * y de haber otras entradas estas representararán los argumentos que acepta
	 * el método a ejecutar.
	 */
	private static List<String> parse(String input){

		List<String> parsedInput = new ArrayList();

		if (input.equals("q")) {
			parsedInput.add("quit");
		}
		else if (input.equals("h")) {
			parsedInput.add("help");
		}
		else {
			// Entrada de la forma <método>([args...])
			String regexStr = "[a-zA-Z]{4,40}(\\()(,?\\s?\\S+){0,4}(\\))$";
			Pattern regexPattern = Pattern.compile(regexStr);
			Matcher match = regexPattern.matcher(input);

			if (match.matches()) {
				int endOfCommand = input.indexOf("(");
				String command = input.substring(0, endOfCommand);
				parsedInput.add(command);

				// Parsear argumentos en caso de que hayan
				if (endOfCommand != (input.length() - 2)) {
					String[] rawArgs = input
						.substring(
							endOfCommand + 1,
							input.length() - 1
						)
						.split(",");
					for (String arg : rawArgs) {
						parsedInput.add(arg.trim());
					}
				}
			}
			else {
				parsedInput.add("invalidInput");
			}
		}

		return parsedInput;
	}

	/**
	 * Recibe un comando con sus argumentos y se encarga de ejecutarlo,
	 * retornando un diccionario que contiene la siguiente información: <br>
	 * commandExecutedSuccessfully: booleano que indica si el comando se
	 * ejecutó sin problemas <br>
	 * requiresToPrintCurrentGraph: booleano que indica si el comando requiere
	 * que se imprima el estado del grafo
	 *
	 */
	private static LinkedHashMap<String, Boolean> executeCommand(List<String> parsedCommand) {

		String command = parsedCommand.get(0);
		LinkedHashMap result = new LinkedHashMap();
		result.put("requiresToPrintCurrentGraph", false);

		// Comandos que no toman argumentos
		if (parsedCommand.size() == 1) {
			try {
				Method method = g.getClass().getMethod(command, Grafo.class);

				// Para los comandos:
				// clone()
				if (command.equals("clone")) {
					Grafo clone = (Grafo)method.invoke(g, g);
					System.out.println("Grafo clonado:\n" + clone.toString(clone));
				}
				// Para los comandos:
				// numeroDeVertices()
				// numeroDeLados()
				// vertices()
				// lados()
				// toString()
				else {
					System.out.println(method.invoke(g, g).toString());
				}
				result.put("commandExecutedSuccessfully", true);
			}
			catch(NoSuchMethodException e) {
				System.out.println("NoSuchMethodException");
				result.put("commandExecutedSuccessfully", false);
			}
			catch(IllegalAccessException e) {
				System.out.println("IllegalAccessException");
				result.put("commandExecutedSuccessfully", false);
			}
			catch(InvocationTargetException e) {
				System.out.println("InvocationTargetException" + e.getCause());
				result.put("commandExecutedSuccessfully", false);
			}
		}

		// Comandos que toman un argumento
		else if (parsedCommand.size() == 2) {
			try {
				Method method = g.getClass().getMethod(
					command,
					Grafo.class,
					String.class
				);
				String arg1 = parsedCommand.get(1);

				// Para los comandos:
				// estaVertice(<id>)
				if (command.equals("eliminarVertice")) {
					System.out.println(method.invoke(g, g, arg1).toString());
				}
				// Para los comandos:
				// eliminarVertice(<id>)
				// eliminarArco(<id>)
				// eliminarArista(<id>)
				else if (
					command.equals("eliminarVertice") ||
					command.equals("eliminarArco") ||
					command.equals("eliminarArista")
				) {
					if ((Boolean)method.invoke(g, g, arg1)) {
						System.out.println("El elemento ha sido eliminado");
						result.put("requiresToPrintCurrentGraph", true);
					}
					else {
						System.out.println(
							"No se encontró el elemento que se deseaba eliminar"
						);
					}
				}
				// Para los comandos:
				// obtenerVertice(<id>)
				// grado(<id>)
				// gradoInterior(<id>)
				// gradoExterior(<id>)
				// adyacentes(<id>)
				// incidentes(<id>)
				// obtenerArco(<id>)
				// obtenerArista(<id>)
				// sucesores(<id>)
				// predecesores(<id>)
				else {
					try {
						System.out.println(method.invoke(g, g, arg1).toString());
					}
					catch(InvocationTargetException e) {
						System.out.println(
							"No se encontró el elemento especificado en el argumento"
						);
					}
				}
				result.put("commandExecutedSuccessfully", true);
			}
			catch(NoSuchMethodException e) {
				System.out.println("NoSuchMethodException");
				result.put("commandExecutedSuccessfully", false);
			}
			catch(IllegalAccessException e) {
				System.out.println("IllegalAccessException");
				result.put("commandExecutedSuccessfully", false);
			}
			catch(InvocationTargetException e) {
				System.out.println("InvocationTargetException");
				result.put("commandExecutedSuccessfully", false);
			}
		}

		// Comandos que toman dos argumentos
		else if (parsedCommand.size() == 3) {
			try {
				Method method = g.getClass().getMethod(
					command,
					Grafo.class,
					String.class,
					String.class
				);
				String arg1 = parsedCommand.get(1);
				String arg2 = parsedCommand.get(2);

				// Para los comandos:
				// estaLado(<v1>, <v2>)
				System.out.println(method.invoke(g, g, arg1, arg2).toString());
				result.put("commandExecutedSuccessfully", true);
			}
			catch(NoSuchMethodException e) {
				System.out.println("NoSuchMethodException");
				result.put("commandExecutedSuccessfully", false);
			}
			catch(IllegalAccessException e) {
				System.out.println("IllegalAccessException");
				result.put("commandExecutedSuccessfully", false);
			}
			catch(InvocationTargetException e) {
				System.out.println("InvocationTargetException");
				result.put("commandExecutedSuccessfully", false);
			}
		}

		// Comandos que toman 3 argumentos
		else if (parsedCommand.size() == 4) {
			try {
				String arg1 = parsedCommand.get(1);
				Double arg3 = Double.parseDouble(parsedCommand.get(3));
				Boolean newElementAdded = false;

				// Para los comandos:
				// agregarVertice(<id>, <dato>, <peso>)
				if (command.equals("agregarVertice")) {
					switch(vertexType) {
						case "B":
							Boolean arg2B = Boolean.parseBoolean(parsedCommand.get(2));
							newElementAdded = g.agregarVertice(g, arg1, arg2B, arg3);
							break;
						case "D":
							Double arg2D = Double.parseDouble(parsedCommand.get(2));
							newElementAdded = g.agregarVertice(g, arg1, arg2D, arg3);
							break;
						case "S":
							String arg2S = parsedCommand.get(2);
							newElementAdded = g.agregarVertice(g, arg1, arg2S, arg3);
							break;
					}
				}
				else {
					throw new NoSuchMethodException();
				}

				if (newElementAdded) {
					System.out.println("Vértice agregado con éxito");
				}
				else {
					System.out.println("El nuevo vértice no pudo ser agregado");
				}
				result.put("commandExecutedSuccessfully", true);
				result.put("requiresToPrintCurrentGraph", true);
			}
			catch(NoSuchMethodException e) {
				System.out.println("NoSuchMethodException");
				result.put("commandExecutedSuccessfully", false);
			}
		}

		// Comandos que toman 5 argumentos
		else if (parsedCommand.size() == 6) {
			try {
				Method method = null;
				switch(edgeType) {
					case "B":
						method = g.getClass().getMethod(
							command,
							Grafo.class,
							String.class,
							Boolean.class,
							Double.class,
							String.class,
							String.class
						);
						break;
					case "D":
						method = g.getClass().getMethod(
							command,
							Grafo.class,
							String.class,
							Double.class,
							Double.class,
							String.class,
							String.class
						);
						break;
					case "S":
						method = g.getClass().getMethod(
							command,
							Grafo.class,
							String.class,
							String.class,
							Double.class,
							String.class,
							String.class
						);
						break;
				}

				String arg1 = parsedCommand.get(1);
				String arg2 = parsedCommand.get(2);
				String arg3 = parsedCommand.get(3);
				String arg4 = parsedCommand.get(4);
				String arg5 = parsedCommand.get(5);

				// Para los comandos:
				// agregarArco(<id>, <dato>, <peso>, <vInicial>, <vFinal>)
				// agregarArista(<id>, <dato>, <peso>, <v1>, <v2>)
				if ((Boolean)method.invoke(g, g, arg1, arg2, arg3, arg4, arg5)) {
					System.out.println("Lado agregado con éxito");
				}
				else {
					System.out.println("El nuevo lado no pudo ser agregado");
				}
				result.put("commandExecutedSuccessfully", true);
				result.put("requiresToPrintCurrentGraph", true);
			}
			catch(NoSuchMethodException e) {
				System.out.println("NoSuchMethodException");
				result.put("commandExecutedSuccessfully", false);
			}
			catch(IllegalAccessException e) {
				System.out.println("IllegalAccessException");
				result.put("commandExecutedSuccessfully", false);
			}
			catch(InvocationTargetException e) {
				System.out.println("InvocationTargetException");
				result.put("commandExecutedSuccessfully", false);
			}
		}

		// Comando inválido:
		else {
			result.put("commandExecutedSuccessfully", false);
		}

		return result;
	}

	/**
	 * Crea un grafo vacío y lo asigna a g
	 */
	private static void initializeGraph(String graphType, String vertexAndEdgeType) {
		if (graphType.equals("D")) {
			switch (vertexAndEdgeType) {
				case "BB": g = new GrafoDirigido<Boolean, Boolean>(); break;
				case "BD": g = new GrafoDirigido<Boolean, Double>(); break;
				case "BS": g = new GrafoDirigido<Boolean, String>(); break;
				case "DB": g = new GrafoDirigido<Double, Boolean>(); break;
				case "DD": g = new GrafoDirigido<Double, Double>(); break;
				case "DS": g = new GrafoDirigido<Double, String>(); break;
				case "SB": g = new GrafoDirigido<String, Boolean>(); break;
				case "SD": g = new GrafoDirigido<String, Double>(); break;
				case "SS": g = new GrafoDirigido<String, String>(); break;
			}
		}
		else {
			switch (vertexAndEdgeType) {
				case "BB": g = new GrafoNoDirigido<Boolean, Boolean>(); break;
				case "BD": g = new GrafoNoDirigido<Boolean, Double>(); break;
				case "BS": g = new GrafoNoDirigido<Boolean, String>(); break;
				case "DB": g = new GrafoNoDirigido<Double, Boolean>(); break;
				case "DD": g = new GrafoNoDirigido<Double, Double>(); break;
				case "DS": g = new GrafoNoDirigido<Double, String>(); break;
				case "SB": g = new GrafoNoDirigido<String, Boolean>(); break;
				case "SD": g = new GrafoNoDirigido<String, Double>(); break;
				case "SS": g = new GrafoNoDirigido<String, String>(); break;
			}
		}
	}

	/**
	 * Crea un grafo sin introducir datos
	 */
	private static void createEmptyGraph(String input) {

		graphType = Character.toString(input.charAt(0));
		vertexType = Character.toString(input.charAt(3));
		edgeType = Character.toString(input.charAt(6));
		String vertexAndEdgeType = vertexType + edgeType;

		initializeGraph(graphType, vertexAndEdgeType);
	}

	/**
	 * Cliente ejecutado cuando el usuario no pasa argumentos
	 */
	private static void displayClientForNoArguments() {

		while(sessionForNoArgumentsIsActive) {

			// Pedir instrucción al usuario
			System.out.println(
				"\nPara comenzar introduzca el tipo de grafo que desea crear " +
				"usando la siguiente sintaxis:\n" +
				"    <tipoDeGrafo>, <tipoDeVertice>, <tipoDeLado>\n"
			);
			String nextCommand = reader.nextLine();

			// Ejecutar comando introducido
			if (nextCommand != null) {
				String regexStr = "(N|D),\\s(B|D|S),\\s(B|D|S)$";
				Pattern regexPattern = Pattern.compile(regexStr);
				Matcher match = regexPattern.matcher(nextCommand);

				if (match.matches()) {
					createEmptyGraph(nextCommand);
					sessionForNoArgumentsIsActive = false;
				}
				else {
					System.out.println("Entrada inválida");
				}
			}
		}
		displayPrompt();
	}

	/**
	 * Crea un grafo a partir de un archivo de texto
	 */
	private static void createGraphFrom(String filename) {

		// Abrir archivo de texto
		List<String> lines = null;
		try {
			lines = Files.readAllLines(
				Paths.get(filename),
				Charset.defaultCharset()
			);
		}
		catch(IOException e) {
			e.printStackTrace();
		}

		// Obtener información básica
		graphType = lines.get(2);
		vertexType = lines.get(0);
		edgeType = lines.get(1);

		String vertexAndEdgeType = vertexType + edgeType;
		String n = lines.get(3);
		String m = lines.get(4);

		// Inicializar y cargar grafo acorde al tipo
		initializeGraph(graphType, vertexAndEdgeType);
		g.cargarGrafo(g, filename);
	}

	/**
	 * Cónsola que muestra las opciones disponibles y permite al usuario
	 * interactuar con el grafo cargado
	 */
	private static void displayPrompt() {

		while(sessionIsActive) {
			// Pedir instrucción al usuario
			System.out.println(
				"\nIntroduzca el nombre de la intrucción que desea ejecutar " +
				"sobre el grafo importado tal cual aparece en la " +
				"documentación o introduzca \"q\" para finalizar"
			);
			String nextCommand = reader.nextLine();

			// Ejecutar comando introducido
			if (nextCommand != null) {
				List<String> parsedCommand = parse(nextCommand);

				if (parsedCommand.get(0) == "quit") {
					sessionIsActive = false;
				}
				else if (parsedCommand.get(0) == "help") {
					printDocumentation();
				}
				else if (parsedCommand.get(0) == "invalidInput") {
					System.out.println(
						"Entrada inválida, introduzca \"h\" si desea ver la " +
						"documentación"
					);
				}
				else {
					LinkedHashMap<String, Boolean> execResult = executeCommand(parsedCommand);

					if (execResult.get("commandExecutedSuccessfully")) {
						System.out.println("Comando ejecutado con éxito");

						if (execResult.get("requiresToPrintCurrentGraph")) {
							System.out.println(
								"Estado actual del grafo:\n" + g.toString(g)
							);
						}
					}
					else {
						System.out.println(
							"El comando introducido es incorrecto, verifique " +
							"que usó la sintaxis correcta"
						);
					}
				}
			}
		}

		reader.close();
		System.out.println("Пока! 👋😊");
		System.exit(0);
	}

	/**
	 * Cliente ejecutado cuando el usuario pasa argumentos
	 */
	private static void displayClientForArguments(String[] args) {

		String filename = args[0];

		try {
			if (args.length > 1) {
				throw new IllegalArgumentException();
			}
			else if (!Utilidades.isValidPath(filename)) {
				throw new FileNotFoundException();
			}
			else if (!Utilidades.documentHasValidFormat(filename)) {
				throw new ParseException("", 0);
			}
		}
		catch(IllegalArgumentException e) {
			System.out.println("Introduzca únicamente el nombre del archivo como argumento");
			System.exit(0);
		}
		catch(FileNotFoundException e) {
			System.out.println("No fue posible importar el archivo, verifique que el nombre es el correcto");
			System.exit(0);
		}
		catch(ParseException e) {
			System.out.println("El documento no tiene el formato correcto");
			System.exit(0);
		}

		createGraphFrom(filename);
		displayPrompt();
	}

	/**
	 * Rutina principal, se encarga de mostrar el cliente correcto dependiendo
	 * del número de argumentos detectados
	 */
	public static void main(String[] args) {

		if (args.length == 0) {
			displayClientForNoArguments();
		}
		else {
			displayClientForArguments(args);
		}
	}
}
