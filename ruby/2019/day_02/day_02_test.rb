  begin
    require 'minitest'
    require 'minitest/autorun'
    require_relative '../lib/computer'
  rescue Gem::LoadError => e
    puts "\nError:\n#{e.backtrace.first} #{e.message}"
    puts DATA.read
    exit 1
  end

 class Day02Test < Minitest::Test
   def setup
    file = File.open(__dir__ + "/input.txt")
    @input = file.read
    @input = @input.split(",")
    @input = @input.map(&:to_i)
    @computer = Computer.new
    @computer.load(@input)
   end

  def test_part_one
    assert_equal 3790645, @computer.run_with(12,2)
   end

  def test_part_two
    n = 0
    v = 0
    (0..99).each do |noun|         
       (0..99).each do |verb|        
         @computer.load(@input)
         r = @computer.run_with(noun,verb)    
         if r == 19690720
           n = noun
           v = verb
         end         
      end      
    end
    assert_equal 6577, 100 * n + v
  end

end
