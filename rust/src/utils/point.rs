#[derive(PartialEq, Eq, Hash, Debug, Clone)]
pub struct Point(pub(crate) i32, pub(crate) i32);
pub enum Dir {
    North,
    South,
    East,
    West,
}
impl Point {
    pub fn manhattan_distance(&self, other: &Point) -> i32 {
        (self.0 - other.0).abs() + (self.1 - other.1).abs()
    }

    pub fn step(&self, dir: Dir, dist: i32) -> Point {
        match dir {
            Dir::North => Point(self.0, self.1 + dist),
            Dir::South => Point(self.0, self.1 - dist),
            Dir::East => Point(self.0 + dist, self.1),
            Dir::West => Point(self.0 - dist, self.1),
        }
    }
}
