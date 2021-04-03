package JCoderAC.Lab6;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class L604 {
    private static final long key= 12345678987654321L;
    private static final long[]keyPowers=new long[100005];
    private static final long[]hashArray1=new long[100005];
    private static final long[]hashArray2=new long[100005];
    private static final long[]hashValues=new long[100005];

    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);
        char[]str1=fastReader.next().toCharArray();
        char[]str2=fastReader.next().toCharArray();

        char[]shorter=str1.length>str2.length?str2:str1;
        char[]longer=shorter==str1?str2:str1;

        setKeyPowers();
        setHashArray(longer,hashArray1);
        setHashArray(shorter,hashArray2);

        fastWriter.println(getAnswerByBinarySearch(longer,shorter));

        fastReader.close();
        fastWriter.close();
    }
    private static int getAnswerByBinarySearch(char[]str1,char[]str2){

        int left=0,right=Math.min(str1.length,str2.length);
        while (left<right){
            int mid=left+(right-left)/2;
            if(isOK(mid,str1,str2)){
                left=mid+1;
            }else {
                right=mid;
            }
        }
        if(!isOK(left,str1,str2)){
            left--;
        }

        return left;
        /*int left=0,right=Math.min(str1.length,str2.length);
        int answer=0;
        while (left<right){
            int mid=left+(right-left)/2;
            if(isOK(mid,str1,str2)){
                answer=mid;
                left=mid+1;
            }else {
                right=mid-1;
            }
        }

        return answer;*/

    }

    private static void setKeyPowers(){
        keyPowers[0]=1;
        for (int i = 1; i < 100005; i++) {
            keyPowers[i]=(keyPowers[i-1]*key);
        }
    }

    private static void setHashArray(char[]str,long[]hashArray){
        int length= str.length;
        hashArray[length]=0;
        for (int i = length-1; i >= 0; i--) {
            hashArray[i]=hashArray[i+1]*key+str[i]-'A'+1;
        }
    }

    private static long getHashValue(int i,int l,long[]hashArray){
        return hashArray[i]-hashArray[i+l]*keyPowers[l];
    }

    private static boolean isOK(int number,char[]str1,char[]str2){
        if(number>str1.length||number>str2.length){
            return false;
        }

        for (int i = 0; i <= str1.length-number; i++) {
            hashValues[i]=getHashValue(i,number,hashArray1);
        }

        Arrays.sort(hashValues,0,str1.length-number);
        for (int i = 0; i <= str2.length - number; i++) {
            long te=getHashValue(i,number,hashArray2);
            if(Arrays.binarySearch(hashValues,0,str1.length-number,te)>=0){
                return true;
            }
        }
        return false;
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
