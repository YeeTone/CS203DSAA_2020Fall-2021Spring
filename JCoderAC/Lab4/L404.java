package JCoderAC.Lab4;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class L404 {
    private static final FastReader fastReader;
    private static final FastWriter fastWriter;
    private static NumberNode first;
    static {
        fastReader = new FastReader(System.in);
        fastWriter = new FastWriter(System.out);
    }

    public static void main(String[] args) throws InterruptedException {
        int t = fastReader.nextInt();
        for (int s = 0; s < t; s++) {
            int n= fastReader.nextInt();
            NumberNode[]nodes=new NumberNode[n];
            for (int i = 0; i < n; i++) {
                int value= fastReader.nextInt();
                nodes[i]=new NumberNode(value);
            }

            for (int i = 0; i < n; i++) {
                try{
                    if(i==0){
                        nodes[i].pre=null;
                        nodes[i].next=nodes[i+1];
                    }else if(i==n-1){
                        nodes[i].next=null;
                        nodes[i].pre=nodes[i-1];
                    }else {
                        nodes[i].next=nodes[i+1];
                        nodes[i].pre=nodes[i-1];
                    }
                }catch (ArrayIndexOutOfBoundsException e){
                    break;
                }

            }
            first=nodes[0];
            removeAllDecreasing();
            printAll();
        }

        fastReader.close();
        fastWriter.close();

    }
    private static void removeAllDecreasing(){
        while (!checkIsAllOK()){
            NumberNode temp=first;
            NumberNode decreasingStart=null;
            NumberNode decreasingEnd=null;
            while (temp!=null){
                if(isDecreasing(temp)&&decreasingStart==null){
                    decreasingStart=temp;
                }else if(!isDecreasing(temp)&&decreasingStart!=null){
                    decreasingEnd=temp;
                }
                temp=temp.next;

                if(decreasingStart!=null&&decreasingEnd!=null){
                    /*fastWriter.println("Start:"+decreasingStart.value);
                    fastWriter.println("End:"+decreasingEnd.value);*/
                    if(decreasingStart.pre==null){
                        if(decreasingEnd.next==null){
                            first=null;
                            return;
                        }else {
                            first=decreasingEnd.next;
                            first.pre=null;
                            temp=first;
                        }
                    }else {
                        if(decreasingEnd.next==null){
                            decreasingStart.pre.next=null;
                            temp=null;
                        }else {
                            decreasingStart.pre.next=decreasingEnd.next;
                            decreasingEnd.next.pre=decreasingStart.pre;
                            temp=decreasingEnd.next;
                        }
                    }

                    decreasingStart=null;
                    decreasingEnd=null;
                }
            }
        }
    }
    private static boolean isDecreasing(NumberNode temp) {
        if (temp == null) {
            return false;
        }
        if (temp.next == null) {
            return false;
        }
        return temp.value > temp.next.value;

    }
    private static boolean checkIsAllOK(){
        NumberNode temp=first;
        while (temp!=null){
            if(temp.next!=null){
                if(temp.value>temp.next.value){
                    return false;
                }
            }
            temp=temp.next;
        }
        return true;
    }

    private static void printAll(){
        NumberNode temp=first;
        while (temp!=null){
            fastWriter.print(temp.value+" ");
            temp=temp.next;
        }
        fastWriter.println();
    }

    private static class NumberNode{
        int value;
        NumberNode pre;
        NumberNode next;
        boolean removed;

        public NumberNode(int v){
            this.value=v;
            this.removed=false;
        }
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
            while (!st.hasMoreTokens()) {
                String s = nextLine();
                if (s == null) {
                    return false;
                }
                eat(s);
            }
            return true;
        }

        public String next() {
            hasNext();
            return st.nextToken();
        }

        public boolean nextBoolean() {
            return Boolean.parseBoolean(next());
        }


        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public float nextFloat() {
            return Float.parseFloat(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecimal() {
            return new BigDecimal(next());
        }

        public void close() {
            try {
                st = null;
                br.close();
            } catch (IOException e) {
                throw new RuntimeException();
            }

        }
    }

    private static class FastWriter implements Closeable {
        private final PrintWriter writer;

        public FastWriter(OutputStream out) {
            this.writer = new PrintWriter(out);
        }

        public void print(Object object) {
            writer.write(object.toString());
        }

        public void printf(String format, Object... os) {
            writer.write(String.format(format, os));
        }

        public void println() {
            writer.write(System.lineSeparator());
        }

        public void println(Object object) {
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
