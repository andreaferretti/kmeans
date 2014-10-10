USING: generalizations io.encodings.utf8 io.files json.reader kernel kmeans tools.time ;
IN: kmeans.benchmark

: load-points ( path -- points ) utf8 file-contents json> ;

: kmeans-benchmark ( runs path -- )
  load-points [
    [ dup 15 swap 10 kmeans drop ] repeat
  ] time drop ;