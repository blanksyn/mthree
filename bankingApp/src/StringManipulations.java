import java.util.Locale;

public class StringManipulations {

	public static void main(String[] args) {
		String line = "-------------------------------------------";

		System.out.println("String assignment qn 1");
        String in = "computer";
        String[] arr = in.split("");
        String temp = "";

        for(int i=arr.length-1; i>-1;i--){

            temp += arr[i];
            if(i>0){
                temp +="-";
            }
        }
        System.out.println(temp);

        System.out.println(line);
//--------------------------------------------------------------
        System.out.println("String assignment qn 2");
        String question2 = "abcxXXcxerxxXXwer";
        String[] xSplit = question2.split("");
        
        String x = "";
        String temp2 = "";

        
        for(int i = 0; i<xSplit.length;i++){
            if(xSplit[i].equals("x")){
//            	string with x
                x +=xSplit[i];
            }
            else {
            	temp2 +=xSplit[i];
            }
        }

        System.out.println(temp2 +x);
        System.out.println();

        System.out.println(line);
//--------------------------------------------------------------
        System.out.println("String assignment qn 3");
        String qn3 = "Hello World";
        String[] q3Split = qn3.split("");
        String newQ3 = q3Split[q3Split.length-1];
        for(int i = 1; i<(q3Split.length-1);i++){
            newQ3+=q3Split[i];
        }
        newQ3 +=q3Split[0];
        System.out.println(newQ3);


        System.out.println(line);
//--------------------------------------------------------------
        System.out.println("String assignment qn 4");
        String one = "Hi How are you";
        String two = "This is java String program";

        String[] store = one.split(" ");
        String temp1 = "";

        for(int i = 0; i<store.length;i++){
            if(store[i].length()>temp1.length()){
//                System.out.println(store[i].length());
                temp1 = store[i];
            }
        }
        System.out.println(temp1);
        temp1 = "";

        System.out.println(line);
//--------------------------------------------------------------
        System.out.println("String assignment qn 5");
        String qn5 = "Softra Services Limited";

        String[] q5 = qn5.split(" ");
        for(int i = 0; i<q5.length;i++){
            temp1 += q5[i].substring(0,1);
        }

        System.out.println(temp1);
        temp1 = "";

        System.out.println(line);
//--------------------------------------------------------------
        System.out.println("String assignment qn 6");
        String qn6 = "Softra Services Limited";
        String[] q6 = qn6.split(" ");

        for(int i = 0; i<q6.length;i++){
            temp1 += q5[i].substring(i,i+1).toUpperCase(Locale.ROOT);
        }

        System.out.println(temp1);
        temp1 = "";

        System.out.println(line);
//--------------------------------------------------------------
        System.out.println("String assignment qn 7");
        String qn7 = "hello java";
        for(int i =0;i<qn7.length();i++){
            temp1 += (char)(qn7.charAt(i)+1);
        }
        System.out.println(temp1);

	}

}
