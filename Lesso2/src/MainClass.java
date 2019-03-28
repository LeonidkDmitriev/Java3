import java.sql.*;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) throws Exception {
        String name,pass;

        init();

        try (Connection connection = getConnection()) {
            statements(connection); //создаем базу пользователей, пароли у всех одинаковы, чтобы проверять было легче
            resultSet(connection);
            System.out.println("Укажите цифрой один из вариантов (любая другая цифра - выход): ");
            System.out.println("1. Войти в чат ");
            System.out.println("2. Зарегистрировать в чате ");
            System.out.println("3. Сменить ник (для зарегистрированных пользователей) ");
            System.out.println("4. Удалиться из пользователей чата ");
            Scanner in = new Scanner(System.in);
            Scanner st = new Scanner(System.in);
            int menuItem = in.nextInt();
            switch(menuItem) {
                case 1:
                    // вход в чат
                    System.out.println("Введите ник ");
                    name = st.nextLine();
                    System.out.println("Введите пароль ");
                    pass = st.nextLine();
                    if (isUser(connection,name,pass)== true)
                        System.out.println("Вы вошли в чат ");
                    else System.out.println("Неверный ник или пароль ");
                    break;
                case 2:
                    //новый пользователь
                    System.out.println("Введите ник ");
                    name = st.nextLine();
                    if (isNik(connection,name)== true)
                        System.out.println("Такой пользователь уже есть ");
                    else {
                        System.out.println("Введите пароль ");
                        pass = st.nextLine();
                        newUser(connection,name,pass);
                        System.out.println("Пользователь добавлен ");
                        resultSet(connection);
                    }
                    break;
                case 3:
                    // переименование
                    System.out.println("Введите ник ");
                    name = st.nextLine();
                    System.out.println("Введите пароль ");
                    pass = st.nextLine();
                    if (isUser(connection,name,pass)== true) {
                        System.out.println("Введите новый ник ");
                        String newName = st.nextLine();
                        if (isNik(connection,name)== true) { //проверим занято ли это имя
                            renameUser(connection,name,newName);
                            System.out.println("Пользователь переименован");
                            resultSet(connection);
                        }
                        else System.out.println("Такой пользователь уже есть, нужно другое имя ");
                    }
                    else System.out.println("Неверный ник или пароль ");
                    break;
                case 4:
                    // удаление
                    System.out.println("Введите ник ");
                    name = st.nextLine();
                    System.out.println("Введите пароль ");
                    pass = st.nextLine();
                    if (isUser(connection,name,pass) == true) {
                        delUser(connection,name);
                        System.out.println("Пользователь удален");
                        resultSet(connection);
                    }
                    else System.out.println("Неверный ник или пароль ");
                    break;
                default:
                    System.out.println("Ну раз Вы ничего не хотите, то всего хорошего!");
                    break;
            }
        }
    }
//драйвера
    public static void init() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
    }
// подключаемся к бд
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:mem:test");
    }
//заполняем нашу таблицу юзеров
    public static void statements(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("create table user(" +
                    "id integer primary key auto_increment, " +
                    "name varchar(100), " + "pass varchar(100));");

            statement.execute("INSERT INTO user (name, pass) VALUES ('Evgen', 'admin')");
            statement.execute("INSERT INTO user (name, pass) VALUES ('Bugai', 'admin')");
            statement.execute("INSERT INTO user (name, pass) VALUES ('Fugas', 'admin')");
            statement.execute("INSERT INTO user (name, pass) VALUES ('11111', 'admin')");
        }
    }
//вывод таблицы на экран
    public static void resultSet(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("select * from user");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " : " + rs.getString("name")+ " : " + rs.getString("pass"));
            }
            System.out.println("----------------");
        }
    }
//проверка иника и пароля
    public static boolean isUser(Connection connection, String nik, String pass) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("select * from user");
            while (rs.next()) {
               if (rs.getString("name").equals(nik) && rs.getString("pass").equals(pass))
                 return true;

            }
        }
        return false;
    }
//проверка только ника
    public static boolean isNik(Connection connection, String nik) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("select * from user");
            while (rs.next()) {
                if (rs.getString("name").equals(nik))
                    return true;

            }
        }
        return false;
    }
//добавление нового пользователя
    public static void newUser(Connection connection, String nik, String pass) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO user (name, pass) VALUES ('" + nik + "', '" + pass + "')");
            connection.commit();
        }
    }
//удаление пользователя
    public static void delUser(Connection connection, String nik) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM user WHERE name = '"+nik+"'");
            connection.commit();
        }
    }
//изменение ника пользователя
    public static void renameUser(Connection connection, String oldNik, String newNik ) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(" UPDATE user SET name = '"+newNik+"' WHERE name = '"+oldNik+"' ");
            connection.commit();
        }
    }

}