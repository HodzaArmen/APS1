import java.util.Random;

public class Izziv4 {
    public static void main(String[] args) {
        int stOperacij = 10000;
        Random random = new Random();
        APQ<Integer> APQ = new APQ<>();
        AHPQ<Integer> AHPQ = new AHPQ<>();
        LHPQ<Integer> LHPQ = new LHPQ<>();

        System.out.println("Implementacija\t\t\tCas[ms]\t\tPremiki\t\tPrimerjave");
        System.out.println("----------------------------------------------------------------------------");

        // APQ
        long zacetek = System.currentTimeMillis();
        for (int i = 0; i < stOperacij; i++) {
            APQ.enqueue(random.nextInt(1000));
        }
        for (int i = 0; i < stOperacij; i++) {
            try {
                APQ.front();
                APQ.dequeue();
                APQ.enqueue(random.nextInt(1000));
            } catch (CollectionException e) {
                e.printStackTrace();
            }
        }
        long konec = System.currentTimeMillis();
        System.out.printf("Neurejeno polje (64,2x):\t%d\t\t%d\t\t%d\n", konec - zacetek, APQ.steviloPremikov,
                APQ.steviloPrimerjav);

        // AHPQ
        zacetek = System.currentTimeMillis();
        for (int i = 0; i < stOperacij; i++) {
            AHPQ.enqueue(random.nextInt(1000));
        }
        for (int i = 0; i < stOperacij; i++) {
            try {
                AHPQ.front();
                AHPQ.dequeue();
                AHPQ.enqueue(random.nextInt(1000));
            } catch (CollectionException e) {
                e.printStackTrace();
            }
        }
        konec = System.currentTimeMillis();
        System.out.printf("Implicitna kopica (64,2x):\t%d\t\t%d\t\t%d\n", konec - zacetek, AHPQ.steviloPremikov,
                AHPQ.steviloPrimerjav);

        // LHPQ
        zacetek = System.currentTimeMillis();
        for (int i = 0; i < stOperacij; i++) {
            LHPQ.enqueue(random.nextInt(1000));
        }
        for (int i = 0; i < stOperacij; i++) {
            try {
                LHPQ.front();
                LHPQ.dequeue();
                LHPQ.enqueue(random.nextInt(1000));
            } catch (CollectionException e) {
                e.printStackTrace();
            }
        }
        konec = System.currentTimeMillis();
        System.out.printf("Eksplicitna kopica:\t\t%d\t\t%d\t\t%d\n", konec - zacetek, LHPQ.steviloPremikov,
                LHPQ.steviloPrimerjav);
    }
}
