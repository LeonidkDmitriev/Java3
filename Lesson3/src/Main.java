import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int sizeHistory = 100;
        RecordReadFile file = new RecordReadFile();
        Bot bot = new Bot();                        //подключаем бота
        String strBot = new String();               // ответы бота
        String strHuman = new String(" ");  //ответы человека
        List chatList = new ArrayList<String>();    // а сюда будем писать историю
        Scanner inStr= new Scanner(System.in);

        System.out.println("Вы вошли в чат...Чтобы покинуть чат, наберите exit ");
        do {
            strHuman = inStr.nextLine();
            strBot = bot.botWords.get((int) (Math.random()*10));  // пускай отвечает случайно
            System.out.println(strBot);
            chatList.add(strHuman);
            chatList.add(strBot);
        } while(!strHuman.equals("exit"));
      file.recordFile("message.txt", chatList);  //запишем историю чата
      chatList = file.readFile("message.txt");  //запишем историю чата
      //вывод истории чата по указанным границам
        System.out.println("---------------------------------------");
      if (sizeHistory >  chatList.size()) sizeHistory=chatList.size(); //если в файле меньше 100 записей
        for (int i = (chatList.size()-sizeHistory); i < ((chatList.size()-sizeHistory)+sizeHistory) ; i++) {
            System.out.println(chatList.get(i));
        }
    }
}
