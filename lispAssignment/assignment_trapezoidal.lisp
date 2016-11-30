; Trapezoidal formula over the function f in the interval a to b with n_steps
(defun trapezoidal_rule (f a b n_steps)
	"trapezoidal's rule over function f in the interval [a,b] with n_steps"
	(let ((STEP_SIZE (/ (- b a) n_steps)))
	; Sumamtion from 1 to n/2 as mentioned in the formula
	(* (/ STEP_SIZE 2) (sum_trapezoidal f a STEP_SIZE 1 n_steps)))
)
; Term used inside the sigma in the formula
(defun term_trapezoidal (f i a step_size) 
	(let 
		(
			(X_i    (+ a (*  i step_size)))
			(X_i-1 (+ a (* (- i 1) step_size)))
		)
		
		(+ (funcall f X_i) (funcall f X_i-1))
	)
)
; Function that does summation of the term defined above from start_index to end_index
(defun sum_trapezoidal (f a step_size start_index end_index)
	(if (> start_index end_index)
		0.0
		(+ (sum_trapezoidal f a step_size (+ 1 start_index) end_index) (term_trapezoidal f start_index a step_size))
	)

)

(write "Design 1 area ")
(print (trapezoidal_rule (lambda (x) (* 4 (* x x))) 2 4 100))
(print "Design 2 area: ")
(print (trapezoidal_rule (lambda (x) (* 4 (exp x))) 2 4 100))
(print "Design 3 area: ")
(print (trapezoidal_rule (lambda (x) (* 4 (- (expt x 4) (expt x 3)))) 2 4 100))