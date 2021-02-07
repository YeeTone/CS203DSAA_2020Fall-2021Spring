package SUSTechACM;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class L1370 {
    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);

        int n= fastReader.nextInt();
        int m= fastReader.nextInt();

        LinkedNode head=new LinkedNode();
        LinkedNode temp=head;
        for (int i = 0; i < n; i++) {
            temp.value=fastReader.nextInt();
            if(i!=n-1){
                temp.next=new LinkedNode();
                temp=temp.next;
            }
        }

        for (int i = 0; i < m; i++) {
            char c=fastReader.next().charAt(0);
            switch(c){
                case 'i':{
                    int p= fastReader.nextInt();
                    int v= fastReader.nextInt();
                    head=insert(head,p,v);
                    break;
                }
                case 'r':{
                    int p= fastReader.nextInt();
                    head=remove(head,p);
                    break;
                }
                case 'q':{
                    int p= fastReader.nextInt();
                    LinkedNode query=query(head,p);
                    fastWriter.println(query.value);
                    break;
                }

            }
            //head.printLinkedList();
            //System.out.println("------------");
        }
    }
    private static LinkedNode insert(LinkedNode head,int position,int value){
        LinkedNode newNode=new LinkedNode();
        newNode.value=value;

        if(position==0){
            newNode.next=head;
            head=newNode;
            return head;
        }else {
            LinkedNode p=head;
            int a=0;
            while (position-1>a+1){
                p=p.next;
                a+=1;
            }

            LinkedNode temp=p.next;
            p.next=newNode;
            newNode.next=temp;

            return head;
        }
    }

    private static LinkedNode remove(LinkedNode head,int position){
        if(position==0){
            LinkedNode next=head.next;
            head.next=null;
            return next;
        }else {
            int a=0;
            LinkedNode p=head;
            while (position-1>a+1){
                p=p.next;
                a+=1;
            }
            //LinkedNode removed=p.next;
            p.next=p.next.next;
            //removed.next=null;
            return head;
        }
    }

    private static LinkedNode query(LinkedNode head,int position){
        int a=0;
        LinkedNode p=head;
        while (position-1>a){
            p=p.next;
            a+=1;
        }
        return p;
    }


    private static class LinkedNode{
        int value;
        LinkedNode next;

        void printLinkedList(){
            LinkedNode temp=this;
            while(temp!=null){
                System.out.println(temp);
                temp=temp.next;
            }
        }

        @Override
        public String toString() {
            return "LinkedNode{" +
                    "value=" + value +
                    '}';
        }
    }

    private static class FastReader implements Closeable{
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

        public void hasNext() {
            while (!st.hasMoreTokens()) {
                String s = nextLine();
                if (s == null) return;
                eat(s);
            }
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
            }

        }
        public void printf(String format,Object... os) {
            try{
                writer.write(String.format(format,os));
                writer.flush();
            }catch (IOException e){
                e.printStackTrace();
            }

        }

        public void println(Object object) {
            try{
                writer.write(object.toString());
                writer.write(System.lineSeparator());
                writer.flush();
            }catch (IOException e){
                e.printStackTrace();
            }

        }

        @Override
        public void close(){
            try{
                writer.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }
}
