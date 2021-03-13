package JCoderAC.Lab3;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class L303 {
    public static void main(String[] args) {
        FastReader fastReader = new FastReader(System.in);
        FastWriter fastWriter = new FastWriter(System.out);

        int n = fastReader.nextInt();
        int a = fastReader.nextInt();
        int b = fastReader.nextInt();

        long answer = 0;
        SingleDay[] days = new SingleDay[n];
        for (int i = 0; i < n; i++) {
            long xi = fastReader.nextLong();
            long yi = fastReader.nextLong();
            days[i] = new SingleDay(xi, yi);
            answer += yi;
            //fastWriter.println("1answer = " + answer);
        }

        if (b == 0) {
            fastWriter.println(answer);
        } else {
            SingleDay.singleDayMergeSort(days);

            /*for (SingleDay day : days) {
                fastWriter.println(day.xi + " " + day.yi + " " + day.improvement);
            }*/

            long improve1 = 0;
            int executableB = Math.min(n, b);
            int executableAStart = executableB;
            for (int i = 0; i < executableB; i++) {
                if (days[i].improvement <= 0) {
                    executableAStart = i;
                    break;
                } else {
                    answer += days[i].improvement;
                    //fastWriter.println("2answer = " + answer);
                    improve1 = Math.max(improve1, days[i].xi);
                }
            }

            improve1 = (long) (improve1 * (Math.pow(2, a) - 1));

            long improve2 = 0;
            for (int i = executableAStart; i < n; i++) {
                long imp = (long) (days[i].xi * Math.pow(2, a) - days[i].yi);
                improve2 = Math.max(improve2, imp);
            }

            if (executableAStart == executableB) {
                improve2 -= days[executableAStart - 1].improvement;
            }

            answer += Math.max(improve1, improve2);

            fastWriter.println(answer);
        }

        fastReader.close();
        fastWriter.close();
    }

    private static class SingleDay {
        long xi;
        long yi;
        long improvement;

        public SingleDay(long xi, long yi) {
            this.xi = xi;
            this.yi = yi;
            this.improvement = xi - yi;
        }

        public static void singleDayMergeSort(SingleDay[] days) {
            if (days.length > 1) {
                int p = days.length / 2;
                SingleDay[] array2 = new SingleDay[p];
                System.arraycopy(days, 0, array2, 0, array2.length);

                SingleDay[] array3 = new SingleDay[days.length - p];
                System.arraycopy(days, p, array3, 0, array3.length);

                singleDayMergeSort(array2);
                singleDayMergeSort(array3);

                System.arraycopy(singleDayMerge(array2, array3), 0, days, 0, days.length);
            }
        }

        private static SingleDay[] singleDayMerge(SingleDay[] days1, SingleDay[] days2) {
            SingleDay[] days = new SingleDay[days1.length + days2.length];
            int i = 0, j = 0;
            for (int k = 0; k < days.length; k++) {
                if (i < days1.length && (j >= days2.length || days1[i].improvement > days2[j].improvement)) {
                    days[k] = days1[i];
                    i++;
                } else {
                    days[k] = days2[j];
                    j++;
                }
            }
            return days;
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
            while (!st.hasMoreTokens()) {
                String s = nextLine();
                if (s == null) return false;
                eat(s);
            }
            return true;
        }

        public String next() {
            hasNext();
            return st.nextToken();
        }

        public boolean nextBoolean() {
            return Boolean.parseBoolean(next());
        }


        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public float nextFloat() {
            return Float.parseFloat(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecimal() {
            return new BigDecimal(next());
        }

        public void close() {
            try {
                st = null;
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }

        }
    }

    private static class FastWriter implements Closeable {
        private final PrintWriter writer;

        public FastWriter(OutputStream out) {
            this.writer = new PrintWriter(out);
        }

        public void print(Object object) {
            writer.write(object.toString());
        }

        public void printf(String format, Object... os) {
            writer.write(String.format(format, os));
        }

        public void println() {
            writer.write(System.lineSeparator());
        }

        public void println(Object object) {
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
