MAKEFLAGS = --no-print-directory

CHPL = chpl

TARGETS = \

default: all

clean: FORCE
	rm -f kmeans

all:
	$(CHPL) --fast-followers --fast -optimize --specialize --local --no-checks --no-debug --optimize-loop-iterators --optimize-on-clauses --main-module main -o kmeans jansson.h -ljansson main.chpl

FORCE:
