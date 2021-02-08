package SUSTechACM;


import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class L1269 {
    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        int n= fastReader.nextInt();

        RingQueue ringQueue=new RingQueue();
        for (int i = 0; i < n; i++) {
            char c=fastReader.next().charAt(0);
            switch (c){
                case 'E':{
                    int x= fastReader.nextInt();
                    ringQueue.enQueue(x);
                    break;
                }
                case 'D':{
                    ringQueue.deQueue();
                    break;
                }
                case 'A':{
                    System.out.println(ringQueue.peek());
                    break;
                }
            }
        }

        while (!ringQueue.isEmpty()){
            System.out.print(ringQueue.peek()+" ");
            ringQueue.deQueue();
        }
    }
    private static class RingQueue{
        private static final int MAX_SIZE=20000000+10;
        private int front=0;
        private int rear=0;
        private final int[]elements=new int[MAX_SIZE];

        public boolean enQueue(int x){
            if(rear<MAX_SIZE){
                elements[rear]=x;
                rear=(rear+1)%MAX_SIZE;
                return true;
            }
            return false;
        }

        public boolean deQueue(){
            if(front<rear){
                front=(front+1)%MAX_SIZE;
                return true;
            }else {
                return false;
            }
        }

        public int peek(){
            return elements[front];
        }

        public boolean isEmpty(){
            return front==rear;
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
