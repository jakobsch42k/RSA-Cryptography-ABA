package aba.math;

import java.math.BigInteger;

public class NumberTheorie {

    public static BigInteger gcd(BigInteger inputr0, BigInteger inputr1){
        if(inputr1.equals(BigInteger.ZERO)){
            return inputr0.abs();
        }
        else{
            return gcd(inputr1.abs(), inputr0.mod(inputr1.abs()));
        }
    }

    public static int[] eea(int r0, int r1){
        if (r1 == 0){
            if(r0 < 0){
                return new int[]{-r0,-1,0};
            }
            else {
                return new int[]{Math.abs(r0), 1, 0};
            }
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

    public static int modularInverse(int a, int m) {
        if (m<=0){
            throw new IllegalArgumentException("Modul is not good");
        }
        int[] result = eea (m,(a%m+m)%m);
        if (result[0] != 1){
            throw new IllegalArgumentException("There is no modular Inverse");
        }
        return ((result[2]%m)+m)%m;
    }
}
