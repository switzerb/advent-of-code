use adventofcode::y2015::d01_not_quite_lisp::{part_one, part_two, to_json};
use adventofcode::{load_file};

fn main() {
    let input_path = "src/bin/y2015d01/input.txt";
    let input = load_file(input_path);

    println!("Part one: {}", part_one(&input));
    println!("Part two: {}", part_two(&input));
    to_json().expect("TODO: panic message");
}