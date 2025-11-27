#!/usr/bin/env ruby

  begin
    gem 'minitest', '>=5.0.0'
    require 'minitest/autorun'
    require_relative '../lib/computer'
    require_relative 'amplifier'
  rescue Gem::LoadError => e
    puts "\nError:\n#{e.backtrace.first} #{e.message}"
    puts DATA.read
    exit 1
  end

 class Day07Test < Minitest::Test
   def setup
    file = File.open(__dir__ + "/input.txt")
    program = file.read
    program = program.split(",")
    @program = program.map(&:to_i)
   end

   def test_day7_part_one
      max = 0
      sequences = [0,1,2,3,4].permutation.to_a

      sequences.each do |seq|
        amp = Amplifier.new seq
        amp.load(@program)
        amp.run
        signal = amp.to_thrusters

        if signal > max
          max = signal
        end
      end
     assert_equal 844468, max
   end

   def test_day7_part_two
      max = 0
      sequences = [5,6,7,8,9].permutation.to_a

      sequences.each do |seq|
        amp = Amplifier.new seq
        amp.load(@program)
        amp.run
        signal = amp.to_thrusters

        if signal > max
          max = signal
        end
      end
      assert_equal 4215746, max
   end

   def test_day7_amp_1
    phases = [9,8,7,6,5]
    amp = Amplifier.new phases
    amp.load([3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5])
    amp.run
    assert_equal 139629729, amp.to_thrusters
   end

   def test_day7_amp_2
    phases = [9,7,8,5,6]
    amp = Amplifier.new phases
    amp.load([3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10])
    amp.run
    assert_equal 18216, amp.to_thrusters
   end
 end
