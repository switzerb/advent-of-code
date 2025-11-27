require_relative '../lib/computer'
require 'fiber'

class Amplifier

  def initialize(phases)
    @amps = Array.new
    @fibers = Array.new
    @phases = phases
    @final = 0
  end

  def load(program)
    @program = program.dup
    init
  end

  def to_thrusters
    @amps[4].get_out
  end

  def init
    5.times do |i|
      @amps[i] = Computer.new
      @amps[i].load(@program)
      @amps[i].set_in(@phases[i])
      @fibers[i] = Fiber.new { @amps[i].run }
    end

    @amps[0].set_in(0)
  end

  def some_alive?
    @fibers.each do |f|
      return true if f.alive?
    end
    false
  end

  def run
    @phases.each do |phase|
      while some_alive?
        (0...5).each do |i|
          prev = (i - 1) % @amps.length
          signal = @amps[prev].get_out
          @amps[i].set_in(signal) unless signal.nil?
          @fibers[i].resume
        end
      end
    end
  end

end
