/**
 * Biblioteca de métodos de uso común.
 */
import java.io.File;
import java.util.List;
import java.lang.Integer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.io.IOException;
import java.lang.NumberFormatException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Utilidades {

	/**
	 * Verifica que el archivo exista
	 */
	public static boolean isValidPath(String filename) {
		File tmpFile = new File(filename);
		if (tmpFile.exists()) {
			return true;
		}
		return false;
	}

	/**
	 * Verifica que el archivo tenga el formato correcto
	 */
	public static boolean documentHasValidFormat(String filename) {
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
		}

		String regexStr =
			"(B|D|S),(B|D|S),(D|N),[0-9]+,[0-9]+,((\\S)+\\s(\\S)+\\s[0-9]+,){" +
			n.toString() + "}((\\S)+\\s(\\S)+\\s[0-9]+\\s(\\S)+\\s(\\S)+,){" +
			m.toString() + "}";
		Pattern regexPattern = Pattern.compile(regexStr);
		Matcher match = regexPattern.matcher(fileContent);

		return match.matches();
	}
}