package org.pspa.gcp.modelo;

import static org.hamcrest.CoreMatchers.is;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

public class AtividadeMensalTests {

	AtividadeMensal am;
	
	@Test
	public void testPrimeiroDiaCorreto() {
		LocalDate ld = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), 1);
		
		am = new AtividadeMensal(ld, "");
		
		Assert.assertThat(am.getPrimeiroDia().getDayOfMonth(), is(1));
	}

	@Test
	public void testPrimeiroDiaErrado() {
		LocalDate ld = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), 3);
		
		am = new AtividadeMensal(ld, "");
		
		Assert.assertThat(am.getPrimeiroDia().getDayOfMonth(), is(1));
	}
	
	@Test
	public void testPrimeiroDiaCorretoPorData() {
		LocalDate ld = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), 31);
		
		am = new AtividadeMensal(ld, "");
		
		Assert.assertThat(am.getPrimeiroDia(), is(LocalDate.of(2017, 8, 1)));
	}
	
	@Test
	public void testSegundaErradaPorData() {
		LocalDate ld = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), 2);
		
		am = new AtividadeMensal(ld, "");
		
		Assert.assertThat(am.getPrimeiroDia(), is(LocalDate.of(2017, 8, 1)));
	}

}
