package SUSTechACM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class L1011 {
    public static void main(String[] args) {
        FastScanner fastScanner=new FastScanner(System.in);
        int n= fastScanner.nextInt();
        int[]array=new int[n];
        for (int i = 0; i < n; i++) {
            array[i]= fastScanner.nextInt();
        }
        Arrays.sort(array);
        for (int i = 0; i < n; i++) {
            System.out.print(array[i]);
            if(i!=n-1){
                System.out.print(" ");
            }
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
    }
}
