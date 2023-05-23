package code;

import code.ListItem.Item;

public class LinkedCacheList implements CacheList {

    private final ListItem first = new ListItem();
    private final ListItem last = new ListItem();
    private int length = 0;

    @Override
    public int len() {
        return length;
    }

    @Override
    public ListItem front() {
        return first.getNext();
    }

    @Override
    public ListItem back() {
        return last.getPrev();
    }

    @Override
    public ListItem pushFront(Item item) {
        ListItem nextItem = first.getNext();
        ListItem newItem = new ListItem(item, nextItem, first);

        if (nextItem == null) {
            newItem.setNext(last);
            last.setPrev(newItem);
        } else {
            nextItem.setPrev(newItem);
        }
        first.setNext(newItem);
        length++;

        return newItem;
    }

    @Override
    public ListItem pushBack(ListItem.Item item) {
        ListItem prevItem = last.getPrev();
        ListItem newItem = new ListItem(item, last, prevItem);

        if (prevItem == null) {
            newItem.setPrev(first);
            first.setNext(newItem);
        } else {
            prevItem.setNext(newItem);
        }
        last.setPrev(newItem);
        length++;

        return newItem;
    }

    @Override
    public void remove(ListItem item) {
        ListItem prevItem = item.getPrev();
        ListItem nextItem = item.getNext();
        prevItem.setNext(len() == 1 ? null : nextItem);
        nextItem.setPrev(len() == 1 ? null : prevItem);
        length--;
    }

    @Override
    public void moveToFront(ListItem item) {
        if (length > 1) {
            remove(item);
            ListItem nextItem = first.getNext();
            nextItem.setPrev(item);
            first.setNext(item);
            item.setPrev(first);
            item.setNext(nextItem);
            length++;
        }
    }
}