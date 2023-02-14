public class Sine extends Method {
    @Override
    public Long apply(long value) {
        double sin = Math.sin(value);
        return Math.round(sin * 64.0);
    }

    @Override
    public String toString() {
        return "Sine";
    }
}
