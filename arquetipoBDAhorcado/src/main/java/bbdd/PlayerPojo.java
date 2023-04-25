package bbdd;

import java.util.Iterator;

import arquetipoBDAhorcado.*;

public class PlayerPojo implements Comparable<PlayerPojo> {
	
	private String nombre;
	private int estado;
	private int intentos;
	private String palabraJuego;
	private String letrasUtilizadas;
	
	public PlayerPojo( ){
		this.nombre = "";
		this.estado = 0;
		this.intentos =0;
		this.palabraJuego ="";
		this.letrasUtilizadas="";
	}
	
	/**
	 * @param nombre
	 * @param estado
	 * @param intentos
	 * @param palabraJuego
	 * @param letrasUtilizadas
	 */
	public PlayerPojo(Player player ){
		String letras="";
	
		this.nombre = player.getNombre();
		this.estado = player.getEstado().getEstado();
		this.intentos =player.getIntentos();
		this.palabraJuego =player.getPalabraJuego();
		
		
		//obtenemos un string con las letras utilizadas
		Iterator<Character> ItLetras = player.getLetraUtilizadas().iterator();
	    while (ItLetras.hasNext()){//mientras halla elementos en el conjuntoL
	      	letras += ItLetras.next().toString() + ",";//imprime elementos
	     }
	        
	     if (!letras.isEmpty()){ //eliminamos la última coma
	       	letras=letras.substring(0, letras.length()-1);
	     }
		
		
		this.letrasUtilizadas =letras;
	}



	
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the estado
	 */
	public int getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}
	/**
	 * @return the intentos
	 */
	public Integer getIntentos() {
		return intentos;
	}
	/**
	 * @param intentos the intentos to set
	 */
	public void setIntentos(Integer intentos) {
		this.intentos = intentos;
	}
	/**
	 * @return the palabraJuego
	 */
	public String getPalabraJuego() {
		return palabraJuego;
	}
	/**
	 * @param palabraJuego the palabraJuego to set
	 */
	public void setPalabraJuego(String palabraJuego) {
		this.palabraJuego = palabraJuego;
	}
	/**
	 * @return the letrasUtilizadas
	 */
	public String getLetrasUtilizadas() {
		return letrasUtilizadas;
	}
	/**
	 * @param letrasUtilizadas the letrasUtilizadas to set
	 */
	public void setLetrasUtilizadas(String letrasUtilizadas) {
		this.letrasUtilizadas = letrasUtilizadas;
	}

	   @Override
public String toString(){	
	String linea="";	
	
	if (!this.getNombre().isBlank()) { //Si no tenemos informado el nombre devolvemos el string vacio
		linea=this.getNombre()+ Player.getToken()+
		this.getEstado()+Player.getToken()+
		this.getIntentos()+Player.getToken()+
		this.getPalabraJuego()+Player.getToken()+
		this.getLetrasUtilizadas();     
	}
        
   return linea;
}	
	
	   @Override
public boolean equals(Object o){
    if (o instanceof PlayerPojo){
           return (this.nombre == (((PlayerPojo) o).getNombre()));
      }else{
            throw new ClassCastException("NO es un PlayerPojo");
      }
}
	
	   @Override
	    public int hashCode(){
	        return (new String(this.nombre)).hashCode();
	    }	
	
	   
public int compareTo(PlayerPojo player){	
    return this.nombre.compareToIgnoreCase(player.getNombre());
	
}
   
}
