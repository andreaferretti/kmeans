# do Pkg.add("JSON") first
using JSON

type Point
    x::Float64
    y::Float64
end

+(p1::Point, p2::Point) = Point(p1.x + p2.x, p1.y + p2.y)
/(p::Point, k::Number) = Point(p.x / k, p.y / k)
# hypot(a, b) = sqrt(a^2 + b^2), but would be faster to
# avoid the sqrt and work in terms of squared distance
dist(p1::Point, p2::Point) = hypot(p1.x - p2.x, p1.y - p2.y)

# one-liner version of closest:
# closest(p1::Point, points) = points[indmin([dist(p1, p2) for p2 in points])]
# devectorized version that avoids allocating an array of all distances:
function closest(p1::Point, points)
    mindist = Inf
    min_i = 0
    for i=1:length(points)
        disti = dist(p1, points[i])
        if disti < mindist
            mindist = disti
            min_i = i
        end
    end
    return points[min_i]
end

function groupby(points, centroids)
    g = Dict{Point, Vector{Point}}()
    for p in points
        c = closest(p, centroids)
        if haskey(g, c)
            push!(g[c], p)
        else
            g[c] = [p]
        end
    end
    return g
end

function update_centroids(points, centroids)
    groups = groupby(points, centroids)
    res = Point[]
    for g in values(groups)
        push!(res, mean(g))
    end
    return res
end

function run(xs, n, iters=15)
    centroids = xs[1:n]
    for i in 1:iters
        centroids = update_centroids(xs, centroids)
    end
    return groupby(xs, centroids)
end

function main()
    points = [Point(x[1], x[2]) for x in JSON.parsefile("../points.json")]
    iterations = 100
    tic()
    for i in 1:iterations
        run(points, 10)
    end
    totaltime = toq() * 1000 / iterations
    println("Made $iterations iterations with an average of $totaltime milliseconds")
end

main()
