package JCoderAC.Lab8;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.StringTokenizer;

public class L802 {
    private static final StreamTokenizer in
            =new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    private static int nextInt(){
        try{
            in.nextToken();
            return (int)in.nval;
        }catch (IOException e){
            return -1;
        }
    }
    private static long nextLong(){
        try{
            in.nextToken();
            return (long)in.nval;
        }catch (IOException e){
            return -1;
        }
    }

    public static void main(String[] args) {
        FastWriter fastWriter=new FastWriter(System.out);

        int t=nextInt();
        while (t-->0){
            int n=nextInt();

            TreeNode[]nodes=new TreeNode[n];
            for (int i = 0; i < n; i++) {
                long value=nextLong();
                nodes[i]=new TreeNode(i+1,value);
            }
            formHeap(nodes,n);
            int m=nextInt();
            int answer=-1;
            for (int i = 0; i < n; i++) {
                if(nodes[i].index==m){
                    answer=i;
                    break;
                }
            }
            int[]height_index=get_height_index(answer);
            fastWriter.println(height_index[0]+" "+height_index[1]);

        }

        fastWriter.close();
    }
    private static int[]get_height_index(int x){
        int height=(int)Math.ceil(Math.log(x+2)/Math.log(2));
        int index=x+2-(int)Math.pow(2,height-1);
        return new int[]{height,index};
    }

    private static void formHeap(TreeNode[]nodes,int n){
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0; j=(j-1)/2) {
                TreeNode father=nodes[(j-1)/2];
                TreeNode child=nodes[j];
                if(father.value< child.value){
                    nodes[j]=father;
                    nodes[(j-1)/2]=child;
                }else {
                    break;
                }
            }
        }
    }

    private static class TreeNode{
        int index;
        long value;
        public TreeNode(int index,long value){
            this.index=index;
            this.value=value;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "index=" + index +
                    ", value=" + value +
                    '}';
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
                throw new RuntimeException();
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
