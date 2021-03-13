package JCoderAC.Lab3;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class L305 {
    public static void main(String[] args) {
        FastReader fastReader = new FastReader(System.in);
        FastWriter fastWriter = new FastWriter(System.out);

        long xa = fastReader.nextLong();
        long ya = fastReader.nextLong();
        long xb = fastReader.nextLong();
        long yb = fastReader.nextLong();

        int k = fastReader.nextInt();

        Enemy[] enemies = new Enemy[k];
        for (int i = 0; i < k; i++) {
            long xi = fastReader.nextLong();
            long yi = fastReader.nextLong();
            Enemy enemy = new Enemy(xi, yi, xa, ya, xb, yb);
            enemies[i] = enemy;
        }

        Enemy.enemyMergeSort(enemies);

        /*for (int i = 0; i < k; i++) {
            fastWriter.println(enemies[i]);
        }*/

        long attackDistanceByA = 0, attackDistanceByB = 0;

        long answer = enemies[0].distanceA;
        for (int i = 0; i < k; i++) {
            answer = Math.min(answer, enemies[i].distanceA + attackDistanceByB);
            attackDistanceByB = Math.max(attackDistanceByB, enemies[i].distanceB);
        }

        answer = Math.min(answer, attackDistanceByB);

        /*System.out.println("attackDistanceByA = " + attackDistanceByA);
        System.out.println("attackDistanceByB = " + attackDistanceByB);*/

        fastWriter.println(answer);

        fastReader.close();
        fastWriter.close();
    }

    private static class Enemy {
        long xi;
        long yi;
        long distanceA;
        long distanceB;

        public Enemy(long xi, long yi, long xa, long ya, long xb, long yb) {
            this.xi = xi;
            this.yi = yi;
            setMinDistances(xa, ya, xb, yb);
        }

        public void setMinDistances(long xa, long ya, long xb, long yb) {
            this.distanceA = getDistance(xa, ya);
            this.distanceB = getDistance(xb, yb);
            //System.out.println("distanceA = " + distanceA);
        }

        @Override
        public String toString() {
            return "Enemy{" +
                    "xi=" + xi +
                    ", yi=" + yi +
                    ", distanceA=" + distanceA +
                    ", distanceB=" + distanceB +
                    '}';
        }

        public long getDistance(long x, long y) {
            return (x - xi) * (x - xi) + (y - yi) * (y - yi);
        }

        public static void enemyMergeSort(Enemy[] enemies) {
            if (enemies.length > 1) {
                int p = enemies.length / 2;

                Enemy[] enemies1 = new Enemy[p];
                System.arraycopy(enemies, 0, enemies1, 0, enemies1.length);

                Enemy[] enemies2 = new Enemy[enemies.length - p];
                System.arraycopy(enemies, p, enemies2, 0, enemies2.length);

                enemyMergeSort(enemies1);
                enemyMergeSort(enemies2);

                System.arraycopy(enemyMerge(enemies1, enemies2), 0, enemies, 0, enemies.length);
            }
        }

        private static Enemy[] enemyMerge(Enemy[] enemies1, Enemy[] enemies2) {
            Enemy[] enemies = new Enemy[enemies1.length + enemies2.length];
            int i = 0, j = 0;
            for (int k = 0; k < enemies.length; k++) {
                if (i < enemies1.length && (j >= enemies2.length || enemies1[i].distanceA > enemies2[j].distanceA)) {
                    enemies[k] = enemies1[i];
                    i++;
                } else {
                    enemies[k] = enemies2[j];
                    j++;
                }
            }

            return enemies;
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
