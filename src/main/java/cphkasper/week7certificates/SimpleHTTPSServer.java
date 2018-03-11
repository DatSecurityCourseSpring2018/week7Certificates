package cphkasper.week7certificates;

/**
 Stolen from
 https://stackoverflow.com/questions/2308479/simple-java-https-server
 Modified by Kasper Østerbye
 */
import java.io.*;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpsServer;
import java.security.KeyStore;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;
import com.sun.net.httpserver.*;

import javax.net.ssl.SSLContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public class SimpleHTTPSServer {

    public static class MyHandler implements HttpHandler {

        @Override
        public void handle( HttpExchange t ) throws IOException {
            String response = "This is the response, noone tampered with it";
            //HttpsExchange httpsExchange = (HttpsExchange) t;
            t.getResponseHeaders().add( "Access-Control-Allow-Origin", "*" );
            t.sendResponseHeaders( 200, response.length() );
            try ( OutputStream os = t.getResponseBody() ) {
                os.write( response.getBytes() );
            }
        }
    }

    public static void main( String[] args ) throws Exception {

        int portNumber = 5678;
        try {
            // setup the socket address
            InetSocketAddress address = new InetSocketAddress( portNumber );

            // initialise the HTTPS server
            HttpsServer httpsServer = HttpsServer.create( address, 0 );

            // initialise the keystore
            String keyStoreLocation = "/home/ralf/kasperskeys.jks";
            char[] password = "surturR1hest".toCharArray();
            KeyStore ks = KeyStore.getInstance( "JKS" );
            FileInputStream fis = new FileInputStream( keyStoreLocation );
            ks.load( fis, password );

            // setup the key manager factory
            KeyManagerFactory kmf = KeyManagerFactory.getInstance( "SunX509" );
            kmf.init( ks, password );

            // setup the trust manager factory
            TrustManagerFactory tmf = TrustManagerFactory.getInstance( "SunX509" );
            tmf.init( ks );

            // setup the HTTPS context and parameters
            SSLContext sslContext = SSLContext.getInstance( "TLS" );
            sslContext.init( kmf.getKeyManagers(), tmf.getTrustManagers(), null );
            HttpsConfigurator configurator = new SimpleHttpsConfigurator( sslContext );
            
            httpsServer.setHttpsConfigurator( configurator );
            httpsServer.createContext( "/test", new MyHandler() );
            httpsServer.setExecutor( null ); // creates a default executor
            System.out.println( "Starting server at https://localhost:" + portNumber + "/test" );
            httpsServer.start();

        } catch ( IOException | KeyManagementException | KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException | CertificateException exception ) {
            System.out.println( "Failed to create HTTPS server on port " + portNumber + " of localhost" );
            exception.printStackTrace();
        }
    }

}
