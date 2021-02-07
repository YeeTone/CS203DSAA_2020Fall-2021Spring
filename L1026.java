package SUSTechACM;

import java.math.*;
import java.io.*;
import java.util.*;

public class L1026 {
    public static void main(String[] args) {
        FastScanner fastScanner=new FastScanner(System.in);
        int t=fastScanner.nextInt();

        for (int i = 0; i < t; i++) {
            int x_balls_a= fastScanner.nextInt();
            int y_balls_b= fastScanner.nextInt();
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
            System.out.println(ans);
        }
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
            }

        }
    }
}
