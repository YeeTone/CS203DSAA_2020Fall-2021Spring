package SUSTechACM;

import java.math.*;
import java.io.*;
import java.util.*;

public class L1365 {
    public static void main(String[] args) {
        FastScanner fastScanner=new FastScanner(System.in);
        int t= fastScanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n= fastScanner.nextInt();
            int[]array=new int[n];
            for (int j = 0; j < n; j++) {
                array[j]= fastScanner.nextInt();
            }
            System.out.println(getBubbleSortCounts(array));
        }
    }
    private static long getBubbleSortCounts(int[]array){
        int[]copy=array.clone();
        return getSubBubbleSortCounts(array,copy,0, array.length-1);
    }

    private static long getSubBubbleSortCounts(int[]array, int[]copy, int low, int high){
        if(low == high){
            return 0;
        }

        int middle = low +(high - low)/2;
        long leftCounts=getSubBubbleSortCounts(array,copy, low, middle );
        long rightCounts=getSubBubbleSortCounts(array, copy, middle +1, high);
        long counts=0;

        int i= middle,j= high;
        int copyLoc= high;
        while (i>= low &&j>middle){
            if(array[i]>array[j]){
                counts+=j-middle;
                copy[copyLoc--]=array[i--];
            }else {
                copy[copyLoc--]=array[j--];
            }
        }
        for(;i>=low;i--)
        {
            copy[copyLoc--]=array[i];
        }
        for(;j>middle;j--)
        {
            copy[copyLoc--]=array[j];
        }
        if (high + 1 - low >= 0){
            System.arraycopy(copy, low, array, low, high + 1 - low);
        }
        return leftCounts+counts+rightCounts;
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
