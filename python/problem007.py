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
