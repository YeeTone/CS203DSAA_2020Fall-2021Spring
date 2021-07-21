package syz;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class SYZ0018 {
    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);

        int n= fastReader.nextInt();
        int m= fastReader.nextInt();

        GraphNode[]nodes=new GraphNode[n+1];
        for (int i = 0; i < n + 1; i++) {
            nodes[i]=new GraphNode(i);
        }

        for (int i = 0; i < m; i++) {
            GraphNode n1=nodes[fastReader.nextInt()];
            GraphNode n2=nodes[fastReader.nextInt()];

            n1.neighbors.add(n2);
            n2.neighbors.add(n1);
        }

        deleteAll3(nodes,n);

        long answer=0;
        for (int i=1;i<=n;i++){
            if(!nodes[i].deleted){
                answer^=nodes[i].index;
            }
        }

        fastWriter.println(answer);

        fastReader.close();
        fastWriter.close();
    }
    private static void deleteAll3(GraphNode[]nodes,int n){
        Queue<GraphNode>nodeQueue=new LinkedList<>();

        for (int i = 1; i < n + 1; i++) {
            if(nodes[i].neighbors.size()<3){
                nodes[i].deleted=true;
                nodeQueue.offer(nodes[i]);
            }
        }

        while (!nodeQueue.isEmpty()){
            GraphNode poll = nodeQueue.poll();
            for (GraphNode g:poll.neighbors){
                g.neighbors.remove(poll);
                if(!g.deleted&&g.neighbors.size()<3){
                    g.deleted=true;
                    nodeQueue.offer(g);
                }
            }
        }
    }

    private static class GraphNode{
        boolean deleted;
        int index;
        List<GraphNode> neighbors;

        public GraphNode(int index){
            this.deleted=false;
            this.index=index;
            this.neighbors=new ArrayList<>();
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
            writer.close();
        }
    }
}
