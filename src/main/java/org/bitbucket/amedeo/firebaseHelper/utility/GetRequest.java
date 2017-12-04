package org.bitbucket.amedeo.firebaseHelper.utility;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.bitbucket.amedeo.firebaseHelper.utility.StreamReader.readFromStream;

public class GetRequest {

    /** Tag for the log messages */
    private static final Logger LOGGER = Logger.getLogger( GetRequest.class.getName() );

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     * @param url
     * @return json response
     * @throws IOException
     */
    public static String makeGetRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

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
