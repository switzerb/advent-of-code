require_relative '../lib/computer'
require 'fiber'
require 'pry'
require_relative 'amplifier'

amp_array = [0,1,2,3,4]
feedback_array = [5,6,7,8,9]
for_amps = amp_array.permutation.to_a
for_feedback = [5,6,7,8,9].permutation.to_a

file = File.open(__dir__ + "/input.txt")
program = file.read
program = program.split(",").map(&:to_i)

max = 0
$debug = false

for_feedback.each do |seq|
  amp = Amplifier.new seq
  amp.load(program)
  amp.run
  boost = amp.to_thrusters

  if boost > max
    max = boost
  end
end

puts "part two: " + max.to_s

max = 0

for_amps.each do |seq|
    amp = Amplifier.new seq
    amp.load(program)
    amp.run
    signal = amp.to_thrusters

  if signal > max
    max = signal
  end
end

puts "part one: " + max.to_s

