package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PlayerPaddle {

    int x, y, width = 20, height = 200,speed=6;
    boolean up = false, down = false;
    Rectangle boundbox;

    public PlayerPaddle(int x, int y) {
        this.x = x;
        this.y = y;
        boundbox = new Rectangle(x, y, width, height);
        boundbox.setBounds(x, y, width, height);
    }

    public void render(Graphics g) {

        g.setColor(Color.decode("#55ff00"));
        g.fillRoundRect(x, y, width, height, 10, 10);

    }

    public void tick(Pong pong) {
                boundbox.setBounds(x, y, width, height);
        if (up && y > 0) {
            y-=speed;
        }
        if (down && y + height < pong.getHeight()) {
            y+=speed;
        }
    }
}
