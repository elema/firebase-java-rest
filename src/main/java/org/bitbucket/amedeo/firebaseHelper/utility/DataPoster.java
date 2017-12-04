package org.bitbucket.amedeo.firebaseHelper.utility;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.bitbucket.amedeo.firebaseHelper.utility.PostRequest.makePostRequest;
import static org.bitbucket.amedeo.firebaseHelper.utility.UrlBuilder.createUrl;

public class DataPoster {

    /** Tag for the log messages */
    private static final Logger LOGGER = Logger.getLogger( DataPoster.class.getName() );

    /**
     * Method that accepts a url and return a json response
     * @param requestUrl
     * @return json response
     */
    public static String postData(String requestUrl, String data) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makePostRequest(url ,data);
        } catch (IOException e) {
            LOGGER.log(Level.FINE, "Error closing input stream", e);
        }

        //return the json response
        return jsonResponse;
    }

}
