#!/usr/bin/env ruby

  begin
    gem 'minitest', '>=5.0.0'
    require 'minitest/autorun'
    require_relative 'day_08.rb'
  rescue Gem::LoadError => e
    puts "\nError:\n#{e.backtrace.first} #{e.message}"
    puts DATA.read
    exit 1
  end

 class Day08Test < Minitest::Test
   def setup
     @sif = SIF.new([0,2,2,2,1,1,2,2,2,2,1,2,0,0,0,0],2,2)
   end

   def test_part_one
     file = File.open(__dir__ + "/input.txt")
     input = file.read
     input = input.chars
     data = input.map(&:to_i)
     sif = SIF.new(data, 25, 6)
     assert_equal 1820, sif.part_one
   end

   def test_process
     assert_equal [[0,2,2,2],[1,1,2,2],[2,2,1,2],[0,0,0,0]], @sif.process
   end

   def test_get_value_black
     assert_equal ".", @sif.get_value([0,1,2,0])
   end

   def test_get_value_white
     assert_equal "#", @sif.get_value([2,1,2,0])
   end

   def test_part_two
     @sif.part_two
   end

 end
