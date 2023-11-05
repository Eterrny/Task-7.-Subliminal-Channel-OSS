import java.math.BigInteger;

public class Bob {
    private BigInteger n, k, h, M, M_;
    Signature s;

    public Bob (BigInteger n, BigInteger k, BigInteger h) {
        this.n = n;
        this.k = k;
        this.h = h;
    }

    public void setM_S(BigInteger M_, Signature s) {
        this.M_ = M_;
        this.s = s;
    }

    public BigInteger getM() {
        return M;
    }

    public boolean checkMessage() {
        BigInteger s1 = s.getS1();
        BigInteger s2 = s.getS2();
        BigInteger check = s1.multiply(s1).subtract(s2.multiply(s2).multiply(k.multiply(k).modInverse(n))).mod(n);
        return check.equals(M_.mod(n));
    }

    public void calculateM() {
        BigInteger div = s.getS1().add(s.getS2().multiply(k.modInverse(n))).modInverse(n);
        this.M = M_.multiply(div).mod(n);
    }
}
