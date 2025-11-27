#!/usr/bin/env ruby

require_relative '../lib/computer'

file = File.open("input.txt")
input = file.read
input = input.split(",")
input = input.map(&:to_i)

program = Computer.new
program.load(input)
puts "part one: #{program.run_with(12,2)}"

(0..99).each do |p1|         
   (0..99).each do |p2|        
     program.load(input)
     r = program.run_with(p1,p2)    
     if r == 19690720
      puts  "part two: #{100 * p1 + p2}"
      exit
     end                      
  end                   
end
