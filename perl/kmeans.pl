#!/usr/bin/env perl

use warnings;
use strict;
use 5.20.0;
use JSON;
use List::Util qw(reduce);

open my $POINTS, '<', '../points.json' or die $!;
my $json = from_json(join('', <$POINTS>));
my @points = map { [$_->[0], $_->[1]] } @{$json};

my $iterations = 100;
my $start = time;

for my $i (0 .. $iterations - 1) {
	run(\@points, 10);
}

my $total = (time - $start) * 1000 / $iterations;
say "Made $iterations iterations with an average of $total milliseconds";

sub run {
	my ($xs, $n, $iters) = @_;
	$iters //= 15;

	my $centroids = [@{$xs}[0 .. $n-1]];

	for my $i (0 .. $iters - 1) {
		$centroids = update_centroids($xs, $centroids);
	}

	return groupby($xs, $centroids);
}

sub update_centroids {
	my ($points, $centroids) = @_;

	my $groups = groupby($points, $centroids);

	my @res = ();
	for my $g (values %{$groups}) {
		my $len = scalar @{$g};
		my $psum = reduce { [$a->[0] + $b->[0], $a->[1] + $b->[1]] } [0,0], @{$g};
		$psum->[0] /= $len;
		$psum->[1] /= $len;
		push @res, $psum;
	}

	return \@res;
}

sub groupby {
	my ($points, $centroids) = @_;

	my %g = ();
	for my $p (@{$points}) {
		my $c = closest($p, $centroids);
		push @{$g{$c}}, $p;
	}

	return \%g;
}

sub dist {
	return sqrt(($_[0]->[0] - $_[1]->[0])**2 + ($_[0]->[1] - $_[1]->[1])**2);
}

sub closest {
	my $point = shift;

	my $min = 9999999999999999;
	my $min_centroid = [];
	for my $c (@{$_[0]}) {
		my $dist = dist($point, $c);
		if ($dist < $min) {
			$min = $dist;
			$min_centroid = $c;
		}
	}

	return $min_centroid;
}
