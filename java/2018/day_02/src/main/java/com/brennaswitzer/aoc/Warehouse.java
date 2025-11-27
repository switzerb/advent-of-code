package com.brennaswitzer.aoc;

import java.util.List;
import java.util.Set;

public class Warehouse {

  private List<String> boxes;

  Warehouse(List<String> boxes) {
    this.boxes = boxes;
  }

  public int getChecksum() {
    int countTwos = 0;
    int countThrees = 0;

    for (String boxID : this.boxes) {
      char[] letters = boxID.toCharArray();
      Histogram<Character> h = this.buildHistogram(letters);
      Set<Character> keys = h.keySet();

      boolean flag2 = false;
      boolean flag3 = false;

      for (Character k : keys) {
        if (h.get(k) == 2) {
          flag2 = true;
        }
        if (h.get(k) == 3) {
          flag3 = true;
        }
      }
      if (flag2) {
        countTwos++;
      }
      if (flag3) {
        countThrees++;
      }
    }
    return countTwos * countThrees;
  }

  public Histogram<Character> buildHistogram(char[] letters) {
    Histogram<Character> h = new Histogram<>();
    for (char letter : letters) {
      if (h.containsKey(letter)) {
        h.put(letter, h.get(letter) + 1);
      } else {
        h.put(letter, 1);
      }
    }
    return h;
  }

  public String getCommonLetters() {

    int len = this.boxes.size();
    String common = "";

    for (int i = 0; i < len - 1; i++) {
      char[] first = this.boxes.get(i).toCharArray();
      for (int j = i + 1; j < len; j++) {
        char[] second = this.boxes.get(j).toCharArray();
        int differences = countDiffs(first, second);

        if(differences == 1) {
          common = buildCommonString(first, second);
          break;
        }
      }
    }
    if (common.length() == 0) {
      common = "No matches found";
    }
    return common;
  }

  public int countDiffs(char[] first, char[] second) {
    int count = 0;

    if (first.length != second.length) {
      throw new RuntimeException(("There is a problem with your input"));
    }
    for (int i = 0; i < first.length; i++) {
      if (first[i] != second[i]) {
        count++;
      }
    }
    return count;
  }

  public String buildCommonString(char[] first, char[] second) {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < first.length; i++) {
      if (first[i] == second[i]) {
        sb.append(first[i]);
      }
    }
    return sb.toString();
  }

}
