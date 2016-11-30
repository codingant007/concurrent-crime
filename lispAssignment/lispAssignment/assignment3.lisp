

(setq inf_path '(99999 ()))
; Graph is represented as list of edges
; Edge is represented as (head tail length)
;(setq graph '((1 2 100) (2 3 600) (3 4 800) (1 3 100) (3 1 20) (4 1 100) ) )
(setq graph '( 
				(1 2 250)
				(2 3 210)
				(3 4 150)
				(3 5 260)
				(5 6 350)
				(6 7 2)
				(7 8 65)
				(8 9 140)
				(9 10 57)
				(9 14 73)
				(14 15 140)
				(14 12 110)
				(12 13 95)
				(11 12 180)
				(11 10 173)
				(2 11 500)
			) 
)


(defun edge_head (edge) (first edge))
(defun edge_tail (edge) (first (rest edge)))
(defun edge_length (edge) (first (rest (rest edge))))


(defun next_nodes (node edges)
	(if (null edges) (return-from next_nodes ()))
	(let
		(
			(head (edge_head (first edges)))
			(tail (edge_tail (first edges)))
			(length (edge_length (first edges)))
			(L (next_nodes node (rest edges)))
		)
		(if (= head node) 
			(cons (list tail length) L)
			L
		)
	)
)

(defun in_list (x L) (if (eq (find x L) nil) nil T))

(defun best-path (paths)
	;(print paths)
	(if (null paths) (return-from best-path inf_path))
	(let 
		(
			(distance (first (first paths)))
			(best-in-remaining (best-path (rest paths)))
		)
		(if (< distance (first best-in-remaining)) (first paths) best-in-remaining)
	)
)

(defun suffix_path (node distance path)
	(if (equal path inf_path) (return-from suffix_path inf_path))
	(list (+ distance (first path)) (cons node (first (last path))))
)

; Shortest path from src to dest not involving nodes in BL
(defun shortest-path (src dest BL)
	; Base case if src is dest then return a distance 0
	(if (= src dest) (return-from shortest-path (list 0 (list dest))))
	; Return the shortest path from src to dest
	(best-path
		; Return the shortest paths from the next nodes
		(loop for (node distance) in (next_nodes src graph)
			; For each of the next nodes if node is blacklisted then return infinite path else return shortest path to dest from the next node
			for paths = (suffix_path src distance (if (in_list node BL) inf_path (shortest-path node dest (cons src BL)))) 
			collect paths
		)
	)
)

;(print (suffix_path 3 100 '(200 (4 5 6 7)) ))

;(print (best-path '( (1 2) (3 4) )))

(print (shortest-path 10 10 '()))