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
                0, 0,
                0, 0,
                0, 0,
                0, 0,
                1,
                0, 0,
                0, 0,
                0, 0
        };

        // TODO check if there is need for this edge case
        //assertEquals(1, Ex14.findSingle(a10));

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
                12, 12
        };

        assertEquals(0, Ex14.findSingle(a19));
    }
}