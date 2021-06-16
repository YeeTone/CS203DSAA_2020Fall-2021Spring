package JCoderAC.Bonus;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class L1002 {
    private static final long MOD=10007;

    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);

        int n= fastReader.nextInt();
        GraphNode[]nodes=new GraphNode[n+1];

        for (int i = 1; i <= n; i++) {
            nodes[i]=new GraphNode(i);
        }

        for (int i = 1; i <= n; i++) {
            GraphNode n1=nodes[fastReader.nextInt()];
            GraphNode n2=nodes[fastReader.nextInt()];

            n1.neighbors.add(n2);
            n2.neighbors.add(n1);
        }

        for (int i = 1; i <= n; i++) {
            nodes[i].setWeight(fastReader.nextInt());
        }

        long[]answers=getAnswers(nodes,n);
        fastWriter.println(answers[0]+" "+answers[1]);

        fastReader.close();
        fastWriter.close();

    }

    private static long[] getAnswers(GraphNode[]nodes,int n){
        long answer=0;

        long maxn=0;
        for (int i = 1; i <= n; i++) {
            long tot=0,s=0;
            long max1=0,max2=0;
            for (int j = 0; j < nodes[i].neighbors.size(); j++) {
                long weight=nodes[i].neighbors.get(j).weight;
                s=(s+ (long) weight *weight%MOD)%MOD;
                tot=(tot+weight%MOD)%MOD;
                if(weight>max1){
                    max2=max1;
                    max1=weight;
                }else if(weight>max2){
                    max2=weight;
                }
            }

            maxn=Math.max(maxn,max1*max2);
            answer=(answer+(tot*tot-s)%MOD)%MOD;
        }

        return new long[]{maxn,answer};
    }

    private static class GraphNode{
        int index;
        int weight;
        ArrayList<GraphNode>neighbors=new ArrayList<>();
        public GraphNode(int i){
            this.index=i;
        }

        public void setWeight(int weight) {
            this.weight = weight;
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
