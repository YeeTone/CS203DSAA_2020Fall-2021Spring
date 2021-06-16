package JCoderAC.Bonus;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class L1005 {
    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);

        int n= fastReader.nextInt();
        int[]toArray=new int[n+1];
        int[]e=new int[n+1];
        boolean[]used=new boolean[n+1];

        for (int i = 1; i <= n; i++) {
            toArray[i]= fastReader.nextInt();
            e[toArray[i]]++;
        }

        Queue<Integer>q=new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            if(e[i]==0){
                q.offer(i);
                used[i]=true;
            }
        }

        while (!q.isEmpty()){
            int u=q.poll();
            e[toArray[u]]--;
            if(e[toArray[u]]==0){
                q.offer(toArray[u]);
                used[toArray[u]]=true;
            }
        }

        long answer=-1;

        for (int i = 1; i <= n; i++) {
            if(!used[i]){
                used[i]=true;
                int sum=1;
                int to=toArray[i];
                while (!used[to]){
                    used[to]=true;
                    to=toArray[to];
                    sum++;
                }

                if(answer==-1){
                    answer=sum;
                }else {
                    answer=Math.min(answer,sum);
                }
            }
        }

        fastWriter.println(answer);

        fastReader.close();
        fastWriter.close();
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
