package JCoderAC;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class L205 {
    public static void main(String[] args) {
        FastReader fastReader =new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);
        int n= fastReader.nextInt();

        Pair_XY[]pairs=new Pair_XY[n];

        long maxValue=0;
        for (int i = 0; i < n; i++) {
            long x= fastReader.nextLong();
            long y= fastReader.nextLong();
            pairs[i]=new Pair_XY(x,y);

            if(y>maxValue){
                maxValue=y;
            }
        }
        fastReader.close();

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
        fastWriter.printf("%.1f",answers[answer]);

        fastReader.close();
        fastWriter.close();
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