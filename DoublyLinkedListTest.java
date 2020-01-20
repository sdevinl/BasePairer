import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class DoublyLinkedListTest {
    private DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
    private DoublyLinkedList<String> list2 = new DoublyLinkedList<>();
    private DoublyLinkedList<Integer> list3 = new DoublyLinkedList<>();

    @Before
    public void setUp() throws Exception {
        list.add(10);
        list.add(20);
        list.add(5);

        list2.add("Hello");

    }

    @Test
    public void addBoolTest() {
        assertTrue(list.add(13));
        assertEquals(Integer.valueOf(13), list.get(3));
        try {
            list.add(null);
        } catch (NullPointerException e) {

        }
        assertTrue(list.add(24));
        assertTrue(list.contains(24));

        assertEquals("Hello", list2.get(0));
        assertTrue(list2.add("haha"));

    }

    @Test
    public void addIndexTest() {

        list.add(1, 3);
        assertEquals(Integer.valueOf(3), list.get(1));

        list.add(0, 2);
        assertEquals(Integer.valueOf(2), list.get(0));

        list.add(3, 9);
        assertEquals(Integer.valueOf(9), list.get(3));

        try {
            list.add(0, null);
        } catch (NullPointerException e) {

        }

        try {
            list.add(20, 111);
        } catch (IndexOutOfBoundsException e) {
        }

        list2.add(0, "GG");
        assertEquals("GG", list2.get(0));
        assertEquals("Hello", list2.get(1));


    }

    @After
    public void clearTest() {
        list.clear();
        assertEquals(0, list.size());
        assertNull(list.get(0));
        assertFalse(list.contains(null));


        list2.clear();
        assertEquals(0, list2.size());
        assertNull(list2.get(0));
        assertFalse(list2.contains(null));

        list3.clear();
        assertEquals(0, list3.size());
    }

    @Test
    public void containsTest() {
        list.add(12);
        assertTrue(list.contains(12));

        list2.add("Helo");
        assertTrue(list2.contains("Helo"));

        list3.add(12);
        list3.remove(0);
        assertFalse(list3.contains(12));
    }

    @Test
    public void getTest() {
        assertEquals(Integer.valueOf(10), list.get(0));
        assertEquals("Hello", list2.get(0));

        try {
            list2.get(20);
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            list2.get(-1);
        } catch (IndexOutOfBoundsException e) {

        }

        assertEquals(Integer.valueOf(20), list.get(1));

    }

    @Test
    public void isEmptyTest() {
        assertFalse(list2.isEmpty());
        assertFalse(list.isEmpty());
        assertTrue(list3.isEmpty());

    }

    @Test
    public void removeTest() {
        assertEquals(Integer.valueOf(10), list.remove(0));
        assertEquals(Integer.valueOf(20), list.get(0));
        assertEquals("Hello", list2.remove(0));
        assertTrue(list3.isEmpty());
        list3.add(10);
        list3.add(20);
        list3.add(30);
        assertEquals(Integer.valueOf(20), list3.remove(1));
        assertEquals(Integer.valueOf(10), list3.get(0));
        assertEquals(Integer.valueOf(30), list3.get(1));

        try {
            list3.remove(200);
        } catch (IndexOutOfBoundsException e) {

        }

    }

    @Test
    public void setTest() {
        list3.add(11);
        list3.add(22);
        list3.add(33);

        assertEquals(Integer.valueOf(11), list3.set(0, 300));
        assertEquals(Integer.valueOf(300), list3.get(0));
        assertEquals(Integer.valueOf(22), list3.set(1, 2223));
        assertEquals(Integer.valueOf(2223), list3.get(1));

        try {
            list3.set(1, null);
        } catch (NullPointerException e) {

        }

        try {
            list3.set(-2302, 20);
        } catch (IndexOutOfBoundsException e) {

        }

    }

    @Test
    public void sizeTest() {
        assertEquals(3, list.size());
        assertEquals(1, list2.size());
        assertEquals(0, list3.size());

        list.remove(0);
        assertEquals(2, list.size());
        list.set(0, 200);
        assertEquals(2, list.size());
        list.add(3);
        assertEquals(3, list.size());
        list.add(1, 1);
        assertEquals(4, list.size());
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void spliceTest() {
        list3.add(7);
        list3.add(2);
        list.splice(1, list3);
        assertEquals(Integer.valueOf(7), list.get(1));
        assertEquals(Integer.valueOf(2), list.get(2));
        assertEquals(Integer.valueOf(20), list.get(3));
        assertEquals(Integer.valueOf(10), list.get(0));
        assertEquals(Integer.valueOf(5), list.get(4));

    }

    @Test
    public void matchTest(){
        list3.add(1);
        list3.add(10);
        list3.add(5);
        list3.add(10); //match
        list3.add(20);
        list3.add(5);
        list3.add(10); // match
        list3.add(20);
        list3.add(5);
        list3.add(10);
        list3.add(10); // match
        list3.add(20);
        list3.add(5);
        list3.add(0);
        list3.add(10); // match
        list3.add(20);
        list3.add(5);

        int[] matches = new int[] {3, 6, 10, 14};

        assertEquals(matches[0], list3.match(list)[0]);
        assertEquals(matches[1], list3.match(list)[1]);
        assertEquals(matches[2], list3.match(list)[2]);
        assertEquals(matches[3], list3.match(list)[3]);

    }

}