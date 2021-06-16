package JCoderAC.Bonus;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class L1003 {
    private static final FastReader fastReader = new FastReader(System.in);
    private static final FastWriter fastWriter = new FastWriter(System.out);

    private static final int VERY_BIG=100000007;
    private static final int MAX_N=10007;
    private static final int MAX_M=300007;
    private static int n;
    private static int m;
    private static final int[] degree=new int[MAX_N];
    private static final int[] head=new int[MAX_N];
    private static int cnte;
    private static final int[]h=new int[MAX_N];
    private static int ce;
    private static final int[]dis=new int[MAX_N];
    private static final int[]tag=new int[MAX_N];
    private static int start;
    private static int end;
    private static final boolean[]visited=new boolean[MAX_N];
    private static final Edge[] ex =new Edge[MAX_M];
    private static final Edge[]edge=new Edge[MAX_M];
    private static final Queue<Integer>team=new LinkedList<>();
    private static final PriorityQueue<Integer>t=new PriorityQueue<>(Comparator.comparingInt(o -> dis[o]));

    private static int answer;

    public static void main(String[] args) {
        n = fastReader.nextInt();
        m = fastReader.nextInt();
        for (int i = 1; i <= m; i++) {
            int x = fastReader.nextInt();
            int y = fastReader.nextInt();
            if (x == y) {
                continue;
            }

            add(x, y);
            degree[x]++;
        }

        start = fastReader.nextInt();
        end = fastReader.nextInt();

        bfs();
        dijkstra();

        fastWriter.println(answer);

        fastReader.close();
        fastWriter.close();
    }
    private static void exitQuickly(){
        fastWriter.println(-1);

        fastReader.close();
        fastWriter.close();
        System.exit(0);
    }

    private static void add(int x,int y){
        ex[++cnte]=new Edge(y,head[x]);
        head[x]=cnte;
        edge[++ce]=new Edge(x,h[y]);
        h[y]=ce;
    }
    private static void bfs(){
        team.offer(end);
        visited[end]=true;

        while (!team.isEmpty()){
            int x=team.poll();

            for (int i=h[x];i!=0;i=edge[i].nxt){
                int y=edge[i].to;
                tag[y]++;
                if(!visited[y]){
                    visited[y]=true;
                    team.offer(y);
                }
            }
        }
    }

    private static void dijkstra(){
        Arrays.fill(visited,false);
        Arrays.fill(dis,VERY_BIG);
        dis[start]=0;
        t.offer(start);

        while (!t.isEmpty()){
            int x=t.poll();
            if(visited[x]){
                continue;
            }

            visited[x]=true;

            for (int i=head[x];i!=0;i= ex[i].nxt){
                int y= ex[i].to;
                if(dis[y]>dis[x]+1 && tag[y]==degree[y]){
                    dis[y]=dis[x]+1;
                    t.offer(y);
                }
            }
        }

        answer=(dis[end]>=VERY_BIG?-1:dis[end]);
    }

    private static class Edge{
        int to;
        int nxt;
        public Edge(){
            this.to=0;
            this.nxt=0;
        }
        public Edge(int t, int nxt){
            this.to= t;
            this.nxt= nxt;
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
