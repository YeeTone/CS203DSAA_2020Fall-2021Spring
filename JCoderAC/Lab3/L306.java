package JCoderAC.Lab3;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.StringTokenizer;

public class L306 {
    private static final long mod=99999997;
    public static void main(String[] args) throws IOException {
        FastReader fastReader=new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);

        int n= fastReader.nextInt();
        Rank_Value[]heights1=new Rank_Value[n];
        Rank_Value[]heights2=new Rank_Value[n];
        for (int i = 0; i < n; i++) {
            heights1[i]= new Rank_Value(fastReader.nextLong());
        }
        for (int i = 0; i < n; i++) {
            heights2[i]= new Rank_Value(fastReader.nextLong());
        }
        fastWriter.println(getSwapTimes(heights1,heights2));

        /*fastReader.close();*/
        fastWriter.close();
    }
    private static long getSwapTimes(Rank_Value[]heights1,Rank_Value[]heights2){
        Rank_Value[]sorted1=heights1.clone();
        rank_value_mergeSort(sorted1);
        for (int i = 0; i < sorted1.length; i++) {
            sorted1[i].rank=i;
        }

        Rank_Value[]sorted2=heights2.clone();
        rank_value_mergeSort(sorted2);
        for (int i = 0; i < sorted2.length; i++) {
            sorted2[i].rank=i;
        }

        int[]ranks1=new int[heights1.length];
        for (int i = 0; i < heights1.length; i++) {
            ranks1[i]=heights1[i].rank;
        }

        int[]ranks2=new int[heights2.length];
        for (int i = 0; i < heights2.length; i++) {
            ranks2[i]= heights2[i].rank;
        }

        HashMap<Integer,Integer>ranks_indexes_function=new HashMap<>();
        for (int i = 0; i < heights2.length; i++) {
            ranks_indexes_function.put(ranks2[i],i);
            //index_ranks_function[i]=new Index_Rank(i,ranks2[i]);
        }

        int[]curIndexes1=new int[heights1.length];
        for (int i = 0; i < heights1.length; i++) {
            curIndexes1[i]=ranks_indexes_function.get(ranks1[i]);
        }
        //System.out.println("Arrays.toString(curIndexes1) = " + Arrays.toString(curIndexes1));

        return getInverseCountByMergeSort(curIndexes1);
    }
    private static long getInverseCountByMergeSort(int[]curIndexes1){
        int[]copy=curIndexes1.clone();
        long answer=getInverseCountByMergeSort(curIndexes1,copy,0, curIndexes1.length-1);
        return answer%mod;
    }
    private static long getInverseCountByMergeSort(int[]array, int[]copy, int low, int high){
        if(low == high){
            return 0;
        }
        int middle = low +(high - low)/2;
        long leftCounts=getInverseCountByMergeSort(array,copy, low, middle );
        long rightCounts=getInverseCountByMergeSort(array, copy, middle +1, high);
        long counts=0;

        int i= middle,j= high;
        int copyLoc= high;
        while (i>= low &&j>middle){
            if(array[i]>array[j]){
                counts+=j-middle;
                copy[copyLoc--]=array[i--];
            }else {
                copy[copyLoc--]=array[j--];
            }
        }
        for(;i>=low;i--)
        {
            copy[copyLoc--]=array[i];
        }
        for(;j>middle;j--)
        {
            copy[copyLoc--]=array[j];
        }
        if (high + 1 - low >= 0){
            System.arraycopy(copy, low, array, low, high + 1 - low);
        }
        return leftCounts+counts+rightCounts;
    }

    private static long getInverseCountByBruteForce(int[]curIndexes1){
        long answer=0;
        for (int i = 0; i < curIndexes1.length; i++) {
            for (int j = i+1; j < curIndexes1.length; j++) {
                if(curIndexes1[i]>curIndexes1[j]){
                    answer++;
                }
            }
        }
        return answer%mod;
    }

    private static void rank_value_mergeSort(Rank_Value[]array) {
        if (array.length > 1) {
            int p = array.length / 2;

            Rank_Value[] array1 = new Rank_Value[p];
            System.arraycopy(array, 0, array1, 0, array1.length);

            Rank_Value[] array2 = new Rank_Value[array.length - p];
            System.arraycopy(array, p, array2, 0, array2.length);

            rank_value_mergeSort(array1);
            rank_value_mergeSort(array2);

            System.arraycopy(rank_value_merge(array1, array2), 0, array, 0, array.length);
        }
    }

    private static Rank_Value[] rank_value_merge(Rank_Value[]array1,Rank_Value[]array2){
        Rank_Value[]merged=new Rank_Value[array1.length+ array2.length];
        int i=0,j=0;
        for (int k = 0; k < merged.length; k++) {
            if(i<array1.length&&(j>= array2.length||array1[i].value< array2[j].value)){
                merged[k]=array1[i];
                i++;
            }else {
                merged[k]=array2[j];
                j++;
            }
        }
        return merged;
    }

    private static class Rank_Value{
        long value;
        int rank;
        public Rank_Value(long v){
            this.value=v;
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
