package streams;

import java.io.*;

public class FileWrite
{
    static void main() throws IOException
    {
        // bytes
        FileOutputStream fos = new FileOutputStream("out.txt");
        fos.write("Hello world!\n".getBytes());
        fos.close();

        // chars
        try(OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("out.txt", true)))
        {
            osw.write("Hello world!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // buffered chars
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt", true)))
        {
            bw.write("Hello world!");
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(PrintWriter writer = new PrintWriter(new FileWriter("out.txt", true), true))
        {
            writer.println("Hello world!");
            writer.printf("Age: %d", 42);
//            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
