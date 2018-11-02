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

public class ClienteGrafo {

	/**
	 * Instancia de Grafo que será manejada a través del cliente
	 */
	private static Grafo g;
	private static String graphType;
	private static String vertexType;
	private static String edgeType;
	private static Boolean sessionIsActive = true;

	/**
	 * Imprime la documentación completa
	 */
	private static void printDocumentation() {
		System.out.println("printDocumentation");
	}

	/**
	 * Analiza la cadena de texto entrada por el usuario y de ser válida,
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
					String[] rawArgs =
						input.substring(endOfCommand + 1, input.length()).split(",");
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
	 * retornando un booleano que indica si la ejecución se realizó exitosamente
	 */
	private static boolean executeCommand(List<String> parsedCommand) {
		System.out.println("parsedCommand");
		//Method method = g.getClass().getMethod();

		if (parsedCommand.size() == 1) {

		}
		else if (parsedCommand.size() == 1) {

		}
		else if (parsedCommand.size() == 6) {

		}
		else {

		}
		return false;
	}

	/**
	 * Cliente ejecutado cuando el usuario no pasa argumentos
	 */
	private static void displayClientForNoArguments() {
		System.out.println("No argument");
	}

	private static void createGraph(String filename) {

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

		// Inicializar grafo acorde al tipo
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

		g.cargarGrafo(g, filename);
	}

	/**
	 * Cónsola que muestra las opciones disponibles y permite al usuario
	 * interactuar con el grafo cargado
	 */
	private static void displayPrompt() {
		Scanner reader = new Scanner(System.in);

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
					if (executeCommand(parsedCommand)) {
						System.out.println(g.getClass().getSimpleName());
						System.out.println(
							"Comando ejecutado con éxito\n" +
							"Estado actual del grafo:\n" + g.toString(g)
						);
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

		createGraph(filename);
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
