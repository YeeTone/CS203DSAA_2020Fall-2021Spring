package SUSTechACM;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class L1367 {
    public static void main(String[] args) {
        FastReader fastReader =new FastReader(System.in);
        FastWriter fastWriter =new FastWriter(System.out);
        int n= fastReader.nextInt();

        long x1= fastReader.nextLong();
        long x2= fastReader.nextLong();

        LineIntersection[]lineIntersections=new LineIntersection[n];
        for (int i = 0; i < n; i++) {
            long k= fastReader.nextLong();
            long b= fastReader.nextLong();
            lineIntersections[i]=new LineIntersection(x1,x2,k,b);
        }

        Arrays.sort(lineIntersections);
        boolean success=false;
        for (int i = 0; i < n-1; i++) {
            if(lineIntersections[i].rightVal>lineIntersections[i+1].rightVal
                    &&lineIntersections[i].leftVal!=lineIntersections[i+1].leftVal){
                success=true;
                break;
            }
        }
        fastWriter.println(success?"YES":"NO");

        fastReader.close();
        fastWriter.close();
    }
    private static class LineIntersection implements Comparable{
        private final long leftVal;
        private final long rightVal;
        public LineIntersection(long x1,long x2,long k,long b){
            this.leftVal=k*x1+b;
            this.rightVal=k*x2+b;
        }

        @Override
        public String toString() {
            return "LineIntersection{" +
                    "leftVal=" + leftVal +
                    ", rightVal=" + rightVal +
                    '}';
        }

        @Override
        public int compareTo(Object o) {
            if(o instanceof LineIntersection){
                LineIntersection o2=(LineIntersection)o;
                if(this.leftVal!=o2.leftVal){
                    return this.leftVal>o2.leftVal?1:-1;
                }else {
                    return this.rightVal>o2.rightVal?1:-1;
                }
            }
            return 0;
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
