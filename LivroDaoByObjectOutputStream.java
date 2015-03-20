import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LivroDaoByObjectOutputStream implements LivroDao {

	private static final String PATH = "";
	private static final String FILE_EXTENSION = ".obj"; 
	
	@Override
	public void save(Livro livro) throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(PATH + livro.getId() + FILE_EXTENSION)));
		oos.writeObject(livro);
		oos.close();
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

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		Livro livro = (Livro) ois.readObject();
		ois.close();
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
