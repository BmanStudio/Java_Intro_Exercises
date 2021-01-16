/**
 * Mmn 15:
 *
 * @author Ori Ben Nun
 * @version 14/01/2021
 */
public class BigNumber {

    private IntNode _head; // Stores the rightmost digit of the number (single unit).

// Constructors:

    /**
     * The empty (default) constructor.
     * Initializing a new BigNumber linked-list which represents the number 0.
     * Time and Space complexity are O(1) always.
     */
    public BigNumber () {
        _head = new IntNode(0);
    }

//    /**
//     * Initializing a new BigNumber linked-list which represents the passed number.
//     * Time complexity - the outer while loop is O(1), and inside the loop we are using the a private method in order
//     *      to add elements to the end of the list, which is O(1) as well - because we know this is a new list, so there cannot be more than 19 digits at this point (the max number of digits in a long typed number in Java)
//     *      Therefore - the time complexity is O(1) * O(1) = O(1).
//     * Space complexity - we are only using local variables, which means O(1).
//     * @param number The non-negative number which will be represented by the BigNumber linked-list.
//     */
//    public BigNumber (long number) {
//        // The idea is to loop through the passed number, and remove the rightmost digit
//        // (the single unit) from the number, while adding it to the end of the BigNumber list,
//        // means that the number of iterations is the number of digits of the given number.
//        // So for the worst case, this while loop will run 19 times (as for the max number of digits in a single long type number in Java)
//        // Therefore - the loop time complexity is O(1).
//        while (number > 0) { // We are dividing the number by 10 each time, so eventually will get to 0
//            System.out.println(number % 10);
//            long temp = number; // taking a copy of the number, so we can check what was the removed digit
//            number = number / 10; // removing the last digit
//            // getting the removed digit, which is the rightmost of the current number, and casting to int to save space (always storing only 1 digit).
//            // we getting the digit by multiplying the number by 10 ("reversing the division"), and because this is an whole number (integer),
//            // the difference between the copy and this one will be exactly the value of the removed digit.
//            int rightDigit = (int) (temp - (number * 10));
//            IntNode newTail = new IntNode(rightDigit);
//            // assigning the digit as the new tail of the list, because we are storing the "reversed" version of the number, so the rightmost digit of the original number
//            // will be the head of the list, while the leftmost is the tail.
//            addToEnd(newTail); // The time complexity of this method is O(N)
//        }
//    }

    /**
     * // TODO finish API and add comments
     * Initializing a new BigNumber linked-list which represents the passed number.
     * Time complexity - because the max digits a
     * Space complexity - we are only using local variables, which means O(1).
     * @param number The non-negative number which will be represented by the BigNumber linked-list.
     */
    public BigNumber(long number) {

        IntNode currTail = _head;

        while (number > 0) {

            int rightDigit = (int) (number % 10);
            IntNode newNode = new IntNode(rightDigit);
            if (currTail == null) {
                _head = newNode;
                currTail = _head;
            }
            else {
                currTail.setNext(newNode);
                currTail = newNode;
            }
            number /= 10;
        }
    }

    /**
     * The copy constructor of the BigNumber linked-list class, which copies another BigNumber.
     * Time complexity - We are iterating through the passed BigNumber list from head to tail, and copying each node to the new BigNumber.
     *      means - the time complexity is O(N).
     * Space complexity - we are only storing references of individual nodes each time,
     *      means - the space complexity is O(1).
     * @param bigNumber The reference BigNumber list to copy from.
     */
    public BigNumber (BigNumber bigNumber) {
        IntNode refNode = bigNumber._head; // The current digit to the copied from the original BigNumber.
        this._head = new IntNode(refNode.getValue());
        IntNode myNode = _head; // The current node that has been added, we keep track of it in order to set the _next node each time.
        refNode = refNode.getNext(); // Moving to the next ref node, and entering the loop if such exists.
        while (refNode != null) {
            // For each reference node, we are creating our own new node which holds the value (the digit) of the original BigNumber digit on the same "index",
            // and setting it as the _next node of the previous one we added.
            IntNode next = new IntNode(refNode.getValue());
            myNode.setNext(next);
            myNode = myNode.getNext();
            refNode = refNode.getNext(); // and finally moving to the next one, until we get to the end of the original list.
        }
    }

// Public methods:

    /**
     * This Recursive method returns a string which contains the "normal form" of the number that the BigNumber list is
     * representing.
     * Time complexity - We are iterating once through all of the nodes in the list before folding back the recursion,
     *      means - the time complexity is O(N).
     * Space complexity - O(1) (not including the recursion stack memory) - since we are not storing any variables.
     * @return The number represented by BigNumber as a string, in its "normal form".
     */
    // This is the public "main" recursion method, which calls the private overloaded version
    // which contains the algorithm logic
    public String toString() {
        return toString(_head);
    }

    /**
     * This method compares between the values represented by two BigNumbers, related to the one that the method was called from.
     * For example:if the value which is represented by this BigNumber (the called object) is smaller than the passed one, well will call is "smaller than", and the method
     * will return -1.
     * Time complexity -
     * Space complexity -
     * @param other The reference BigNumber object, which it's value will be compared to this BigNumber's value.
     * @return
     */
    public int compareTo (BigNumber other) {
        // If this bignumber has more digits than other - means its the bigger one.
        if (this.length() > other.length()) {
            return 1;
        }
        else if (this.length() < other.length()) {
            return -1;
        }

        // TODO finish, API and comments
        // if has the same length:
        // flip the numbers and compare
        return 0;
    }

    public BigNumber addBigNumber (BigNumber other) {

        IntNode resultHead = null;
        IntNode resultCurrTail = null;

        IntNode myCurrent = _head;
        IntNode otherCurrent = other._head;
        int reminder = 0; //TODO change name

        while (myCurrent != null | otherCurrent != null) {
            int myCurrentValue;
            int otherCurrentValue;

            if (myCurrent == null) {
                myCurrentValue = 0;
            }
            else {
                myCurrentValue = myCurrent.getValue();
                myCurrent = myCurrent.getNext();
            }

            if (otherCurrent == null) {
                otherCurrentValue = 0;
            }
            else {
                otherCurrentValue = otherCurrent.getValue();
                otherCurrent = otherCurrent.getNext();
            }

            int resultNewValue = reminder + myCurrentValue + otherCurrentValue;

            if (resultNewValue > 9) {
                reminder = 1;
                resultNewValue = resultNewValue % 10;
            }
            else {
                reminder = 0;
            }

            IntNode resultNewDigit = new IntNode(resultNewValue);

            if (resultHead == null) {
                resultHead = resultNewDigit;
                resultCurrTail = resultHead;
            }
            else {
                resultCurrTail.setNext(resultNewDigit);
                resultCurrTail = resultNewDigit;
            }
        }

        if (reminder == 1) {
            resultCurrTail.setNext(new IntNode(1));
        }
        BigNumber resultNumber = new BigNumber();

        resultNumber._head.setValue(resultHead.getValue());
        resultNumber._head.setNext(resultHead.getNext());
        return resultNumber;
    }


// Private methods:

    // The helper method of the public toString recursive method.
    // The idea - iterating through all the digits until we get to the last one, which returns an empty string (so the string concatenation will work)
    // and then while folding the recursion, each node adding itself after the digits that
    // located After it (because the BigNumber is stored as a "reversed" version of the number)
    private String toString(IntNode node) {
        if (node == null) {
            return "";
        }
        return toString(node.getNext()) + node.getValue();
    }

    // Taken from the Uni's presentation:

    /**
     * Taken from the IntList class, made by the Uni.
     * @param node The node to be added as the head of the list
     */
    private void addToHead (IntNode node) {
        IntNode temp = _head;
        _head = node;
        node.setNext(temp);
    }

    /**
     * Taken from the IntList class, made by the Uni.
     * @return True if the list is empty, false if not.
     */
    private boolean empty () {
        return _head == null;
    }

    /**
     * Taken from the IntList class, made by the Uni.
     * @param node The reference node
     * @return The following node if exist, null if isn't.
     */
    private IntNode nextElement (IntNode node) {
        if (node.getNext() == null) { return null; }
        return node.getNext();
    }

    /**
     * Taken from the IntList class, made by the Uni.
     */
    private int getValueOfNode (IntNode node) {
        return node.getValue();
    }

    /**
     * Taken from the IntList class, made by the Uni.
     * ATTENTION - adding complexity of O(N)!
     */
    private void addToEnd (IntNode node) {
        if (empty() ) {
            _head = node;
        }
        else {
            IntNode ptr = _head;
            while (ptr.getNext() != null) {
                ptr = ptr.getNext();
            }
            ptr.setNext(node);
        }
    }

    /**
     * Taken from the IntList class, made by the Uni.
     * ATTENTION - adding complexity of O(N)!
     */
    private int length () {
        IntNode temp = _head;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.getNext();
        }
        return count;
    }

    /**
     * Taken from the IntList class, made by the Uni.
     * ATTENTION - adding complexity of O(N)!
     */
    private void printList() {
        IntNode temp = _head;
        while (temp != null) {
            System.out.print(temp.getValue() + "-->");
            temp = temp.getNext();
        }
        System.out.println(" null");
    }
}