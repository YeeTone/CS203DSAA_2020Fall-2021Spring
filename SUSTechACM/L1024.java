package SUSTechACM;

import java.math.*;
import java.io.*;
import java.util.*;

public class L1024 {
    public static void main(String[] args) {
        FastReader fastReader =new FastReader(System.in);
        FastWriter fastWriter =new FastWriter(System.out);
        int t= fastReader.nextInt();

        for (int i = 0; i < t; i++) {
            int n= fastReader.nextInt();
            int k= fastReader.nextInt();

            int[]seats=new int[n];
            for (int j = 0; j < n; j++) {
                seats[j]= fastReader.nextInt();
            }
            Arrays.sort(seats);

            int low=0,high=seats[n-1]-seats[0];
            int ans=0;
            while (low<=high){
                int mid=low+(high-low)/2;
                if(checkIsAvailable(mid,k,n,seats)){
                    ans=mid;
                    low=mid+1;
                }else {
                    high=mid-1;
                }
            }
            fastWriter.println(ans);
        }

        fastReader.close();
        fastWriter.close();
    }
    private static boolean checkIsAvailable(int minimumDistance,int students,int seatNumbers,int[]seats){
        int[]distances=new int[seatNumbers-1];
        for (int i = 0; i < seatNumbers-1; i++) {
            distances[i]=seats[i+1]-seats[i];
        }

        int remain=0;
        int rest_students=students;
        for (int distance : distances) {
            if (remain + distance >= minimumDistance) {
                remain = 0;
                rest_students -= 1;
            } else {
                remain += distance;
            }
        }
        rest_students-=1;
        return rest_students<=0;
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
