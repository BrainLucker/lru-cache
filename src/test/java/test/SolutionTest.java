package test;

import code.LRUCache;
import code.LinkedCacheList;
import code.ListItem;

import static code.ListItem.*;
import static org.junit.jupiter.api.Assertions.*;

import code.Solution;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.Array;
import java.util.ArrayList;

class SolutionTest {
    private static Item test = new Item("Test", new Key("Key"));

    @BeforeEach
    public void setUp() {
    }

//    @ParameterizedTest
//    @CsvSource({
//            "Regular string, aaaaYniuiiiipopo, a4Yniui4popo",
//            "Escape chars, \\4\\5aaaa\\\\bbbb, 45a4\\b4"
//    })

    @Test
    public void shouldCreateEmptyList() {
        LinkedCacheList cacheList = new LinkedCacheList();
        int expectedLength = 0;

        int actualLength = cacheList.len();

        assertEquals(expectedLength, actualLength);
        assertNull(cacheList.front());
        assertNull(cacheList.back());
    }

    @Test
    public void shouldPushFront() {
        LinkedCacheList cacheList = new LinkedCacheList();
        Item item = new Item("Value", new Key("Key"));
        cacheList.pushFront(item);
        int expectedLength = 1;

        int actualLength = cacheList.len();
        Item actualFrontItem = cacheList.front().getItem();
        Item actualBackItem = cacheList.back().getItem();

        assertEquals(expectedLength, actualLength);
        assertEquals(item, actualFrontItem);
        assertEquals(item, actualBackItem);
    }

    @Test
    public void shouldPushBack() {
        LinkedCacheList cacheList = new LinkedCacheList();
        Item item = new Item("Value", new Key("Key"));
        cacheList.pushBack(item);
        int expectedLength = 1;

        int actualLength = cacheList.len();
        Item actualFrontItem = cacheList.front().getItem();
        Item actualBackItem = cacheList.back().getItem();

        assertEquals(expectedLength, actualLength);
        assertEquals(item, actualFrontItem);
        assertEquals(item, actualBackItem);
    }

    @Test
    public void shouldRemoveLastItem() {
        LinkedCacheList cacheList = new LinkedCacheList();
        Item item = new Item("Value", new Key("Key"));
        int expectedLength = 0;

        cacheList.pushFront(item);
        ListItem front = cacheList.front();
        cacheList.remove(front);
        int actualLength = cacheList.len();

        assertEquals(expectedLength, actualLength);
        assertNull(cacheList.front());
        assertNull(cacheList.back());
    }

    @Test
    public void shouldRemoveFrontItem() {
        LinkedCacheList cacheList = new LinkedCacheList();
        Item item1 = new Item("Value1", new Key("Key"));
        Item item2 = new Item("Value2", new Key("Key"));
        int expectedLength = 1;

        cacheList.pushFront(item1);
        cacheList.pushBack(item2);
        ListItem front = cacheList.front();
        cacheList.remove(front);

        int actualLength = cacheList.len();
        Item actualFrontItem = cacheList.front().getItem();
        Item actualBackItem = cacheList.back().getItem();

        assertEquals(expectedLength, actualLength);
        assertEquals(item2, actualFrontItem);
        assertEquals(item2, actualBackItem);
    }

    @Test
    public void shouldRemoveBackItem() {
        LinkedCacheList cacheList = new LinkedCacheList();
        Item item1 = new Item("Value1", new Key("Key"));
        Item item2 = new Item("Value2", new Key("Key"));
        int expectedLength = 1;

        cacheList.pushFront(item1);
        cacheList.pushBack(item2);
        ListItem back = cacheList.back();
        cacheList.remove(back);

        int actualLength = cacheList.len();
        Item actualFrontItem = cacheList.front().getItem();
        Item actualBackItem = cacheList.back().getItem();

        assertEquals(expectedLength, actualLength);
        assertEquals(item1, actualFrontItem);
        assertEquals(item1, actualBackItem);
    }

    @Test
    public void shouldRemoveMiddleItem() {
        LinkedCacheList cacheList = new LinkedCacheList();
        Item item1 = new Item("Value1", new Key("Key"));
        Item item2 = new Item("Value2", new Key("Key"));
        Item item3 = new Item("Value3", new Key("Key"));
        int expectedLength = 2;

        cacheList.pushFront(item1);
        cacheList.pushBack(item2);
        ListItem middle = cacheList.back();
        cacheList.pushBack(item3);
        cacheList.remove(middle);

        int actualLength = cacheList.len();
        Item actualFrontItem = cacheList.front().getItem();
        Item actualBackItem = cacheList.back().getItem();

        assertEquals(expectedLength, actualLength);
        assertEquals(item1, actualFrontItem);
        assertEquals(item3, actualBackItem);
    }

    @Test
    public void pushEqualItems() {
        LinkedCacheList cacheList = new LinkedCacheList();
        Item item = new Item("Value1", new Key("Key"));
        int expectedLength = 2;

        cacheList.pushFront(item);
        cacheList.pushFront(item);

        int actualLength = cacheList.len();
        Item actualFrontItem = cacheList.front().getItem();
        Item actualBackItem = cacheList.back().getItem();

        assertEquals(expectedLength, actualLength);
        assertEquals(item, actualFrontItem);
        assertEquals(item, actualBackItem);
    }

    @Test
    public void should() {
        LinkedCacheList list = new LinkedCacheList();
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
        }   // [80, 60, 40, 10, 30, 50, 70]

        assertEquals(7, list.len());
        assertEquals(80, list.front().getValue());
        assertEquals(70, list.back().getValue());

        list.moveToFront(list.front()); // [80, 60, 40, 10, 30, 50, 70]
        list.moveToFront(list.back());  // [70, 80, 60, 40, 10, 30, 50]

        int[] v = new int[list.len()];
        int i = 0;
        for (ListItem li = list.front(); li != null; li = li.getNext()) {
            v[i] = (int) li.getValue();
            i++;
            System.out.println((int) li.getValue());
        }

        assertArrayEquals(v, new int[]{70, 80, 60, 40, 10, 30, 50});
    }

}