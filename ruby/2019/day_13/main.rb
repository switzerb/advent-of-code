#!/usr/bin/env ruby

require_relative 'day_13.rb'

file = File.open(__dir__ + "/input.txt")
input = file.read
program = input.split(",").map(&:to_i)
breakout = Arcade.new(program)

breakout.freeplay

