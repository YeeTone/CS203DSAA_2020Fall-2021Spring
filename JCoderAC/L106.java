package CS203;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class L106 {
    public static void main(String[] args) {
        FastScanner reader = new FastScanner(System.in);
        int n = reader.nextInt();
        char[] line1 = reader.next().toCharArray();
        char[] line2 = reader.next().toCharArray();

        int countA1 = 0;
        int countA2 = 0;
        for (int i = 0; i < n; i++) {
            if (line1[i] == 'A') {
                countA1++;
            }

            if (line2[i] == 'A') {
                countA2++;
            }
        }
        if (countA1 != countA2) {
            System.out.println(-1);
        } else {
            System.out.println(rotate(line1,line2));
            //System.out.println(rotate_continuous(line1,line2));
        }
    }
    private static int rotate(char[]cs1,char[]cs2){
        int diff=0;
        for (int i=0;i<cs1.length;i++){
            if(cs1[i]!=cs2[i]){
                diff++;
            }
        }

        int[]zero_ones=new int[diff];
        for (int i = 0,count = 0; i < cs1.length; i++) {
            if(cs1[i]!=cs2[i]){
                if(cs1[i]=='A'){
                    zero_ones[count++]=1;
                }else {
                    zero_ones[count++]=0;
                }
            }
        }

        int c0=0,c1=0;
        for (int i = 0; i < diff; i++) {
            if(zero_ones[i]==1){
                if(c0==0){
                    c1++;
                }else {
                    c0--;
                    c1++;
                }
            }else {
                if(c1==0){
                    c0++;
                }else {
                    c1--;
                    c0++;
                }
            }
        }
        return c0+c1;
    }

    private static int rotate_continuous(char[] cs1, char[] cs2) {
        int count = 0;
        while (true) {
            char pre = '\0';
            int first = -1;
            for (int i = 0; i < cs1.length; i++) {
                if (cs1[i] != cs2[i]) {
                    if (first == -1) {
                        first = i;
                        pre = cs1[i];
                    } else {
                        char temp = cs1[i];
                        cs1[i] = pre;
                        pre = temp;
                    }
                }
            }

            if (first == -1) {
                break;
            } else {
                cs1[first] = pre;
                count++;
            }
        }
        return count;
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