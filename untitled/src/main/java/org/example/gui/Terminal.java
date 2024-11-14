package org.example.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static java.awt.event.KeyEvent.*;
import static java.awt.event.KeyEvent.VK_X;

public class Terminal implements TerminalRules{

    private final TerminalCreator screenCreator;
    private final String title;
    private Screen screen;
    private boolean keySpam;
    private ResizableTerminal.Resolution resolution;

    public Terminal(TerminalCreator screenCreator, String title) throws IOException, URISyntaxException, FontFormatException {
        this.screenCreator = screenCreator;
        this.title = title;
        setResolution(null);
    }

    private Screen createScreen(ResizableTerminal.Resolution resolution) throws IOException, URISyntaxException, FontFormatException {
        Screen screen = screenCreator.createScreen(resolution, title);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    @Override
    public int getWidth() {
        return screenCreator.getWidth();
    }

    @Override
    public int getHeight() {
        return screenCreator.getHeight();
    }

    public ResizableTerminal.Resolution getResolution() {
        return resolution;
    }


    public void setResolution(ResizableTerminal.Resolution resolution) throws IOException, URISyntaxException, FontFormatException {
        if (screen != null)
            screen.close();
        this.resolution = resolution;
        this.screen = createScreen(resolution);
    }

    @Override
    public void drawPixel(double x, double y, TextColor color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(color);
        tg.setCharacter((int) x, (int) y, ' ');
    }

    @Override
    public void drawRectangle(double x, double y, int width, int height, TextColor color) {
        if (width > 0 && height > 0) {
            TextGraphics tg = screen.newTextGraphics();
            tg.setBackgroundColor(color);
            tg.fillRectangle(new TerminalPosition((int) x, (int) y), new TerminalSize(width, height), ' ');
        }
    }


    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }
}
