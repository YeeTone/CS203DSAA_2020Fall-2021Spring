package SUSTechACM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class L1368 {
    public static void main(String[] args) {
        FastScanner fastScanner=new FastScanner(System.in);
        int n= fastScanner.nextInt();
        int m= fastScanner.nextInt();

        long[]array=new long[n];
        for (int i = 0; i < n; i++) {
            array[i]= fastScanner.nextLong();
        }

        Long[]sum=new Long[n];
        long pre=0;
        for (int i = n-1; i >= 0; i--) {
            sum[i]=array[i]+pre;
            pre=sum[i];
        }
        Arrays.sort(sum, 1, n, (o1, o2) -> {
            if(o1.equals(o2)){
                return 0;
            }
            return o1>o2?-1:1;
        });

        long result=0;
        for (int i = 0; i < m; i++) {
            result+=sum[i];
        }
        System.out.println(result);
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
