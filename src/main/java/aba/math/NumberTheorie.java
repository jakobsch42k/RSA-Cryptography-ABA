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

    public static EEARecord eea(BigInteger r0, BigInteger r1){
        if (r1.equals(BigInteger.ZERO)){
            if(r0.compareTo(BigInteger.ZERO) < 0){
                return new EEARecord(BigInteger.valueOf(-1), BigInteger.ZERO, r0.negate());
            }
            else {
                return new EEARecord(BigInteger.ONE, BigInteger.ZERO, r0.abs());
            }
        }

        EEARecord result = eea(r1, r0.remainder(r1));
        BigInteger gcd = result.gcd();
        BigInteger s1 = result.s();
        BigInteger t1 = result.t();

        BigInteger s = t1;
        BigInteger t = s1.subtract((r0.divide(r1)).multiply(t1));

        return new EEARecord(s, t, gcd);

    }

    public static int phi(int m){
        int result = m;
        for (int p=2;p*p<=m;p++){
            if(m%p == 0){
                result -= result/p;
                while (m % p == 0){m /= p;}
            }
        }
        if (m > 1){
            result -= result/m;
        }
        return result;
    }

    public static BigInteger modularInverse(BigInteger a, BigInteger m) {
        if (m.compareTo(BigInteger.ONE) < 0){
            throw new IllegalArgumentException("Modul is not good");
        }
        EEARecord result = eea(m, a.mod(m));
        if (!result.gcd().equals(BigInteger.ONE)){
            throw new IllegalArgumentException("There is no modular Inverse");
        }
        return result.t().mod(m);
    }
}
