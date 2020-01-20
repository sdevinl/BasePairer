/*
 * NAME: TODO Steven Liu
 * PID: TODO A12902940
 */

import java.util.AbstractList;

/**
 * Doubly-Linked List Implementation
 *
 * @author TODO
 * Steven Liu A12902940
 * @since TODO
 * 10/26/2019
 */
public class DoublyLinkedList<T> extends AbstractList<T> {
    private int nelems;
    private Node head;
    private Node tail;

    /**
     * Node for chaining together to create a linked list
     */
    protected class Node {
        T data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         */
        private Node(T element) {
            //TODO: complete constructor
            data = element;
            next = null;
            prev = null;
        }

        /**
         * Constructor to create singleton link it between previous and next
         *
         * @param element  Element to add, can be null
         * @param nextNode successor Node, can be null
         * @param prevNode predecessor Node, can be null
         */
        private Node(T element, Node nextNode, Node prevNode) {
            //TODO: complete implementation
            data = element;
            next = nextNode;
            prev = prevNode;
        }

        /**
         * Set the element
         *
         * @param element new element
         */
        public void setElement(T element) {
            //TODO: complete implementation
            data = element;
        }

        /**
         * Accessor to get the Nodes Element
         */
        public T getElement() {
            //TODO: complete implementation
            return data;
        }

        /**
         * Set the next node in the list
         *
         * @param n new next node
         */
        public void setNext(Node n) {
            //TODO: complete implementation
            next = n;
        }

        /**
         * Get the next node in the list
         *
         * @return the successor node
         */
        public Node getNext() {
            //TODO: complete implementation
            return next;
        }

        /**
         * Set the previous node in the list
         *
         * @param p new previous node
         */
        public void setPrev(Node p) {
            //TODO: complete implementation
            prev = p;
        }


        /**
         * Accessor to get the prev Node in the list
         *
         * @return predecessor node
         */
        public Node getPrev() {
            //TODO: complete implementation
            return prev;
        }

        /**
         * Remove this node from the list.
         * Update previous and next nodes
         */
        public void remove() {
            //TODO: complete implementation
            prev.setNext(next);
            next.setPrev(prev);
            setNext(null);
            setPrev(null);
        }
    }

    /**
     * Creates a new, empty doubly-linked list.
     */
    public DoublyLinkedList() {
        //TODO: complete default constructor
        nelems = 0;
        head = new Node(null);
        tail = new Node(null);

    }

    /**
     * Add an element to the end of the list
     *
     * @param element data to be added
     * @return whether or not the element was added
     * @throws NullPointerException if data received is null
     */
    @Override
    public boolean add(T element) throws NullPointerException {
        //TODO: Implementation for throwing exceptions followed by
        //implementation of adding the new data

        if (element == null) {
            throw new NullPointerException();
        }

        if (nelems == 0) {
            Node node = new Node(element, tail, head);
            head.setNext(node);
            tail.setPrev(node);
            nelems++;
            return true;
        }

        try {
            Node node = new Node(element, tail, tail.getPrev());
            tail.getPrev().setNext(node);
            tail.setPrev(node);
            nelems++;
            return true;
        } catch (NullPointerException e) {
            return false;

        }
    }


    /**
     * Adds an element to a certain index in the list, shifting exist elements
     * create room. Does not accept null values.
     * <p>
     * TODO: Javadoc comments
     * @param index index to add
     * @param element element to add at index
     * @throws IndexOutOfBoundsException when index is not in list
     * @throws NullPointerException when element is null
     */
    @Override
    public void add(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        //TODO: Implementation for throwing exceptions followed by 
        //implementation of adding the new data
        Node curr = head;

        if (element == null) {
            throw new NullPointerException();
        }

        if ((index < 0) | (index >= nelems + 1)) {
            throw new IndexOutOfBoundsException();
        }


        if (nelems == 0) {
            Node node = new Node(element, tail, head);
            head.setNext(node);
            tail.setPrev(node);
        } else {
            for (int i = 0; i < index; i++) {
                curr = curr.getNext();
            }
            Node node = new Node(element, curr.getNext(), curr);
            curr.getNext().setPrev(curr);
            curr.setNext(node);

        }

        nelems += 1;


    }

    /**
     * Clear the linked list
     */
    @Override
    public void clear() {
        //TODO: implement clear
        head.setNext(tail);
        tail.setPrev(head);
        nelems = 0;
    }

    /**
     * Determine if the list contains the data element anywhere in the list.
     * <p>
     * TODO: Javadoc comments
     * @param element object element to add
     * @returns true if element is found, else false
     */
    @Override
    public boolean contains(Object element) {
        T data = (T) element;
        //TODO: Fill in implementation
        Node curr = head;

        while (curr.getNext() != tail) {
            curr = curr.getNext();
            if (curr.getElement() == data) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves the element stored with a given index on the list.
     * <p>
     * TODO: Javadoc comments
     * @param index index to get data
     * @throws IndexOutOfBoundsException if index is not in list
     * @return element at index
     *
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        //TODO: Fill in implementation to get the node at index
        if ((index > nelems) || (index < 0)) {
            throw new IndexOutOfBoundsException();
        }

        Node node = getNth(index);
        return node.getElement();
    }

    /**
     * Helper method to get the Nth node in our list
     * <p>
     * TODO: Javadoc comments
     * @param index int index
     * @return node at the index
     */
    private Node getNth(int index) {
        //TODO: implement
        Node curr = head;

        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
        }
        return curr.getNext();
    }

    /**
     * Determine if the list empty
     * <p>
     * TODO: javadoc comments
     * @return true if list is nelems is 0, else false
     */
    @Override
    public boolean isEmpty() {
        //TODO: implement isEmpty
        return nelems == 0;
    }

    /**
     * Remove the element from position index in the list
     * <p>
     * TODO: javadoc comments
     * @param index index to remove
     * @throws IndexOutOfBoundsException when index is outside of list
     * @return element at the index
     */
    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        //TODO: Fill in implementation
        if (nelems < index) {
            throw new IndexOutOfBoundsException();
        }
        Node curr = getNth(index);

        T data = curr.getElement();
        /*
        curr.getPrev().setNext(curr.getNext());
        curr.getNext().setPrev(curr.getPrev());
        */
        curr.remove();
        nelems--;

        return data;
    }

    /**
     * Set the value of an element at a certain index in the list.
     * <p>
     * TODO: javadoc comments
     * @param index int index to set
     * @param element at value
     * @throws IndexOutOfBoundsException index not in list
     * @throws NullPointerException if element is null
     * @return element at index
     */
    @Override
    public T set(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        //TODO: Fill in implmentation
        if (nelems < index) {
            throw new IndexOutOfBoundsException();
        }

        if (element == null) {
            throw new NullPointerException();
        }

        Node curr = getNth(index);
        T data = curr.getElement();
        curr.setElement(element);

        return data;
    }

    /**
     * Retrieves the amount of elements that are currently on the list.
     * <p>
     * TODO: javadoc comments
     * @return number of nodes
     */
    @Override
    public int size() {
        //TODO: complete implementation
        return nelems;
    }

    /**
     * Inserts another linked list of the same type into this one
     * <p>
     * TODO: javadoc comments
     * @param index index to insert otherList
     * @param otherList list to insert at index
     * @throws IndexOutOfBoundsException if index is not in list
     *
     */
    public void splice(int index, DoublyLinkedList<T> otherList) throws IndexOutOfBoundsException {
        //TODO: Determine if index is valid
        if (index > nelems || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        //TODO: Splicing implementation. HINT: remember DoublyLinkedList's  have dummy nodes
        Node otherTail = otherList.tail;
        Node otherHead = otherList.head;

        Node toAdd = getNth(index - 1);
        /*
        if (index == size() - 1) {
            Node append = new Node(null, tail, toAdd);
            toAdd.setNext(append);
            tail.setPrev(append);
        }
        */
        toAdd.getNext().setPrev(otherTail.getPrev());
        otherTail.getPrev().setNext(toAdd.getNext());
        otherHead.getNext().setPrev(toAdd);
        toAdd.setNext(otherHead.getNext());

        nelems += otherList.size();


    }

    /**
     * Determine the starting indices that match the subSequence
     * <p>
     * TODO: javadoc comments
     * @param subsequence list to match
     * @return array of indexes where the subsequence starts, if none return empty array
     */
    public int[] match(DoublyLinkedList<T> subsequence) {

        //A list to hold all the starting indices found
        DoublyLinkedList<Integer> indices = new DoublyLinkedList<>();

        //TODO: Add implementation to find the starting indices
        Node curr = head;
        for (int i = 0; i < size(); i++) {
            curr = curr.getNext();
            if (curr.getElement() == subsequence.head.getNext().getElement()) {
                boolean match = false;
                Node seq = curr;
                Node sub = subsequence.head.getNext();
                for (int x = 0; x < subsequence.size(); x++) {
                    if (seq.getElement() == sub.getElement()) {
                        seq = seq.getNext();
                        sub = sub.getNext();
                        match = true;
                        continue;
                    }
                    match = false;
                    break;
                    }
                if (match) {
                    indices.add(i);
                }
            }
        }

        // Array Conversion
        int[] startingIndices = new int[indices.size()];
        for (int i = 0; i < indices.size(); i++) {
            startingIndices[i] = indices.get(i);
        }
        return startingIndices;
    }

}



