package CS203;

import java.io.*;
import java.util.*;

public class L203 {
    public static void main(String[] args) {
        FastScanner reader = new FastScanner(System.in);
        int n = reader.nextInt();
        long k = reader.nextLong();
        long sum = 0;

        long[] height_counts = new long[n];
        for (int i = 0; i < n; i++) {
            height_counts[i] = reader.nextLong();
            sum += height_counts[i];
        }

        long low=0,high=sum,mid_per=0;

        long ans=0;
        while (low<=high){
            mid_per=(low+high)/2;
            if(checkIsValid(height_counts,k,mid_per)){
                low=mid_per+1;
                ans=mid_per;
            }else {
                high=mid_per-1;
            }
        }
        //System.out.println("mid_per = " + mid_per);
        System.out.println(ans*k);
    }
    private static boolean checkIsValid(long[]heights,long k,long perLine) {
        long[]height_counts=heights.clone();
        long counts=0;
        //System.out.println("perLine = " + perLine);
        for (int i = 0; i < height_counts.length;) {
            //System.out.println("i:"+i+",Arrays.toString(height_counts) = " + Arrays.toString(height_counts));
            if(i!= height_counts.length-1){
                if(height_counts[i]+height_counts[i+1]>=perLine){
                    if(height_counts[i]>=perLine){
                        counts+=height_counts[i]/perLine;
                        height_counts[i]%=perLine;
                    }else {
                        counts+=1;
                        height_counts[i+1]-=perLine-height_counts[i];
                        height_counts[i]=0;
                        i++;
                    }
                }else {
                    i++;
                }
            }else {
                counts+=height_counts[i]/perLine;
                height_counts[i]%=perLine;
                i++;
            }
        }
        //System.out.println("(counts>=k) = " + (counts>=k));
        return counts>=k;
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
