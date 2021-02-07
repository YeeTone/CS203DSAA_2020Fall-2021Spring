package SUSTechACM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class L1367 {
    public static void main(String[] args) {
        FastScanner fastScanner=new FastScanner(System.in);
        int n= fastScanner.nextInt();

        long x1= fastScanner.nextLong();
        long x2= fastScanner.nextLong();

        LineIntersection[]lineIntersections=new LineIntersection[n];
        for (int i = 0; i < n; i++) {
            long k= fastScanner.nextLong();
            long b= fastScanner.nextLong();
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
        System.out.println(success?"YES":"NO");
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

    private static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner(InputStream in) {
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
            }

        }
    }
}
