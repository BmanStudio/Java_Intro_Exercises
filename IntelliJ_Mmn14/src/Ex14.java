/**
 * Holds all the answers for Mmn 14, 2021a
 * @author Ori Ben Nun
 * @version 26/12/2020
 */
public class Ex14 {

    // Question 1:

    /**
     * This method takes an appropriate array of integers,
     * which each number occurs twice in a row but only 1 number which occurs only once ("orphan"),
     * and returns that one number (not it's index!)
     * The runtime complexity of the method is big O of log base 2 of N => O(logN), because we are using a binary search algorithm // TODO explain more about the complexity?
     * The space complexity is, of course, O(1)
     * @param a An appropriate array of integers.
     * @return The "orphan" number which occurs only once in the array provided (not it's index!).
     */
    public static int findSingle (int [] a) {

        // edge case: only 1 element in the array => this is the number to be returned
        if (a.length == 1) {
            return a[0];
        }

        // the pointers for the binary search:
        int start = 0; // the index of the first element
        int end = a.length - 1; // the index of the last element
        int mid; // will be overridden in the while loop

        while (start <= end) {

            // if start == end, means one of them is the orphan
            if (start == end) {
                return a[start];
            }

            // sets the mid pointer to be halfway between start and end
            mid = start + ((end - start) / 2);

            // in the edge case where there is only 3 elements, mid will start at 1 without the check for mod 2:
            if (mid != 1) {
                // logically, the orphan must be in an even index (including 0), and the end will be even as well
                // so in order to make sure the mid will always look at an even index, will check for mod 2
                // if mid is on odd index, decrease by 1 (so it will be even)
                if (mid % 2 != 0) {
                    mid--;
                }
            }

            // if the mid block isn't equal to the previous block, means there are even number of blocks on the left,
            // means the orphan cannot be there (because they're all in pairs)
            // means the orphan has to be on the right side of mid, or it is mid itself
            if (a[mid] != a[mid - 1]) {
                // check if it the orphan itself
                if (a[mid] != a[mid + 1]) {
                    return a[mid];
                }
                // if it is equal to the right one, we want to look only on the right side of mid
                else {
                    // if mid not equals left and do equals right, and mid is just between start and end, means start is the orphan
                    if (start == mid - 1) {
                        return a[start];
                    }
                    // we will set start to be mid + 2 (because we already know that a[mid] == a[mid + 1] so there is a pair)
                    start = mid + 2;
                }
            }
            // same goes for the other side, analogically
            // if mid is equal to the left block
            else {
                // TODO edge case of equals to both sides (example - [1,1,1,1,0,1,1,1,1,1,1])
                // if mid equals left, and mid is just between start and end, means end is the orphan
                if (end == mid + 1) {
                    return a[end];
                }
                end = mid - 2;
            }
        }
        // if got here, means something wrong, and returns Int.MinValue as indicator
        return Integer.MIN_VALUE;
    }

    // Question 2:

    // TODO add API with complexity
    // TODO change to pure O(N) from the geekforgeek website solution
    public static int smallestSubSum(int arr[], int x) {
        int subArrMinLength = Integer.MAX_VALUE;
        int subArrStartIndex = 0;
        //int subArrSum = 0;

        int currentLength = 0;
        int currentSum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > x) {
                return 1;
            }
            else {
                currentSum += arr[i];
                currentLength++;
                while (currentSum > x) {
                    if ( currentLength < subArrMinLength) {
                        subArrMinLength = currentLength;
                    }
                    currentSum -= arr[subArrStartIndex];
                    subArrStartIndex++;
                    currentLength--;
                }
            }
        }

        if (subArrMinLength == Integer.MAX_VALUE) {
            return -1;
        }
        return subArrMinLength;
    }

    // Question 3:

    // TODO remove!!
    public static int temp = 0;
    // TODO write api
    public static int solutions(int num) {
        temp = 0;
        if (num < 3 || num > 30) {
            return 0;
        }

        return solutions(num, 1, 1, 1);
    }

    // TODO check that everything is good with the >10 numbers, find a formula to check the answers, write some API and comments
    private static int solutions(int num, int x1, int x2, int x3) {
        if (x1 + x2 + x3 < num) {
            if (x3 == 10) {
                x2 += 1;
            }
            else {
                x3 += 1;
            }
            return solutions(num, x1, x2, x3);
        }
        else if (x1 + x2 + x3 == num) {
            temp++;
            System.out.println(x1 + " + " + x2 + " + " + x3);
            if (x3 == 1) {
                if (x2 == 1) {
                    return 1;
                }
                else {
                    x2 = 1;
                    x1 += 1;
                    if (x1 > 10) {
                        System.out.println("temp - " + temp);
                        return 1;
                    }
                    return 1 + solutions(num, x1, x2, x3);
                }
            }
            else {
                x3 -= 1;
                // TODO fix the bug here:
                if (x2 == 10) {
                    x1 += 1;
                    x2 -= 1;
                }
                else {
                    x2 += 1;
                }
                return 1 + solutions(num, x1, x2, x3);
            }
        }
        return 0;
    }

    public static int solutionsS(int num, int x1, int x2, int x3) {
        if (x1 + x2 + x3 < num) {
                x3 += 1;

            return solutionsS(num, x1, x2, x3);
        }
        else if (x1 + x2 + x3 == num) {
            temp++;
            System.out.println(x1 + " + " + x2 + " + " + x3);
            if (x3 == 1) {
                if (x2 == 1) {
                    System.out.println("temp - " + temp);
                    return 1;
                }
                else {
                    x2 = 1;
                    x1 += 1;
                    return 1 + solutionsS(num, x1, x2, x3);
                }
            }
            else {
                x3 -= 1;
                    x2 += 1;

                return 1 + solutionsS(num, x1, x2, x3);
            }
        }
        return 0;
    }
}
