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
// Online Sources: I used the javadocs for the TicketQueueIterator class
//////////////// (https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2023/fall/p08/doc
//////////////// /TicketQueueIterator.html) to help make the methods in this class
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator for a TicketQueue that keeps the original queue intact. This iterator will return
 * elements in the order of the queue from front to back.
 */
public class TicketQueueIterator implements Iterator<TicketSiteUser> {
  // data fields
  // deep copy of a TicketQueue
  private TicketQueue userQueue;

  /**
   * Constructor for a TicketQueueIterator that sets the data field to be a deep copy of the given
   * queue.
   * 
   * @param queue - the TicketQueue for this iterator to use
   * @throws IllegalArgumentException - if the queue is null
   */
  public TicketQueueIterator(TicketQueue queue) {
    // exception for when queue is null
    if (queue == null) {
      throw new IllegalArgumentException("The queue is null.");
    }

    // deep copy of given queue
    this.userQueue = queue.deepCopy();
  }

  /**
   * Determines whether or not there is another TicketSiteUser in the queue.
   * 
   * @return true if there are more TicketSiteUsers in the queue, false otherwise
   */
  public boolean hasNext() {
    // check for next user
    if (userQueue.isEmpty()) {
      return false;
    }
    // another user in queue
    return true;
  }

  /**
   * Returns the next TicketSiteUser in the queue, based on the order from front to back.
   * 
   * @return the next TicketSiteUser in the queue
   * @throws NoSuchElementException - if there are no more TicketSiteUsers in the queue.
   */
  public TicketSiteUser next() {
    if (!hasNext()) {
      throw new NoSuchElementException("No more users in queue.");
    }

    // return next user in queue
    return userQueue.dequeue();
  }
}
