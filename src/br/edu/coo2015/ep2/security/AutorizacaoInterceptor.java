package br.edu.coo2015.ep2.security;

import java.io.Serializable;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.edu.coo2015.ep2.controller.LoginController;
import br.edu.coo2015.ep2.controller.UsuarioSession;

@Intercepts
public class AutorizacaoInterceptor implements Interceptor, Serializable {

	private static final long serialVersionUID = 1L;
	private final UsuarioSession usuario;
	private final Result result;

	public AutorizacaoInterceptor(UsuarioSession usuario, Result result) {
		this.usuario = usuario;
		this.result = result;
	}

	private boolean ehRestrito(ResourceMethod method) {
		return method.containsAnnotation(RestritoUsuarioCadastrado.class);
	}

	public boolean accepts(ResourceMethod method) {
		if (!usuario.estaLogado() && ehRestrito(method)) {
			return true;
		}

		return false;
	}

	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object resourceInstance) throws InterceptionException {
		result.redirectTo(LoginController.class)
				.mostraMensagem(
						"Para acessar esse conteudo, o usuario precisa estar logado no sistema.");
	}
}