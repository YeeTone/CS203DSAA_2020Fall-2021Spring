package BinarySearch;

import java.util.Arrays;
import java.util.Comparator;

public class IntBinarySearcher{
    private IntBinarySearcher(){

    }

    public static int intBinarySearch(int[]array,int value){
        return intBinarySearch0(array,value,0,array.length);
    }

    public static int intBinarySearch0(int[]array,int value,int fromIndex,int toIndex){
        // search in [fromIndex,toIndex];
        int low=fromIndex,high=toIndex-1;
        while (low<=high){
            int mid=low+(high-low)/2;
            if(array[mid]<value){
                low=mid+1;
            }else if(array[mid]>value){
                high=mid-1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    public static int intBinarySearch1(int[]array,int value,int fromIndex,int toIndex){
        // search in [fromIndex,toIndex)
        int low=fromIndex,high=toIndex;
        while (low<high){
            int mid=low+(high-low)/2;
            if(array[mid]<value){
                low=mid+1;
            }else if(array[mid]>value){
                high=mid;
            }else {
                return mid;
            }
        }
        return -1;
    }


}
