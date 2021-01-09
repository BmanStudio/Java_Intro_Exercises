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
     * Time complexity:
     *  O(logN) - The runtime complexity of the method is big O of log base 2 of N, because we are using a method based on binary search algorithm,
     *  because we are "inspecting" the middle element in the array, and each time "narrowing" the array by half, and repeating the process until we found the single number
     *  or we got to a 1 element's array.
     * Space complexity
     *  O(1) - because we are just storing 3 integers variables.
     * @param a An appropriate array of integers.
     * @return The "orphan" number which occurs only once in the array provided (not it's index!).
     */
    public static int findSingle (int[] a) {

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

    /**
     * This method takes an array of positive integers and a number (positive integer),
     * and returns the size of the shortest
     * subarray which sums to more than the given number, or returns -1 in cases which this condition is not met.
     * Time complexity:
     *  O(N) - there is one iteration over all the elements of the array, which is O(N).
     *      Within the for loop we are "adding" elements to a temporary subarray which is
     *      being checked every time (after the element is being added).
     *      once we found a subarray which sums to more than the given number - we will
     *      enter a while loop, but LETS PAY ATTENTION - within the while loop we are "removing" elements from the temporary subarray.
     *      So - the maximum iterations of the inner while loop is the size of the subarray, which can only get as big as N (which will happen ONLY on the last iteration).
     *      Because the while loop removes elements from the subarray, there cannot be more than 1 subarray with a size of N.
     *      We conclude that, for the worst case, the inner "while loop" will run A TOTAL of N times during the whole "for loop" iteration
     *      (because IN THE WORST CASE there is only N elements we can add in total, means we have N elements to remove in total)
     *      SO, in total - there is O(N) + O(N) == O(2N) == O(N)
     * Space complexity:
     *  O(1) - We are storing only 4 integers variables (not "really" creating the subarray, but looking at a "window" of elements - defined by start and end indices).
     * @param arr The array of positive integers.
     * @param x The target number.
     * @return The size of the shortest subarray, or -1 in case there is no subarray which meet the condition.
     */
    // TODO add commenting
    public static int smallestSubSum(int[] arr, int x) {
        // This variable is being overridden every time we find the min size subarray (by comparing)
        int subArrMinLength = Integer.MAX_VALUE;
        int subArrStartIndex = 0;

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

    /**
     * This Recursive method takes an int number and finds all the possible combinations of
     * three numbers between 1-10 which sums to the passed number, while printing every possible combination
     * once it was found.
     * @param num The number which we inspect to find all the groups of three number that sums to it
     * @return The number of different combinations of groups of three numbers that sums to the passed number
     */
    // This is the "main" method, which calls to an helper recursion overloaded method, which contains all the algorithm logic
    public static int solutions(int num) {
        if (num < 3 || num > 30) {
            return 0;
        }
        // calling to the overloaded recursive method which takes the num and initialise the iteration
        // with the base case of x1,x2,x3 = 1
        return solutions(num, 1, 1, 1);
    }

    /**
     * Overloaded method of the public solutions method.
     * This method holds the logic for the algorithm.
     * The main idea is to begin with all the possible combinations which starts with x1 = 1, by first increasing x3 until it gets to 10
     * and then repeat the process while increasing x2 and decreasing x3, and after that we repeat the all process again after increasing x1.
     * We will repeat this loop until we get to the point where x1 == num - 2, (means x2,x3 = 1), and then we know this is the last possible combination, and finally returning the final answer.
     * @param num The original num passed from the public method, which stays the same during the all recursion
     * @param x1 The first number
     * @param x2 The second number
     * @param x3 The third number
     * @return The sum of combinations, and the last loop will return to the public method the final answer
     */
    private static int solutions(int num, int x1, int x2, int x3) {
        // while the sum below num, we want to check that every number is still below 10, and increasing each one of them
        // by the order of:
        // x3 first
        // x2 second
        // x1 last
        // and we will keep the recursion loop with the modified numbers until we find the combination which sums to num
        if (x1 + x2 + x3 < num) {
            if (x3 == 10) {
                if (x2 < 10){
                    x2 += 1;
                }
                else {
                    // Recursion stop condition below:
                    if (x1 < 10) {
                        x1 += 1;
                    }
                    // if x1 == 10, while x2,x3 == 10 too, means num was 30 and this is the only legal combination, means we should return 1 and fold the recursion back.
                    else {
                        return 1;
                    }
                }
            }
            else {
                x3 += 1;
            }
            return solutions(num, x1, x2, x3);
        }
        else if (x1 + x2 + x3 == num) {
            System.out.println(x1 + " + " + x2 + " + " + x3);
            // if x3 == 1, means (in all cases but 1 + 1 + 1 = 3) that we already added the combinations of the current x1,x2 values, and decreased x3 back to 1, so we need to reset x2
            if (x3 == 1) {
                // Recursion stop condition below:
                if (x2 == 1) {
                    // We found the last combination
                    // if both x2,x3 == 1, means x1 == num - 2, which means we cannot increase x1 anymore, and we can return 1 to fold the recursion back
                    return 1;
                }
                // If x2 > 1, means we found the biggest value x2 can be with the current x1, means we can reset x2 and move to the next x1
                else {
                    x2 = 1;
                    x1 += 1;
                    // Recursion stop condition below:
                    if (x1 > 10) {
                        // We found the last combination
                        // We looped (if was possible) through all of the combinations of x1 == 10, therefore we just found the last combination before x1 becomes greater than 10.
                        // So we found the last legal combination and can return 1 and fold the recursion back.
                        return 1;
                    }
                    // After some modification was made to the numbers, we will add 1 to the total sum and run the recursion check again with the new combination
                    return 1 + solutions(num, x1, x2, x3);
                }
            }
            // if x3 > 1, means we should start decreasing it by 1 and "add it back" to x2, which should now complete the sum back to num, so we actually found a "group" of answers
            else {
                x3 -= 1;
                if (x2 == 10) {
                    // Recursion stop condition below:
                    if (x1 < 10) {
                        // if x2 == 10 and x1 < 10, means we should move to the next x1, and reset x2 back to 1 for the next iteration
                        x1 += 1;
                        x2 = 1;
                    }
                    // if x1 == 10, means we got here after going through all the possible combinations where x1,x2 == 10, and therefore we actually found the last combination and we can return 1 and fold the recursion back
                    else {
                        // We found the last combination
                        return 1;
                    }
                }
                else {
                    x2 += 1;
                }
                // After some modification was made to the numbers, we will add 1 to the total sum and run the recursion check again with the new combination
                return 1 + solutions(num, x1, x2, x3);
            }
        }
        // The method shouldn't get to this point whatsoever, but this return expression is necessary for compilation
        return 0;
    }

    // Question 4:

    // TODO write API and comments
    public static int cntTrueReg (boolean[][]mat) {
        return cntTrueReg(mat, 0, 0);
    }

    private static int cntTrueReg (boolean[][]mat, int row, int col) {
        if (col == mat.length) {
            col = 0;
            row += 1;
        }
        if (row == mat.length) {
            return 0;
        }

        if (mat[row][col]) {
            flipRegion(mat, row, col);
            return 1 + cntTrueReg(mat, row, col + 1);
        }
        else {
            return cntTrueReg(mat, row, col + 1);
        }
    }

    private static void flipRegion(boolean[][] mat, int row, int col) {
        mat[row][col] = false;

        int left = row - 1;
        int right = row + 1;

        int up = col - 1;
        int down = col + 1;

        if (left >= 0) {
            if (mat[left][col]) {
                flipRegion(mat, left, col);
            }
        }

        if (right < mat.length) {
            if (mat[right][col]) {
                flipRegion(mat, right, col);
            }
        }

        if (up >= 0) {
            if (mat[row][up]) {
                flipRegion(mat, row, up);
            }
        }

        if (down < mat.length) {
            if (mat[row][down]) {
                flipRegion(mat, row, down);
            }
        }
    }
}
