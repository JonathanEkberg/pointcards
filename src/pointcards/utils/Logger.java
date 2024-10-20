package pointcards.utils;

/**
 * The Logger class provides a simple logging utility with different log levels.
 * It supports logging messages at DEBUG, INFO, WARN, and ERROR levels.
 * The log level can be set using the environment variable LOGGER_LEVEL.
 * If the environment variable is not set or is invalid, the default log level
 * is ERROR.
 * 
 * Log messages are color-coded for better readability in the console.
 */
public class Logger {
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String ORANGE = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";

    /**
     * Enum representing the different log levels.
     */
    public enum LogLevel {
        DEBUG, INFO, WARN, ERROR
    }

    private static LogLevel logLevel;

    static {
        String logLevelEnv = System.getenv("LOGGER_LEVEL");
        if (logLevelEnv != null) {
            try {
                logLevel = LogLevel.valueOf(logLevelEnv.toUpperCase());
            } catch (IllegalArgumentException e) {
                logLevel = LogLevel.ERROR; // Default level
            }
        } else {
            logLevel = LogLevel.ERROR; // Default level
        }
    }

    /**
     * Converts an array of objects to a single string.
     * 
     * @param messages The array of objects to convert.
     * @return A string representation of the objects.
     */
    private static String objToStr(Object... messages) {
        StringBuilder sb = new StringBuilder();
        for (Object message : messages) {
            sb.append(" ");
            sb.append(message);
        }
        return sb.toString();
    }

    /**
     * Logs an INFO level message.
     * 
     * @param message The message to log.
     */
    public static void info(Object... message) {
        if (logLevel.ordinal() <= LogLevel.INFO.ordinal()) {
            System.out.println(GREEN + "[INFO]" + RESET + " " + objToStr(message));
        }
    }

    /**
     * Logs an ERROR level message.
     * 
     * @param message The message to log.
     */
    public static void error(Object... message) {
        if (logLevel.ordinal() <= LogLevel.ERROR.ordinal()) {
            System.err.println(RED + "[ERROR]" + RESET + " " + objToStr(message));
        }
    }

    /**
     * Logs a WARN level message.
     * 
     * @param message The message to log.
     */
    public static void warn(Object... message) {
        if (logLevel.ordinal() <= LogLevel.WARN.ordinal()) {
            System.out.println(ORANGE + "[WARNING]" + RESET + " " + objToStr(message));
        }
    }

    /**
     * Logs a DEBUG level message.
     * 
     * @param message The message to log.
     */
    public static void debug(Object... message) {
        if (logLevel.ordinal() <= LogLevel.DEBUG.ordinal()) {
            System.out.println(BLUE + "[DEBUG]" + RESET + " " + objToStr(message));
        }
    }
}