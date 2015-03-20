import java.io.File;
import java.io.RandomAccessFile;

public class LivroDaoByRandomAccessFile implements LivroDao {

	private static final int STRING_FIELD_LENGHT = 50;
	private static final int NUMBER_OF_STRING_FIELDS = 3;
	private static final int LONG_FIELD_LENGHT = 8;
	private static final int NUMBER_OF_LONG_FIELDS = 1;
	private static final int REGISTER_LENGHT = (STRING_FIELD_LENGHT * NUMBER_OF_STRING_FIELDS)
			+ (LONG_FIELD_LENGHT * NUMBER_OF_LONG_FIELDS);

	private static final char EMPTY_CHAR = '*';
	private static final String ARQUIVO_DE_LIVROS = "Livros.dat";
	private byte[] bytes = new byte[STRING_FIELD_LENGHT];
	private RandomAccessFile raf;

	@Override
	public void save(Livro livro) throws Exception {
		// j‡ existe um registro com esse ID?
		if (search(livro.getId()) == null) {
			RandomAccessFile raf = new RandomAccessFile(new File(ARQUIVO_DE_LIVROS), "rw");
			raf.seek(raf.length());// posiciona no final do arquivo
			raf.writeLong(livro.getId());
			raf.write(StringUtils.fillOrTruncate(livro.getAutor(), STRING_FIELD_LENGHT, EMPTY_CHAR).getBytes());
			raf.write(StringUtils.fillOrTruncate(livro.getTitulo(), STRING_FIELD_LENGHT, EMPTY_CHAR).getBytes());
			raf.write(StringUtils.fillOrTruncate(livro.getEditora(), STRING_FIELD_LENGHT, EMPTY_CHAR).getBytes());
			raf.close();
		}
	}

	@Override
	public Livro search(long id) throws Exception {
		raf = new RandomAccessFile(new File(ARQUIVO_DE_LIVROS), "rw");

		while (hasNext()) {
			Livro livro = next();

			if (livro.getId() == id) {
				raf.close();
				return livro;
			}
		}

		raf.close();
		return null;
	}

	@Override
	public void update(Livro livro) throws Exception {
		raf = new RandomAccessFile(new File(ARQUIVO_DE_LIVROS), "rw");

		while (hasNext()) {
			Livro nextLivroInFile = next();

			if (livro.getId() == nextLivroInFile.getId()) {
				raf.seek(raf.getFilePointer() - REGISTER_LENGHT);
				raf.writeLong(livro.getId());
				raf.write(StringUtils.fillOrTruncate(livro.getAutor(), STRING_FIELD_LENGHT, EMPTY_CHAR).getBytes());
				raf.write(StringUtils.fillOrTruncate(livro.getTitulo(), STRING_FIELD_LENGHT, EMPTY_CHAR).getBytes());
				raf.write(StringUtils.fillOrTruncate(livro.getEditora(), STRING_FIELD_LENGHT, EMPTY_CHAR).getBytes());
				break;
			}
		}

		raf.close();
	}

	// Tem que melhorar!
	@Override
	public void remove(Livro livro) throws Exception {
		// TODO Auto-generated method stub
	}

	private boolean hasNext() throws Exception {
		return raf.getFilePointer() < raf.length();
	}

	private Livro next() throws Exception {
		Livro livro = new Livro();

		// l todos os campos
		livro.setId(raf.readLong());
		raf.read(bytes);
		livro.setAutor(new String(bytes));
		raf.read(bytes);
		livro.setTitulo(new String(bytes));
		raf.read(bytes);
		livro.setEditora(new String(bytes));

		return livro;
	}
}
