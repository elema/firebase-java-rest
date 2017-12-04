package org.bitbucket.amedeo.firebaseHelper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static org.bitbucket.amedeo.firebaseHelper.utility.DataPoster.postData;
import static org.bitbucket.amedeo.firebaseHelper.utility.DataFetcher.fetchData;

/**
 * Main class containing the CRUD methods
 */
public class FirebaseHelper {

    //instance field
    private static FirebaseHelper firebaseHelper = new FirebaseHelper();

    //base path
    private String basePath;

    //instantiate as singleton
    private FirebaseHelper(){}

    /**
     * Singleton instance constructor
     * @param basePath is the path parent of all entity nodes. This is ideally the user's auth token
     * @return instance of the helper class
     */
    public static FirebaseHelper getInstance(String basePath) {
        firebaseHelper.setBasePath(basePath);
        return firebaseHelper;
    }

    /**
     * Method to insert a new record for one entity node
     * @param o
     * @return json response of data
     */
    public String insert(Object o){

        //prepare write url
        String url = firebaseHelper.getBasePath() + "/" + o.getClass().getSimpleName() + ".json";

        //turn object into json
        Gson gson = new GsonBuilder().create();
        String data = gson.toJson(o);// obj is your object

        return postData(url, data);

    }

    /**
     * Method to return all records for one entity node
     * @param entity
     * @return json response of data
     */
    public String select(String entity) {

        String url = firebaseHelper.getBasePath() + "/" + entity + ".json";

        return fetchData(url);

    }

    //getters and setters
    private String getBasePath() {
        return basePath;
    }
    private void setBasePath(String basePath) {
        this.basePath = basePath;
    }

}
