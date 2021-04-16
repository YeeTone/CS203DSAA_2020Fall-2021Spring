package JCoderAC.Lab7;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class L706 {
    //private static final FastReader fastReader=new FastReader(System.in);
    private static final FastWriter fastWriter=new FastWriter(System.out);
    private static final StreamTokenizer in
            =new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static long answer=0;
    private static TreeNode sub =null;

    public static void main(String[] args) {
        int t= nextInt();
        while (t-->0){
            int n= nextInt();
            int k= nextInt();
            TreeNode[]nodes=new TreeNode[n+1];
            for (int i=1;i<=n;i++){
                nodes[i]=new TreeNode(i);
            }

            for (int i = 0; i < n - 1; i++) {
                int u= nextInt();
                int v= nextInt();
                nodes[u].addNeighbor(nodes[v]);
                nodes[v].addNeighbor(nodes[u]);
            }

            TreeNode last=null;
            for (int i = 0; i < k; i++) {
                int p= nextInt();
                nodes[p].peopleHere();
                last=nodes[p];
            }

            last.dfs(null,1);
            sub.dfs(null,1);
            fastWriter.println(answer/2);
            rollback();
        }

        fastWriter.close();
    }

    private static void rollback(){
        answer=0;
        sub=null;
    }

    private static class TreeNode{
        int index;
        ArrayList<TreeNode>neighbors;
        boolean hasPeople;

        public TreeNode(int i){
            this.index=i;
            this.neighbors=new ArrayList<>();
            this.hasPeople=false;
        }

        public void peopleHere(){
            this.hasPeople=true;
        }

        public void addNeighbor(TreeNode neighbor){
            this.neighbors.add(neighbor);
        }

        public void dfs(TreeNode pre,int num){
            if(hasPeople&&answer<num){
                answer=num;
                sub =this;
            }

            for (TreeNode t : this.neighbors) {
                if (t != pre) {
                    t.dfs(this, num + 1);
                }
            }
        }
    }

    private static int nextInt(){
        try{
            in.nextToken();
            return (int)in.nval;
        }catch (IOException e){
            return -1;
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
