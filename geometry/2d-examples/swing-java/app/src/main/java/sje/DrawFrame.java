package sje;

import javax.swing.*;
import java.awt.*;

public class DrawFrame extends JFrame {
    public DrawFrame() throws HeadlessException {
        DrawComponent comp = new DrawComponent();
        comp.setPreferredSize(new Dimension(100, 50));
        add(comp);
        pack();
    }
}
