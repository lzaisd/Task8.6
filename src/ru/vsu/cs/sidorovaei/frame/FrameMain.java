package ru.vsu.cs.sidorovaei.frame;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import ru.vsu.cs.sidorovaei.logics.Logic;
import ru.vsu.cs.sidorovaei.util.ArrayUtils;
import ru.vsu.cs.sidorovaei.util.JTableUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class FrameMain extends JFrame {
    private JPanel panelMain;
    private JTable tableInput;
    private JButton buttonLoadInputFromFile;
    private JButton buttonRandomInput;
    private JButton buttonSaveInputIntoFile;
    private JButton buttonDeleteEqualRows;
    private JTable tableOutput;
    private JButton buttonSaveOutputIntoFile;

    private final JFileChooser fileChooserOpen;
    private final JFileChooser fileChooserSave;

    public FrameMain() {
        this.setTitle("FrameMain");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(tableInput, 45, true, true, true, true);
        JTableUtils.initJTableForArray(tableOutput, 45, true, true, true, true);
        tableInput.setRowHeight(25);
        tableOutput.setRowHeight(25);
        JTableUtils.writeArrayToJTable(tableInput, new int[][]{
                {1, 3, 5, 8, 8},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {9, 6, 3, 3, 8},
                {9, 6, 3, 3, 8}
        });

        this.pack();

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        buttonLoadInputFromFile.addActionListener(actionEvent -> {
            try {
                if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    int[][] arr = ArrayUtils.readIntArray2FromFile(fileChooserOpen.getSelectedFile().getPath());
                    if (Logic.checkIfArrIsNull(arr) || Logic.checkIfArrIsEmpty(arr) || !Logic.checkIfArrIsRectangle(arr)) {
                        System.out.println("Массив пустой или не прямоугольный");
                        System.err.println(1);
                    }
                    JTableUtils.writeArrayToJTable(tableInput, arr);
                }
            } catch (Exception e) {
                ru.vsu.cs.util.SwingUtils.showErrorMessageBox(e);
            }
        });
        buttonRandomInput.addActionListener(actionEvent -> {
            try {
                int[][] matrix = ArrayUtils.createRandomIntMatrix(
                        tableInput.getRowCount(), tableInput.getColumnCount(), 100);
                JTableUtils.writeArrayToJTable(tableInput, matrix);
            } catch (Exception e) {
                ru.vsu.cs.util.SwingUtils.showErrorMessageBox(e);
            }
        });
        buttonSaveInputIntoFile.addActionListener(actionEvent -> {
            try {
                if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    int[][] matrix = JTableUtils.readIntMatrixFromJTable(tableInput);
                    String file = fileChooserSave.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    ArrayUtils.writeArrayToFile(file, matrix);
                }
            } catch (Exception e) {
                ru.vsu.cs.util.SwingUtils.showErrorMessageBox(e);
            }
        });
        buttonSaveOutputIntoFile.addActionListener(actionEvent -> {
            try {
                if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    int[][] matrix = JTableUtils.readIntMatrixFromJTable(tableOutput);
                    String file = fileChooserSave.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    ArrayUtils.writeArrayToFile(file, matrix);
                }
            } catch (Exception e) {
                ru.vsu.cs.util.SwingUtils.showErrorMessageBox(e);
            }
        });
        buttonDeleteEqualRows.addActionListener(e -> {
            try {
                int[][] arr = JTableUtils.readIntMatrixFromJTable(tableInput);
                JTableUtils.writeArrayToJTable(tableInput, arr);
                if (Logic.checkIfArrIsNull(arr) || Logic.checkIfArrIsEmpty(arr) || !Logic.checkIfArrIsRectangle(arr)) {
                    System.out.println("Массив пустой или не прямоугольный");
                    System.err.println(1);
                }
                int[][] arrResult = Logic.deleteEqualRows(arr);
                JTableUtils.writeArrayToJTable(tableOutput, arrResult);
            } catch (Exception ex) {
                ru.vsu.cs.util.SwingUtils.showErrorMessageBox(ex);
            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panelMain = new JPanel();
        panelMain.setLayout(new GridLayoutManager(5, 1, new Insets(10, 10, 10, 10), 10, 10));
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setVerticalScrollBarPolicy(20);
        panelMain.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 200), null, 0, false));
        tableInput = new JTable();
        scrollPane1.setViewportView(tableInput);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonLoadInputFromFile = new JButton();
        buttonLoadInputFromFile.setText("Загрузить из файла");
        panel1.add(buttonLoadInputFromFile, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonRandomInput = new JButton();
        buttonRandomInput.setText("Заполнить случайными числами");
        panel1.add(buttonRandomInput, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonSaveInputIntoFile = new JButton();
        buttonSaveInputIntoFile.setText("Сохранить введённое в файл");
        panel1.add(buttonSaveInputIntoFile, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panel2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonDeleteEqualRows = new JButton();
        buttonDeleteEqualRows.setText("Заменить одинаковые строки одной");
        panel2.add(buttonDeleteEqualRows, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel2.add(spacer2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JScrollPane scrollPane2 = new JScrollPane();
        panelMain.add(scrollPane2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 200), null, 0, false));
        tableOutput = new JTable();
        scrollPane2.setViewportView(tableOutput);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panel3, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonSaveOutputIntoFile = new JButton();
        buttonSaveOutputIntoFile.setText("Сохранить в файл");
        panel3.add(buttonSaveOutputIntoFile, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel3.add(spacer3, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

}