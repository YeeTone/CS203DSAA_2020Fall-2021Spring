package CS203;

import java.io.*;
import java.util.*;

public class L201 {
    public static void main(String[] args) {
        FastScanner reader=new FastScanner(System.in);
        long a= reader.nextLong();
        long b= reader.nextLong();
        if(a==b){
            System.out.println(1);
            System.exit(0);
        }
        System.out.println((int)Math.ceil(Math.log(b-a+1)/Math.log(2)));
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
