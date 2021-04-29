package JCoderAC.Lab8;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class L803 {
    private static final StreamTokenizer in
            =new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    private static int nextInt(){
        try{
            in.nextToken();
            return (int)in.nval;
        }catch (IOException e){
            return -1;
        }
    }
    private static long nextLong(){
        try{
            in.nextToken();
            return (long)in.nval;
        }catch (IOException e){
            return -1;
        }
    }

    public static void main(String[] args) {
        FastWriter fastWriter=new FastWriter(System.out);
        int t=nextInt();
        while (t-->0){
            int n=nextInt();
            Good[]goods=new Good[n];
            for (int i = 0; i < n; i++) {
                goods[i]=new Good();
            }

            for (int i = 0; i < n; i++) {
                int ai=nextInt();
                goods[i].setProfit(ai);
            }

            for (int i = 0; i < n; i++) {
                int bi=nextInt();
                goods[i].setDeadline(bi);
            }

            Arrays.sort(goods, Comparator.comparingInt(g -> g.biBuyDeadline));

            long answer=0;
            int time=1;

            GoodMinHeap minHeap=new GoodMinHeap(n);
            for (int i = 0; i < n; i++) {
                if(minHeap.isEmpty()){
                    minHeap.push(goods[i]);
                    time++;
                }

                if(time<=goods[i].biBuyDeadline){
                    minHeap.push(goods[i]);
                    time++;
                }else{
                    if(minHeap.getMin().aiProfit<goods[i].aiProfit){
                        minHeap.popMin();
                        minHeap.push(goods[i]);
                    }
                }
            }

            while (!minHeap.isEmpty()){
                answer+=minHeap.getMin().aiProfit;
                minHeap.popMin();
            }

            /*for (int i = 0; i < minHeap.size; i++) {
                try{
                    answer+=minHeap.goods[i].aiProfit;
                }catch (RuntimeException re){
                    break;
                }
            }*/
            fastWriter.println(answer);
        }
        fastWriter.close();
    }
    private static class Good{
        int aiProfit;
        int biBuyDeadline;

        public void setProfit(int aiProfit) {
            this.aiProfit = aiProfit;
        }

        public void setDeadline(int biBuyDeadline) {
            this.biBuyDeadline = biBuyDeadline;
        }
    }
    public static class GoodMinHeap {
        Good[]goods;
        int size;
        int capacity;

        public GoodMinHeap(int capacity){
            this.goods=new Good[capacity+1];
            this.size=0;
            this.capacity=capacity;
        }
        public boolean isEmpty(){
            return this.size==0;
        }

        public void swap(int x,int y){
            Good temp=goods[x];
            goods[x]=goods[y];
            goods[y]=temp;
        }

        public void smallAdjust(int index){
            while (index>1){
                if(goods[index].aiProfit<goods[index/2].aiProfit){
                    swap(index,index/2);
                    index/=2;
                }else {
                    break;
                }
            }
        }

        private void bigAdjust() {
            int index = 1;
            while (2 * index <= size) {
                int min_index = index;
                if (2 * index <= size) {
                    min_index = goods[min_index].aiProfit > goods[2 * index].aiProfit ? 2 * index : min_index;
                }

                if (2 * index + 1 <= size) {
                    min_index = goods[min_index].aiProfit > goods[2 * index + 1].aiProfit ? (2 * index + 1)
                            : min_index;
                }

                if (min_index != index) {
                    swap(min_index, index);
                    index = min_index;
                } else {
                    break;
                }
            }
        }

        public void popMin(){
            swap(1,size);
            size--;
            bigAdjust();
        }

        public Good getMin(){
            return goods[1];
        }

        public void push(Good g){
            goods[++size]=g;
            smallAdjust(size);
        }
    }


    private static class FastReader implements Closeable {
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
                throw new RuntimeException();
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
