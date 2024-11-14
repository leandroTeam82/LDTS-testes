package org.example.gui;

import com.googlecode.lanterna.screen.Screen;

import java.awt.*;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URISyntaxException;

public interface TerminalCreator {
    Screen createScreen(ResizableTerminal.Resolution resolution, String title)
            throws IOException, URISyntaxException, FontFormatException;

    int getWidth();
    int getHeight();
}
