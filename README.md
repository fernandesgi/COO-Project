# COO-Project

[PROJETO]

  Para aplicarmos os conceitos de orientação a objetos vistos em aula, implementaremos um sistema de biblioteca compartilhada. A ideia geral é gerenciar o empréstimo de livros entre pessoas de um grupo que possuem
interesses em comum e que frequentem um mesmo local. Por exemplo, o grupo dos alunos do curso de Sistemas de Informação da EACH-USP. Cada usuário terá a responsabilidade de cadastrar no sistema os livros que deseja colocar à disposição para empréstimo e, além disso, poderá solicitar empréstimos aos outros usuários.

  Suporemos que existirá uma entidade (subgrupo) responsável por fazer a mediação entre os usuários nos instantes de entrega
e devolução. No exemplo mencionado, o papel do mediador poderia ser desempenhado por alguns membros do centro acadêmico.

O fluxo normal de um empréstimo será o seguinte: 

(1) um usuário solicita, através do sistema, o empréstimo de um livro; 

(2) o proprietário do livro recebe uma notificação do sistema e indica, através do sistema, que aceita fazer o empréstimo; 

(3) o proprietário do livro leva o exemplar solicitado até o mediador, que dá baixa no sistema; 

(4) o usuário que solicitou o empréstimo se dirige ao mediador, que entrega-lhe o livro e dá baixa no sistema;

(5) após consultar o livro dentro do prazo predeterminado, o usuário o devolve ao mediador, que dá baixa no sistema;

(6) o proprietário do livro é notificado pelo sistema de que seu livro foi devolvido; 

(7) o proprietário do livro se dirige ao mediador, que lhe devolve o livro e dá baixa no sistema; (8) o proprietário do livro indica no sistema qual foi o estado em que seu livro foi devolvido (em perfeito estado, rasgado, riscado, etc...) – tais informações devem ser utilizadas para gerar m sistema de classificação dos usuários com base em seus históricos. Além do fluxo normal, muitos outros fluxos podem ocorrer. Alguns exemplos são: o proprietário pode não querer emprestar o livro;

(8) o usuário que pegou o livro pode não devolvê-lo; o usuário que fez a solicitação do empréstimo pode cancelá-la antes de pegar o livro; o proprietário que indicou que emprestaria o livro pode cancelar tal ação antes de se dirigir ao mediador;

(9) o proprietário que indicou que emprestaria o livro pode não entregá-lo ao mediador; etc...

[EMPRESTIMO]

  Fazer um sitemas que funciona como uma biblioteca, você procura o livro e ve quantos exemplares tem no sistema e quando alguem quiser emprestar, aparece o perfil dessa pessoa e voce pode aceitar ou nao.


[DEVERES]

  Estudar MySQL e JDBC (Apostila Caelum - Desenvolvimento Web)
   - Saber acessar um banco de dados.
