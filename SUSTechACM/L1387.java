package SUSTechACM;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class L1387 {
    //Not AC
    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);
        int t= fastReader.nextInt();

        for (int i = 0; i < t; i++) {
            int n = fastReader.nextInt();
            FootballPlayer[] footballPlayers = new FootballPlayer[n];
            for (int j = 0; j < n; j++) {
                footballPlayers[j] = new FootballPlayer();
                footballPlayers[j].aiPower = fastReader.nextInt();
            }

            for (int j = 0; j < n; j++) {
                footballPlayers[j].biBuyDeadline = fastReader.nextInt();
            }
            /*for (int j = 0; j < n; j++) {
                fastWriter.println(footballPlayers[j]);
            }*/

            Arrays.sort(footballPlayers, (o1, o2) -> {
                if (o1.biBuyDeadline != o2.biBuyDeadline) {
                    return o1.biBuyDeadline - o2.biBuyDeadline;
                } else {
                    return o2.aiPower - o1.aiPower;
                }
            });

            Stack<FootballPlayer>footballPlayerStack=new Stack<>();

            for (int j = 0; j < n; j++) {
                if(footballPlayerStack.isEmpty()){
                    footballPlayerStack.push(footballPlayers[j]);
                }else if(footballPlayerStack.peek().biBuyDeadline<footballPlayers[j].biBuyDeadline){
                    footballPlayerStack.push(footballPlayers[j]);
                }
            }

            long ans=0;
            while (!footballPlayerStack.isEmpty()){
                //fastWriter.println("footballPlayerStack.peek().aiPower = " + footballPlayerStack.peek().aiPower);
                ans+=footballPlayerStack.pop().aiPower;
            }
            fastWriter.println(ans);
        }

        fastReader.close();
        fastWriter.close();
    }
    private static class FootballPlayer{
        int aiPower;
        int biBuyDeadline;

        @Override
        public String toString() {
            return "FootballPlayer{" +
                    "aiPower=" + aiPower +
                    ", biBuyDeadline=" + biBuyDeadline +
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
