
# This assignment asks you to write an assembly-language program that
# multiplies two integers.
        
# Namely, your task is to compute $0 * $1 and store the result in $2.
# The result is guaranteed to be in the range 0,1,...,65535.
# Both $0 and $1 should be viewed as unsigned integers.
# Complete and submit the part delimited by "----" and indicated by
# a "nop" below.

# Here is some wrapper code to test your solution:

        mov     $0, 82          # load some values to registers $0,$1
        mov     $1, 430

# Your solution starts here ...
# ------------------------------------------
        
        nop                     # ready for your code over here

# ------------------------------------------
# ... and ends here 

        hlt                     # the processor stops here

# (at halt we should have 35260 in $2)


