defmodule Main do

  defp strip_brackets(xs) do
    Enum.slice xs, 1, length(xs) - 2
  end

  defp parse_float(xs) do
    Enum.map xs, fn(s) -> elem(Float.parse(s), 0) end
  end

  defp to_tuples(xs) do
    case xs do
      []            -> []
      [a, b | tail] -> [{a, b} | to_tuples tail]
    end
  end

  defp read_points(filename) do
    File.read!(filename)
      |> String.split(~r{[][,]+})
      |> strip_brackets
      |> parse_float
      |> to_tuples
  end

  def main() do

    times =       100
    k =           10
    iterations =  15
    points =      read_points "../points.json"

    benchmarks =  for _ <- 1..times do
                    { elapsed, _ } = :timer.tc Kmeans, :run, [points, k, iterations]
                    elapsed / 1000
                  end

    IO.puts "Running #{iterations} iterations has required #{Enum.sum(benchmarks) / times} ms"
  end

end

Main.main()

