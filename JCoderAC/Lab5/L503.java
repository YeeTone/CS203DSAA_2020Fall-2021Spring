package JCoderAC.Lab5;

import java.math.*;
import java.util.*;
import java.io.*;

public class L503 {
    private static final int[]array;
    private static final int[][]stTable;
    private static final FastReader fastReader;
    private static final FastWriter fastWriter;
    static {
        array=new int[200010];
        stTable=new int[200010][30];
        fastReader=new FastReader(System.in);
        fastWriter=new FastWriter(System.out);
    }

    public static void main(String[] args) {
        int n= fastReader.nextInt();
        int m= fastReader.nextInt();

        for (int i = 1; i <= n; i++) {
            array[i]= fastReader.nextInt();
        }

        buildSTTable(n);
        for (int i = 0; i < m; i++) {
            int left = fastReader.nextInt();
            int right = fastReader.nextInt();
            fastWriter.println(getMaxValue(left, right));
        }

        fastReader.close();
        fastWriter.close();

    }
    private static void buildSTTable(int n){
        for (int i = 1; i <= n; i++) {
            stTable[i][0]= array[i];
        }

        for (int j = 1; j <= Math.log(n)/Math.log(2); j++) {
            for (int i = 1; i +(1<<j)-1<=n; i++) {
                stTable[i][j]=Math.max(stTable[i][j-1],stTable[i+(1<<(j-1))][j-1]);
            }
        }
    }

    private static int getMaxValue(int left, int right){
        int k=(int)(Math.log(right - left +1)/Math.log(2));
        return Math.max(stTable[left][k], stTable[right -(1<<k)+1][k]);
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
