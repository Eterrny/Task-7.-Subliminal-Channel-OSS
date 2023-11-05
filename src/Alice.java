import java.math.BigInteger;
import java.security.SecureRandom;

public class Alice {
    private BigInteger n, k, h, M, M_;

    public Alice(BigInteger M) {
        this.M = M;
        this.n = this.findCoprime(this.M);
        this.k = this.findCoprime(this.n);
        this.h = this.k.multiply(this.k).negate().mod(n);
        this.M_ = this.findCoprime(n);
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getK() {
        return k;
    }

    public void setM(BigInteger m) {
        M = m;
    }

    public void setM_(BigInteger m_) {
        M_ = m_;
    }

    private BigInteger findCoprime(BigInteger n) {
        SecureRandom rnd = new SecureRandom();
        BigInteger coprime = new BigInteger(n.bitLength(), rnd);
        while(!coprime.gcd(n).equals(BigInteger.ONE)) {
            coprime = coprime.add(BigInteger.ONE);
        }
        return coprime;
    }
}
