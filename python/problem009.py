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


