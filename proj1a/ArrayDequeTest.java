import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void addRemoveTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();

        assertTrue(lld1.isEmpty());

        lld1.addFirst(10);
        assertFalse(lld1.isEmpty());

        lld1.removeFirst();
        assertTrue(lld1.isEmpty());
    }

    @Test
    public void addIsEmptySizeTest() {
        ArrayDeque<String> lld1 = new ArrayDeque<String>();
        assertTrue(lld1.isEmpty());

        lld1.addFirst("front");
        assertEquals(1, lld1.size());
        assertFalse(lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());
    }
}
