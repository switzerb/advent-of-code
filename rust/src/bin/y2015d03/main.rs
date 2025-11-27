use adventofcode::load_file;
use adventofcode::y2015::d03_spherical::{part_one, part_two};

fn main() {
    let input_path = "src/bin/y2015d03/input.txt";
    let input = load_file(input_path);

    println!("Part one: {}", part_one(&input));
    println!("Part one: {}", part_two(&input));
}
