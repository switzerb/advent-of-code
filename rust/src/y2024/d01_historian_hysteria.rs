use std::collections::HashMap;

pub fn parse(input: &String) -> Vec<(i32, i32)> {
    let mut list: Vec<(i32, i32)> = Vec::new();
    for line in input.trim().lines() {
        let [left, right] = line
            .split("   ")
            .map(|it| it.parse().expect("badness."))
            .collect::<Vec<i32>>()
            .try_into()
            .unwrap();
        list.push((left, right));
    }
    list
}

fn partition(lists: &Vec<(i32, i32)>) -> (Vec<i32>, Vec<i32>) {
    let mut left: Vec<i32> = Vec::new();
    let mut right: Vec<i32> = Vec::new();
    for it in lists {
        left.push(it.0);
        right.push(it.1);
    }
    left.sort();
    right.sort();
    (left, right)
}

pub fn part_one(input: &Vec<(i32, i32)>) -> i32 {
    let (left, right) = partition(input);
    let mut distances: Vec<i32> = Vec::new();
    for idx in 0..left.len() {
        distances.push((left[idx] - right[idx]).abs());
    }
    distances.iter().sum()
}

pub fn part_two(input: &Vec<(i32, i32)>) -> i32 {
    let (left, right) = partition(input);
    let mut similarities: Vec<i32> = Vec::new();
    let mut histogram: HashMap<i32, i32> = HashMap::new();
    for it in right {
        let count = histogram.entry(it).or_insert(0);
        *count += 1;
    }
    for n in left {
        let score = histogram.get(&n).copied().unwrap_or(0);
        similarities.push(score * n);
    }
    similarities.iter().sum()
}

#[cfg(test)]
mod tests {
    use super::{part_one, part_two};

    #[test]
    fn test_one() {
        let example = vec![(3, 4), (4, 3), (2, 5), (1, 3), (3, 9), (3, 3)];
        assert_eq!(part_one(&example), 11);
    }

    #[test]
    fn test_two() {
        let example = vec![(3, 4), (4, 3), (2, 5), (1, 3), (3, 9), (3, 3)];
        assert_eq!(part_two(&example), 31);
    }
}
