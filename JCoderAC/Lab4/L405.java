package JCoderAC.Lab4;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class L405 {
    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);
        int t= fastReader.nextInt();

        for (int i = 0; i < t; i++) {
            char[]str=fastReader.next().toCharArray();
            int n= fastReader.nextInt();
            ArrayListNode[]nodes=initiate(str);
            for (int j = 0; j < n; j++) {
                int op= fastReader.nextInt();
                if(op==1){
                    char in=fastReader.next().charAt(0);
                    int pos= fastReader.nextInt();
                    insert(nodes,in,pos);
                }else {
                    int pos= fastReader.nextInt();
                    fastWriter.println(search(nodes,pos));
                }
            }
        }

        fastReader.close();
        fastWriter.close();
    }
    private static ArrayListNode[] initiate(char[]str){
        ArrayListNode[]nodes=new ArrayListNode[1000];
        int eachSize=300;
        for (int i = 0; i < 1000; i++) {
            nodes[i]=new ArrayListNode(eachSize);
            try{
                nodes[i-1].next=nodes[i];
            }catch (ArrayIndexOutOfBoundsException ignored){

            }
        }
        for (int i = 0; i < str.length; i++) {
            int nodeIndex=i/200;
            int insertIndex=i%200;
            nodes[nodeIndex].characters.add(insertIndex,str[i]);
        }

        return nodes;
    }
    private static void insert(ArrayListNode[]nodes,char c,int pos){
        ArrayListNode temp=nodes[0];
        while (pos>temp.characters.size()){
            pos-=temp.characters.size();
            temp=temp.next;
        }

        temp.characters.add(pos-1,c);
    }
    private static char search(ArrayListNode[]nodes,int pos){
        ArrayListNode temp=nodes[0];
        while (pos>temp.characters.size()){
            pos-=temp.characters.size();
            temp=temp.next;
        }

        return temp.characters.get(pos-1);
    }

    private static class ArrayListNode{
        ArrayList<Character>characters;
        ArrayListNode next;
        public ArrayListNode(int initialSize){
            this.characters=new ArrayList<>(initialSize);
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
