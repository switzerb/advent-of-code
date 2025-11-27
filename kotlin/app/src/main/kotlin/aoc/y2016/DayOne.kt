package aoc.y2016

class DayOne2016(val input: String) {

    fun parser(): List<String> {
        return input.split(",")
    }

    fun partOne(): Int {
        val instructions = parser()
        val something = instructions.map { it }
        return 0
    }
}

fun main(args: Array<String>) {
    println("hello world?")
}

//    # use map to go from R3 to (0,1) (0,2) or whatever to get an array of tuple steps for that move
//    # then parse_input(example_one) = [('R', 2), ('L', 3)]
//    # print(reduce(lambda a, i: draw_line(a, i), parse_input(example_one), {'current': {'x': 0, 'y': 0, 'facing': 'north'}, 'lines': []} ))

//    # map the list of start and end points into an expanded list of all the points along the way
//    # aggregate the list into one big list
//    # use find to find the first duplicates in one big array of steps

//    def turn(direction):
//    return direction[0]
//
//    def distance(direction):
//    return int(direction[1:])
//
//    def parse_input(x):
//    return map(lambda i: (turn(i), distance(i)), split_input(x))
//
//
//    def face(facing, pivot):
//    if facing == "north":
//    return 'east' if pivot == 'R' else 'west'
//
//    if facing == "south":
//    return 'west' if pivot == 'R' else 'east'
//
//    if facing == "east":
//    return 'south' if pivot == 'R' else 'north'
//
//    if facing == 'west':
//    return 'north' if pivot == 'R' else 'south'
//
//
//    def delta(facing, step):
//    if facing == 'north':
//    return 0, step
//
//    if facing == 'south':
//    return 0, -step
//
//    if facing == 'east':
//    return step, 0
//
//    if facing == 'west':
//    return -step, 0
//
//
//    def walk(current, (pivot, step)):
//    facing = face(current['facing'], pivot)
//    (dx, dy) = delta(facing, step)
//    return {'x': current['x'] + dx, 'y': current['y'] + dy, 'facing': face(current['facing'], pivot)}
//
//
//    def draw_line(aggregator, (pivot, step)):
//    # this will return my next position
//    next = walk(aggregator['current'], (pivot, step))
//    return {'current': next, 'lines': aggregator['lines'] + [(aggregator['current'], next)]}
//
//    # use map to go from R3 to (0,1) (0,2) or whatever to get an array of tuple steps for that move
//    # then parse_input(example_one) = [('R', 2), ('L', 3)]
//    # print(reduce(lambda a, i: draw_line(a, i), parse_input(example_one), {'current': {'x': 0, 'y': 0, 'facing': 'north'}, 'lines': []} ))
//
//
//    # map the list of start and end points into an expanded list of all the points along the way
//    # aggregate the list into one big list
//    # use find to find the first duplicates in one big array of steps
//
//    # [(0, 0), (0, 1), (0, 2), (1, 2), (2, 2), (3, 2)]
//
//
//    def end(puzzle):
//    return reduce(walk, parse_input(puzzle), {'x': 0, 'y': 0, 'facing': 'north'})
//
//
//    def total_distance(endpoint):
//    return abs(endpoint['x']) + abs(endpoint['y'])
//
//
//    print(total_distance(end(puzzle_input)))
//    print('\n')
