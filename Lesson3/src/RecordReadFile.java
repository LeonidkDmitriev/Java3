import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RecordReadFile {

    //запись в файл
    public void recordFile(String fileName, List listMessage) {
        try(FileWriter writer = new FileWriter(fileName, false))
        {
            for (int i=0; i<listMessage.size(); i++) {
                writer.write((String) listMessage.get(i));  // пишем в файл
                writer.write("\r\n");               // переход на новую строку, понятный для блокнота
            }
            writer.flush();
        }
        catch(IOException ex){
            System.out.println("Ошибка записи файла");
        }
    }

    //чтение 100 последних сообщений
    public List readFile(String fileName) {
        List listMessage = new ArrayList<String>();
        String strLine;
        try{
            FileInputStream fstream = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            while ((strLine = br.readLine()) != null){
               listMessage.add(strLine);
            }
        }catch (IOException e){
            System.out.println("Ошибка чтения файла");
        }
        return listMessage;
    }

}
