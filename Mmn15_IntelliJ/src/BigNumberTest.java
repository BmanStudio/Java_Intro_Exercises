import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BigNumberTest {

    @Test
    void BigNumberTest() {
        BigNumber bigNumber1 = new BigNumber();

        BigNumber bigNumber2 = new BigNumber(12345);
        System.out.println(bigNumber2.toString());
        BigNumber bigNumber3 = new BigNumber(1020304050);
        long aroh = 999999999;
        aroh *= aroh * aroh;
        aroh *= aroh;
        BigNumber bigNumber4 = new BigNumber(aroh);
        System.out.println(bigNumber4.toString());
        // Copy Constructor:

        BigNumber bigNumber5 = new BigNumber(bigNumber4);
        BigNumber bigNumber6 = new BigNumber(bigNumber5);
        BigNumber bigNumber7 = new BigNumber(bigNumber2);
    }

    @Test
    void testToString() {
        BigNumber bigNumber1 = new BigNumber(12345);
        assertEquals("12345", bigNumber1.toString());

        BigNumber bigNumber2 = new BigNumber(1054345345);
        assertEquals("1054345345", bigNumber2.toString());

        BigNumber bigNumber3 = new BigNumber();
        assertEquals("0", bigNumber3.toString());

        BigNumber bigNumber4 = new BigNumber(1);
        assertEquals("1", bigNumber4.toString());

        BigNumber bigNumber5 = new BigNumber(bigNumber2);
        assertEquals("1054345345", bigNumber5.toString());
    }

    @Test
    void addBigNumber() {
        BigNumber bigNumber1 = new BigNumber(123456);
//        assertEquals("12345", bigNumber1.toString());

        BigNumber bigNumber2 = new BigNumber(105456);
//        assertEquals("1054345345", bigNumber2.toString());

        assertEquals("228912", bigNumber1.addBigNumber(bigNumber2).toString());

        BigNumber bigNumber3 = new BigNumber(105459);
        BigNumber bigNumber4 = new BigNumber(15459);
        assertEquals("120918", bigNumber3.addBigNumber(bigNumber4).toString());

    }
}