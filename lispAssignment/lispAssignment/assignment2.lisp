; Simpsons formula over the function f in the interval a to b with n_steps
(defun simpson_rule (f a b n_steps)
	"Simpson's rule over function f in the interval [a,b] with n_steps"
	(let ((STEP_SIZE (/ (- b a) n_steps)))
	; Sumamtion from 1 to n/2 as mentioned in the formula
	(sum f a STEP_SIZE 0 (/ n_steps 2)))
)
; Term used inside the sigma in the formula
(defun term (f i a step_size) 
	(let 
		(
			(X_2i-2 (+ a (* (- (* 2 i) 2) step_size))) 
			(X_2i-1 (+ a (* (- (* 2 i) 1) step_size))) 
			(X_2i   (+ a (* (* 2 i) step_size)))
		)
		
		(+ (funcall f X_2i-2) (* 4 (funcall f X_2i-1)) (funcall f X_2i))
	)
)
; Function that does summation of the term defined above from start_index to end_index
(defun sum (f a step_size start_index end_index)
	(if (> start_index end_index)
		0.0
		(+ (sum f a step_size (+ 1 start_index) end_index) (term f start_index a step_size))
	)
)

(write "Design 1 area ")
(print (simpson_rule (lambda (x) (* 4 (* x x))) 2 4 100))
(print "Design 2 area: ")
(print (simpson_rule (lambda (x) (* 4 (exp x))) 2 4 100))
(print "Design 3 area: ")
(print (simpson_rule (lambda (x) (* 4 (- (expt x 4) (expt x 3)))) 2 4 100))