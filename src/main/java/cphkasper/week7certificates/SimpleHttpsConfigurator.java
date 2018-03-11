package cphkasper.week7certificates;

import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsParameters;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;

/**
 The purpose of SimpleHttpsConfigurator is to...

 @author kasper
 */
public class SimpleHttpsConfigurator extends HttpsConfigurator {

    public SimpleHttpsConfigurator( SSLContext sslc ) {
        super( sslc );
    }

    @Override
    public void configure( HttpsParameters params ) {
        try {
            // initialise the SSL context
            SSLContext c = SSLContext.getDefault();
            SSLEngine engine = c.createSSLEngine();
            params.setNeedClientAuth( false );
            params.setCipherSuites( engine.getEnabledCipherSuites() );
            params.setProtocols( engine.getEnabledProtocols() );

            // get the default parameters
            SSLParameters defaultSSLParameters = c.getDefaultSSLParameters();
            params.setSSLParameters( defaultSSLParameters );

        } catch ( NoSuchAlgorithmException ex ) {
            System.out.println( "Failed to create HTTPS port" );
        }
    }
}
