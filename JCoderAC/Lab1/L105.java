package JCoderAC.Lab1;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class L105{
    public static void main(String[] args) {
        FastReader fastReader =new FastReader(System.in);
        FastWriter fastWriter =new FastWriter(System.out);

        int n = fastReader.nextInt();
        int m = fastReader.nextInt();
        long[][] values = new long[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                values[i][j] = fastReader.nextLong();
            }
        }
        fastWriter.println(minimumEffortPath(n,m,values));

        fastReader.close();
        fastWriter.close();
    }

    private static long minimumEffortPath(int n,int m,long[][]map) {
        UnionGraph unionGraph = new UnionGraph(n * m);
        List<PathEdge> pathEdges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i + 1 < n) {
                    pathEdges.add(new PathEdge(i * m + j, (i + 1) * m + j, Math.max(map[i][j], map[i + 1][j])));
                }
                if (j + 1 < m) {
                    pathEdges.add(new PathEdge(i * m + j, i * m + j + 1, Math.max(map[i][j], map[i][j + 1])));
                }
            }
        }
        long ans = 0;
        pathEdges.sort(Comparator.comparingLong(a -> a.length));
        for (PathEdge e : pathEdges) {
            int start = e.start;
            int end = e.end;
            unionGraph.uniteTogether(start, end);
            ans = Math.max(ans, e.length);
            if (unionGraph.is2PointsUnited(0, n * m - 1)) {
                break;
            }
        }
        return ans;
    }

    private static class PathEdge{
        private final int start;
        private final int end;
        private final long length;

        public PathEdge(int start,int end,long length){
            this.start=start;
            this.end=end;
            this.length=length;
        }
    }

    private static class UnionGraph{
        private final int[]fathers;
        private final int[]sonSizes;
        public UnionGraph(int counts){
            this.fathers=new int[counts];
            for (int i = 0; i < counts; i++) {
                fathers[i]=i;
            }
            this.sonSizes=new int[counts];
            Arrays.fill(sonSizes,1);
        }

        public int findFatherLocation(int son){
            if(fathers[son]==son){
                return fathers[son];
            }else {
                fathers[son]=findFatherLocation(fathers[son]);
                return fathers[son];
            }
        }

        private int getSonSize(int father){
            return sonSizes[father];
        }

        private void mergeSonSize(int father1,int father2){
            int size1=getSonSize(father1);
            int size2=getSonSize(father2);

            if(size1>=size2){
                sonSizes[father1]+=size2;
                fathers[father2]=father1;
            }else{
                sonSizes[father2]+=size1;
                fathers[father1]=father2;
            }
        }

        public boolean is2PointsUnited(int s1,int s2){
            int father1=findFatherLocation(s1);
            int father2=findFatherLocation(s2);
            return father1==father2;
        }

        private void uniteTogether(int s1,int s2){
            int father1=findFatherLocation(s1);
            int father2=findFatherLocation(s2);

            if(father1!=father2){
                mergeSonSize(father1,father2);
            }

        }
    }

    private static class FastReader implements Closeable{
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
            writer.flush();
            writer.close();
        }
    }
}
