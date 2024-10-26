package MenuSetUp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.*;

import javax.swing.JSlider;
import javax.swing.plaf.basic.BasicSliderUI;

public class SliderUI extends BasicSliderUI {
    public SliderUI(JSlider test) {
        super(test);
    }

    @Override
    protected Dimension getThumbSize() {
        return new Dimension(15, 15);
    }

    @Override
    public void paintThumb(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        AffineTransform trans = g2.getTransform();
        g2.translate(thumbRect.x, thumbRect.y);
        g2.setTransform(trans);
        g2.setPaint(new GradientPaint(trackRect.x, trackRect.y, Color.WHITE, trackRect.width, trackRect.height, Color.GRAY));
        g2.fill(new Ellipse2D.Double(thumbRect.x + 2, thumbRect.y + 2, thumbRect.getWidth() - 3, thumbRect.getHeight() - 3));
        g2.dispose();
    }

    @Override
    public void paintTrack(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(new GradientPaint(0, 0, Color.WHITE, trackRect.width, trackRect.height, Color.GRAY));
        int size = 5;
        int x = 0;
        int y = (trackRect.height - size) / 2;
        g2.fill(new RoundRectangle2D.Double(trackRect.x + x, trackRect.y + y, trackRect.width, size, size, size));
    }
}
