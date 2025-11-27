#!/usr/bin/env ruby
require 'set'

 class Asteroids
    attr_accessor :asteroids, :station

    PI = Math::PI

   def initialize(input)
     @asteroids = Array.new

     input.map.with_index do |r,i|
       r.map.with_index do |c,j|
         a = Asteroid.new(j,i)
         @asteroids << a if c == "#"
       end
       line_of_sight
     end
   end

   def theta(asteroid, station)
     Math.atan2(asteroid.y - station.y, asteroid.x - station.x)
   end

   def r(asteroid, station)
     Math.hypot(asteroid.y - station.y, asteroid.x - station.x)
   end

   def line_of_sight
     hist = Hash.new(0)

     @asteroids.map do |a|
       thetas = Set.new
       @asteroids.map { |o| thetas << theta(a,o) if o != a }
       hist[a] = thetas.length
     end

     max = 0

     hist.each do |k,v|
       if v > max	
         max = v
         @station = k
       end
     end
     max
   end

   def targets
     targets = @asteroids
       .select { |a| a != @station }
       .map do |a|
        l = LaserTargets.new
        offset = Asteroid.new(a.x - @station.x, a.y - @station.y)
        l.asteroid=(a)
        l.theta=(theta(a,@station))
        l.r=(r(a,@station))
        l
      end

     targets.group_by { |t| t.theta }.each do |theta,lined|
        lined.sort { |a,b| a.r - b.r }.each_with_index do |line, i|
          line.order = i
        end
     end

     targets.map do |t|
       if t.theta > -PI && t.theta < -PI/2
         t.theta= t.theta + (PI * 2)
       end
     end
     targets
   end
   
 end

 class LaserTargets
   include Comparable
   attr_accessor :a, :theta, :r, :order, :quadrant
   alias_method :asteroid=, :a=

   def<=>(o)
     return nil unless o.a.is_a? Asteroid
     return 0 if self.a == o.a
     
     return 1 if  self.order > o.order
     return -1 if self.order < o.order
     
     return 1 if self.theta > o.theta
     return -1 if self.theta < o.theta

     return 0
   end

   def to_s
     "coords: [#{a.x}, #{a.y}], theta: #{theta}, order: #{order}, Q: #{quadrant}"
   end

   def print
     "[#{a.x},#{a.y}]"
   end
 end


 class Asteroid
   attr_accessor :x, :y
   alias_method :==, :eql?

   def initialize(x,y)
     @x = x
     @y = y
   end

   def to_s
    "[#{@x},#{@y}]"
   end

   def hash
     @x + @y
   end

   def eql?(other)
     other.class == self.class && other.state == self.state
   end
   
   def state
     self.instance_variables.map { |variable| self.instance_variable_get variable }
   end

 end
