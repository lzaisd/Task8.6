package ru.vsu.cs.sidorovaei.frame;

import java.util.Locale;

public class MainJTable {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        java.awt.EventQueue.invokeLater(() -> new FrameMain().setVisible(true));
    }
}