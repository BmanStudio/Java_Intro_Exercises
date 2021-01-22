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
        assertEquals("123456", bigNumber1.toString());

        BigNumber bigNumber2 = new BigNumber(105456);
        assertEquals("105456", bigNumber2.toString());

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

    @Test
    void multBigNumber() {
        BigNumber bigNumber1 = new BigNumber(150);
        BigNumber bigNumber2 = new BigNumber(10);
        assertEquals("1500", bigNumber1.multBigNumber(bigNumber2).toString());

        BigNumber bigNumber3 = new BigNumber(5879);
        assertEquals("8818500", bigNumber3.multBigNumber(bigNumber2.multBigNumber(bigNumber1)).toString());

        BigNumber bigNumber4 = new BigNumber(1);
        BigNumber bigNumber5 = new BigNumber(10879);
        assertEquals("10879", bigNumber4.multBigNumber(bigNumber5).toString());

        BigNumber bigNumber6 = new BigNumber(0);
        BigNumber bigNumber7 = new BigNumber(10879);
        assertEquals("0", bigNumber7.multBigNumber(bigNumber6).toString());
        assertEquals("0", bigNumber6.multBigNumber(bigNumber7).toString());

        BigNumber bigNumber8 = new BigNumber(8912379123L);
        assertEquals("96957772479117", bigNumber8.multBigNumber(bigNumber7).toString());


        BigNumber bigNumber9 = new BigNumber(0);
        BigNumber bigNumber10 = new BigNumber(0);
        assertEquals("0", bigNumber10.multBigNumber(bigNumber9).toString());

        BigNumber bigNumber11 = new BigNumber(2);
        BigNumber bigNumber12 = new BigNumber(4);
        assertEquals(0, bigNumber11.multBigNumber(bigNumber11).compareTo(bigNumber12));

        bigNumber11 = bigNumber11.addBigNumber(new BigNumber(bigNumber11));
        bigNumber12 = bigNumber12.multBigNumber(bigNumber12);
        assertEquals(0, bigNumber11.multBigNumber(bigNumber11).compareTo(bigNumber12));

        bigNumber11 = bigNumber11.addBigNumber(new BigNumber(bigNumber11));
        assertEquals(1, bigNumber11.multBigNumber(bigNumber11).compareTo(bigNumber12));

        BigNumber bigNumber13 = new BigNumber(103);
        BigNumber bigNumber14 = new BigNumber(99);
        assertEquals("10197", bigNumber13.multBigNumber(bigNumber14).toString());

        BigNumber bigNumber15 = new BigNumber(123);
        BigNumber bigNumber16 = new BigNumber(11);
        assertEquals("1353", bigNumber15.multBigNumber(bigNumber16).toString());
    }

    @Test
    public void toStringOfBigNumber() {

        BigNumber b1 = new BigNumber(1234567895432L);
        assertEquals("1234567895432", b1.toString());

        BigNumber b2 = new BigNumber(123L);
        assertEquals("123", b2.toString());

        BigNumber b3 = new BigNumber();
        assertEquals("0", b3.toString());

        BigNumber b4 = new BigNumber(b1);
        assertEquals("1234567895432", b4.toString());

    }

    @Test
    public void compareTo2() {
        BigNumber b1 = new BigNumber();
        assertEquals(0, b1.compareTo(b1));

        BigNumber b2 = new BigNumber(1234567895432L);
        assertEquals(-1, b1.compareTo(b2));
        assertEquals(1, b2.compareTo(b1));

        BigNumber b3 = new BigNumber(1000000009L);
        assertEquals(-1, b3.compareTo(b2));

        BigNumber b4 = new BigNumber(234567895432L);
        assertEquals(-1, b4.compareTo(b2));

        BigNumber b5 = new BigNumber(900000009L);
        BigNumber b6 = new BigNumber(123456789L);
        assertEquals(1, b5.compareTo(b6));
        assertEquals(-1, b6.compareTo(b5));

        BigNumber b7 = new BigNumber(123496789L);
        assertEquals(1, b7.compareTo(b6));

        BigNumber b8 = new BigNumber(123496788L);
        assertEquals(1, b7.compareTo(b8));

        BigNumber b9 = new BigNumber(223496788L);
        assertEquals(1, b9.compareTo(b8));

        BigNumber b10 = new BigNumber(223496788L);
        assertEquals(0, b10.compareTo(b9));


    }

    @Test
    public void addBigNumber2() {
        BigNumber b1 = new BigNumber();
        BigNumber b2 = b1.addBigNumber(new BigNumber(123456789L));
        assertEquals("123456789", b2.toString());

        BigNumber b3 = b2.addBigNumber(new BigNumber(123456789L));
        assertEquals("246913578", b3.toString());

        BigNumber b4 = b3.addBigNumber(new BigNumber(946913578L));
        assertEquals("1193827156", b4.toString());

        BigNumber b5 = b4.addBigNumber(new BigNumber(100000000000L));
        assertEquals("101193827156", b5.toString());

        BigNumber b6 = b5.addBigNumber(new BigNumber());
        assertEquals("101193827156", b6.toString());

        BigNumber b7 = b6.addBigNumber(new BigNumber(b6));
        assertEquals("202387654312", b7.toString());
    }

    @Test
    public void addLong2() {
        BigNumber b1 = new BigNumber();
        BigNumber b2 = b1.addLong(123456789L);
        assertEquals("123456789", b2.toString());

        BigNumber b3 = b2.addLong(123456789L);
        assertEquals("246913578", b3.toString());

        BigNumber b4 = b3.addLong(946913578L);
        assertEquals("1193827156", b4.toString());

        BigNumber b5 = b4.addLong(100000000000L);
        assertEquals("101193827156", b5.toString());

    }

    @Test
    public void subtractBigNumber2() {
        BigNumber b1 = new BigNumber();
        BigNumber b2 = new BigNumber(123456789L);
        assertEquals("123456789", b2.subtractBigNumber(b1).toString());
        assertEquals("123456789", b1.subtractBigNumber(b2).toString());


        BigNumber b3 = new BigNumber(1023456789L);
        assertEquals("900000000", b3.subtractBigNumber(b2).toString());

        BigNumber b4 = new BigNumber(10289L);
        assertEquals("123446500", b4.subtractBigNumber(b2).toString());

        BigNumber b5 = new BigNumber(100000087L);
        assertEquals("23456702", b5.subtractBigNumber(b2).toString());


        BigNumber b6 = new BigNumber(32L);
        BigNumber b7 = new BigNumber(27L);
        assertEquals("5", b6.subtractBigNumber(b7).toString());


        BigNumber b8 = new BigNumber(1000032L);
        BigNumber b9 = new BigNumber(27L);
        assertEquals("1000005", b8.subtractBigNumber(b9).toString());


        BigNumber b10 = new BigNumber(3005L);
        BigNumber b11 = new BigNumber(3003L);
        assertEquals("2", b11.subtractBigNumber(b10).toString());
        assertEquals("0", b11.subtractBigNumber(b11).toString());
    }

    @Test
    public void multBigNumber2() {
        BigNumber b1 = new BigNumber();
        BigNumber b2 = new BigNumber(123456789L);
        assertEquals("0", b2.multBigNumber(b1).toString());
        assertEquals("0", b1.multBigNumber(b2).toString());

        BigNumber b3 = new BigNumber(1087L);
        assertEquals("134197529643", b3.multBigNumber(b2).toString());

        BigNumber bigNumber1 = new BigNumber(150);
        BigNumber bigNumber2 = new BigNumber(10);
        assertEquals("1500", bigNumber1.multBigNumber(bigNumber2).toString());

        BigNumber bigNumber3 = new BigNumber(5879);
        assertEquals("8818500", bigNumber3.multBigNumber(bigNumber2.multBigNumber(bigNumber1)).toString());

        BigNumber bigNumber4 = new BigNumber(1);
        BigNumber bigNumber5 = new BigNumber(10879);
        assertEquals("10879", bigNumber4.multBigNumber(bigNumber5).toString());

        BigNumber bigNumber6 = new BigNumber(0);
        BigNumber bigNumber7 = new BigNumber(10879);
        assertEquals("0", bigNumber7.multBigNumber(bigNumber6).toString());
        assertEquals("0", bigNumber6.multBigNumber(bigNumber7).toString());

        BigNumber bigNumber8 = new BigNumber(8912379123L);
        assertEquals("96957772479117", bigNumber8.multBigNumber(bigNumber7).toString());

        BigNumber bigNumber9 = new BigNumber(0);
        BigNumber bigNumber10 = new BigNumber(0);
        assertEquals("0", bigNumber10.multBigNumber(bigNumber9).toString());

        BigNumber bigNumber11 = new BigNumber(2);
        BigNumber bigNumber12 = new BigNumber(4);
        assertEquals(0, bigNumber11.multBigNumber(bigNumber11).compareTo(bigNumber12));

        bigNumber11 = bigNumber11.addBigNumber(new BigNumber(bigNumber11));
        bigNumber12 = bigNumber12.multBigNumber(bigNumber12);
        assertEquals(0, bigNumber11.multBigNumber(bigNumber11).compareTo(bigNumber12));

        bigNumber11 = bigNumber11.addBigNumber(new BigNumber(bigNumber11));
        assertEquals(1, bigNumber11.multBigNumber(bigNumber11).compareTo(bigNumber12));

        BigNumber bigNumber13 = new BigNumber(999);
        BigNumber bigNumber14 = new BigNumber(1003);
        assertEquals("1001997", bigNumber13.multBigNumber(bigNumber14).toString());

    }
}
