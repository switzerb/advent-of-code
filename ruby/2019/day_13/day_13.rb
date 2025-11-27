#!/usr/bin/env ruby
require_relative '../lib/computer.rb'
require_relative '../lib/point.rb'
require 'pry'

class Arcade
  attr_accessor :computer, :stream, :screen

  EMPTY = 0
  WALL = 1
  BLOCK = 2
  PADDLE = 3
  BALL = 4

  def initialize(program)
    @computer = Computer.new
    @computer.load(program)
    @screen = Hash.new(0)
    @score = 0
  end

  def freeplay
    @computer.set_address(0,2)
    play
  end

  def play
    game = Fiber.new { @computer.run }
    game.resume
    while game.alive?
      tick
      puts draw
      # move = mover(gets.chomp)
      @computer.set_in move_paddle
      game.resume
    end
    tick
    puts "FINAL" + draw
  end

  def move_paddle
    bx = @ball.x
    px = @paddle.x
    
    return 1 if bx > px
    return -1 if bx < px
    return 0
  end

  def mover(input)
    return -1 if input == "a"
    return 1 if input == "d"
    return 0
  end

  def tick
    output = @computer.stdout
    squares = output.each_slice(3)
    squares.each do |square|
      x,y,v = square
      if x == -1 && y == 0
        @score = v
      else
        @screen[Point.new(x,y)] = v
      end
    end
  end

  def block_count
    blocks = @screen.values
    filtered = blocks.select { |value| value == 2 }
    filtered.length
  end

  def draw
    level = 0
    p = "\n\n"
    p += "SCORE: "
    p += @score.to_s
    p += "\n\n"
    @screen.each do |k,type|
      if k.y > level
        p += "\n"
        level = k.y
      end
      case type
      when 0 then p += " "
      when 1 then p += "#"
      when 2 then p += "="
      when 3 
        p += "_"
        @paddle = k
      when 4 
        p += "O"
        @ball = k
      else p += "~"
      end
    end
    p
  end
end
