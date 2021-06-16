package JCoderAC.Bonus;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class L1001 {
    private static final long MOD=10007;

    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);

        long a= fastReader.nextLong();
        long b= fastReader.nextLong();
        int k= fastReader.nextInt();
        int n= fastReader.nextInt();
        int m= fastReader.nextInt();

        fastWriter.println(getResult(a,b,k,n,m));

        fastReader.close();
        fastWriter.close();
    }
    private static long getResult(long a,long b,int k,int n,int m) {
        long[][]table=new long[1005][1005];
        for (int i = 0; i <= 1000; i++) {
            table[i][0] = 1;
        }
        table[1][1]=1;
        for (int i = 2; i <= 1000; i++) {
            for (int j = 1; j <= i; j++) {
                table[i][j]=(table[i-1][j]+table[i-1][j-1])%MOD;
            }
        }

        long t1=fastPower(a,n);
        long t2=fastPower(b,m);

        long t=t1*t2%MOD;

        return t*table[k][n]%MOD;
    }
    private static long fastPower(long a,long b) {
        long power = 1 % MOD;
        a %= MOD;

        while (b != 0) {
            if ((b & 1)!=0) {
                power = (power * a) % MOD;
            }

            a = (a * a) % MOD;
            b >>= 1;
        }

        return power;
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
