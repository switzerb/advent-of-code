class Integer
  def to_fuel
    m = (self / 3).floor - 2
  end

  def to_fuels
   m = self.to_fuel
   return 0 if m <= 0
   m.to_fuels + m
  end
end

class Rocket

  def initialize(input)
    @input = input
  end

  def part_one
    fuels = @input.map(&:to_fuel)
    fuels.sum
  end

  def part_two
    fuels = @input.map(&:to_fuels)
    fuels.sum
  end

end
