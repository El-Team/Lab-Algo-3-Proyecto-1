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
import java.nio.charset.Charset;
import java.io.IOException;

public class ClienteGrafo {

	private static void displayClientForNoArguments() {
		System.out.println("No argument");
	}

	private static void displayClientForArguments(String[] args) {
		try {
			if (args.length > 1) {
				throw new IllegalArgumentException();
			}
			else if (!Utilidades.isValidPath(args[0])) {
				throw new FileNotFoundException();
			}
			else if (!Utilidades.documentHasValidFormat(args[0])) {
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
