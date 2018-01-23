package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ComPaddle {

    int x, y, width = 20, height = 200, speed = 4;
    boolean up = false, down = false;
    Rectangle boundbox;
    boolean TwoPlayer = false;

    public ComPaddle(int x, int y) {
        this.x = x;
        this.y = y;
        boundbox = new Rectangle(x, y, width, height);
        boundbox.setBounds(x, y, width, height);
    }

    public void render(Graphics g) {

        g.setColor(Color.decode("#ff9900"));
        g.fillRoundRect(x, y, width, height,10,10);

    }

    public void tick(Pong pong) {
        boundbox.setBounds(x, y, width, height);
        if (!TwoPlayer) {
            if (y > pong.ball.y && y >= 0) {
                y -= speed;
            } else if (y < pong.ball.y && y + height <= pong.getHeight()) {
                y += speed;
            }
        } else {
            if (up && y > 0) {
            y-=speed;
        }
        if (down && y + height < pong.getHeight()) {
            y+=speed;
        }
            
        }
    }
}
