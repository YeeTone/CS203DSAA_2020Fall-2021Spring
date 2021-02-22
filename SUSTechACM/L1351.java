package SUSTechACM;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class L1351 {
    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);
        int t= fastReader.nextInt();
        for (int i = 0; i < t; i++) {
            int n= fastReader.nextInt();
            char[]instructions=fastReader.next().toCharArray();
            CharNode head=getResultCharNode(n,instructions);
            head.printLinkedList(fastWriter);
        }

        fastReader.close();
        fastWriter.close();
    }
    private static CharNode getResultCharNode(int n,char[]instructions){
        CharNode head=new CharNode();
        CharNode eof=new CharNode();
        head.next=eof;
        eof.prev=head;

        CharNode curNode=head.next;

        boolean replacing=false;
        for(int i=0;i<n;i++){
            switch (instructions[i]){
                case 'r':{
                    replacing=true;
                    break;
                }
                case 'I':{
                    curNode=head.next;
                    break;
                }
                case 'H':{
                    if(curNode!=head.next){
                        curNode=curNode.prev;
                    }
                    break;
                }
                case 'L':{
                    if(curNode!=eof){
                        curNode=curNode.next;
                    }
                    break;
                }

                case 'x':{
                    if(curNode==eof){

                    }else if(curNode.next==eof){
                        curNode.prev.next=eof;
                        eof.prev=curNode.prev;
                        curNode=curNode.next;
                    }else {
                        curNode.prev.next=curNode.next;
                        curNode.next.prev=curNode.prev;
                        curNode=curNode.next;
                    }
                    break;
                }
                default:{
                    char c=instructions[i];
                    CharNode otherNode=new CharNode();
                    otherNode.value=c;

                    if(replacing){
                        replacing=false;
                        if(curNode==eof){
                            curNode.prev.next=otherNode;
                            otherNode.next=curNode;
                            otherNode.prev=curNode.prev;
                            curNode.prev=otherNode;
                            curNode=curNode.prev;
                        }else {
                            curNode.value=c;
                        }
                    }else {
                        curNode.prev.next=otherNode;
                        otherNode.next=curNode;
                        otherNode.prev=curNode.prev;
                        curNode.prev=otherNode;
                    }
                    break;
                }
            }
            //head.printLinkedList();
        }
        return head;
    }

    private static class CharNode{
        char value='\0';
        CharNode prev=null;
        CharNode next=null;

        void printLinkedList(FastWriter fastWriter){
            CharNode temp=this;
            int count=0;
            while (temp!=null){
                if(temp.value=='\0'){
                    count+=1;
                }else {
                    fastWriter.print(temp.value);
                }
                temp=temp.next;
                /*if(count>=2){
                    System.out.print("EOF");
                    break;
                }*/

            }
            fastWriter.println();
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
