import java.util.ArrayList;
import java.util.List;

class TV {
    private boolean isOn = false;
    private int channel = 1;

    public void turnOn() {
        isOn = true;
        System.out.println("Телевизор включен");
    }

    public void turnOff() {
        isOn = false;
        System.out.println("Телевизор выключен");
    }

    public void setChannel(int channel) {
        if (isOn) {
            this.channel = channel;
            System.out.println("Телевизор переключен на канал " + channel);
        } else {
            System.out.println("Телевизор выключен, невозможно сменить канал");
        }
    }

    public String getStatus() {
        return "TV: " + (isOn ? "ВКЛ" : "ВЫКЛ") + ", канал: " + channel;
    }
}

class AudioSystem {
    private boolean isOn = false;
    private int volume = 20;
    private String source = "TV";

    public void turnOn() {
        isOn = true;
        System.out.println("Аудиосистема включена");
    }

    public void turnOff() {
        isOn = false;
        System.out.println("Аудиосистема выключена");
    }

    public void setVolume(int volume) {
        if (isOn) {
            this.volume = Math.max(0, Math.min(100, volume));
            System.out.println("Громкость установлена на " + this.volume);
        } else {
            System.out.println("Аудиосистема выключена, невозможно изменить громкость");
        }
    }

    public void setSource(String source) {
        if (isOn) {
            this.source = source;
            System.out.println("Источник звука установлен на " + source);
        } else {
            System.out.println("Аудиосистема выключена, невозможно изменить источник");
        }
    }

    public String getStatus() {
        return "Audio: " + (isOn ? "ВКЛ" : "ВЫКЛ") + ", громкость: " + volume + ", источник: " + source;
    }
}

class DVDPlayer {
    private boolean isOn = false;
    private boolean isPlaying = false;
    private String currentMovie = null;

    public void turnOn() {
        isOn = true;
        System.out.println("DVD-проигрыватель включен");
    }

    public void turnOff() {
        isOn = false;
        isPlaying = false;
        System.out.println("DVD-проигрыватель выключен");
    }

    public void play(String movie) {
        if (isOn) {
            currentMovie = movie;
            isPlaying = true;
            System.out.println("Воспроизведение фильма: " + movie);
        } else {
            System.out.println("DVD-проигрыватель выключен, невозможно воспроизвести");
        }
    }

    public void pause() {
        if (isOn && isPlaying) {
            isPlaying = false;
            System.out.println("Фильм на паузе");
        } else {
            System.out.println("Невозможно поставить на паузу");
        }
    }

    public void stop() {
        if (isOn) {
            isPlaying = false;
            currentMovie = null;
            System.out.println("Воспроизведение остановлено");
        }
    }

    public String getStatus() {
        String status = "DVD: " + (isOn ? "ВКЛ" : "ВЫКЛ");
        if (isPlaying) {
            status += ", воспроизведение: " + currentMovie;
        }
        return status;
    }
}

class GameConsole {
    private boolean isOn = false;
    private String currentGame = null;

    public void turnOn() {
        isOn = true;
        System.out.println("Игровая консоль включена");
    }

    public void turnOff() {
        isOn = false;
        currentGame = null;
        System.out.println("Игровая консоль выключена");
    }

    public void startGame(String game) {
        if (isOn) {
            currentGame = game;
            System.out.println("Запуск игры: " + game);
        } else {
            System.out.println("Консоль выключена, невозможно запустить игру");
        }
    }

    public String getStatus() {
        String status = "Console: " + (isOn ? "ВКЛ" : "ВЫКЛ");
        if (currentGame != null) {
            status += ", игра: " + currentGame;
        }
        return status;
    }
}

class HomeTheaterFacade {
    private TV tv;
    private AudioSystem audio;
    private DVDPlayer dvd;
    private GameConsole console;

    public HomeTheaterFacade() {
        this.tv = new TV();
        this.audio = new AudioSystem();
        this.dvd = new DVDPlayer();
        this.console = new GameConsole();
    }

    public void watchMovie(String movie, int channel, int volume) {
        System.out.println("\nЗапуск режима просмотра фильма:");
        tv.turnOn();
        tv.setChannel(channel);

        audio.turnOn();
        audio.setVolume(volume);
        audio.setSource("DVD");

        dvd.turnOn();
        dvd.play(movie);

        System.out.println("Система готова для просмотра фильма!");
    }

    public void watchMovie(String movie) {
        watchMovie(movie, 3, 35);
    }

    public void listenMusic(int channel, int volume) {
        System.out.println("\nЗапуск режима прослушивания музыки:");
        tv.turnOn();
        tv.setChannel(channel);

        audio.turnOn();
        audio.setVolume(volume);
        audio.setSource("TV");

        System.out.println("Система готова для прослушивания музыки!");
    }

    public void listenMusic() {
        listenMusic(5, 40);
    }

    public void playGame(String game, int volume) {
        System.out.println("\nЗапуск игрового режима:");
        tv.turnOn();
        tv.setChannel(1);

        audio.turnOn();
        audio.setVolume(volume);
        audio.setSource("Console");

        console.turnOn();
        console.startGame(game);

        System.out.println("Система готова для игры!");
    }

    public void playGame(String game) {
        playGame(game, 50);
    }

    public void turnOffAll() {
        System.out.println("\nВыключение всей системы:");
        tv.turnOff();
        audio.turnOff();
        dvd.turnOff();
        console.turnOff();
        System.out.println("Вся система выключена");
    }

    public void setVolume(int volume) {
        System.out.println("\nРегулировка громкости: " + volume);
        audio.setVolume(volume);
    }

    public void getSystemStatus() {
        System.out.println("\nСтатус системы:");
        System.out.println(tv.getStatus());
        System.out.println(audio.getStatus());
        System.out.println(dvd.getStatus());
        System.out.println(console.getStatus());
    }
}

abstract class FileSystemComponent {
    protected String name;

    public FileSystemComponent(String name) {
        this.name = name;
    }

    public abstract void display(int indent);
    public abstract long getSize();
    public abstract boolean isDirectory();

    public void display() {
        display(0);
    }

    public String getName() {
        return name;
    }
}

class File extends FileSystemComponent {
    private long size;

    public File(String name, long size) {
        super(name);
        this.size = size;
    }

    @Override
    public void display(int indent) {
        String indentation = "  ".repeat(indent);
        System.out.println(indentation + name + " (" + size + " bytes)");
    }

    @Override
    public long getSize() {
        return size;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }
}

class Directory extends FileSystemComponent {
    private List<FileSystemComponent> children;

    public Directory(String name) {
        super(name);
        this.children = new ArrayList<>();
    }

    public boolean add(FileSystemComponent component) {
        // Проверка на дублирование имен
        for (FileSystemComponent child : children) {
            if (child.getName().equals(component.getName())) {
                System.out.println("Ошибка: компонент с именем '" + component.getName() +
                        "' уже существует в папке '" + name + "'");
                return false;
            }
        }

        children.add(component);
        return true;
    }

    public boolean remove(String componentName) {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).getName().equals(componentName)) {
                children.remove(i);
                return true;
            }
        }
        System.out.println("Ошибка: компонент с именем '" + componentName +
                "' не найден в папке '" + name + "'");
        return false;
    }

    public FileSystemComponent getChild(String name) {
        for (FileSystemComponent child : children) {
            if (child.getName().equals(name)) {
                return child;
            }
        }
        return null;
    }

    @Override
    public void display(int indent) {
        String indentation = "  ".repeat(indent);
        System.out.println(indentation + name + "/ (размер: " + getSize() + " bytes)");
        for (FileSystemComponent child : children) {
            child.display(indent + 1);
        }
    }

    @Override
    public long getSize() {
        long totalSize = 0;
        for (FileSystemComponent child : children) {
            totalSize += child.getSize();
        }
        return totalSize;
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    public int getChildrenCount() {
        return children.size();
    }
}

public class hwork9 {

    public static void testHomeTheater() {
        System.out.println("ПАТТЕРН 'ФАСАД' - МУЛЬТИМЕДИЙНЫЙ ЦЕНТР");

        HomeTheaterFacade homeTheater = new HomeTheaterFacade();

        homeTheater.getSystemStatus();

        homeTheater.watchMovie("Интерстеллар", 5, 45);
        homeTheater.getSystemStatus();

        homeTheater.setVolume(60);

        homeTheater.playGame("The Legend of Zelda", 55);
        homeTheater.getSystemStatus();

        homeTheater.listenMusic(12, 38);
        homeTheater.getSystemStatus();

        homeTheater.turnOffAll();
        homeTheater.getSystemStatus();
    }

    public static void testFileSystem() {
        System.out.println("ПАТТЕРН 'КОМПОНОВЩИК' - ФАЙЛОВАЯ СИСТЕМА");

        File file1 = new File("document.txt", 1500);
        File file2 = new File("image.jpg", 250000);
        File file3 = new File("program.py", 8000);
        File file4 = new File("readme.md", 1200);
        File file5 = new File("data.csv", 35000);
        File file6 = new File("config.json", 800);

        Directory root = new Directory("root");
        Directory documents = new Directory("Documents");
        Directory images = new Directory("Images");
        Directory code = new Directory("Code");
        Directory pythonProjects = new Directory("PythonProjects");

        System.out.println("\n1. Создание структуры файловой системы:");

        root.add(file1);
        root.add(file6);

        documents.add(file4);

        images.add(file2);

        pythonProjects.add(file3);
        pythonProjects.add(file5);
        code.add(pythonProjects);

        root.add(documents);
        root.add(images);
        root.add(code);

        System.out.println("\n2. Структура файловой системы:");
        root.display();

        System.out.println("\n3. Тест проверки дублирования:");
        File duplicateFile = new File("document.txt", 2000);
        root.add(duplicateFile);

        System.out.println("\n4. Тест удаления файла:");
        root.remove("config.json");

        System.out.println("\n5. Обновленная структура:");
        root.display();

        System.out.println("\n6. Дополнительная информация:");
        System.out.println("Общий размер корневой папки: " + root.getSize() + " bytes");
        System.out.println("Количество элементов в корневой папке: " + root.getChildrenCount());
        System.out.println("Количество элементов в папке Code: " + code.getChildrenCount());

        System.out.println("\n7. Тест удаления несуществующего файла:");
        root.remove("nonexistent.file");
    }

    public static void main(String[] args) {
        System.out.println("ДЕМОНСТРАЦИЯ СТРУКТУРНЫХ ПАТТЕРНОВ");

        testHomeTheater();

        testFileSystem();
    }
}