package JCoderAC.Lab5;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class L506 {
    private static final FastReader fastReader;
    private static final FastWriter fastWriter;
    private static final int[]a;
    static {
        fastReader=new FastReader(System.in);
        fastWriter=new FastWriter(System.out);
        a=new int[300000+10];
    }

    public static void main(String[] args) {
        int n= fastReader.nextInt();
        int q= fastReader.nextInt();
        int[]heights=new int[n];

        for (int i = 0; i < n; i++) {
            heights[i]= fastReader.nextInt();
        }

        for (int i = 0; i < q; i++) {
            int left = fastReader.nextInt()-1;
            int right = fastReader.nextInt()-1;
            fastWriter.println(getAnswer(left,right,heights));
            fastWriter.println(getAnswerByBruteForce(left,right,heights));
        }

        fastReader.close();
        fastWriter.close();
    }
    private static long getAnswerByBruteForce(int left,int right,int[]heights){
        long answer=0;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                if (i + 1 == j) {
                    /*System.out.println("i = " + i);
                    System.out.println("j = " + j);*/
                    answer++;
                    continue;
                }
                int min = Math.min(heights[i], heights[j]);
                boolean isOK = true;
                for (int k = i + 1; k <= j - 1; k++) {
                    if (heights[k] >= min) {
                        isOK = false;
                        break;
                    }
                }
                if(isOK){
                    /*System.out.println("i = " + i);
                    System.out.println("j = " + j);*/
                    answer++;
                }
            }
        }
        return answer;
    }

    private static long getAnswer(int left,int right,int[]heights){

        int top=left;
        long answer=0;
        int temp;

        a[left]=heights[left];
        for (int i = left+1; i <= right; i++) {
            temp=heights[i];
            if(a[top]>temp){
                a[++top]=temp;
                answer++;
            }else {
                int l=left,r=top;
                while (l<r){
                    int m=(l+r)/2;
                    if(r==l+1){
                        m=r;
                    }

                    if(a[m]<temp){
                        r=m-1;
                    }else {
                        l=m;
                    }
                }

                answer+=top-l+1;
                while (top>=left&&a[top]<=temp){
                    top--;
                }
                a[++top]=temp;
            }
        }
        return answer;
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
