package com.develop24k;

import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Entry
 *
 */
public class BotApp
{
    public static void main( String[] args )
    {
        MatrixBot matrixBot = new MatrixBot();
        matrixBot.start();
    }
}
