package streams;

import java.io.*;

public class FileRead
{
    static void main() throws IOException
    {
        // bytes
        FileInputStream fin = new FileInputStream("text.txt");
        int b;
        while((b = fin.read()) != -1)
        {
//            System.out.print(b);
            System.out.print((char) b);
        }
        fin.close();

        System.out.println("\n" + "=".repeat(32));

        // chars

        InputStreamReader isr = new InputStreamReader(new FileInputStream("text.txt"));
        int c;
        while((c = isr.read()) != -1)
        {
//            System.out.print(c);
            System.out.print((char) c);
        }
        isr.close();

        System.out.println("\n" + "=".repeat(32));

        // buffer

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("text.txt")));
        String line;
        while((line = br.readLine()) != null)
        {
            System.out.println(line);
        }
        br.close();
    }
}
