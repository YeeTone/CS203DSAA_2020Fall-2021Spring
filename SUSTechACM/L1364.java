package SUSTechACM;

import java.math.*;
import java.io.*;
import java.util.*;

public class L1364 {
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

            int[]array2=array.clone();

            long counts1=selectionSort(array);
            long counts2=insertionSort(array2);
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[j]);
                if(j!= array.length-1){
                    fastWriter.print(" ");
                }
            }

            fastWriter.println();
            if(counts1>counts2){
                fastWriter.println("Insertion Sort wins!");
            }else {
                fastWriter.println("Selection Sort wins!");
            }
        }

        fastReader.close();
        fastWriter.close();
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
