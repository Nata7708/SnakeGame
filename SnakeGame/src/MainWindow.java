import javax.swing.*;

public class MainWindow extends JFrame { //JFrame создаем окно игрового поля

    public MainWindow () { //конструктор с параметрами окна
        setTitle ("ЗМЕЙКА"); //название
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //выход из окна крестиком, завершение работы программы
        setSize(370, 380);
        setLocationRelativeTo(null);
        add (new GameFiled()); // ссылка на файл с игрой
        setVisible(true);
            }


}
