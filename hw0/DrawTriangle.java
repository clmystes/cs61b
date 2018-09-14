public class DrawTriangle {
    public static void main(String[] args) {
        int MAX = 5;
        int n = 1;

        while (n <= MAX) {
            int tn = n;
            while (tn > 0) {
                System.out.print("*");
                tn--;
            }
            System.out.print("\n");
            n++;
        }
    }
}