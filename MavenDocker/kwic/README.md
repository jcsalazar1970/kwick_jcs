# KWIC

## Enunciado Kwic

El objetivo de esta práctica es realizar un glosario de palabras atendiendo a su aparición en un conjunto de frases (KeyWord? In Context, KWIC), desechando aquéllas que no se consideren significativas.

Para ello, contaremos con:
  *  Un array de String --> donde tendremos una relación de frases, a partir de las cuales, deberemos obtener el correspondiente índice.
  * Un String --> representa las palabras no significativas(y que, por lo tanto, no aparecerán en el listado KWIC)

Un ejemplo de String de palabras no significativas podría contener la siguiente línea:

```
el la los las un una unos unas y o a ante bajo cabe con contra de desde en entre hacia hasta para por según sin sobre tras si no
```

El siguiente listado de títulos de películas podría servir como ejemplo de contenido de un array de String con las frases (frase por línea) a partir de las cuales construir un índice KWIC:

```
El color del dinero
Color púrpura
Misión: imposible
La misión
La rosa púrpura del Cairo
El dinero llama al dinero
La rosa del azafrán
El nombre de la rosa
Toma el dinero y corre
```

El índice que se desea generar debe tener el siguiente aspecto:

**AZAFRÁN**

  La rosa del ...

**CAIRO**

  La rosa púrpura del ...

**COLOR**

  ... púrpura

  El ... del dinero

**DINERO**

  El color del ...

  El ... llama al ...

  Toma el ... y corre

**IMPOSIBLE**

  Misión: ...

**MISIÓN**

  La ...

  ...: imposible

**NOMBRE**

  El ... de la rosa

**PÚRPURA**

  Color ...

  La rosa ... del Cairo

**ROSA**

  El nombre de la ...

  La ... del azafrán

  La ... púrpura del Cairo

Como vemos, el índice está ordenado por palabras significativas y, por cada una de ellas, aparecen todas las frases que la contienen (ordenadas alfabéticamente), con las apariciones de la palabra sustituidas por "...".

Visto lo anterior, se pide:

  * a) Definir una clase TituloKWIC que dé envoltura a una frase o título (de tipo String), y que permita ordenar y comparar frases independientemente de si éstas contienen caracteres en minúsculas o mayúsculas, así como un método para producir una cadena, a partir del título, con las apariciones de una palabra sustituidas por “...”.
  *  Definir una clase KWIC que incluya los métodos necesarios para:
     * Leer (y almacenar) la información de las palabras no significativas,
     * Generar la estructura del índice a partir de un array de String (p.e. títulos de películas) teniendo en cuenta las palabras no significativas leídas previamente.

Para resolver esta práctica vamos a realizar una primera división del problema:
Tendremos las siguientes clases:

  * TituloKWIC, la cual me permitirá cadena de caracteres sin distinguir entre mayúsculas y minúsculas.
  * KWIC, va a ser :
    * Un Map<TituloKWIC,Set<String> glosario
    * Set<TituloKWIC> noClaves
    es una estructura para guardar las no claves.

![Representación Kwic](https://raw.githubusercontent.com/redeskako/EjemplosJ2SE/master/MavenDocker/kwic/kwic.PNG)

El `map, será una clase `TreeMap`, y un `Set` será una clase `TreeSet`.
Como vamos a implementar clases de `TreeMap` y `TreeSet`, necesito redefinir:

  * `compareTo`
  * `equals`
  * `hasCode`
  * `toString`

## Comentarios del código fuente

#### contructor Kwic()

Método constructor, se inicializa la estructura

```
public KWIC(){
         noClaves = new TreeSet();
         glosario = new TreeMap();
    }
```
#### entrada palabras no clave desde fichero

Esta parte del código representa como se obtienen la entrada de palabras no claves desde fichero:
```
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
		
````		

### Entrada de frases desde fichero

```
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
```
#### palabrasNoSignificativas(String)

Esta parte del codigo tratará exclusivamente las **noClaves**. Que metan una estructura de String y yo computarla, recibiendo el `String` noclaves.
  * Uso `StringTokenizer`para coger cada palabra no significativa.
  * Tomaré un token hasta que encuentre una `,` `:` `;`
  * Mientras halla elementos,lo introduzco en mi conjunto.

```
public void computaNoClaves(String noclaves){
		StringTokenizer strk= new StringTokenizer(noclaves," ,");

		while (strk.hasMoreTokens()){
			this.noclaves.add(new TituloKwic(strk.nextToken()));
		}
	}
```

#### generarFrases(String)

  * Aquí trataremos la parte de las palabras significativas. Ej: Si olvido de todas las frases, solo cojo una:
    `String str = "La silla está rota en el balcón"`
  * Tengo que coger una palabra significativa: `esta`  --> entra como clave, palabra del índice.Tengo que integrarla en el indice de este mapa, puede ser que exista ese indice o no exista. A la clave Hay que ponerle un envoltorio de `TituloKWIC` , lo que hará es que: `esta --> ESTA` 

  * Método que recibe una frase y se computa al kwic:

     1. primer paso --> para extraer la palabra de la frase.
     2. segundo paso: Detectar si esa palabra es indice o no.
     3. tercer paso: Sólamente para los indice: incluirlo en el kwic.

Utilizo el `StringTokenizer` para coger las palabras significativas que me valdrán como índice para mi estructura `map`

  * Mientras halla elementos, cojo una palabra y la envuelvo en `TituloKWIC`.
  * Si no es una palabra no significativa, significa que voy bien, lo que tengo es una palabra válida en este caso. En este caso, me voy a un método privado que ahora comentaremos.

```
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
```

Aqui tengo mi método private, que se va a llamar frases, lo único que ahora se recibe un `TituloKWIC` y un `String` que será la frase a introducir.
   1. Creo un conjunto para el caso que ese indice no esté en mi estructura.
   2. Si mi estructura Map contiene ese indice, devuelveme el conjunto de frases de ese índice.
   3. Tanto si está el índice en la estructura como si no, tengo que añadir la frase al conjunto y luego relacionar ese índice con mi conjunto actualizado.
   4. Lo saco del `if`, y no me hace falta poner un `else`.
   5. Añado `replace` para que me sustituya en la frase el indice por `...`. Este método está en `codigoTituloKWIC`.

```
private void computaIndice(TituloKwic palabra, String frase){
		//Necesariamente se aÃ±ade al map.
		// Si esta -> Solo aÃ±ado la frase en el Set
		// Si no estÃ¡ -> AdemÃ¡s de la frase el Ã­ndice
		Set<String> frases= new TreeSet();
		if (this.kwic.containsKey(palabra)){
			// Que lo contiene
			 //CariÃ±o dame ese valor de la palabra
			frases= this.kwic.get(palabra);
		}
		frases.add(palabra.reemplaza(frase));
		//frases.add(TituloKwic.reemplaza(palabra, frase));

		// AÃ±adiro al map como nuevo y machaco el que habÃ­a.
		this.kwic.put(palabra, frases);

	}

```

#### Método toString():String

Utilizaremos dos métodos privados:i
  *`imprimirNoClaves()` --> para imprimir por pantalla el conjunto de claves.
  *`imprimirGlosario()` --> para imprimir por pantalla la estructura `map`.
    * `clave (índice)` y valor (conjunto de frases relacionadas y con el índice sustituido por `...`)


public String toString(){
	 String str="";
	 str+= this.escribeNoClaves();
	 str+= this.escribeKwic();
	 return str;
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
	  * primeramente prepara un cadena con el valor "--INDICE--" salto de línea
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
  
```

#### KwicException
  * Clase de contorl de Errores personalizada

#### TituloKwic
  * Código envoltorio de `String`
  
#### driverKwic
   * Código del `main(String [])`.
 
