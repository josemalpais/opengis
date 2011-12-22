package resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.JOptionPane;


/**
 * Clase encargada de hacer las conversiones de RC a Coordenada XY, para posteriormente mostrar una parcela en concreto
 * @author knals
 *
 */
public class W3CSigPac {

	private String auxx="";
    private String auxy="";
	
	
	public W3CSigPac (String u) throws IOException, KeyManagementException, NoSuchAlgorithmException{
        SSLContext ctx = SSLContext.getInstance("TLS");
        //aceptamos todos los tipos de certificado
        ctx.init(new KeyManager[0], new TrustManager[] {new DefaultTrustManager()}, new SecureRandom());
        SSLContext.setDefault(ctx);

		URL url = new URL(u);

        HttpsURLConnection conexion = (HttpsURLConnection) url.openConnection();
        conexion.setDoOutput(true);
        conexion.setDoInput(true);
        conexion.setRequestMethod("GET");
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        
        //JOptionPane.showMessageDialog(null, reader);
        String line;
        String x,y;
        int p=1;
        boolean bolx=true;
        boolean boly=true;
        do {
        	line=reader.readLine();
        	x=reader.readLine();
        	y=reader.readLine();
        	//tomamos la quinta linea donde se encuentran las coordenadas
        	if (p==5){
        		int v;
        		for (v=0;v<x.length();v++){
        			if((Character.isDigit(x.charAt(v))==true)&& bolx==true){
        				auxx=auxx+x.charAt(v);
        			}
        			//no tomara los decimales
        			if(x.charAt(v)=='.'){
        				bolx=false;
        			}
        			if((Character.isDigit(y.charAt(v))==true)&& boly==true){
        				auxy=auxy+y.charAt(v);
        			}
        			//no tomara los decimales
        			if(x.charAt(v)=='.'){
        				boly=false;
        			}
        		}
            }
            p=p+1;
        }while (line != null);
		System.out.println(auxx);
		System.out.println(auxy);
        
        conexion.setHostnameVerifier(new HostnameVerifier() {
        @Override
        public boolean verify(String arg0, SSLSession arg1) {
             return true;
         }
        });
        //System.out.println(conn.getResponseCode());
        //System.out.println(conn);
        conexion.disconnect();
    }
    /**
     * Clase que nos aceptará todos los certificados.
     * @author knals
     *
     */
    private class DefaultTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

    }
    public String getAuxx() {
		return auxx;
	}
	public String getAuxy() {
		return auxy;
	}
}


