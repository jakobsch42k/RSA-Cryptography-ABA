public class eucledianAlgorythm {

    public static void main(String[] args){
        System.out.println(gcf(973,301));
    }

    public static int gcf(int inputr0, int inputr1){
        if(inputr1 == 0){
            return inputr0;
        }
        else{
            return gcf(inputr1, inputr0 % inputr1);
        }
    }

}