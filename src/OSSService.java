import java.math.BigInteger;
import java.util.HashMap;

class IncorrectParametersException extends Exception {
    public IncorrectParametersException(String s) {
        super(s);
    }
}

class BobSignatureException extends Exception {
    public BobSignatureException(String s) {
        super(s);
    }
}

public class OSSService {
    Alice alice;
    Bob bob;
    Wolter wolter;
    private HashMap<BigInteger, String> intToStringMap = new HashMap<>();
    private boolean exceptionHandled = false;

    public OSSService(BigInteger n, String secretMessage) {
        this.alice = new Alice(n);
        this.intToStringMap.put(alice.getM(), secretMessage);
        bob = new Bob(alice.getN(), alice.getK(), alice.getH());
        wolter = new Wolter(alice.getM_());
        System.out.println(this);
    }

    @Override
    public String toString() {
        return String.format("""
                        Алиса хочет отправить скрытое сообщение М = %d в безобидном сообщении M' = %d
                        Параметры:
                            общедоступный модуль n = %d
                            закрытый ключ k = %d
                            открытый ключ h = %d"""
                , alice.getM(), alice.getM_(), alice.getN(), alice.getK(), alice.getH());
    }

    public void runProtocol() {
        try {
            if (alice.checkParameters()) {
                System.out.println("Алиса проверила, что пары M' и n, а также M и n являются взаимно простыми числами.");
                this.signMessage();
            } else {
                throw new IncorrectParametersException("ОШИБКА: Пары чисел M' и n, а также M и n не являются взаимно простыми числами");
            }
        } catch (IncorrectParametersException e) {
            System.out.println(e.getMessage());
            this.exceptionHandled = true;
            return;
        }
    }

    private void signMessage() {
        alice.signMessage();
        System.out.printf("""
                Алиса подписала сообщение M':
                    S1 = %d
                    S2 = %d\n""", alice.getS().getS1(), alice.getS().getS2());
        this.sendMessageWithSignature();
    }

    private void sendMessageWithSignature() {
        bob.setM_S(wolter.getM_(), alice.getS());
        if (!wolter.getM_().equals(alice.getM_())) {
            System.out.println("Уолтер решил подсунуть Бобу поддельное сообщение M' = " + wolter.getM_());
        }
        System.out.printf("""
                Уолтер передал Бобу от Алисы следующее:
                    M' = %d
                    S = %s\n""", wolter.getM_(), alice.getS());
        try {
            if (bob.checkMessage()) {
                bob.calculateM();
                System.out.println("Боб проверил подлинность открытого сообщения и вычислил скрытое сообщение M = " + bob.getM() + " = \"" + intToStringMap.get(bob.getM()) + "\"");
            } else{
                throw new BobSignatureException("Боб выяснил, что сообщение не является подлинным.");

            }
        } catch (BobSignatureException e) {
            System.out.println(e.getMessage());
            this.exceptionHandled = true;
            return;
        }
    }

    public void makeWolterBad() {
        wolter.badWolter();
    }

    public Alice getAlice() {
        return alice;
    }

    public boolean getException() {
        return exceptionHandled;
    }
}
