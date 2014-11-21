""" The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
Find the sum of all the primes below two million."""
"""142913828922"""

from math import sqrt

notPrimes = set([1])
limit = 2000000

def addNotPrimes(i, limit):
    for m in xrange(2, (limit / i) + 1):
        notPrimes.add(i * m)

for i in xrange(2, int(sqrt(limit)) + 1):
    addNotPrimes(i, limit)

primes = [i for i in xrange(1, limit) if i not in notPrimes]
print primes
print sum(primes)
