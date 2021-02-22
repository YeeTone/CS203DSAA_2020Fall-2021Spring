package SUSTechACM;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Stack;
import java.util.StringTokenizer;

public class L1300 {
    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);

        int n= fastReader.nextInt();
        int m= fastReader.nextInt();

        Stack<Integer>treasureStack=new Stack<>();
        Stack<Integer>maxValueStack=new Stack<>();
        for (int i = 0; i < n; i++) {
            int ai= fastReader.nextInt();
            if(maxValueStack.isEmpty()||maxValueStack.peek()<=ai){
                maxValueStack.push(ai);
            }
            treasureStack.push(ai);
        }

        for (int i = 0; i < m; i++) {
            char operation=fastReader.next().charAt(0);
            switch (operation){
                case 'P':{
                    int x= fastReader.nextInt();
                    if(maxValueStack.isEmpty()||maxValueStack.peek()<=x){
                        maxValueStack.push(x);
                    }
                    treasureStack.push(x);
                    break;
                }
                case 'R':{
                    int peek=treasureStack.pop();
                    if(peek==maxValueStack.peek()){
                        maxValueStack.pop();
                    }
                    break;
                }
                case 'Q':{
                    fastWriter.println(maxValueStack.peek());
                    break;
                }
            }
        }
        fastReader.close();
        fastWriter.close();
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
