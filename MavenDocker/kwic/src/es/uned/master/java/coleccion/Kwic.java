package es.uned.master.java.coleccion;
import java.util.*;

import es.uned.master.java.basico.*;

public class Kwic {
	private Set<TituloKwic> noclaves; // conjunto de objetos TituloKwic donde almacenar las palabras no claves
	private Map<TituloKwic,Set<String>> kwic; // map para almacenar las palabras claves y las frases asociadas a dicha palabra clave

	//Inicializo la estructura
	public Kwic(){
		this.noclaves= new TreeSet(); // creamos un TreeSet de manera que el conjunto de palabras no claves esten ordenadas alfabeticamente
		this.kwic= new TreeMap(); // kwic es una estructura map ordenada alfabeticamente por ello la creamos como TreeMap
	}

/*
 * Esta parte s�lo trata las palabras no claves
 * parsea la cadena noclaves de manera que va cogiendo e introduciendo en noclaves aquellas palabras
 * que estan separadas por un espacio en blanco o un coma.
 */
	public void computaNoClaves(String noclaves){
		StringTokenizer strk= new StringTokenizer(noclaves," ,");

		while (strk.hasMoreTokens()){
			this.noclaves.add(new TituloKwic(strk.nextToken()));
		}
	}

/*
 * Esta parte se tratar�a de kwic usando las noclaves como consulta
 */
	// Tu solo vales para cuando una palabra SEA INDICE
	// Se utiliza con el compunta que recibe un String
	private void computaIndice(TituloKwic palabra, String frase){
		//Necesariamente se añade al map.
		// Si esta -> Solo añado la frase en el Set
		// Si no está -> Además de la frase el índice
		Set<String> frases= new TreeSet();
		if (this.kwic.containsKey(palabra)){
			// Que lo contiene
			 //Cariño dame ese valor de la palabra
			frases= this.kwic.get(palabra);
		}
		frases.add(palabra.reemplaza(frase));
		//frases.add(TituloKwic.reemplaza(palabra, frase));

		// Añadiro al map como nuevo y machaco el que había.
		this.kwic.put(palabra, frases);

	}

	// Este es yupi.
	//Mëtodod que recibe una frase y se computa al kwic
	// Con mucha gracia y dos pares

	public void computaIndice(String frase){
		//Creo un Tokenizer para poder extraer palaba a palabra
		StringTokenizer strk= new StringTokenizer(frase," ,");

		while (strk.hasMoreTokens()){
			// Primer paso para extraer la palabra de la frase
			TituloKwic palabra= new TituloKwic(strk.nextToken());

			// Segundo Detectar si esa palabra es indice o no ->
			// Si la palabra es NOCLAVE no computa nada ...no se hace me voy a la bartola ...que estoy agusto
			// Dame una cerveza
			if (!(this.noclaves.contains(palabra))){
				// Tercer paso -solamente para las indice- incluirlo en el kwic
				// Ole ya llegao
				this.computaIndice(palabra, frase);
			}
		}
	}
	
	/* 
	 * Procedimiento para escribir las palabras no claves, primero muestra la cadena "Palabras no claves"
	 * seguidamente crea un interador sobre las palabras no claves y va concatenando las palabra no claves
	 * devuelve las palabras no claves concatenadas
	 */
		private String escribeNoClaves(){
			String str="Palabras no claves: ";
			Iterator<TituloKwic> it = this.noclaves.iterator();
			while (it.hasNext()){
				str+= it.next().toString()+", ";
			}
			return str;
		}
			private String escribeKwic(Set<String> s){
				String str="";
				Iterator<String> it= s.iterator();
				while (it.hasNext()){
					str+= "\t"+it.next()+"\n";
				}
				return str;
			}
			
	 /*
	  * Procedimiento para escribir el indice kwick
	  * primeramente prepara un cadena con el valor "--INDICE--" salto de l�nea
	  * Crea un interador sobre el map del kwick y va concatenando las claves del map y
	  * haciendo una llamada al procedimiento anterior escribekwick(Set<string>) 
	  * para concatenar los valores de la claves , es decir las frases asociadas a la palabra clave
	  */
		private String escribeKwic(){
			String str="--INDICE--\n";
			Iterator<Map.Entry<TituloKwic,Set<String>>> it= this.kwic.entrySet().iterator();

			while (it.hasNext()){
				Map.Entry<TituloKwic,Set<String>> mp = it.next();
				str+= mp.getKey()+"\n";
				str+= escribeKwic(mp.getValue());
			}
			return str;
		}
 // M�todo para imprimir que llama a los metodos anteriores escribeNoClaves() que pone por pantalla las palabras no claves
 // concatena seguidamente el �ndice de palabras claves con sus frases asociadadas donde aparece la palabra clave
 public String toString(){
	 String str="";
	 str+= this.escribeNoClaves();
	 str+= this.escribeKwic();
	 return str;
 }
}














