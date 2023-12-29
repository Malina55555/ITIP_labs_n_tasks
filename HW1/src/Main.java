public class Main {
    public static void main(String[] args) {
        System.out.println("___1.1___");
        String r111 = String.format("%.3f", convert(5));
        System.out.println(r111);
        String r112 = String.format("%.3f", convert(3));
        System.out.println(r112);
        System.out.println(convert(8));
        System.out.println("___1.2___");
        System.out.println(fitCalc(15, 1));
        System.out.println(fitCalc(24, 2));
        System.out.println(fitCalc(41, 3));
        System.out.println("___1.3___");
        System.out.println(containers(3,4,2));
        System.out.println(containers(5,0,2));
        System.out.println(containers(4,1,4));
        System.out.println("___1.4___");
        System.out.println(triangleType(5,5,5));
        System.out.println(triangleType(5,4,5));
        System.out.println(triangleType(3,4,5));
        System.out.println(triangleType(5,1,1));
        System.out.println("___1.5___");
        System.out.println(ternaryEvaluation(8,4));
        System.out.println(ternaryEvaluation(1,11));
        System.out.println(ternaryEvaluation(5,9));
        System.out.println("___1.6___");
        System.out.println(howManyItems(22, 1.4f, 2));
        System.out.println(howManyItems(45, 1.8f, 1.9f));
        System.out.println(howManyItems(100,2,2));
        System.out.println("___1.7___");
        System.out.println(factorial(3));
        System.out.println(factorial(5));
        System.out.println(factorial(7));
        System.out.println("___1.8___");
        System.out.println(gcd(48,18));
        System.out.println(gcd(52,8));
        System.out.println(gcd(259,28));
        System.out.println("___1.9___");
        System.out.println(ticketSaler(70,1500));
        System.out.println(ticketSaler(24,950));
        System.out.println(ticketSaler(53,1250));
        System.out.println("___1.10___");
        System.out.println(tables(5,2));
        System.out.println(tables(31,20));
        System.out.println(tables(123,58));
    }

    public static float convert(int x) {
        return x*3.785f;
    }
    public static int fitCalc(int x, int y) {
        return x*y;
    }
    public static int containers(int x, int y, int z) {
        return x*20+y*50+z*100;
    }
    public static String triangleType(int x, int y, int z) {
        if ((x>=(y+z)) || (y>=(x+z)) || (z>=(x+y))) {
            return "не треугольник";
        }
        if (x==y && x==z) {
            return "равносторонний";
        }
		else if (x==y || x==z || z==y) {
            return "равнобедренный";
        }
		else {
            return "разносторонний";
        }
    }
    public static int ternaryEvaluation(int x, int y) {
        return x>y?x:y;
    }
    public static int howManyItems(int x, float y, float z) {
        float q = x;
        int result = 0;
        while (q >= (y<z?y:z)*2) {
            q-=(y<z?y:z)*2;
            result +=1;
        }
        return result/2; //? по идее ответ должен быть в 2 раза больше
    }
    public static int factorial(int x) {
        int q = x;
        int result = 1;
        while (q > 1) {
            result*=q;
            q-=1;
        }
        return result;
    }
    public static int gcd(int x, int y) {
        int result = 1;

        for (int i=2; i<((x<y?x:y)+1); i++) {
            if (x%i == 0 && y%i ==0) {
                result = i;
            }
        };
        return result;
    }
    public static int ticketSaler(int x, int y) {
        int result = Math.round(x*y*0.72f);
        return result;
    }
    public static int tables(int x, int y) {
        int need_tables = 0;
        int q = x;
        while (q > 0) {
            q-=2;
            need_tables +=1;
        }
        if (need_tables-y > 0) {
            return need_tables-y;
        }
        else {
            return 0;
        }
    }
}