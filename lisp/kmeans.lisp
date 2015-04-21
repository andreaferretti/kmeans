(declaim (optimize (speed 3) (space 0) (debug 0) (safety 0) (compilation-speed 0)))

(defconstant n 10)
(defconstant iterations 15)

(defconstant executions 100)

(defstruct (point (:constructor make-point (x y)))
  (x 0.0D0 :type double-float)
  (y 0.0D0 :type double-float))

(declaim (inline add addf
                 divide dividef
                 modulus dist
                 average averagef
                 closest clusters))

(defun add (p1 p2)
  (declare (point p1 p2))
  (make-point (+ (point-x p1) (point-x p2))
              (+ (point-y p1) (point-y p2))))

(defun addf (p1 p2)
  (declare (point p1 p2))
  (setf (point-x p1) (+ (point-x p1) (point-x p2))
        (point-y p1) (+ (point-y p1) (point-y p2)))
  p1)

(defun divide (p d &aux (df (coerce d 'double-float)))
  (declare (point p))
  (make-point (/ (point-x p) df)
              (/ (point-y p) df)))

(defun dividef (p d &aux (df (coerce d 'double-float)))
  (declare (point p))
  (setf (point-x p) (/ (point-x p) df)
        (point-y p) (/ (point-y p) df))
  p)

(defun modulus (x y)
  (declare (double-float x y))
  (sqrt (the (double-float 0.0D0) (+ (* x x) (* y y)))))

(defun dist (p1 p2)
  (declare (point p1 p2))
  (modulus (- (point-x p1) (point-x p2))
           (- (point-y p1) (point-y p2))))

(defun average (points)
  (loop :with sum := (make-point 0.0D0 0.0D0)
        :for point :in points
        :for length :of-type fixnum :from 1
        :do (addf sum point)
        :finally (return (dividef sum length))))

(defun averagef (average points)
  (declare (point average))
  (setf (point-x average) 0.0D0
        (point-y average) 0.0D0)
  (loop :for point :in points
        :for length :of-type fixnum :from 1
        :do (addf average point)
        :finally (return (dividef average length))))

(defun closest (rp choices)
  (loop :with min := (first choices)
        :with min-dist :of-type double-float := (dist rp min)
        :for point :in (rest choices)
        :for dist :of-type double-float := (dist rp point)
        :when (< dist min-dist)
        :do (setq min-dist dist min point)
        :finally (return min)))

(defun clusters (xs centroids)
  (loop :with clusters := (make-hash-table :test 'eq)
        :for x :in xs
        :do (push x (gethash (closest x centroids) clusters))
        :finally (return clusters)))

(defun main-loop (xs)
  (loop :repeat executions
        :for centroids := (loop :repeat n
                                :for point :in xs
                                :collect (copy-point point))
        :do (loop :repeat iterations
                  :for clusters := (clusters xs centroids)
                  :do (loop :for centroid :in centroids
                            :do (averagef centroid (gethash centroid clusters))))
        :finally (return centroids)))

(defun make-points-readtable ()
  (let ((readtable (with-standard-io-syntax (copy-readtable))))
    (set-macro-character #\[ (lambda (stream char)
                               (declare (ignore char))
                               (loop :for char := (read-char stream t nil t)
                                     :until (char= char #\])
                                     :do (unread-char char stream)
                                     :collect (read stream t nil t)))
                         nil readtable)
    (set-syntax-from-char #\] #\) readtable)
    (set-syntax-from-char #\, #\  readtable)
    readtable))

(defun benchmark ()
  (let* ((xs (mapcar (lambda (p) (apply 'make-point p))
                     (let ((*readtable* (make-points-readtable))
                           (*read-default-float-format* 'double-float))
                       (with-open-file (in "../points.json" :direction :input) (read in)))))
         (start (get-internal-real-time))
         (centroids (main-loop xs))
         (stop (get-internal-real-time)))
    (format t "Last centroids are: ~{~%~A~}~%" centroids)
    (format t "Elapsed time is ~A~%" (/ (/ (* (- stop start) internal-time-units-per-second) 1000.0) executions))))

(benchmark)

