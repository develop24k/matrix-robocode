package com.develop24k;

import dev.robocode.tankroyale.botapi.Bot;
import dev.robocode.tankroyale.botapi.BotInfo;
import dev.robocode.tankroyale.botapi.Color;
import dev.robocode.tankroyale.botapi.events.*;


public class MatrixBot extends Bot {
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
            turnLeft(10_0000);
            // Limit our speed to 5
            //setMaxSpeed(5);
            // Start moving (and turning)
            //rward(10_0);
        }
    }

    @Override
    public void onHitBot(HitBotEvent botHitBotEvent) {
    }

    @Override
    public void onScannedBot(ScannedBotEvent e) {

        if (Math.abs(Math.abs(getX()) - Math.abs(e.getX())) < 150 && Math.abs(Math.abs(getY()) - Math.abs(e.getY())) < 150) {
            setBulletColor(Color.MAROON);
            fire(3);
            fire(3);
            fire(3);
        } else if (Math.abs(Math.abs(getX()) - Math.abs(e.getX())) < 300 && Math.abs(Math.abs(getY()) - Math.abs(e.getY())) < 300) {
            setBulletColor(Color.CYAN);
            fire(2);
            fire(2);fire(2);
        } else if (Math.abs(Math.abs(getX()) - Math.abs(e.getX())) < 600 && Math.abs(Math.abs(getY()) - Math.abs(e.getY())) < 600) {
            setBulletColor(Color.ORANGE);
            fire(1);fire(1);fire(1);
        } else if (Math.abs(Math.abs(getX()) - Math.abs(e.getX())) < 800 && Math.abs(Math.abs(getY()) - Math.abs(e.getY())) < 800) {
            fire(.5);
        } else {
            fire(.1);
        }


    }

    @Override
    public void onHitWall(HitWallEvent botHitWallEvent) {


    }

    // We were hit by a bullet -> turn perpendicular to the bullet
    @Override
    public void onHitByBullet(HitByBulletEvent e) {
        // Calculate the bearing to the direction of the bullet
        var bearing = calcBearing(e.getBullet().getDirection());

        // Turn 90 degrees to the bullet direction based on the bearing
        turnLeft(90 - bearing);

    }

}
