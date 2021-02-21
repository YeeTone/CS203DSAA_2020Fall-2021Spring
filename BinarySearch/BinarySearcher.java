package BinarySearch;

import java.util.Arrays;

public class BinarySearcher{
    private BinarySearcher(){

    }
    public static int binarySearch0(int[]array,int value,int fromIndex,int toIndex){
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

    public static int binarySearch1(int[]array,int value,int fromIndex,int toIndex){
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
