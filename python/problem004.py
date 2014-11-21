"""A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
Find the largest palindrome made from the product of two 3-digit numbers."""
"""906609"""

import math

maxP = 0

def isPal(i):
    s = str(i)
    return s[::-1] == s

for x in xrange(100, 999):
    for y in xrange(x, 999):
        p = x * y
        isP = isPal(p)
        print x, y, p, isP
        if (isPal(p)):
            maxP = max(maxP, p)

print isPal(111 * 111)
print maxP

