/**
 * Programa cliente que permite hacer uso de las funcionalidades de las clases
 * que implementan la interfaz Grafo.
 */
import java.lang.IllegalArgumentException;
import java.io.FileNotFoundException;
import java.text.ParseException;

public class ClienteGrafo {

	/**
	 * Verifica que el archivo exista
	 */
	private static boolean isValidPath(String filename) {
		// use a regex
		return true;
	}

	/**
	 * Verifica que el archivo tenga el formato correcto
	 */
	private static boolean documentHasValidFormat(String filename) {
		// use a regex
		return false;
	}

	private static void displayClientForNoArguments() {
		System.out.println("No argument");
	}

	private static void displayClientForArguments(String[] args) {
		try {
			if (args.length > 1) {
				throw new IllegalArgumentException();
			}
			else if (!isValidPath(args[0])) {
				throw new FileNotFoundException();
			}
			else if (!documentHasValidFormat(args[0])) {
				throw new ParseException("", 0);
			}
		}
		catch(IllegalArgumentException e) {
			System.out.println("Introduzca únicamente el nombre del archivo como argumento");
		}
		catch(FileNotFoundException e) {
			System.out.println("No fue posible importar el archivo, verifique que el nombre es el correcto");
		}
		catch(ParseException e) {
			System.out.println("El documento no tiene el formato correcto");
		}
	}

	public static void main(String[] args) {

		if (args.length == 0) {
			displayClientForNoArguments();
		}
		else {
			displayClientForArguments(args);
		}
	}
}
