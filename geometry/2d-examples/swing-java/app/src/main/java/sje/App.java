/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package sje;

import javax.swing.*;
import java.awt.*;

/**
 * example inspired by Listing 10.3 from Core Java 11th (page 584)
 */
public class App {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new JFrame();
            frame.setTitle("Draw test");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            DrawComponent comp = new DrawComponent();
            comp.setPreferredSize(new Dimension(100, 50));
            frame.add(comp);

            frame.pack();
            frame.setVisible(true);
        });
    }
}
