package br.com.alura.leilao.leiloes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.alura.leilao.login.LoginPage;
import junit.framework.Assert;
import org.junit.jupiter.api.Assertions;

public class LeiloesTest {
	
	private LeiloesPage paginaDeLeiloes;
	private CadastroLeilaoPage paginaDeCadastro;
	
	
	@BeforeEach
	public void beforeEach() {
		LoginPage paginadeLogin = new LoginPage();
		paginadeLogin.preencheFormularioDeLogin("fulano", "pass");
		this.paginaDeLeiloes = paginadeLogin.efetuaLogin();
		this.paginaDeCadastro = paginaDeLeiloes.carregarFormulario();
	}
	
	@AfterEach
	public void afterEach() {
		//Abrir o navegador, passar a URL e fechar o navegador
		this.paginaDeLeiloes.fechar();
	}
	
	@Test
	public void deveriaCadastrarLeilao() {		
		
		String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String nome = "Leilao do dia " + hoje;
		String valor = "500.00";		
		
		System.out.println(hoje);
		System.out.println(nome);
		System.out.println(valor);
		
 		this.paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao(nome, valor, hoje);
		//Verificar se foram preenchidas as linhas do formulário
		Assert.assertTrue(paginaDeLeiloes.isLeilaoCadastrado(nome, valor, hoje));
		
		
	}
	
	@Test
	public void deveriaValidarCadastroDeLeilao() {
		this.paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao("", "", "");
		
		Assert.assertFalse(this.paginaDeCadastro.isPaginaAtual());
		Assert.assertTrue(this.paginaDeLeiloes.isPaginaAtual());
		Assert.assertTrue(this.paginaDeCadastro.isMensagensDeValidacaoVisiceis());

	}
	
	

	
}
