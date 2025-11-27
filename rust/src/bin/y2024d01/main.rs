use adventofcode::load_file;
use adventofcode::y2024::d01_historian_hysteria::{parse, part_one, part_two};

fn main() {
    let input_path = "src/bin/y2024d01/input.txt";
    let input = load_file(input_path);

    let parsed = parse(&input);

    println!("Part one: {}", part_one(&parsed)); // 1765812
    println!("Part two: {}", part_two(&parsed)); // 20520794
}
