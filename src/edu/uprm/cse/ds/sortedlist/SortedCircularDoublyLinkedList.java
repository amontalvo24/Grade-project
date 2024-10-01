package edu.uprm.cse.ds.sortedlist;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortedCircularDoublyLinkedList<E extends Comparable<E>> implements SortedList<E> {

    /**
     * sets up the circular double linked list, constructor
     * @param <E>
     */

    private static class Node<E> {
        private E element;
        private Node<E> next;
        private Node<E> prev;

        public Node(E element, Node<E> next, Node<E> prev) {
            super();
            this.element = element;
            this.next = next;
            this.prev= prev;
        }
        public Node() {
            super();
        }


        public E getElement() {
            return element;
        }
        public void setElement(E element) {
            this.element = element;
        }
        public Node<E> getNext() {
            return next;
        }
        public void setNext(Node<E> next) {
            this.next = next;
        }
        public Node<E> getPrev(){
            return prev;
        }
        public void setPrev(Node<E> prev) {
            this.prev= prev;
        }
    }


    private class SortedCircularDoublyLinkedListIterator<E> implements Iterator<E>{
        private Node<E> nextNode;


        @SuppressWarnings("unchecked")
        public SortedCircularDoublyLinkedListIterator() {
            this.nextNode = (Node<E>) header.getNext();
        }

        @SuppressWarnings("unchecked")
        public SortedCircularDoublyLinkedListIterator(int i) {
            this.nextNode= (Node<E>) getPosition(i);
        }

        @Override
        public boolean hasNext() {
            return nextNode.getElement() != null;
        }

        @Override
        public E next() {
            if (this.hasNext()) {
                E result = this.nextNode.getElement();
                this.nextNode = this.nextNode.getNext();
                return result;
            }
            else {
                throw new NoSuchElementException();
            }
        }

    }

    private class SortedCircularDoublyLinkedListReverseIterator<E> implements ReverseIterator<E>{
        private Node<E> prevNode;



        @SuppressWarnings("unchecked")
        public SortedCircularDoublyLinkedListReverseIterator() {
            this.prevNode = (Node<E>) header.getPrev();
        }

        @SuppressWarnings("unchecked")
        public SortedCircularDoublyLinkedListReverseIterator(int i) {
            this.prevNode= (Node<E>) getPosition(i);
        }

            @Override
        public boolean hasPrevious() {
            return prevNode.getElement() != null;
        }

        @Override
        public E previous() {
            if (this.hasPrevious()) {
                E result = this.prevNode.getElement();
                this.prevNode = this.prevNode.getPrev();
                return result;
            }
            else {
                throw new NoSuchElementException();
            }
        }

    }

    private Node<E> header;
    private int currentSize;

    public SortedCircularDoublyLinkedList(){
        this.header= new Node<>();
        this.currentSize= 0;
    }

    /**
     * adds a an element to the list, increases the size and returns false if the object is null if not true
     * @param obj
     * @return true or false
     * @author Alondra Montalvo
     */
    @Override
    public boolean add(E obj) {
        if (obj==null){
            return false;
        }
        Node<E> newtemp= new Node<>();
        newtemp.setElement(obj);
        if (this.isEmpty()){
            newtemp.setNext(this.header);
            newtemp.setPrev(this.header);
            this.header.setNext(newtemp);
            this.header.setPrev(newtemp);
            currentSize++;
        }else if (this.header.getPrev().getElement().compareTo(obj)<0){
                this.header.getPrev().setNext(newtemp);
                newtemp.setNext(this.header);
                newtemp.setPrev(this.header.getPrev());
                this.header.setPrev(newtemp);
                currentSize++;
        }else{
            for (Node<E> temp = this.header.getNext(); temp.getElement()!=null; temp= temp.getNext()) {
                Node<E> prevtemp = temp.getPrev();
                if (temp.getElement().compareTo(obj)>0){
//                    newtemp = new Node<E>(obj, temp, prevtemp);
                    prevtemp.setNext(newtemp);
                    temp.setPrev(newtemp);
                    newtemp.setNext(temp);
                    newtemp.setPrev(prevtemp);
                    currentSize++;
                    return true;
                }
            }
        }
        return true;
    }

    /**
     * tells the size of the list
     * @return current size
     * @author Alondra Montalvo
     */
    @Override
    public int size() {
        return this.currentSize;
    }

    /**
     * removes en element from the list based on the object, reduces the current size and it returns false if the obj
     * is not in the list if not true
     * @param obj
     * @return true or false
     * @author Alondra Montalvo
     */
    @Override
    public boolean remove(E obj) {
//        for(Node<E> temp= this.header.getNext(); temp.getElement()!=null; temp= temp.getNext()) {
//            if (temp.getElement().compareTo(obj)==0){
//                Node<E> nexttemp= temp.getNext();
//                Node<E> prevtemmp= temp.getPrev();
//                nexttemp.setPrev(prevtemmp);
//                prevtemmp.setNext(nexttemp);
//                temp.setElement(null);
//                temp.setNext(null);
//                temp.setPrev(null);
//                currentSize--;
//                return true;
//
//            }
//        }
//        return false;

        int i = this.firstIndex(obj);
        if (i < 0) {
            return false;
        }else {
            this.remove(i);
            return true;
        }
    }

    /**
     * removes en element from the list based on the index of the position in the list, return false if the index is
     * not in the list
     * @param index
     * @return true or false
     * @throws IndexOutOfBoundsException
     * @author Alondra Montalvo
     */
    @Override
    public boolean remove(int index) {
//        if (index < 0 || index >= this.size()){
//            throw new IndexOutOfBoundsException();
//        } else {
//            int counter = 0;
//            Node<E> temp;
//            for (temp = header.getNext(); temp.getElement()!=null; temp = temp.getNext()) {
//                if (index == counter) {
//                    Node<E> nexttemp = temp.getNext();
//                    Node<E> prevtemp = temp.getPrev();
//                    nexttemp.setPrev(prevtemp);
//                    prevtemp.setNext(nexttemp);
//                    temp.setElement(null);
//                    temp.setNext(null);
//                    temp.setPrev(null);
//                    currentSize--;
//                    return true;
//                }else {
//                    counter++;
//                }
//            }
//        }
//        return false;


        int count=0;
        for (Node<E> temp = this.header.getNext(); temp.getElement()!=null; temp= temp.getNext()) {
            Node<E> nextemp = temp.getNext();
            Node<E> prevtemp= temp.getPrev();
            count++;
            if (count-1==index){
                nextemp.setPrev(prevtemp);
                prevtemp.setNext(nextemp);
                this.remove(temp.element);
                currentSize--;

                return true;
            }
        }
        return false;
    }

    /**
     * removes all the elements that match with the parameter in the list
     * @param obj
     * @return how many elements were removed, how many elements matched with the parameter
     * @author Alondra Montalvo
     */
    @Override
    public int removeAll(E obj) {
        int count= 0;
        while (this.contains(obj)){
            this.remove(this.firstIndex(obj));
            count++;
        }
        return count;
    }

    /**
     * tells us what is the element that is first in the list
     * @return the first element in the list
     * @author Alondra Montalvo
     */
    @Override
    public E first() {
        if (this.isEmpty()){
            return null;
        }else{
            return this.header.getNext().getElement();
        }
    }

    /**
     * tells us wat is the element that is last in the list
     * @return the last element in the list
     * @author Alondra Montalvo
     */
    @Override
    public E last() {
        if (this.isEmpty()){
            return null;
        }else{
            return this.header.getPrev().getElement();
        }
    }

    /**
     * if the index is in the list it, if not the index is out of bound
     * @param index
     * @return the element corresponding to the position of the index in the list
     * @throws IndexOutOfBoundsException
     * @author Alondra Montalvo
     */
    @Override
    public E get(int index) {
        if ((index < 0) || index >= this.currentSize) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> temp  = this.getPosition(index);
        return temp.getElement();
    }

    private Node<E> getPosition(int index){
        int currentPosition=0;
        Node<E> temp = this.header.getNext();

        while(currentPosition != index) {
            temp = temp.getNext();
            currentPosition++;
        }
        return temp;

    }

    /**
     * empties out the list, puts the current size at 0
     * @author Alondra Montalvo
     */
    @Override
    public void clear() {
        while (this.currentSize!=0){
            this.remove(this.first());
            currentSize--;
        }
    }

    /**
     * tells us if an element inside the list matches with the parameter
     * @param e
     * @return true or false
     * @author Alondra Montalvo
     */
    @Override
    public boolean contains(E e) {
        if (this.firstIndex(e)==-1){
            return false;
        }
        return true;
    }

    /**
     * checks if the list has no elements inside
     * @return true or false
     * @author Alondra Montalvo
     */
    @Override
    public boolean isEmpty() {
        if (this.currentSize==0){
            return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator(int index) {
        return new SortedCircularDoublyLinkedListIterator<>(index);
//        return null;
    }

    /**
     * tells us what is the index of the parameter inside the list from front half of the list
     * @param e
     * @return the number of the position the paramter is located in the list or -1 if the parameter its not in the list
     */
    @Override
    public int firstIndex(E e) {
        int count=0;
        for (Node<E> temp = this.header.getNext(); temp.getElement()!=null; temp= temp.getNext()) {
            if (temp.getElement().compareTo(e)==0){
                return count;
            }else {
                count++;
            }

        }
        return -1;
    }

    /**
     * tells us what is the index of the parameter inside the list from the back half of the list
     * @param e
     * @return the number of the position the paramter is located in the list or -1 if the parameter its not in the list
     * @author Alondra Montalvo
     */
    @Override
    public int lastIndex(E e) {
        int count=this.currentSize;
        for (Node<E> temp = this.header.getPrev(); temp.getElement()!=null; temp= temp.getPrev()) {
            if (temp.getElement().compareTo(e)==0){

                System.out.println(count);

                return count;
            }else {
                count--;
            }

        }
        return -1;
    }

    /**
     * iterates through the list from back of the last element to the first element in the list
     * @return reverse iterator for circular linked list
     * @author
     */
    @Override
    public ReverseIterator<E> reverseIterator() {
        return new SortedCircularDoublyLinkedListReverseIterator<>();

    }

    @Override
    public ReverseIterator<E> reverseIterator(int index) {
        return new SortedCircularDoublyLinkedListReverseIterator<>(index);
//        return null;
    }

    /**
     * iterates through list from the first element to the last element in the list
     * @return iterator for circular linked list
     * @author Alondra Montalvo
     */
    @Override
    public Iterator<E> iterator() {
        return new SortedCircularDoublyLinkedListIterator<>();
    }
}
