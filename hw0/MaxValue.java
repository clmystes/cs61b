public class MaxValue {
    /** Returns the maximum value from m. */
    public static int max(int[] m) {
        int len = m.length;
        int n = 0;
        int maxValue = 0;

        while (n < len) {
           if (maxValue < m[n]) {
               maxValue = m[n];
           }
           n++;
        }

        return maxValue;
    }

    public static int forMax(int[] m) {
        int maxValue = 0;

        for (int i = 0; i < m.length; i++) {
            if (maxValue < m[i]) {
                maxValue = m[i];
            }
        }

        return maxValue;
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        System.out.print(max(numbers));
        System.out.print("\n");
        System.out.print(forMax(numbers));
    }
}