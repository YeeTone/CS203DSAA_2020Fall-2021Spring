package SUSTechACM;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class L1215 {
    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        int m= fastReader.nextInt();
        Queue<Long>queue=new LinkedList<>();
        Deque<Long> maxQueue=new LinkedList<>();

        long result=0;
        int count=0;

        while (true) {
            long value = fastReader.nextLong();
            if (value == -1) {
                break;
            }
            count+=1;
            if(count<m){
                queue.offer(value);

                int pollCount=0;
                while (maxQueue.peekLast()!=null&&maxQueue.peekLast()<value){
                    pollCount+=1;
                    maxQueue.pollLast();
                }

                for (int i = 0; i < pollCount+1; i++) {
                    maxQueue.offer(value);
                }
            }else {
                queue.offer(value);
                queue.poll();
                int pollCount=0;
                while (maxQueue.peekLast()!=null&&maxQueue.peekLast()<value){
                    pollCount+=1;
                    maxQueue.pollLast();
                }

                for (int i = 0; i < pollCount+1; i++) {
                    maxQueue.offer(value);
                }
                //System.out.println("MaxValue="+maxQueue.peek());
                result^=maxQueue.poll();
            }
        }
        System.out.println(result);
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
