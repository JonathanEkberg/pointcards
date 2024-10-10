package pointcards.utils;

public class Logger {
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String ORANGE = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";

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
                logLevel = LogLevel.DEBUG; // Default level
            }
        } else {
            logLevel = LogLevel.DEBUG; // Default level
        }
    }

    public static void info(Object message) {
        if (logLevel.ordinal() <= LogLevel.INFO.ordinal()) {
            System.out.println(GREEN + "[INFO]" + RESET + " " + message);
        }
    }

    public static void logError(Object message) {
        if (logLevel.ordinal() <= LogLevel.ERROR.ordinal()) {
            System.err.println(RED + "[ERROR]" + RESET + " " + message);
        }
    }

    public static void logWarning(Object message) {
        if (logLevel.ordinal() <= LogLevel.WARN.ordinal()) {
            System.out.println(ORANGE + "[WARNING]" + RESET + " " + message);
        }
    }

    public static void debug(Object message) {
        if (logLevel.ordinal() <= LogLevel.DEBUG.ordinal()) {
            System.out.println(BLUE + "[DEBUG]" + RESET + " " + message);
        }
    }
}
