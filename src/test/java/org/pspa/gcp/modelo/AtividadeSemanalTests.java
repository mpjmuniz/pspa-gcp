package org.pspa.gcp.modelo;

import static org.hamcrest.CoreMatchers.is;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

public class AtividadeSemanalTests {

	AtividadeSemanal as;

	@Test
	public void testSegundaCorreta() {
		LocalDate ld = LocalDate.of(2017, 07, 31);
		
		as = new AtividadeSemanal(ld);
		
		Assert.assertThat(as.getSegunda().getDayOfWeek(), is(DayOfWeek.MONDAY));
	}

	@Test
	public void testSegundaErrada() {
		LocalDate ld = LocalDate.of(2017, 07, 29);
		
		as = new AtividadeSemanal(ld);
		
		Assert.assertThat(as.getSegunda().getDayOfWeek(), is(DayOfWeek.MONDAY));
	}
	
	@Test
	public void testSegundaCorretaPorData() {
		LocalDate ld = LocalDate.of(2017, 07, 31);
		
		as = new AtividadeSemanal(ld);
		
		Assert.assertThat(as.getSegunda(), is(LocalDate.of(2017, 07, 31)));
	}
	
	@Test
	public void testSegundaErradaPorData() {
		LocalDate ld = LocalDate.of(2017, 07, 29);
		
		as = new AtividadeSemanal(ld);
		
		Assert.assertThat(as.getSegunda(), is(LocalDate.of(2017, 07, 24)));
	}
}
