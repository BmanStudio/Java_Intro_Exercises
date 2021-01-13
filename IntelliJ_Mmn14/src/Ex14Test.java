import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class Ex14Test {

    @org.junit.jupiter.api.Test
    void findSingle() {
        int[] a1 = {
                6, 6,
                18, 18,
                -4, -4,
                12,
                9, 9
        };

        assertEquals(12, Ex14.findSingle(a1));

        int[] a2 = {
                6, 6,
                18, 18,
                -4, -4,
                12,
                9, 9,
                8, 8,
                1, 1
        };

        assertEquals(12, Ex14.findSingle(a2));

        int[] a3 = {
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                5, 5,
                4
        };

        assertEquals(4, Ex14.findSingle(a3));

        int[] a4 = {
                5
        };

        assertEquals(5, Ex14.findSingle(a4));

        int[] a5 = {
                5, 5,
                1
        };

        assertEquals(1, Ex14.findSingle(a5));

        int[] a6 = {
                1,
                5, 5
        };

        assertEquals(1, Ex14.findSingle(a6));

        int[] a7 = {
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                5, 5,
                4,
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                5, 5
        };

        assertEquals(4, Ex14.findSingle(a7));

        int[] a8 = {
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                8, 8,
                4,
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                5, 5
        };

        assertEquals(4, Ex14.findSingle(a8));

        int[] a9 = {
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                8, 8,
                4,
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                5, 5,
                1, 1,
                0, 0,
                1, 1,
                0, 0,
                3, 3,
                12, 12
        };

        assertEquals(4, Ex14.findSingle(a9));

        int[] a10 = {
                3, 3,
                4, 4,
                4, 4,
                4, 4,
                0, 0,
                1,
                0, 0,
                0, 0,
                9, 9
        };

        assertEquals(1, Ex14.findSingle(a10));

        int[] a11 = {
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                8, 8,
                4, 4,
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                5, 5,
                1, 1,
                0, 0,
                1, 1,
                0, 0,
                3, 3,
                12, 12
        };

        assertEquals(Integer.MIN_VALUE, Ex14.findSingle(a11));

        int[] a12 = {
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                8, 8,
                4, 4,
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                5, 5,
                1, 1,
                0, 0,
                1, 1,
                0, 0,
                3, 3,
                12, 12,
                -500
        };

        assertEquals(-500, Ex14.findSingle(a12));

        int[] a13 = {
                -23,
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                8, 8,
                4, 4,
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                5, 5,
                1, 1,
                0, 0,
                1, 1,
                0, 0,
                3, 3,
                12, 12,
                -500, -500
        };

        assertEquals(-23, Ex14.findSingle(a13));

        int[] a14 = {
                45, 45,
                -23,
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                8, 8,
                4, 4,
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                5, 5,
                1, 1,
                0, 0,
                1, 1,
                0, 0,
                3, 3,
                12, 12,
                -500, -500
        };

        assertEquals(-23, Ex14.findSingle(a14));

        int[] a15 = {
                -23,
                8, 8,
                -500, -500
        };

        assertEquals(-23, Ex14.findSingle(a15));

        int[] a16 = {
                8, 8,
                -500, -500,
                -23
        };

        assertEquals(-23, Ex14.findSingle(a16));

        int[] a17 = {
                8, 8,
                -23
        };

        assertEquals(-23, Ex14.findSingle(a17));

        int[] a18 = {
                -23
        };

        assertEquals(-23, Ex14.findSingle(a18));

        int[] a19 = {
                0,
                123, 123,
                65, 65,
                76, 76,
                99, 99,
                9, 9,
                -12, -12,
                7655, 7655,
                0, 0,
                1, 1,
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                8, 8,
                4, 4,
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                5, 5,
                1, 1,
                0, 0,
                1, 1,
                0, 0,
                3, 3,
                12, 12,
                123, 123,
                65, 65,
                76, 76,
                99, 99,
                9, 9,
                -12, -12,
                7655, 7655,
                0, 0,
                1, 1,
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                8, 8,
                4, 4,
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                5, 5,
                1, 1,
                0, 0,
                1, 1,
                0, 0,
                3, 3,
                12, 12,
                123, 123,
                65, 65,
                76, 76,
                99, 99,
                9, 9,
                -12, -12,
                7655, 7655,
                0, 0,
                1, 1,
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                8, 8,
                4, 4,
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                5, 5,
                1, 1,
                0, 0,
                1, 1,
                0, 0,
                3, 3,
                12, 12
        };

        assertEquals(0, Ex14.findSingle(a19));

        int[] a20 = {
                123, 123,
                65, 65,
                76, 76,
                99, 99,
                9, 9,
                -12, -12,
                7655, 7655,
                0, 0,
                1, 1,
                123, 123,
                65, 65,
                76, 76,
                99, 99,
                9, 9,
                -12, -12,
                7655, 7655,
                0, 0,
                1, 1,
                123, 123,
                65, 65,
                76, 76,
                99, 99,
                9, 9,
                -12, -12,
                7655, 7655,
                0, 0,
                1, 1,
                123, 123,
                65, 65,
                76, 76,
                99, 99,
                9, 9,
                -12, -12,
                7655, 7655,
                0, 0,
                1, 1,
                123, 123,
                65, 65,
                76, 76,
                99, 99,
                9, 9,
                -12, -12,
                7655, 7655,
                0, 0,
                1, 1,
                123, 123,
                65, 65,
                76, 76,
                99, 99,
                9, 9,
                -12, -12,
                7655, 7655,
                0, 0,
                1, 1,
                0,
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                8, 8,
                4, 4,
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                5, 5,
                1, 1,
                0, 0,
                1, 1,
                0, 0,
                3, 3,
                12, 12,
                123, 123,
                65, 65,
                76, 76,
                99, 99,
                9, 9,
                -12, -12,
                7655, 7655,
                0, 0,
                1, 1,
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                8, 8,
                4, 4,
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                5, 5,
                1, 1,
                0, 0,
                1, 1,
                0, 0,
                3, 3,
                12, 12,
                123, 123,
                65, 65,
                76, 76,
                99, 99,
                9, 9,
                -12, -12,
                7655, 7655,
                0, 0,
                1, 1,
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                8, 8,
                4, 4,
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                5, 5,
                1, 1,
                0, 0,
                1, 1,
                0, 0,
                3, 3,
                12, 12
        };

        assertEquals(0, Ex14.findSingle(a20));

        int[] a21 = {
                123, 123,
                65, 65,
                76, 76,
                99, 99,
                9, 9,
                -12, -12,
                7655, 7655,
                0, 0,
                1, 1,
                123, 123,
                65, 65,
                76, 76,
                99, 99,
                9, 9,
                -12, -12,
                7655, 7655,
                0, 0,
                1, 1,
                123, 123,
                65, 65,
                76, 76,
                99, 99,
                9, 9,
                -12, -12,
                7655, 7655,
                0, 0,
                1, 1,
                123, 123,
                65, 65,
                76, 76,
                99, 99,
                9, 9,
                -12, -12,
                7655, 7655,
                0, 0,
                1, 1,
                123, 123,
                65, 65,
                76, 76,
                99, 99,
                9, 9,
                -12, -12,
                7655, 7655,
                0, 0,
                1, 1,
                123, 123,
                65, 65,
                76, 76,
                99, 99,
                9, 9,
                -12, -12,
                7655, 7655,
                0, 0,
                1, 1,
                0, 0,
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                8, 8,
                4, 4,
                8, 8,
                -7, -7,
                            3,
                0, 0,
                10, 10,
                5, 5,
                1, 1,
                0, 0,
                1, 1,
                0, 0,
                3, 3,
                12, 12,
                123, 123,
                65, 65,
                76, 76,
                99, 99,
                9, 9,
                -12, -12,
                7655, 7655,
                0, 0,
                1, 1,
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                8, 8,
                4, 4,
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                5, 5,
                1, 1,
                0, 0,
                1, 1,
                0, 0,
                3, 3,
                12, 12,
                123, 123,
                65, 65,
                76, 76,
                99, 99,
                9, 9,
                -12, -12,
                7655, 7655,
                0, 0,
                1, 1,
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                8, 8,
                4, 4,
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                5, 5,
                1, 1,
                0, 0,
                1, 1,
                0, 0,
                3, 3,
                12, 12,
                0, 0,
                10, 10,
                8, 8,
                4, 4,
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                5, 5,
                1, 1,
                0, 0,
                1, 1,
                0, 0,
                3, 3,
                12, 12,
                0, 0,
                10, 10,
                8, 8,
                4, 4,
                8, 8,
                -7, -7,
                3, 3,
                0, 0,
                10, 10,
                5, 5,
                1, 1,
                0, 0,
                1, 1,
                0, 0,
                3, 3,
                12, 12
        };

        assertEquals(3, Ex14.findSingle(a21));

        int[] a22 = {
                1, 1,
                -23, -23,
                1, 1,
                4, 4,
                1, 1,
                1, 1,
                        0,
                1, 1,
                4, 4
        };

        assertEquals(0, Ex14.findSingle(a22));

        int[] a23 = {
                8, 8,
                1, 1,
                3, 3,
                5, 5,
                        -23,
                0, 0,
                2, 2
        };

        assertEquals(-23, Ex14.findSingle(a23));

        int[] a24 = {
                8, 8,
                1, 1,
                3, 3,
                5, 5,
                9, 9,
                100, 100,
                23, 23,
                        -23,
                0, 0,
                2, 2
        };

        assertEquals(-23, Ex14.findSingle(a24));

        int[] a25 = {
                8, 8,
                1, 1,
                3, 3,
                5, 5,
                -23,
                0, 0
        };

        assertEquals(-23, Ex14.findSingle(a25));

        int[] a26 = {
                8, 8,
                1, 1,
                3, 3,
                5, 5,
                        -23
        };

        assertEquals(-23, Ex14.findSingle(a26));

        int[] a27 = {
                        -23,
                8, 8,
                1, 1,
                0, 0,
                2, 2
        };

        assertEquals(-23, Ex14.findSingle(a27));

        int[] a28 = {
                1, 1,
                8, 8,
                2, 2,
                8, 8,
                1, 1,
                1, 1,
                0, 0,
                -23,
                0, 0,
                2, 2
        };

        assertEquals(-23, Ex14.findSingle(a28));
    }

    @Test
    void smallestSubSum() {
        int[] arr1 = {8, 1, 4, 45, 6, 0, 19};
        assertEquals(3, Ex14.smallestSubSum(arr1, 51));

        int[] arr2 = {8, 1, 4, 45, 6, 0, 19};
        assertEquals(2, Ex14.smallestSubSum(arr2, 49));

        int[] arr3 = {8, 1, 4, 45, 6, 0, 19};
        assertEquals(1, Ex14.smallestSubSum(arr3, 20));

        int[] arr4 = {100, 100, 100, 600, 0, 100, 8, 1, 4, 45, 6, 0, 19, 500, 200, 30, 400};
        assertEquals(4, Ex14.smallestSubSum(arr4, 1000));

        int[] arr5 = {100, 100, 100, 600, 0, 100, 8, 1, 4, 45, 6, 0, 19, 500, 200, 30, 400};
        assertEquals(-1, Ex14.smallestSubSum(arr5, 10050));

        int[] arr6 = {100, 100, 100, 600, 0, 101, 8, 1, 4, 45, 6, 0, 19, 500, 200, 30, 40};
        assertEquals(6, Ex14.smallestSubSum(arr6, 1000));

        int[] arr7 = {100, 100, 100, 600, 0, 100, 0, 1, 4, 45, 6, 0, 19, 500, 200, 30, 40};
        assertEquals(8, Ex14.smallestSubSum(arr7, 1000));

        int[] arr8 = {100, 100, 100, 600, 0, 100, 0, 1, 999, 45, 6, 0, 19, 500, 200, 30, 40};
        assertEquals(2, Ex14.smallestSubSum(arr8, 1000));

        int[] arr9 = {100, 100, 100, 600, 0, 1000, 0, 1, 4, 45, 6, 0, 19, 500, 200, 30, 40};
        assertEquals(3, Ex14.smallestSubSum(arr9, 1000));

        int[] arr10 = {1000, 1000, 1000, 600, 401, 1000, 0, 1, 4, 45, 6, 0, 19, 500, 200, 30, 40, 1001};
        assertEquals(1, Ex14.smallestSubSum(arr10, 1000));
    }

    @Test
    void solutions() {


        assertEquals(6, Ex14.solutions(5));
        System.out.println("----------Test for 5 Ended------------");
        assertEquals(0, Ex14.solutions(2));
        System.out.println("----------Test for 2 Ended------------");
        assertEquals(0, Ex14.solutions(0));
        System.out.println("----------Test for 0 Ended------------");
        assertEquals(0, Ex14.solutions(31));
        System.out.println("----------Test for 31 Ended------------");
        assertEquals(0, Ex14.solutions(-4));
        System.out.println("----------Test for -4 Ended------------");
        assertEquals(0, Ex14.solutions(98));
        System.out.println("----------Test for 98 Ended------------");
        assertEquals(1, Ex14.solutions(3));
        System.out.println("----------Test for 3 Ended------------");
        assertEquals(10, Ex14.solutions(6));
        System.out.println("----------Test for 6 Ended------------");
        assertEquals(3, Ex14.solutions(4));
        System.out.println("----------Test for 4 Ended------------");
        assertEquals(15, Ex14.solutions(7));
        System.out.println("----------Test for 7 Ended------------");
        assertEquals(21, Ex14.solutions(8));
        System.out.println("----------Test for 8 Ended------------");
        assertEquals(28, Ex14.solutions(9));
        System.out.println("----------Test for 9 Ended------------");
        assertEquals(36, Ex14.solutions(10));
        System.out.println("----------Test for 10 Ended------------");
        assertEquals(45, Ex14.solutions(11));
        System.out.println("----------Test for 11 Ended------------");
        assertEquals(55, Ex14.solutions(12));
        System.out.println("----------Test for 12 Ended------------");
        assertEquals(63, Ex14.solutions(13));
        System.out.println("----------TestS for 13 Ended------------");
        assertEquals(69, Ex14.solutions(14));
        System.out.println("----------Test for 14 Ended------------");
        assertEquals(73, Ex14.solutions(15));
        System.out.println("----------Test for 15 Ended------------");
        assertEquals(63, Ex14.solutions(20));
        System.out.println("----------Test for 20 Ended------------");
        assertEquals(55, Ex14.solutions(21));
        System.out.println("----------Test for 21 Ended------------");
        assertEquals(3, Ex14.solutions(29));
        System.out.println("----------Test for 29 Ended------------");
        assertEquals(1, Ex14.solutions(30));
        System.out.println("----------Test for 30 Ended------------");
        System.out.println(Ex14.solutions(18));
    }

    @Test
    void cntTrueReg() {
        boolean[][] mat1 = {
                { false, false, false, false, true},
                { false, true, true, true, false},
                { false, false, true, true, false},
                {true, false, false, false, false},
                {true, true, false, false, false}
        };
        assertEquals(3, Ex14.cntTrueReg(mat1));

        boolean[][] mat2 = {
                { false, false, false, false, false},
                { false, true, true, true, false},
                { false, false, true, true, false},
                {true, false, false, false, false},
                {true, true, false, false, false}
        };
        assertEquals(2, Ex14.cntTrueReg(mat2));

        boolean[][] mat3 = {
                { true,  true,  true,  false, true},
                { false, true,  true,  true,  false},
                { false, false, true,  true,  false},
                { true,  false, false, false, false},
                { true,  true,  false, false, false}
        };
        assertEquals(3, Ex14.cntTrueReg(mat3));

        boolean[][] mat4 = {
                { true }
        };
        assertEquals(1, Ex14.cntTrueReg(mat4));

        boolean[][] mat5 = {
                { false }
        };
        assertEquals(0, Ex14.cntTrueReg(mat5));

        boolean[][] mat6 = {
                { true,  false,  true,  false,  true},
                { true,  false,  true,  false,  true},
                { true,  false,  true,  false,  true},
                { true,  false,  true,  false,  true},
                { true,  false,  true,  false,  true}
        };
        assertEquals(3, Ex14.cntTrueReg(mat6));

        boolean[][] mat7 = {
                { true,  false,  true,  false,  true},
                { true,  false,  true,  false,  true},
                { true,  true,  true,  true,  true},
                { true,  false,  true,  false,  true},
                { true,  false,  true,  false,  true}
        };
        assertEquals(1, Ex14.cntTrueReg(mat7));

        boolean[][] mat8 = {
                { true,  false,  true,  false,  true},
                { true,  false,  true,  true,  true},
                { true,  true,  true,  false,  true},
                { true,  false,  true,  false,  true},
                { true,  false,  true,  false,  true}
        };
        assertEquals(1, Ex14.cntTrueReg(mat8));
    }

    @Test
    void UniTeachersTest() {
        Ex14StudentTester.main();
    }

    // Aviv tester from github:


    // question 1
    static int[] a = new int[] {6, 6, 18, 18, -4, -4, 12, 9, 9};
    static int[] b = new int[] {8, 8, -7, -7, 3, 3, 0, 0, 10, 10, 5, 5, 4};
    static int[] c = new int[] {5};
    static int[] anotherOne = new int[] {4, 8, 8};
    // question 2

    static int[] d = new int[] { 1, 4, 45, 6, 0, 19, 12, 23, 42, 32, 11, 23};
    static int dx = 51;

    static int[] e = new int[] { 1, 4, 6};
    static int ex = 200;

    static int[] f = new int[] { 7, 2, 5, 10, 1};
    static int fx = 9;

    static int[] g = new int[] {};
    static int gx = 0;

    static int[] h = new int[] {4, 2, 9, 21};
    static int hx = 6;

    static int[] i = new int[] {12, 122, 99, 201};
    static int ix = 301;

    static int[] j = new int[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    static int jx = 54;

    // question 3
    String[] so1 = {"1 + 1 + 3", "1 + 2 + 2", "1 + 3 + 1", "2 + 1 + 2", "2 + 2 + 1", "3 + 1 + 1"};
    String[] so2 = {"1 + 1 + 1"};
    String[] so4 = {};
    String[] so5 = {};

    // question 4
    boolean[][] mat1 =
            {
                    { false, false, false, false, true },
                    { false, true, true, true, false },
                    { false, false, true, true, false },
                    { true, false, false, false, false },
                    { true, true, false, false, false },
            };
    // 3
    boolean [][] mat2 =  {
            { false, false, false, false, false },
            { false, true, true, true, true },
            { false, true, true, false, false },
            { true, false, false, false, false },
            { true, true, false, false, false },
    };
    // 2
    boolean [][] mat3 =  {
            { false, false, false, false, false },
            { false, false, false, false, false },
            { false, false, false, false, false },
            { false, false, false, false, false },
            { false, false, false, false, false },
    };
    // 0
    boolean [][] mat4 =  {
            { true, false, false, false, true },
            { false, true, true, true, false },
            { false, true, true, false, false },
            { true, false, false, false, false },
            { true, true, false, false, true },
    };
    //5

    boolean [][] mat5 =  {
            { true, false, false, false, true },
            { false, true, true, true, false },
            { false, true, true, false, true },
            { true, false, true, true, false },
            { true, true, false, false, true },
    };
    // 6

    boolean [][] mat6 =  {
            { true, false, false, false, false },
            { false, false, false, false, false },
            { false, true, true, true, false },
            { false, false, true, false, false },
            { false, false, false, false, true },
    }; // 3

    boolean [][] mat7 =  {
            { false, false, false, false, false },
            { false, true, true, true, false },
            { false, false, true, false, false },
            { false, false, false, false, false },
            { false, false, false, false, false },
    }; // 1

    boolean [][] mat8 =  {
            { true, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            { true, false, true, false, false, false, false, false, false, false, false, false, false, false, false, true, true },
            { false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, true },
            { true, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { true, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { true, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { true, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, true, false, false, false, false, false, false, false, false, false, true, false, false, false, false },
            { true, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { true, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, true, false, true, false, false, false, false, false, false, false, false, false, true, false, false },
            { false, false, true, false, true, false, false, false, false, false, false, false, false, false, false, false, false },
    };

    boolean [][] mat9 = {
            {true,  false, false, false, true,  true},
            {true,  false, false, false, false, true},
            {true,  false, true,  true,  true,  true},
            {true,  true,  true,  true,  true,  true},
            {false, false, false, false, false, true},
            {true,  false, false, false, true,  true},
    };

    boolean [][] mat10 = {
            {true,  false, false, false, true,  true},
            {true,  false, false, false, false, true},
            {true,  false, true,  true,  true,  true},
            {true,  true,  true,  false, false, true},
            {false, false, true,  false, false, true},
            {true,  false, true,  true,  true,  true},
    };

    private ByteArrayOutputStream baos;
    private PrintStream outBackup;


    private void redirectOutput()
    {
        // Create a stream to hold the output
        baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        // IMPORTANT: Save the old System.out!
        if (outBackup == null) outBackup = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);
    }

    private String readOutput()
    {
        if (baos == null) return null;

        var output = baos.toString(); // Read
        System.out.flush(); // Clear
        redirectOutput(); // Recreate stream (making sure it's cleared)
        return output;
    }

    private void restoreOutput()
    {
        System.out.flush();
        System.setOut(outBackup);
    }

    private int contains(String str, String[] arr)
    {
        int count = 0;
        for (String s : arr) if (str.replaceAll(" ", "").contains(s.replaceAll(" ", ""))) count++;
        return count;
    }

    @Test
    public void question1Tester() {
        assertEquals(Ex14.findSingle(a), 12);
        assertEquals(Ex14.findSingle(b), 4);
        assertEquals(Ex14.findSingle(c), 5);
        assertEquals(Ex14.findSingle(anotherOne), 4);
    }

    @Test
    public void question2Tester() {
        assertEquals(Ex14.smallestSubSum(d, dx), 2);
        assertEquals(Ex14.smallestSubSum(e, ex), -1);
        assertEquals(Ex14.smallestSubSum(f, fx), 1);
        assertEquals(Ex14.smallestSubSum(g, gx), -1);
        assertEquals(Ex14.smallestSubSum(h, hx), 1);
        assertEquals(Ex14.smallestSubSum(i, ix), 3);
        assertEquals(Ex14.smallestSubSum(j, jx), 10);
    }

    @Test
    public void question3Tester() {
        assertEquals(Ex14.solutions(5), 6);
        assertEquals(Ex14.solutions(3), 1);
        assertEquals(Ex14.solutions(32), 0);
        assertEquals(Ex14.solutions(2), 0);

        String o1,o2,o3,o4,o5;

        // Check outputs
        try {
            redirectOutput();

            Ex14.solutions(5);
            o1 = readOutput();

            Ex14.solutions(3);
            o2 = readOutput();

            Ex14.solutions(10);
            o3 = readOutput();

            Ex14.solutions(32);
            o4 = readOutput();

            Ex14.solutions(2);
            o5 = readOutput();

            restoreOutput();

            var cccc = contains(o1, so1);
            if (contains(o1, so1) < so1.length) // missing solution(s)
                assertEquals(String.join("\r\n", so1), o1);
            if (contains(o2, so2) < so2.length) assertEquals(String.join("\r\n", so2), o2);
            if (contains(o4, so4) < so4.length) assertEquals(String.join("\r\n", so4), o4);
            if (contains(o4, so4) < so4.length) assertEquals(String.join("\r\n", so5), o5);

        } catch (Exception e) {
            System.out.println("Failed to check question 3 outputs.");
        }
    }

    @Test
    public void question4Tester() {
        assertEquals(Ex14.cntTrueReg(mat1), 3);
        assertEquals(Ex14.cntTrueReg(mat2), 2);
        assertEquals(Ex14.cntTrueReg(mat3), 0);
        assertEquals(Ex14.cntTrueReg(mat4), 5);
        assertEquals(Ex14.cntTrueReg(mat5), 6);
        assertEquals(Ex14.cntTrueReg(mat6), 3);
        assertEquals(Ex14.cntTrueReg(mat7), 1);
        assertEquals(Ex14.cntTrueReg(mat8), 13);
        assertEquals(Ex14.cntTrueReg(mat9), 2);
        assertEquals(Ex14.cntTrueReg(mat10), 2);
    }
}