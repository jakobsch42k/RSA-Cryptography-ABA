public class NumberTheorieAlgorithms {
    public static int gcd(int inputr0, int inputr1){
        if(inputr1 == 0){
            return inputr0;
        }
        else{
            return gcd(inputr1, inputr0 % inputr1);
        }
    }

    public static int[] eea(int r0, int r1){
        if (r1 == 0){
            return new int[]{r0,1,0};
        }

        int[] result = eea(r1, r0 % r1);
        int gcd = result[0];
        int s1 = result[1];
        int t1 = result[2];

        int s = t1;
        int t = s1 - (r0/r1) * t1;

        return new int[]{gcd, s, t};

    }

    public static int phi(int m){
        int result = m;
        for (int p=2;p*p<=m;p++){
            if(m%p == 0){
                result -= result/p;
                while (m % p == 0){
                    m /= p;
                }
            }
        }
        if (m > 1){
            result -= result/m;
        }
        return result;

    }
}
