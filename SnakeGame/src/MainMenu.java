import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame { // создаем главное меню
    MainMenu () {
        setTitle("Главное меню");
        add (new MainMenuPanel());
        setBackground(Color.CYAN);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        System.out.println("Maim menu start");

    }
}
