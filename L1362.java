package SUSTechACM;

import java.math.*;
import java.io.*;
import java.util.*;

public class L1362 {
    public static void main(String[] args) {
        FastScanner fastScanner=new FastScanner(System.in);
        int t= fastScanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n= fastScanner.nextInt();
            int m= fastScanner.nextInt();

            int[]array1=new int[n];
            for (int j = 0; j < n; j++) {
                array1[j]= fastScanner.nextInt();
            }

            int[]array2=new int[m];
            for (int j = 0; j < m; j++) {
                array2[j]= fastScanner.nextInt();
            }

            int ptr1=0,ptr2=0;
            for (int k = 0; k < n+m; k++) {
                if(ptr1<n&&(ptr2>=m||array1[ptr1]<=array2[ptr2])){
                    System.out.print(array1[ptr1]);
                    ptr1+=1;
                }else {
                    System.out.print(array2[ptr2]);
                    ptr2+=1;
                }
                if(k!=n+m-1){
                    System.out.print(" ");
                }
            }
            System.out.println();
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
