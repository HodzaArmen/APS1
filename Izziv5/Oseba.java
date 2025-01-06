import java.util.Random;

public class Oseba implements Comparable<Oseba> {
    private final static String[] imena = { "Ana", "Borut", "Cvetka", "David", "Eva" };
    private final static String[] priimki = { "Novak", "Kranjc", "Skok", "Peternelj", "Volk" };
    private static int atr = 0; // 0: priimek, 1: ime, 2: letoR
    private static boolean smer = true; // true: naraščajoče, false: padajoče
    private String ime;
    private String priimek;
    private int letoR;

    public Oseba() {
        Random rand = new Random();
        this.ime = imena[rand.nextInt(imena.length)];
        this.priimek = priimki[rand.nextInt(priimki.length)];
        this.letoR = rand.nextInt(100) + 1925;
    }

    public static int getAtr() {
        return atr;
    }

    public static void setAtr(int atr) {
        Oseba.atr = atr;
    }

    public static boolean getSmer() {
        return smer;
    }

    public static void setSmer(boolean smer) {
        Oseba.smer = smer;
    }

    @Override
    public String toString() {
        return switch (atr) {
            case 0 -> priimek;
            case 1 -> ime;
            case 2 -> String.valueOf(letoR);
            default -> throw new IllegalArgumentException("Neveljaven atribut za izpis.");
        };
    }

    @Override
    public int compareTo(Oseba o) {
        int result;
        switch (atr) {
            case 0 -> result = this.priimek.compareTo(o.priimek);
            case 1 -> result = this.ime.compareTo(o.ime);
            case 2 -> result = Integer.compare(this.letoR, o.letoR);
            default -> throw new IllegalArgumentException("Neveljaven atribut za primerjavo.");
        }
        return smer ? result : -result; // Upoštevanje smeri
    }

    public String getIme() {
        return this.ime;
    }

    public String getPriimek() {
        return this.priimek;
    }

    public int getLetoR() {
        return this.letoR;
    }
}