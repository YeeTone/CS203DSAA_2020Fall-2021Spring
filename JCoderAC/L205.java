package CS203;

import java.io.*;
import java.util.*;

public class L205 {
    public static void main(String[] args) {
        FastScanner fastScanner=new FastScanner(System.in);
        int n=fastScanner.nextInt();

        Pair_XY[]pairs=new Pair_XY[n];

        long maxValue=0;
        for (int i = 0; i < n; i++) {
            long x= fastScanner.nextLong();
            long y= fastScanner.nextLong();
            pairs[i]=new Pair_XY(x,y);

            if(y>maxValue){
                maxValue=y;
            }
        }
        fastScanner.close();

        Arrays.sort(pairs, (o1, o2) -> {
            if(o1.x!=o2.x){
                return (int) (o1.x-o2.x);
            }else {
                return (int)(o1.y- o2.y);
            }
        });

        int counts=0;
        long curX=-1;
        long[]minYs=new long[n];
        long[]maxYs=new long[n];

        for (int i = 0; i < n; i++) {
            if(pairs[i].x!=curX){
                counts+=1;
                curX=pairs[i].x;
                minYs[counts-1]=pairs[i].y;
            }

            maxYs[counts-1]=pairs[i].y;
        }

        double[]answers=new double[(int) (maxValue*2+1)];
        for (int i = 0; i < answers.length; i++) {
            answers[i]=i/2.0;
        }

        long low=0,high=2*maxValue;
        int answer=0;
        while (low<high){
            long mid=(low+high)/2;
            double error=answers[(int) mid];
            if(checkIsOK(maxYs,minYs,pairs,error,counts)){
                high=mid;
                answer=(int)mid;
            }else {
                low=mid+1;
            }
        }
        System.out.printf("%.1f",answers[answer]);
    }
    private static boolean checkIsOK(long[]maxYs,long[]minYs,Pair_XY[]pairs,double error,int counts){
        boolean v1used=false,v2used=false;
        long min=1000000000,max=-1;
        for (int i = 0; i < counts; i++) {
            if(!v1used){
                if(maxYs[i]>error){
                    min=minYs[i];
                    max=maxYs[i];
                    if(i==0&&pairs[0].x==0){
                        return false;
                    }
                    if(max-min>2*error){
                        return false;
                    }
                    v1used=true;
                }
            }
            else if(!v2used){
                if(min>minYs[i]){
                    min=minYs[i];
                }
                if(max<maxYs[i]){
                    max=maxYs[i];
                }

                if(max-min>2*error) {
                    v2used=true;
                    min=minYs[i];
                    max=maxYs[i];
                    if(max-min>2*error){
                        return false;
                    }
                }
            }
            else {
                if(min>minYs[i]){
                    min=minYs[i];
                }
                if(max<maxYs[i]){
                    max=maxYs[i];
                }

                if(max-min>2*error) {
                    return false;
                }
                if(maxYs[i]-minYs[i]>2*error){
                    return false;
                }
            }
        }
        return true;
    }

    private static class Pair_XY {
        private final long x;
        private final long y;

        public Pair_XY(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Pair_XY{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }


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