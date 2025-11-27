#!/usr/bin/env ruby
require_relative '../lib/computer'

file = File.open(__dir__ + '/input.txt')
input = file.read
program = input.split(",").map(&:to_i)

$debug = false

#test = Computer.new
#test.load([109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99])
#test.run
#pp test.get_stdout

nine = Computer.new
nine.load(program)
nine.set_in(2)
nine.run
pp nine.get_stdout
