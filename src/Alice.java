import java.math.BigInteger;
import java.security.SecureRandom;

public class Alice {
    private BigInteger n, k, h, M, M_;
    private Signature s;

    public Alice(BigInteger n) {
        this.n = n;
        this.M = this.findCoprime(this.n);
        this.k = this.findCoprime(this.n);
        this.h = this.k.multiply(this.k).negate().mod(n);
        this.M_ = this.findCoprime(this.n);
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getK() {
        return k;
    }

    public BigInteger getH() {
        return h;
    }

    public BigInteger getM() {
        return M;
    }

    public BigInteger getM_() {
        return M_;
    }

    public Signature getS() {
        return s;
    }

    public void setM(BigInteger m) {
        M = m;
    }

    public void setM_(BigInteger m_) {
        M_ = m_;
    }

    public BigInteger findCoprime(BigInteger n) {
        SecureRandom rnd = new SecureRandom();
        BigInteger coprime = new BigInteger(n.bitLength(), rnd).mod(n);
        while(!coprime.gcd(n).equals(BigInteger.ONE)) {
            coprime = coprime.add(BigInteger.ONE).mod(n);
        }
        return coprime;
    }

    public boolean checkParameters() {
        return M_.gcd(n).equals(BigInteger.ONE) && M.gcd(n).equals(BigInteger.ONE);
    }

    public void signMessage() {
        BigInteger invTwo = BigInteger.TWO.modInverse(n);
        BigInteger invM = M.modInverse(n);
        this.s = new Signature(
                invTwo.multiply(M_.multiply(invM).add(M)).mod(n),
                k.multiply(invTwo).multiply(M_.multiply(invM).subtract(M)).mod(n));
    }
}
