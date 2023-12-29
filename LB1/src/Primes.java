public class Primes {
    public static void main(String[] args) {
        for (int q=2; q<101; q++) {
            if (isPrime(q)) {
                System.out.println(q);
            }
        }
    }
    public static boolean isPrime(int n) {
        for (int i=2; i<n; i++) {
            if (n%i == 0) {
                return false;
            }
        }
        return true;
    }
}
