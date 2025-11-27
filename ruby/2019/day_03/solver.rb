require_relative 'day_03'

file = File.open('input.txt')
input = file.read
input = input.split("\n")

wireA = input[0]
wireB = input[1]

wireA = wireA.split(",")
wireB = wireB.split(",")

fueler = Fueler.new
fueler.draw_paths(wireA, "A")
fueler.draw_paths(wireB, "B")
puts fueler.intersections.to_s
puts fueler.manhattan_distance

puts fueler.min_distance
