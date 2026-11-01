package aba.math;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;
import java.util.Random;

import static aba.math.NumberTheorie.*;
import static org.junit.jupiter.api.Assertions.*;


public class NumberTheorieTest {

    @Test
    void gcdStimmtBeiZufallszahlGegenBigInt(){
        Random random = new Random(42);
        for (int i = 0; i < 10000; i++){

            BigInteger a = randomPosNeg(random);
            BigInteger b = randomPosNeg(random);

            BigInteger result = gcd(a, b);

            String inputs = "a= " + a + ", b= " + b;

            assertEquals(b.gcd(a), result, inputs);
        }
    }
    @ParameterizedTest
    @CsvSource({
            "8, 0, 8",
            "8, 8, 8",
            "0, 1, 1",
            "6, 10, 2",
            "22, 25, 1",
            "-8, 0, 8",

    })
    void gcdStimmtBeiGrenzfaellen(BigInteger a, BigInteger b, BigInteger expected){

        BigInteger result1 = gcd(a, b);

        assertEquals(expected, result1);
    }
    @Test
    void gcdStimmtBeiGrossenZufallszahlGegenBigInt(){
        Random random = new Random(42);
        for (int i = 0; i < 1000; i++){

            BigInteger x = randomPosNeg(random);
            BigInteger y = randomPosNeg(random);
            BigInteger c = new BigInteger(512, random);

            BigInteger a = x.multiply(c);
            BigInteger b = y.multiply(c);

            BigInteger result = gcd(a, b);

            String inputs = "a= " + a + ", b= " + b;

            assertEquals(b.gcd(a), result, inputs);
        }
    }
    @Test
    void eeaGiltBeiVielenZufallszahlenGegenBigInt(){
        Random random = new Random(42);
        for (int i = 0; i < 10000; i++) {

            BigInteger a = randomPosNeg(random);
            BigInteger b = randomPosNeg(random);

            EEARecord result = eea(a, b);

            String inputs = "a= " + a + ", b= " + b;

            assertEquals(a.gcd(b), result.gcd(), inputs);
            assertEquals(a.multiply(result.s()).add(b.multiply(result.t())), result.gcd(), inputs);
        }
    }
    @Test
    void eeaGiltBeiVielenGrossenZufallszahlenGegenBigInt(){
        Random random = new Random(42);
        for (int i = 0; i < 10000; i++) {

            BigInteger x = randomPosNeg(random);
            BigInteger y = randomPosNeg(random);
            BigInteger c = new BigInteger(512, random);

            BigInteger a = x.multiply(c);
            BigInteger b = y.multiply(c);

            EEARecord result = eea(a, b);

            String inputs = "a= " + a + ", b= " + b;

            assertEquals(a.gcd(b), result.gcd(), inputs);
            assertEquals(a.multiply(result.s()).add(b.multiply(result.t())), result.gcd(), inputs);
        }
    }
    @ParameterizedTest
    @CsvSource({
            "8, 0, 8",
            "8, 8, 8",
            "0, 1, 1",
            "6, 10, 2",
            "22, 25, 1",
            "-22, 25, 1"

    })
    void eeaGiltBeiGrenzfaellen(BigInteger a, BigInteger b, BigInteger expected){

         EEARecord result = eea(a, b);

         assertEquals(expected, result.gcd());
        assertEquals(a.multiply(result.s()).add(b.multiply(result.t())), result.gcd());
    }
    @Test
    void modularInverseVonZufallszahlenGegenBigInt(){
        Random random = new Random(7);
        for (int i = 0; i < 10000; i++) {
            BigInteger a = randomPosNeg(random);
            BigInteger m = new BigInteger(512, random);
            String inputs = "a= " + a + ", m= " + m;

            if(a.gcd(m).compareTo(BigInteger.ONE) > 0){
                assertThrows(IllegalArgumentException.class, () -> modularInverse(a, m), inputs);
            }
            else{
                BigInteger result = modularInverse(a, m);
                assertEquals(a.modInverse(m), result, inputs);
                assertEquals(BigInteger.ONE, a.multiply(result).mod(m), inputs);
            }
        }
    }
    @ParameterizedTest
    @CsvSource({
            "1, 10, 1",
            "9, 10, 9",
            "11, 10, 1",
            "-7, 10, 7",
            "5, 1, 0"
    })
    void modularInverseVonGrenzfaellen(BigInteger a, BigInteger m, BigInteger expected){
        BigInteger result = modularInverse(a, m);
        assertEquals(expected, result);

    }
    @ParameterizedTest
    @CsvSource({
            "4, 6",
            "0, 10",
            "5, 0"
    })
    void modularInverseVonGrenzfaellenWithException(BigInteger a, BigInteger m){
            assertThrows(IllegalArgumentException.class, () -> modularInverse(a, m));
    }

    @Test
    void phiBis2000(){
        for (int i = 1; i <= 2000; i++){
            int result = phi(i);
            int expected = 0;
            for (int k = 1; k <= i; k++){
                if(BigInteger.valueOf(k).gcd(BigInteger.valueOf(i)).equals(BigInteger.ONE)){
                    expected++;
                }
            }
            assertEquals(expected, result, "i=" + i);
        }
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1",
            "7, 6",
            "9, 6"
    })
    void phiBeiGrenzfaellen(int i, int expected){
        int result = phi(i);
        assertEquals(expected, result, "i=" + i);
    }

    private static BigInteger randomPosNeg(Random random){
        BigInteger output = new BigInteger(512, random);
        if(random.nextBoolean()){
            output = output.negate();
        }
        return output;
    }
}
