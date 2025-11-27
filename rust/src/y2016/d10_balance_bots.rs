use regex::Regex;
use std::collections::{HashMap, VecDeque};

#[derive(Debug)]
enum ToType {
    Output(i32),
    Bot(i32),
}

impl ToType {
    fn from(tipo: &str, id: i32) -> ToType {
        match tipo {
            "bot" => ToType::Bot(id),
            "output" => ToType::Output(id),
            _ => panic!("don't know what this is"),
        }
    }
}

#[derive(Debug)]
struct Bot {
    id: i32,
    low: ToType,
    high: ToType,
}

fn to_int(s: &str) -> i32 {
    s.trim().parse::<i32>().expect("must be numeric")
}

fn parse(input: &String) -> (VecDeque<(i32, i32)>, HashMap<i32, Bot>) {
    let mut queue: VecDeque<(i32, i32)> = VecDeque::new();
    let mut bots: HashMap<i32, Bot> = HashMap::new();
    let re_values = Regex::new(r"value (?<value>\d+) goes to bot (?<id>\d+)").unwrap();
    let re_bots =
        Regex::new(r"bot (?<id>\d+) gives low to (?<ltype>output|bot) (?<lid>\d+) and high to (?<htype>output|bot) (?<hid>\d+)")
            .unwrap();

    for line in input.trim().lines() {
        if let Some(caps) = re_values.captures(line) {
            queue.push_back((to_int(&caps["id"]), to_int(&caps["value"])));
        }

        if let Some(caps) = re_bots.captures(line) {
            let id = to_int(&caps["id"]);
            let low = ToType::from(&caps["ltype"], to_int(&caps["lid"]));
            let high = ToType::from(&caps["htype"], to_int(&caps["hid"]));
            let bot = Bot { id, low, high };
            bots.insert(id, bot);
        }
    }
    (queue, bots)
}

pub fn part_one(input: &String) -> i32 {
    helper(input, 61, 17).0
}

fn ranked(n: i32, m: i32) -> (i32, i32) {
    if n > m {
        (n, m)
    } else {
        (m, n)
    }
}

pub fn helper(input: &String, value_one: i32, value_two: i32) -> (i32, i32) {
    let (mut queue, bots) = parse(input);
    let mut output: HashMap<i32, i32> = HashMap::new();
    let mut special_bot: i32 = 0;

    // this represents the topological sort
    let mut bot_values: HashMap<i32, i32> = HashMap::new();
    let (high_target, low_target) = ranked(value_one, value_two);

    while let Some((id, second)) = queue.pop_front() {
        if let Some(&first) = bot_values.get(&id) {
            if let Some(bot) = bots.get(&id) {
                // we have two values to distribute
                let (high_value, low_value) = ranked(first, second);

                if high_target == high_value && low_target == low_value {
                    special_bot = bot.id;
                }

                match bot.high {
                    ToType::Output(id) => {
                        output.insert(id, high_value);
                    }
                    ToType::Bot(id) => {
                        queue.push_back((id, high_value));
                    }
                }
                match bot.low {
                    ToType::Output(id) => {
                        output.insert(id, low_value);
                    }
                    ToType::Bot(id) => {
                        queue.push_back((id, low_value));
                    }
                }
            }
        } else {
            bot_values.insert(id, second);
        }
    }
    let zero = output.get(&0).copied().unwrap_or(1);
    let one = output.get(&1).copied().unwrap_or(1);
    let two = output.get(&2).copied().unwrap_or(1);

    (special_bot, zero * one * two)
}

pub fn part_two(input: &String) -> i32 {
    helper(input, 0, 0).1
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_one() {
        let example = String::from(
            "value 5 goes to bot 2
bot 2 gives low to bot 1 and high to bot 0
value 3 goes to bot 1
bot 1 gives low to output 1 and high to bot 0
bot 0 gives low to output 2 and high to output 0
value 2 goes to bot 2",
        );
        assert_eq!(helper(&example, 5, 2).0, 2);
    }
}
