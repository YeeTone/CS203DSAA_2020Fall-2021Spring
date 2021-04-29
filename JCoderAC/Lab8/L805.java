package JCoderAC.Lab8;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class L805 {
    private static final StreamTokenizer in
            =new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static final FastWriter fastWriter=new FastWriter(System.out);

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
        int n=nextInt();
        int m=nextInt();
        AVLTree avlTree=new AVLTree();
        long[]values=new long[n];
        for (int i = 0; i < n; i++) {
            long value=nextLong();
            values[i]=value;
        }

        for (int i = 0; i < m; i++) {
            avlTree.addValue(values[i]);
        }

        int[] kis=new int[n-m+1];
        for (int i = 0; i < n - m + 1; i++) {
            try{
                kis[i]=nextInt();
                fastWriter.println(avlTree.getKth(kis[i]));
                avlTree.removeValue(values[i]);
                avlTree.addValue(values[m+i]);
            }catch (ArrayIndexOutOfBoundsException e){
                break;
            }

        }

        fastWriter.close();
    }
    private static class AVLTree{
        AVLTreeNode root;
        int size;
        AVLTreeNode predecessor;
        AVLTreeNode successor;

        public AVLTree(){
            this.root=null;
            this.size=0;
        }

        public long getKth(int k){
            return getKth(this.root,k);
        }

        public int getSubSize(AVLTreeNode node){
            return node==null?0:node.subTreeSize;
        }

        public int getHeight(AVLTreeNode node){
            return node==null?0:node.height;
        }

        public void setPredecessor(AVLTreeNode node,long value){
            try{
                if(node.value<value){
                    predecessor=node;
                    setPredecessor(node.rightChild,value);
                }else {
                    setPredecessor(node.leftChild,value);
                }
            }catch (NullPointerException ignored){

            }
        }

        public void setSuccessor(AVLTreeNode node,long value){
            try{
                if(node.value>value){
                    successor=node;
                    setSuccessor(node.leftChild,value);
                }else {
                    setSuccessor(node.rightChild,value);
                }
            }catch (NullPointerException ignored){

            }

        }

        public int getBalanceFactor(AVLTreeNode node){
            try{
                return (getHeight(node.leftChild)-getHeight(node.rightChild));
            }catch (NullPointerException e){
                return 0;
            }
        }

        public boolean isBalanced(AVLTreeNode node){
            try{
                int balance=Math.abs(getBalanceFactor(node));
                if(balance>1){
                    return false;
                }else {
                    return isBalanced(node.leftChild)&&isBalanced((node.rightChild));
                }
            }catch (NullPointerException e){
                return true;
            }
        }

        public void addValue(long value){
            this.root = addNode(this.root, value);
        }

        public void removeValue(long value){
            this.root = removeNode(this.root, value);
        }

        private AVLTreeNode rightRotate(AVLTreeNode node){
            AVLTreeNode left=node.leftChild;
            AVLTreeNode left_right=left.rightChild;
            left.rightChild=node;
            node.leftChild=left_right;

            node.height=Math.max(getHeight(node.leftChild),getHeight(node.rightChild))+1;
            left.height=Math.max(getHeight(left.leftChild),getHeight(left.rightChild))+1;

            node.setSubSize();
            left.setSubSize();

            return left;
        }

        private AVLTreeNode leftRotate(AVLTreeNode node){
            AVLTreeNode right=node.rightChild;
            AVLTreeNode right_left=right.leftChild;

            right.leftChild=node;
            node.rightChild=right_left;

            node.height=Math.max(getHeight(node.leftChild),getHeight(node.rightChild))+1;
            right.height=Math.max(getHeight(right.leftChild),getHeight(right.rightChild))+1;

            node.setSubSize();
            right.setSubSize();
            return right;
        }

        public AVLTreeNode getMin(AVLTreeNode node){
            if(node==null){
                return null;
            }

            return node.leftChild==null?node:getMin(node.leftChild);
        }

        private AVLTreeNode addNode(AVLTreeNode node,long value){
            if(node==null){
                size++;
                return new AVLTreeNode(value);
            }

            if(node.value==value){
                node.capacity++;
                node.setSubSize();
                return node;
            }

            if(node.value>value){
                node.leftChild=addNode(node.leftChild,value);
            }else {
                node.rightChild=addNode(node.rightChild,value);
            }

            node.height=Math.max(getHeight(node.leftChild),getHeight(node.rightChild))+1;
            node.setSubSize();

            int balance=getBalanceFactor(node);
            if(balance>1&&getBalanceFactor(node.leftChild)>0){
                return rightRotate(node);
            }else if(balance<-1&&getBalanceFactor(node.rightChild)<0){
                return leftRotate(node);
            }else if(balance>1&&getBalanceFactor(node.leftChild)<0){
                node.leftChild=leftRotate(node.leftChild);
                return rightRotate(node);
            }else if(balance<-1&&getBalanceFactor(node.rightChild)>0){
                node.rightChild=rightRotate(node.rightChild);
                return leftRotate(node);
            }else {
                return node;
            }
        }

        private AVLTreeNode removeNode(AVLTreeNode node,long value){
            if(node==null){
                return null;
            }

            AVLTreeNode new_node=null;
            if(node.value>value){
                node.leftChild=removeNode(node.leftChild,value);
                new_node=node;
            }else if(node.value<value){
                node.rightChild=removeNode(node.rightChild,value);
                new_node=node;
            }else {
                if(node.capacity >1){
                    node.capacity--;
                    node.setSubSize();
                    return node;
                }

                if(node.leftChild==null){
                    AVLTreeNode right=node.rightChild;
                    node.rightChild=null;
                    size--;
                    new_node=right;
                }else if(node.rightChild==null){
                    AVLTreeNode left=node.leftChild;
                    node.leftChild=null;
                    size--;
                    new_node=left;
                }else {
                    AVLTreeNode minNode=getMin(node.rightChild);
                    minNode.rightChild=removeNode(node.rightChild,minNode.value);
                    minNode.leftChild=node.leftChild;
                    node.leftChild=node.rightChild=null;

                    new_node=minNode;
                }
            }

            if(new_node==null){
                return null;
            }else {
                new_node.height=Math.max(getHeight(new_node.leftChild),getHeight(new_node.rightChild))+1;
                new_node.setSubSize();

                int balance=getBalanceFactor(new_node);
                if(balance>1&&getBalanceFactor(new_node.leftChild)>=0){
                    return rightRotate(new_node);
                }else if(balance<-1&&getBalanceFactor(new_node.rightChild)<=0){
                    return leftRotate(new_node);
                }else if(balance>1&&getBalanceFactor(new_node.leftChild)<0){
                    new_node.leftChild=leftRotate(new_node.leftChild);
                    return rightRotate(new_node);
                }else if(balance<-1&&getBalanceFactor(new_node.rightChild)>0){
                    new_node.rightChild=rightRotate(new_node.rightChild);
                    return leftRotate(new_node);
                }else {
                    return new_node;
                }
            }
        }

        private long getKth(AVLTreeNode node,int k){
            try{
                if(node==null) {
                    throw new NullPointerException();
                }

                if(k<=0||k>node.subTreeSize){
                    throw new IllegalArgumentException();
                }

                if(k<getSubSize(node.leftChild)+1){
                    return getKth(node.leftChild,k);
                }else if(k>getSubSize(node.leftChild)+node.capacity){
                    return getKth(node.rightChild,k-node.capacity -getSubSize(node.leftChild));
                }else {
                    return node.value;
                }
            }catch (NullPointerException|IllegalArgumentException e){
                return 0;
            }

        }
    }

    private static class AVLTreeNode {
        AVLTreeNode leftChild;
        AVLTreeNode rightChild;
        int height;
        int subTreeSize;
        int capacity;
        long value;
        public AVLTreeNode(long value) {
            this.value = value;
            this.height=1;
            this.leftChild=null;
            this.rightChild=null;
            this.capacity =1;
            this.subTreeSize =1;
        }

        public int getHeight() {
            return height;
        }
        static int getSubTreeSize(AVLTreeNode node){
            try{
                return node.subTreeSize;
            }catch (NullPointerException e){
                return 0;
            }
        }

        public void setSubSize() {
            this.subTreeSize = getSubTreeSize(this.leftChild)+ getSubTreeSize(this.rightChild)+this.capacity;
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
