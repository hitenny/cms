package org.cms;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import static org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory.*;

public class CmsApp {
        // Base URI the Grizzly HTTP server will listen on
        public static final String BASE_URI = "http://localhost:8080/cms/";

        /**
         * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
         * @return Grizzly HTTP server.
         */
        public static HttpServer startServer() {
            // create a resource config that scans for JAX-RS resources and providers
            // in com.underdog.jersey.grizzly package
            final ResourceConfig rc = new ResourceConfig()
                    .packages("org.cms")
                    .register(getJsonProvider());

            // create and start a new instance of grizzly http server
            // exposing the Jersey application at BASE_URI
            return createHttpServer(URI.create(BASE_URI), rc);
        }

        /**
         * Main method.
         * @param args
         * @throws IOException
         */
        public static void main(String[] args) throws IOException {
            final HttpServer server = startServer();
            System.out.println(String.format("Jersey app started with WADL available at "
                    + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
            System.in.read();
            server.stop();
        }
        
        private static JacksonJaxbJsonProvider getJsonProvider() {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
            
            // create JsonProvider to provide custom ObjectMapper
            JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
            provider.setMapper(mapper);
            
            return provider;
        }
}
