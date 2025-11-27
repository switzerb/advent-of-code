from library.Grid import Direction


class Point:
    def __init__(self, x=0, y=0, facing=Direction.NORTH):
        self.x = x
        self.y = y
        self.facing = facing

    def __str__(self):
        return "Point(%s,%s,%s)" % (self.x, self.y, self.facing)

    def turn(self, turn_to):
        if turn_to == "R":
            return self.turn_right()
        elif turn_to == "L":
            return self.turn_left()

    def move(self, move_to, dist=1):
        if move_to == "R":
            return self.move_right(dist)
        elif move_to == "L":
            return self.move_left(dist)

    def turn_right(self):
        if self.facing == Direction.NORTH:
            self.facing = Direction.EAST
        elif self.facing == Direction.SOUTH:
            self.facing = Direction.WEST
        elif self.facing == Direction.EAST:
            self.facing = Direction.SOUTH
        elif self.facing == Direction.WEST:
            self.facing = Direction.NORTH
        return self

    def turn_left(self):
        if self.facing == Direction.NORTH:
            self.facing = Direction.WEST
        elif self.facing == Direction.SOUTH:
            self.facing = Direction.EAST
        elif self.facing == Direction.EAST:
            self.facing = Direction.NORTH
        elif self.facing == Direction.WEST:
            self.facing = Direction.SOUTH
        return self

    def move_right(self, dist=1):
        if self.facing == Direction.NORTH:
            self.y -= dist
        elif self.facing == Direction.SOUTH:
            self.y += dist
        elif self.facing == Direction.EAST:
            self.x += dist
        elif self.facing == Direction.WEST:
            self.x -= dist
        return self

    def move_left(self, dist=1):
        if self.facing == Direction.NORTH:
            self.y -= dist
        elif self.facing == Direction.SOUTH:
            self.y += dist
        elif self.facing == Direction.EAST:
            self.x += dist
        elif self.facing == Direction.WEST:
            self.x -= dist
        return self

    def __eq__(self, other):
        return self.x == other.x and self.y == other.y

    def __ne__(self, other):
        return not self.__eq__(other)

    # manhattan distance = abs(x1 - x2) + abs(y1 - y2)
    def manhattan_distance(self, other):
        return abs(self.x - other.x) + abs(self.y - other.y)
