package com.brennaswitzer.aoc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Register {

  List<Record> records;

  Register(List<Record> records) {
    this.records = records;
  }

  Set<Integer> getAllGuards() {
    Set<Integer> guards = new HashSet<>();
    for (Record record : records) {
      guards.add(record.getGuardOnDuty());
    }
    return guards;
  }

  int getSleepiestGuard() {
    int sleepiest = 0;
    int max = 0;
    Set<Integer> guards = getAllGuards();
    for (int guard : guards) {
      int totalSleep = getTotalforGuard(guard);
      if (totalSleep > max) {
        max = totalSleep;
        sleepiest = guard;
      }
    }
    return sleepiest;
  }

  int getTotalforGuard(int id) {
    int total = 0;
    for (Record record : records) {
      if (record.getGuardOnDuty() == id) {
        total += record.getNapTotal();
      }
    }
    return total;
  }

  int getSleepiestMinutePerGuard(int id) {
    Histogram<Integer> night = createFrequencyMapPerGuard(id);
    int max = 0;
    int minute = 0;
    for (Frequency.Entry<Integer, Integer> entry : night.entrySet()) {
      if( entry.getValue() > max) {
        max = entry.getValue();
        minute = entry.getKey();
      }
    }
    return minute;
  }

  Frequency createFrequencyMapPerGuard(int id) {
    Frequency night = new Frequency();
    for (Record record : records) {
      if (record.getGuardOnDuty() == id) {
        int[] schedule = record.getSleepSchedule();

        for (int i = 0; i < schedule.length; i++) {
          int n = 0;
          if (night.containsKey(i)) {
            n = night.get(i);
          }
          night.put(i, schedule[i] + n);
        }
      }
    }
    return night;
  }

  int getGuardwithMostMinutes() {
    int max = 0;
    int guard = 0;
    for(Record record : records) {
      int most = getMostMinutesPerGuard(record.getGuardOnDuty());
      if(most > max) {
        max = most;
        guard = record.getGuardOnDuty();
      }
    }
    return guard;
  }

  int getMostMinutesPerGuard(int id) {
    Frequency night = createFrequencyMapPerGuard(id);
    int max = 0;
    for (Frequency.Entry<Integer, Integer> entry : night.entrySet()) {
      if( entry.getValue() > max) {
        max = entry.getValue();
      }
    }
    return max;
  }

}
