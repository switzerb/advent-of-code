#!/usr/bin/env ruby

  begin
    gem 'minitest', '>=5.0.0'
    require 'minitest/autorun'
    require_relative 'day_11.rb'
  rescue Gem::LoadError => e
    puts "\nError:\n#{e.backtrace.first} #{e.message}"
    puts DATA.read
    exit 1
  end

 class Day11Test < Minitest::Test
   def setup
     file = File.open(__dir__+"/input.txt")
     @input = file.read
     @robot = PaintRobot.new
     @robot.load(@input)
   end

   def test_direction_1
     assert_equal @robot.step(:east).to_s, "[1,0]" 
     assert_equal @robot.here.to_s, "[1,0]"
   end

   def test_direction_2
     assert_equal @robot.step(:west).to_s, "[-1,0]"
     assert_equal @robot.here.to_s, "[-1,0]"
   end

   def test_direction_3
     assert_equal @robot.step(:north).to_s, "[0,-1]"
     assert_equal @robot.here.to_s, "[0,-1]"
   end

   def test_direction_4
     assert_equal @robot.step(:south).to_s, "[0,1]"
     assert_equal @robot.here.to_s, "[0,1]"
   end

   def test_hull_1
     @robot.paint(1)
     @robot.paint(1)
     assert_equal @robot.hull.size, 1
   end

   def test_photo_1
     @robot.here= Point.new(1,3)
     @robot.hull[Point.new(1,3)] = 1
     assert_equal [1], @robot.photo
   end

   def test_turn_and_step_left
     @robot.turn_and_step(0)
     assert_equal @robot.here.to_s, "[-1,0]"
     assert_equal @robot.dir, :west
   end

   def test_turn_and_step_right
     @robot.turn_and_step(1)
     assert_equal @robot.here.to_s, "[1,0]"
     assert_equal @robot.dir, :east
   end

   def test_turn_step_complex
     @robot.paint(1)
     @robot.turn_and_step(0)
     @robot.paint(0)
     @robot.turn_and_step(0)
     @robot.paint(1)
     @robot.turn_and_step(0)
     @robot.paint(1)
     @robot.turn_and_step(0)
     @robot.paint(0)
     @robot.turn_and_step(1)
     @robot.paint(1)
     @robot.turn_and_step(0)
     @robot.paint(1)
     @robot.turn_and_step(0)

     assert_equal @robot.here.to_s, "[0,-1]"
     assert_equal @robot.hull[@robot.here], 0
     assert_equal @robot.hull.size, 6
   end

   def test_go
     @robot.go
     assert_equal @robot.hull.size, 2211
   end

   def test_visuals
     robot = PaintRobot.new(1)
     robot.load(@input)
     robot.go
     print robot.show_hull
   end

 end
