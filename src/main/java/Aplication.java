import DocumentFilter.FilterFormat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private OperacionesRedes redes = new OperacionesRedes();
    private OperacionesPermutacionCombinacion permutacionCombinacion = new OperacionesPermutacionCombinacion();
    private List<String> numbers;
    private List<JTextField> textFieldsIPv4;
    private List<JTextField> textFieldsIPv6;


    public Aplication() {
        convertirIPv4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numbers = new ArrayList<>();
                for(JTextField j : textFieldsIPv4) {
                    if(!j.getText().isEmpty())
                        numbers.add(j.getText());
                    else JOptionPane.showMessageDialog(null, "Llene todos los cuadros de texto, por favor.");
                }

                String ipv4Text = numbers.stream().map(x -> x).collect(Collectors.joining("."));
                String decimalText = redes.conversionDecimalIPv4(ipv4Text);
                textAreaIPv4.setText(decimalText);

                for (JTextField j: textFieldsIPv4) {
                    j.setText("");
                }

                numbers.clear();
            }
        });

        //TODO cambiar los datos de int a BIGINT
        convertirIPv6Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numbers = new ArrayList<>();
                for(JTextField j : textFieldsIPv6) {
                    if(!j.getText().isEmpty())
                        numbers.add(j.getText());
                    else JOptionPane.showMessageDialog(null, "Llene todos los cuadros de texto, por favor.");
                }

                String ipv4Text = numbers.stream().map(x -> x).collect(Collectors.joining(":"));
                String decimalText = redes.conversionDecimalIPv4(ipv4Text);
                textAreaIPv6.setText(decimalText);

                for (JTextField j: textFieldsIPv6) {
                    j.setText("");
                }

                numbers.clear();
            }
        });
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
