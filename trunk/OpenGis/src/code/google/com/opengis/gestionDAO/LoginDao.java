package code.google.com.opengis.gestionDAO;

	import java.sql.ResultSet;
	import java.sql.SQLException;
	
	public class LoginDao {
	String user;
	String password;
	char tipodato;
	 String lol;
	
	/***
	 *CONSTRUCTOR
	 ***/
	public LoginDao(String user, String password){
		this.user= user;
		this.password = password;
		
	}
	public LoginDao(String user){
		this.user= user;
		this.password = null;
		
	}
	/***
	 *Getters & setters
	 ***/
		public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public char getTipodato() {
		return tipodato;
	}
	
	public void setTipodato(char tipodato) {
		this.tipodato = tipodato;
	}
	
	
	/***
	 *Metodo que introduce el usuario y contraseņa a la base de datos para comparar y devuelve un
	 *char con el tipo de usuario que tiene que ser
	 ***/
		public char datosDba() throws SQLException{
		ConectarDBA dba = new ConectarDBA();
	    dba.acceder();		     
	    
	    
	     String sentencia = "SELECT dni, password, tipo FROM usuario WHERE dni LIKE '"+this.user+"'AND password LIKE '"+this.password+"'";
	     ResultSet rs = dba.consulta(sentencia);
	     
	     while (rs.next()){
	    	 String tipo = rs.getString(3);
		      tipodato = tipo.charAt(0);
		      } 
		      rs.close();
		      dba.cerrarCon();
	     return tipodato;
		}
		
		public String email() throws SQLException{
			ConectarDBA dba = new ConectarDBA();
		    dba.acceder();		     
		    
		    
		     String sentencia = "SELECT usuario.email FROM dai2opengis.usuario WHERE dni LIKE '"+this.user+"'";
		     ResultSet rs = dba.consulta(sentencia);
		    
		     while (rs.next()){
		    	  lol = rs.getString(1);
		    	 }
		     return lol;
		}
		public String pass() throws SQLException{
			ConectarDBA dba = new ConectarDBA();
		    dba.acceder();		     
		    
		    
		     String sentencia = "SELECT usuario.password FROM dai2opengis.usuario WHERE dni LIKE '"+this.user+"'";
		     ResultSet rs = dba.consulta(sentencia);
		     while (rs.next()){
		    	  lol = rs.getString(1);
		    	 }
		     return lol;
		}
	}
	
