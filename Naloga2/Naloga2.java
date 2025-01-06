import java.util.Scanner;

class ResizableArray {
    private int[] array;
    private int size;

    public ResizableArray() {
        this.array = new int[10];
        this.size = 0;
    }

    public void add(int value) {
        if (size == array.length) {
            resize();
        }
        array[size++] = value;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    public void set(int index, int value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = value;
    }

    public int size() {
        return size;
    }

    private void resize() {
        int[] newArray = new int[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    public int[] toArray() {
        int[] result = new int[size];
        System.arraycopy(array, 0, result, 0, size);
        return result;
    }

    public void izpis() {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                System.out.print(array[i] + " ");
            }
        }
    }
}

public class Naloga2 {
    private static int premiki = 0;
    private static int primerjave = 0;
    private static String mode;
    private static String method;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] settings = scanner.nextLine().split("\\s+");
        mode = settings[0];
        method = settings[1];
        String direction = settings[2];
        String[] inputNumbers = scanner.nextLine().split("\\s+");
        scanner.close();
        ResizableArray numbers = new ResizableArray();
        for (String num : inputNumbers) {
            if (num != "")
                numbers.add(Integer.parseInt(num));
        }

        int[] array = numbers.toArray();
        boolean ascending = direction.equalsIgnoreCase("up"); // true-up, false-down
        boolean trace = mode.equalsIgnoreCase("trace"); // true-trace, false-count

        switch (method) {
            case "insert":
                insertionSort(array, trace, ascending);
                if (!trace) {
                    System.out.print(premiki + " " + primerjave + " | ");
                    insertionSort(array, trace, ascending);
                    System.out.print(premiki + " " + primerjave + " | ");
                    insertionSort(array, trace, !ascending);
                    System.out.println(premiki + " " + primerjave + "");
                }
                break;
            case "select":
                selectionSort(array, trace, ascending);
                if (!trace) {
                    System.out.print(premiki + " " + primerjave + " | ");
                    selectionSort(array, trace, ascending);
                    System.out.print(premiki + " " + primerjave + " | ");
                    selectionSort(array, trace, !ascending);
                    System.out.println(premiki + " " + primerjave + "");
                }
                break;
            case "bubble":
                bubbleSort(array, trace, ascending);
                if (!trace) {
                    System.out.print(premiki + " " + primerjave + " | ");
                    bubbleSort(array, trace, ascending);
                    System.out.print(premiki + " " + primerjave + " | ");
                    bubbleSort(array, trace, !ascending);
                    System.out.println(premiki + " " + primerjave + "");
                }
                break;
            case "heap":
                heapSort(array, trace, ascending);
                if (!trace) {
                    System.out.print(premiki + " " + primerjave + " | ");
                    heapSort(array, trace, ascending);
                    System.out.print(premiki + " " + primerjave + " | ");
                    heapSort(array, trace, !ascending);
                    System.out.println(premiki + " " + primerjave + "");
                }
                break;
            case "merge":
                mergeSort(array, trace, ascending);
                if (!trace) {
                    System.out.print(premiki + " " + primerjave + " | ");
                    mergeSort(array, trace, ascending);
                    System.out.print(premiki + " " + primerjave + " | ");
                    mergeSort(array, trace, !ascending);
                    System.out.println(premiki + " " + primerjave + "");
                }
                break;
            case "quick":
                quickSort(array, trace, ascending);
                if (!trace) {
                    System.out.print(premiki + " " + primerjave + " | ");
                    quickSort(array, trace, ascending);
                    System.out.print(premiki + " " + primerjave + " | ");
                    quickSort(array, trace, !ascending);
                    System.out.println(premiki + " " + primerjave + "");
                }
                break;
            case "radix":
                radixSort(array, trace, ascending);
                if (!trace) {
                    System.out.print(premiki + " " + primerjave + " | ");
                    radixSort(array, trace, ascending);
                    System.out.print(premiki + " " + primerjave + " | ");
                    radixSort(array, trace, !ascending);
                    System.out.println(premiki + " " + primerjave + "");
                }
                break;
            case "bucket":
                bucketSort(array, trace, ascending);
                if (!trace) {
                    System.out.print(premiki + " " + primerjave + " | ");
                    bucketSort(array, trace, ascending);
                    System.out.print(premiki + " " + primerjave + " | ");
                    bucketSort(array, trace, !ascending);
                    System.out.println(premiki + " " + primerjave + "");
                }
                break;
            default:
                System.out.println("Neveljavna izbira metode.");
        }
    }

    public static void print(int[] array, int index) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
            if (method.equals("insert") && index == array.length && i == index - 1) {
                System.out.print("| ");
            }
            if (i == index - 1 && index != array.length) {
                System.out.print("| ");
            }
            if (i == array.length - 1 && index == -1) {
                System.out.print("| ");
            }
            if (method.equals("bucket") && index == array.length && i == array.length - 1) {
                System.out.print("|");
            }
        }
        System.out.println();
    }

    private static void insertionSort(int[] array, boolean trace, boolean ascending) {
        if (method.equals("insert")) {
            premiki = 0;
            primerjave = 0;
        }
        int temp, j;
        if (trace == true && method.equals("insert"))
            print(array, 0);
        for (int i = 1; i < array.length; i++) {
            temp = array[i];
            // prvi premik v spremenljivko temp
            premiki++;
            // spremenljivka j = za primerjanje v urejenem delu
            j = i - 1;
            while (j >= 0) {
                // primerjave povečam za ena, pa potem gre v if primerjat
                primerjave++;
                if (ascending ? array[j] > temp : array[j] < temp) {
                    array[j + 1] = array[j];
                    // premiki vmes
                    premiki++;
                    j--;
                } else {
                    break;
                }
            }
            // zadnji premik iz spremenljivke temp
            array[j + 1] = temp;
            premiki++;
            if (trace)
                print(array, i + 1);
        }
    }

    private static int najmanjsi(int[] array, int index) {
        int min = array[index];
        int minIndex = index;
        for (int i = index + 1; i < array.length; i++) {
            primerjave++;
            if (min > array[i]) {
                min = array[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    private static int najvecji(int[] array, int index) {
        int max = array[index];
        int maxIndex = index;
        for (int i = index + 1; i < array.length; i++) {
            primerjave++;
            if (max < array[i]) {
                max = array[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private static void selectionSort(int[] array, boolean trace, boolean ascending) {
        premiki = 0;
        primerjave = 0;
        int temp, trenutni;
        if (trace)
            print(array, 0);
        for (int i = 0; i < array.length - 1; i++) {
            // Najde najmanjšega/največjega in zamenja z array[i]
            trenutni = ascending ? najmanjsi(array, i) : najvecji(array, i);
            temp = array[trenutni];
            array[trenutni] = array[i];
            array[i] = temp;
            premiki += 3;
            if (trace)
                print(array, i + 1);
        }
    }

    private static void bubbleSort(int[] array, boolean trace, boolean ascending) {
        premiki = 0;
        primerjave = 0;
        int urejenoDo = 0;
        int temp;
        if (trace)
            print(array, 0);
        do {
            int spremenjenZadnji = 0;
            boolean spremenjeno = false;
            for (int i = array.length - 1; i > urejenoDo; i--) {
                primerjave++;
                if (ascending ? array[i] < array[i - 1] : array[i] > array[i - 1]) {
                    temp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = temp;
                    premiki += 3;
                    spremenjenZadnji = i - 1;
                    spremenjeno = true;
                }
            }

            if (!spremenjeno) {
                urejenoDo = array.length - 1;
                if (trace)
                    print(array, array.length - 1);
            } else {
                if (spremenjenZadnji > urejenoDo) {
                    urejenoDo = spremenjenZadnji + 1;
                    if (trace)
                        print(array, urejenoDo);
                } else {
                    urejenoDo++;
                    if (trace)
                        print(array, urejenoDo);
                }
            }

        } while (urejenoDo < array.length - 1);
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void siftDown(int[] array, int size, int index, boolean ascending) {
        while (index < size / 2) {
            int levi = 2 * index + 1;
            int desni = 2 * index + 2;
            int spr = index;
            if (levi < size) {
                primerjave++;
                if (ascending ? array[levi] > array[spr] : array[levi] < array[spr]) {
                    spr = levi;
                }
            }
            if (desni < size) {
                primerjave++;
                if (ascending ? array[desni] > array[spr] : array[desni] < array[spr]) {
                    spr = desni;
                }
            }
            if (spr == index)
                break;
            swap(array, index, spr);
            premiki += 3;
            index = spr;
        }
    }

    private static void heapSort(int[] array, boolean trace, boolean ascending) {
        premiki = 0;
        primerjave = 0;
        int n = array.length;
        if (trace)
            print(array, array.length);
        // izgradnja kopice
        for (int i = n / 2 - 1; i >= 0; i--) {
            siftDown(array, n, i, ascending);
        }
        if (trace) {
            print(array, -1);
        }
        // urejanje s kopico
        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i); // zamenjamo koren z zadnjim listom
            premiki += 3;
            siftDown(array, i, 0, ascending);
            if (trace)
                print(array, i);
        }
    }

    private static void mergeSort(int[] array, boolean trace, boolean ascending) {
        premiki = 0;
        primerjave = 0;
        ResizableArray tempArray = new ResizableArray();
        if (trace) {
            print(array, 0);
        }
        mergeSortRek(array, tempArray, 0, array.length - 1, trace, ascending);
    }

    private static void mergeSortRek(int[] array, ResizableArray tempArray, int levi, int desni, boolean trace,
            boolean ascending) {
        if (levi >= desni) {
            return; // če je en element, returna
        }
        int sredina = (levi + desni) / 2; // sredina
        if (trace) {
            printMerge(array, levi, desni, sredina);
        }
        mergeSortRek(array, tempArray, levi, sredina, trace, ascending); // levo
        mergeSortRek(array, tempArray, sredina + 1, desni, trace, ascending); // desno
        merge(array, tempArray, levi, sredina, desni, trace, ascending); // združi
    }

    private static void merge(int[] array, ResizableArray tempArray, int levi, int sredina, int desni, boolean trace,
            boolean ascending) {
        int i = levi;
        int j = sredina + 1;
        tempArray = new ResizableArray();
        while (i <= sredina && j <= desni) {
            primerjave++;
            if (ascending ? array[i] <= array[j] : array[i] >= array[j]) {
                tempArray.add(array[i++]);
            } else {
                tempArray.add(array[j++]);
            }
            premiki++;
        }
        while (i <= sredina) {
            tempArray.add(array[i++]);
            premiki++;
        }
        while (j <= desni) {
            tempArray.add(array[j++]);
            premiki++;
        }
        for (int k = 0; k < tempArray.size(); k++) {
            array[levi + k] = tempArray.get(k);
            premiki++;
        }
        if (trace) {
            printMerge(array, levi, desni, -1);
        }
    }

    private static void printMerge(int[] array, int levi, int desni, int sredina) {
        for (int i = levi; i <= desni; i++) {
            if (i == sredina + 1 && sredina != -1) {
                System.out.print("| ");
            }
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    private static void printQuick(int[] a, int pivotIndex, int left, int right) {
        for (int i = left; i <= right; i++) {
            if (i == pivotIndex) {
                System.out.print("| " + a[i] + " | ");
            } else {
                System.out.print(a[i] + " ");
            }
        }
        System.out.println();
    }

    public static void quickSort(int[] a, boolean trace, boolean ascending) {
        primerjave = 0;
        premiki = 0;
        if (trace) {
            print(a, 0);
        }
        quickSortRecursive(a, 0, a.length - 1, trace, ascending);
        if (trace) {
            print(a, 0);
        }
    }

    private static void quickSortRecursive(int[] a, int left, int right, boolean trace, boolean ascending) {
        if (left >= right)
            return;
        int r = partition(a, left, right, ascending);
        if (trace) {
            printQuick(a, r, left, right);
        }
        quickSortRecursive(a, left, r - 1, trace, ascending);
        quickSortRecursive(a, r + 1, right, trace, ascending);
    }

    private static int partition(int[] a, int left, int right, boolean ascending) {
        int p = a[left];
        int l = left;
        int r = right + 1;
        while (true) {
            if (ascending) {
                do {
                    l++;
                    primerjave++;
                } while (l < right && a[l] < p);
                do {
                    r--;
                    primerjave++;
                } while (a[r] > p);
            } else {
                do {
                    l++;
                    primerjave++;
                } while (l < right && a[l] > p);
                do {
                    r--;
                    primerjave++;
                } while (a[r] < p);
            }
            if (l >= r)
                break;
            swap(a, l, r);
            premiki += 3;
        }
        swap(a, left, r);
        premiki += 4;
        return r;
    }

    private static void radixSort(int[] array, boolean trace, boolean ascending) {
        premiki = 0;
        primerjave = 0;
        if (trace) {
            print(array, 0);
        }
        int max = array[0];
        for (int num : array) {
            if (num > max)
                max = num;
        }
        int exp = 1; // prvo enice, pa desetice, stotice ...
        while (max / exp > 0) {
            countSort(array, exp, ascending);
            if (trace) {
                print(array, 0);
            }
            exp *= 10; // desetice, stotice ...
        }
    }

    private static void countSort(int[] array, int exp, boolean ascending) {
        int[] temp = new int[array.length];
        int[] c = new int[10]; // število pojavitev za cifre (0-9)
        int digit;
        for (int num : array) {
            digit = (num / exp) % 10;
            c[digit]++;
            primerjave++;
        }
        if (ascending) {
            for (int i = 1; i < 10; i++) {
                c[i] += c[i - 1];
            }
        } else {
            for (int i = 8; i >= 0; i--) {
                c[i] += c[i + 1];
            }
        }
        for (int i = array.length - 1; i >= 0; i--) {
            digit = (array[i] / exp) % 10;
            temp[c[digit] - 1] = array[i];
            c[digit]--;
            premiki++;
            primerjave++;
        }
        System.arraycopy(temp, 0, array, 0, array.length);
        premiki += array.length;
    }

    private static void bucketSort(int[] array, boolean trace, boolean ascending) {
        premiki = 0;
        primerjave = 0;

        if (trace) {
            print(array, 0);
        }
        int min = array[0], max = array[0];
        for (int i = 1; i < array.length; i++) {
            primerjave += 2;
            if (array[i] < min) {
                primerjave--;
                min = array[i];
            } else if (array[i] > max) {
                max = array[i];
            }
        }
        int k = (int) Math.floor(array.length / 2);
        double v = (double) (max - min + 1) / k;
        ResizableArray[] buckets = new ResizableArray[k];
        for (int i = 0; i < k; i++) {
            buckets[i] = new ResizableArray();
        }
        for (int num : array) {
            int bucketIndex = (int) ((num - min) / v);
            if (ascending == false) {
                bucketIndex = k - bucketIndex - 1;
            }
            buckets[bucketIndex].add(num);
            premiki++;
        }
        if (trace) {
            for (int i = 0; i < buckets.length; i++) {
                buckets[i].izpis();
                if (i != buckets.length - 1) {
                    System.out.print("| ");
                }
            }
            System.out.println();
        }
        int index = 0;
        for (ResizableArray bucket : buckets) {
            for (int i = 0; i < bucket.size(); i++) {
                array[index++] = bucket.get(i);
                premiki++;
            }
        }
        primerjave += array.length * 2;
        insertionSort(array, trace, ascending);
    }
}