use std::collections::HashMap;
use crate::utils::point::Point;

const INPUT : &'static str = "361527";
const PORT : &'static Point = &Point(0, 0);
const NEIGHBORS: [Point; 8] = [
    Point(1, 0),
    Point(1, -1),
    Point(0, -1),
    Point(-1, -1),
    Point(-1, 0),
    Point(-1, 1),
    Point(0, 1),
    Point(1, 1)
];

fn anchor_coords(n: i32, depth: u32) -> Point {
    if n == 1 {
        return Point(0,0);
    }
    if n == 2 {
        Point(0, 1)
    } else {
        if n % 2 == 0 {
            let pos = anchor_coords(n - 1, depth + 1);
            Point(-pos.0, pos.1.abs() + 1)
        } else {
            let pos = anchor_coords(n - 1, depth);
            Point(pos.0.abs() + 1, -pos.1)
        }
    }
}

fn offset_coords(n: i32) -> Point {
    let root = (n as f64).sqrt();
    let floor = root.floor() as i32;
    let fraction = root.fract();

    // number is an integer square root
    if fraction == 0.0_f64 {
        return anchor_coords(floor, 0);
    }

    let ceil = floor + 1;
    let offset = ceil.pow(2) - n;
    let anchor = anchor_coords(ceil, 0);
    let turn = floor.pow(2) + floor;
    if ceil % 2 == 0 { // even
        if turn < n {
            Point(anchor.0 + offset, anchor.1)
        } else {
            Point(anchor.0 + floor, anchor.1 - (offset - floor))
        }
    } else { //odd
        if turn < n {
            Point(anchor.0 - offset, anchor.1)
        } else {
            Point(anchor.0 - floor, anchor.1 + (offset - floor))
        }
    }
}

pub fn parse(input: &str) -> i32 {
    return input.trim().parse().expect("Target must be a number")
}

pub fn run(input: i32) -> i32 {
    let coords = offset_coords(input);
    return coords.manhattan_distance(PORT);
}

pub fn part_one() -> i32 {
    let target: i32 = parse(INPUT);
    let coords = offset_coords(target);
    return coords.manhattan_distance(PORT);
}

pub fn part_two() -> i32 {
    let target: i32 = parse(INPUT);
    let mut x = 0;
    let mut y = 0;
    let mut dx = 0;
    let mut dy = -1;
    let mut grid : HashMap<Point, i32> = HashMap::new();

    loop {
        let mut total = 0;
        for offset in NEIGHBORS {
            let something = Point(x + offset.0, y + offset.1);
            if grid.contains_key(&something) {
                total += grid[&something];
            }
        }

        if total > target {
            return total
        }

        if Point(x, y) == *PORT {
            grid.insert(Point(0,0), 1);
        } else {
            grid.insert(Point(x,y), total);
        }
        if (x == y) || (x < 0 && x == -y) || (x > 0 && x == 1 - y) {
            (dx, dy) = (-dy, dx);
        }
        x += dx;
        y += dy;
    }
}

#[cfg(test)]
mod tests {
    use super::{run, anchor_coords, offset_coords, part_one, part_two};
    use super::Point;

    #[test]
    fn test_one() {
        assert_eq!(Point(0, 0), anchor_coords(1,0));
        assert_eq!(Point(0, 1), anchor_coords(2,0));
        assert_eq!(Point(1, -1), anchor_coords(3,0));
        assert_eq!(Point(-1, 2), anchor_coords(4,0));
        assert_eq!(Point(2, -2), anchor_coords(5,0));
        assert_eq!(Point(-2, 3), anchor_coords(6,0));
        assert_eq!(Point(3, -3), anchor_coords(7,0));
        assert_eq!(Point(-3, 4), anchor_coords(8,0));
        assert_eq!(Point(4, -4), anchor_coords(9,0));
    }

    #[test]
    fn test_1() {
        assert_eq!(0, run(1));
        assert_eq!(1, run(4));
        assert_eq!(2, run(9));
        assert_eq!(3, run(16));
        assert_eq!(4, run(25));
        assert_eq!(5, run(36));
    }

    #[test]
    fn test_2() {
        assert_eq!(Point(1,-1), offset_coords(9));
        assert_eq!(Point(0, -1), offset_coords(8));
        assert_eq!(Point(-1, -1),   offset_coords(7));
        assert_eq!(Point(-1, 0),    offset_coords(6));
        assert_eq!(Point(-1, 1),    offset_coords(5));
        assert_eq!(Point(0, 1),     offset_coords(4));
        assert_eq!(Point(0, 2),     offset_coords(15));
        assert_eq!(Point(1, 2),     offset_coords(14));
        assert_eq!(Point(2, 2),     offset_coords(13));
        assert_eq!(Point(2, 1),     offset_coords(12));
        assert_eq!(Point(2, 0),     offset_coords(11));
        assert_eq!(Point(2, -1),    offset_coords(10));
    }

    #[test]
    fn test_3() {
        assert_eq!(326, part_one());
    }

    #[test]
    fn test_4() {
        assert_eq!(363010, part_two());
    }
}
