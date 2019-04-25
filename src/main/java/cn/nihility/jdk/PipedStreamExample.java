package cn.nihility.jdk;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by yzx on 2019/4/25.
 */
public class PipedStreamExample {

    public static void main(String[] args) throws IOException, InterruptedException {

        final PipedInputStream pipedInputStream = new PipedInputStream();
        final PipedOutputStream pipedOutputStream = new PipedOutputStream();

        /* Connect pipe */
        pipedInputStream.connect(pipedOutputStream);

        /* Thread for writing data to pipe */
        Thread pipeWriter = new Thread(() -> {
            for (int i = 65; i < 91; i++) {
                try {
                    pipedOutputStream.write(i);
                    Thread.sleep(500);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        /* Thread for reading data from pipe */
        Thread pipeReader = new Thread(() -> {
            for (int i = 65; i < 91; i++) {
                try {
                    System.out.print((char)pipedInputStream.read());
                    Thread.sleep(1000);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        /* Starting Thread */
        pipeWriter.start();
        pipeReader.start();

        /* Join Thread */
        pipeWriter.join();
        pipeReader.join();

        /* Close Stream */
        pipedOutputStream.close();
        pipedInputStream.close();

    }

}
