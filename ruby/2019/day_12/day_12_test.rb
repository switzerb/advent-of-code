#!/usr/bin/env ruby

  begin
    gem 'minitest', '>=5.0.0'
    require 'minitest/autorun'
    require_relative 'day_12.rb'
  rescue Gem::LoadError => e
    puts "\nError:\n#{e.backtrace.first} #{e.message}"
    puts DATA.read
    exit 1
  end

 class Day12Test < Minitest::Test
   def setup
     @io = Moon.new("Io", [4,12,13])
     @europa = Moon.new("Europa",[-9,14,-3])
     @ganymede = Moon.new("Ganymede", [-7,-1,2])
     @callisto = Moon.new("Callisto", [-11,17,-1])
   end

   def test_moon_initialize
     moon = Moon.new("Europa", [1,2,3])
     puts moon
   end
   
   def test_apply_gravity
     moon = Moon.new("Ganymede", [3,4,5])
     pair = Moon.new("Callisto", [5,4,2])
     moon.apply_gravity(pair)
     pair.apply_gravity(moon)
     assert_equal "Vector[1, 0, -1]", moon.vel.to_s
     assert_equal "Vector[-1, 0, 1]", pair.vel.to_s
   end

   def test_apply_velocity
     moon = Moon.new("Europa", [1,2,3])
     moon.vel= Vector.elements([-2,0,3])
     moon.apply_velocity
     assert_equal "Vector[-1, 2, 6]", moon.pos.to_s
   end

   def test_potential_energy
     moon = Moon.new("Europa", [-2,7,1])
     moon.vel= Vector.elements([-2,0,3])
     assert_equal 10, moon.potential
   end
   
   def test_total_energy
     moon = Moon.new("Europa", [-2,-1,3])
     moon.vel= Vector.elements([-3,2,1])
     assert_equal 36, moon.total_energy
   end

   def test_system_step_1
     io = Moon.new("Io", [-1,0,2])
     europa = Moon.new("Europa",[2,-10,-7])
     ganymede = Moon.new("Ganymede", [4,-8,8])
     callisto = Moon.new("Callisto", [3,5,-1])
     moons = [io, europa, ganymede, callisto]
     system = System.new(moons)
     system.step
     assert_equal "Io pos=<x=2, y=-1, z=1>, vel=<x=3, y=-1, z=-1>", system.moons[0].to_s
     assert_equal "Europa pos=<x=3, y=-7, z=-4>, vel=<x=1, y=3, z=3>", system.moons[1].to_s
     assert_equal "Ganymede pos=<x=1, y=-7, z=5>, vel=<x=-3, y=1, z=-3>", system.moons[2].to_s
     assert_equal "Callisto pos=<x=2, y=2, z=0>, vel=<x=-1, y=-3, z=1>", system.moons[3].to_s
   end

   def test_system_step_10
     io = Moon.new("Io", [-1,0,2])
     europa = Moon.new("Europa",[2,-10,-7])
     ganymede = Moon.new("Ganymede", [4,-8,8])
     callisto = Moon.new("Callisto", [3,5,-1])
     moons = [io, europa, ganymede, callisto]
     system = System.new(moons)
     10.times.each do
       system.step
     end
     assert_equal "Io pos=<x=2, y=1, z=-3>, vel=<x=-3, y=-2, z=1>", system.moons[0].to_s
     assert_equal "Europa pos=<x=1, y=-8, z=0>, vel=<x=-1, y=1, z=3>", system.moons[1].to_s
     assert_equal "Ganymede pos=<x=3, y=-6, z=1>, vel=<x=3, y=2, z=-3>", system.moons[2].to_s
     assert_equal "Callisto pos=<x=2, y=0, z=4>, vel=<x=1, y=-1, z=-1>", system.moons[3].to_s
     
     assert_equal 179, system.total_energy
   end

   def test_part_one
     moons = [@io, @europa, @ganymede, @callisto]
     system = System.new(moons)
     1000.times.each do
       system.step
     end
     assert_equal 5350, system.total_energy
   end
 end
