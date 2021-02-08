package SUSTechACM;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class L1354 {
    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);
        int t= fastReader.nextInt();

        for (int i = 0; i < t; i++) {
            StringBuilder b=new StringBuilder(fastReader.next());
            int n= fastReader.nextInt();
            for (int j = 0; j < n; j++) {
                int op= fastReader.nextInt();
                if(op==1){
                    char c=fastReader.next().charAt(0);
                    int index= fastReader.nextInt();
                    b.insert(index-1,c);
                }else {
                    int index=fastReader.nextInt();
                    fastWriter.println(b.charAt(index-1));
                }
            }

            /*CharNode head=new CharNode();

            CharNode temp=head;
            for (int j = 0; j < base.length; j++) {
                temp.value=base[j];
                if(j!= base.length-1){
                    temp.next=new CharNode();
                    temp=temp.next;
                }
            }

            int n= fastReader.nextInt();
            for (int j = 0; j < n; j++) {
                temp=head;
                int op= fastReader.nextInt();
                if(op==1){
                    char c=fastReader.next().charAt(0);
                    int index= fastReader.nextInt();

                    CharNode otherNode=new CharNode();
                    otherNode.value=c;

                    if(index==1){
                        otherNode.next=head;
                        head=otherNode;
                    }else {
                        int a=0;
                        while (index-1>a+1){
                            temp=temp.next;
                            a+=1;
                        }
                        otherNode.next=temp.next;
                        temp.next=otherNode;
                    }


                }else {
                    int index= fastReader.nextInt();
                    int a=0;
                    while (index-1>a){
                        temp=temp.next;
                        a+=1;
                    }

                    System.out.println(temp.value);
                }
                //head.printLinkedList();
            }*/

        }
    }
    private static class CharNode{
        char value;
        CharNode next;

        void printLinkedList(){
            CharNode temp=this;
            while (temp!=null){
                System.out.print(temp.value);
                temp=temp.next;
            }
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
