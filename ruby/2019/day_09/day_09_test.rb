#!/usr/bin/env ruby

  begin
    gem 'minitest', '>=5.0.0'
    require 'minitest/autorun'
    require_relative 'day_09.rb'
  rescue Gem::LoadError => e
    puts "\nError:\n#{e.backtrace.first} #{e.message}"
    puts DATA.read
    exit 1
  end

 class Day09Test < Minitest::Test
   def setup
     @day = Solver.new
   end

   def test_hello
     assert_equal "hello, world!", @day.hello
   end
 end
