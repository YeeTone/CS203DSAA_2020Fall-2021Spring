package JCoderAC.Lab3;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class L301 {
    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);
        int n= fastReader.nextInt();
        int[]array=new int[n];

        for (int i = 0; i < n; i++) {
            array[i]= fastReader.nextInt();
        }

        mergeSort(array);

        for (int i = 0; i < n; i++) {
            fastWriter.print(array[i]);
            if(i!=n-1){
                fastWriter.print(" ");
            }
        }

        fastReader.close();
        fastWriter.close();
    }
    public static void mergeSort(int[]array){
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

    private static int[] merge(int[]array2,int[]array3){
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

    private static class FastReader implements Closeable {
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
