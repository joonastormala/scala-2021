# This assignment asks you to write an assembly-language program similar to 
# the previous remainder computation exercise, except this time for 32-bit 
# numbers. This means the numbers will not fit into one register.
        
# Your task is to write an assembly-language program that computes the 
# remainder when dividing two 32-bit integers. You are given a memory
# address in $0. The four words at and after the address contains the
# dividend and divisor, in order, stored in big-endian format. The
# result must be stored in registers $1 for the high word and $2 for
# the low word.

# Hint: Think carefully about how you access the memory and what
# data you need to have in registers at each time.

# Here is some wrapper code to test your solution:

        # Actual variables
        mov     $0, 1000        # start address
        mov     $1, 50277       # upper word of dividend
        mov     $2, 13824       # lower word of dividend
        mov     $3, 31          # upper word of divisor
        mov     $4, 60700       # lower word of divisor

        # Memory code
        sto     $0, $1          # store everything to memory
        mov     $1, 1
        add     $0, $0, $1
        sto     $0, $2
        add     $0, $0, $1
        sto     $0, $3
        add     $0, $0, $1
        sto     $0, $4
        sub     $0, $0, 3       # restore start address
        eor     $1, $1, $1      # clear registers
        mov     $2, $1
        mov     $3, $2
        mov     $4, $2

# Your solution starts here ...
# ------------------------------------------

        nop

# ------------------------------------------
# ... and ends here 

        hlt                     # the processor stops here

# (at halt we should have 25 in $1 and 23512 in $2)

