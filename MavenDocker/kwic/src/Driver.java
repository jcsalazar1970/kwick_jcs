import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Set;

import es.uned.master.java.basico.*;
import es.uned.master.java.coleccion.*;

/*programa kwick modificado introduciendo la entrada de palabras no claves y frases desde fichero para hacerlo más dinámico
 * programador : juan carlos salazar caballero
 * fecha ultima modificación: 27022020
 * el programa tiene incluido la dependencia en el fichero pom de junit para poder llevar pruebas unitarias
 * el código está documentado de manera que se entienda que queda claro en la práctica que se entiende el código por
 * parte del programador.
 */

public class Driver {
	public static void main(String[] arg){
		Kwic kwic= new Kwic();
	// paths desde donde coger las palabras no claves y frases desde fichero
	// comentamos la entradas de noclaves y frases[] ya que la entrada la realizamos desde fichero
		String nombrefichero_noclave = "C:\\kwick\\fichero_cadenas_no_claves.txt";
		String nombrefichero_frases = "C:\\kwick\\ficherofrases.txt";
	//	String noclaves="a ante cabe con el y y y la contra de por mi las las si segun sobre tras y con a ante con";
		
		// String noclaves es la cadena que parsearemos con la clase tokenizer para procesar las palabras no claves 
		String noclaves="";
	/*	String [] frases={
		                  "El caballero rojo",
		                  "El reloj de la abadia",
		                  "Dabale arroz a la zorra el abad",
		                  "Las aguas contaminadas y claras de Jaen mi tierra",
		                  "El abad esta ciego",
		                  "El AbAd estA CiegO"
		};
		*/
		
		// ArrayList de cadenas para introducir desde fichero las frases
		ArrayList <String> frases = new ArrayList<String>();
		
     // lectura de palabras no clase desde fichero de palabras no clave,
	 // va leyendo lineas de fichero y concatenando las líneas a la cadena que contendrá todas las palabras no claves
		try {
			FileReader fr = new FileReader(nombrefichero_noclave);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			while((linea = br.readLine()) != null) {
			//	System.out.println(linea);
				noclaves += linea ;
				
			}
				
			fr.close();
			
		}catch(Exception e) {
			System.out.println("Excepcion leyendo fichero" + nombrefichero_noclave + ": " + e);
		}
		
		// tratamiento para leer las frases desde fichero en vez desde ArrayList
		// el codigo irá leyendo linea a linea el fichero de manera que va introduciendo cada linea
		// 
		try {
			FileReader frf = new FileReader(nombrefichero_frases);
			BufferedReader brf = new BufferedReader(frf);
			String linea;
			int i=0;
			while((linea = brf.readLine()) != null) {
			
				frases.add(linea);
			
				i++;
				
			}
				
			frf.close();
			
		}catch(Exception e) {
			System.out.println("Excepcion leyendo fichero" + nombrefichero_frases + ": " + e);
		}
		
		/*
		 * procedimiento para introducir en private Set<TituloKwic> noclaves;
		 * las palabras no claves, mediante un parseo con la clase tokenizer de palabras
		 * separada por espacios o comas, esto introduce en un conjunto de objetos TituloKwi las
		 * palabras no claves
		 */
		kwic.computaNoClaves(noclaves);

		/*
		 * bucle sobre el arraylist frases que contiene todas las frases a procesar, dichas se han obtenido previamente
		 * desde fichero, codigo anterior
		 * el bucle va recorriendo el arraylist frases, según su tamaño, de ahí 'i<frases.size()'
		 * como condición de recorrido
		 * así llama al metodo 'kwick.computaIndice(con la frase a procesar)'
		 * 
		 */
		for (int i=0; i<frases.size();i++){
			kwic.computaIndice(frases.get(i));
			
		}

		System.out.println("Kwic:"+kwic.toString());
		
	}
}
