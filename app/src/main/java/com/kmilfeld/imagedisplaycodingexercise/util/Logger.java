package com.kmilfeld.imagedisplaycodingexercise.util;

import android.util.Log;

/**
 * Logs messages to stdout with additional information such
 * as file name, method name, and line number.
 *
 * Created by karenm on 2/4/17.
 */
public class Logger
{
    /**
     * Logs a verbose-level message
     * @param text Text to log
     */
    public static void verbose( String text )
    {
        Log.v( getTag(), text );
    }

    /**
     * Logs an error-level message.
     * @param text Text to log
     */
    public static void error( String text )
    {
        Log.e( getTag(), text );
    }

    /**
     * Get a log tag consisting of the file, line number and method
     *
     * @return The tag to use when logging
     */
    private static String getTag()
    {
        // Get the stack trace element for the calling method
        StackTraceElement caller = Thread.currentThread().getStackTrace()[4];

        // Get the file, method, and line number
        String file = caller.getFileName();
        String method = caller.getMethodName();
        int lineNumber = caller.getLineNumber();

        // Return formatted log string
        return "(" + file + ":" + lineNumber + ") - " + method + "()";
    }
}
