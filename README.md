# RSA Crack
This was a small homework assignment for my cryptography course. 
## Summary
Find the primes `p` and `q` and the exponent `d` that were used to generate an RSA public key.
## Compile
Compile with `javac CrackRsa.java`
## Run
Run with `java CrackRsa <e> <n>`, where `e` is the exponent and `n` is the modulus of the public key. 
## How do I know it worked
The program will output the primes `p` and `q` which were used to generate the public key, as well `d`, which is the multiplicative inverse of `e mod n`. The private key that we have just hacked is `p * q` and `d`. 