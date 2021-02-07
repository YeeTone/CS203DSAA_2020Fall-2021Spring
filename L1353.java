package SUSTechACM;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class L1353 {

    //Not solved yet:Time Limit Exceed
    static final FastReader fastReader;
    static final FastWriter fastWriter;
    static {
        fastReader=new FastReader(System.in);
        fastWriter=new FastWriter(System.out);
    }
    public static void main(String[] args) {
        int t= fastReader.nextInt();
        int[]bucketArray=new int[300000+1];
        for (int i = 0; i < t; i++) {
            int n= fastReader.nextInt();
            Arrays.fill(bucketArray,0);

            for (int j = 0; j < n; j++) {
                int index= fastReader.nextInt();
                bucketArray[index]++;
                if(j%2==0){
                    System.out.print(getMedianValue(bucketArray,j/2));
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    private static int getMedianValue(int[]bucketArray,int medianCount){
        int count=0;
        for (int i=0;i< bucketArray.length;i++){
            count+=bucketArray[i];
            if(count>=medianCount){
                return i;
            }
        }
        return 0;
    }


    private static class LinkedNode{
        int vi;
        LinkedNode next;
    }

    private static class FastReader implements Closeable {
        BufferedReader br;
        StringTokenizer st;

        public FastReader(InputStream in) {
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

        public void close()throws IOException{
            st=null;
            br.close();
        }
    }

    private static class FastWriter implements Closeable {
        private final BufferedWriter writer;
        public FastWriter(OutputStream out){
            this.writer=new BufferedWriter(new OutputStreamWriter(out));
        }

        public void print(Object object){
            try{
                writer.write(object.toString());
                writer.flush();
            }catch (IOException e){
                e.printStackTrace();
                System.exit(1);
            }

        }
        public void printf(String format,Object... os) {
            try{
                writer.write(String.format(format,os));
                writer.flush();
            }catch (IOException e){
                e.printStackTrace();
                System.exit(1);
            }

        }
        public void println(){
            try{
                writer.write(System.lineSeparator());
                writer.flush();
            }catch (IOException e){
                e.printStackTrace();
                System.exit(1);
            }
        }

        public void println(Object object) {
            try{
                writer.write(object.toString());
                writer.write(System.lineSeparator());
                writer.flush();
            }catch (IOException e){
                e.printStackTrace();
                System.exit(1);
            }

        }

        @Override
        public void close()throws IOException{
            writer.close();
        }
    }
}
