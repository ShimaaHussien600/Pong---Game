package pong;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Pong extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    public static PlayerPaddle player;
    public static ComPaddle comPaddle;
    public static Ball ball;
    InputHandeler ih;
    static JFrame frame;
    public static final int WIDTH = 1279;
    public static final int HEIGHT = 690;
    public final Dimension gameSize = new Dimension(WIDTH, HEIGHT);
    public static boolean gameRunning = false;
    public static boolean space ;
    public static boolean winner1 ;
    public static boolean winner2 ;
    BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
    public static int scorep1, scorep2;

    public Pong() {
        space=false;
        winner1=false;
        winner2=false;
        scorep1=0;
        scorep2=0;
        frame = new JFrame("PONG");
        setMinimumSize(gameSize);
        setMaximumSize(gameSize);
        setPreferredSize(gameSize);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        ih = new InputHandeler(this);
        player = new PlayerPaddle(10, 70);
        comPaddle = new ComPaddle(getWidth() - 25, 70);
        ball = new Ball(getWidth() / 2, getHeight() / 2);
    }

    @Override
    public void run() {
        while (gameRunning) {
            tick();
            render();
            try {
                Thread.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public synchronized void start() {
        gameRunning = true;
        new Thread(this).start();
    }

    public static synchronized void stop() {
        gameRunning = false;
        frame.dispose();
    }

    public void tick() {
        if (!space && !winner1 && !winner2) {
            player.tick(this);
            comPaddle.tick(this);
            ball.tick(this);
        }
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        //g.setColor(Color.BLACK);
        //g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        ImageIcon i = new ImageIcon(getClass().getClassLoader().getResource("modified.png"));
        Image img = i.getImage();
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        player.render(g);
        comPaddle.render(g);
        ball.render(g);
        g.setColor(Color.green);
        g.setFont(new Font("Algerian", Font.ITALIC, 40));
        g.drawString("" + scorep1, 25, 40);
        g.setColor(Color.decode("#ff9900"));
        g.drawString("" + scorep2, getWidth() - 55, 40);
        if (space) {
            g.setColor(Color.GREEN);
            g.setFont((new Font("Algerian", Font.BOLD + Font.ITALIC, 50)));
            g.drawString("Pau", WIDTH / 2 - 110, HEIGHT / 2 + 20);
            g.setColor(Color.decode("#D28A0E"));
            g.setFont((new Font("Algerian", Font.BOLD + Font.ITALIC, 50)));
            g.drawString("sed", WIDTH / 2 + 7, HEIGHT / 2 + 20);
        }
        if (winner1) {

            g.setColor(Color.GREEN);
            g.setFont((new Font("Algerian", Font.BOLD + Font.ITALIC, 120)));
            g.drawString("Pla", WIDTH / 2 - 250, HEIGHT / 2);
            g.drawString("Wi", WIDTH / 2 - 160, HEIGHT / 2 + 200);
            g.setColor(Color.decode("#000000"));
            g.setFont((new Font("Algerian", Font.BOLD + Font.ITALIC, 120)));
            g.drawString("1", WIDTH / 2 - 40, HEIGHT / 2 + 95);
            g.setColor(Color.decode("#D28A0E"));
            g.setFont((new Font("Algerian", Font.BOLD + Font.ITALIC, 120)));
            g.drawString("yer", WIDTH / 2 + 5, HEIGHT / 2);
            g.drawString("ns", WIDTH / 2 + 18, HEIGHT / 2 + 200);
            g.setColor(Color.black);
            g.setFont((new Font("Algerian", Font.BOLD + Font.ITALIC, 30)));
            g.drawString("Back To Main Menu Press N", 90, HEIGHT - 10);
            g.drawString("Exit From Game Press ESC", WIDTH-550, HEIGHT - 10);
        }
        if (winner2) {

            g.setColor(Color.GREEN);
            g.setFont((new Font("Algerian", Font.BOLD + Font.ITALIC, 120)));
            g.drawString("Pla", WIDTH / 2 - 250, HEIGHT / 2);
            g.drawString("Wi", WIDTH / 2 - 160, HEIGHT / 2 + 200);
            g.setColor(Color.decode("#000000"));
            g.setFont((new Font("Algerian", Font.BOLD + Font.ITALIC, 120)));
            g.drawString("2", WIDTH / 2 - 40, HEIGHT / 2 + 95);
            g.setColor(Color.decode("#D28A0E"));
            g.setFont((new Font("Algerian", Font.BOLD + Font.ITALIC, 120)));
            g.drawString("yer", WIDTH / 2 + 5, HEIGHT / 2);
            g.drawString("ns", WIDTH / 2 + 18, HEIGHT / 2 + 200);
            g.setColor(Color.black);
            g.setFont((new Font("Algerian", Font.BOLD + Font.ITALIC, 30)));
            g.drawString("Back To Main Menu Press N", 90, HEIGHT - 10);
            g.drawString("Exit From Game Press ESC", WIDTH-550, HEIGHT - 10);

        }

        g.dispose();
        bs.show();
    }

    public static void music_win1() {

        try {
            File soundFile = new File("player1.wav");
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundFile);
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
        }
    }

    public static void music_win2() {

        try {
            File soundFile = new File("player2.wav");
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundFile);
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
        }
    }
}
