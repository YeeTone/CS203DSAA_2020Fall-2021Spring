package JCoderAC.Lab7;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class L703 {
    private static final StreamTokenizer in =
            new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static final FastWriter fastWriter=new FastWriter(System.out);
    private static int nextInt(){
        try{
            in.nextToken();
            return (int)in.nval;
        }catch (IOException e){
            return -1;
        }
    }

    public static void main(String[] args) {
        int t= nextInt();

        while (t-->0){
            int n= nextInt();
            TreeNode[]nodes=new TreeNode[n+1];
            for (int i = 1; i <= n; i++) {
                nodes[i]=new TreeNode(i);
            }

            for (int i = 1; i < n; i++) {
                int ai= nextInt();
                TreeNode father=nodes[ai];
                TreeNode child=nodes[i+1];

                father.children.add(child);
                child.father=father;
            }

            TreeNode root=nodes[1];
            root.floorTraversal();

        }

        fastWriter.close();
    }

    private static class TreeNode{
        int index;
        TreeNode father;
        ArrayList<TreeNode>children;

        public TreeNode(int index){
            this.index=index;
            this.father=null;
            this.children=new ArrayList<>();
        }

        public void floorTraversal(){
            Queue<TreeNode>queue=new LinkedList<>();
            queue.offer(this);

            while (!queue.isEmpty()){
                fastWriter.print(queue.peek().index+" ");
                queue.peek().children.sort(Comparator.comparingInt(o -> o.index));
                for (TreeNode child:queue.peek().children){
                    if(child!=null){
                        queue.offer(child);
                    }
                }
                queue.poll();
            }

            fastWriter.println();
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
