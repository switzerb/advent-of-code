pub fn part_one(_input: &String) -> i32 {
    0
}

pub fn part_two(_input: &String) -> i32 {
    0
}

#[cfg(test)]
mod tests {
    use super::{part_one, part_two};

    #[test]
    fn test_one() {
        let example = String::from("hello, world");
        assert_eq!(part_one(&example), 0);
    }

    #[test]
    fn test_two() {
        let example = String::from("goodbye, world");
        assert_eq!(part_two(&example), 0);
    }
}
