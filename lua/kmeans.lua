local sqrt = math.sqrt
local clock = os.clock
local json = require("dkjson")

local Point = {}
Point.__index = Point

setmetatable(Point, {
	__call = function(self, x, y)
		return setmetatable({_x=x, _y=y}, Point)
	end
})

function Point:x()
	return self._x
end
function Point:y()
	return self._y
end
function Point:__add(other)
	return Point(self._x + other._x, self._y + other._y)
end
function Point:__div(value)
	return Point(self._x / value, self._y / value)
end
function Point:dist(other)
	return sqrt((self._x - other._x)^2 + (self._y - other._y)^2)
end
function Point:closest(points)
	local min = math.huge
	local min_i
	for i, point in ipairs(points) do
		local dist = self:dist(point)
		if dist < min then
			min = dist
			min_i = i
		end
	end
	return points[min_i]
end
function Point:__tostring()
	return ("Point(%f,%f)"):format(self._x, self._y)
end

local function sum(t, start)
	local total = start or 0
	for _, i in ipairs(t) do
		total = total + i
	end
	return total
end

local function len(t)
	local l = 0
	for _ in pairs(t) do
		l = l + 1
	end
	return l
end

local function groupby(points, centroids)
	local g = {}
	for _, p in ipairs(points) do
		local c = p:closest(centroids)
		g[c] = g[c] or {}
		table.insert(g[c], p)
	end
	return g
end

local function update_centroids(points, centroids)
	local groups = groupby(points, centroids)
	local res = {}
	local i = 1
	for _, g in pairs(groups) do
		res[i] = sum(g, Point(0, 0)) / len(g)
		i = i + 1
	end
	return res
end

local function run(xs, n, iters)
	iters = iters or 15
	local centroids = {}
	for i = 1, n do
		table.insert(centroids, xs[i])
	end
	for i = 1, iters do
		centroids = update_centroids(xs, centroids)
	end
	return groupby(xs, centroids)
end

if not ... then
	local data = json.decode(io.open("../points.json"):read("*a"))
	local points = {}
	for i, x in ipairs(data) do
		points[i] = Point(x[1], x[2])
	end
	local start = clock()
	local iterations = 100
	for i = 1, iterations do
		run(points, 10)
	end
	local total = (clock() - start) * 1000 / iterations
	print(("Made %d iterations with an average of %.2f milliseconds"):format(iterations, total))
end
