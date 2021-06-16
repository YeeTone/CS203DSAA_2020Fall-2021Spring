package JCoderAC.Lab9;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class L906 {
    private static long currentMaxDanger;

    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);

        int N= fastReader.nextInt();
        int M= fastReader.nextInt();
        int K= fastReader.nextInt();

        GraphNode[]nodes=new GraphNode[N+1];
        GraphEdge[]edges=new GraphEdge[M+1];

        for (int i = 1; i <= N; i++) {
            nodes[i]=new GraphNode(i);
        }

        long maxDanger =-1;

        for (int i = 1; i <= M; i++) {
            int ai= fastReader.nextInt();
            int bi= fastReader.nextInt();
            long li= fastReader.nextLong();

            edges[i]=new GraphEdge(nodes[ai], nodes[bi], li);
            maxDanger =Math.max(li, maxDanger);
        }
        Arrays.sort(edges,1,M+1,Comparator.comparingLong(e->e.danger));

        long low=0;
        long high=maxDanger;
        long answer=-1;

        while (low<=high){
            long mid=(low)+(high-low)/2;
            System.out.println("low = " + low);
            System.out.println("high = " + high);
            System.out.println("mid = " + mid);

            if(checkIsOK(nodes,edges,N,M,K,mid)){
                if(answer==-1){
                    answer=mid;
                }else {
                    answer=Math.min(currentMaxDanger,answer);
                }

                high=mid-1;
            }else {
                low=mid+1;
            }
            rollback(nodes);
        }

        fastWriter.println(answer);

        fastReader.close();
        fastWriter.close();
    }
    private static boolean checkIsOK(GraphNode[]nodes,GraphEdge[]edges,int N,int M,int K,long mid) {
        for (int i = 1; i <= M; i++) {
            GraphNode t1 = edges[i].fromNode.getFather();
            GraphNode t2 = edges[i].toNode.getFather();
            long danger = edges[i].danger;

            if (t1 != t2) {
                if (danger > mid) {
                    if (K > 0) {
                        K--;
                    } else {
                        return false;
                    }
                }
                t2.father = t1.father;
            }

            if (nodes[1].father == nodes[N].father) {
                break;
            }
        }

        if(nodes[1].father == nodes[N].father){
            currentMaxDanger=mid;
            return true;
        }
        return false;
    }

    private static void rollback(GraphNode[]nodes){
        for (GraphNode gn:nodes){
            if(gn!=null){
                gn.father=gn;
            }
        }
        currentMaxDanger=0;
    }

    private static class GraphNode{
        int index;
        List<GraphEdge> edges;
        GraphNode father;
        public GraphNode(int i){
            this.index=i;
            this.edges=new ArrayList<>();
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
        long danger;
        boolean visited;

        public GraphEdge(GraphNode f,GraphNode t,long d){
            this.fromNode=f;
            this.toNode=t;
            this.danger=d;
            this.visited=false;
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
