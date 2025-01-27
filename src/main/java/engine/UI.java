package engine;

import engine.loop.FrameBasedGameLoop;
import object.Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import static engine.GameEngine.*;

public class UI{
    //
    BufferedImage keyImage ;
    Font font_arial_40, font_arial_80B;
    //
    int messageCounter = 0;
    public String message = "";
    public boolean messageOn = false;
    //
    public boolean gameFinished = false;
    //
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");


    public UI() {
        font_arial_40 = new Font("Arial", Font.PLAIN, 40);
        font_arial_80B = new Font("Arial", Font.BOLD, 80);
        Key key = new Key();
        keyImage = key.image;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;

    }

    public void draw(Graphics2D g2){

        if (gameFinished){
            FrameBasedGameLoop.shouldRun = false;
            g2.setFont(font_arial_40);
            g2.setColor(Color.white);

            String text = "You found the treasure!";
            int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            int x = screenWidth / 2 - textLength / 2;
            int y = screenHeight / 2 - tileSize * 3 ;
            g2.drawString(text, x, y);

            text = "Your time is: " + dFormat.format(playTime) + "!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = screenWidth / 2 - textLength / 2;
            y = screenHeight / 2 + tileSize * 4 ;
            g2.drawString(text, x, y);

            g2.setFont(font_arial_80B);
            g2.setColor(Color.yellow);
            text = "Congratulations!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = screenWidth / 2 - textLength / 2;
            y = screenHeight / 2 + tileSize * 2 ;
            g2.drawString(text, x, y);

            //gp.gameLoop.shouldRun = false;
        }

        else {
            g2.setFont(font_arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, tileSize / 2, tileSize / 2, tileSize, tileSize, null);
            g2.drawString("x " + player.playerKeys, 74, 65);

            // ΤΙΜΕ
            playTime += (double) 1 / 60;
            g2.drawString("Time: " + dFormat.format(playTime), tileSize * 11, 65);

            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, tileSize / 2, tileSize * 5);

                messageCounter++;

                if (messageCounter == 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
