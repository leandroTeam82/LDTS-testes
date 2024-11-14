package org.example.view;

import com.googlecode.lanterna.TextColor;
import org.example.gui.TerminalRules;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sprite {

    private final int width;
    private final int height;
    private final BufferedImage image;

    public Sprite(String filepath) throws IOException {
        URL resource = getClass().getClassLoader().getResource(filepath);
        this.image = ImageIO.read(Objects.requireNonNull(resource));
        this.width = image.getWidth();
        this.height = image.getHeight();
    }
    public BufferedImage getImage() {
        return image;
    }

    public void draw(TerminalRules terminalRules, double x, double y, int dx, int dy, int dxM, int dyM) {

        dx = Math.max(0, dx);
        dy = Math.max(0, dy);
        dxM = Math.min(dxM, image.getWidth());
        dyM = Math.min(dyM, image.getHeight());

        for (int i = dx; i < dxM; i++) {
            for (int j = dy; j < dyM; j++) {
                int ARGB = image.getRGB(i, j);
                if (getTransparency(ARGB) == 0) continue;
                terminalRules.drawPixel(x + i - dx, y + j - dy, getRGB(ARGB));
            }
        }
    }


    private int getTransparency(int ARGB) {
        return ARGB >> 24;
    }

    private TextColor getRGB(int ARGB) {
        int red = ARGB >> 16 & 0xFF;
        int green = ARGB >> 8 & 0xFF;
        int blue = ARGB & 0xFF;
        return new TextColor.RGB(red, green, blue);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
