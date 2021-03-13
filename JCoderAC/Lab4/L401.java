package JCoderAC.Lab4;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class L401 {
    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);
        int t= fastReader.nextInt();
        for (int i = 0; i < t; i++) {
            int n= fastReader.nextInt();
            int m= fastReader.nextInt();
            killAllPeopleSlow(n,m,fastWriter);
        }
        fastReader.close();
        fastWriter.close();
    }

    private static void killAllPeopleSlow(int n, int m,FastWriter fastWriter){
        Person[]people=new Person[n];
        for (int i = 0; i < n; ++i) {
            people[i]=new Person(i+1);
        }
        if(n==1){
            people[0].pre=people[0];
            people[0].next=people[0];
        }else {
            for (int i = 0; i < n; ++i) {
                if(i==0){
                    people[i].pre=people[n-1];
                    people[i].next=people[i+1];
                }else if(i==n-1){
                    people[i].pre=people[i-1];
                    people[i].next=people[0];
                }else {
                    people[i].pre=people[i-1];
                    people[i].next=people[i+1];
                }
            }
        }

        Person temp=people[0];
        int size=n;
        while (size>0){
            int count=0;
            while (count<m){
                ++count;
                if(count==m){
                    break;
                }
                temp=temp.next;
            }
            fastWriter.print(temp.index);
            if(size!=1){
                fastWriter.print(" ");
            }
            temp.pre.next=temp.next;
            temp.next.pre=temp.pre;
            temp=temp.next;
            --size;
        }
        fastWriter.println();
    }
    private static class Person{
        Person pre;
        Person next;
        int index;

        public Person(int i){
            index=i;
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
