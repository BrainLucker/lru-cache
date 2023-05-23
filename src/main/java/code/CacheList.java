package code;

import code.ListItem.Item;

public interface CacheList {
    int len();

    ListItem front();

    ListItem back();

    ListItem pushFront(Item item);

    ListItem pushBack(Item item);

    void remove(ListItem item);

    void moveToFront(ListItem item);
}