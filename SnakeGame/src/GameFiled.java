import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameFiled extends JPanel implements ActionListener {  //1. файл с игрой //10. добавляем инерфейс, имплементируем его
    //2. параметры игры
    private final int SIZE = 320; //размер игрового поля
    private final int DOT_SIZE = 16; //размер в пикселях яблока и одной ячейки змейки
    private final int ALL_DOTS = 400; //сколько яблок помещается на поле
    private Image pic4;
    private Image apple2; //
    private int appleX; //позиция яблока
    private int appleY;
    private int[] x = new int[ALL_DOTS]; // массив для хранения всех положений змейки
    private int[] y = new int[ALL_DOTS];
    private int dots; // размер змейки в данный момент времени
    private Timer timer; //таймер
    private boolean left = false;// напрвление движения змейки
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;

    public GameFiled() { //3. создаем конструктор
        setBackground(Color.WHITE); //цвет игрового поля
        loadImages(); // 5. вызов метода загрузки картинок
        initGame(); // 6,1 вызов метода запуска игры
        addKeyListener (new FieldKeyListener()); // 17.добавляем обработчик событий
        setFocusable(true); // 18. соединение клавиш с игровым полем
    }

    public void initGame() { // 6. метод инициализирующий начало игры
        dots = 3; // 7. начальное количество точек
        for (int i = 0; i < dots; i++) {
            x[i] = 48 - i * DOT_SIZE; // цикл, начальная ячейка 48, потому что 48 кратно  16 (наш размер ячейки)
            y[i] = 48;
        }

        timer = new Timer(250, this); // 8. таймер, отвечает за скорость в этом методе, и сразу его запускаем
        timer.start();
        createApple(); //9. метод для создания яблока
    }

    public void createApple() { //9. создание яблока
        appleX = new Random().nextInt(20) * DOT_SIZE;
        appleY = new Random().nextInt(20) * DOT_SIZE;
    }

    public void loadImages() {  //4. метод для загрузки картинок, картинки надо добавить в файл
        ImageIcon iia = new ImageIcon("apple2.png");
        apple2 = iia.getImage();
        ImageIcon iid = new ImageIcon("pic4.png");
        pic4 = iid.getImage();
    }

    @Override   //13. добавляем метод (правая кнопка/generate / consructor
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 13.1 происходит перерисовка компонентов
        if (inGame) {
            g.drawImage(apple2, appleX, appleY, this); // 13.2 рисуем яблоко

            for (int i = 0; i < dots; i++) {
                g.drawImage(pic4, x[i], y[i], this); // 13.3 перерисовываем змейку
            }
        }
        else {
            String str = "GAME OVER"; // 19, добавляем окончание игры
            Font f = new Font("Arial", Font.BOLD, 26); //19.1 создали шрифт
            g.setColor(Color.RED);
            g.setFont(f); // 19.2 добавили шрифт
            g.drawString(str, 100 , SIZE/2); // 19.3 положение строки на экране
        }

    }

    public void move() { // 12. создаем метод движения змейки, в нем происходит логическая перерисовка точек
        for (int i = dots; i > 0; i-- ) {
           x [i] = x [i-1]; //сдвигаем точки на  1 позицию, за счет этого происходит движение
           y [i] = y [i-1];
        }
        if (left) {
            x [0] -= DOT_SIZE;
        }
        if (right) {
            x [0] += DOT_SIZE;
        }
        if (up) {
            y [0] -= DOT_SIZE;
        }
        if (down) {
            y [0] += DOT_SIZE;
        }
    }

    public void checkApple () { //14.1 метод встреча с яблоком
        if (x[0] == appleX && y [0] == appleY) { //14.2 голова встречает яблоко
            dots ++; // 14.3 тело увеличивается на 1 клетку
            createApple(); //14.4 создается новое яблоко
        }

    }

    public void checkCollisions () { // 15.1 метод встреча с бордюром или с собой
        for (int i = dots; i > 0; i--){  // 15.2 если столкновение с собой
            if (i > 4 && x[0] == x[i] && y[0] == y [i]) {
                inGame = false; // игра заканчивается
            }
        }
        if (x[0] > SIZE) {
            inGame = false; // 15.3 выход за пределы поля
        }
        if (x[0] < 0) {
            inGame = false; // 15.3 выход за пределы поля
        }
        if (y[0] > SIZE) {
            inGame = false; // 15.3 выход за пределы поля
        }
        if (y[0] < 0) {
            inGame = false; // 15.3 выход за пределы поля
        }
    }

    @Override
    //11. добавляестя само после имплементации метода из п.11 Метод срабатывает от таймера из п. 8, зависит от того, в игре ты или нет
    public void actionPerformed(ActionEvent e) {
        if (inGame) { // 11.1 если в игре
            checkApple(); // 14. змейка встречает яблоко, создаем метод
            checkCollisions (); // 15. проверка на столкновение с бордюром или с собой
            move(); //двигать змейку
        }
        repaint(); // перерисовывает поле после движения змейки
    }

    class FieldKeyListener extends KeyAdapter {        //16. обработка нажатия клавиш
        @Override   //16.1 через конструктор (правая кнопка/ generate / overideMethods/KeyPressed
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode(); //16.2 заполняем для клавиш
            if (key == KeyEvent.VK_LEFT && !right) {//16.3 если нажата клавиша влево, при этом нет движения вправо
                 left = true;
                 up = false;
                 down = false;
            }
            if (key == KeyEvent.VK_RIGHT && !left) {//16.4 если нажата клавиша вправо, при этом нет движения влево
                right = true;
                up = false;
                down = false;
            }
            if (key == KeyEvent.VK_UP && !down) {
                left = false;
                up = true;
                right = false;
            }
            if (key == KeyEvent.VK_DOWN && !up) {
                left = false;
                right = false;
                down = true;
            }
            if(!inGame && key == KeyEvent.VK_SPACE) { //рестарт игры по нажатию пробел
                inGame = true; //возвращаем все значения в исходное состояние
                left = false; //
                right = true; //
                up = false; //
                down = false; //
                timer.stop(); //останавливаем таймер
                initGame(); //вызываем метод старта новой игры
            }

        }
    }
}