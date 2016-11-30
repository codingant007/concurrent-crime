; Given a line from one of the course files prints the roll number
(defun print_roll (line) 
			(setq rollno (parse-integer line))
			(print rollno))

; Returns a list of integers from start to end
(defun range (start end) 
	(setq L nil)
	(loop (when (> start end) (return (reverse L))) 
		(setq L (cons start L))
		(setq start (+ start 1))
	)
)
; Does logical and of all the ouputs of elements transformed by f
(defun forall (L f) 
	(if (null L)
		T
		(and (funcall f (first L)) (forall (rest L) f))
	)
)
; Checks if a number is prime
(defun is_prime (n) 
	(forall (range 2 (floor (sqrt n)))
		#'(lambda (d) (not (= (mod n d) 0)))
	)
)
; Computes sum of digits in a roll number
(defun sum_digits (rollno)
	(if (= rollno 0)
		0
		(+ (sum_digits (floor (/ rollno 10))) (mod rollno 10))
	)
)
; Checks if the sum of digits in the roll number is prime
(defun is_sum_prime (line)
	(setq rollno (parse-integer line))
	(is_prime (sum_digits rollno))
)
; Print the line if the roll number in the line has prime sum of digits
(defun print_if_sum_prime (line) 
	(if (is_sum_prime line)
		(print_roll line)
	)
)
; Prints all the roll numbers in the list of files with prime sum
(defun random_rollno_generator (L)
	(if (null L) (return 0) (print "from "))
	(write (first L))
	(with-open-file (stream (first L) :direction :input)
		(loop for line = (read-line stream nil)
			until (eq line nil)
			do (print_if_sum_prime line))
	)
	(random_rollno_generator (rest L))
)
; Generating pseudo random numbers from the four courses given in the assignment
(random_rollno_generator '("cs341.txt" "cs201.txt" "cs431.txt" "ma101.txt"))