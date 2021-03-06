package SUSTechACM;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class L1299 {
    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);
        int t= fastReader.nextInt();
        for (int i = 0; i < t; i++) {
            int f= fastReader.nextInt();
            int w= fastReader.nextInt();
            int s= fastReader.nextInt();
            fastWriter.println(getAttacks(f,w,s));
        }

        fastReader.close();
        fastWriter.close();
    }
    private static int getAttacks(int f,int w,int s){
        int low=0,high=Math.max(Math.max(f,w),s);
        int answer=0;
        while (low<=high){
            int mid=(low)+(high-low)/2;
            if(checkAttackIsOK(mid,f,w,s)){
                answer=mid;
                low=mid+1;
            }else {
                high=mid-1;
            }
        }
        return answer;
    }
    private static boolean checkAttackIsOK(int attack,int f,int w,int s){
        f-=attack;
        w-=attack;
        int rest=f+w+s;

        return f>=0&&w>=0&&rest>=attack;
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
