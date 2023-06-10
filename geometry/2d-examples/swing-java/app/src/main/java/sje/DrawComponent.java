package sje;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class DrawComponent extends JComponent {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        var g2 = (Graphics2D) g;
        Rectangle bounds = getBounds();
        float w = bounds.width;
        float h = bounds.height;
        g2.draw(new Ellipse2D.Float(0.1f * w, 0.1f * h, 0.8f * w, 0.8f * h));
        g2.draw(new Rectangle2D.Float(0.3f * w, 0.3f * h, 0.4f * w, 0.4f * h));
        g2.draw(new Ellipse2D.Float(0.3f * w, 0.3f * h, 0.4f * w, 0.4f * h));
        g2.draw(new Line2D.Float(0.3f * w, 0.3f * h, 0.7f * w, 0.7f * h));
    }
}
