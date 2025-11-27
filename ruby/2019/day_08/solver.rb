require_relative 'day_08'

file = File.open(__dir__ + "/input.txt")
input = file.read
input = input.chars
data = input.map(&:to_i)

# puts input

sif = SIF.new(data, 25, 6)
puts sif.part_one
image = sif.part_two

image.each_slice(25) do |line|
  puts line.join
end






