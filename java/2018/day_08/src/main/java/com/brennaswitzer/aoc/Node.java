package com.brennaswitzer.aoc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Node {

    List<Integer> metadata = new ArrayList<>();
    List<Node> children = new ArrayList<>();
    AtomicInteger cursor = new AtomicInteger();

    Node() {}

    Node buildNode(int[] numbers) {

        Node node = new Node();

        int children = numbers[cursor.getAndIncrement()];
        int metadata = numbers[cursor.getAndIncrement()];

        for(int j = 0; j < children; j++){
            node.children.add(buildNode(numbers));
        }

        for(int k = 0; k < metadata; k++) {
            node.metadata.add(numbers[cursor.getAndIncrement()]);
        }
        return node;

    }

    int sumMeta() {
        int sum = 0;
        for(Integer i : metadata) {
            sum+= i;
        }
        for(Node child : children) {
            sum += child.sumMeta();
        }
        return sum;
    }

    int getValue() {
        int value = 0;

        if (children.size() == 0) {
            return sumMeta();
        }

        for( Integer m : metadata ) {
            if (m <= children.size()) {
                value += children.get(m-1).getValue();
            }
        }
        return value;
    }
}
