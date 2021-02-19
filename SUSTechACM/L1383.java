package SUSTechACM;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class L1383 {
    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);
        int t= fastReader.nextInt();

        for (int i = 0; i < t; i++) {
            int n= fastReader.nextInt();
            Tree tree=new Tree(n);
            for (int j = 0; j < n-1; j++) {
                int ai= fastReader.nextInt();
                tree.setNode(j+1,ai-1);
            }
            //System.out.println(tree);
            tree.levelTraverse();
        }
    }
    private static class Tree{
        private final TreeNode[] nodes;

        public Tree(int n){
            this.nodes=new TreeNode[n];
            for (int i = 0; i < n; i++) {
                nodes[i]=new TreeNode(i);
            }
        }

        @Override
        public String toString() {
            return "Tree{" +
                    "nodes=" + Arrays.toString(nodes) +
                    '}';
        }

        public void setNode(int index, int fatherIndex){
            nodes[index].value=index;
            nodes[index].fatherIndex=fatherIndex;
            nodes[fatherIndex].childrenIndexes.add(index);
        }

        public TreeNode getRoot(){
            TreeNode t0=nodes[0];
            while (t0.fatherIndex!=-1){
                t0=nodes[t0.fatherIndex];
            }
            return t0;
        }

        private void levelTraverse(){
            TreeNode root=getRoot();
            Queue<TreeNode> treeNodeQueue=new LinkedList<>();
            treeNodeQueue.offer(root);

            while (!treeNodeQueue.isEmpty()){

                TreeNode node=treeNodeQueue.peek();
                treeNodeQueue.poll();
                System.out.print((node.value+1)+" ");

                Collections.sort(node.childrenIndexes);
                for(int index:node.childrenIndexes){
                    treeNodeQueue.offer(nodes[index]);
                }
            }
            System.out.println();
        }

        private static class TreeNode{
            int value=-1;
            int fatherIndex=-1;
            ArrayList<Integer> childrenIndexes=new ArrayList<>();

            public TreeNode(int index){
                value=index;
            }
            @Override
            public String toString() {
                return "TreeNode{" +
                        "value=" + value +
                        ", fatherIndex=" + fatherIndex +
                        '}';
            }
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
