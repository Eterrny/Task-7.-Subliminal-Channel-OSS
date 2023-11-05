import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class OSSServiceTest {

    @org.junit.jupiter.api.Test
    void badParameters() {
        OSSService service = new OSSService(new BigInteger("1024"), "Test");
        service.getAlice().setM(new BigInteger("256"));
        service.getAlice().setM_(new BigInteger("1024"));
        service.runProtocol();
        assertTrue(service.getException());
    }

    @org.junit.jupiter.api.Test
    void makeWolterBad() {
        OSSService service = new OSSService(new BigInteger("1023"), "Test");
        service.makeWolterBad();
        service.runProtocol();
        assertTrue(service.getException());
    }

    @org.junit.jupiter.api.Test
    void checkCoprime() {
        Alice alice = new Alice(new BigInteger("1023"));
        BigInteger n = alice.getN();
        BigInteger a = new BigInteger("100");
        assertTrue(alice.getM_().gcd(n).equals(BigInteger.ONE)
                && alice.getM().gcd(n).equals(BigInteger.ONE)
                && alice.getK().gcd(n).equals(BigInteger.ONE)
                && a.gcd(alice.findCoprime(a)).equals(BigInteger.ONE));
    }
}