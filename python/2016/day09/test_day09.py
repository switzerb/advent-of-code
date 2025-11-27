from unittest import TestCase
import day09


class Test(TestCase):
    def test_p1_1(self):
        self.assertEqual(day09.part_one("ADVENT"), 6)

    def test_p1_2(self):
        self.assertEqual(7, day09.part_one("A(1x5)BC"))

    def test_p1_3(self):
        self.assertEqual(9, day09.part_one("(3x3)XYZ"))

    def test_p1_4(self):
        self.assertEqual(11, day09.part_one("A(2x2)BCD(2x2)EFG"))

    def test_p1_5(self):
        self.assertEqual(6, day09.part_one("(6x1)(1x3)A"))

    def test_p1_6(self):
        self.assertEqual(18, day09.part_one("X(8x2)(3x3)ABCY"))

    def test_p1_actual(self):
        filename = open("input.txt")
        self.assertEqual(98135, day09.part_one(filename.read()))
        filename.close()

    def test_p2_1(self):
        """No markers"""
        self.assertEqual(3, day09.part_two("XYZ"))

    def test_p2_2(self):
        """One marker"""
        self.assertEqual(9, day09.part_two("(3x3)XYZ"))

    def test_p2_3(self):
        """Two markers that overlap"""
        self.assertEqual(20, day09.part_two("X(8x2)(3x3)ABCY"))

    def test_p2_4(self):
        """Many markers that overlap"""
        self.assertEqual(241920, day09.part_two("(27x12)(20x12)(13x14)(7x10)(1x12)A"))
