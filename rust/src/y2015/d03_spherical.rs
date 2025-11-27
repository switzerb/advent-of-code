use crate::utils::point::{Dir, Point};
use itertools::Itertools;
use std::collections::HashMap;

const ORIGIN: Point = Point(0, 0);

fn step(curr: Point, c: char) -> Point {
    match c {
        '^' => curr.step(Dir::North, 1),
        'v' => curr.step(Dir::South, 1),
        '>' => curr.step(Dir::East, 1),
        '<' => curr.step(Dir::West, 1),
        _ => panic!("{:?}", c),
    }
}

pub fn part_one(input: &String) -> i32 {
    let mut hist: HashMap<Point, i32> = HashMap::new();
    let mut curr: Point = ORIGIN;
    hist.insert(ORIGIN, 1);
    for i in input.trim().chars() {
        curr = step(curr, i);
        let count = hist.entry(curr.clone()).or_insert(0);
        *count += 1;
    }
    hist.keys().len() as i32
}
pub fn part_two(input: &String) -> i32 {
    let mut hist: HashMap<Point, i32> = HashMap::new();
    let mut santa: Point = ORIGIN;
    let mut robo: Point = ORIGIN;
    hist.insert(ORIGIN, 1);
    for (s, r) in input.chars().tuples() {
        santa = step(santa, s);
        let s_count = hist.entry(santa.clone()).or_insert(0);
        *s_count += 1;

        robo = step(robo, r);
        let r_count = hist.entry(robo.clone()).or_insert(0);
        *r_count += 1
    }
    hist.keys().len() as i32
}

#[cfg(test)]
mod tests {
    use super::{part_one, part_two};

    #[test]
    fn test_one() {
        let example = String::from(">");
        assert_eq!(part_one(&example), 2);
    }

    #[test]
    fn test_one_1() {
        let example = String::from("^>v<");
        assert_eq!(part_one(&example), 4);
    }

    #[test]
    fn test_one_2() {
        let example = String::from("^v^v^v^v^v");
        assert_eq!(part_one(&example), 2);
    }

    #[test]
    fn test_two_1() {
        let example = String::from("^v");
        assert_eq!(part_two(&example), 3);
    }

    #[test]
    fn test_two_2() {
        let example = String::from("^>v<");
        assert_eq!(part_two(&example), 3);
    }

    #[test]
    fn test_two_3() {
        let example = String::from("^v^v^v^v^v");
        assert_eq!(part_two(&example), 11);
    }
}
