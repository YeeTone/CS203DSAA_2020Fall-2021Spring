package CS203;

import java.io.*;
import java.util.*;

public class L204 {
    public static void main(String[] args) {
        FastScanner reader=new FastScanner(System.in);
        long xr= reader.nextLong();
        long yr= reader.nextLong();
        long xc= reader.nextLong();
        long yc= reader.nextLong();

        int n= reader.nextInt();
        char[]instructions=reader.next().toCharArray();

        long low_length =0, high_length =(long)1e15;
        long answer=-1;
        while (low_length <= high_length){
            long mid_length= low_length +(high_length - low_length)/2;
            long[]robotFinal=findFinalRobotLocation(xr,yr,n,instructions,mid_length);
            long[]playerInitial=new long[]{xc,yc};
            if(chaseAble(playerInitial,robotFinal,mid_length)){
                answer=mid_length;
                high_length=mid_length-1;
            }else {
                low_length=mid_length+1;
            }
        }
        System.out.println(answer);
    }
    private static long[] findFinalRobotLocation(long xr,long yr,int period,char[]instructions,long mid_length){
        long period_counts=mid_length/period;
        long period_left=mid_length%period;

        long x =0, y =0;
        for (char c : instructions) {
            switch (c){
                case 'U':{
                    y++;
                    break;
                }
                case 'D':{
                    y--;
                    break;
                }
                case 'L':{
                    x--;
                    break;
                }
                case 'R':{
                    x++;
                    break;
                }
            }
        }

        x*=period_counts;
        y*=period_counts;

        for (int i = 0; i < period_left; i++) {
            switch (instructions[i]){
                case 'U':{
                    y++;
                    break;
                }
                case 'D':{
                    y--;
                    break;
                }
                case 'L':{
                    x--;
                    break;
                }
                case 'R':{
                    x++;
                    break;
                }
            }
        }

        xr+=x;
        yr+=y;
        return new long[]{xr,yr};
    }

    private static boolean chaseAble(long[]playerLocation,long[]robotLocation,long mid_length){
        long length=Math.abs(playerLocation[0]-robotLocation[0])+Math.abs(playerLocation[1]-robotLocation[1]);
        return length<=mid_length;
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
