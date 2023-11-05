import java.math.BigInteger;

public class OSSChannel {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Входные параметры отсутствуют");
            return;
        }
        if (args[0].equals("/help") || args[0].equals("h")) {
            System.out.println("""
                    Программе должны передаваться следующие параметры:
                    \t- большое целое нечетное число n
                    \t- скрытое сообщение M""");
            return;
        }
        if (args.length < 2) {
            System.out.println("Передано некорректное число параметров.");
            return;
        }
        BigInteger n;
        String secretMessage;
        try {
            n = new BigInteger(args[0]);
            if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)){
                throw new IncorrectParametersException("Число n должно быть нечетным.");
            }
            secretMessage = args[1];
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Выход за пределы массива.");
            return;
        } catch (NumberFormatException e) {
            System.out.println("Ошибка при чтении входных параметров.");
            return;
        } catch (IncorrectParametersException e){
            System.out.println(e.getMessage());
            return;
        }
        OSSService service = new OSSService(n, secretMessage);
        service.runProtocol();
    }
}
