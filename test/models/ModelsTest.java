package models;

import org.junit.Before;
import org.junit.Test;
import play.test.Helpers;
import play.test.WithApplication;

public class ModelsTest extends WithApplication{
    @Before
    public void setUp(){
        start(Helpers.fakeApplication(Helpers.inMemoryDatabase()));
    }

    @Test
    public void createAndRetrieveNeighbor(){
        new Neighbor("mamad", "1234").save();
        Neighbor mamad = Neighbor.find.where().eq("username", "mamad").findUnique();
        org.junit.Assert.assertNotNull(mamad);
        org.junit.Assert.assertEquals("mamad", mamad.username);
    }
}
