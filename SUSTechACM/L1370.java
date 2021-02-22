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

        fastReader.close();
        fastWriter.close();
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
