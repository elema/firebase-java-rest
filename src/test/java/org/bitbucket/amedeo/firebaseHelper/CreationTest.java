package org.bitbucket.amedeo.firebaseHelper;

import org.bitbucket.amedeo.firebaseHelper.model.Item;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @Exclude Class containing tests
 */
public class CreationTest {

    //firebase nodes
    private static final String NODE_BASE = "https://sandbox-project-8c68c.firebaseio.com";

    //test model object
    Item item;

    //helper object
    FirebaseHelper firebaseHelper;

    @Before
    public void setup(){

        //create test model object
        item = new Item("Item 1", "Test item 1");

        firebaseHelper = FirebaseHelper.getInstance(NODE_BASE);

    }

    @Test
    public void insert_shouldReturnJsonInserted(){

        //insert item without specifying node
        String result = firebaseHelper.insert(item);

        //check the result equals the expected
        assertNotNull(result);

    }

    @Test
    public void select_shouldReturnJson(){

        String result = firebaseHelper.select(item.getClass().getSimpleName());

        assertNotNull(result);

    }


}
