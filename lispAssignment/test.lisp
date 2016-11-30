;(write ((lambda (x) (* 2 x)) 3))


(defun fun1 (f x y) (funcall f (* x y)))

(defun fun2 (x) (* 2 x))

;(write (fun1 #'(lambda (x) (* 2 x)) 1 1))
(write (fun1 fun2 1 1))

