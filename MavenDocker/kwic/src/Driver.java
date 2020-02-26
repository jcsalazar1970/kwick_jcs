import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import es.uned.master.java.basico.*;
import es.uned.master.java.coleccion.*;

public class Driver {
	public static void main(String[] arg){
		Kwic kwic= new Kwic();
		String nombrefichero_noclave = "C:\\kwick\\fichero_cadenas_no_claves.txt";
		String nombrefichero_frases = "C:\\kwick\\ficherofrases.txt";
	//	String noclaves="a ante cabe con el y y y la contra de por mi las las si segun sobre tras y con a ante con";
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
		ArrayList <String> frases = new ArrayList<String>();
		
     // lectura de palabras no clase desde fichero
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
		
		// tratamiento para leer las frases desde fichero en vez desde Array
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
		
		kwic.computaNoClaves(noclaves);

		for (int i=0; i<frases.size();i++){
			kwic.computaIndice(frases.get(i));
			
		}

		System.out.println("Kwic:"+kwic.toString());
		
	}
}
