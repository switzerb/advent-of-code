#!/usr/bin/env ruby

class Integer

  def larger_group?(password)
    return true if password.match(/(.)\1{2,}/)
  end

  def multiple?(password)
   return true if password.match(/(.)\1/)
  end

  def is_password?
    s = self.to_s
    return false unless multiple? s
    s.each_char.inject do |prev, curr|
      return false if curr < prev
      curr
    end
    true
  end
end
codes = (197487..673251).select(&:is_password?)
puts "Part One: #{codes.length}"
