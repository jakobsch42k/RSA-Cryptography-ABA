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

            int a = random.nextInt(-1000000, 1000000);
            int b = random.nextInt(-1000000, 1000000);

            int result = gcd(a, b);

            String inputs = "a= " + a + ", b= " + b;

            assertEquals((BigInteger.valueOf(b)).gcd(BigInteger.valueOf(a)), BigInteger.valueOf(result), inputs);
        }
    }
    @ParameterizedTest
    @CsvSource({
            "8, 0, 8",
            "8, 8, 8",
            "0, 1, 1",
            "6, 10, 2",
            "22, 25, 1"

    })
    void gcdStimmtBeiGrenzfaellen(int a, int b, int expected){

        int result1 = gcd(a, b);

        assertEquals(expected, result1);
    }
    @Test
    void eeaGiltBeiVielenZufallszahlenGegenBigInt(){
        Random random = new Random(42);
        for (int i = 0; i < 10000; i++) {

            int a = random.nextInt(-1000000, 1000000);
            int b = random.nextInt(-1000000, 1000000);

            int[] result = eea(a, b);

            String inputs = "a= " + a + ", b= " + b;

            assertEquals(BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)), BigInteger.valueOf(result[0]), inputs);
            assertEquals((long)a * result[1] + (long) b * result[2], result[0], inputs);
        }
    }
    @ParameterizedTest
    @CsvSource({
            "8, 0, 8",
            "8, 8, 8",
            "0, 1, 1",
            "6, 10, 2",
            "22, 25, 1"

    })
    void eeaGiltBeiGrenzfaellen(int a, int b, int expected){

         int[] result = eea(a, b);

         assertEquals(expected, result[0]);
         assertEquals((long)a * result[1] + (long) b * result[2], result[0]);
    }
    @Test
    void modularInverseVonZufallszahlenGegenBigInt(){
        Random random = new Random(7);
        for (int i = 0; i < 10000; i++) {
            int a = random.nextInt(0, 1000000);
            int m = random.nextInt(2, 1000000);
            String inputs = "a= " + a + ", m= " + m;

            if(BigInteger.valueOf(a).gcd(BigInteger.valueOf(m)).compareTo(BigInteger.valueOf(1)) > 0){
                assertThrows(IllegalArgumentException.class, () -> modularInverse(a, m), inputs);
            }
            else{
                int result = modularInverse(a, m);
                assertEquals(BigInteger.valueOf(a).modInverse(BigInteger.valueOf(m)), BigInteger.valueOf(result), inputs);
                assertEquals(1, Math.floorMod(((long) a * result), m), inputs);
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
    void modularInverseVonGrenzfaellen(int a, int m, int expected){
        int result = modularInverse(a, m);
        assertEquals(expected, result);

    }
    @ParameterizedTest
    @CsvSource({
            "4, 6",
            "0, 10",
            "5, 0"
    })
    void modularInverseVonGrenzfaellenWithException(int a, int m){
            assertThrows(IllegalArgumentException.class, () -> modularInverse(a, m));
    }
}
