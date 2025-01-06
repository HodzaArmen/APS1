import java.util.Scanner;

public class Izziv5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Vnesi velikost n: ");
        int n = sc.nextInt();
        Oseba[] tt = new Oseba[n];
        for (int i = 0; i < n; i++) {
            tt[i] = new Oseba();
        }
        boolean ponovi = true;
        while (ponovi) {
            Oseba[] t = tt.clone();
            System.out.println("Polje t:");
            for (Oseba o : t) {
                System.out.print(o.getIme() + " " + o.getPriimek() + " (" + o.getLetoR() + "), ");
            }
            System.out.println();
            System.out.println("Izberi atribut za urejanje (0: priimek, 1: ime, 2: leto rojstva): ");
            int atr = sc.nextInt();
            Oseba.setAtr(atr);
            System.out.println("Izberi smer (1: naraščajoče, 0: padajoče): ");
            boolean smer = sc.nextInt() == 1;
            Oseba.setSmer(smer);
            System.out.println("Urejanje:");
            Urejanje.bubbleSort(t);
            System.out.println("Želite ponoviti? (1: da, 0: ne): ");
            ponovi = sc.nextInt() == 1;
        }
        sc.close();
    }
}