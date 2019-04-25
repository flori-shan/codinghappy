package cn.nihility.jdk;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by yzx on 2019/4/25.
 */
public class StreamExample {

    public static void main(String[] args) {
        try {
//            write();
            read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read() throws IOException {

        String fileUrl = "io/poem.txt";
        String fileRoot = StreamExample.class.getClassLoader().getResource("").getFile();
        String filePath = fileRoot + fileUrl;

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File " + filePath + " do not exists");
            return;
        }

        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int buffer = 512;
        int read = 0;
        byte[] data = new byte[512];


        while ((read = bufferedInputStream.read(data, 0, buffer)) != -1) {
            System.out.println("read byte is " + read);
            System.out.println(new String(data, 0, read, "UTF-8"));
            byteArrayOutputStream.write(data, 0, read);
        }

        System.out.println("----------------------------------");
        byte[] totalData = byteArrayOutputStream.toByteArray();
        System.out.println(new String(totalData, 0, totalData.length, "UTF-8"));

        bufferedInputStream.close();
        fileInputStream.close();
        byteArrayOutputStream.close();

    }

    public static void write() throws IOException {
        String[] lines = new String[] {
                "蒹葭苍苍，白露为霜。所谓伊人，在水一方。溯洄从之，道阻且长。溯游从之，宛在水中央。",
                "蒹葭萋萋，白露未晞。所谓伊人，在水之湄。溯洄从之，道阻且跻。溯游从之，宛在水中坻。",
                "蒹葭采采，白露未已。所谓伊人，在水之涘。溯洄从之，道阻且右。溯游从之，宛在水中沚。"
        };

        String fileUrl = "io/1poem.txt";
        String fileRoot = StreamExample.class.getClassLoader().getResource("").getFile();


        String filePath = fileRoot + fileUrl;

        File file = new File(filePath);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }

        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

        for (String s : lines) {
            bufferedOutputStream.write(System.getProperty("line.separator").getBytes(Charset.forName("UTF-8")));
            bufferedOutputStream.write(s.getBytes(Charset.forName("UTF-8")));
        }

        bufferedOutputStream.flush();


        bufferedOutputStream.close();
        fileOutputStream.close();

    }

}
