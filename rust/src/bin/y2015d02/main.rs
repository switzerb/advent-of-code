use adventofcode::y2015::d02_no_math::{part_one, part_two};
use adventofcode::{load_file};

fn main() {
    let input_path = "src/bin/y2015d02/input.txt";
    let input = load_file(input_path);

    println!("Part one: {}", part_one(&input)); // 1588178
    println!("Part two: {}", part_two(&input)); // 3783758
}