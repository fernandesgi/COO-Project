public class TestaDaos {

	public static void main(String[] args) throws Exception {
		LivroDao livroDao = new LivroDaoFactory().getInstance();
		
		Livro l1 = new Livro(1, "Paulo Coelho", "Brida", "Editora Abril");
		Livro l2 = new Livro(2, "Paulo Coelho", "O Alquimista", "Editora Abril");
		Livro l3 = new Livro(3, "Paulo Coelho", "O di‡rio de um mago", "Editora Abril");

		livroDao.save(l1);
		livroDao.save(l2);
		livroDao.save(l3);
		
		System.out.println(livroDao.search(1));
		System.out.println(livroDao.search(2));
		System.out.println(livroDao.search(3));
	}
}
