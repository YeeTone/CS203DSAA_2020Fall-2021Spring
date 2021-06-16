package JCoderAC.Bonus;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class L1006 {
    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);

        long L= fastReader.nextLong();
        int N= fastReader.nextInt();
        int M= fastReader.nextInt();

        long[]dis=new long[N+1];
        for (int i = 0; i < N; i++) {
            dis[i]= fastReader.nextLong();
        }
        dis[N]=L;
        //System.out.println(Arrays.toString(dis));

        long[] diff=new long[N+1];
        for (int i = 0; i <= N; i++) {
            if(i==0){
                diff[i]=dis[i];
            }else {
                diff[i]=dis[i]-dis[i-1];
            }
        }

        //System.out.println(Arrays.toString(diff));

        long low=0,high=L;
        long answer=-1;
        while (low<=high){
            long mid=(high-low)/2+low;
            if(isJumpable(diff,mid,M)){
                answer=mid;
                low=mid+1;
            }else {
                high=mid-1;
            }
        }


        fastWriter.println(answer);

        fastReader.close();
        fastWriter.close();

    }
    private static boolean isJumpable(long[]dis,final long minDistance,int M){
        long currentLength=0;
        for (long di : dis) {
            if (currentLength + di < minDistance) {
                M--;
                currentLength += di;
            } else {
                currentLength = 0;
            }
        }

        return M>=0;
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
