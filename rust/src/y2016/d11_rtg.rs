use std::cmp::PartialEq;
use std::collections::{HashMap, HashSet, VecDeque};

#[derive(Debug, Eq, PartialEq, Clone)]
enum Item {
    Generator(char),
    Chip(char),
    Me,
}

type State = HashMap<i8, Vec<Item>>;

// h is hydrogen
// l for lithium
fn parse(_input: &String) -> State {
    // initial state
    let mut floors: State = State::new();
    floors.entry(1).or_default().push(Item::Me);
    floors.entry(1).or_default().push(Item::Chip('h'));
    floors.entry(1).or_default().push(Item::Chip('l'));
    floors.entry(2).or_default().push(Item::Generator('h'));
    floors.entry(3).or_default().push(Item::Generator('l'));
    floors
}

fn is_valid(floor: Vec<Item>) -> bool {
    // a microchip can't be on a floor without it's corresponding generator
    // what are the generators? carried or already there
    true
}

fn get_current_floor(state: &State) -> i8 {
    state
        .iter()
        .find(|&(_, v)| v.contains(&Item::Me))
        .map(|(k, _)| *k)
        .expect("I do not exist?")
}

// represents a list of all the possible next legal moves
fn get_adjacent(current: State) -> Vec<State> {
    let elevator = get_current_floor(&current);
    let items = current.get(&elevator).expect("there are no items");
    println!("{:?}", items);
    let something = items.iter().filter(|&v| *v != Item::Me).collect::<Vec<_>>();
    println!("{:?}", items);

    for (idx, it) in something.iter().enumerate() {
        for rest in &something[(idx + 1)..] {
            let mut next = current.clone();
            println!("{:?}", idx);
            println!("{:?}", it);
            println!("{:?}", rest);
            println!("--");
        }
    }

    // // list of items on this floor: Me, Chip(h), Chip(l)
    // if let Some(items) = next.get_mut(&elevator) {
    //     let moved_value = items.remove(1); // remove item1
    //
    //     if let Some(floor) = next.get_mut(&2) {
    //         floor.push(moved_value);
    //     }
    // } else {
    //     println!("Key not found!");
    // }

    // match elevator {
    //     1 => {
    //         // go to 2
    //     }
    //     2 => {
    //         // go to 1
    //         // go to 3
    //     }
    //     3 => {
    //         // go to 2
    //         // go to 4
    //     }
    //     4 => {
    //         // go to 3
    //     }
    // }

    let adj = vec![current];
    adj
}

// track on where I am
pub fn part_one(input: &String) -> i32 {
    let mut visited: Vec<State> = Vec::new();
    let mut queue: VecDeque<State> = VecDeque::new();
    let start = parse(input);
    queue.push_back(start);

    while !queue.is_empty() {
        let current = queue
            .pop_front()
            .expect("The queue is actually empty so something is amiss.");

        if visited.contains(&current) {
            continue;
        }

        let neighbors = get_adjacent(current);

        for neighbor in neighbors {
            // println!("{:?}", neighbor);
            queue.push_back(neighbor);
        }
    }
    0
}

pub fn part_two(_input: &String) -> i32 {
    0
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_one() {
        let example = String::from("hello, world");
        assert_eq!(part_one(&example), 0);
    }

    #[test]
    fn test_current_floor() {
        let mut floors: State = State::new();
        floors.entry(2).or_default().push(Item::Me);
        floors.entry(1).or_default().push(Item::Chip('h'));
        assert_eq!(get_current_floor(&floors), 2)
    }

    #[test]
    fn test_loop() {
        let something = vec![1, 2, 3];
        for (idx, it) in something.iter().enumerate() {
            for another in &something[(idx + 1)..] {
                println!("{}", idx);
                println!("{}", it);
                println!("{}", another);
            }
        }
    }

    #[test]
    fn test_two() {
        let example = String::from("goodbye, world");
        assert_eq!(part_two(&example), 0);
    }
}
