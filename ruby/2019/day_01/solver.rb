#!/usr/bin/env ruby

require_relative 'day_01'

lines = File.readlines('input.txt', chomp: true)
input = lines.map(&:to_i)

rocket = Rocket.new(input)

puts "Part One:" + rocket.part_one.to_s
puts "Part Two:" + rocket.part_two.to_s
