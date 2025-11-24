import java.util.ArrayList;
import java.util.List;

public class Part_2 {
    public static void main(String[] args) {
        int[] nums = {1,2,1,2,3,1,2}; //example array
        List<List<Integer>> res=strictlyIncreasing(nums); //function
        System.out.println(res); //print result

    }
    /*
     * loop over the main array and add on the sub array the next integer if it is greater the current integer
     * add keep going while the number is bigger
     * and after it finishes check if the sub array size is greater then one
     * */

    public static List<List<Integer>> strictlyIncreasing(int [] arr){
        List<List<Integer>> result=new ArrayList<>(); //create array
        int i=0; //start of sub array
        while(i < arr.length) {
            List<Integer> sub=new ArrayList<>(); //create sub array
            int j = i, k = i+1;
            sub.add(arr[j]);
            for (; k < arr.length && arr[j] < arr[k]; j++, k++) {
                sub.add(arr[k]);
            }
            i=k;
            if(sub.size()>=2){
                result.add(sub);
            }
        }
        return result;

    }
}

