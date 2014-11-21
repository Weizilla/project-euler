"""A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,

a**2 + b**2 = c**2
For example, 32 + 42 = 9 + 16 = 25 = 52.

There exists exactly one Pythagorean triplet for which a + b + c = 1000.
Find the product abc."""
"""31875000"""

from math import sqrt

def isValid(a, b, c):
    return a ** 2 + b ** 2 == c ** c

print isValid(3, 4, 5)

for a in xrange(1, 1000):
    for b in xrange(a, 1000):
        c = sqrt(a ** 2 + b ** 2)
        if a + b + c == 1000:
            print a, b, c
            print a * b * c


