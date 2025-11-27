#!/usr/bin/env ruby

 class OrbitMap

   def initialize
     @orbits = Hash.new
     @orbits["COM"] = ""
     @orbits["B"] = "COM"
     @orbits["C"] = "B"
     @orbits["D"] = "C"
     @orbits["G"] = "B"
   end

   def direct(key)
     @orbits[key]
   end

   def indirect(key)
   end
 end
