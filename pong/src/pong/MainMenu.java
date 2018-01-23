package pong;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JFrame {

    int screenwidth = 1279, screenheight = 712, buttonwidth = 332, buttonheight = 68;
    //buttonwidth = 141, buttonheight = 146
    JButton play, exit, menu;
    JCheckBox twoplayer;

    public MainMenu() {
        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon("E:\\Faculty\\3) Third year\\2nd Term\\Computer Graphics\\Pong_Project\\pong\\image\\line.png")));
        setLayout(new FlowLayout());
        AddButtons();
        AddActions();
        getContentPane().setLayout(null);
        //menu.setBounds(150, 0, 400, 180);
        //play.setBounds(60, 180, buttonwidth, buttonheight);
        //exit.setBounds(500, 180, buttonwidth, buttonheight);
        //twoplayer.setBounds(290, 180, buttonwidth, buttonheight);
        //menu.setBounds(0, 0, screenwidth, screenheight);
        play.setBounds(477, 278, buttonwidth, buttonheight);
        twoplayer.setBounds(477, 378, buttonwidth, buttonheight);
        exit.setBounds(477, 478, buttonwidth, buttonheight);
        
        //getContentPane().add(menu);
        getContentPane().add(play);
        getContentPane().add(exit);
        getContentPane().add(twoplayer);

        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(screenwidth, screenheight);
        setTitle("PONG MENU");

    }

    private void AddButtons() {

        //ImageIcon iplay = new ImageIcon("C:\\Users\\Jimmy Kudo\\Documents\\NetBeansProjects\\pong\\image\\rsz_play.png");
        //ImageIcon iexit = new ImageIcon("C:\\Users\\Jimmy Kudo\\Documents\\NetBeansProjects\\pong\\image\\rsz_quit.png");
        //ImageIcon itwo = new ImageIcon("C:\\Users\\Jimmy Kudo\\Documents\\NetBeansProjects\\pong\\image\\rsz_2play.png");
        //ImageIcon imenu = new ImageIcon("C:\\Users\\Jimmy Kudo\\Documents\\NetBeansProjects\\pong\\image\\rsz_menu1.png");
        ImageIcon iplay = new ImageIcon("E:\\Faculty\\3) Third year\\2nd Term\\Computer Graphics\\Pong_Project\\pong\\image\\button 1.png");
        ImageIcon iexit = new ImageIcon("E:\\Faculty\\3) Third year\\2nd Term\\Computer Graphics\\Pong_Project\\pong\\image\\button 3.png");
        ImageIcon itwo = new ImageIcon("E:\\Faculty\\3) Third year\\2nd Term\\Computer Graphics\\Pong_Project\\pong\\image\\button 2.png");
        ImageIcon imenu = new ImageIcon("E:\\Faculty\\3) Third year\\2nd Term\\Computer Graphics\\Pong_Project\\pong\\image\\line.png");

        //menu = new JButton(null, imenu);
        //menu.setContentAreaFilled(false);
        //menu.setBorderPainted(false);        
        play = new JButton(null, iplay);
        play.setContentAreaFilled(false);
        play.setBorderPainted(false);
        exit = new JButton(null, iexit);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        twoplayer = new JCheckBox(null, itwo);
        twoplayer.setContentAreaFilled(false);
        twoplayer.setBorderPainted(false);
    }

    private void AddActions() {
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Pong pong = new Pong();
                pong.start();

            }
        });
        twoplayer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Pong pong = new Pong();
                pong.comPaddle.TwoPlayer=true;
                pong.comPaddle.speed=6;
                pong.start();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


    }
}
