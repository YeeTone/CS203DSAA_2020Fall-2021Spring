import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class L1014 {
    public static void main(String[] args) throws Throwable{
        Scanner in=new Scanner(System.in);
        StringBuilder input1;
        StringBuilder input2;
        StringBuilder output;
        while (true){
            if(in.hasNext()){
                input1=new StringBuilder(in.next()).reverse();
            }else {
                break;
            }
            if(in.hasNext()){
                input2=new StringBuilder(in.next()).reverse();
            }else {
                break;
            }
            output=new StringBuilder();

            int maxLength=Math.max(input1.length(),input2.length());
            for (int i = input1.length()-1; i < maxLength; i++) {
                input1.append(0);
            }
            for (int j = input2.length()-1; j < maxLength; j++) {
                input2.append(0);
            }
            //System.out.println(input1.toString());
            //System.out.println(input2.toString());

            int overflow=0;
            for (int i=0;i<maxLength;i++){
                int number=input1.charAt(i)+input2.charAt(i)-'0'-'0'+overflow;
                if(number>=10){
                    number-=10;
                    overflow=1;
                }else {
                    overflow=0;
                }
                output.append(number);
            }
            if(overflow==1){
                output.append(1);
            }
            //System.out.println(output.toString());
            System.out.println(output.reverse().toString());
        }
    }
    private static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public char[] nextCharArray() {
            return next().toCharArray();
        }

        //         public boolean hasNext() {
//             try {
//                 return reader.ready();
//             } catch(IOException e) {
//                 throw new RuntimeException(e);
//             }
//         }
        public boolean hasNext() {
            try {
                String string = reader.readLine();
                if (string == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(string);
                return tokenizer.hasMoreTokens();
            } catch (IOException e) {
                return false;
            }
        }

        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecinal() {
            return new BigDecimal(next());
        }
    }
}
