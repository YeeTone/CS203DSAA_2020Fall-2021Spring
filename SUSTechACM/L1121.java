package SUSTechACM;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class L1121 {
    private static final FastReader fastReader;
    private static final FastWriter fastWriter;
    static {
        fastReader=new FastReader(System.in);
        fastWriter=new FastWriter(System.out);
    }
    public static void main(String[] args) {

        int t= fastReader.nextInt();

        for (int i = 0; i < t; i++) {
            int n= fastReader.nextInt();
            TreeNode[]treeNodes=new TreeNode[n];
            for (int j = 0; j < n; j++) {
                treeNodes[j]=new TreeNode();
            }

            for (int j = 0; j < n; j++) {
                int x= fastReader.nextInt();
                int y= fastReader.nextInt();

                TreeNode left;
                TreeNode right;
                if(x==0){
                    left=null;
                }else {
                    left=treeNodes[x-1];
                    treeNodes[j].leftChild=left;
                    left.father=treeNodes[j];
                }

                if(y==0){
                    right=null;
                }else {
                    right=treeNodes[y-1];
                    treeNodes[j].rightChild=right;
                    right.father=treeNodes[j];
                }
            }

            TreeNode root=treeNodes[0].getRoot();
            if(root.isCompleteBinaryTree()){
                System.out.println("Yes");
            }else {
                System.out.println("No");
            }
        }

        fastReader.close();
        fastWriter.close();
    }


    private static class TreeNode{
        TreeNode father;
        TreeNode leftChild;
        TreeNode rightChild;

        public TreeNode getRoot(){
            TreeNode root=this;
            while (root.father!=null){
                root=root.father;
            }
            return root;
        }

        public boolean isCompleteBinaryTree(){
            Queue<TreeNode>queue=new LinkedList<>();
            boolean shouldBeLeaf=false;
            queue.offer(this);

            while (!queue.isEmpty()){
                TreeNode cur=queue.poll();
                if(shouldBeLeaf&&(cur.leftChild!=null||cur.rightChild!=null)){
                    return false;
                }

                if(cur.leftChild==null&&cur.rightChild!=null){
                    return false;
                }

                if(cur.leftChild!=null){
                    queue.offer(cur.leftChild);
                }

                if(cur.rightChild!=null){
                    queue.offer(cur.rightChild);
                }else {
                    shouldBeLeaf=true;
                }
            }
            return true;
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
