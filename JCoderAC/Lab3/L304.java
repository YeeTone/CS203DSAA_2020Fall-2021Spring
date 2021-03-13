package JCoderAC.Lab3;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class L304 {
    public static void main(String[] args) {
        FastReader fastReader =new FastReader(System.in);
        FastWriter fastWriter =new FastWriter(System.out);
        int n= fastReader.nextInt();
        int m= fastReader.nextInt();

        long[]array=new long[n];
        for (int i = 0; i < n; i++) {
            array[i]= fastReader.nextLong();
        }

        long[]sum=new long[n];
        long pre=0;
        for (int i = n-1; i >= 0; i--) {
            sum[i]=array[i]+pre;
            pre=sum[i];
        }


        long[]cloned=new long[n-1];
        System.arraycopy(sum,1,cloned,0,cloned.length);
        mergeSort(cloned);
        System.arraycopy(cloned,0,sum,1,cloned.length);

        long result=0;
        for (int i = 0; i < m; i++) {
            result+=sum[i];
        }
        fastWriter.println(result);

        fastReader.close();
        fastWriter.close();
    }

    public static void mergeSort(long[]array){
        if(array.length>1){
            int p=array.length/2;

            long[]array2=new long[p];
            System.arraycopy(array,0,array2,0,array2.length);

            long[]array3=new long[array.length-p];
            System.arraycopy(array,p,array3,0,array3.length);

            mergeSort(array2);
            mergeSort(array3);

            System.arraycopy(merge(array2,array3),0,array,0,array.length);
        }
    }

    private static long[] merge(long[]array2,long[]array3){
        long[] array=new long[array2.length+array3.length];
        int i=0,j=0;

        for (int k = 0; k < array.length; k++) {
            if(i<array2.length&&(j>=array3.length||array2[i]>array3[j])){
                array[k]=array2[i];
                i++;
            }else {
                array[k]=array3[j];
                j++;
            }
        }

        return array;
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
