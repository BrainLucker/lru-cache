package code;

public class ListItem {
    private Item itemValue;
    private ListItem next;
    private ListItem prev;

    public ListItem() {
    }

    public ListItem(Item value, ListItem next, ListItem prev) {
        this.itemValue = value;
        this.next = next;
        this.prev = prev;
    }

    public Object getValue() {
        return itemValue.getValue();
    }

    public Item getItem() {
        return itemValue;
    }

    public void setValue(Object value) {
        this.itemValue.setValue(value);
    }

    public Key getKey() {
        return itemValue.getKey();
    }

    public ListItem getNext() {
        return next;
    }

    public void setNext(ListItem next) {
        this.next = next;
    }

    public ListItem getPrev() {
        return prev;
    }

    public void setPrev(ListItem prev) {
        this.prev = prev;
    }

    public static class Item {
        private Object value;
        private final Key key;

        public Item(Object value, Key key) {
            this.value = value;
            this.key = key;
        }

        public Key getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }

    public static class Key {
        private final String value;

        public Key(String key) {
            this.value = key;
        }

        public String getValue() {
            return value;
        }
    }

}