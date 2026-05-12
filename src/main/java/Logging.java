import java.io.IOException;
import java.util.logging.*;

public class Logging
{
    private static final Logger logger = Logger.getLogger("wfis.klientserwer.logging");

    static void main()
    {
        try
        {
            FileHandler fileHandler = getFileHandler();
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);

        } catch (IOException e)
        {
            logger.severe("Failed to set up file handler for logging");
        }

        logger.info("Hello");
        logger.log(Level.INFO, "Test #{0}, msg: {1}", new Object[]{1, "Hello"});

        try{
            double a = 5 / 0;
        } catch (ArithmeticException e)
        {
            logger.log(Level.SEVERE, "Exception: " + e.getMessage(), e);
        }
    }

    private static FileHandler getFileHandler() throws IOException
    {
        FileHandler fileHandler = new FileHandler("app.log", true);
        fileHandler.setLevel(Level.INFO);
        fileHandler.setFormatter(new SimpleFormatter());

//        Formatter formatter = new SimpleFormatter()
//        {
//            @Override
//            public String format(LogRecord record) {
//                return String.format(
//                        "[%s] [%s] [%s] %s%n",
//                        LocalDateTime.now(),
//                        record.getLevel(),
//                        record.getLoggerName(),
//                        record.getMessage()
//                );
//            }
//        };
//
//        fileHandler.setFormatter(formatter);
        return fileHandler;
    }
}
