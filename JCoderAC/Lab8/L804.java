package JCoderAC.Lab8;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class L804 {
    private static final StreamTokenizer in
            =new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    private static int nextInt(){
        try{
            in.nextToken();
            return (int)in.nval;
        }catch (IOException e){
            return -1;
        }
    }
    private static long nextLong(){
        try{
            in.nextToken();
            return (long)in.nval;
        }catch (IOException e){
            return -1;
        }
    }

    private static final FastWriter fastWriter=new FastWriter(System.out);

    public static void main(String[] args) {

        int n=nextInt();
        int k=nextInt();
        int s=nextInt();

        long lastAnswer=s;

        MyPriorityQueue myPriorityQueue=new MyPriorityQueue(k);
        ArrayList<Long>list=new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            long num=a(i+lastAnswer);

            if(myPriorityQueue.size()<k){
                myPriorityQueue.push(num);
            }else {
                if(myPriorityQueue.getMin()<num){
                    myPriorityQueue.popMin();
                    myPriorityQueue.push(num);
                }
            }

            if(i%100==0){
                fastWriter.print(myPriorityQueue.getMin()+" ");
                lastAnswer=myPriorityQueue.getMin();
            }
        }

        fastWriter.close();
    }

    private static long a(long x){
        long result =x;
        while (x!=0){
            result +=x%10;
            x/=10;
        }
        return result;
    }

    private static class MyPriorityQueue{
        long[] elements;
        int size;
        int capacity;

        public int size(){
            return size;
        }

        public MyPriorityQueue(int capacity){
            this.capacity=capacity;
            this.size=0;
            this.elements=new long[capacity+1];
        }

        public void swap(int x,int y){
            long temp=elements[x];
            elements[x]=elements[y];
            elements[y]=temp;
        }

        private void smallAdjust(int index){
            while (index>1){
                if(elements[index]<elements[index/2]){
                    swap(index,index/2);
                    index/=2;
                }else {
                    break;
                }
            }
        }

        private void bigAdjust(){
            int index=1;
            while (2*index<=size){
                int min_index=index;
                if(2*index<=size){
                    min_index=elements[min_index]>elements[2*index]?2*index:min_index;
                }

                if(2*index+1<=size){
                    min_index=elements[min_index]>elements[2*index+1]?(2*index+1):min_index;
                }

                if(min_index!=index){
                    swap(min_index,index);
                    index=min_index;
                }else {
                    break;
                }
            }
        }

        public void popMin(){
            swap(1,size);
            size--;
            bigAdjust();;
        }

        public long getMin(){
            return elements[1];
        }

        public void push(long x){
            elements[++size]=x;
            smallAdjust(size);
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
