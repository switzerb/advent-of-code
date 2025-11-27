require_relative 'day_06.rb'
require 'rgl/adjacency'
require 'rgl/traversal'
require 'rgl/dot'

orbits = RGL::DirectedAdjacencyGraph.new

orbits.add_edge "COM","B"
orbits.add_edge "B","C"
orbits.add_edge "C","D"
orbits.add_edge "D","E"
orbits.add_edge "E","F"
orbits.add_edge "B","G"
orbits.add_edge "G","H"
orbits.add_edge "D","I"
orbits.add_edge "E","J"
orbits.add_edge "J","K"
orbits.add_edge "K","L"

# orbits.write_to_graphic_file('jpg')

#def count_orbits(graph)
#  graph.dfs_iterator.to_a
#end

#puts count_orbits(orbits)

require 'set'

map = File.open('./input.txt').read.split("\n")

orbits = {}

map.each do |m|
  o = m.split(')')
  planet = o[0]
  orbiter = o[1]
  orbits[orbiter] = Set.new([planet, orbits[planet].to_a].flatten.compact)

  # Could replace this with an undirected graph class using recursive linking to
  # avoid the nested loop building up a set.
  orbits.each do |orb, p|
    if p.include?(orbiter)
      orbits[orb].merge([planet, orbits[planet].to_a].flatten.compact)
    end
  end
end

# Part 1:
puts(orbits.values.inject(0) { |sum, p| sum += p.length })

# Part 2:
puts((orbits['YOU'] - orbits['SAN']).length + (orbits['SAN'] - orbits['YOU']).length)
