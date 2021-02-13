package CS203;

import java.io.*;
import java.util.*;

public class L202 {
    public static void main(String[] args) {
        FastScanner reader=new FastScanner(System.in);
        int n= reader.nextInt();
        int m= reader.nextInt();

        long[]array=new long[n];
        for (int i = 0; i < n; i++) {
            array[i]= reader.nextLong();
        }
        for (int i = 0; i < m; i++) {
            long v= reader.nextLong();
            boolean b=(Arrays.binarySearch(array,v)>=0);
            if(b){
                System.out.println("YES");
            }else {
                System.out.println("NO");
            }
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
    }
}
