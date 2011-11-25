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
	 * M�todo que asigna un valor a idparcela, siempre y cuando �ste este entre 1 y 8 d�gitos.
	 * @param idparcela : identificador �nico de la parcela
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
	 * M�todo para asignar el alias a la parcela, �sta deber� estar comprandida entre 1 y 20 car�cteres.
	 * @param alias : nombre representativo para que el usuario identifique c�modamente la parcela.
	 */
	public void setAlias(String alias){
		if((alias.length()>0)&&(alias.length()<21)){ //comprobamos que el alias este entre 1 y 20
			this.alias = alias;
		}else{
			JOptionPane.showMessageDialog(null, "EL ALIAS DEBE ESTAR COMPRENDIDO ENTRE 1 y 20 CAR�CTERES");
		}
	}
	public String getProvincia() {
		return provincia;
	}
	/**
	 * M�todo para asignar la provincia a la parcela, �sta deber� estar comprandida entre 1 y 20 car�cteres.
	 * @param provincia : Provincia de la parcela.
	 */
	public void setProvincia(String provincia) {
		if((provincia.length()>0)&&(provincia.length()<21)&&(esNumerico(provincia))){ //comprobamos que el alias este entre 1 y 20
			this.provincia = provincia;
		}else{
			JOptionPane.showMessageDialog(null,"LA PROVINCIA DEBE ESTAR COMPRENDIDA ENTRE 1 y 20 CAR�CTERES");
		}
	}
	public String getPoblacion() {
		return poblacion;
	}
	/**
	 * M�todo para asignar la poblaci�n a la parcela, �sta deber� estar comprandida entre 1 y 30 car�cteres.
	 * @param poblacion : Poblaci�n de la parcela.
	 */
	public void setPoblacion(String poblacion) {
		if((poblacion.length()>0)&&(poblacion.length()<31)&&(esNumerico(poblacion))){ //comprobamos que el alias este entre 1 y 30
			this.poblacion = poblacion;
		}else{
			JOptionPane.showMessageDialog(null,"LA POBLACION DEBE ESTAR COMPRENDIDA ENTRE 1 y 30 CAR�CTERES");
		}
	}
	public String getPoligono() {
		return poligono;
	}
	/**
	 * M�todo para asignar el pol�gono a la poblaci�n, �ste deber� estar comprandido entre 1 y 10 car�cteres.
	 * @param poligono : Pol�gono de la parcela.
	 */
	public void setPoligono(String poligono) {
		if((poligono.length()>0)&&(poligono.length()<11)){ //comprobamos que el poligono este entre 1 y 10
			this.poligono = poligono;
		}else{
			JOptionPane.showMessageDialog(null,"EL POLIGONO DEBE ESTAR COMPRENDIDO ENTRE 1 y 10 CAR�CTERES");
		}
	}
	public String getNumero() {
		return numero;
	}
	/**
	 * M�todo para asignar el numero a la parcela, �sta deber� estar comprendida entre 1 y 10 car�cteres y ademas ser numerico.
	 * @param numero : N�mero de la  de la parcela.
	 */
	public void setNumero(String numero) {
		if((numero.length()>0)&&(numero.length()<11)&&(esNumerico(numero))){ //comprobamos que sea numerico y entre 1 y 10
			this.numero = numero;
		}else{
			JOptionPane.showMessageDialog(null,"EL NUMERO DEBE ESTAR COMPRENDIDO ENTRE 1 y 10 CAR�CTERES");
		}
	}
	public boolean isActivo() {
		return activo;
	}
	/**
	 * M�todo para asignar el estado de la parcela.
	 * @param activo : Estado de la parcela en la base de datos (activo/inactivo).
	 */
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public String getPartida() {
		return partida;
	}
	/**
	 * M�todo encargado de asignar la partida a la parcela, �sta deber� estar comprendida entre 1 y 20.
	 * @param partida : Partida de la parcela.
	 */
	public void setPartida(String partida) {
		if((partida.length()>0)&&(partida.length()<21)){//comprobamos que la partida este entre 1 y 20
			this.partida = partida;
		}else{
			JOptionPane.showMessageDialog(null, "LA PARTIDA DEBE ESTAR COMPRENDIDA ENTRE 1 Y 20 CAR�CTERES");
		}
	}
	public String getDniPropietario() {
		return dniPropietario;
	}
	/**
	 * M�todo encargado de asignar el dnipropietario a la parcela, recibiremos un string de 8 car�cteres, y cargaremos uno de 9
	 * @param dnipropietario : DNI del propietario de la parcela.
	 */
	public void setDniPropietario(String dnipropietario) {
		if((dnipropietario.length()>8) && (dnipropietario.length()<11)){
			validarDni(dnipropietario);
		}
	}
	
	// M E T O D O S 
	/**
	 * M�todo encargado de decirnos si el string es num�rico o no.
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
	 * M�todo que calcula la letra del dni.
	 * @return el valor calculado de la letra
	 */
	public String calcularDNI(String dni){
		int pletra;
		String aux="";
		String[]arrayLetra = {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E","T"};
		for(int x=0;x<8;x++){//este for nos cojera los primeros car�cteres y los guardara en el string aux
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
	 * @return boolean que nos determinar� si el dni introducido es verdadero o falso.
	 */
	public boolean validarDni(String dni){
		int pletra;
		String aux="";
		String aux2="";
		String[]arrayLetra = {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E","T"};
		
		for(int x=0;x<8;x++){//este for nos cojera los 8 primeros car�cteres y los guardar� en el string aux
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
			dniPropietario= aux + arrayLetra[pletra];//lo colocamos en formato de 9 car�cteres
			return true;
		}else{
			JOptionPane.showMessageDialog(null, "EL NUMERO QUE HA INTRODUCIDO NO SE CORRESPONDE CON LA LETRA");
			return false;
		}
	}
}
