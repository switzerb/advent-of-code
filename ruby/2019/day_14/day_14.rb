#!/usr/bin/env ruby
require 'pry'

module Nanofactory

  class Reactor
    attr_accessor :reactions, :base

    def initialize
      @base = []
    end

    def load(input)
      @reactions = Hash.new
      input.each do |line|
        data = line.split("=>").map(&:strip)
        output = key_parse(data[1])
        input = value_parse(data[0])
        @reactions[output] = input
      end
    end

    def key_parse(key)
      q, c = key.split(" ")
      Unit.new(c,q.to_i)
    end

    def value_parse(value)
      splits = value.split(",")
      splits.map do |unit|
        q,c = unit.split(" ")
        Unit.new(c,q.to_i)
      end
    end

    def chain(input)
      input.each do |unit|
        key = Unit.new(unit.chemical)
        value = @reactions.fetch(key)
        if value.length == 1
          @base << unit
        else
          chain(value)
        end
      end
    end

    def minimum_ore
      fuel = Unit.new("FUEL")
      start = @reactions.fetch(fuel)
      chain(start)
      collapse
      calculate_materials
    end

    def calculate_materials
      total = 0
      @base.each do |unit|
        requires = @reactions.fetch(Unit.new(unit.chemical))
        produces = unit.quantity
        q = requires[0].quantity
        d,r = unit.quantity.divmod(q)
        total += d * q
        total += q if r > 0
      end
      binding.pry
      total
    end

    def collapse
      temp = []
      @base.each do |unit|
        if temp.include? unit
          idx = temp.index { |u| u == unit }
          @base[idx].quantity += unit.quantity 
        else
          temp << unit
        end
      end
      @base = temp
    end

  end

  class Unit
    attr_accessor :chemical, :quantity

    def initialize(chemical,quantity = 1)
      @chemical = chemical
      @quantity = quantity
    end

    def ==(other)
      @chemical == other.chemical
    end

    def eql?(other)
      self == other
    end

    def hash
      @chemical.to_i
    end
  end

end
