package cn.nihility.jdk;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 * Created by yzx on 2019/4/25.
 */
public class ReaderExample {

    public static void main(String[] args) {
        try {
//            write();
            read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read() throws IOException {

        String ext = "io/reader.txt";
        String fileRoot = ReaderExample.class.getResource("/").getFile();
        String filePath = fileRoot + ext;
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("Can not find file " + filePath);
            return;
        }

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8")));

        String line = bufferedReader.readLine();
        while (null != line) {
            System.out.println(line);
            line = bufferedReader.readLine();
        }

        bufferedReader.close();

    }

    public static void write() throws IOException {

        String[] lines = new String[] {
                "蒹葭苍苍，白露为霜。所谓伊人，在水一方。溯洄从之，道阻且长。溯游从之，宛在水中央。",
                "蒹葭萋萋，白露未晞。所谓伊人，在水之湄。溯洄从之，道阻且跻。溯游从之，宛在水中坻。",
                "蒹葭采采，白露未已。所谓伊人，在水之涘。溯洄从之，道阻且右。溯游从之，宛在水中沚。"
        };
        String ext = "io/reader.txt";
        String fileRoot = ReaderExample.class.getResource("/").getFile();
        String filePath = fileRoot + ext;

        File file = new File(filePath);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }

        BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(file), Charset.forName("UTF-8")));

        for (String s : lines) {
            bufferedWriter.write(s);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

    }

}
