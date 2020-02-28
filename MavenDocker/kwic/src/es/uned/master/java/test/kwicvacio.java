package es.uned.master.java.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import es.uned.master.java.facade.fachada;

class kwicvacio {

	/*
	 * prueba unitaria para ver que ocurre si el contenido de las frases es null, al correr la prueba unitaria falla y no funciona
	 */
	@Test
	void test() {
		String noclaves="a ante cabe con el y y y la contra de por mi las las si segun sobre tras y con a ante con";
		ArrayList <String> frasesvacio = null;
		fachada f = new fachada(noclaves,frasesvacio);
		assertEquals(null, f.getK());
		
	}

}
