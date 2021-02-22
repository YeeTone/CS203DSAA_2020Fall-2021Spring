package SUSTechACM;

import java.math.*;
import java.io.*;
import java.util.*;

public class L1365 {
    public static void main(String[] args) {
        FastReader fastReader =new FastReader(System.in);
        FastWriter fastWriter =new FastWriter(System.out);
        int t= fastReader.nextInt();
        for (int i = 0; i < t; i++) {
            int n= fastReader.nextInt();
            int[]array=new int[n];
            for (int j = 0; j < n; j++) {
                array[j]= fastReader.nextInt();
            }
            fastWriter.println(getBubbleSortCounts(array));
        }

        fastReader.close();
        fastWriter.close();
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

    private static class FastReader implements Closeable{
        private final BufferedReader br;
        private StringTokenizer st;

        public FastReader(InputStream in) {
            br = new BufferedReader(new InputStreamReader(in), 16384);
            eat("");
        }

        private void eat(String s) {
            st = new StringTokenizer(s);
        }

        public String nextLine() {
            try {
                return br.readLine();
            } catch (IOException e) {
                return null;
            }
        }

        public boolean hasNext() {
            while(!st.hasMoreTokens()) {
                String s = nextLine();
                if(s==null) return false;
                eat(s);
            }
            return true;
        }

        public String next() {
            hasNext();
            return st.nextToken();
        }

        public boolean nextBoolean(){
            return Boolean.parseBoolean(next());
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
                System.exit(1);
            }

        }
    }

    private static class FastWriter implements Closeable{
        private final PrintWriter writer;

        public FastWriter(OutputStream out){
            this.writer=new PrintWriter(out);
        }

        public void print(Object object){
            writer.write(object.toString());
        }

        public void printf(String format,Object... os){
            writer.write(String.format(format,os));
        }

        public void println(){
            writer.write(System.lineSeparator());
        }

        public void println(Object object){
            writer.write(object.toString());
            writer.write(System.lineSeparator());
        }

        @Override
        public void close() {
            writer.flush();
            writer.close();
        }
    }
}
