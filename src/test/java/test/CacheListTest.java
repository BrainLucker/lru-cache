package test;

import code.LinkedCacheList;
import code.ListItem;

import static code.ListItem.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CacheListTest {

    LinkedCacheList list;

    @BeforeEach
    public void setUp() {
        list = new LinkedCacheList();
    }

    @Test
    public void shouldCreateEmptyList() {
        int expectedLength = 0;

        int actualLength = list.len();

        assertEquals(expectedLength, actualLength);
        assertNull(list.front());
        assertNull(list.back());
    }

    @Test
    public void shouldPushFront() {
        Item item = new Item("Value", new Key("Key"));
        list.pushFront(item);
        int expectedLength = 1;

        int actualLength = list.len();
        Item actualFrontItem = list.front().getItem();
        Item actualBackItem = list.back().getItem();

        assertEquals(expectedLength, actualLength);
        assertEquals(item, actualFrontItem);
        assertEquals(item, actualBackItem);
    }

    @Test
    public void shouldPushBack() {
        Item item = new Item("Value", new Key("Key"));
        list.pushBack(item);
        int expectedLength = 1;

        int actualLength = list.len();
        Item actualFrontItem = list.front().getItem();
        Item actualBackItem = list.back().getItem();

        assertEquals(expectedLength, actualLength);
        assertEquals(item, actualFrontItem);
        assertEquals(item, actualBackItem);
    }

    @Test
    public void shouldRemoveLastItem() {
        Item item = new Item("Value", new Key("Key"));
        int expectedLength = 0;

        list.pushFront(item);
        ListItem front = list.front();
        list.remove(front);
        int actualLength = list.len();

        assertEquals(expectedLength, actualLength);
        assertNull(list.front());
        assertNull(list.back());
    }

    @Test
    public void shouldRemoveFrontItem() {
        Item item1 = new Item("Value1", new Key("Key"));
        Item item2 = new Item("Value2", new Key("Key"));
        int expectedLength = 1;

        list.pushFront(item1);
        list.pushBack(item2);
        ListItem front = list.front();
        list.remove(front);

        int actualLength = list.len();
        Item actualFrontItem = list.front().getItem();
        Item actualBackItem = list.back().getItem();

        assertEquals(expectedLength, actualLength);
        assertEquals(item2, actualFrontItem);
        assertEquals(item2, actualBackItem);
    }

    @Test
    public void shouldRemoveBackItem() {
        Item item1 = new Item("Value1", new Key("Key"));
        Item item2 = new Item("Value2", new Key("Key"));
        int expectedLength = 1;

        list.pushFront(item1);
        list.pushBack(item2);
        ListItem back = list.back();
        list.remove(back);

        int actualLength = list.len();
        Item actualFrontItem = list.front().getItem();
        Item actualBackItem = list.back().getItem();

        assertEquals(expectedLength, actualLength);
        assertEquals(item1, actualFrontItem);
        assertEquals(item1, actualBackItem);
    }

    @Test
    public void shouldRemoveMiddleItem() {
        Item item1 = new Item("Value1", new Key("Key"));
        Item item2 = new Item("Value2", new Key("Key"));
        Item item3 = new Item("Value3", new Key("Key"));
        int expectedLength = 2;

        list.pushFront(item1);
        list.pushBack(item2);
        ListItem middle = list.back();
        list.pushBack(item3);
        list.remove(middle);

        int actualLength = list.len();
        Item actualFrontItem = list.front().getItem();
        Item actualBackItem = list.back().getItem();

        assertEquals(expectedLength, actualLength);
        assertEquals(item1, actualFrontItem);
        assertEquals(item3, actualBackItem);
    }

    @Test
    public void pushEqualItems() {
        Item item = new Item("Value1", new Key("Key"));
        int expectedLength = 2;

        list.pushFront(item);
        list.pushFront(item);

        int actualLength = list.len();
        Item actualFrontItem = list.front().getItem();
        Item actualBackItem = list.back().getItem();

        assertEquals(expectedLength, actualLength);
        assertEquals(item, actualFrontItem);
        assertEquals(item, actualBackItem);
    }

    @Test
    public void complexTest() {
        Item item1 = new Item(10, new Key("1"));
        Item item2 = new Item(20, new Key("2"));
        Item item3 = new Item(30, new Key("3"));

        list.pushFront(item1);  // [10]
        list.pushBack(item2);   // [10, 20]
        list.pushBack(item3);   // [10, 20, 30]
        assertEquals(3, list.len());

        ListItem middle = list.front().getNext();   // [20]
        list.remove(middle);                        // [10, 30]
        assertEquals(2, list.len());

        for (int i = 40; i < 90; i += 10) {
            Item item = new Item(i, new Key(String.valueOf(i)));
            if (i % 20 == 0) {
                list.pushFront(item);
            } else {
                list.pushBack(item);
            }
        }                               // [80, 60, 40, 10, 30, 50, 70]

        assertEquals(7, list.len());
        assertEquals(80, list.front().getValue());
        assertEquals(70, list.back().getValue());

        list.moveToFront(list.front()); // [80, 60, 40, 10, 30, 50, 70]
        list.moveToFront(list.back());  // [70, 80, 60, 40, 10, 30, 50]

        int[] expected = {70, 80, 60, 40, 10, 30, 50};
        int[] actual = new int[list.len()];
        ListItem li = list.front();
        for (int i = 0; i < list.len(); i++) {
            actual[i] = (int) li.getValue();
            li = li.getNext();
        }
        assertArrayEquals(actual, expected);
    }

}