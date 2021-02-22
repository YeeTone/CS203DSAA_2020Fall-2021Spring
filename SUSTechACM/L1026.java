package SUSTechACM;

import java.math.*;
import java.io.*;
import java.util.*;

public class L1026 {
    public static void main(String[] args) {
        FastReader fastReader =new FastReader(System.in);
        FastWriter fastWriter =new FastWriter(System.out);
        int t= fastReader.nextInt();

        for (int i = 0; i < t; i++) {
            int x_balls_a= fastReader.nextInt();
            int y_balls_b= fastReader.nextInt();
            //System.out.println("y_balls_b = " + y_balls_b);

            long low=0,high=x_balls_a*y_balls_b;
            long ans=-1;
            while (low<=high){
                long mid=low+(high-low)/2;
                //System.out.println("mid="+mid);
                if(isMoveBallsSuccess(mid,x_balls_a,y_balls_b)){
                    ans=mid;
                    high=mid-1;
                }else {
                    low=mid+1;
                }
            }
            fastWriter.println(ans);
        }

        fastReader.close();
        fastWriter.close();
    }
    private static boolean isMoveBallsSuccess(long mid,int x_balls_a,int y_balls_b){
        int counts=0;
        /*System.out.println("x_balls_a = " + x_balls_a);
        System.out.println("y_balls_b = " + y_balls_b);
        System.out.println("------------------");*/
        while (counts<mid){
            if(x_balls_a==y_balls_b){
                return true;
            } else if(x_balls_a<y_balls_b){
                y_balls_b=y_balls_b-x_balls_a;
                x_balls_a+=x_balls_a;
            }else {
                x_balls_a=x_balls_a-y_balls_b;
                y_balls_b+=y_balls_b;
            }
            counts+=1;
            /*System.out.println("x_balls_a = " + x_balls_a);
            System.out.println("y_balls_b = " + y_balls_b);
            System.out.println("------------------");*/
        }
        return false;
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
