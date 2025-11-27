package com.brennaswitzer.aoc;

import java.util.HashMap;

/**
 * Represents the stats from one evening
 */
public class Record {

  private int month;
  private int day;
  private int guard; // guard on duty
  private int[] minutes = new int[60];
  private int totalSleep = 0;

  Record(int guard, int month, int day) {
    this.guard = guard;
    this.month = month;
    this.day = day;
  }

  Record(int guard, int month, int day, int start, int end) {
    this.guard = guard;
    this.month = month;
    this.day = day;

    setNap(start, end);
  }

  void setNap(int start, int end) {
    for (int i = start; i < end; i++) {
      minutes[i] = 1;
    }
  }

  int getNapTotal() {
    for (int i : minutes) {
      totalSleep = totalSleep + i;
    }
    return totalSleep;
  }

  int getGuardOnDuty() {
    return this.guard;
  }

  String getDay() {
    return this.month + "-" + this.day;
  }

  int[] getSleepSchedule() {
    return this.minutes;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < minutes.length; i++) {
      sb.append(i);
      sb.append("  ");
    }
    sb.append("\n");
    for (int sleep : minutes) {
      sb.append(sleep);
      sb.append("  ");
    }
    return sb.toString();
  }
}
