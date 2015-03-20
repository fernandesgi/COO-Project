public class LivroDaoFactory {

	// Devolve um LivroDao (interface)
	public LivroDao getInstance() {
		return new LivroDaoByObjectOutputStream();
		//return new LivroDaoByRandomAccessFile();
		//return new LivroDaoBySequencialAccessByteFile();
	}
}
