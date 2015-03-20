MAKEFLAGS = --no-print-directory

TARGETS = \

#default: all

clean: FORCE
	rm -f javabin/*.*
	rm -f cbin/*.*
	rm -f javalib/jsonRead.jar

java:
	javac -cp javalib/jackson-core-2.5.1.jar jsonRead.java
	jar cf javalib/jsonRead.jar jsonRead.class
	rm jsonRead.class
	x10c -O -cp javalib/jackson-core-2.5.1.jar:javalib/jsonRead.jar -d javabin *.x10 

runJava:
	x10 -O -cp javabin:javalib/jackson-core-2.5.1.jar:javalib/jsonRead.jar Main

c:
	x10c++ -O -d cbin *.x10 -cxx-postarg -ljansson

runC:
	./cbin/a.out

FORCE:
