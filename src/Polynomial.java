public class Polynomial extends Method {
    @Override
    public Long apply(long value) {

        long v1 = value % 256;
        value = value / 256;
        long v2 = value % 256;
        value = value / 256;
        long v3 = value % 256;
        value = value / 256;
        long v4 = value % 256;
        value = value / 256;
        long v5 = value % 256;
        value = value / 256;
        long v6 = value % 256;
        value = value / 256;
        long v7 = value % 256;
        value = value / 256;
        long v8 = value % 256;

        double total;
        total = v1 +
                Math.pow(v2,2) % 64 +
                Math.pow(v3,3) % 64 +
                Math.pow(v4,4) % 64 +
                Math.pow(v5,2) % 64 +
                Math.pow(v6,3) % 64 +
                Math.pow(v7,4) % 64 +
                Math.pow(v8,3) % 64 ;
        return (long)total;
    }

    @Override
    public String toString() {
        return "Polynomial";
    }
}