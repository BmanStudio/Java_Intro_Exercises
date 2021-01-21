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

    /**
     * // TODO finish API and add comments and change names
     * Initializing a new BigNumber linked-list which represents the passed number.
     * Time complexity - because the max digits a
     * Space complexity - we are only using local variables, which means O(1).
     * @param number The non-negative number which will be represented by the BigNumber linked-list.
     */
    public BigNumber(long number) {

        IntNode currTail = null;

        if (number == 0) {
            IntNode newNode = new IntNode(0);
            _head = newNode;
            return;
        }

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
        IntNode myCurrNode = _head; // The current node that has been added, we keep track of it in order to set the _next node each time.
        refNode = refNode.getNext(); // Moving to the next ref node, and entering the loop if such exists.
        while (refNode != null) {
            // For each reference node, we are creating our own new node which holds the value (the digit) of the original BigNumber digit on the same "index",
            // and setting it as the _next node of the previous one we added.
            IntNode next = new IntNode(refNode.getValue());
            myCurrNode.setNext(next);
            myCurrNode = myCurrNode.getNext();
            refNode = refNode.getNext(); // and finally moving to the next one, until we get to the end of the original list.
        }
    }

// Public methods:

    /**
     * This Recursive method returns a string which contains the "normal form" of the number that the BigNumber list is
     * representing.
     * Time complexity - We are iterating once through all of the nodes in the list before folding back the recursion,
     *      means - the time complexity is O(N).
     * Space complexity - O(1) (not including the recursion stack memory) - since we are not storing any variables. // TODO describe the space complexity of the stack
     * @return The number represented by BigNumber as a string, in its "normal form".
     */
    // This is the public "main" recursion method, which calls the private overloaded version
    // which contains the algorithm logic
    public String toString() {
        return toString(_head);
    }

/*    *//**
     * // TODO check if we can traverse less times
     * This method compares between the values represented by two BigNumbers, related to the one that the method was called from.
     * For example: if the value which is represented by this BigNumber (the called object) is smaller than the passed one, well will call it is "smaller than", and the method
     * will return -1.
     * The idea - if one of the numbers has more digits than the other one - means its the bigger one.
     *  if the lengths are equal - we are creating new "flipped" versions of the numbers which represents the "normal form" of the numbers,
     *  which allows us to normally traverse through them one time and compare each digit at each "level" (node) for the two flipped lists.
     * Time complexity - O(N) - we are traversing the lists couple of times, by never as nested loops. // TODO write exactly how much iterations
     * Space complexity - O(N) - we are creating two BigNumber lists for the flipped versions as part of the algorithm.
     * @param other The reference BigNumber object, which it's value will be compared to this BigNumber's value.
     * @return 1 if this number is bigger, 0 if they are equal, -1 if other is bigger.
     *//*
    public int compareTo (BigNumber other) {
        int myLength = this.length();   // this check takes O(N)
        int otherLength = other.length(); // this check takes O(N)
        // If this bigNumber has more digits than other - means its the bigger one.
        if (myLength > otherLength) {
            return 1;
        }
        else if (myLength < otherLength) {
            return -1; // This bigNumber has less digits and therefore its the smaller one.
        }
        // if has the same length:
        // flip the numbers and compare
        else {
            // We are flipping the copies of the BigNumbers lists that is representing the numbers as its "normal forms", so the leftmost digit will represent
            // the "biggest 10^n multiplier" - means we can compare the numbers "normally" - comparing each digit from left to right.
            // Time complexity - the private method is taking O(N) and the copy constructor is O(N) as well - so total of O(N).
            // Space complexity added is O(N)
            // TODO instead of creating a copy - just flip it once again after the algorithm is done.
            BigNumber myFlippedNumber = flipBigNumber(new BigNumber(this));
            BigNumber otherFlippedNumber = flipBigNumber(new BigNumber(other));

            // traverse the two numbers until one of the digits is bigger than the other, which means its the bigger one.
            IntNode myFlippedCurr = myFlippedNumber._head;
            IntNode otherFlippedCurr = otherFlippedNumber._head;

            // this loop is O(N)
            while (myFlippedCurr != null | otherFlippedCurr != null) {
                // if my digits are over, means other got more digits - so other is bigger
                if (myFlippedCurr == null) {
                    return -1;
                }
                // If other's digits are over, my BigNumber is bigger
                else if (otherFlippedCurr == null) {
                    return 1;
                }
                // otherwise - we will compare the two current digits values
                if (myFlippedCurr.getValue() > otherFlippedCurr.getValue()) {
                    return 1;
                }
                else if (myFlippedCurr.getValue() < otherFlippedCurr.getValue()) {
                    return -1;
                }
                // else, means the two values are equal and we will move to the next digits
                else {
                    myFlippedCurr = myFlippedCurr.getNext();
                    otherFlippedCurr = otherFlippedCurr.getNext();
                }
            }
            // else - means the two numbers are completely equal, and we will return 0
            return 0;
        }
    }*/

    // TODO API and comments
    // this is 2 traverses - one down one up
    public int compareTo (BigNumber other) {
        return compareTo(_head, other._head);
    }

    private int compareTo(IntNode myCurrNode, IntNode otherCurrNode) {
        if (myCurrNode.getNext() == null) {
            if (otherCurrNode.getNext() == null) {
                // Starting to fold back the recursion and check the values
                return compareDigits(myCurrNode, otherCurrNode);
            }
            // Means my digits are done before other's, so im the smaller one
            else {
                return -1;
            }
        }
        else if (otherCurrNode.getNext() == null) {
            // Means i got nodes but other is done, so im bigger:
            return 1;
        }
        // Means we call still go down the recursion
        else {
            int tempResult  = compareTo(myCurrNode.getNext(), otherCurrNode.getNext());
            if ( tempResult == 0) {
                return compareDigits(myCurrNode, otherCurrNode);
            }
            else {
                return tempResult;
            }
        }
    }

    /**
     * // TODO add API and comments
     * this is only 1 traverse
     * @param other
     * @return
     */
    public BigNumber addBigNumber (BigNumber other) {

        IntNode resultHead = null;
        IntNode resultCurrTail = null;

        int reminder = 0;
        IntNode otherCurrent = other._head;
        IntNode myCurrent = _head;

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


    // TODO add API and comments
    public BigNumber addLong (long num) {

        IntNode newNumberHead = null;
        IntNode newNumberCurrNode = null;
        IntNode myCurrNode = _head;

        int reminder = 0;

        while (myCurrNode != null | num > 0) {

            // This will also handle one BigNumber still got digits but num is 0
            if (myCurrNode != null) {

                int newRightDigitValue = (int) (num % 10) + myCurrNode.getValue() + reminder;

                myCurrNode = myCurrNode.getNext();

                if (newRightDigitValue > 9) {
                    reminder = 1;
                    newRightDigitValue = newRightDigitValue % 10;
                } else {
                    reminder = 0;
                }

                IntNode newNode = new IntNode(newRightDigitValue);

                if (newNumberHead == null) {
                    newNumberHead = newNode;
                    newNumberCurrNode = newNumberHead;
                } else {
                    newNumberCurrNode.setNext(newNode);
                    newNumberCurrNode = newNode;
                }
            }
            else {
                int newRightDigitValue = (int) (num % 10) + reminder;
                reminder = 0;
                IntNode newNode = new IntNode(newRightDigitValue);
                newNumberCurrNode.setNext(newNode);
                newNumberCurrNode = newNode;
            }
            num /= 10;
        }

        BigNumber newBigNumber = new BigNumber();

        newBigNumber._head.setValue(newNumberHead.getValue());
        newBigNumber._head.setNext(newNumberHead.getNext());
        return newBigNumber;
//        return addBigNumber(new BigNumber(num));
    }



// Private methods:
    // The helper method of the public toString recursive method.
    // The idea - traverse through all the digits until we get to the last one, which returns an empty string (so the string concatenation will work)
    // and then while folding the recursion, each node adding itself after the digits that
    // located After it (because the BigNumber is stored as a "reversed" version of the number)

    private String toString(IntNode node) {
        if (node == null) {
            return "";
        }
        return toString(node.getNext()) + node.getValue();
    }

    // An helper method for compareTo which compares two IntNodes' values
    private int compareDigits(IntNode myCurrNode, IntNode otherCurrNode) {
        if (myCurrNode.getValue() > otherCurrNode.getValue()) {
            return 1;
        }
        else if (myCurrNode.getValue() < otherCurrNode.getValue()) {
            return -1;
        }
        return 0;
    }


    // This is an helper method which flipping the passed linked list
    // so the head will become the tail and the other way around.
    // The idea is to keep track of 3 pointers which points to the prev, current and next nodes while we
    // traverse the list, so we could change current's _next to its prev node at each step
    // Time complexity - O(N) - traversing over the list once.
    // Space complexity - O(1) - using only 3 nodes pointers variables.
    private BigNumber flipBigNumber(BigNumber bigNumber) {
        // Initializing 3 pointers to be able to change the next node while still holding the address of the "original next",
        // so we could still traverse the list while changing each node's _next to the prev node,
        // which eventually will be the new flipped head.
        IntNode prev = null;
        IntNode current = bigNumber._head;
        IntNode next = null;

        while (current != null) {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        bigNumber._head = prev;
        return bigNumber;
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