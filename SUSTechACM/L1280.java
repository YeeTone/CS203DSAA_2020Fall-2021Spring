package SUSTechACM;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class L1280 {
    private static final FastReader fastReader;
    private static final FastWriter fastWriter;
    static {
        fastReader=new FastReader(System.in);
        fastWriter=new FastWriter(System.out);
    }
    public static void main(String[] args) {
        int t= fastReader.nextInt();
        for (int i = 0; i < t; i++) {
            int n= fastReader.nextInt();
            PriorityQueue<Integer>priorityQueue=new PriorityQueue<>();

            for (int j = 0; j < n; j++) {
                priorityQueue.offer(fastReader.nextInt());
            }

            int sum=0;
            while (priorityQueue.size()!=1){
                int stick1= priorityQueue.poll();
                int stick2= priorityQueue.poll();
                sum+=stick1;
                sum+=stick2;

                priorityQueue.offer(stick1+stick2);
            }

            fastWriter.println(sum);
        }
        fastReader.close();
        fastWriter.close();
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
