import unittest
import day07


class DaySeven(unittest.TestCase):
    def test_p1_1(self):
        self.assertEqual(True, day07.contains_abba("abba"))

    def test_p1_2(self):
        self.assertEqual(True, day07.contains_abba("xyyxjump"))

    def test_p1_3(self):
        self.assertEqual(False, day07.contains_abba("aaaa"))

    def test_p1_4(self):
        self.assertEqual(True, day07.supports_tls("abba[mnop]qrst"))

    def test_p1_5(self):
        self.assertEqual(False, day07.supports_tls("abcd[bddb]xyyx"))

    def test_p1_6(self):
        self.assertEqual(False, day07.supports_tls("aaaa[qwer]tyui"))

    def test_p1_7(self):
        self.assertEqual(True, day07.supports_tls("ioxxoj[asdfgh]zxcvbn"))

    def test_p1_actual(self):
        file = open("input.txt")
        data = file.read().splitlines()
        self.assertEqual(118, day07.part_one(data))
        file.close()

    def test_p2_1(self):
        self.assertEqual(['bab'], day07.contains_aba("aba"))

    def test_p2_2(self):
        self.assertEqual(True, day07.supports_ssl("aba[bab]xyz"))

    def test_p2_3(self):
        self.assertEqual(False, day07.supports_ssl("xyx[xyx]xyx"))

    def test_p2_4(self):
        self.assertEqual(True, day07.supports_ssl("aaa[kek]eke"))

    def test_p2_5(self):
        self.assertEqual(True, day07.supports_ssl("zazbz[bzb]cdb"))

    def test_p2_6(self):
        self.assertEqual(True, day07.supports_ssl("obdhcczonzvbfby[fbf]"))

    def test_p2_7(self):
        self.assertEqual(True, day07.supports_ssl("bab[xxx]xyxyx[xyx]"))

    def test_p2_8(self):
        self.assertEqual(True, day07.supports_ssl("dddaba[babddd]"))


if __name__ == '__main__':
    unittest.main()
