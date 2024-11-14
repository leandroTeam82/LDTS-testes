package org.example;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import org.example.gui.ResizableTerminal;
import org.example.gui.Terminal;
import org.example.gui.TerminalCreatorC;
import org.example.view.Sprite;
import com.googlecode.lanterna.TerminalSize;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) {

        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        TerminalSize terminalSize = new TerminalSize(400, 300);
        Rectangle defaultBounds = new Rectangle(1280, 720);

        TerminalCreatorC terminalCreatorC = new TerminalCreatorC(terminalFactory, terminalSize, defaultBounds);
        Terminal terminal = null;

        try {

            terminal = new Terminal(terminalCreatorC, "My Lanterna Game");

            ResizableTerminal.Resolution resolution = ResizableTerminal.Resolution.WXGA;
            terminal.setResolution(resolution);

            Sprite sprite = new Sprite("sprites/sprite1.png");

            int x = 1;

            while (true) {
                terminal.clear();


                sprite.draw(terminal, 5, 5, (16 * x) - 16, 0, 16 * x, 16);

                terminal.refresh();
                Thread.sleep(200);


                x++;
                if (x > 2) {
                    x = 1;
                }
            }
        } catch (IOException | URISyntaxException | FontFormatException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (terminal != null) {
                try {
                    terminal.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
