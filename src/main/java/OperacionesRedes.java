import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OperacionesRedes {
    private String ipv4 = "";
    private final int ipv4MinimoGlobal = 0;
    private final int ipv4MaximoGlobal = 255;
    private String ipv6 = "";
    private final String ipv6MinimoGlobal = "0000";
    private final String ipv6MaximoGlobal = "FFFF";

    public OperacionesRedes() {
    }

    public String conversionDecimalIPv4(String ipv4) {
        String decimal = "";
        String[] str = ipv4.split("\\.");

        if(isIPv4(str)) {
            List<String> list = Arrays.asList(str);
            decimal = list.stream().map(x -> Integer.toString(toDecimal(x))).collect(Collectors.joining("."));
            this.ipv4 = decimal;
        }

        return decimal;
    }

    /*public String conversionHexadecimalIPv4() throws Exception {
        if(!this.ipv4.isEmpty()) {

        }
    }*/

    public boolean isIPv4(String [] arr) {
        int number = 0;
        int len = arr.length;

        for(int i = 0; i < len; i++) {
            number = toDecimal(arr[i]);
            if(i == 0) {
                if(number < 0 || number > 127)
                    return false;
            }else {
                if(number < 0 || number > 255) return false;
            }
        }

        return true;
    }


    //
    private int toDecimal(String str) {
        int decimal = 0;
        int cont = 0;
        int len = str.length();

        for(int i = len - 1; i >= 0; i--) {
            if(str.charAt(i) == '1') {
                decimal += Math.pow(2, cont);
                cont++;
            }
        }
        return decimal;
    }


    //@TODO transformar el número de binario a hexadecimal antes de transformar a decimal
    public String conversionDecimalIPv6(String ipv6) {
        String[] str = ipv6.split(":");
        String decimal = "";
        if(isIPv6(str)) {

            decimal = Arrays.stream(str).map(x -> Integer.toString(toDecimal(x))).collect(Collectors.joining(":"));
        }
        return decimal;
    }

    public boolean isIPv6(String[] arr) {
        int number = 0;
        int len = arr.length;
        int minimoGlobal = hexToDecimal(ipv6MinimoGlobal);
        int maximoGlobal = hexToDecimal(ipv6MaximoGlobal);

        for(String str : arr) {
            number = toDecimal(str);
            if(number <= minimoGlobal || number >= maximoGlobal) return false;
        }

        return true;
    }

    /*private String[] binToHex(String[] str) {
        List<String> list = new ArrayList<>();
        int dec = 0;
        int len = 0;
        for (String a : str) {
            dec = 0;
            len = a.length();
            for(int i = len - 1; i >= 0; i--) {
                dec += Math.pow(2, i);
            }
            list.add(Integer.toString(dec, 16));
        }
    }*/

    private int hexToDecimal(String str) {
        int len = str.length();
        int dec = 0;


        for(int i = 0; i < len; i++) {
            char c = str.charAt(i);
            int digit = Character.digit(c, 16);
            dec = dec * 16 + digit;
        }

        return dec;
    }
}