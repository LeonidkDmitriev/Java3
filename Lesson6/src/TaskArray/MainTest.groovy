package TaskArray

import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Test

class MainTest {
    private static Main maintest;
    @BeforeClass
    public static void init(){
        System.out.println("Инициализация");
        maintest = new Main();
    }

    @Test
    public void jastFile() {
      Assert.fail();

    }
    @Test
    void testClippingArray() {
     // Assert.assertArrayEquals(maintest.clippingArray(4,1,1),1,1); //ок
     // Assert.assertArrayEquals(maintest.clippingArray(1,1),1,1);  //провал

    }

    @Test
    void testIsOneWihtFour() {
     // Assert.assertEquals(maintest.isOneWihtFour(0), false); // ок
     // Assert.assertEquals(maintest.isOneWihtFour(4,1,1),true); //ок
      //  Assert.assertFalse( maintest.isOneWihtFour(0)) //ок
        Assert.assertTrue( maintest.isOneWihtFour(4,1)) //ок
    }
}
