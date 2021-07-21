package syz;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class SYZ0009 {
    public static void main(String[] args) {
        FastReader fastReader = new FastReader(System.in);
        FastWriter fastWriter = new FastWriter(System.out);

        int n = fastReader.nextInt();
        int m = fastReader.nextInt();
        GraphNode[] nodes = new GraphNode[n];

        for (int i = 0; i < n; i++) {
            nodes[i] = new GraphNode(i);
        }

        for (int i = 0; i < m; i++) {
            GraphNode from = nodes[fastReader.nextInt() - 1];
            GraphNode to = nodes[fastReader.nextInt() - 1];
            from.addNeighbor(to);
        }

        nodes[0].bfs();

        fastWriter.println(nodes[n - 1].distanceFrom1);

        fastReader.close();
        fastWriter.close();
    }

    private static class GraphNode {
        boolean confirmed;
        int index;
        int distanceFrom1;
        GraphNode father;
        List<GraphNode> outNeighbors;

        public GraphNode(int index) {
            this.confirmed= false;
            this.index = index;
            this.distanceFrom1 = Integer.MAX_VALUE - 150;
            this.father = null;
            this.outNeighbors = new ArrayList<>();
        }

        public void addNeighbor(GraphNode out) {
            this.outNeighbors.add(out);
        }

        public void bfs() {
            this.father = null;
            this.distanceFrom1 = 0;

            Queue<GraphNode> nodeQueue = new LinkedList<>();
            nodeQueue.offer(this);

            while (!nodeQueue.isEmpty()) {
                GraphNode node = nodeQueue.poll();
                node.confirmed=true;
                for (GraphNode n : node.outNeighbors) {
                    if(n.confirmed){
                        continue;
                    }
                    nodeQueue.offer(n);
                    if (n.distanceFrom1 > node.distanceFrom1 + 1) {
                        n.distanceFrom1 = node.distanceFrom1 + 1;
                        n.father = node;
                    }
                }
            }
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
            while (!st.hasMoreTokens()) {
                String s = nextLine();
                if (s == null) return false;
                eat(s);
            }
            return true;
        }

        public String next() {
            hasNext();
            return st.nextToken();
        }

        public boolean nextBoolean() {
            return Boolean.parseBoolean(next());
        }


        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public float nextFloat() {
            return Float.parseFloat(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecimal() {
            return new BigDecimal(next());
        }

        public void close() {
            try {
                st = null;
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }

        }
    }

    private static class FastWriter implements Closeable {
        private final PrintWriter writer;

        public FastWriter(OutputStream out) {
            this.writer = new PrintWriter(out);
        }

        public void print(Object object) {
            writer.write(object.toString());
        }

        public void printf(String format, Object... os) {
            writer.write(String.format(format, os));
        }

        public void println() {
            writer.write(System.lineSeparator());
        }

        public void println(Object object) {
            writer.write(object.toString());
            writer.write(System.lineSeparator());
        }

        @Override
        public void close() {
            writer.close();
        }
    }
}
