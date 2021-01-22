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
            _head = new IntNode(0);
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
     * // TODO Rewrite the API
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
     */
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
     * Time complexity -
     * Number of iterations over lists -
     * Space complexity -      * @param other
     * @return
     */
    public BigNumber addBigNumber (BigNumber other) {

        IntNode resultHead = null;
        IntNode resultCurrTail = null;

        int reminder = 0;
        IntNode otherCurrent = other._head;
        IntNode myCurrent = _head;

        while (myCurrent != null || otherCurrent != null) {
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

        if (resultHead != null) {
            resultNumber._head.setValue(resultHead.getValue());
        }
        resultNumber._head.setNext(resultHead.getNext());
        return resultNumber;
    }


    // TODO add API and comments
    public BigNumber addLong (long num) {

        IntNode newNumberHead = null;
        IntNode newNumberCurrNode = null;
        IntNode myCurrNode = _head;

        int reminder = 0;

        while (myCurrNode != null || num > 0) {

            // This will also handle when BigNumber still got digits but num is 0
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
    }


    /** // TODO API and comments
     * we iterate the list only once
     * The idea is very similar to addBigNumber - we are iterating through the longer list, while at each step subtracting
     * the current digit of the smaller number from the bigger number's digit (while keeping track of any adjustments
     * need to be made to the "next" digit if the subtraction result is smaller than 0.
     * In order to handle a case where there is a string of zeros in the result (for ex. --> "3005" - "3002" = "0002"):
     *  we are creating a new "list" (not a bigNumber) which holds all of the following zeros created in the subtraction process, and then:
     *      if we got to the end of the bigger number while no numbers other than zero was found since we "started finding zeros" - we will just return the result number "as is"
     *      without adding the zeros ("dumping all of the zeros")
     *      else - means we found a non-zero number AFTER a string of zeros, means all of the zeros "defining" the result number,
     *      so we will just concat the result to the list of zeros, and then reset the list of zeros and keep moving to the next digit (if exists)
     * Time complexity -
     * Number of iterations over lists -
     * Space complexity -
     * @param other
     * @return
     */
    public BigNumber subtractBigNumber (BigNumber other) {
        // Checking to see which is the bigger number, so we will subtract the other one from it
        // and we are storing a reference to the bigger BigNumber
        int isMyNumberBigger = compareTo(other); // this method doing 2 iterations and takes O(N) space on memory stack for the recursion

        BigNumber biggerNumber;
        BigNumber smallerNumber;

        if (isMyNumberBigger == 1) {
            biggerNumber = this;
            smallerNumber = other;
        }
        else if (isMyNumberBigger == -1) {
            biggerNumber = other;
            smallerNumber = this;
        }
        // In the case they are equal, we can just return a new "empty" BigNumber with the value of 0
        else {
            return new BigNumber();
        }

        IntNode resultHead = null;
        IntNode resultCurrNode = null;

        IntNode suspectedSegment = null;
        IntNode suspectedSegmentHead = null;

        int reminder = 0;
        IntNode biggerCurrentNode = biggerNumber._head;
        IntNode smallerCurrentNode = smallerNumber._head;

        while (biggerCurrentNode != null) {

            int biggerCurrentValue = biggerCurrentNode.getValue();
            biggerCurrentNode = biggerCurrentNode.getNext();
            int smallerCurrentValue;

            if (smallerCurrentNode == null) {
                smallerCurrentValue = 0;
            }
            else {
                smallerCurrentValue = smallerCurrentNode.getValue();
                smallerCurrentNode = smallerCurrentNode.getNext();
            }

            biggerCurrentValue -= reminder;

            int resultNewValue = biggerCurrentValue - smallerCurrentValue;

            if (resultNewValue < 0) {
                reminder = 1;
                resultNewValue = (biggerCurrentValue + 10) - smallerCurrentValue;
            }

            else {
                reminder = 0;
            }

            IntNode candidateNewDigit = new IntNode(resultNewValue);

            if (resultHead == null) {
                resultHead = candidateNewDigit;
                resultCurrNode = resultHead;
            }

            // The idea is to create a separated suspected segment of nodes which all holds the value of 0,
            // so if we get to the end of the process without adding any more values other
            // than 0 - we could just not concat the "0"th list to the result list
            // we are using else if because if the head is 0 we can assume that it should be there
            else if (resultNewValue == 0) {
                if (suspectedSegmentHead == null) {
                    suspectedSegmentHead = new IntNode(0);
                    suspectedSegment = suspectedSegmentHead;
                }
                else {
                    suspectedSegment.setNext(candidateNewDigit);
                    suspectedSegment = candidateNewDigit;
                }
            }

            else {
                // if we got to a node which is not 0, but we already have a list of zeros,
                // we will concat it before adding the new non-zero node, and set the resultCurrNode to the tail of that
                // list of zeros.
                if (suspectedSegmentHead != null) {
                    resultCurrNode.setNext(suspectedSegmentHead);
                    resultCurrNode = suspectedSegment;

                    // And finally resetting the zeros list
                    suspectedSegmentHead = null;
                    suspectedSegment = null;
                }
                resultCurrNode.setNext(candidateNewDigit);
                resultCurrNode = candidateNewDigit;
            }
        }

        BigNumber resultNumber = new BigNumber();

        if (resultHead != null) {
            resultNumber._head.setValue(resultHead.getValue());
        }
        if (resultHead.getNext() != resultHead) {
            resultNumber._head.setNext(resultHead.getNext());
        }
        return resultNumber;
    }

    /**
     * The idea:
     *  we are going to loop each node of the other list for every node of this list (means O(N * M)), trying to say:
     *  for each node of this list:
     *      for each node of other list:
     *          1) Multiply the values stored in the current nodes.
     *          2) add the result by modulo 10 to a new node in the result list (in the corresponding "position").
     *             notice that at this point the new node could hold a number bigger than 9 (means "2 digits") - we will handle it at the end outside this loop.
     *          3) Calculate a Carry variable which will be added to the next result node which represents the 10^n "reminder" from the multiplication.
     *          4) move to the next node of se other list until we get to the tail
     *      move to the next node of this list until we get to the tail
     *  and finally initialize, store the list in a BigNumber object and return it
     * Time complexity -
     * Number of iterations over lists -
     * Space complexity -
     * @param other
     * @return
     */
    // TODO API
    public BigNumber multBigNumber (BigNumber other) {

        // taking care of edge case where the value of one of the numbers is "plain" 0 (means its an "empty" BigNumber), so always the result will be 0
        // so we can just return a new empty (0 initialized) BigNumber
        if ((isPlainZero(this)) ||
                isPlainZero(other)) {
            return new BigNumber();
        }

        // Initializing the result head to a node with a value of 0 so we could add to its value
        IntNode resultHead = new IntNode(0);
        IntNode resultCurrNode = resultHead;

        // We will use this list as the outer loop, so we are starting from the head
        IntNode myCurrNode = this._head;

        // Multiply each node of other list with this list
        while (myCurrNode != null) {

            // Creating and resetting the carry variable which holds the "positive reminder" for inner loop multiplication.
            int carry = 0;

            // At each iteration of the outer loop we moved one node forward, so we want to multiply this node by every
            // node of other's list, so we will reset it back to its head
            IntNode otherCurrNode = other._head;

            // For the result node, we want to keep track of both the result node which corresponding to this list,
            // as well as another one which we be modified inside the inner loop
            // (because each node of result will be modified many times)
            // so we will initialize another IntNode which will be in the corresponding position according to other's list
            IntNode resultInCurrNode = resultCurrNode;

            while (otherCurrNode != null) {

                // Multiply the two current nodes, added with the current carry variable
                int multiplyResult = (myCurrNode.getValue() * otherCurrNode.getValue()) + carry;

                // checking if there is a next node in the result list AND there will be another loop
                // (so we are not adding a node which will never be modify, means we will end up with a 0 at the beginning of the result number)
                // and initializing one if isn't
                if (resultInCurrNode.getNext() == null &&
                        (myCurrNode.getNext() != null || otherCurrNode.getNext() != null)) {
                    resultInCurrNode.setNext( new IntNode(0) );
                }

                // Add the value to the corresponding result node with.
                // We are adding (to the current stored value) the result modulo 10 value, as we want it to be a single digit value, while we taking care of
                // the event where it isn't with the carry variable and reassigning the value of the result node after updating the carry variable
                int val = resultInCurrNode.getValue() + (multiplyResult % 10);
                resultInCurrNode.setValue(val);

                // The carry will be set as any "positive reminder" that should be "moved" (added) to the next node (the digit which is on the left side of this one)
                carry = (multiplyResult / 10) + (resultInCurrNode.getValue() / 10);

                resultInCurrNode.setValue( resultInCurrNode.getValue() % 10 );

                // Moving to the next nodes of other list and result
                // Also checking for the edge case where both of the nodes are the tails (which means we haven't
                // created the next result node, so when exiting the inner loop we will just add the carry to the
                // existing node, otherwise we will try to add the value to a null pointer)
                if (resultInCurrNode.getNext() != null) {
                    resultInCurrNode = resultInCurrNode.getNext();
                }
                else if (carry > 0) {
                    resultInCurrNode.setNext( new IntNode(0) );
                    resultInCurrNode = resultInCurrNode.getNext();
                }
                otherCurrNode = otherCurrNode.getNext();
            }

            // If we haven't added the remaining carry variable in the inner loop, we will add it now
            if (carry > 0) {
                resultInCurrNode.setValue( resultInCurrNode.getValue() + carry );
            }

            // Move to the next node of the outer loop (this list) as well as the corresponding result node
            // (to modify all the existing nodes once again with the new multiplication process)
            resultCurrNode = resultCurrNode.getNext();
            myCurrNode = myCurrNode.getNext();
        }
        // Similar to the last methods - we will initialize a new BigNumber and assign the result list to it
        BigNumber resultNumber = new BigNumber();

        resultNumber._head.setValue(resultHead.getValue());

        if (resultHead.getNext() != resultHead) {
            resultNumber._head.setNext(resultHead.getNext());
        }
        return resultNumber;
    }

    /**
     * An helper method to check if the BigNumber is "empty" (means its only storing 0 as the head and there is no other digit
     * This method is O(1) of Time and Space complexity
     */
    private boolean isPlainZero(BigNumber bigNumber) {
        return bigNumber._head.getValue() == 0 && bigNumber._head.getNext() == null;
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
    public void printList() {
        IntNode temp = _head;
        while (temp != null) {
            System.out.print(temp.getValue() + "-->");
            temp = temp.getNext();
        }
        System.out.println(" null");
    }
}