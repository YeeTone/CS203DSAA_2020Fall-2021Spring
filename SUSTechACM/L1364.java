package SUSTechACM;

import java.math.*;
import java.io.*;
import java.util.*;

public class L1364 {
    public static void main(String[] args) {
        FastScanner fastScanner=new FastScanner(System.in);
        int t= fastScanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n= fastScanner.nextInt();
            int[]array=new int[n];
            for (int j = 0; j < n; j++) {
                array[j]= fastScanner.nextInt();
            }

            int[]array2=array.clone();

            long counts1=selectionSort(array);
            long counts2=insertionSort(array2);
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[j]);
                if(j!= array.length-1){
                    System.out.print(" ");
                }
            }

            System.out.println();
            if(counts1>counts2){
                System.out.println("Insertion Sort wins!");
            }else {
                System.out.println("Selection Sort wins!");
            }
        }
    }
    private static long selectionSort(int[]array){
        long counts=0;
        for (int i = 0; i < array.length-1; i++) {
            int k=i;
            for (int j = i+1; j < array.length; j++) {
                counts+=1;
                if(array[k]>array[j]){
                    k=j;
                }
            }
            int temp=array[i];
            array[i]=array[k];
            array[k]=temp;
            counts+=1;
        }
        return counts;
    }
    private static long insertionSort(int[]array){
        long counts=0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j >0 ; j--) {
                counts+=1;
                if(array[j-1]>array[j]){
                    int temp=array[j-1];
                    array[j-1]=array[j];
                    array[j]=temp;
                    counts+=1;
                }else {
                    break;
                }
            }
        }
        return counts;
    }

    private static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner(InputStream in) {
            br = new BufferedReader(new InputStreamReader(in), 16384);
            eat("");
        }

        public void eat(String s) {
            st = new StringTokenizer(s);
        }

        public String nextLine() {
            try {
                return br.readLine();
            } catch (IOException e) {
                return null;
            }
        }

        public void hasNext() {
            while (!st.hasMoreTokens()) {
                String s = nextLine();
                if (s == null) return;
                eat(s);
            }
        }

        public String next() {
            hasNext();
            return st.nextToken();
        }

        public boolean nextBoolean(){
            return Boolean.parseBoolean(next());
        }

        public byte nextByte(){
            return Byte.parseByte(next());
        }

        public short nextShort(){
            return Short.parseShort(next());
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public float nextFloat(){
            return Float.parseFloat(next());
        }

        public double nextDouble(){
            return Double.parseDouble(next());
        }

        public BigInteger nextBigInteger(){
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecimal(){
            return new BigDecimal(next());
        }

        public void close(){
            try{
                st=null;
                br.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }
}
