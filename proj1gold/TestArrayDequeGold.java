import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void randomTest() {
        StudentArrayDeque<Integer> tested = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> good = new ArrayDequeSolution<>();

        // addFirst
        int random = StdRandom.uniform(100);
        tested.addFirst(random);
        good.addFirst(random);
        assertEquals("addFirst("+random+")", good.get(0), tested.get(0));
        System.out.println("addFirst("+random+")");

        // addLast
        random = StdRandom.uniform(100);
        tested.addLast(random);
        good.addLast(random);
        assertEquals("addLast("+random+")", good.get(1), tested.get(1));
        System.out.println("addLast("+random+")");

        // removeFirst
        assertEquals("removeFirst()", good.removeFirst(), tested.removeFirst());
        System.out.println("removeFirst()");

        // removeLast
        assertEquals("removeLast()", good.removeLast(), tested.removeLast());
        System.out.println("removeLast()");
    }
}
