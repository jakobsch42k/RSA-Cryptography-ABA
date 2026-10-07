public class euclidianAlgorythm {

    public static void main(String[] args){
        System.out.println(gcd(973,301));
    }

    public static int gcd(int inputr0, int inputr1){
        if(inputr1 == 0){
            return inputr0;
        }
        else{
            return gcd(inputr1, inputr0 % inputr1);
        }
    }

}