USING: assocs generalizations kernel math.statistics math.vectors sequences ;
IN: kmeans

: closest ( ys x -- y ) [ distance ] curry infimum-by ;

: clusters ( xs centroids -- clusters ) [ swap closest ] curry collect-by values ;

: vsum ( vs -- w ) { 0 0 } [ v+ ] reduce ;

: avg ( vs -- w ) [ vsum ] [ length ] bi v/n ;

: centroids ( clusters -- centroids ) [ avg ] map ;

: kmeans-start ( xs n -- clusters ) dupd head clusters ;

: kmeans-step ( clusters -- clusters ) [ concat ] [ centroids ] bi clusters ;

: kmeans ( iters xs n -- clusters ) kmeans-start [ kmeans-step ] repeat ;