@SuppressWarnings({ "rawtypes", "unchecked" })
public class Urejanje {
    public static void bubbleSort(Comparable[] a) {
        int n = a.length;
        int zadnjaZamenjava = n - 1;
        for (int i = 0; i < n - 1; i++) {
            boolean zamenjano = false;
            int novaZadnjaZamenjava = 0;
            for (int j = 0; j < zadnjaZamenjava; j++) {
                if (a[j].compareTo(a[j + 1]) > 0) {
                    Comparable temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    zamenjano = true;
                    novaZadnjaZamenjava = j;
                }
            }
            zadnjaZamenjava = novaZadnjaZamenjava;
            izpisiSled(a, zadnjaZamenjava);
            if (!zamenjano)
                break;
        }
    }

    private static void izpisiSled(Comparable[] a, int zadnjaZamenjava) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            if (i == zadnjaZamenjava)
                System.out.print(" |");
            if (i != a.length - 1)
                System.out.print(" ");
        }
        System.out.println();
    }
}
