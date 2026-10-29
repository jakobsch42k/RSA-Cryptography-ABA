# RSA-Cryptography-ABA
This is the repositpry for my RSA-Encoder. It is part of my ABA. Its purpose is to see the difference different key lenghts have on safety and runtime

## Layout
| Path | Content |
|---|---|
| `src/main/java/aba/math` | Number-theory building blocks |
| `src/main/java/aba/primes` | Primality tests and prime generation |
| `src/main/java/aba/rsa` | Keys, key generation, encryption and decryption |
| `src/main/java/aba/attacks` | Factorisation of weak keys |
| `src/main/java/aba/benchmark` | Measurement series, write CSV to `data/` |
| `src/test/java/aba` | JUnit 5 tests |
| `data/` | Raw measurement results (CSV) |
| `plots/` | Plots generated from `data/` |

## Build and test
Requires JDK 21 and Maven (IntelliJ ships one).

```
mvn test
```
