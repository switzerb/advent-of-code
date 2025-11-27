#!/usr/bin/env ruby

  begin
    require 'minitest'
    require 'minitest/autorun'
    require_relative 'day_01.rb'
  rescue Gem::LoadError => e
    puts "\nError:\n#{e.backtrace.first} #{e.message}"
    puts DATA.read
    exit 1
  end

class SolverTest < Minitest::Test

  def test_to_fuel
    assert_equal 2, 12.to_fuel
  end

  def test_to_fuel_2
    assert_equal 2, 14.to_fuel
  end

  def test_to_fuel_3
    assert_equal 654, 1969.to_fuel
  end

  def test_to_fuel_4
    assert_equal 33583, 100756.to_fuel
  end

  def test_fuels_1
    assert_equal 966, 1969.to_fuels
  end

  def test_fuels_2
    assert_equal 50346, 100756.to_fuels
  end

  def test_part_1
    lines = File.readlines(__dir__ + "/input.txt", chomp: true)
    input = lines.map(&:to_i)
    rocket = Rocket.new(input)
   assert_equal 3550236, rocket.part_one
  end

  def test_part_2
    lines = File.readlines(__dir__ + "/input.txt", chomp: true)
    input = lines.map(&:to_i)
    rocket = Rocket.new(input)
    assert_equal 5322455, rocket.part_two
  end

end
