require 'pry'
require_relative 'day_14'

# reduce the chain to have only unique chemicals
# do some math to get totals on those unique chemicals
# final ore count


input = ["10 ORE => 10 A", "1 ORE => 1 B", "7 A, 1 B => 1 C", "7 A, 1 C => 1 D", "7 A, 1 D => 1 E", "7 A, 1 E => 1 FUEL"]

factory = Nanofactory::Reactor.new
factory.load(input)
pp factory.minimum_ore

