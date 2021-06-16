package JCoderAC.Lab9;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class L903 {
    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);

        int N= fastReader.nextInt();
        int M= fastReader.nextInt();

        GraphNode[]nodes=new GraphNode[N+1];
        GraphEdge[]edges=new GraphEdge[M+1];

        for (int i = 1; i <= N; i++) {
            nodes[i]=new GraphNode(i);
        }

        for (int i = 1; i <= M; i++) {
            int si= fastReader.nextInt();
            int ti= fastReader.nextInt();
            long wi= fastReader.nextLong();
            edges[i]=new GraphEdge(nodes[si], nodes[ti], wi);
        }

        Arrays.sort(edges,1,M+1, Comparator.comparingLong(e->e.wi));
        int k=1;
        long totalLength=0;
        for (int i = 1; i <= M; i++) {
            GraphNode t1=edges[i].fromNode.getFather();
            GraphNode t2=edges[i].toNode.getFather();
            if(t1!=t2){
                totalLength+=edges[i].wi;
                t2.father=t1.father;
                k++;
                if(k==N){
                    break;
                }
            }
        }

        fastWriter.println(totalLength);

        fastReader.close();
        fastWriter.close();
    }
    private static class GraphNode{
        int index;
        GraphNode father;
        public GraphNode(int i){
            this.index=i;
            this.father=this;
        }

        public GraphNode getFather(){
            if(this.father!=this){
                return this.father=this.father.getFather();
            }else {
                return this;
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
