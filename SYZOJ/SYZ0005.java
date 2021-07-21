package syz;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class SYZ0005 {
    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);

        int n= fastReader.nextInt();
        int m= fastReader.nextInt();

        GraphNode[]nodes=new GraphNode[n+1];

        for (int i = 0; i < n + 1; i++) {
            nodes[i]=new GraphNode();
            nodes[i].index=i;
        }

        for (int i = 0; i < m; i++) {
            int f= fastReader.nextInt();
            int t= fastReader.nextInt();
            int d= fastReader.nextInt();

            GraphEdge e=new GraphEdge();
            e.from=nodes[f];
            e.to=nodes[t];
            e.distance=d;

            e.from.outEdges.add(e);
        }

        nodes[1].dijkstra();
        fastWriter.println(nodes[n].currentDistance1);

        fastReader.close();
        fastWriter.close();
    }

    private static class GraphNode{
        int index=-1;
        int currentDistance1=Integer.MAX_VALUE/2;
        List<GraphEdge> outEdges=new ArrayList<>();

        public void dijkstra(){
            Queue<GraphNode>nodeQueue=new LinkedList<>();
            this.currentDistance1=0;
            nodeQueue.add(this);

            while (!nodeQueue.isEmpty()){
                GraphNode currentFrom =nodeQueue.poll();

                for (GraphEdge e: currentFrom.outEdges){
                    GraphNode currentTo=e.to;
                    if(currentTo.currentDistance1> currentFrom.currentDistance1+e.distance){
                        currentTo.currentDistance1= currentFrom.currentDistance1+e.distance;
                        nodeQueue.offer(e.to);
                    }
                }
            }
        }

    }

    private static class GraphEdge{
        GraphNode from;
        GraphNode to;
        int distance;
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
