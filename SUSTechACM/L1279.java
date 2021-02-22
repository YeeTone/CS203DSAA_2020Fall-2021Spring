package SUSTechACM;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class L1279 {

    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);
        int t= fastReader.nextInt();

        for (int i = 0; i < t; i++) {
            int n= fastReader.nextInt();
            int[]preorder=new int[n];
            int[]inorder=new int[n];

            for (int j = 0; j < n; j++) {
                preorder[j]= fastReader.nextInt();
            }

            for (int j = 0; j < n; j++) {
                inorder[j]= fastReader.nextInt();
            }

            TreeNode tree=getTree(preorder,inorder,0,n-1,0,n-1);
            postTraversal(tree,fastWriter);
            fastWriter.println();
        }

        fastReader.close();
        fastWriter.close();

    }
    private static void postTraversal(TreeNode node,FastWriter fastWriter){
        if(node!=null){
            postTraversal(node.left,fastWriter);
            postTraversal(node.right,fastWriter);
            fastWriter.print(node.value+" ");
        }
    }

    private static TreeNode getTree(int[]preorder,int[]inorder,int preleft,int preright,int inleft,int inright){
        if(preleft>preright||inleft>inright){
            return null;
        }

        TreeNode root=new TreeNode(preorder[preleft]);

        int index=getIndex(inorder,root.value);

        root.left=getTree(preorder,inorder,preleft+1,index-inleft+preleft,inleft,index-1);

        root.right=getTree(preorder,inorder,index-inleft+preleft+1,preright,index+1,inright);

        return root;
    }

    private static int getIndex(int[]array,int value){
        for (int i = 0,len=array.length; i < len; i++) {
            if(array[i]==value){
                return i;
            }
        }
        return -1;
    }


    private static class TreeNode{
        int value;
        TreeNode left;
        TreeNode right;
        public TreeNode(int v){
            this.value=v;
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
