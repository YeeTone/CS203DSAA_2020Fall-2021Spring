package CS203;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class L103 {
    public static void main(String[] args) {
        FastScanner reader=new FastScanner(System.in);
        int t= reader.nextInt();
        for (int i = 0; i < t; i++) {
            int n=reader.nextInt();
            int k=reader.nextInt();
            long[]numbers=new long[n];
            long[]groups=new long[k];
            for (int j = 0; j < n; j++) {
                numbers[j]=Long.parseLong(reader.next()) ;
            }
            for (int j = 0; j < k; j++) {
                groups[j]= Long.parseLong(reader.next());
            }
            Arrays.sort(numbers);
            Arrays.sort(groups);
            //System.out.println(Arrays.toString(groups));

            //System.out.println(Arrays.toString(groups));
            int left=0;
            int right=numbers.length-1;

            long sum=0;
            for (long group : groups) {
                if (group == 1) {
                    sum += numbers[right] * 2;
                    right -= 1;
                } else {
                    break;
                }
            }
            for (int j = groups.length-1; j >= 0; j--) {
                if(groups[j]==1){
                    break;
                }else {
                    sum+=numbers[right];
                    right-=1;
                    sum+=numbers[left];
                    left+=groups[j]-1;
                }
            }
            System.out.println(sum);
        }
    }
    private static class FastScanner {
        private final BufferedReader br;
        private StringTokenizer st;

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
    }
}
