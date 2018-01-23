package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class Ball extends JFrame implements ActionListener {

    int x, y, size = 50, speed = 8, dx, dy;
    Rectangle boundbox;
    Timer timer = new Timer(100, this);
    Random rand = new Random();

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        //dx = speed;
        //dy = speed;
        speed = rand.nextInt(10) + 1;
        if (speed <= 2) {
            speed = 6;
        }
        dx = speed;
        dy = speed;
        boundbox = new Rectangle(x, y, size, size);
        boundbox.setBounds(x, y, size, size);
    }

    public void render(Graphics g) {

        ImageIcon i = new ImageIcon(getClass().getClassLoader().getResource("ball.png"));
        Image img = i.getImage();
        g.drawImage(img, x, y, size, size, this);

        //g.setColor(Color.decode("#7f2aff"));
        //g.fillOval(x, y, size, size);

    }

    public void tick(Pong pong) {
        boundbox.setBounds(x, y, size, size);
        if (x <= 0) {
            music();
            pong.scorep2++;
            timer.start();
            //dx = speed;
            speed = rand.nextInt(10) + 1;
            if (speed <= 2) {
                speed = 6;
            }
            dx = speed;
        } else if (x + size >= pong.getWidth()) {
            music();
            pong.scorep1++;
            timer.start();
            //dx = -speed;
            speed = rand.nextInt(10) + 1;
            if (speed <= 2) {
                speed = 6;
            }
            dx = -speed;
        }
        if (y <= 0) {
            music();
            //dy = speed;
            speed = rand.nextInt(10) + 1;
            if (speed <= 2) {
                speed = 6;
            }
            dy = speed;
        } else if (y + size >= pong.getHeight()) {
            music();
            //dy = -speed;
            speed = rand.nextInt(10) + 1;
            if (speed <= 2) {
                speed = 6;
            }
            dy = -speed;
        }
        x += dx;
        y += dy;
        if (Pong.scorep1 == 7) {
            Pong.music_win1();
            Pong.winner1 = true;
        } else if (Pong.scorep2 == 7) {
            Pong.music_win2();
            Pong.winner2 = true;
        }
        collision(pong);
    }

    private void collision(Pong pong) {
        if (boundbox.intersects(pong.player.boundbox)) {
            music();
            speed = rand.nextInt(10) + 1;
            if (speed <= 2) {
                speed = 6;
            }
            dx = speed;
        } else if (boundbox.intersects(pong.comPaddle.boundbox)) {
            music();
            speed = rand.nextInt(10) + 1;
            if (speed <= 2) {
                speed = 6;
            }
            dx = -speed;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x = Pong.frame.getWidth() / 2;
        y = Pong.frame.getHeight() / 2;
        timer.stop();
    }

    public static void music() {

        try {
            File soundFile = new File("Pong.wav");
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundFile);
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
        }
    }
}
