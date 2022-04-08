package CoreDiscordBot.FileLog;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileLogNewLogs {

    private final File LogFileName = new File("logs.txt");

    public static FileLogNewLogs FileLog = new FileLogNewLogs();

    public FileLogNewLogs(){
        try {
            if (LogFileName.createNewFile()) {
                System.out.println("File created: " + LogFileName.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void addLogTrace(String Log){
        try {

            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd-HH:mm :");
            Date date = new Date(System.currentTimeMillis());
            String dateToday = formatter.format(date);

            Reader read = new FileReader(LogFileName);
            BufferedReader br = new BufferedReader(read);
            StringBuilder sb = new StringBuilder();
            String content;
            while((content = br.readLine())!=null){
                sb.append(content).append("\n");
            }
            br.close();

            Log = dateToday + Log;
            Writer writer = new FileWriter(LogFileName);
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(sb+Log);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
