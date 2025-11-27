use std::fs;
use std::fs::File;
use std::io::ErrorKind;

pub mod utils;
pub mod y2015;
pub mod y2016;
pub mod y2017;
pub mod y2024;

pub fn load_file(path: &str) -> String {
    let open = File::open(path);

    match open {
        Ok(file) => file,
        Err(error) => match error.kind() {
            ErrorKind::NotFound => match File::create(path) {
                Ok(fc) => fc,
                Err(e) => panic!("Problem creating the file: {:?}", e),
            },
            other_error => {
                panic!("Problem opening the file: {:?}", other_error);
            }
        },
    };

    let input = fs::read_to_string(path).expect("Should have been able to read the file");
    input
}
