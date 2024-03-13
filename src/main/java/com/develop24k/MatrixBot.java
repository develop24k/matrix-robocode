package com.develop24k;

import dev.robocode.tankroyale.botapi.Bot;
import dev.robocode.tankroyale.botapi.BotInfo;
import dev.robocode.tankroyale.botapi.Color;
import dev.robocode.tankroyale.botapi.events.*;

import java.util.Random;


public class MatrixBot extends Bot {

    int turnDirection = 1;
    MatrixBot(){
        super(BotInfo.fromInputStream(Thread.currentThread().getContextClassLoader().getResourceAsStream("matrixbot.json")));
    }
    @Override
    public void run()
    {
       setTargetSpeed(800);
        setFireAssist(true);
        while (isRunning()) {
            // Tell the game that when we take move, we'll also want to turn right... a lot
            //fire(3);
            turnLeft(10_0000);

            System.out.println("In Running");
            // Limit our speed to 5
            //setMaxSpeed(5);
            // Start moving (and turning)
            //rward(10_0);
        }
    }

    @Override
    public void onHitBot(HitBotEvent e) {
        headOnTarget(e.getX(), e.getY());

        if (e.getEnergy() > 16) {
            fire(3);
        } else if (e.getEnergy() > 10) {
            fire(2);
        } else if (e.getEnergy() > 4) {
            fire(1);
        } else if (e.getEnergy() > 2) {
            fire(.5);
        } else if (e.getEnergy() > .4) {
            fire(.1);
        }
        else run();
    }

    @Override
    public void onScannedBot(ScannedBotEvent e) {

        var bearingFromGun = gunBearingTo(e.getX(), e.getY());

        if (Math.abs(Math.abs(getX()) - Math.abs(e.getX())) < 150 && Math.abs(Math.abs(getY()) - Math.abs(e.getY())) < 150) {
            setBulletColor(Color.MAROON);
            fire(3);
        } else if (Math.abs(Math.abs(getX()) - Math.abs(e.getX())) < 300 && Math.abs(Math.abs(getY()) - Math.abs(e.getY())) < 300) {
            setBulletColor(Color.CYAN);
            fire(2);
        } else if (Math.abs(Math.abs(getX()) - Math.abs(e.getX())) < 600 && Math.abs(Math.abs(getY()) - Math.abs(e.getY())) < 600) {
            setBulletColor(Color.ORANGE);
            fire(1);
        } else if (Math.abs(Math.abs(getX()) - Math.abs(e.getX())) < 800 && Math.abs(Math.abs(getY()) - Math.abs(e.getY())) < 800) {
            fire(.5);
        } else {
            fire(.1);
        }
        if (bearingFromGun == 0) {
            rescan();
        }
    }


    private void headOnTarget(double x, double y) {
        var bearing = bearingTo(x, y);
        if (bearing >= 0) {
            turnDirection = 1;
        } else {
            turnDirection = -1;
        }
        turnLeft(bearing);
    }

    @Override
    public void onHitByBullet(HitByBulletEvent e) {

        var bearing = calcBearing(e.getBullet().getDirection());

        turnRight(90 - bearing);

    }

}
