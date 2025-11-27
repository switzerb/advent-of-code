#!/usr/bin/env ruby

  begin
    gem 'minitest', '>=5.0.0'
    require 'minitest/autorun'
    require_relative 'day_10.rb'
  rescue Gem::LoadError => e
    puts "\nError:\n#{e.backtrace.first} #{e.message}"
    puts DATA.read
    exit 1
  end

 class Day10Test < Minitest::Test
   def setup
     ex1 = <<~INPUT
      .#..##.###...#######
      ##.############..##.
      .#.######.########.#
      .###.#######.####.#.
      #####.##.#.##.###.##
      ..#####..#.#########
      ####################
      #.####....###.#.#.##
      ##.#################
      #####.##.###..####..
      ..######..##.#######
      ####.##.####...##..#
      .#####..#.######.###
      ##...#.##########...
      #.##########.#######
      .####.#.###.###.#.##
      ....##.##.###..#####
      .#.#.###########.###
      #.#.#.#####.####.###
      ###.##.####.##.#..##
     INPUT

     ex2 = <<~INPUT
     .#....#####...#..
     ##...##.#####..##
     ##...#...#.#####.
     ..#.....#...###..
     ..#.#.....#....##
     INPUT

     @input = File.readlines(__dir__ + "/input.txt", chomp:true).map(&:chars)
     @ex1 = ex1.split("\n").map(&:chars)
     @ex2 = ex2.split("\n").map(&:chars)
   end

   def test_part_one
     asteroids = Asteroids.new(@input)
     assert_equal 296, asteroids.line_of_sight
   end

   def test_lasertargets_1
     asteroids = Asteroids.new(@ex2)
     assert_equal "[8,3]", asteroids.station.to_s 
   end

   def test_lasertragets_2
     asteroids = Asteroids.new(@ex2)
     targets = asteroids.targets
     sorted = targets.sort
     assert_equal "[8,1]", sorted[0].print
     assert_equal "[9,0]", sorted[1].print
   end

   def test_laser_3
     asteroids = Asteroids.new(@ex1)
     targets = asteroids.targets
     sorted = targets.sort
     #puts sorted
     assert_equal sorted[0].print, "[11,12]"
     assert_equal sorted[1].print,   "[12,1]"
     assert_equal sorted[2].print,   "[12,2]"
     assert_equal sorted[9].print,  "[12,8]"
     assert_equal sorted[19].print,  "[16,0]"
     assert_equal sorted[49].print,  "[16,9]"
     assert_equal sorted[99].print, "[10,16]"
     assert_equal sorted[198].print, "[9,6]"
     assert_equal sorted[199].print, "[8,2]"
     assert_equal sorted[200].print, "[10,9]"
     assert_equal sorted[298].print, "[11,1]"
   end

 end
