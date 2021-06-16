package JCoderAC.Lab9;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class L905 {
    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);

        int N= fastReader.nextInt();
        int M= fastReader.nextInt();

        GraphNode[]nodes=new GraphNode[N+1];
        for (int i = 1; i <= N; i++) {
            int wi= fastReader.nextInt();
            nodes[i]=new GraphNode(i,wi);
        }

        for (int i = 0; i < M; i++) {
            int si= fastReader.nextInt();
            int ti= fastReader.nextInt();
            int pi= fastReader.nextInt();

            GraphNode fromNode=nodes[si];
            GraphNode toNode=nodes[ti];

            if(pi==1){
                fromNode.addOutNeighbor(toNode);
            }else {
                fromNode.addOutNeighbor(toNode);
                toNode.addOutNeighbor(fromNode);
            }
        }

        fastReader.close();
        fastWriter.close();
    }
    private static class GraphNode{
        ArrayList<GraphNode>outNeighbors=new ArrayList<>();
        ArrayList<GraphNode>inNeighbors=new ArrayList<>();
        int index;
        int weight;
        public GraphNode(int i,int w){
            this.index=i;
            this.weight=w;
        }

        public void addOutNeighbor(GraphNode to){
            this.outNeighbors.add(to);
            to.inNeighbors.add(this);
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
