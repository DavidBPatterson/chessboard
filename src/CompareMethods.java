import java.util.List;
import java.util.random.RandomGenerator;

public class CompareMethods {


    private static final int MAX_NUM = 10_000_000;

    private long MOD = 64;

    List<Method> methods = List.of(
            new MD5(),
            new Sine(),
            new Polynomial()
    );

    public static void main(String[] args) {
        new CompareMethods().compare();
    }

    public void compare() {
        methods.forEach(method -> {
            long total = 0;
            for (int x = 0; x < MAX_NUM; x++) {
                long result = 0;
                long test = RandomGenerator.getDefault().nextLong();
                var initialHash = method.apply(test);
                var initialMod = Math.abs(initialHash % MOD);
                //System.out.println("Initial: " + initialHash + " mod " + MOD + " " + initialMod);

                for (int i = 0; i < 64; i++) {
                    var nextTry = test ^ (1L << i);
                    var hash = method.apply(nextTry);
                    var mod = Math.abs(hash % MOD);
                    //System.out.println(binString(nextTry) + " hash: " + hash + " mod " + MOD + " " + mod);
                    result |= 1L << mod;
                }

                var countString = binString(result);
                //System.out.println(countString);
                long count = countString.chars().filter(ch -> ch == '1').count();
                //System.out.println(count);
                total += count;
            }
            System.out.println(method + " = " + (double)total/(double)MAX_NUM/(double)64);
        });
    }

    private String binString(long l) {
        var s = Long.toBinaryString(l);
        if (s.length() < 64) {
            return "0".repeat(64 - s.length()) + s;
        }
        return s;
    }
}
