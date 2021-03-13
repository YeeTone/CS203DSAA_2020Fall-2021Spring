package JCoderAC.Lab1;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class L101 {
    public static void main(String[] args) {
        FastReader fastReader = new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);
        String number1= fastReader.next();
        String number2= fastReader.next();
        fastWriter.println(multiplication(number1,number2));

        fastReader.close();
        fastWriter.close();
    }
    private static String multiplication(String s1,String s2){
        try{
            if(Integer.parseInt(s1)==0){
                return "0";
            }
        }catch (Throwable ignored){

        }

        try{
            if(Integer.parseInt(s2)==0){
                return "0";
            }
        }catch (Throwable ignored){

        }
        boolean aNeg=(s1.charAt(0)=='-');
        boolean bNeg=(s2.charAt(0)=='-');
        if(aNeg){
            if(bNeg){
                return multiplication(s1.substring(1),s2.substring(1));
            }else{
                return '-'+multiplication(s1.substring(1),s2);
            }
        }else {
            if (bNeg) {
                return '-'+multiplication(s1, s2.substring(1));
            } else {
                int length = Math.max(s1.length(), s2.length());
                StringBuilder s1Builder = new StringBuilder(s1);
                for (int i = s1.length(); i < length; i++) {
                    s1Builder.insert(0, '0');
                }
                s1 = s1Builder.toString();

                StringBuilder s2Builder = new StringBuilder(s2);
                for (int i = s2.length(); i < length; i++) {
                    s2Builder.insert(0, '0');
                }
                s2 = s2Builder.toString();

                int resultLength = s1.length() + s2.length();
                long[] result = new long[resultLength];
                for (int i = s1.length() - 1; i >= 0; i--) {
                    for (int j = s2.length() - 1; j >= 0; j--) {
                        result[i+j+1]+=(s1.charAt(i)-'0')*(s2.charAt(j)-'0');
                    }
                }
                //System.out.println(Arrays.toString(result));
                for (int i = resultLength-1; i > 0; i--) {
                    result[i-1]+=(result[i]/10);
                    result[i]%=10;
                }

                boolean isZero=true;
                StringBuilder b=new StringBuilder();
                for (long j : result) {
                    if (j != 0) {
                        isZero = false;
                    }
                    if (!isZero) {
                        b.append(j);
                    }
                }

                if(isZero){
                    return "0";
                    //System.exit(0);
                }else {
                    return b.toString();
                }
            }
        }
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
