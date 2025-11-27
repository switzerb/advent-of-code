#!/usr/bin/env ruby

  begin
    gem 'minitest', '>=5.0.0'
    require 'minitest/autorun'
    require_relative 'day_14.rb'
  rescue Gem::LoadError => e
    puts "\nError:\n#{e.backtrace.first} #{e.message}"
    puts DATA.read
    exit 1
  end

 class Day14Test < Minitest::Test
   def test_example1
     
    input = ["10 ORE => 10 A", "1 ORE => 1 B", "7 A, 1 B => 1 C", "7 A, 1 C => 1 D", "7 A, 1 D => 1 E", "7 A, 1 E => 1 FUEL"]

    factory = Nanofactory::Reactor.new
    factory.load(input)
    assert_equal 31, factory.minimum_ore
   end

   def test_example2
     input = ["9 ORE => 2 A","8 ORE => 3 B","7 ORE => 5 C","3 A, 4 B => 1 AB","5 B, 7 C => 1 BC","4 C, 1 A => 1 CA","2 AB, 3 BC, 4 CA => 1 FUEL"]
     factory = Nanofactory::Reactor.new
     factory.load(input)
     assert_equal 165, factory.minimum_ore
   end

 end
