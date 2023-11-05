import java.math.BigInteger;

public class Signature {
    private final BigInteger S1, S2;

    public Signature(BigInteger S1, BigInteger S2) {
        this.S1 = S1;
        this.S2 = S2;
    }

    public BigInteger getS1() {
        return S1;
    }

    public BigInteger getS2() {
        return S2;
    }

    @Override
    public String toString() {
        return "(" + S1 + ", " + S2 + ")";
    }
}
