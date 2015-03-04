defmodule Kmeans do

  defp sq(x) do
    x * x
  end

  defp distance({x1, y1}, {x2, y2}) do
    :math.sqrt( sq(x1 - x2) + sq(y1 - y2) )
  end

  defp closest(point, centroids) do
    Enum.min_by centroids, &distance(&1, point)
  end

  defp clusters(centroids, points) do
    Enum.group_by(points, &closest(&1, centroids)) |> Dict.values
  end

  defp sum({x1, y1}, {x2, y2}) do
    {x1 + x2, y1 + y2}
  end

  defp shrink({x, y}, factor) do
    {x / factor, y / factor}
  end

  defp average(cluster) do
    List.foldl(tl(cluster), hd(cluster), &sum/2) |> shrink length(cluster)
  end

  defp step(points, iterations, centroids) do
    case iterations do
      0 -> centroids
      _ -> step points, iterations - 1, Enum.map(clusters(centroids, points), &average/1)
    end
  end

  def run(points, k, iterations) do
    centroids = step points, iterations, Enum.take(points, k)
    clusters centroids, points
  end

end

