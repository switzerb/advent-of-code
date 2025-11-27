#!/usr/bin/env ruby

 class SIF
  
   def initialize(input, width, height)
     @data = input
     @width = width
     @height = height
     @layers = []
   end

   def process
     layer = 0
     i = 0
     @data.each_slice(@width) do |a|
       @layers[layer] = [] if @layers[layer].nil?
       @layers[layer].concat(a)
       layer += 1 if i % @height == @height - 1
       i += 1
     end
     @layers
   end

   def part_one
    process
    min = 1000
    idx = 0

    @layers.each_with_index do |layer, index|
      zeros = layer.select {|item| item == 0 }
      if zeros.length < min
        min = zeros.length
        idx = index
      end
    end

    ones = @layers[idx].select{|item| item == 1}
    twos = @layers[idx].select{|item| item == 2}

    ones.length * twos.length
   end

   def get_value(layer)
     layer.each do |p|
      return "#" if p == 1
      return "." if p == 0
     end
   end

   def part_two
     process
     image = Array.new
     line = @width * @height

     (0..line).each do |c|
       stack = Array.new
       @layers.each_with_index do |layer,r|
         stack << @layers[r][c]
       end
       image[c] = get_value(stack)
     end
     image
   end

 end
