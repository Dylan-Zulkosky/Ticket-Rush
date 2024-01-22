//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Navigating a Queue
// Course: CS 300 Fall 2023
//
// Author: Dylan Zulkosky
// Email: dzulkosky@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: None
// Partner Email: None
// Partner Lecturer's Name: None
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: (identify each by name and describe how they helped)
// Online Sources: I used the javadocs for the TicketQueue class
//////////////// (https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2023/fall/p08/doc
//////////////// /TicketQueue.html) to help make the methods in this class
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;
import java.lang.Iterable;
import java.util.NoSuchElementException;

/**
 * A capacity based linked-list queue for TicketSiteUsers
 */
public class TicketQueue implements QueueADT<TicketSiteUser>, Iterable<TicketSiteUser> {
  // data fields
  // the linked node at the back of the queue
  private LinkedNode<TicketSiteUser> back;
  // the MAXIMUM number of TicketSiteUsers that the queue can hold
  private int capacity;
  // the linked node at the front of the queue
  private LinkedNode<TicketSiteUser> front;
  // the number of TicketSiteUsers in the queue
  private int size;

  /**
   * Creates an empty queue of TicketSiteUsers with the given capacity.
   * 
   * @param capacity - the capacity of this queue
   * @throws IllegalArgumentException - if the capacity is less than 1
   */
  public TicketQueue(int capacity) {
    // throw exception if capacity is less than 1
    if (capacity < 1) {
      throw new IllegalArgumentException("Capacity is less than 1.");
    }

    // create empty queue
    this.back = null;
    this.capacity = capacity;
    this.front = null;
    this.size = 0;
  }

  /**
   * Reports whether or not this queue is full.
   * 
   * @return true is the number of TicketSiteUsers is the same or more of the capacity, false
   *         otherwise
   */
  public boolean isFull() {
    if (size >= capacity) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Removes and returns the TicketSiteUser from the front of the queue.
   * 
   * @return the TicketSiteUser at the front of the queue
   * @throws NoSuchElementException - if the queue is empty
   */
  public TicketSiteUser dequeue() {
    // exception if queue is empty
    if (isEmpty()) {
      throw new NoSuchElementException("The queue is emtpy.");
    }

    // get front user and update front
    TicketSiteUser frontOfQueue = front.getData();
    front = front.getNext();
    // decrease queue size by 1
    size--;

    // made using errors from grade scope
    if (isEmpty()) {
      back = null;
    }

    // return ticket site user
    return frontOfQueue;
  }

  /**
   * Returns the TicketSiteUser from the front of the queue without removing it.
   * 
   * @return the element at the front of the queue
   * @throws NoSuchElementException - if the queue is empty
   */
  public TicketSiteUser peek() {
    // exception for if queue is empty
    if (isEmpty()) {
      throw new NoSuchElementException("The queue is empty.");
    }

    // return user from front of queue
    return front.getData();
  }

  /**
   * Reports if this queue is empty.
   * 
   * @return true if the queue has no TicketSiteUsers in it, false otherwise
   */
  public boolean isEmpty() {
    if (size == 0) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Reports the current size of the queue.
   * 
   * @return the number of TicketSiteUsers in the queue
   */
  public int size() {
    return size;
  }

  /**
   * Reports the capacity of the queue.
   * 
   * @return the MAXIMUM number of TicketSiteUsers this queue can hold
   */
  public int capacity() {
    return capacity;
  }

  /**
   * Changes the capacity of the queue to the new capacity. If the capacity is lowered, DO NOT
   * remove any elements. It will be considered full until enough TicketSiteUsers are dequeued by
   * the application.
   * 
   * @param newCapacity - the new MAXIMUM number of TicketSiteUsers this queue can hold
   * @throws IllegalArgumentException - if the newCapacity is less than 1
   */
  public void setCapacity(int newCapacity) {
    // exception for capacity less than 1
    if (newCapacity < 1) {
      throw new IllegalArgumentException("Capacity needs to be at least 1.");
    }

    // set new capacity
    this.capacity = newCapacity;
  }

  /**
   * Creates and returns and instance of a TicketQueueIterator for this queue.
   */
  public Iterator<TicketSiteUser> iterator() {
    return new TicketQueueIterator(this);
  }

  /**
   * Creates and returns a deep copy (not the deepest copy) of this TicketQueue.
   * 
   * @return a deep copy of the TicketQueue
   */
  public TicketQueue deepCopy() {
    // create deep copy
    TicketQueue deepCopy = new TicketQueue(capacity);

    LinkedNode<TicketSiteUser> currentNode = this.front;
    while (currentNode != null) {
      TicketSiteUser userCopy = currentNode.getData();
      // add user to back of queue
      deepCopy.enqueue(userCopy);
      currentNode = currentNode.getNext();
    }

    // return deep copy of queue
    return deepCopy;
  }

  /**
   * Implementation provided; see writeup.
   */
  @Override
  public String toString() {
    String s = "";
    LinkedNode<TicketSiteUser> runner = this.front;
    while (runner != null) {
      s += runner.getData() + "\n";
      runner = runner.getNext();
    }
    return s;
  }

  /**
   * Adds the given TicketSiteUser to the back of the queue.
   * 
   * @param newObject - element to add at the back (end) of the queue
   * @throws IllegalStateException    - if the queue is full
   * @throws IllegalArgumentException - if the TicketSite user is not able to buy a ticket.
   */
  public void enqueue(TicketSiteUser newObject) {
    // exception for full queue
    if (size == capacity) {
      throw new IllegalStateException("The queue is full.");
    }

    // exception for user cannot buy ticket
    if (!newObject.canBuyTicket()) {
      throw new IllegalArgumentException("TicketSiteUser cannot buy ticket.");
    }

    LinkedNode<TicketSiteUser> nextNode = new LinkedNode<TicketSiteUser>(newObject);
    if (isEmpty()) {
      // no nodes, nextNode is the front
      front = nextNode;
    } else {
      // add nextNode to the back
      back.setNext(nextNode);
    }

    // update end node pointer
    back = nextNode;
    // increment size with added node
    size++;
  }
}
