public class ArrayDeque<Item> implements Deque<Item> {
    private Item[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private static final int INITIAL_CAPACITY = 8;

    public ArrayDeque() {
        size = 0;
        nextFirst = 0;
        nextLast = 1;
        items = (Item[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void addFirst(Item item) {
        if (isFull()) {
            extend();
        }
        items[nextFirst] = item;
        // 保证 nextFirst 不会越界
        nextFirst = minusOne(nextFirst);
        size++;
    }

    @Override
    public void addLast(Item item) {
        if (isFull()) {
            extend();
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;
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
        for (int i = plusOne(nextFirst); i != nextLast; i = plusOne(i)) {
            System.out.print(items[i] + " ");
        }
    }

    @Override
    public Item removeFirst() {
        nextFirst = plusOne(nextFirst);
        Item r = items[nextFirst];
        items[nextFirst] = null;
        size--;

        if (isSparse()) {
            shrink();
        }
        return r;
    }

    @Override
    public Item removeLast() {
        nextLast = minusOne(nextLast);
        Item r = items[nextLast];
        items[nextLast] = null;
        size--;

        if (isSparse()) {
            shrink();
        }
        return r;
    }

    @Override
    public Item get(int index) {
        Item item = null;
        for (int i = nextFirst; i < index; i++) {
            item = items[i];
        }
        return item;
    }

    private boolean isFull() {
        return size == items.length;
    }

    private void extend() {
        resize(size * 2);
    }

    private int minusOne(int n) {
        return Math.floorMod(n - 1, items.length);
    }

    private int plusOne(int n) {
        return Math.floorMod(n + 1, items.length);
    }

    private void resize(int n) {
        int from = plusOne(nextFirst);
        int to = minusOne(nextLast);
        Item[] r = (Item[]) new Object[n];
        for (int i = from; i != to; plusOne(i)) {
            r[i] = items[i];
        }
        items = r;
    }

    private void shrink() {
        resize(items.length / 2);
    }

    private boolean isSparse() {
        return items.length >= 16 && size() < items.length / 4;
    }
}
