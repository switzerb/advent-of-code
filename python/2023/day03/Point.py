class Point:
    def __init__(self, point):
        self.x = point[0]
        self.y = point[1]

    def __str__(self):
        return "Point(%s,%s)" % (self.x, self.y)

    def neighbors(self):
        return [
            (self.x + 1, self.y),
            (self.x - 1, self.y),
            (self.x, self.y + 1),
            (self.x, self.y - 1),
            (self.x + 1, self.y + 1),
            (self.x - 1, self.y - 1),
            (self.x + 1, self.y - 1),
            (self.x - 1, self.y + 1),
        ]
