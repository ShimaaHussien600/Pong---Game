package pong;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.security.Key;
import javax.swing.JFrame;

public class InputHandeler extends JFrame implements KeyListener {

    public InputHandeler(Pong pong) {
        pong.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            Pong.player.up = true;
        }
        if (key == KeyEvent.VK_S) {
            Pong.player.down = true;
        }
        if (key == KeyEvent.VK_UP) {
            Pong.comPaddle.up = true;
        }
        if (key == KeyEvent.VK_DOWN) {
            Pong.comPaddle.down = true;
        }
        if (key == KeyEvent.VK_N) {
            new MainMenu();
            Pong.stop();
            
        }
        if (key == KeyEvent.VK_SPACE) {
            if (Pong.space == false) {
                Pong.space = true;
            } else if (Pong.space == true) {
                Pong.space = false;
            }

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            Pong.player.up = false;
        }
        if (key == KeyEvent.VK_S) {
            Pong.player.down = false;
        }
        if (key == KeyEvent.VK_UP) {
            Pong.comPaddle.up = false;
        }
        if (key == KeyEvent.VK_DOWN) {
            Pong.comPaddle.down = false;
        }
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }


    }
}
