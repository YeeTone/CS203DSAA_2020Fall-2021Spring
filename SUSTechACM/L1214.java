package SUSTechACM;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class L1214 {
    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        int t=fastReader.nextInt();
        for (int i = 0; i < t; i++) {
            int n= fastReader.nextInt();
            char[]brackets=fastReader.next().toCharArray();
            System.out.println(checkIsOK(n,brackets)?"YES":"NO");
        }
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
        private char[]elements=new char[MAX_SIZE];
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

    private static class FastReader implements Closeable {
        BufferedReader br;
        StringTokenizer st;

        public FastReader(InputStream in) {
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

        public void close()throws IOException{
            st=null;
            br.close();
        }
    }

    private static class FastWriter implements Closeable {
        private final BufferedWriter writer;
        public FastWriter(OutputStream out){
            this.writer=new BufferedWriter(new OutputStreamWriter(out));
        }

        public void print(Object object){
            try{
                writer.write(object.toString());
                writer.flush();
            }catch (IOException e){
                e.printStackTrace();
                System.exit(1);
            }

        }
        public void printf(String format,Object... os) {
            try{
                writer.write(String.format(format,os));
                writer.flush();
            }catch (IOException e){
                e.printStackTrace();
                System.exit(1);
            }

        }
        public void println(){
            try{
                writer.write(System.lineSeparator());
                writer.flush();
            }catch (IOException e){
                e.printStackTrace();
                System.exit(1);
            }
        }

        public void println(Object object) {
            try{
                writer.write(object.toString());
                writer.write(System.lineSeparator());
                writer.flush();
            }catch (IOException e){
                e.printStackTrace();
                System.exit(1);
            }

        }

        @Override
        public void close()throws IOException{
            writer.close();
        }
    }
}