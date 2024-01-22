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
// Online Sources: I used the javadocs of TicketQueueTester
//////////////// (https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2023/fall/p08/doc
//////////////// /TicketQueueTester.html) to help make the methods in this class
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A series of static tester methods to check the correctness of the TicketQueue and the
 * TicketQueueIterator.
 */
public class TicketQueueTester {

  /**
   * Checks the correctness of the TicketQueue's peek() method, including case(s) where it should
   * throw exceptions.
   * 
   * @return true if all test cases pass, false otherwise
   */
  public static boolean testPeek() {
    boolean testsPassed = true;

    // create new ticket queue
    TicketQueue queue = new TicketQueue(3);

    // check queue with empty queue
    try {
      queue.peek();
      testsPassed = false;
      return testsPassed;
    } catch (NoSuchElementException e) {
      // handle exception
    } catch (Exception e) {
      // handle exception
      return false;
    }

    // create user for queue
    // test for one user
    TicketSiteUser firstUser = new TicketSiteUser("User1", "123456789", "1324658790137824");
    firstUser.login("User1", "123456789");
    queue.enqueue(firstUser);
    TicketSiteUser expectedUser = firstUser;
    String expectedString = "User1: *\n";

    if (!expectedUser.equals(queue.peek()) || !expectedString.equals(queue.toString())
        || queue.isEmpty()) {
      return false;
    }

    // create user for queue
    // test for users to not change queue
    TicketSiteUser secondUser = new TicketSiteUser("User2", "098765432", "9387291772837239");
    secondUser.login("User2", "098765432");
    queue.enqueue(secondUser);
    TicketSiteUser expectedUser2 = firstUser;
    String expectedString2 = "User1: *\nUser2: *\n";

    if (!expectedUser2.equals(queue.peek()) || !expectedString2.equals(queue.toString())
        || queue.isEmpty()) {
      return false;
    }
    // return true is tests pass
    return true;
  }

  /**
   * Checks the correctness of the TicketQueue's enqueue() method, including case(s) where it should
   * throw exceptions.
   * 
   * @return true if all test cases pass, false otherwise
   */
  public static boolean testEnqueue() {
    // create new ticket queue
    // test for adding users to queue
    TicketQueue queue = new TicketQueue(3);
    // add user
    TicketSiteUser firstUser = new TicketSiteUser("User1", "123456789", "1324658790137824");
    firstUser.login("User1", "123456789");
    queue.enqueue(firstUser);
    int expectedSize = 1;
    String expectedString = "User1: *\n";

    if (expectedSize != queue.size() || !queue.toString().equals(expectedString)
        || queue.isEmpty()) {
      return false;
    }

    // test for adding users to queue with users
    // add second user
    TicketSiteUser secondUser = new TicketSiteUser("User2", "098765432", "9387291772837239");
    secondUser.login("User2", "098765432");
    queue.enqueue(secondUser);
    int expectedSize2 = 2;
    String expectedString2 = "User1: *\nUser2: *\n";

    if (expectedSize2 != queue.size() || !queue.toString().equals(expectedString2)
        || queue.isEmpty()) {
      return false;
    }

    // try to add person who cannot buy ticket
    try {
      TicketSiteUser cannotBuy = new TicketSiteUser("cannotBuy", "317824398", "198214971");
      queue.enqueue(cannotBuy);
      return false;
    } catch (IllegalArgumentException e) {
      // handle exception
    } catch (Exception e) {
      // handle exception
      return false;
    }

    // return true if tests pass
    return true;
  }

  /**
   * Checks the correctness of the TicketQueue's dequeue() method, including case(s) where it should
   * throw exceptions.
   * 
   * @return true if all test cases pass, false otherwise
   */
  public static boolean testDequeue() {
    // create new ticket queue
    // test for dequeue
    TicketQueue queue = new TicketQueue(3);
    // add users
    TicketSiteUser firstUser = new TicketSiteUser("User1", "123456789", "1324658790137824");
    firstUser.login("User1", "123456789");
    queue.enqueue(firstUser);
    int expectedSize = 0;
    String expectedString = "";
    TicketSiteUser expectedUser = firstUser;

    if (!expectedUser.equals(queue.dequeue()) || expectedSize != queue.size()
        || !expectedString.equals(queue.toString()) || !queue.isEmpty()) {
      System.out.println(queue.toString());
      System.out.println(queue.size());
      return false;
    }

    // check for dequeue with empty queue
    try {
      TicketQueue queue2 = new TicketQueue(3);
      queue2.dequeue();
      return false;
    } catch (NoSuchElementException e) {
      // handle exception
    } catch (Exception e) {
      // handle exception
      return false;
    }

    // return true if all tests pass
    return true;
  }

  /**
   * Checks the correctness of the TicketQueue's constructor, including case(s) where it should
   * throw exceptions. Also checks the correctness of isEmpty(), isFull(), size(), capacity(), and
   * toString() on a newly created TicketQueue.
   * 
   * @return true if all test cases pass, false otherwise
   */
  public static boolean testConstructor() {
    boolean testsPassed = true;
    // create queue with bas capacity
    try {
      TicketQueue badCapacity = new TicketQueue(0);
      testsPassed = false;
      return testsPassed;
    } catch (IllegalArgumentException e) {
      // handle exception
    } catch (Exception e) {
      // handle exception
      return false;
    }

    // create new ticket queue
    TicketQueue queue = new TicketQueue(2);
    if (queue.size() != 0 || !queue.isEmpty() || queue.isFull() || queue.capacity() != 2
        || !queue.toString().equals("")) {
      testsPassed = false;
      return testsPassed;
    }
    return testsPassed;

  }

  /**
   * Checks the correctness of the TicketQueueIterator method(s) and iterating through a
   * TicketQueue. See write-up for more details on how to write this test. You DO NOT need to test
   * if the TicketQueueIterator constructor throws an exception when the queue parameter is null.
   * 
   * @return true if all test cases pass, false otherwise
   */
  public static boolean testIterator() {
    boolean testsPassed = true;
    // create new ticket queue
    TicketQueue queue = new TicketQueue(3);
    // add users
    TicketSiteUser firstUser = new TicketSiteUser("User1", "123456789", "1324658790137824");
    firstUser.login("User1", "123456789");
    queue.enqueue(firstUser);
    TicketSiteUser secondUser = new TicketSiteUser("User2", "098765432", "9387291772837239");
    secondUser.login("User2", "098765432");
    queue.enqueue(secondUser);
    TicketSiteUser thirdUser = new TicketSiteUser("User3", "212345712", "4283476123098742");
    thirdUser.login("User3", "212345712");
    queue.enqueue(thirdUser);

    // create iterator for queue
    try {
      Iterator<TicketSiteUser> run = queue.iterator();
      TicketSiteUser[] expectedNext = {firstUser, secondUser, thirdUser};
      int runs = 0;
      int expectedRuns = 3;
      for (TicketSiteUser person : expectedNext) {
        runs++;
        if (!run.hasNext()) {
          testsPassed = false;
          return testsPassed;
        }
        if (!person.equals(run.next())) {
          testsPassed = false;
          return testsPassed;
        }
      }

      if (expectedRuns != runs || queue.size() != 3) {
        testsPassed = false;
        return testsPassed;
      }
    } catch (NoSuchElementException e) {
      // handle exception
      return true;
    }
    // return true if tests pass
    return testsPassed;
  }

  /**
   * Prints out the results of all of the testers for this project
   * 
   * @return true if all test cases pass, false otherwise
   */
  private static boolean runAllTests() {
    // tests methods
    System.out.println(testPeek());
    System.out.println(testEnqueue());
    System.out.println(testDequeue());
    System.out.println(testConstructor());
    System.out.println(testIterator());
    return true;
  }
}
