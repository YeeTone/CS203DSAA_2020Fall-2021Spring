package JCoderAC.Lab6;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class L601 {
    private static final FastReader fastReader=new FastReader(System.in);
    private static final FastWriter fastWriter=new FastWriter(System.out);
    public static void main(String[] args) throws InterruptedException {
        char[]str1=fastReader.next().toCharArray();
        char[]str2=fastReader.next().toCharArray();
        int[]next=getNext(str2);

        int start=0;
        while (start!=-1&&start<str1.length){
            start=kmpSearch(str1,str2,next,start);
        }
        fastWriter.println();
        for(int i:next){
            fastWriter.print(i+" ");
        }
        fastWriter.println();

        fastReader.close();
        fastWriter.close();

    }

    public static int kmpSearch(char[] str1, char[] str2,int[]next,int start) {
        for (int i = start, j = 0; i < str1.length; i++) {
            while (j > 0 && str1[i] != str2[j]) {
                j = next[j - 1];
            }

            if (str1[i] == str2[j]) {
                j++;
            }
            if (j == str2.length) {
                fastWriter.print((i-j+2)+" ");
                if(str2.length==1){
                    return i+1;
                }else {
                    return i;
                }
            }
        }
        return -1;
    }


    private static int[] getNext(char[]patternStr){
        int m=patternStr.length;
        int[]next=new int[m];
        next[0]=0;
        int k=0,j=1;

        while (j<m){
            if(patternStr[j]==patternStr[k]){
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
