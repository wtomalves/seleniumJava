package br.com.alura.leilao.login;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import junit.framework.Assert;
import org.junit.jupiter.api.Assertions;

public class LoginTest {
	
	private LoginPage paginaDeLogin;
	
	@BeforeEach
	public void beforeEach() {
		this.paginaDeLogin = new LoginPage();	
	}
	
	@AfterEach
	public void afterEach() {
		//Abrir o navegador, passar a URL e fechar o navegador
		this.paginaDeLogin.fechar();
	}
	
	@Test
	public void efetuarLoginComDadosValidos() {						
		//Encontrar elementos na página
		paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");		
		paginaDeLogin.efetuaLogin();
		//verique se eu não estou mais na página de login
		Assert.assertFalse(paginaDeLogin.EPaginaDeLogin()); 
		Assert.assertEquals("fulano", paginaDeLogin.pegueNomeUsuarioLogado());
				
	}
	
	@Test
	public void efetuarLoginComDadosInválidos() {				
		//Encontrar elementos na página
		paginaDeLogin.preencheFormularioDeLogin("fulanisBeltranis", "passaasspsspspp");
		paginaDeLogin.efetuaLogin();
		
		Assert.assertTrue(paginaDeLogin.EPaginaDeLoginComDadosInvalidos()); 
		Assert.assertNull(paginaDeLogin.pegueNomeUsuarioLogado());			
		Assert.assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos."));
		
	}
	
	@Test
	public void nãoAcessarPaginaRestritaSemAutenticacao() {
		paginaDeLogin.navegaParaPaginaDeLances();	
		Assert.assertTrue(paginaDeLogin.EPaginaDeLogin());
		Assert.assertFalse(paginaDeLogin.contemTexto("Dados do Leilão"));
	}	
	
}
