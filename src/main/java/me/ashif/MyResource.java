package me.ashif;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


/**
 * Root resource (exposed at "myresource" path)
 */
@Path("rates")
public class MyResource {
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRates() throws IOException {
        URL url = new URL("http://www.apilayer.net/api/live?access_key=00926a6373e932b5d61fa824897b5c5a&format=1");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        StringBuffer stringBuffer = new StringBuffer();

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output;
        while ((output = br.readLine()) != null) {
            stringBuffer.append(output);
        }
        conn.disconnect();
        StringBuffer trimmed = new StringBuffer();
        trimmed = stringBuffer.replace(5,125,"");
        return trimmed.toString();
  }
}



