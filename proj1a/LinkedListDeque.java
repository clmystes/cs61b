public class LinkedListDeque<Item> implements Deque<Item> {
    private class Node {
        Node prev;
        Node next;
        Item item;

        public Node(Node p, Node n, Item i) {
            prev = p;
            next = n;
            item = i;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    @Override
    public void addFirst(Item item) {
        Node newFirst = new Node(sentinel, sentinel.prev, item);
        sentinel.prev.prev = newFirst;
        sentinel.prev = newFirst;
        size += 1;
    }

    @Override
    public void addLast(Item item) {
        Node newLast = new Node(sentinel.next, sentinel, item);
        sentinel.next.next = newLast;
        sentinel.next = newLast;
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node cur = sentinel.prev;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size; i++) {
            str.append(cur.item).append(" ");
            cur = cur.next;
        }
        System.out.println(str);
    }

    @Override
    public Item removeFirst() {
        Item result = sentinel.prev.item;
        Node second = sentinel.prev.next;
        second.prev = sentinel;
        sentinel.prev = second;
        size -= 1;
        return result;
    }

    @Override
    public Item removeLast() {
        Item result = sentinel.next.item;
        Node ls = sentinel.next.prev;
        ls.next = sentinel;
        sentinel.next = ls;
        size -= 1;
        return result;
    }

    @Override
    public Item get(int index) {
        Node cur = sentinel.prev;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.item;
    }

    public Item getRecursive(int index) {
        return getRecursive(index, sentinel.prev);
    }

    private Item getRecursive(int index, Node n) {
        if (index == 0) {
            return n.item;
        }
        return getRecursive(index - 1, n.next);
    }
}
