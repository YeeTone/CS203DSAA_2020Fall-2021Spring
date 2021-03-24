package JCoderAC.Lab5;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class L504 {
    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);
        String inform;

        MyFreqStack freqStack=new MyFreqStack();

        whole:
        while (true){
            inform=fastReader.next();
            switch (inform.charAt(0)){
                case 'p':{
                    int candy= fastReader.nextInt();
                    freqStack.push(candy);
                    break;
                }
                case 'e':{
                    int x= freqStack.pop();
                    if(x==-1){
                        fastWriter.println("pa");
                    }else {
                        fastWriter.println(x);
                    }
                    break;
                }
                case 'n':{
                    break whole;
                }
            }

        }

        fastReader.close();
        fastWriter.close();
    }

    private static class MyFreqStack{
        HashMap<Integer,Integer>frequency_hashMap=new HashMap<>();
        HashMap<Integer,MyStack> group_hashMap=new HashMap<>();
        int maxFrequency=0;

        public void push(int x){
            int f;
            try{
                f=frequency_hashMap.get(x)+1;
            }catch (NullPointerException e){
                f=1;
            }
            frequency_hashMap.put(x,f);
            if(f>maxFrequency){
                maxFrequency=f;
            }
            group_hashMap.computeIfAbsent(f, z->new MyStack()).push(x);
        }

        public int pop(){
            try{
                int x= group_hashMap.get(maxFrequency).pop();
                frequency_hashMap.put(x,frequency_hashMap.get(x)-1);
                if(group_hashMap.get(maxFrequency).isEmpty()){
                    maxFrequency--;
                }
                return x;
            }catch (NullPointerException e){
                return -1;
            }
        }
    }

    private static class MyStack{
        private final int[]elements=new int[100];
        private int top=-1;

        public void push(int x){
            top++;
            elements[top]=x;
        }

        public int pop(){
            top--;
            return elements[top+1];
        }

        public boolean isEmpty(){
            return top==-1;
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
