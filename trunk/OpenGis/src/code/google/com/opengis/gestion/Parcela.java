/*****************************************************************************
* Copyright (c) 2011 [OpenGisTeam]                                           *
******************************************************************************/
package code.google.com.opengis.gestion;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import code.google.com.opengis.gestionDAO.ConectarDBA;
import code.google.com.opengis.gestionDAO.Idioma;


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
	private  String numero; //numero de la parcela
	private int activo; //nos avisara si la parcela esta activa o no, a los ojos del usuario
	private String partida; //identificador de parcela a nivel localidad
	private String dniPropietario; //dni del propietario de la parcela
	private String sentencia; //String en el cual almacenaremos las consultas.
	
	
	private static String resultado[];
	private static boolean existe;
	private static boolean valido=true;
	
	// C O N S T R U C T O R
	public Parcela(int idparcela,String alias, String provincia, String poblacion, String poligono, String numero,
			int activo, String partida, String dnipropietario){
		valido=true;
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
	
	private void setIdParcela(int idparcela2) {
		this.idparcela= idparcela2;	
	}
	public int getIdParcela() {
		//idparcela=(Integer) null;
		return idparcela;
	}
	/**
	 * Método que asigna un valor a idparcela, siempre y cuando éste este entre 1 y 8 dígitos.
	 * @param idparcela : identificador único de la parcela
	 */
	//public void setIdParcela(int idparcela) {
		//String str= idparcela+"";
		//if((str.length()>0)&&(str.length()<9)){ //comprobamos que idparcela este entre 1 y 8
			//this.idparcela = idparcela;
		//}else{
			//JOptionPane.showMessageDialog(null, "EL NUMERO ID DEBE ESTAR COMPRENDIDO ENTRE 1 y 8");
			//valido=false;
		//}
	//}
	public String getAlias(){
		return alias;
	}
	/**
	 * Método para asignar el alias a la parcela, ésta deberá estar comprandida entre 1 y 20 carácteres.
	 * @param alias : nombre representativo para que el usuario identifique cómodamente la parcela.
	 */
	public void setAlias(String alias){
		
		if(ValidacionDatos.validarTextoEspecial(alias,Idioma.getString("etAlias"))==false){
			
			
			valido= false;
			
		}else{
			
		if (alias.length()>2){
			if (Character.isLetter(alias.charAt(0)) == true && alias.charAt(0) != (' ') && Character.isLetter(alias.charAt(1)) ==
				true && alias.charAt(1) != (' ')){

				if((alias.length()>0)&&(alias.length()<21)){ //comprobamos que el alias este entre 1 y 20
					this.alias = alias;
				}else{
					JOptionPane.showMessageDialog(null, Idioma.getString("msgAliasBetween1And20")); //$NON-NLS-1$
					valido=false;
				}
			}else{
			
				JOptionPane.showMessageDialog(null, Idioma.getString("msgAliasNotBlankSpace")); //$NON-NLS-1$
				valido=false;
			}
		}else{
			JOptionPane.showMessageDialog(null, Idioma.getString("msgAliasThreeChar")); //$NON-NLS-1$
			valido = false;
		}
		
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
			int prov;
		try {
			prov= Integer.parseInt(provincia);
			

			if((esNumerico(provincia)==true &&(prov>0)&&(prov<52))){ //comprobamos que el alias este entre 1 y 20
				this.provincia = provincia;
			}else{
				JOptionPane.showMessageDialog(null,Idioma.getString("msgProvNumWrong")); //$NON-NLS-1$
				valido=false;
			}
		} catch (Exception e2) {
				JOptionPane.showMessageDialog(null,Idioma.getString("msgProvNumNumeric")); //$NON-NLS-1$
				valido = false;
		}
	}
	public String getPoblacion() {
		return poblacion;
	}
	/**
	 * Método para asignar la población a la parcela, ésta deberá estar comprandida entre 1 y 30 carácteres.
	 * @param poblacion : Población de la parcela.
	 */
	public void setPoblacion(String poblacion) {//&&(esNumerico(poblacion)==false)
		int pobl;
		try {
			pobl= Integer.parseInt(poblacion);
		
			if((esNumerico(poblacion)==true &&(pobl>0)&&(pobl<8175))){ //comprobamos que el alias este entre 1 y 30
				this.poblacion = poblacion;
			}else{
				JOptionPane.showMessageDialog(null,Idioma.getString("msgCityNumWrong")); //$NON-NLS-1$
				valido=false;
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null,Idioma.getString("msgCityNumNumeric")); //$NON-NLS-1$
			valido = false;
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
		int pol;
		try {
			pol= Integer.parseInt(poligono);

		
		if((esNumerico(poligono)&&(pol>0)&&(pol<1000))){ //comprobamos que el poligono este entre 1 y 10
			this.poligono = poligono;
		}else{
			JOptionPane.showMessageDialog(null,Idioma.getString("msgAreaNumWrong")); //$NON-NLS-1$
			valido=false;
		}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null,Idioma.getString("msgAreaNumNumeric")); //$NON-NLS-1$
			valido = false;
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
		int num;
		try {
			num= Integer.parseInt(numero);
		if((esNumerico(numero)&&(num>0)&&(num<100000))){ //comprobamos que sea numerico y entre 1 y 10
			this.numero = numero;
		}else{
			JOptionPane.showMessageDialog(null,Idioma.getString("msgLotNumWrong")); //$NON-NLS-1$
			valido=false;
		}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null,Idioma.getString("msgLotNumNumeric")); //$NON-NLS-1$
			valido = false;
	}
	}
	public int isActivo() {
		return activo;
	}
	/**
	 * Método para asignar el estado de la parcela.
	 * @param activo : Estado de la parcela en la base de datos (activo/inactivo).
	 */
	public void setActivo(int activo) {
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
		if((partida.length()>-1)&&(partida.length()<21)){//comprobamos que la partida este entre 1 y 20
			this.partida = partida;
		}else{
			JOptionPane.showMessageDialog(null, Idioma.getString("msgEntryBetween0And20")); //$NON-NLS-1$
			valido=false;
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
		
		
		this.dniPropietario = dnipropietario;
		
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

	
	//PARCELADAO
	
	public void comprobarParcela(int s) throws SQLException{
		ConectarDBA.acceder();
		sentencia = "SELECT `alias`, `activo` FROM `parcela` WHERE `alias` LIKE '"+s+"'"; //$NON-NLS-1$ //$NON-NLS-2$
		ResultSet rs = ConectarDBA.consulta(sentencia);
		resultado = new String[2];
		activo = 0;
		while(rs.next()){
			System.out.println("Ejecuto el while"); //$NON-NLS-1$
				resultado[0] = rs.getString(1);
				System.out.println(resultado[0]);
				resultado[1] = rs.getString(2);
				System.out.println(resultado[1]);
		}
			System.out.println("Enviado: "+alias+" esperado: "+resultado[0].toString()); //$NON-NLS-1$ //$NON-NLS-2$
			if(resultado[0] == null){
				existe = false;
				System.out.println("El estado de existe es: "+existe); //$NON-NLS-1$
			}else if (resultado[0].equals(alias)){
				existe = true;
				if (resultado[1].equals("0")){ //$NON-NLS-1$
					activo = 1;	
				}
				System.out.println("El estado de activo es: "+activo); //$NON-NLS-1$
				System.out.println("El estado de existe es: "+existe); //$NON-NLS-1$
			}
			
		rs.close();
	}
	
/**
 * Método que realiza las busquedas de parcela 
 * @param criterio: criterio con el que buscaremos en todas las columnas de la base de datos.
 * @return ResultSet con las tuplas encontradas
 * @throws SQLException
 */
public static ResultSet buscar(String criterio) throws SQLException{
 	ResultSet rs = null;
 	ConectarDBA.acceder();
	/*String sentencia = "SELECT `idparcela`, `alias`, `provincia`, `poblacion`, `poligono`, `numero`, `partida`, `dni_propietario` FROM `parcela` WHERE (`idparcela` = '"+criterio+"' Or `alias` = '"
			+criterio+"' Or `provincia` = '"+criterio+"' Or  `poblacion` = '"+criterio+"' Or `poligono` = '"
			+criterio+"' Or  `numero` = '"+criterio+"' Or  `partida` = '"
			+criterio+"' Or  `dni_propietario`= '"+criterio+"' ) AND  `activo` <> '0'";
	*/
	String sentencia = "SELECT `idparcela`, `alias`, `provincia`, `poblacion`, `poligono`, `numero`, `partida`, `dni_propietario` FROM `parcela` WHERE (idparcela LIKE '%"+criterio+"%' Or alias LIKE '%" //$NON-NLS-1$ //$NON-NLS-2$
			+criterio+"%' Or provincia LIKE '%"+criterio+"%' Or  poblacion LIKE '%"+criterio+"%' Or poligono LIKE '%" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			+criterio+"%' Or  numero LIKE '%"+criterio+"%' Or  partida LIKE '%" //$NON-NLS-1$ //$NON-NLS-2$
			+criterio+"%' Or  dni_propietario LIKE '%"+criterio+"%' ) AND  activo <> '0'"; //$NON-NLS-1$ //$NON-NLS-2$
	
	//String sentencia = "SELECT `dni` , `nombre` , `apellidos` , `dirección` , `población` , `provincia` , `cp` , `teléfono` , `email` , `fecha_nacimiento` , `tipo` , `activo` FROM `usuario` WHERE dni LIKE '%"+criterio+"%' OR nombre LIKE '%"+criterio+"%' OR apellidos LIKE '%"+criterio+"%' OR dirección LIKE '%"+criterio+"%' OR población LIKE '%"+criterio+"%' OR provincia LIKE '%"+criterio+"%'";
	try{
		System.out.println("Ejecutada sentencia "+ sentencia); //$NON-NLS-1$
	rs = ConectarDBA.consulta(sentencia);
	}catch (SQLException e){
		System.out.println(e);
	}
	return rs;
}

	/**
	 * Método con el que realizaremos las Altas de parcela
	 * @throws SQLException
	 */
	public void altaParcela() throws SQLException{
		ConectarDBA.acceder();
		existe = false;

		String sentencia = "INSERT INTO `dai2opengis`.`parcela` (`idparcela`, `alias`, `provincia`, `poblacion`, `poligono`, `numero`, `activo`, `partida`, `dni_propietario`) VALUES (NULL,'" + this.alias  + "','" + this.provincia +"','" +this.poblacion +"','" + this.poligono +"','" + this.numero +"','"  + this.activo +"','"+ this.partida +"','"+ this.dniPropietario +"')"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$
		
		if (existe == true){ 	
			 JOptionPane.showMessageDialog(null,Idioma.getString("msgLotIDAlreadyExist")); //$NON-NLS-1$
		}else{
			ConectarDBA.modificar(sentencia);
			JOptionPane.showMessageDialog(null,Idioma.getString("msgLotRegistrySuccess")); //$NON-NLS-1$
		}
		ConectarDBA.cerrarCon();
	}
	
	
	
	/**
	 * Método con el que relizaremos las Bajas de parcela
	 * @throws SQLException
	 */

	public static void bajaParcela(String id) throws SQLException{
		ConectarDBA.acceder();
		String sentencia = "UPDATE parcela SET `activo` = '0' WHERE idparcela  LIKE '"+id+"'"; //$NON-NLS-1$ //$NON-NLS-2$
		ConectarDBA.modificar(sentencia);
		JOptionPane.showMessageDialog(null,Idioma.getString("msgHasBeenDeactivated")); //$NON-NLS-1$
		ConectarDBA.cerrarCon();
	}
	
	public static void activarParcela(String id) throws SQLException{
		ConectarDBA.acceder();
		String sentencia = "UPDATE parcela SET `activo` = '1' WHERE idparcela  LIKE '"+id+"'"; //$NON-NLS-1$ //$NON-NLS-2$
		ConectarDBA.modificar(sentencia);
		JOptionPane.showMessageDialog(null,Idioma.getString("msgHasBeenActivated")); //$NON-NLS-1$
		ConectarDBA.cerrarCon();
	}
	
	/**
	 * Método con el que realizaremos las Modificaciones de parcela
	 * @throws SQLException
	 */
	public void modificarParcela() throws SQLException{
		
			sentencia = "UPDATE `dai2opengis`.`parcela` SET `idparcela`= `"+this.idparcela+"`,`alias`=`"+this.alias+"`," + //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					" `provincia`= `"+this.provincia+"`, `poblacion`= `"+this.poblacion+"`,`poligono`= `"+this.poligono+"`," + //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
							"`numero`= `"+this.numero+"`,`activo`= 1,`partida`= `"+this.partida+"`,`dni_propietario`= `"+this.dniPropietario+ //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
							"`  WHERE `idparcela` = `"+this.idparcela+"`)"; //$NON-NLS-1$ //$NON-NLS-2$
	
			ConectarDBA.modificar(sentencia);
			JOptionPane.showMessageDialog(null,Idioma.getString("msgLotModifySuccess")); //$NON-NLS-1$
	}

		
	public void calcularPosicion() throws SQLException{
		int count=0;
		ConectarDBA.acceder();
		sentencia="Select * FROM `parcela`"; //$NON-NLS-1$
		ResultSet rs = ConectarDBA.consulta(sentencia);
		while(rs.next()){
			count=count+1;
		}
		rs.close();
		JOptionPane.showMessageDialog(null, count);
		ConectarDBA.cerrarCon();
	}
	

	public static boolean isValido() {
		return valido;
	}

	public static void setValido(boolean valido) {
		Parcela.valido = valido;
	}

	
	
}
