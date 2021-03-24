package JCoderAC.Lab5;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class L501 {
    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);
        int t=fastReader.nextInt();
        for (int i = 0; i < t; i++) {
            int n= fastReader.nextInt();
            char[]brackets=fastReader.next().toCharArray();
            fastWriter.println(checkIsOK(n,brackets)?"YES":"NO");
        }

        fastReader.close();
        fastWriter.close();
    }
    private static boolean checkIsOK(int n,char[]brackets){
        MyStack bracketStack=new MyStack();
        for (char b:brackets){
            switch (b){
                case '(':
                case'[':
                case'{':{
                    bracketStack.push(b);
                    break;
                }

                case ')':{
                    char top=bracketStack.pop();
                    if(top!='('){
                        return false;
                    }
                    break;
                }
                case ']':{
                    char top=bracketStack.pop();
                    if(top!='['){
                        return false;
                    }
                    break;
                }
                case '}':{
                    char top=bracketStack.pop();
                    if(top!='{'){
                        return false;
                    }
                    break;
                }
            }
        }
        return bracketStack.isEmpty();
    }
    private static class MyStack{
        private static final int MAX_SIZE=30000+10;
        private final char[]elements=new char[MAX_SIZE];
        private int top=-1;

        public boolean push(char x){
            if(top==MAX_SIZE){
                return false;
            }else {
                top++;
                elements[top]=x;
                return true;
            }
        }

        public char pop(){
            if(top==-1){
                return '\0';
            }else {
                top--;
                return elements[top+1];
            }
        }
        public boolean isEmpty(){
            return top==-1;
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
