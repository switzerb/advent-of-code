package com.brennaswitzer.aoc;

import java.util.ArrayList;
import java.util.List;

public class Sky {

    List<Point> stars = new ArrayList<>();
    int seconds = 0;
    int MIN = Integer.MIN_VALUE;
    int MAX = Integer.MAX_VALUE;
    int minX = MIN;
    int maxX = MAX;
    int minY = MIN;
    int maxY = MAX;

    void setStar(int x, int y, int vx, int vy) {
        Point star = new Point(x, y, vx, vy);
        stars.add(star);
    }

    void reset() {
        this.minX = MAX;
        this.maxX = MIN;
        this.minY = MAX;
        this.maxY = MIN;
    }

    void animate() {
        int width = MAX;
        int height = MAX;

        while ((this.maxX - this.minX) < width && (this.maxY - this.minY) < height) {
            if (seconds != 0) {
                width = maxX - minX;
                height = maxY - minY;
            }
            reset();
            step("forward");
            seconds++;
        }
        rewind();
    }

    void step(String direction) {
        for (Point point : stars) {
            if (direction.equals("forward")) {
                point.move();
            }

            if (point.getX() < this.minX) {
                this.minX = point.getX();
            }
            if (point.getX() > maxX) {
                this.maxX = point.getX();
            }

            if (point.getY() < minY) {
                this.minY = point.getY();
            }
            if (point.getY() > maxY) {
                this.maxY = point.getY();
            }

            if (direction.equals("back")) {
                point.moveBack();
            }
        }
    }

    private void rewind() {
        reset();
        step("back");
        seconds--;
        draw();
    }

    private void draw() {
        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                boolean isThere = false;
                for (Point point : stars) {
                    if (point.hasStar(x, y)) {
                        isThere = true;
                        break;
                    }
                }

                if (isThere) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println("");
        }
    }

    int getSeconds() {
        return this.seconds;
    }

    static public class Point {
        int x;
        int y;
        int vx;
        int vy;

        Point(int x, int y, int vx, int vy) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
        }

        void move() {
            this.x += this.vx;
            this.y += this.vy;
        }

        void moveBack() {
            this.x -= this.vx;
            this.y -= this.vy;
        }

        int getX() {
            return x;
        }

        int getY() {
            return y;
        }

        boolean hasStar(int x, int y) {
            return this.x == x && this.y == y;
        }

    }

}
