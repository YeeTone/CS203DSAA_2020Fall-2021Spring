package JCoderAC.Lab5;

import java.io.*;
import java.math.*;
import java.util.*;

public class L505 {
    private static final MyDequeue maxDequeue;
    private static final MyDequeue minDequeue;
    static {
        maxDequeue=new MyDequeue();
        minDequeue=new MyDequeue();
    }
    public static void main(String[] args) throws IOException {
        StreamTokenizer in=new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        FastWriter fastWriter=new FastWriter(System.out);

        in.nextToken();
        int k=(int)in.nval;
        in.nextToken();
        int n=(int)in.nval;
        int[]array=new int[n];
        for (int i = 0; i < n; i++) {
            in.nextToken();
            array[i] = (int)in.nval;
        }
        answerProblem(array,n,k,fastWriter);

        fastWriter.close();
    }
    private static void answerProblem(int[]array,int n,int k,FastWriter fastWriter){
        int i=0;
        int max;
        int min;
        int len=0;

        for (int j = i; j < n&&i<n; j++) {
            while (!maxDequeue.isEmpty()&&array[j]>array[maxDequeue.getLast()]){
                maxDequeue.removeLast();
            }
            maxDequeue.addLast(j);

            while (!minDequeue.isEmpty()&&array[j]<array[minDequeue.getLast()]){
                minDequeue.removeLast();
            }
            minDequeue.addLast(j);

            max=array[maxDequeue.getFirst()];
            min=array[minDequeue.getFirst()];

            if(Math.abs(max-min)<=k){
                if((j-i+1)>len){
                    len=j-i+1;
                }
            }else {
                while (!maxDequeue.isEmpty()&&maxDequeue.getFirst()==i){
                    maxDequeue.removeFirst();
                }

                while (!minDequeue.isEmpty()&& minDequeue.getFirst()==i){
                    minDequeue.removeFirst();
                }
                i++;
            }
        }

        fastWriter.println(len);

    }

    private static class MyDequeue{
        ArrayList<Integer>elements=new ArrayList<>();

        public void addFirst(int x){
            elements.add(0,x);
        }

        public void addLast(int x){
            elements.add(x);
        }

        public int getFirst(){
            return elements.get(0);
        }

        public int getLast(){
            return elements.get(elements.size()-1);
        }

        public void removeFirst(){
            elements.remove(0);
        }

        public void removeLast(){
            elements.remove(elements.size()-1);
        }

        public boolean isEmpty(){
            return elements.isEmpty();
        }
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
                throw new RuntimeException();
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
