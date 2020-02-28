package es.uned.master.java.facade;
import java.util.ArrayList;

import es.uned.master.java.coleccion.Kwic;

public class fachada {

	Kwic k;
	
	public fachada(String noclaves, ArrayList<String> frases) {
	 	
		this.k = new Kwic();
		
		/*
		 * procedimiento para introducir en private Set<TituloKwic> noclaves;
		 * las palabras no claves, mediante un parseo con la clase tokenizer de palabras
		 * separada por espacios o comas, esto introduce en un conjunto de objetos TituloKwi las
		 * palabras no claves
		 */
		this.k.computaNoClaves(noclaves);
		
		
		/*
		 * bucle sobre el arraylist frases que contiene todas las frases a procesar, dichas se han obtenido previamente
		 * desde fichero, codigo anterior
		 * el bucle va recorriendo el arraylist frases, según su tamaño, de ahí 'i<frases.size()'
		 * como condición de recorrido
		 * así llama al metodo 'kwick.computaIndice(con la frase a procesar)'
		 */
		for (int i=0; i<frases.size();i++){
			
			this.k.computaIndice(frases.get(i));
			
		}
		
	}
	
	public void imprimir() {
		
		System.out.println("Kwic:"+this.k.toString());
	}
	
	public String imprimirstring() {
		
		return this.k.toString();
	}

	public Kwic getK() {
		return k;
	}

	public void setK(Kwic k) {
		this.k = k;
	}
	
	
}
