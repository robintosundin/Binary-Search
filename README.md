# A Binary Search implementation written in java
The complexity for indexOfKey (which contains() executes) is log(n). The reason behind this is in the algorithm for indexOfKey; indexOfKey uses a binary search. The binary search algorithm iteratively halves the searched list until a match is found (or not). The length of the list has to at worst be halved k times i.e. n=2^k for some integer k which means that k=log(n). 

## Practical Example (time in nanoseconds for medium performance pc ~ 2010)
|Set size  |Execution time|
|----------|:------------:|
|      10  |           7.3|
|     100  |          13.5|
|    1000  |          47.7|
|   10000  |          52.2|
|  100000  |          60.9|

## Theoretical Example (time in nanoseconds, for some values a and b)
|Set size  |Execution time      |
|----------|:------------------:|
|n=    10  | k=a*log(n)+b=  7.3 |
|n=   100  | k=a*log(n)+b= 13.5 |
|n=  1000  | k=a*log(n)+b= 47.7 |
|n= 10000  | k=a*log(n)+b= 52.2 |
|n=100000  | k=a*log(n)+b= 60.9 |

Plotting the practical execution time versus log(n) for some set sizes n it is possible to find some approximate values on a and b so that plotting execetuion time vs a*log(n)+b become a proportionate plot.
