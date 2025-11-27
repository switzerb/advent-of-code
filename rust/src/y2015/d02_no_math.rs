/**
https://adventofcode.com/2015/day/2
--- Day 2: I Was Told There Would Be No Math ---
*/
#[derive(Debug)]
struct Present {
    l: i32,
    w: i32,
    h: i32,
}

impl Present {
    fn smallest_sides(&self) -> (&i32, &i32) {
        let Present {l,w,h} = self;
        if w >= h && w >= l { return (h, l) }
        if l >= h && l >= w { return (h, w) }
        (w, l)
    }

    fn area(&self) -> i32 {
        let Present {l,w,h} = self;
        (2 * l * w) + (2 * l * h) + (2 * w * h)
    }

    fn smallest_area(&self) -> i32 {
        let (a, b) = self.smallest_sides();
        a * b
    }

    fn smallest_perimeter(&self) -> i32 {
        let (a, b) = self.smallest_sides();
        2 * a + 2 * b
    }

    fn volume(&self) -> i32 {
        let Present {l,w,h} = self;
        l * w * h
    }
}

fn parse(input: &String) -> Vec<Present> {
    let list = input.split("\n");
    let mut presents : Vec<Present> = Vec::new();
    for item in list {
        let [length, width, height] = item
            .split("x")
            .map(|it| it.parse().expect("badness."))
            .collect::<Vec<i32>>()
            .try_into().unwrap();
        let present = Present {
            l: length,
            w: width,
            h: height,
        };
        presents.push(present);
    }
    presents
}

pub fn part_one(input: &String) -> i32 {
    let presents = parse(input);
    presents
        .iter()
        .fold(0, |acc, it| acc + it.area() + it.smallest_area())
}

pub fn part_two(input: &String) -> i32 {
    let presents = parse(input);
    presents
        .iter()
        .fold(0, |acc, it| acc + it.volume() + it.smallest_perimeter())
}

#[cfg(test)]
mod tests {
    use super::{part_one, part_two, Present};

    #[test]
    fn test_one() {
        let example = String::from("2x3x4\n1x1x10");
        assert_eq!(part_one(&example), 101); // 58 + 43
    }

    #[test]
    fn test_one_two() {
        let example = String::from("19x18x22");
        assert_eq!(part_one(&example), 2654);
    }

    #[test]
    fn test_one_three() {
        let p1 = Present {
            l: 1,
            w: 10,
            h: 10,
        };
        let p2 = Present {
            l: 10,
            w: 10,
            h: 1,
        };
        let p3 = Present {
            l: 10,
            w: 1,
            h: 10,
        };
        assert_eq!(p1.smallest_area(), 10);
        assert_eq!(p2.smallest_area(), 10);
        assert_eq!(p3.smallest_area(), 10);
    }

    #[test]
    fn test_two_one() {
        let example = String::from("1x1x10");
        assert_eq!(part_two(&example), 14);
    }

    #[test]
    fn test_two_two() {
        let example = String::from("2x3x4");
        assert_eq!(part_two(&example), 34);
    }
}
