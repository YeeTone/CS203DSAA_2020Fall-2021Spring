package JCoderAC.Lab8;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class L801 {
    private static final StreamTokenizer in=
            new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    private static int nextInt(){
        try{
            in.nextToken();
            return (int)in.nval;
        }catch (IOException e){
            return -1;
        }
    }

    public static void main(String[] args) {
        FastWriter fastWriter=new FastWriter(System.out);
        int t= nextInt();
        for (int i = 0; i < t; i++) {
            TreeNode.isBinaryTree=true;
            int n= nextInt();

            TreeNode[]treeNodes=new TreeNode[n];
            for (int j = 0; j < n; j++) {
                int value= nextInt();
                treeNodes[j]=new TreeNode(value);
            }

            boolean isMaxHeap=false;
            boolean isMinHeap=false;
            boolean isHeap=true;
            for (int j = 0; j < n-1; j++) {
                int x= nextInt();
                int y= nextInt();
                treeNodes[x-1].setChild(treeNodes[y-1]);

                if(isMaxHeap&&treeNodes[x-1].value<treeNodes[y-1].value){
                    isHeap=false;
                }
                if(isMinHeap&&treeNodes[x-1].value>treeNodes[y-1].value){
                    isHeap=false;
                }

                if(treeNodes[x-1].value>treeNodes[y-1].value){
                    isMaxHeap=true;
                }else if(treeNodes[x-1].value<treeNodes[y-1].value){
                    isMinHeap=true;
                }

            }

            TreeNode root= TreeNode.getRoot(treeNodes);
            boolean isComplete=isCompleteBinaryTree(root);

            /*System.out.println("isComplete = " + isComplete);
            System.out.println("isHeap = " + isHeap);
            System.out.println("TreeNode.isBinaryTree = " + TreeNode.isBinaryTree);*/
            if(isComplete&&isHeap&& TreeNode.isBinaryTree){
                fastWriter.println("Case #"+(i+1)+": YES");
            }else {
                fastWriter.println("Case #"+(i+1)+": NO");
            }
        }
        fastWriter.close();
    }
    private static boolean isCompleteBinaryTree(TreeNode root){
        if(root==null){
            return true;
        }
        Queue<TreeNode>queue=new LinkedList<>();
        queue.offer(root);
        boolean shouldBeLeaf=false;
        while (!queue.isEmpty()){
            TreeNode cur=queue.poll();
            TreeNode left=cur.leftChild;
            TreeNode right=cur.rightChild;
            if(shouldBeLeaf&&(left!=null||right!=null)){
                return false;
            }

            if(left==null&&right!=null){
                return false;
            }

            if(left!=null){
                queue.offer(left);
            }

            if(right!=null){
                queue.offer(right);
            }else {
                shouldBeLeaf=true;
            }
        }

        return true;
    }

    private static class TreeNode{
        static boolean isBinaryTree=true;
        int value;
        TreeNode father;

        TreeNode leftChild;
        TreeNode rightChild;
        public TreeNode(int v){
            this.value=v;
        }

        public void setChild(TreeNode child){
            if(leftChild==null){
                leftChild=child;
            }else if(rightChild==null){
                rightChild=child;
            }else {
                isBinaryTree=false;
            }

            child.father=this;
        }

        public static TreeNode getRoot(TreeNode[]treeNodes){
            TreeNode node=treeNodes[0];
            while (node.father!=null){
                node=node.father;
            }
            return node;
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
