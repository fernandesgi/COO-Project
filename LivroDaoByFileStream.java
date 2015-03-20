import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class LivroDaoByFileStream implements LivroDao {

	// final porque sao constantes, soh pra leitura
	private static final int FIELD_LENGHT = 50; // Tamanho de campo de registro
	private static final char EMPTY_CHAR = '*'; // Char que completa String , trocar por espaço
	private static final String FILE_EXTENSION = ".dat";
	private static final String PATH = ""; // Diretorio qeu ta salvando
	private byte[] bytes = new byte[FIELD_LENGHT]; // Leitor de Bytes
	
	// Salvar no arquivo, usa a classe abstract e a classe StringUtils(manipuladora de String)
	@Override
	public void save(Livro livro) throws Exception {
		OutputStream fos = new FileOutputStream(PATH + livro.getId() + FILE_EXTENSION);
		fos.write(StringUtils.fillOrTruncate(livro.getAutor(), FIELD_LENGHT, EMPTY_CHAR).getBytes());
		fos.write(StringUtils.fillOrTruncate(livro.getTitulo(), FIELD_LENGHT, EMPTY_CHAR).getBytes());
		fos.write(StringUtils.fillOrTruncate(livro.getEditora(), FIELD_LENGHT, EMPTY_CHAR).getBytes());
		fos.close();
	}

	@Override
	public void update(Livro livro) throws Exception {
		remove(livro);
		save(livro);
	}

	@Override
	public Livro search(long id) throws Exception {
		File file = new File(PATH + id + FILE_EXTENSION);

		if (!file.exists() || !file.isFile()) {
			return null;
		}

		InputStream fis = new FileInputStream(file);
		Livro livro = new Livro();

		// le todos os campos
		fis.read(bytes); // talvez eteja reeado
		livro.setid((new String(bytes)); // talvez esteja errado
		fis.read(bytes);
		livro.setAutor(new String(bytes));
		fis.read(bytes);
		livro.setTitulo(new String(bytes));
		fis.read(bytes);
		livro.setEditora(new String(bytes));

		fis.close();
		return livro;
	}

	@Override
	public void remove(Livro livro) throws Exception {
		File file = new File(PATH + livro.getId() + FILE_EXTENSION);
		if (file.exists() && file.isFile()) {
			file.delete();
		}
	}
}
