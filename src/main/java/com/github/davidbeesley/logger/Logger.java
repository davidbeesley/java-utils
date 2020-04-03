package com.github.davidbeesley.logger;

import com.github.davidbeesley.util.Util;

import java.util.Stack;
import java.util.concurrent.Semaphore;

/**
 * System wide logger.
 * This logger is meant to be shared among all parts of the program. It is thread safe.
 * It is designed so that output can be easily turned on or off.
 */
public class Logger {

    private  final LogLevel logLevelDefault = LogLevel.INFO;
    private  LogLevel logLevel = logLevelDefault;
    private static Logger instance;
    private Semaphore semaphore;
    private Stack<LogLevel> logLevels;

    /**
     * Singleton
     */
    public static Logger getInstance(){
        if (instance == null){
            instance = new Logger();
        }
        return instance;
    }

    private Logger(){
        semaphore = new Semaphore(1);
        logLevels = new Stack<>();
    }


    /**
     *
     * @param messageLogLevel
     * @param message
     * @return TRUE if output was printed.
     */
    private boolean output(LogLevel messageLogLevel, String message){
        semaphore.acquireUninterruptibly();
        if (!logLevel.shouldOutput(messageLogLevel)) return false;

        System.out.print(messageLogLevel.toString() + "\t");

        Thread ct = Thread.currentThread();
        String threadID = ct.getId() + "";
        System.out.print(ct.getName() + "("+ threadID + ")\t");
        System.out.print(Util.getTimeStamp()+"\t");
        StackTraceElement[] stackTraceElements = ct.getStackTrace();


        int loggerIndex = 0;
        String className = getClassName(stackTraceElements[loggerIndex].getClassName());
        while (!className.equals("Logger")){
            loggerIndex += 1;
            className = getClassName(stackTraceElements[loggerIndex].getClassName());
        }
        loggerIndex += 2;
        className = getClassName(stackTraceElements[loggerIndex].getClassName());





        System.out.print(className + ":" + stackTraceElements[loggerIndex].getMethodName() + "():\n\t\t");
        System.out.println(message);
        System.out.println();
        semaphore.release();

        return true;
    }

    /**
     * Set the global logging level.
     *
     * Keeps a stack to allow reverting to previous level
     * @param logLevel
     */
    public void pushLogLevel(LogLevel logLevel){
        logLevels.push(logLevel);
        this.logLevel = logLevel;
    }

    /**
     * Revert to the previous log level.
     */
    public void popLogLevel() {
        if (logLevels.empty()) {
            warning("Popped empty log level stack");
            logLevel = logLevelDefault;
        } else {
            logLevel = logLevels.pop();
        }
    }

    public void trace(String message){
        output(LogLevel.TRACE, message);
    }
    public void debug(String message){
        output(LogLevel.DEBUG, message);

    }
    public void info(String message){
        output(LogLevel.INFO, message);

    }
    public void warning(String message){
        output(LogLevel.WARNING, message);

    }
    public void error(String message){
        output(LogLevel.ERROR, message);

    }

    public void restoreDefault(){
        logLevel = logLevelDefault;
    }


    public LogLevel getLogLevel() {
        return logLevel;
    }

    private String getClassName(String s){
        int i = s.lastIndexOf('.');
        s = s.substring(i+1);
        return s;
    }




    /**
     * Valid Logger Levels.
     */
    public enum LogLevel {
        TRACE(0), DEBUG(1), INFO(2), WARNING(3), ERROR(4), NONE(5);



        private Integer level;

        LogLevel(int level) {
            this.level = level;
        }

        /**
         * For comparing a message loglevel to the current log level
         * @param messageLevel message loglevel
         * @return true if the message loglevel is important enough to print.
         */
        public boolean shouldOutput(LogLevel messageLevel) {
            if (this.level == NONE.level) return false;
            return this.level <= messageLevel.level;
        }
    }


}
