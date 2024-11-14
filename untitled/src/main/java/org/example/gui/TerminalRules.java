package org.example.gui;

import com.googlecode.lanterna.TextColor;

import java.io.IOException;

public interface TerminalRules {

    int getWidth();
    int getHeight();
    void drawPixel(double x, double y, TextColor color);
    void drawRectangle(double x, double y, int width, int height, TextColor color);
    void clear();
    void refresh() throws IOException;
    void close() throws IOException;

}
