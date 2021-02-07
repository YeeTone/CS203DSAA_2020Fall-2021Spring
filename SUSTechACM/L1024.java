package SUSTechACM;

import java.math.*;
import java.io.*;
import java.util.*;

public class L1024 {
    public static void main(String[] args) {
        FastScanner fastScanner=new FastScanner(System.in);
        int t=fastScanner.nextInt();

        for (int i = 0; i < t; i++) {
            int n= fastScanner.nextInt();
            int k= fastScanner.nextInt();

            int[]seats=new int[n];
            int min=0,max=0;
            for (int j = 0; j < n; j++) {
                seats[j]= fastScanner.nextInt();
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
            System.out.println(ans);
        }
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
