## --- Day 2: 1202 Program Alarm ---  ##
# https://adventofcode.com/2019/day/2  #
 class Intcode

  def initialize
  end

  def load(program)
    @program = program.dup
    @reset = program.dup
  end

  def run_with(noun, verb)
    @program[1] = noun
    @program[2] = verb
    run
    @program[0]
  end

  def run
    @count = 0
    until @count >= @program.length do
     opcodes = @program.slice(@count, 4)
     code = opcodes[0]
     p1 = opcodes[1]
     p2 = opcodes[2]
     w = opcodes[3] 
     
     return if code == 99
     # raise "" unless (code == 1 || code == 2 || code == 99)

     if code == 1
      value = @program[p1] + @program[p2]
     elsif code == 2
      value = @program[p1] * @program[p2]
     end
    
     @program[w] = value
     @count += 4
    end
  end

  def finish
    @program
  end

end
