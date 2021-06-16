package JCoderAC.Lab9;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class L902 {
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
        int N= nextInt();
        int M= nextInt();
        int T= nextInt();
        GraphNode[]nodes=new GraphNode[N+1];
        GraphEdge[]edges=new GraphEdge[M+1];

        for (int i = 1; i <= N; i++) {
            nodes[i]=new GraphNode(i);
        }

        for (int i = 1; i <= M; i++) {
            int si= nextInt();
            int ti= nextInt();
            long wi= nextLong();
            edges[i]=new GraphEdge(nodes[si], nodes[ti], wi);
            nodes[si].outEdges.add(edges[i]);
            nodes[ti].inEdges.add(edges[i]);
        }

        nodes[T].dijkstra();
        for (int i = 1; i <= N; i++) {
            fastWriter.print(nodes[i].distanceFromT+" ");
        }

        fastWriter.close();
    }
    private static class GraphNode {
        int index;
        boolean visited;
        long distanceFromT =2147483647;
        ArrayList<GraphEdge> outEdges = new ArrayList<>();
        ArrayList<GraphEdge> inEdges = new ArrayList<>();

        public GraphNode(int index) {
            this.index = index;
            this.visited = false;
        }

        public void dijkstra() {
            this.distanceFromT = 0;

            Queue<GraphNode>nodeQueue=new LinkedList<>();
            nodeQueue.offer(this);
            GraphNode current;

            while (!nodeQueue.isEmpty()) {
                current=nodeQueue.poll();

                for (int i = 0; i < current.outEdges.size(); i++) {
                    GraphEdge edge=current.outEdges.get(i);
                    GraphNode toNode= edge.toNode;
                    if(toNode.distanceFromT> edge.wi+current.distanceFromT){
                        toNode.distanceFromT=edge.wi+current.distanceFromT;
                        nodeQueue.offer(toNode);
                    }
                }
            }
        }
    }

    private static class GraphEdge{
        GraphNode fromNode;
        GraphNode toNode;
        long wi;

        public GraphEdge(GraphNode from,GraphNode to,long wi){
            this.fromNode=from;
            this.toNode=to;
            this.wi=wi;
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
