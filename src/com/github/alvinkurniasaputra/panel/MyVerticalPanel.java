package com.github.alvinkurniasaputra.panel;

import javax.swing.*;
import java.awt.*;

public class MyVerticalPanel extends JPanel implements Scrollable{
    public MyVerticalPanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    public MyVerticalPanel(LayoutManager layout) {
        super(layout);
    }

    public MyVerticalPanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    public MyVerticalPanel() {
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return getPreferredSize();
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 0;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 0;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return true;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }
}
