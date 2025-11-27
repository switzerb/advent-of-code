#!/usr/bin/env ruby
#--- Day 3: Crossed Wires ---#
# https://adventofcode.com/2019/day/3 #

 class Fueler

  def initialize
    @wireA = []
    @wireB = []
    @intersections = []
    @x = 0
    @y = 0
    @x2 = 0
    @y2 = 0
  end

  def draw_paths(path, wire)

    if (wire == "A")
     path.each do |i|
       draw_wireA(i)
     end
    end

    if (wire == "B")
      path.each do |i|
        draw_wireB(i)
      end
    end

    # calculate all the points from the path instructions?
    # for both wireA and wireB
    # the intersections of those points are the total number they have in common
    # get the closest one to 0,0

  end

  def draw_wireA(instruction)
    dir = instruction[0]
    dist = instruction[1..-1].to_i

      dist.times do
        @x = @x + 1 if dir == "R"
        @x = @x - 1 if dir == "L"
        @y = @y + 1 if dir == "U"
        @y = @y - 1 if dir == "D"
        @wireA << [@x, @y]
      end
    @wireA
  end

  def draw_wireB(instruction)
  
    dir = instruction[0]
    dist = instruction[1..-1].to_i

      dist.times do
        @x2 = @x2 + 1 if dir == "R"
        @x2 = @x2 - 1 if dir == "L"
        @y2 = @y2 + 1 if dir == "U"
        @y2 = @y2 - 1 if dir == "D"
        @wireB << [@x2, @y2]
      end
  end

  def intersections
    @intersections = @wireA & @wireB
    @intersections
  end

  def manhattan_distance
    least = 0;
    dists = @intersections.map { |point| point[0].abs + point[1].abs}
    puts dists
    dists.min
  end

  def min_distance
    sA = @wireA.find_index(@intersections[0])
    sB = @wireB.find_index(@intersections[0])
    sA + sB + 2
  end

 end
