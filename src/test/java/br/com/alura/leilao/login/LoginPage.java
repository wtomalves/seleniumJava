package br.com.alura.leilao.login;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.alura.leilao.leiloes.LeiloesPage;
import junit.framework.Assert;

public class LoginPage {
	
	private static final String URL_LOGIN = "http://localhost:8080/login";
	private WebDriver browser;	
	
	public LoginPage() {
		//Abrir o navegador, passar a URL e fechar o navegador
		this.browser = new ChromeDriver();
		browser.navigate().to(URL_LOGIN);
	}

	public void fechar() {
		this.browser.quit();
		
	}
	
	public void preencheFormularioDeLogin(String username, String password) {
			
		browser.findElement(By.id("username")).sendKeys(username);
		browser.findElement(By.id("password")).sendKeys(password);
		
	}

	public LeiloesPage efetuaLogin() {
		browser.findElement(By.id("login-form")).submit();
//		try {
//			browser.manage().wait(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return new LeiloesPage(browser);
		
	}

	public boolean EPaginaDeLogin() {		
		return browser.getCurrentUrl().equals(URL_LOGIN);
	}

	public String pegueNomeUsuarioLogado() {		
		try {
			return browser.findElement(By.id("usuario-logado")).getText();
		} catch (NoSuchElementException e) {
			return null;
		}		
	}

	public void navegaParaPaginaDeLances() {
		browser.navigate().to("http://localhost:8080/leiloes/2");			
	}

	public boolean contemTexto(String texto) {
		return browser.getPageSource().contains(texto);
	}

	public boolean EPaginaDeLoginComDadosInvalidos() {	
		return browser.getCurrentUrl().equals(URL_LOGIN + "?error");
	}
}
	

