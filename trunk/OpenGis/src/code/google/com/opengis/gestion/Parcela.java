/*****************************************************************************
* Copyright (c) 2011 [OpenGisTeam]                                           *
******************************************************************************/
package code.google.com.opengis.gestion;
import javax.swing.JOptionPane;


/**
 * @author knals
 *Clase que nos permite validar los datos de Parcela.
 *
 */
public class Parcela {
	private int idparcela; //identificador de la parcela
	private String alias; //nombre que el usuario asignara a la parcela para relacionarlo
	private String provincia;  //provincia de la parcela
	private String poblacion; //poblacion de la parcela
	private String poligono; //poligono de la parcela
	private String numero; //numero de la parcela
	private boolean activo; //nos avisara si la parcela esta activa o no, a los ojos del usuario
	private String partida; //identificador de parcela a nivel localidad
	private String dniPropietario; //dni del propietario de la parcela
	
	// C O N S T R U C T O R
	public Parcela(int idparcela, String alias, String provincia, String poblacion, String poligono, String numero,
					boolean activo, String partida, String dnipropietario){
		this.setIdParcela(idparcela);
		this.setAlias(alias);
		this.setProvincia(provincia);
		this.setPoblacion(poblacion);
		this.setPoligono(poligono);
		this.setNumero(numero);
		this.setActivo(activo);//opcion de crear el objeto como inactivo
		this.setPartida(partida);
		this.setDniPropietario(dnipropietario);
	}
	
	//   M E T O D O S    G E T T E R    &    S E T T E R
	public int getIdParcela() {
		return idparcela;
	}
	/**
	 * Método que asigna un valor a idparcela, siempre y cuando éste este entre 1 y 8 dígitos.
	 * @param idparcela : identificador único de la parcela
	 */
	public void setIdParcela(int idparcela) {
		String str= idparcela+"";
		if((str.length()>0)&&(str.length()<9)){ //comprobamos que idparcela este entre 1 y 8
			this.idparcela = idparcela;
		}else{
			JOptionPane.showMessageDialog(null, "EL NUMERO ID DEBE ESTAR COMPRENDIDO ENTRE 1 y 8");
		}
	}
	public String getAlias(){
		return alias;
	}
	/**
	 * Método para asignar el alias a la parcela, ésta deberá estar comprandida entre 1 y 20 carácteres.
	 * @param alias : nombre representativo para que el usuario identifique cómodamente la parcela.
	 */
	public void setAlias(String alias){
		if((alias.length()>0)&&(alias.length()<21)){ //comprobamos que el alias este entre 1 y 20
			this.alias = alias;
		}else{
			JOptionPane.showMessageDialog(null, "EL ALIAS DEBE ESTAR COMPRENDIDO ENTRE 1 y 20 CARÁCTERES");
		}
	}
	public String getProvincia() {
		return provincia;
	}
	/**
	 * Método para asignar la provincia a la parcela, ésta deberá estar comprandida entre 1 y 20 carácteres.
	 * @param provincia : Provincia de la parcela.
	 */
	public void setProvincia(String provincia) {
		if((provincia.length()>0)&&(provincia.length()<21)&&(esNumerico(provincia))){ //comprobamos que el alias este entre 1 y 20
			this.provincia = provincia;
		}else{
			JOptionPane.showMessageDialog(null,"LA PROVINCIA DEBE ESTAR COMPRENDIDA ENTRE 1 y 20 CARÁCTERES");
		}
	}
	public String getPoblacion() {
		return poblacion;
	}
	/**
	 * Método para asignar la población a la parcela, ésta deberá estar comprandida entre 1 y 30 carácteres.
	 * @param poblacion : Población de la parcela.
	 */
	public void setPoblacion(String poblacion) {
		if((poblacion.length()>0)&&(poblacion.length()<31)&&(esNumerico(poblacion))){ //comprobamos que el alias este entre 1 y 30
			this.poblacion = poblacion;
		}else{
			JOptionPane.showMessageDialog(null,"LA POBLACION DEBE ESTAR COMPRENDIDA ENTRE 1 y 30 CARÁCTERES");
		}
	}
	public String getPoligono() {
		return poligono;
	}
	/**
	 * Método para asignar el polígono a la población, éste deberá estar comprandido entre 1 y 10 carácteres.
	 * @param poligono : Polígono de la parcela.
	 */
	public void setPoligono(String poligono) {
		if((poligono.length()>0)&&(poligono.length()<11)){ //comprobamos que el poligono este entre 1 y 10
			this.poligono = poligono;
		}else{
			JOptionPane.showMessageDialog(null,"EL POLIGONO DEBE ESTAR COMPRENDIDO ENTRE 1 y 10 CARÁCTERES");
		}
	}
	public String getNumero() {
		return numero;
	}
	/**
	 * Método para asignar el numero a la parcela, ésta deberá estar comprendida entre 1 y 10 carácteres y ademas ser numerico.
	 * @param numero : Número de la  de la parcela.
	 */
	public void setNumero(String numero) {
		if((numero.length()>0)&&(numero.length()<11)&&(esNumerico(numero))){ //comprobamos que sea numerico y entre 1 y 10
			this.numero = numero;
		}else{
			JOptionPane.showMessageDialog(null,"EL NUMERO DEBE ESTAR COMPRENDIDO ENTRE 1 y 10 CARÁCTERES");
		}
	}
	public boolean isActivo() {
		return activo;
	}
	/**
	 * Método para asignar el estado de la parcela.
	 * @param activo : Estado de la parcela en la base de datos (activo/inactivo).
	 */
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public String getPartida() {
		return partida;
	}
	/**
	 * Método encargado de asignar la partida a la parcela, ésta deberá estar comprendida entre 1 y 20.
	 * @param partida : Partida de la parcela.
	 */
	public void setPartida(String partida) {
		if((partida.length()>0)&&(partida.length()<21)){//comprobamos que la partida este entre 1 y 20
			this.partida = partida;
		}else{
			JOptionPane.showMessageDialog(null, "LA PARTIDA DEBE ESTAR COMPRENDIDA ENTRE 1 Y 20 CARÁCTERES");
		}
	}
	public String getDniPropietario() {
		return dniPropietario;
	}
	/**
	 * Método encargado de asignar el dnipropietario a la parcela, recibiremos un string de 8 carácteres, y cargaremos uno de 9
	 * @param dnipropietario : DNI del propietario de la parcela.
	 */
	public void setDniPropietario(String dnipropietario) {
		if((dnipropietario.length()>8) && (dnipropietario.length()<11)){
			validarDni(dnipropietario);
		}
	}
	
	// M E T O D O S 
	/**
	 * Método encargado de decirnos si el string es numérico o no.
	 * @param str: Es el parametro que facilitaremos para comprobar si es numerico o no.
	 * @return
	 */
	public static boolean esNumerico(String str){
		int i =0;
		for(i=0;i<str.length();i++){
			if(Character.isDigit(str.charAt(i))==false){
				return  false;
			}
		}
		return true;
	}	
	/**
	 * Método que calcula la letra del dni.
	 * @return el valor calculado de la letra
	 */
	public String calcularDNI(String dni){
		int pletra;
		String aux="";
		String[]arrayLetra = {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E","T"};
		for(int x=0;x<8;x++){//este for nos cojera los primeros carácteres y los guardara en el string aux
			aux=aux + dni.charAt(x);
		}	
		pletra= Integer.parseInt(aux);
		pletra=pletra%23;			
		aux= aux + arrayLetra[pletra];
		
		return dni;
	}
	/**
	 * Clase que valida el DNI, y lo coloca en formato 00000000L
	 * @param dni
	 * @return boolean que nos determinará si el dni introducido es verdadero o falso.
	 */
	public boolean validarDni(String dni){
		int pletra;
		String aux="";
		String aux2="";
		String[]arrayLetra = {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E","T"};
		
		for(int x=0;x<8;x++){//este for nos cojera los 8 primeros carácteres y los guardará en el string aux
			aux=aux + dni.charAt(x);
		}
		try {  
			 pletra= Integer.parseInt(aux); //si no fueran enteros saldriamos del metodo con un false
		}  
	    catch(NumberFormatException ex ) {
			JOptionPane.showMessageDialog(null, "LOS 8 PRIMEROS DIGITOS HAN DE SER ENTEROS");
	        return false;  
	    }  
		pletra=pletra%23;
		aux2=dni.charAt(dni.length()-1)+"";
		
		if(arrayLetra[pletra].equalsIgnoreCase(aux2)){
			dniPropietario= aux + arrayLetra[pletra];//lo colocamos en formato de 9 carácteres
			return true;
		}else{
			JOptionPane.showMessageDialog(null, "EL NUMERO QUE HA INTRODUCIDO NO SE CORRESPONDE CON LA LETRA");
			return false;
		}
	}
}
