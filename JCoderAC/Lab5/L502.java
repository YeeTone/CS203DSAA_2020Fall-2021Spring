package JCoderAC.Lab5;

import java.math.*;
import java.util.*;
import java.io.*;

public class L502 {
    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);
        int n= fastReader.nextInt();
        int m= fastReader.nextInt();
        MyDequeue[]dequeues=new MyDequeue[n];
        for (int i = 0; i < n; i++) {
            dequeues[i]=new MyDequeue();
        }

        for (int i = 0; i < m; i++) {
            int op= fastReader.nextInt();
            switch (op){
                case 1:{
                    int u= fastReader.nextInt();
                    int w= fastReader.nextInt();
                    int val= fastReader.nextInt();
                    insert(dequeues,u,w,val);
                    break;
                }
                case 2:{
                    int u= fastReader.nextInt();
                    int w= fastReader.nextInt();
                    search_delete(dequeues,u,w,fastWriter);
                    break;
                }
                case 3:{
                    int u= fastReader.nextInt();
                    int v= fastReader.nextInt();
                    int w= fastReader.nextInt();
                    link(dequeues,u,v,w);
                    break;
                }
            }
        }

        fastReader.close();
        fastWriter.close();

    }
    private static void insert(MyDequeue[]dequeues,int u,int w,int val){
        if(w==0){
            dequeues[u-1].addFirst(val);
        }else {
            dequeues[u-1].addLast(val);
        }
    }

    private static void search_delete(MyDequeue[]dequeues,int u,int w,FastWriter fastWriter){
        if(w==0){
            fastWriter.println(dequeues[u-1].getFirst());
            dequeues[u-1].removeFirst();
        }else {
            fastWriter.println(dequeues[u-1].getLast());
            dequeues[u-1].removeLast();
        }
    }

    private static void link(MyDequeue[]dequeues,int u,int v,int w){
        if(w==0){
            dequeues[u-1].linkAll(dequeues[v-1]);
        }else {
            dequeues[v-1].reverse();
            dequeues[u-1].linkAll(dequeues[v-1]);
        }
    }

    private static class MyDequeue{
        ArrayList<Integer>elements=new ArrayList<>();

        public void reverse(){
            Collections.reverse(elements);
        }

        public int getFirst(){
            if(elements.isEmpty()){
                return -1;
            }

            return elements.get(0);
        }

        public int getLast(){
            if(elements.isEmpty()){
                return -1;
            }

            return elements.get(elements.size()-1);
        }

        public void addFirst(int x){
            elements.add(0,x);
        }

        public void addLast(int x){
            elements.add(x);
        }

        public void removeFirst(){
            if(!elements.isEmpty()){
                elements.remove(0);
            }
        }

        private void removeLast(){
            if(!elements.isEmpty()){
                elements.remove(elements.size()-1);
            }

        }

        public void linkAll(MyDequeue toBeLinked){
            elements.addAll(toBeLinked.elements);
            toBeLinked.elements.clear();
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
            while (!st.hasMoreTokens()) {
                String s = nextLine();
                if (s == null) return false;
                eat(s);
            }
            return true;
        }

        public String next() {
            hasNext();
            return st.nextToken();
        }

        public boolean nextBoolean() {
            return Boolean.parseBoolean(next());
        }


        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public float nextFloat() {
            return Float.parseFloat(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecimal() {
            return new BigDecimal(next());
        }

        public void close() {
            try {
                st = null;
                br.close();
            } catch (IOException e) {
                throw new RuntimeException();
            }

        }
    }

    private static class FastWriter implements Closeable {
        private final PrintWriter writer;

        public FastWriter(OutputStream out) {
            this.writer = new PrintWriter(out);
        }

        public void print(Object object) {
            writer.write(object.toString());
        }

        public void printf(String format, Object... os) {
            writer.write(String.format(format, os));
        }

        public void println() {
            writer.write(System.lineSeparator());
        }

        public void println(Object object) {
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
