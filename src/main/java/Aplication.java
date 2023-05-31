import DocumentFilter.FilterFormat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Aplication {
    private JPanel Principal;
    private JTabbedPane tabbedPane1;
    private JTextField textFieldFirstv4;
    private JTextField textFieldSecondv4;
    private JTextField textFieldThirdv4;
    private JTextArea textAreaIPv4;
    private JButton convertirIPv4Button;
    private JTextField textFieldFirstv6;
    private JTextField textFieldFourthv6;
    private JTextField textFieldThirdv6;
    private JTextField textFieldSecondv6;
    private JTextField textFieldFifthv6;
    private JTextField textFieldEigthv6;
    private JTextField textFieldSixthv6;
    private JTextField textFieldSeventhv6;
    private JButton convertirIPv6Button;
    private JTextArea textAreaIPv6;
    private JTextField textFieldFourthv4;
    private JComboBox comboBoxOpcionesPermutacion;
    private JTextField textFieldPermutacion;
    private JButton permutarButton;
    private JTextArea textAreaPermutacion;
    private JComboBox comboBoxOpcionesCombinacion;
    private JTextField textFieldCombinacionTotal;
    private JTextField textFieldCombinacionMuestra;
    private JButton combinarButton;
    private JTextArea textAreaCombinacion;
    private final OperacionesRedes redes = new OperacionesRedes();
    private final OperacionesPermutacionCombinacion permutacionCombinacion = new OperacionesPermutacionCombinacion();
    private List<String> numbers;
    private List<JTextField> textFieldsIPv4;
    private List<JTextField> textFieldsIPv6;


    public Aplication() {

        //Función que aplica la conversión de IPv4 de binario a decimal.
        convertirIPv4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numbers = new ArrayList<>();
                if(!isEmpty(textFieldsIPv4)) {
                    for(JTextField j : textFieldsIPv4) {
                        numbers.add(j.getText());
                    }
                } else JOptionPane.showMessageDialog(null, "Llene todos los cuadros de texto, por favor.");

                String ipv4Text = String.join(".", numbers);
                String decimalText = redes.conversionDecimalIPv4(ipv4Text);
                textAreaIPv4.setText(decimalText);

                for (JTextField j: textFieldsIPv4) {
                    j.setText("");
                }

                numbers.clear();
            }
        });

        //Aplica la conversión a decimal en redes IPv6
        convertirIPv6Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numbers = new ArrayList<>();
                if(!isEmpty(textFieldsIPv6)) {
                    for (JTextField j : textFieldsIPv6) {
                        numbers.add(j.getText());
                    }
                } else JOptionPane.showMessageDialog(null, "Llene todos los cuadros de texto, por favor.");

                String ipv6Text = String.join(":", numbers);
                String decimalText = redes.conversionDecimalIPv6(ipv6Text);
                textAreaIPv6.setText(decimalText);

                for (JTextField j: textFieldsIPv6) {
                    j.setText("");
                }

                numbers.clear();
            }
        });

        //Aplica la función de permutar con y sin repetición
        permutarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean isRepeticion = Objects.requireNonNull(comboBoxOpcionesPermutacion.getSelectedItem()).toString().equals("Con repetición");

                if(!textFieldPermutacion.getText().isEmpty()){
                    int totalDatos = Integer.parseInt(textFieldPermutacion.getText());

                    if(!isRepeticion) {
                        textAreaPermutacion.setText(permutacionCombinacion.permutacionSinRepeticion(totalDatos).toString());
                    } else {
                        //m el número de variables que se repiten en la permutación, para que los números sean válidos su suma debe ser
                        //menor igual al total de datos.
                        int m = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de variables que se repiten:"));
                        int[] numbers = new int[m];
                        for (int i = 0; i < m; i++) {
                            numbers[i] = Integer.parseInt(JOptionPane.showInputDialog("Ingrese un número:"));
                        }
                        //Comprueba si no existe algún número ingresado negativo o si su suma es menor igual al total de datos.
                        if(Arrays.stream(numbers).sum() <= totalDatos && Arrays.stream(numbers).filter(x -> x <= 0).findAny().isEmpty())
                            textAreaPermutacion.setText(permutacionCombinacion.permutacionConRepeticion(totalDatos, numbers).toString());
                        else
                            JOptionPane.showMessageDialog(null, "Los números ingresados para la repetición deben ser positivos y" +
                                    " menores que el total de datos.");
                    }
                } else JOptionPane.showMessageDialog(null, "Llene todos los campos de texto, por favor");

                textFieldPermutacion.setText("");
            }
        });

        //Boton que aplica las funciones de Combinación
        combinarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isRepeticion = Objects.requireNonNull(comboBoxOpcionesCombinacion.getSelectedItem()).toString().equals("Con repetición");

                if(textFieldCombinacionTotal.getText().isEmpty() || textFieldCombinacionMuestra.getText().isEmpty()) {
                    int totalElementos = Integer.parseInt(textFieldCombinacionTotal.getText());
                    int numeroDeElementosRepetidos = Integer.parseInt(textFieldCombinacionMuestra.getText());

                    //Si el número de elementos repetidos es mayor al total de elementos, los datos son incorrectos
                    if(!isRepeticion && numeroDeElementosRepetidos <= totalElementos) {
                        textAreaCombinacion.setText(permutacionCombinacion.combinacionSinRepeticion(totalElementos, numeroDeElementosRepetidos).toString());
                    } else if(isRepeticion && numeroDeElementosRepetidos <= totalElementos) {
                        textAreaCombinacion.setText(permutacionCombinacion.combinacionConRepeticion(totalElementos, numeroDeElementosRepetidos).toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Datos no válidos el total de elementos debe ser mayor al" +
                                " número de elementos seleccionados.");
                    }
                } else JOptionPane.showMessageDialog(null, "Llene todos los cuadros de texto, por favor");

                textFieldCombinacionMuestra.setText("");
                textFieldCombinacionTotal.setText("");
            }
        });
    }

    /**
     * @param fields
     *          Acepta como parámetro una lista de JTextfield, si algún elemento de la lista contiene texto vacío entonces
     *          retorna true, sino false.
     * */
    public boolean isEmpty(List<JTextField> fields) {
        boolean isEmpty = false;

        for (JTextField field: fields) {
            if(field.getText().isEmpty()) return true;
        }

        return  false;
    }

    private void createUIComponents() {
        String[] regex = {"[01]*", "[0-9]*"};
        //textFields de IPv4
        textFieldFirstv4 = new FilterFormat().filterFormatField(4, regex[0]);
        textFieldSecondv4 = new FilterFormat().filterFormatField(4, regex[0]);
        textFieldThirdv4 = new FilterFormat().filterFormatField(4, regex[0]);
        textFieldFourthv4 = new FilterFormat().filterFormatField(4, regex[0]);

        textFieldsIPv4 = Arrays.asList(textFieldFirstv4, textFieldSecondv4, textFieldThirdv4, textFieldFourthv4);

        //textFields de IPv6

        textFieldFirstv6 = new FilterFormat().filterFormatField(6, regex[0]);
        textFieldSecondv6= new FilterFormat().filterFormatField(6, regex[0]);
        textFieldThirdv6 = new FilterFormat().filterFormatField(6, regex[0]);
        textFieldFourthv6 = new FilterFormat().filterFormatField(6, regex[0]);
        textFieldFifthv6 = new FilterFormat().filterFormatField(6, regex[0]);
        textFieldSixthv6 = new FilterFormat().filterFormatField(6, regex[0]);
        textFieldSeventhv6 = new FilterFormat().filterFormatField(6, regex[0]);
        textFieldEigthv6 = new FilterFormat().filterFormatField(6, regex[0]);

        textFieldsIPv6 = Arrays.asList(textFieldFirstv6, textFieldSecondv6, textFieldThirdv6,
                textFieldFourthv6, textFieldFifthv6, textFieldSixthv6, textFieldSeventhv6, textFieldEigthv6);

        //textField de Permutaciones
        textFieldPermutacion = new FilterFormat().filterFormatField(0, regex[1]);

        //textField de Combinación
        textFieldCombinacionMuestra = new FilterFormat().filterFormatField(0, regex[1]);
        textFieldCombinacionTotal = new FilterFormat().filterFormatField(0, regex[1]);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Aplication");
        frame.setContentPane(new Aplication().Principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
