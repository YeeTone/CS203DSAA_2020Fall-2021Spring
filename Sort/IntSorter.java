import java.util.Arrays;

public class IntSorter {
    private static final IntSorter sorter=new IntSorter();
    private IntSorter(){

    }

    public static IntSorter getInstance() {
        return sorter;
    }
    public void bubbleSort(int[]array){
        for (int i = 0; i < array.length; i++) {
            for (int j = i+1; j < array.length; j++) {
                if(array[j-1]>array[j]){
                    int temp=array[j-1];
                    array[j-1]=array[j];
                    array[j]=temp;
                }
            }
        }
    }

    public void selectionSort(int[]array){
        for (int i = 0; i < array.length-1; i++) {
            int k=i;
            for (int j = k+1; j < array.length; j++) {
                if(array[k]>array[j]){
                    k=j;
                }
            }

            int temp=array[i];
            array[i]=array[k];
            array[k]=temp;
        }
    }

    public void insertionSort(int[]array){
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if(array[j-1]>array[j]){
                    int temp=array[j-1];
                    array[j-1]=array[j];
                    array[j]=temp;
                }else {
                    break;
                }
            }
        }
    }

    public void mergeSort(int[]array){
        if(array.length>1){
            int p=array.length/2;

            int[]array2=new int[p];
            System.arraycopy(array,0,array2,0,array2.length);

            int[]array3=new int[array.length-p];
            System.arraycopy(array,p,array3,0,array3.length);

            mergeSort(array2);
            mergeSort(array3);

            System.arraycopy(merge(array2,array3),0,array,0,array.length);
        }
    }

    private int[] merge(int[]array2,int[]array3){
        int[] array=new int[array2.length+array3.length];
        int i=0,j=0;

        for (int k = 0; k < array.length; k++) {
            if(i<array2.length&&(j>=array3.length||array2[i]<array3[j])){
                array[k]=array2[i];
                i++;
            }else {
                array[k]=array3[j];
                j++;
            }
        }

        return array;
    }

    public void shellSort(int[]array){
        for (int step = array.length; step > 0; step/=2) {
            for (int j = step; j < array.length; j++) {
                int i=j;
                while (i>=step&&array[i-step]>array[i]){
                    int temp=array[i-step];
                    array[i-step]=array[i];
                    array[i]=temp;
                    i-=step;
                }
            }
        }
    }

    public void countSort(int[]array)throws OutOfMemoryError{
        int max=Integer.MIN_VALUE;
        int min=Integer.MAX_VALUE;

        for(int i:array){
            max=Math.max(i,max);
            min=Math.min(i,min);
        }

        int[]countArray=new int[max-min+1];
        for(int i:array){
            countArray[i-min]++;
        }

        int index=0;
        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                array[index]=i+min;
                index++;
            }
        }
    }
}
