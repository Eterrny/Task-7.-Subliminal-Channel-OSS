import java.math.BigInteger;
import java.security.SecureRandom;

public class Wolter {
    private BigInteger M_;

    public Wolter (BigInteger M_) {
        this.M_ = M_;
    }

    public void badWolter() {
        SecureRandom rnd = new SecureRandom();
        this.M_ = new BigInteger(this.M_.bitLength(), rnd);
    }

    public BigInteger getM_() {
        return M_;
    }
}
