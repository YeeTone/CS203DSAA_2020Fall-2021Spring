package CS203;

import java.io.*;
import java.util.*;

public class L105{
    public static void main(String[] args) {
        FastScanner reader=new FastScanner(System.in);
        int n = reader.nextInt();
        int m = reader.nextInt();
        long[][] values = new long[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                values[i][j] = reader.nextLong();
            }
        }
        System.out.println(minimumEffortPath(n,m,values));
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

    private static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner(InputStream in) {
            br = new BufferedReader(new InputStreamReader(in), 16384);
            eat("");
        }

        public void eat(String s) {
            st = new StringTokenizer(s);
        }

        public String nextLine() {
            try {
                return br.readLine();
            } catch (IOException e) {
                return null;
            }
        }

        public void hasNext() {
            while (!st.hasMoreTokens()) {
                String s = nextLine();
                if (s == null) return;
                eat(s);
            }
        }

        public String next() {
            hasNext();
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}

class L105_UF {
    public static int n;
    public static int m;
    public static long[][] map;
    public static edge[] edges;
    public static int[] fa;
    public static int[] sz;
    static class edge implements Comparable{
        int point1;
        int point2;
        long val;

        public edge(int point1, int point2, long val) {
            this.point1 = point1;
            this.point2 = point2;
            this.val = val;
        }

        @Override
        public int compareTo(Object o) {
            edge oo = (edge) o;
            return this.val>oo.val?1:-1;
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        edges = new edge[2*m*n-n-m];
        map = new long[2][m];
        fa = new int[n*m];
        sz = new int[n*m];
        long ans = 0L;
        int edge_index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i%2][j] = in.nextLong();
                //construct the edge from (i,j-1) to (i,j)
                if(j!=0){
                    edges[edge_index++] =new edge(i*m+j-1,i*m+j,Math.max(map[i%2][j-1],map[i%2][j]));
                }
                //construct the edge from (i-1,j) to (i,j)
                if(i!=0){
                    edges[edge_index++] = new edge((i-1)*m+j,i*m+j,Math.max(map[0][j],map[1][j]));
                }

            }
        }
        Arrays.sort(edges);

        for (int i = 0; i < n * m; i++) {
            fa[i] = i;
        }
        for (int i = 0; i < n * m; i++) {
            sz[i] = 1;
        }
        for (int i = 0; i < 2*m*n-n-m; i++) {
            union(edges[i].point1,edges[i].point2);
            ans = Math.max(ans,edges[i].val);
            if(isUnion(0,m*n-1)){
                break;
            }
        }
        System.out.println(ans);
    }

    public static int find(int x){
        if(fa[x] == x)return fa[x];
        fa[x] = find(fa[x]);
        return fa[x];
    }

    public static boolean isUnion(int i, int j){
        return find(i) == find(j);
    }

    public static void union(int x, int y){
        int xfa = find(x);
        int yfa = find(y);
        if (xfa == yfa) return;
        if(sz[xfa]>=sz[yfa]){
            sz[xfa] += sz[yfa];
            fa[yfa] = xfa;
        }else{
            sz[yfa] += sz[xfa];
            fa[xfa] = yfa;
        }
    }
}
