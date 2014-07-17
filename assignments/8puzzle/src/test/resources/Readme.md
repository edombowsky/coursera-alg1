**Input files.** This directory contains many sample puzzle input files.

* The shortest solution to `puzzle[T].txt` requires exactly *T* moves.
* The shortest solution to `puzzle4x4-hard1.txt` and `puzzle4x4-hard2.txt` are `38` and `47`, respectively.
* **Warning:** `puzzle36.txt` is especially difficult. 

**Priority queue trace.**

* Here are the contents of our priority queue (sorted by priority) just before dequeueing each node when using the Manhattan priority function on `puzzle04.txt`.

        Step 0:    priority  = 4
                   moves     = 0
                   manhattan = 4
                   3            
                    0  1  3     
                    4  2  5     
                    7  8  6     

        Step 1:    priority  = 4    priority  = 6
                   moves     = 1    moves     = 1
                   manhattan = 3    manhattan = 5
                   3                3            
                    1  0  3          4  1  3     
                    4  2  5          0  2  5     
                    7  8  6          7  8  6     

        Step 2:    priority  = 4    priority  = 6    priority  = 6
                   moves     = 2    moves     = 1    moves     = 2
                   manhattan = 2    manhattan = 5    manhattan = 4
                   3                3                3            
                    1  2  3          4  1  3          1  3  0     
                    4  0  5          0  2  5          4  2  5     
                    7  8  6          7  8  6          7  8  6     

        Step 3:    priority  = 4    priority  = 6    priority  = 6    priority  = 6    priority  = 6
                   moves     = 3    moves     = 3    moves     = 2    moves     = 3    moves     = 1
                   manhattan = 1    manhattan = 3    manhattan = 4    manhattan = 3    manhattan = 5
                   3                3                3                3                3            
                    1  2  3          1  2  3          1  3  0          1  2  3          4  1  3     
                    4  5  0          4  8  5          4  2  5          0  4  5          0  2  5     
                    7  8  6          7  0  6          7  8  6          7  8  6          7  8  6     

        Step 4:    priority  = 4    priority  = 6    priority  = 6    priority  = 6    priority  = 6    priority  = 6
                   moves     = 4    moves     = 3    moves     = 4    moves     = 2    moves     = 3    moves     = 1
                   manhattan = 0    manhattan = 3    manhattan = 2    manhattan = 4    manhattan = 3    manhattan = 5
                   3                3                3                3                3                3            
                    1  2  3          1  2  3          1  2  0          1  3  0          1  2  3          4  1  3     
                    4  5  6          0  4  5          4  5  3          4  2  5          4  8  5          0  2  5     
                    7  8  0          7  8  6          7  8  6          7  8  6          7  0  6          7  8  6     

       There were a total of 10 search nodes enqueued and 5 search nodes dequeued. In general, the number of search nodes enqueued and dequeued may vary slightly, depending the order in which the search nodes with equal priorities come off the priority queue, which depends on the order in which neighbors() returns the neighbors of a board. However, for this input, there are no such ties, so you should have exactly 10 search nodes enqueued and 5 search nodes dequeued.


* The contents of our priority queue (sorted by priority) just before dequeueing each node when using the Hamming priority function on `puzzle04.txt` turns out to be identical to the results above: for this input file, throughout the A* algorithm, a block is never more than one position away from its goal position, which implies that the Hamming function and the Manhattan functions are equal. 