public class Izziv1 {
    public static void main(String[] args) {
        System.out.println("    n   |   linearno     |   dvojisko     ");
        System.out.println("--------+----------------+----------------");
        for (int i = 20000; i <= 1000000; i += 20000) {
            System.out.println(i + "\t|\t" + timeLinear(i) + "\t|\t" + timeBin(i));
        }
    }

    private static int timeBin(int i) {
        int[] a = generateTable(i);
        long startTime = System.nanoTime();
        for (int x = 0; x < 1000; x++) {
            int v = (int) (Math.random() * i) + 1;
            findBinary(a, 0, i - 1, v);
        }
        long executionTime = (System.nanoTime() - startTime);
        return (int) (executionTime / 1000);
    }

    private static int timeLinear(int i) {
        int[] a = generateTable(i);
        long startTime = System.nanoTime();
        for (int x = 0; x < 1000; x++) {
            int v = (int) (Math.random() * i) + 1;
            findLinear(a, v);
        }

        long executionTime = System.nanoTime() - startTime;
        return (int) (executionTime / 1000);
    }

    private static int[] generateTable(int n) {
        int[] t = new int[n];
        for (int i = 0; i < n; i++) {
            t[i] = i + 1;
        }
        return t;
    }

    private static int findLinear(int[] a, int v) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == v)
                return i;
        }
        return -1;
    }

    private static int findBinary(int[] a, int l, int r, int v) {
        if (l > r)
            return -1;
        int mid = (l + r) / 2;
        if (v < a[mid])
            return findBinary(a, l, mid - 1, v); // iskanje v levem delu
        if (v > a[mid])
            return findBinary(a, mid + 1, r, v); // iskanje v desnem delu
        return mid;
    }
}
