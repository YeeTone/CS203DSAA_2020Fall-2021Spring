package SUSTechACM;

import java.math.*;
import java.io.*;
import java.util.*;

public class L1025 {
    public static void main(String[] args) {
        FastScanner fastScanner = new FastScanner(System.in);
        int t = fastScanner.nextInt();

        for (int i = 0; i < t; i++) {
            int n = fastScanner.nextInt();
            int[] array = new int[n];
            for (int j = 0; j < n; j++) {
                array[j]= fastScanner.nextInt();
            }

            int q= fastScanner.nextInt();
            for (int j = 0; j < q; j++) {
                int element= fastScanner.nextInt();
                System.out.println(getIndexByBinary(array,element));
            }
        }
    }
    private static int getIndexByBinary(int[]array,int element) {
        int low = 0, high = array.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid] == element) {
                return mid;
            } else if (array[mid] < element) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
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
