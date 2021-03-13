package JCoderAC.Lab2;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class L204 {
    public static void main(String[] args) {
        FastReader fastReader =new FastReader(System.in);
        FastWriter fastWriter =new FastWriter(System.out);

        long xr= fastReader.nextLong();
        long yr= fastReader.nextLong();
        long xc= fastReader.nextLong();
        long yc= fastReader.nextLong();

        int n= fastReader.nextInt();
        char[]instructions= fastReader.next().toCharArray();

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
        fastWriter.println(answer);

        fastReader.close();
        fastWriter.close();
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
