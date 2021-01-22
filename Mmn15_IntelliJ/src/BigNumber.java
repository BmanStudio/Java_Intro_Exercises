/**
 * Mmn 15
 * // TODO general class API
 * @author Ori Ben Nun
 * @version 22/01/2021
 */
public class BigNumber {

    private IntNode _head; // Stores the rightmost digit of the number (single unit).

// Constructors:

    /**
     * The empty (default) constructor.
     * Initializing a new BigNumber linked-list which represents the number 0.
     *
     * Time and Space complexity are O(1).
     */
    public BigNumber () {
        _head = new IntNode(0);
    }

    /**
     * Initializing a new BigNumber linked-list which represents the passed number,
     * by assigning each digit of the number to a Node in the BigNumber list (which is reversed to the "normal form")
     *
     * Time complexity - O(N) - because we are traversing ONCE through the digits that defining the passed number - but we actually knows that the max value on N will be 19.
     *
     * Space complexity - O(N) - but we actually knows that the max value on N will be 19.
     *
     * @param number The non-negative number which will be represented by the BigNumber linked-list.
     */
    public BigNumber(long number) {

        IntNode currTail = null;

        if (number == 0) {
            _head = new IntNode(0);
            return;
        }

        // number is being divided by 10 in each loop, means eventually it will decrease to 0
        // notice that we are working with whole numbers (integers)
        while (number > 0) {

            // Every number (which is bigger than 9) will become its own rightmost digit once being preformed modulo 10 on.
            // So by doing number % 10, we actually storing the rightmost digit (while NOT changing the passed number itself)
            // and then we storing the rightmost digit as the a new node.
            int rightDigit = (int) (number % 10);
            IntNode newNode = new IntNode(rightDigit);

            // If this is the first node of the new BigNumber, we will set the new node as head, which is also the current tail
            if (currTail == null) {
                _head = newNode;
                currTail = _head;
            }
            // For all of the other nodes, we will set the next node's value to the new node, and move the tail to it
            else {
                currTail.setNext(newNode);
                currTail = newNode;
            }
            // and finally, after adding the rightmost digit of the passed number as the new node,
            // we can just "remove" the rightmost digit from the number, by dividing it by 10.
            number /= 10;
        }
    }

    /**
     * The copy constructor of the BigNumber linked-list class, which copies another BigNumber.
     *
     * Time complexity - We are iterating through the passed BigNumber list from head to tail, and copying each node to the new BigNumber.
     *      means - the time complexity is O(N), and the Runtime is 1 iteration over the list.
     *
     * Space complexity - we are only storing references of individual nodes each time,
     *      means - the space complexity is O(N) - but we actually knows that the max value on N will be 19.
     *
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
     *
     * Time complexity - We are iterating once through all of the nodes in the list before folding back the recursion,
     *      means - the time complexity is O(N), and the Runtime is 2 iterations (back and forth - down the recursion and back up).
     *
     * Space complexity - O(N) (including the recursion stack memory, which equals to the number of nodes, which is N) - although we are not storing any variables.
     *
     * @return The number represented by BigNumber as a string, in its "normal form".
     */
    // This is the public "main" recursion method, which calls the private overloaded version
    // which contains the algorithm logic
    public String toString() {
        return toString(_head);
    }

    /**
     * This Recursive method compares between the values represented by two BigNumbers, corresponding to the one that the method was called from.
     *  For example: if the value which is represented by this BigNumber (the called object) is smaller than the passed one,
     *  well will call it as "smaller than", and the method will return -1.
     *
     * The idea - we are using recursion (an overloaded private method) to traverse through the lists until we get to a point which either:
     *  a) we arrived at the tail of one of the lists (but not both) - means we found the bigger number and we can return the corresponding answer back up.
     *  b) we arrived at the tails of both of the lists - then we starting to compare between the values while returning each comparison result back up.
     *      If the recursion brought us back a value of 0 - means all of the next nodes are equals - so we just need to return the comparison of the two current nodes.
     *      Otherwise - we will just return the result that came back from the recursion (because the answer was found at the comparisons of the next nodes).
     *
     * Time complexity - O(N) - we are going back and forth only once over the list (the max N value will be the length of the shorter list).
     *
     * Number of iterations over the list - we are traversing both of the lists twice at the most - by going down until the tail (of the shorter list or both of the tails), and then back up.
     *
     * Space complexity - O(N) - because of the recursion stack memory which is being stacked for N times - as for the shorter list length.
     *
     * @param other The reference BigNumber object, which it's value will be compared to this BigNumber's value.
     * @return 1 if this number is bigger, 0 if they are equal, -1 if other is bigger.
     */
    public int compareTo (BigNumber other) {
        // calling the private overloaded method, with the head nodes of both of the lists, which holds the logic
        return compareTo(_head, other._head);
    }

    /**
     * This method takes a BigNumber and creates a new BigNumber object which represents the number which is the sum of this BigNumber
     *  and the passed BigNumber.
     *
     * The idea - we are traversing once over the lists (while there are nodes left even in one of them), while at each iteration we are
     *  adding the two corresponding nodes, and storing in the corresponding position result's node the sum value. In the case the sum is
     *  bigger than 9 (means the node will hold more than 1 digit) - we are increasing a reminder variable which holds the value that should be added to the next node
     *  (which represent the 10^n+1 position, related to the current node), and modifying the value to be modulo 10 (means a single digit).
     *
     * Time complexity - O(N) - because we are iterating in a single loop over all of the nodes of the longer list.
     *
     * Number of iterations over lists - this method is traversing the longer list only once.
     *
     * Space complexity - O(N) - because we are creating a new BigNumber, and its length will be N+1 for the "worst case" (where N represents the length of the longer list)
     *
     * @param other The reference BigNumber object, which it's value will be summed with this BigNumber value.
     * @return A new BigNumber object which represents the number which is the sum of this BigNumber and the passed BigNumber.
     */
    public BigNumber addBigNumber (BigNumber other) {
        // Storing and keeping track of the result list, which start with an empty head,
        // while the current node will be the one we modify and "moving forward" in the loop.
        IntNode resultHead = null;
        IntNode resultCurrNode = null;

        // This variable will store the value which should be added to the next node (because the sum of two nodes was bigger than 9)
        int reminder = 0;

        // Tracking both of the lists nodes so we can traverse them.
        IntNode otherCurrent = other._head;
        IntNode myCurrent = _head;

        // We are keep on looping until both of the lists are done, because once one of them is done, we still want to copy (add 0) each
        // of the remaining nodes on the list that wasn't done yet
        while (myCurrent != null || otherCurrent != null) {

            int myCurrentValue;
            int otherCurrentValue;

            // To "copy" the value of the other node
            if (myCurrent == null) {
                myCurrentValue = 0;
            }
            else {
                myCurrentValue = myCurrent.getValue();
                myCurrent = myCurrent.getNext();
            }

            // To "copy" the value of the other node
            if (otherCurrent == null) {
                otherCurrentValue = 0;
            }
            else {
                otherCurrentValue = otherCurrent.getValue();
                otherCurrent = otherCurrent.getNext();
            }

            // At this point we want to address the reminder (if exists) from the previous iteration
            int resultNewValue = reminder + myCurrentValue + otherCurrentValue;

            // If the current sum is greater than 9, we want to pass a reminder of 1 (which represents 10 "unit" of the current node's value)
            // to the next iteration, which means we "subtracted" 10 off the current value, so we can keep it's modulo 10 (the rightmost digit).
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
                resultCurrNode = resultHead;
            }
            else {
                resultCurrNode.setNext(resultNewDigit);
                resultCurrNode = resultNewDigit;
            }
        }

        // If we traversed the list and there is still a reminder (which should be 1 if any), means we should create an extra node for
        // the result list, which should hold the value of the reminder, and it will be the last node being added (as the tail, which is the leftmost digit of the number)
        if (reminder > 0) {
            resultCurrNode.setNext(new IntNode(reminder));
        }

        // Initializing a new BigNumber and linking the list we just created
        BigNumber resultNumber = new BigNumber();

        resultNumber._head.setValue(resultHead.getValue());
        resultNumber._head.setNext(resultHead.getNext());

        return resultNumber;
    }


    /**
     * This method takes a long number and creating a new BigNumber object which represents the number which is the sum of this BigNumber
     *  and the passed number.
     *
     * The idea - Very similar to addBigNumber - we are traversing once over the list (or as long as the number of digits of the passed number),
     *  while at each iteration we are
     *  adding the two corresponding nodes, and storing in the corresponding position result's node the sum value. In the case the sum is
     *  bigger than 9 (means the node will hold more than 1 digit) - we are increasing a reminder variable which holds the value that should be added to the next node
     *  (which represent the 10^n+1 position, related to the current node), and modifying the value to be modulo 10 (means a single digit).
     *
     * Time complexity - O(N) - because we are iterating in a single loop over all of the nodes of the longer list.
     *
     * Number of iterations over lists - this method is traversing the longer list only once.
     *
     * Space complexity - O(N) - because we are creating a new BigNumber, and its length will be N+1 for the "worst case" (where N represents the length of the longer list)
     *
     * @param num The reference BigNumber object, which it's value will be summed with this BigNumber value.
     * @return A new BigNumber object which represents the number which is the sum of this BigNumber and the passed BigNumber.
     */
    public BigNumber addLong (long num) {

        // Storing and keeping track of the result list, which start with an empty head,
        // while the current node will be the one we modify and "moving forward" in the loop.
        IntNode resultHead = null;
        IntNode resultCurrNode = null;

        // Tracking the list's current node so we can traverse it.
        IntNode myCurrNode = _head;

        // This variable will store the value which should be added to the next node (because the sum of two nodes was bigger than 9)
        int reminder = 0;

        // We are keep on looping until both the lists and the passed number are "done", because once one of them is done, we still want to copy (add 0) each
        // of the remaining digits / nodes on the number / list  that wasn't done yet
        while (myCurrNode != null || num > 0) {

            // We want to take the sum of the current list node's value and the rightmost digit of the current number
            // just as long as there are more nodes.
            // This will also handle when BigNumber still got digits but num is 0 (so always 0 will be added)
            if (myCurrNode != null) {

                // The same idea as in the BigNumber(long ..) constructor - we are "separating" the rightmost digit of number by applying modulo 10 to it,
                // which is being added to the current node's value and the reminder from previous iteration.
                int newRightDigitValue = (int) (num % 10) + myCurrNode.getValue() + reminder;

                // moving to the next node
                myCurrNode = myCurrNode.getNext();

                // If the current sum is greater than 9,we want to pass a reminder of 1 (which represents 10 "unit" of the current node's value)
                // to the next iteration, which means we "subtracted" 10 off the current value, so we can keep it's modulo 10 (the rightmost digit).
                if (newRightDigitValue > 9) {
                    reminder = 1;
                    newRightDigitValue = newRightDigitValue % 10;
                } else {
                    reminder = 0;
                }

                IntNode newNode = new IntNode(newRightDigitValue);

                if (resultHead == null) {
                    resultHead = newNode;
                    resultCurrNode = resultHead;
                } else {
                    resultCurrNode.setNext(newNode);
                    resultCurrNode = newNode;
                }
            }

            // Otherwise - means the list is done but the number still got digits to contribute to the result,
            // so we just adding each digit as a new node (using modulo 10 of the current number)
            else {
                int newRightDigitValue = (int) (num % 10) + reminder;
                reminder = 0;
                IntNode newNode = new IntNode(newRightDigitValue);
                resultCurrNode.setNext(newNode);
                resultCurrNode = newNode;
            }
            // And in any case - we would want to "remove" the rightmost digit of num (doing so by dividing by 10)
            num /= 10;
        }

        // If we traversed the list and there is still a reminder (which should be 1 if any), means we should create an extra node for
        // the result list, which should hold the value of the reminder, and it will be the last node being added (as the tail, which is the leftmost digit of the number)
        if (reminder > 0) {
            resultCurrNode.setNext(new IntNode(reminder));
        }

        // Initializing a new BigNumber and linking the list we just created
        BigNumber newBigNumber = new BigNumber();

        newBigNumber._head.setValue(resultHead.getValue());
        newBigNumber._head.setNext(resultHead.getNext());
        return newBigNumber;
    }


    /**
     * This method takes a BigNumber and creates a new BigNumber object which represents the number which is the subtraction result between the bigger number
     *  and the other one (which is being calculated by the method as well).
     *
     * The idea is based on the method of "long subtraction" (which is being taught in elementary school).
     *  and it is very similar to addBigNumber - we are iterating through the longer list, while at each step subtracting
     *  the current digit of the smaller number from the bigger number's digit (while keeping track of any adjustments
     *  need to be made to the "next" digit if the subtraction result is smaller than 0.
     *  In order to handle a case where there is a string of zeros in the result (for ex. --> "3005" - "3002" = "0002"):
     *  we are creating a new "list" (not a bigNumber) which holds all of the following zeros created in the subtraction process, and then:
     *      if we got to the end of the bigger number while no numbers other than zero was found since we "started finding zeros" - we will just return the result number "as is"
     *      without adding the zeros ("dumping all of the zeros")
     *      else - means we found a non-zero number AFTER a string of zeros, means all of the zeros "defining" the result number,
     *      so we will just concat the result to the list of zeros, and then reset the list of zeros and keep moving to the next digit (if exists)
     *
     * Time complexity - O(N) - because we are iterating in a single loop over all of the nodes of the longer list.
     *
     * Number of iterations over lists - this method is traversing the longer list only once.
     *
     * Space complexity - O(N) - because we are creating a new BigNumber, and its length will be N for the "worst case" (where N represents the length of the longer list)
     *
     * @param other The reference BigNumber object, which it's value will be either subtracted from this BigNumber OR
     *              this BigNumber will be subtracted from, depends on which is bigger.
     * @return A new BigNumber object which represents the number which is the subtraction result.
     */
    public BigNumber subtractBigNumber (BigNumber other) {
        // Checking to see which is the bigger number, so we will subtract the other one from it
        // and we are storing a reference to the bigger BigNumber
        int isMyNumberBigger = compareTo(other); // this method doing 2 iterations and takes O(N) space on memory stack for the recursion
        BigNumber biggerNumber;
        BigNumber smallerNumber;

        // Assigning to the variables according to the comparison result
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

        // Storing and keeping track of the result list, which start with an empty head,
        // while the current node will be the one we modify and "moving forward" in the loop.
        IntNode resultHead = null;
        IntNode resultCurrNode = null;

        // The variables for the "zeros string", which will be created when we find a string of zeros, and "removed" once we found another number which isn't 0.
        IntNode suspectedSegmentHead = null;
        IntNode suspectedSegment = null;

        // This variable will store the value which should be subtracted from the next node (because the subtraction of two nodes was smaller than 0, so we "converted" 1 reminder to 10 "units")
        int reminder = 0;

        // Tracking both of the lists nodes so we can traverse them.
        IntNode biggerCurrentNode = biggerNumber._head;
        IntNode smallerCurrentNode = smallerNumber._head;

        // We should only consider the length of the longer list, because once the shorter list was done we can just store the values of the remaining nodes
        while (biggerCurrentNode != null) {

            int biggerCurrentValue = biggerCurrentNode.getValue();
            biggerCurrentNode = biggerCurrentNode.getNext();
            int smallerCurrentValue;

            // To "copy" the value of the bigger list's node
            if (smallerCurrentNode == null) {
                smallerCurrentValue = 0;
            }
            else {
                smallerCurrentValue = smallerCurrentNode.getValue();
                smallerCurrentNode = smallerCurrentNode.getNext();
            }

            // Subtracting any reminder left
            biggerCurrentValue -= reminder;

            int resultNewValue = biggerCurrentValue - smallerCurrentValue;

            // If the current result value is smaller than 0, we want to pass a reminder of 1 (which represents 10 "unit" of the current node's value)
            // to the next iteration, which means we "converted" 1 reminder (which is the value of the next node)
            // so we could add 10 to the current value, so the result won't be below 0, and we could assign it to the new node
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

        // Initializing a new BigNumber and linking the list we just created
        BigNumber resultNumber = new BigNumber();

        resultNumber._head.setValue(resultHead.getValue());
        resultNumber._head.setNext(resultHead.getNext());

        return resultNumber;
    }

    /**  // TODO API
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

// Private methods:

    /**
     * The helper method of the public toString recursive method.
     * The idea - traverse through all the digits until we get to the last one, which returns an empty string (so the string concatenation will work)
     * and then while folding the recursion, each node adding itself after the digits that
     * located After it (because the BigNumber is stored as a "reversed" version of the number)
     */
    private String toString(IntNode node) {
        if (node == null) {
            return "";
        }
        return toString(node.getNext()) + node.getValue();
    }

    /**
     * This Recursive method is the overloaded version of compareTo public method.
     * The method is performing 2 iterations - one down one up.
     * The idea of this method is described at the public method API
     */
    private int compareTo(IntNode myCurrNode, IntNode otherCurrNode) {

        if (myCurrNode.getNext() == null) {
            if (otherCurrNode.getNext() == null) {
                // If the two lists has an equal lengths, we would compare each of the nodes from the tail back to head,
                // until we find a non-equal pair, which holds the answer
                return compareDigits(myCurrNode, otherCurrNode);    // Starting to fold back the recursion and check the values
            }
            // Means my digits are done before other's, so im the smaller one
            else {
                return -1;
            }
        }
        else if (otherCurrNode.getNext() == null) {
            // Means i got nodes but other is done, so im bigger
            return 1;
        }
        // Means we can still "go down the recursion"
        else {
            // This var will hold the comparison answer of the next nodes
            int tempResult  = compareTo(myCurrNode.getNext(), otherCurrNode.getNext());
            // if the result is 0, means the two lists are completely equal from the next node and forward, means the comparison of the
            // two current nodes will determine the answer (or will be equal as well, which will "pass the responsibility" to the previous nodes).
            if ( tempResult == 0) {
                return compareDigits(myCurrNode, otherCurrNode);
            }
            // otherwise - we already found a non-equal pair, which holds the final answer.
            else {
                return tempResult;
            }
        }
    }

    /**
     * An helper method for compareTo which compares two IntNodes' values
     * and returns a corresponding results for compareTo method
     * Time and Space complexity is O(1)
     */
    private int compareDigits(IntNode myCurrNode, IntNode otherCurrNode) {
        if (myCurrNode.getValue() > otherCurrNode.getValue()) {
            return 1;
        }
        else if (myCurrNode.getValue() < otherCurrNode.getValue()) {
            return -1;
        }
        return 0;
    }

    /**
     * An helper method to check if the BigNumber is "empty" (means its only storing 0 as the head and there is no other digit
     * This method is O(1) of Time and Space complexity
     */
    private boolean isPlainZero(BigNumber bigNumber) {
        return bigNumber._head.getValue() == 0 && bigNumber._head.getNext() == null;
    }

}