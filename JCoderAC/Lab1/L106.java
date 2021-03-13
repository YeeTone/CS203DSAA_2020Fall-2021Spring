package JCoderAC.Lab1;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class L106 {
    public static void main(String[] args) {
        FastReader fastReader = new FastReader(System.in);
        FastWriter fastWriter = new FastWriter(System.out);
        int n = fastReader.nextInt();
        char[] line1 = fastReader.next().toCharArray();
        char[] line2 = fastReader.next().toCharArray();

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
            fastWriter.println(-1);
        } else {
            fastWriter.println(rotate(line1,line2));
            //System.out.println(rotate_continuous(line1,line2));
        }

        fastReader.close();
        fastWriter.close();

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

    private static int rotate_bruteForce(char[] cs1, char[] cs2) {
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

    private static class FastReader implements Closeable{
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

        public boolean nextBoolean(){
            return Boolean.parseBoolean(next());
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
                System.exit(1);
            }

        }
    }

    private static class FastWriter implements Closeable{
        private final PrintWriter writer;

        public FastWriter(OutputStream out){
            this.writer=new PrintWriter(out);
        }

        public void print(Object object){
            writer.write(object.toString());
        }

        public void printf(String format,Object... os){
            writer.write(String.format(format,os));
        }

        public void println(){
            writer.write(System.lineSeparator());
        }

        public void println(Object object){
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