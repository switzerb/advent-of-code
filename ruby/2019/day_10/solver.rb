require_relative 'day_10'
require 'set'

input = File.readlines('input.txt', chomp: true).map(&:chars)

asteroids = Asteroids.new(input)
puts "Part One: " + asteroids.line_of_sight.to_s
# puts asteroids.station

targets = asteroids.targets
sorted = targets.sort
puts "Part Two:" + sorted[199].print
