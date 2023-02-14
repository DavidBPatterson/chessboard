import java.util.random.RandomGenerator;

public class Main {

    private static final int MAX_NUM = 1_000_000_000;
    private static long array[] = new long[64];

    public static void main(String[] args) {

        for (int i = 0; i < 64; i++) {
            array[i] = grayEncode(i);
        }

        long count = 0;

        for (int x = 0; x < MAX_NUM; x++) {
            long board = x;// 345235235; //RandomGenerator.getDefault().nextLong();

            long groupValue = getGroupValue(board);

            boolean boardGood = true;
            for (int i=0;i<64;i++) {
                long cellNumber = grayDecode(groupValue ^ grayEncode(i));
                board ^= (1L << cellNumber);
                long target = grayDecode(getGroupValue(board));
                if (target != i) {
                    boardGood = false;
                }
                board ^= (1L << cellNumber);
            }
            if (!boardGood) {
                System.out.println("Oh noes!!!");
            } else {
                count++;
            }
            if (count % 1_000_000 == 0) {
                System.out.println("Boards checked: "+count);
            }
        }
        System.out.println("Boards checked: "+count);
    }

    private static long getGroupValue(long board) {
        long groupValue = 0;

        for (int group = 0; group < 6; group++) {
            for (int i = 0; i < 64; i++) {
                long groupBit = 1L << group;
                if ((array[i] & groupBit) != 0) {
                    if ((board & (1L << i)) != 0) {
                        groupValue ^= groupBit;
                    }
                }
            }
        }

        return groupValue;
    }

    private static String binString(long l) {
        return binString(l, 64);
    }

    private static String binString(long l, int count) {
        var s = Long.toBinaryString(l);
        if (s.length() < count) {
            return "0".repeat(count - s.length()) + s;
        }
        return s;
    }

    public static long grayEncode(long n) {
        return n ^ (n >>> 1);
    }

    public static long grayDecode(long n) {
        long p = n;
        while ((n >>>= 1) != 0)
            p ^= n;
        return p;
    }

}