public class SummativeSums {

    public static int arraySum(int[] a){
        int sum =0;

        //for loop to add each value in the array
        for(int i=0; i<a.length;i++){
            sum+=a[i];
        }
        return sum;
    }

    public static void main(String[] args){

        int[] one = { 1, 90, -33, -55, 67, -16, 28, -55, 15 };
        int[] two = { 999, -60, -77, 14, 160, 301 };
        int[] three = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, -99 };

        System.out.println("#1 Array Sum: "+ arraySum(one));
        System.out.println("#2 Array Sum: "+ arraySum(two));
        System.out.println("#3 Array Sum: "+ arraySum(three));
    }
}
