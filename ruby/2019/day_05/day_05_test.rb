#!/usr/bin/env ruby

  begin
    gem 'minitest', '>=5.0.0'
    require 'minitest/autorun'
    require_relative '../lib/computer'
  rescue Gem::LoadError => e
    puts "\nError:\n#{e.backtrace.first} #{e.message}"
    puts DATA.read
    exit 1
  end

 class Day05Test < Minitest::Test
   def test_day5_part_one
     c = Computer.new
     c.set_in(1)
     file = File.open(__dir__ + "/input.txt")
     input = file.read
     input = input.split(",")
     input = input.map(&:to_i)
     c.load(input)
     c.run
     assert_equal 11933517, c.get_out
  end

  def test_day5_part_two
     c = Computer.new
     c.set_in(5)
     file = File.open(__dir__ + "/input.txt")
     input = file.read
     input = input.split(",")
     input = input.map(&:to_i)
     c.load(input)
     c.run
     assert_equal 10428568, c.get_out
  end
 end
