package org.bitbucket.amedeo.firebaseHelper.utility;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.bitbucket.amedeo.firebaseHelper.utility.StreamReader.readFromStream;

public class PostRequest {

    /** Tag for the log messages */
    private static final Logger LOGGER = Logger.getLogger( GetRequest.class.getName() );

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     * @param url
     * @return json response
     * @throws IOException
     */
    public static String makePostRequest(URL url, String data) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {

            byte[] postData       = data.getBytes( StandardCharsets.UTF_8 );
            int    postDataLength = postData.length;

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setDoOutput( true );
            urlConnection.setInstanceFollowRedirects( false );
            urlConnection.setRequestMethod( "POST" );
            urlConnection.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty( "charset", "utf-8");
            urlConnection.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
            urlConnection.setUseCaches( false );
            try( DataOutputStream wr = new DataOutputStream( urlConnection.getOutputStream())) {
                wr.write( postData );
            }

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {

                LOGGER.log(Level.FINE, "Error response code: " + urlConnection.getResponseCode());

            }
        } catch (IOException e) {

            LOGGER.log(Level.FINE, "Problem retrieving the JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

}
