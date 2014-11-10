This benchmark is born to compare the performance of Pharo 3 in executing a simple machine learning algorithm with a reference implementation in Python and Scala. Since then, it got a little out of hand, and a few other implementations are available.

Rules
=====

The implementations should all follow the same algorithm, and be optimized for idiomatic code and not for speed. The example is intended to compare time of execution for a typical machine learning algorithm, ideally during an interactive session, instead of highly optimized production code. As such, it is important that the code is straightforward and that there is no separate phase to prepare the caches.

The points are in `points.json`, and are to be grouped into 10 clusters, using 15 iterations of kmeans. The initial centroids are initialized to the first 10 points, and we take an average over 100 runs.

Results
=======

Time for running on my laptop are available under `results`. A few surprises:

* Scala seems to run consistently faster than Rust...
* ...but PyPy is able to outperform both
* Factor is pretty impressive, given that it is a fairly small project with a dedicated VM. With an implementation in 8 (!) lines, we get the best performing dynamic language, (second, counting PyPy), before V8 and Clojure.