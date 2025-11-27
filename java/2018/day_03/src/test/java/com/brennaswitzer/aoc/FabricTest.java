package com.brennaswitzer.aoc;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FabricTest {

  Fabric fabric;

  @Before
  public void setup() {
    List<Claim> claims = new ArrayList<>();
    Claim a = new Claim(1, 1, 3, 4, 4);
    Claim b = new Claim(2, 3, 1, 4, 4);
    Claim c = new Claim(3, 5, 5, 2, 2);
    claims.add(a);
    claims.add(b);
    claims.add(c);
    fabric = new Fabric(10, 10, claims);
    fabric.claimRunner();
  }

  @Test
  public void testGrid() {
    // NOTE: This is just a visual inspection tool of the fabric state
    System.out.println(fabric);
  }

  @Test
  public void testSetClaim() {
    Claim a = new Claim(5, 1, 3, 4, 4);
    fabric.setClaim(a);
    System.out.println(fabric);
  }

  @Test
  public void testGetOverlap() {
    System.out.println(fabric);
    assertEquals(4, fabric.getOverlappingClaims());
  }

  @Test
  public void testGetIndex() {
    assertEquals(0, fabric.getIndex(0, 0));
    assertEquals(11, fabric.getIndex(1, 1));
    assertEquals(31, fabric.getIndex(1, 3));
  }

  @Test
  public void getNoOverlap() {
    System.out.println(fabric);
    assertEquals(3, fabric.getNoOverlap());
  }
}
