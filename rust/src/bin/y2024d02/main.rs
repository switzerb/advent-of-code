use adventofcode::load_file;
use adventofcode::y2024::d02_red_nosed_reports::{part_one, part_two};

fn main() {
    let input_path = "src/bin/y2024d02/input.txt";
    let input = load_file(input_path);

    println!("Part one: {}", part_one(&input));
    println!("Part two: {}", part_two(&input));
}
