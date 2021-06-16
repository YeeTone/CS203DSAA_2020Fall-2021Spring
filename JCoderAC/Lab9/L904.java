package JCoderAC.Lab9;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class L904 {
    public static void main(String[] args) {
        FastReader fastReader = new FastReader(System.in);
        FastWriter fastWriter = new FastWriter(System.out);

        int N = fastReader.nextInt();
        int M = fastReader.nextInt();

        GraphNode[] nodes = new GraphNode[N + 1];
        GraphEdge[] edges = new GraphEdge[M + 1];

        for (int i = 1; i <= N; i++) {
            nodes[i] = new GraphNode(i);
        }

        for (int i = 1; i <= M; i++) {
            int si= fastReader.nextInt();
            int ti= fastReader.nextInt();
            nodes[si].outNeighbors.add(nodes[ti]);
            nodes[ti].inNeighbors.add(nodes[si]);
            edges[i]=new GraphEdge(nodes[si], nodes[ti]);
        }

        for (int i=1;i<=N;i++){
            nodes[i].dp();
        }

        for (int i = 1; i <= N; i++) {
            fastWriter.println(nodes[i].distance);
        }

        fastReader.close();
        fastWriter.close();
    }

    private static class GraphNode{
        List<GraphNode>inNeighbors=new ArrayList<>();
        List<GraphNode>outNeighbors=new ArrayList<>();
        int index;
        long distance;
        boolean visited;

        public GraphNode(int i){
            this.index=i;
            this.distance=1;
            this.visited=false;
        }

        public long dp(){
            if(this.visited){
                return this.distance;
            }
            this.visited=true;

            for (GraphNode inNeighbor : inNeighbors) {
                this.distance = Math.max(this.distance, inNeighbor.dp() + 1);
            }

            return this.distance;
        }

    }
    private static class GraphEdge{
        GraphNode from;
        GraphNode to;
        int weight=1;

        public GraphEdge(GraphNode f,GraphNode t){
            this.from=f;
            this.to=t;
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
