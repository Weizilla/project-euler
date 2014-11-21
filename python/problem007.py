"""By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
What is the 10 001st prime number?"""
"""104743"""

from math import sqrt

notPrimes = set([1])
limit = 200000

def addNotPrimes(i, limit):
    for m in xrange(2, limit / i):
        notPrimes.add(i * m)

for i in xrange(2, int(sqrt(limit)) + 1):
    addNotPrimes(i, limit)

primes = [i for i in xrange(1, limit) if i not in notPrimes]
print "total", len(primes)
print "10001", primes[10000]
