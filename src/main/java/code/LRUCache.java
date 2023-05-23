package code;

import code.ListItem.*;

import java.util.HashMap;

public class LRUCache {

    private CacheList list;
    private HashMap<Key, ListItem> map;
    private int size;

    public LRUCache(int n) {
        list = new LinkedCacheList();
        map = new HashMap<>();
        size = n;
    }

    boolean set(Key key, Object value) {
        boolean isContained = map.containsKey(key);

        if (isContained) {
            ListItem li = map.get(key);
            li.setValue(value);
            list.moveToFront(li);
        } else {
            Item item = new Item(value, key);

            if (list.len() >= size) {
                ListItem last = list.back();
                list.remove(last);
                map.remove(last.getKey());
            }

            ListItem li = list.pushFront(item);
            map.put(key, li);
        }

        return isContained;
    }

    Object get(Key key) {
        if (!map.containsKey(key)) {
            return null;
        } else {
            ListItem li = map.get(key);
            list.moveToFront(li);
            return li.getValue();
        }
    }

    void clear() {
        list = new LinkedCacheList();
        map.clear();
    }

}