package utils;

import org.junit.Before;
import org.junit.BeforeClass;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Demils on 31/05/2016.
 */
public abstract class DatabaseTest {

    protected static TestUtils mTestUtils;

    @BeforeClass
    public static void setUpTests(){
        mTestUtils = new TestUtils();
    }

    @Before
    public void vaciarDatabase(){
        mTestUtils.vaciarDB();
    }
}
