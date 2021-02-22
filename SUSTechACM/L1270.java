package SUSTechACM;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Stack;
import java.util.StringTokenizer;

public class L1270 {
    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);
        int t= fastReader.nextInt();

        for (int i = 1; i <= t; i++) {
            int n= fastReader.nextInt();
            int[]heights=new int[n];

            int[]answers_left=new int[n];
            int[]answers_right=new int[n];

            for (int j = 0; j < n; j++) {
                heights[j]= fastReader.nextInt();
            }

            Stack<Integer>indexStack=new Stack<>();
            Stack<Integer>numberStack=new Stack<>();

            for (int j = 0; j < n; j++) {
                getAnswers(heights, answers_left, indexStack, numberStack, j);
            }
            indexStack.clear();
            numberStack.clear();

            for (int j = n-1; j >= 0; j--) {
                getAnswers(heights, answers_right, indexStack, numberStack, j);
            }

            fastWriter.println("Case "+i+":");
            for (int j = 0; j < n; j++) {
                fastWriter.println(answers_left[j]+" "+answers_right[j]);
            }
        }
        fastReader.close();
        fastWriter.close();
    }

    private static void getAnswers(int[] heights, int[] answers_right, Stack<Integer> indexStack, Stack<Integer> numberStack, int j) {
        int rightIndex=0;
        while (!indexStack.isEmpty()&&numberStack.peek()<heights[j]){
            rightIndex=indexStack.pop();
            numberStack.pop();
        }
        indexStack.push(j+1);
        numberStack.push(heights[j]);

        answers_right[j]=rightIndex;
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
