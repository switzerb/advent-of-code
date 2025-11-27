use std::fs::File;
use serde_json::json;
use std::io::Write;

pub fn part_one(input: &String) -> i32 {
    let mut count = 0;
    for c in input.trim().chars() {
        if c == '(' { count += 1 }
        if c == ')' { count -= 1 }
    }
    count
}

pub fn part_two(input: &String) -> i32 {
    let mut position = 0;
    let mut count = 0;
    for c in input.trim().chars() {
        if c == '(' { count += 1 }
        if c == ')' { count -= 1 }
        position += 1;
        if count < 0 {
            break;
        }
    }
    position
}

pub fn to_json() -> std::io::Result<()> {
    let john = json!({
  "base": {"element": {"rectangle": [50,50,20,20]}},
  "frames": [
    {
      "element": {
        "rectangle":[100,100,30,30],
        "color": "#009900"
      },
    },
    {
      "element": {
        "rectangle":[150,150,30,30],
        "color": "#000000"
      },
    },
    {
      "element": {
        "rectangle":[180,100,40,30],
        "color": "#000099"
      },
    },
  ]
});
    let json = john.to_string();
    let path = "results.txt";
    let mut output = File::create(path)?;
    write!(output, "{}", json)
}

#[cfg(test)]
mod tests {
    use super::{part_one, part_two};

    #[test]
    fn test_one() {
        let example = String::from("(())");
        assert_eq!(part_one(&example), 0);
    }

    #[test]
    fn test_two() {
        let example = String::from(")())())");
        assert_eq!(part_one(&example), -3);
    }

    #[test]
    fn test_three() {
        let example = String::from(")())())");
        assert_eq!(part_two(&example), 1)
    }
}
