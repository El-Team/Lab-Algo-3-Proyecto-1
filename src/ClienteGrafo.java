/**
 * Programa cliente que permite hacer uso de las funcionalidades de las clases
 * que implementan la interfaz Grafo.
 */
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.IllegalArgumentException;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;
import java.nio.charset.Charset;
import java.io.IOException;
import java.lang.NumberFormatException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.lang.Integer;

public class ClienteGrafo {

	/**
	 * Verifica que el archivo exista
	 */
	private static boolean isValidPath(String filename) {
		File tmpFile = new File(filename);
		if (tmpFile.exists()) {
			return true;
		}
		return false;
	}

	/**
	 * Verifica que el archivo tenga el formato correcto
	 */
	private static boolean documentHasValidFormat(String filename) {
		List<String> lines = null;
		String fileContent = "";
		Integer n = 0;
		Integer m = 0;

		try {
			lines = Files.readAllLines(
				Paths.get(filename),
				Charset.defaultCharset()
			);
			n = Integer.parseInt(lines.get(3));
			m = Integer.parseInt(lines.get(4));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(NumberFormatException e) {
			System.out.println("Número de vértices o lados inválido");
		}

		for (String line : lines) {
			fileContent += line;
			fileContent += ",";
			System.out.println(fileContent);
		}

		String regexStr =
			"(B|D|S),(B|D|S),(D|N),[0-9]+,[0-9]+,((\\S)+\\s(\\S)+\\s[0-9]+,){" +
			n.toString() + "}((\\S)+\\s(\\S)+\\s[0-9]+\\s(\\S)+\\s(\\S)+,){" +
			m.toString() + "}";
		Pattern regexPattern = Pattern.compile(regexStr);
		Matcher match = regexPattern.matcher(fileContent);

		return match.matches();
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
