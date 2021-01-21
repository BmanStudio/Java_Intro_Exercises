import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BigNumberTest {

    @Test
    void BigNumberTest() {
        BigNumber bigNumber1 = new BigNumber(0);

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
        assertEquals("123456", bigNumber1.toString());
        assertEquals("105456", bigNumber2.toString());

        BigNumber bigNumber3 = new BigNumber(105459);
        BigNumber bigNumber4 = new BigNumber(15459);
        assertEquals("120918", bigNumber3.addBigNumber(bigNumber4).toString());
        assertEquals("15459", bigNumber4.toString());


    }

    @Test
    void compareTo() {
        BigNumber bigNumber1 = new BigNumber(150);
        BigNumber bigNumber2 = new BigNumber(200);
        assertEquals(-1, bigNumber1.compareTo(bigNumber2));
        assertEquals("150", bigNumber1.toString());
        assertEquals("200", bigNumber2.toString());

        BigNumber bigNumber3 = new BigNumber(1500);
        BigNumber bigNumber4 = new BigNumber(200);
        assertEquals(1, bigNumber3.compareTo(bigNumber4));
        assertEquals("1500", bigNumber3.toString());
        assertEquals("200", bigNumber4.toString());

        BigNumber bigNumber5 = new BigNumber(1500);
        BigNumber bigNumber6 = new BigNumber(15000);
        assertEquals(-1, bigNumber5.compareTo(bigNumber6));
        assertEquals("1500", bigNumber5.toString());
        assertEquals("15000", bigNumber6.toString());

        BigNumber bigNumber7 = new BigNumber(1500);
        BigNumber bigNumber8 = new BigNumber(2000);
        assertEquals(-1, bigNumber7.compareTo(bigNumber8));
        bigNumber7 = bigNumber7.addBigNumber(bigNumber8);
//        System.out.println(bigNumber7.toString());
        assertEquals(1, bigNumber7.compareTo(bigNumber8));
        assertEquals("3500", bigNumber7.toString());
        assertEquals("2000", bigNumber8.toString());

        BigNumber bigNumber10 = new BigNumber(15000);
        BigNumber bigNumber11 = new BigNumber(15000);
        assertEquals(0, bigNumber10.compareTo(bigNumber11));

        BigNumber bigNumber12 = new BigNumber(15000);
        BigNumber bigNumber13 = new BigNumber(15001);
        assertEquals(-1, bigNumber12.compareTo(bigNumber13));

        BigNumber bigNumber14 = new BigNumber(900000);
        BigNumber bigNumber15 = new BigNumber(899999);
        assertEquals(1, bigNumber14.compareTo(bigNumber15));

        BigNumber bigNumber16 = new BigNumber(900000);
        BigNumber bigNumber17 = new BigNumber(8999991);
        assertEquals(-1, bigNumber16.compareTo(bigNumber17));

        BigNumber bigNumber18 = new BigNumber();
        BigNumber bigNumber19 = new BigNumber();
        assertEquals(0, bigNumber18.compareTo(bigNumber19));

        BigNumber bigNumber20 = new BigNumber();
        BigNumber bigNumber21 = new BigNumber(bigNumber20);
        assertEquals(0, bigNumber20.compareTo(bigNumber21));

        bigNumber20 = bigNumber20.addBigNumber(new BigNumber(1000000));
        assertEquals(1, bigNumber20.compareTo(bigNumber21));
        bigNumber21 = bigNumber21.addBigNumber(bigNumber20);
        assertEquals(0, bigNumber20.compareTo(bigNumber21));
    }

    @Test
    void addLong() {
        BigNumber bigNumber1 = new BigNumber(100);
        BigNumber bigNumber2 = bigNumber1.addLong(100);
        assertEquals("200", bigNumber2.toString());

        BigNumber bigNumber3 = new BigNumber(15547);
        BigNumber bigNumber4 = bigNumber3.addLong(0);
        assertEquals("15547", bigNumber4.toString());

        BigNumber bigNumber5 = new BigNumber(15547);
        BigNumber bigNumber6 = bigNumber5.addLong(105);
        assertEquals("15652", bigNumber6.toString());

        BigNumber bigNumber7 = new BigNumber(105);
        BigNumber bigNumber8 = bigNumber7.addLong(15547);
        assertEquals("15652", bigNumber8.toString());

        BigNumber bigNumber9 = new BigNumber(0);
        BigNumber bigNumber10 = bigNumber9.addLong(15547);
        assertEquals("15547", bigNumber10.toString());
    }

    @Test
    void subtractBigNumber() {
        BigNumber bigNumber1 = new BigNumber(5391);
        BigNumber bigNumber2 = new BigNumber(3589);
        BigNumber bigNumber1m2 = bigNumber1.subtractBigNumber(bigNumber2);
        assertEquals("1802", bigNumber1m2.toString());

        BigNumber bigNumber3 = new BigNumber(1000);
        bigNumber1m2 = bigNumber1m2.subtractBigNumber(bigNumber3);
        assertEquals("802", bigNumber1m2.toString());

        BigNumber bigNumber4 = new BigNumber(1000);
        bigNumber1m2 = bigNumber1m2.subtractBigNumber(bigNumber4);
        assertEquals("198", bigNumber1m2.toString());

        BigNumber bigNumber5 = new BigNumber(3005);
        BigNumber bigNumber6 = new BigNumber(3003);
        BigNumber bigNumber56 = bigNumber6.subtractBigNumber(bigNumber5);
        assertEquals("2", bigNumber56.toString());

        BigNumber bigNumber7 = bigNumber56.addBigNumber(bigNumber6);
        BigNumber bigNumber75 = bigNumber5.subtractBigNumber(bigNumber7);
        assertEquals("0", bigNumber75.toString());

        BigNumber bigNumber8 = new BigNumber(10000);
        BigNumber bigNumber9 = new BigNumber(20000);
        BigNumber bigNumber89 = bigNumber9.subtractBigNumber(bigNumber8);
        assertEquals("10000", bigNumber89.toString());


        bigNumber89 = bigNumber89.subtractBigNumber(bigNumber89);
        assertEquals("0", bigNumber89.toString());

        BigNumber bigNumber10 = new BigNumber(6785675634530000000L);
        BigNumber bigNumber11 = new BigNumber(1);
        BigNumber bigNumber1011 = bigNumber10.subtractBigNumber(bigNumber11);
        assertEquals("6785675634529999999", bigNumber1011.toString());

        bigNumber1011 = bigNumber1011.addBigNumber(bigNumber1011);
        assertEquals("13571351269059999998", bigNumber1011.toString());

        BigNumber bigNumber1012 = bigNumber1011.addBigNumber(bigNumber1011);
        assertEquals("27142702538119999996", bigNumber1012.toString());

        bigNumber1012 = bigNumber1012.subtractBigNumber(bigNumber1011);
        assertEquals("13571351269059999998", bigNumber1012.toString());

        BigNumber bigNumber13 = new BigNumber(59999990);
        bigNumber1012 = bigNumber1012.subtractBigNumber(bigNumber13);
        assertEquals("13571351269000000008", bigNumber1012.toString());

        BigNumber bigNumber14 = new BigNumber(3571351269000000000L);
        bigNumber1012 = bigNumber1012.subtractBigNumber(bigNumber14);
        assertEquals("10000000000000000008", bigNumber1012.toString());

        bigNumber1012 = bigNumber1012.subtractBigNumber(new BigNumber(9));
        assertEquals("9999999999999999999", bigNumber1012.toString());
    }
}