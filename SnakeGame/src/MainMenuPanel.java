import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel{
    private JButton newGame = new JButton("Новая игра");
    private JButton scores = new JButton("Рекорды");
    private JButton settings = new JButton("Настройки");
    private JButton cookie = new JButton("Подарки");
    private JButton exit = new JButton("Выход");

    public MainMenuPanel(){

        setLayout(new GridLayout(5, 1, 10, 10));

        setSize(150, 300);
        setFocusable(true);
        setVisible(true);
        add(newGame);
        add(scores);
        add(settings);
        add(cookie);
        add(exit);

        ActionListener startGameListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow newGameWindow = new MainWindow();
            }
        };
        newGame.addActionListener(startGameListener);

        ActionListener scoresListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Таблица чемпионов");
            }
        };
        scores.addActionListener(scoresListener);

        ActionListener settingsListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Настройки");
            }
        };
        settings.addActionListener(settingsListener);

        ActionListener cookieListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Кариночка - молодец! =3 <3");
            }
        };
        cookie.addActionListener(cookieListener);

        ActionListener exitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Завершение работы");
                System.exit(0);
            }
        };
        exit.addActionListener(exitListener);
    }
}
