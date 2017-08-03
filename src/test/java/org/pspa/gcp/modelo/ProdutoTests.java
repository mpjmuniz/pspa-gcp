package org.pspa.gcp.modelo;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProdutoTests {

	Produto p;
	
	@Before
	public void setUp() throws Exception {
		p = new Produto();
	}

	@Test
	public void testNecessarioBasico() {
		p.setNecessario(5);
		
		Assert.assertThat(p.getNecessario(), is(5));
	}

	@Test
	public void testNecessarioComDias() {
		p.setNecessario(5);
		p.setSegunda(1);
		p.setTerca(1);
		p.setQuarta(1);
		p.setQuinta(1);
		p.setSexta(1);
		
		Assert.assertThat(p.getNecessario(), is(10));
	}
	
	@Test
	public void testComprarNulo() {
		p.setNecessario(5);
		p.setSegunda(1);
		p.setTerca(1);
		p.setQuarta(1);
		p.setQuinta(1);
		p.setSexta(1);
		p.setEstoque(10);
		
		Assert.assertThat(p.getComprar(), is(0));
	}
	
	@Test
	public void testComprarNegativo() {
		p.setNecessario(1);
		p.setSegunda(1);
		p.setTerca(1);
		p.setQuarta(1);
		p.setQuinta(1);
		p.setSexta(1);
		p.setEstoque(10);
		
		Assert.assertThat(p.getComprar(), is(0));
	}
	
	@Test
	public void testComprarPositivo() {
		p.setNecessario(10);
		p.setSegunda(1);
		p.setTerca(1);
		p.setQuarta(1);
		p.setQuinta(1);
		p.setSexta(1);
		p.setEstoque(10);
		
		Assert.assertThat(p.getComprar(), is(5));
	}
}
