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

