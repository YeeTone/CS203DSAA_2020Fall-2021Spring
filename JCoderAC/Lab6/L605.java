package JCoderAC.Lab6;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class L605 {
    public static void main(String[] args) {
        FastReader fastReader = new FastReader(System.in);
        FastWriter fastWriter = new FastWriter(System.out);

        char[]codeTable=new char[26];
        for (int i = 0; i < 26; i++) {
            codeTable[i]=fastReader.next().charAt(0);
        }
        char[] encodedStr = fastReader.next().toCharArray();

        fastWriter.println(getAnswer(encodedStr,codeTable));

        fastReader.close();
        fastWriter.close();
    }
    private static int getAnswer(char[]encoded,char[]codeTable){
        char[]translated_12=translate_1_2(encoded,codeTable);
        int[]next=getNext(encoded,translated_12);
        return encoded.length-next[encoded.length-1];
    }

    private static char[] translate_1_2(char[]encoded,char[]decodeTable){
        char[] translated = encoded.clone();
        int place_1_2= (encoded.length+1)/2;

        for (int i = place_1_2; i < translated.length; i++) {
            char curChar=encoded[i];
            char tr=decodeTable[curChar-'a'];
            translated[i]=tr;
        }

        return translated;
    }

    private static int[]getNext(char[]encoded,char[]translated){
        int v_1_2=(encoded.length+1)/2;
        int m = encoded.length;
        int[]next=new int[m];
        next[0]=0;
        int k=0;
        int j=v_1_2;
        while (j<m){
            if(encoded[k]==translated[j]){
                k++;
                next[j]=k;
                j++;
            }else if(k==0){
                next[j]=0;
                j++;
            }else {
                k=next[k-1];
            }
        }

        return next;
    }

    private static class FastReader implements Closeable {
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
                throw new RuntimeException();
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
