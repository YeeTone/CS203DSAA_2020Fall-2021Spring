package SUSTechACM;


import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class L1340 {

    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);
        int t=fastReader.nextInt();

        for (int i = 0; i < t; i++) {
            int n= fastReader.nextInt();
            int k= fastReader.nextInt();

            DishNode firstDish=new DishNode();
            DishNode curDish=firstDish;
            for (int j = 0; j < n; j++) {
                curDish.value=j+1;
                if(j==n-1){
                    curDish.next=firstDish;
                }else {
                    curDish.next=new DishNode();
                    curDish=curDish.next;
                }
            }

            curDish=firstDish;
            int rest=n;
            while (rest>0){

                int count=0;
                //fastWriter.println("-----------");
                while (count<k){

                    if(!curDish.visited){
                        //fastWriter.println("Count:"+count);
                        //fastWriter.println(curDish);
                        count+=1;
                    }
                    if(count==k){
                        break;
                    }
                    curDish=curDish.next;

                }
                curDish.visited=true;
                //System.out.print("CurDish:");

                fastWriter.print(curDish.value);
                if(rest!=1){
                    fastWriter.print(" ");
                }

                curDish=curDish.next;
                rest--;
            }
            fastWriter.println();
        }
    }
    private static class DishNode{
        boolean visited=false;
        int value;
        DishNode next;

        @Override
        public String toString() {
            return "DishNode{" +
                    "visited=" + visited +
                    ", value=" + value +
                    '}';
        }
    }


    private static class FastReader implements Closeable {
        BufferedReader br;
        StringTokenizer st;

        public FastReader(InputStream in) {
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

        public void close()throws IOException{
            st=null;
            br.close();
        }
    }

    private static class FastWriter implements Closeable {
        private final BufferedWriter writer;
        public FastWriter(OutputStream out){
            this.writer=new BufferedWriter(new OutputStreamWriter(out));
        }

        public void print(Object object){
            try{
                writer.write(object.toString());
                writer.flush();
            }catch (IOException e){
                e.printStackTrace();
                System.exit(1);
            }

        }
        public void printf(String format,Object... os) {
            try{
                writer.write(String.format(format,os));
                writer.flush();
            }catch (IOException e){
                e.printStackTrace();
                System.exit(1);
            }

        }
        public void println(){
            try{
                writer.write(System.lineSeparator());
                writer.flush();
            }catch (IOException e){
                e.printStackTrace();
                System.exit(1);
            }
        }

        public void println(Object object) {
            try{
                writer.write(object.toString());
                writer.write(System.lineSeparator());
                writer.flush();
            }catch (IOException e){
                e.printStackTrace();
                System.exit(1);
            }

        }

        @Override
        public void close()throws IOException{
            writer.close();
        }
    }
}
