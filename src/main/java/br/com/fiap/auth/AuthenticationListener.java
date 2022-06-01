package br.com.fiap.auth;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.fiap.model.User;

public class AuthenticationListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent arg0) {
		
		String page = pegarPaginaAtual();
		if (page.equals("/login.xhtml") || page.equals("/user_new.xhtml") ) return;
		
		User user = pegarUsuarioNaSessao();
		if (user != null) return;
		
		encaminharParaLogin();
	}

	private String pegarPaginaAtual() {
		return FacesContext
					.getCurrentInstance()
					.getViewRoot()
					.getViewId();
	}

	private void encaminharParaLogin() {
		FacesContext
			.getCurrentInstance()
			.getApplication()
			.getNavigationHandler()
			.handleNavigation(
					FacesContext.getCurrentInstance(), 
					null, 
					"login");
	}

	private User pegarUsuarioNaSessao() {
		return (User) FacesContext
			.getCurrentInstance()
			.getExternalContext()
			.getSessionMap()
			.get("user");
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
