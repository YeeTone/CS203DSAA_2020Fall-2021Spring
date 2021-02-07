package SUSTechACM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class L1010 {
    public static void main(String[] args) {
        FastScanner reader = new FastScanner(System.in);
        int x_min = reader.nextInt();
        int x_max = reader.nextInt();
        int y_min = reader.nextInt();
        int y_max = reader.nextInt();

        int px = reader.nextInt();
        int py = reader.nextInt();

        System.out.println((x_min < px && px < x_max && y_min < py & py < y_max) ? "Yes" : "No");
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
