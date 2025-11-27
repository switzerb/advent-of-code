#!/usr/bin/env ruby
class Integer
  def digits
    Enumerator.new do |x|
      to_s.chars.map{|c| x << c.to_i }
    end
  end
end

class Password

  def validLength?(password)
    password.length == 6
  end

  def multiple?(i)
    num.digits.each do |d|
      p digit
    end    
  end

  def ascending?(i)
 
  end

  def validPassword?(i)
    i.is_a? Integer && multiple?(i) && ascending?(i) && validLength?(i)
  end
 
 end
