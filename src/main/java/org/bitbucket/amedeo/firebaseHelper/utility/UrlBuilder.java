package org.bitbucket.amedeo.firebaseHelper.utility;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UrlBuilder {

    /** Tag for the log messages */
    private static final Logger LOGGER = Logger.getLogger( UrlBuilder.class.getName() );

    /**
     * Returns a URL object from a URL string
     * @param stringUrl
     * @return url object
     */
    public static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            LOGGER.log(Level.FINE, "Error closing input stream", e);
        }
        return url;
    }

}
