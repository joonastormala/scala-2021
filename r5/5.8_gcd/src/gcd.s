
# This assignment asks you to write an assembly-language program that
# computes the greatest common divisor of two positive integers.
        
# Namely, your task is to compute the greatest common divisor of $0 and $1.
# The result must be stored into $2. Both $0 and $1 are guaranteed to
# be nonzero. Complete and submit the part delimited
# by "----" and indicated by a "nop" below.
#
# What makes this task challenging is that our "armlet" architecture
# has no hardware support for division, so you will have to make do
# without.  Note that there is a limit to the number of ticks your 
# program may run. Even if your program produces correct output you 
# may need to optimize it if it's not fast enough.
#
# Hint: Greatest common divisors may be computed using, for example,
# the Euclidean algorithm or the Binary GCD algorithm (faster).

# Here is some wrapper code to test your solution:

        mov     $0, 26064       # load values to registers $0,$1
        mov     $1, 4706

# Your solution starts here ...
# ------------------------------------------
        
        nop                     # ready for your code over here

# ------------------------------------------
# ... and ends here 

        hlt                     # the processor stops here

# (at halt we should have 362 in $2)


