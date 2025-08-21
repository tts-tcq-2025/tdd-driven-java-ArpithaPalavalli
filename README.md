# TDD Driven StringCalculator

Build a StringCalculator functionality that can take up to two numbers, separated by commas, and will return their sum. 
for example “” or “1” or “1,2” as inputs.

> DO NOT jump into implementation! Read the example and the starting task below.

- For an empty string it will return 0
- Allow the Add method to handle an unknown amount of numbers
- Allow the Add method to handle new lines between numbers (instead of commas).
  - the following input is ok: “1\n2,3” (will equal 6)
  - the following input is NOT ok: “1,\n” (not need to prove it - just clarifying)
- Support different delimiters : to change a delimiter, the beginning of the string will contain a separate line that looks like this: “//[delimiter]\n[numbers…]” for example “//;\n1;2” should return three where the default delimiter is ‘;’ .
the first line is optional. all existing scenarios should still be supported
- Calling Method with a negative number will throw an exception “negatives not allowed” - and the negative that was passed. if there are multiple negatives, show all of them in the exception message.
- Numbers bigger than 1000 should be ignored, so adding 2 + 1001 = 2
- Delimiters can be of any length with the following format: “//[delimiter]\n” for example: “//[***]\n1***2***3” should return 6

## Tasks



Establish quality parameters:

- Ensure  maximum complexity (CCN) per function == 3

- Ensure 100% line and branch coverage at every step

 ## Test Specification
 | Test Case ID | Description                                            | Given Input                   | When Action         | Expected Outcome                                          |
|--------------|--------------------------------------------------------|-------------------------------|---------------------|----------------------------------------------------------|
| TC001        | Empty String Input                                     | `""`                          | Calculator is called | Result should be `0`                                     |
| TC002        | Single Number Input                                    | `"1"`                         | Calculator is called | Result should be `1`                                     |
| TC003        | Two Number Input (Comma Separated)                    | `"1,2"`                       | Calculator is called | Result should be `3`                                     |
| TC004        | Multiple Numbers Input (Comma and New Line Separated) | `"1\n2,3"`                    | Calculator is called | Result should be `6`                                     |
| TC005        | Input with New Line Only                               | `"1\n2\n3"`                   | Calculator is called | Result should be `6`                                     |
| TC006        | Negative Number Input                                  | `"1,-2"`                      | Calculator is called | Exception message should be "negatives not allowed: -2" |
| TC007        | Multiple Negative Numbers Input                        | `"1,-2,-3"`                   | Calculator is called | Exception message should be "negatives not allowed: -2, -3" |
| TC008        | Numbers Greater than 1000                             | `"2,1001"`                    | Calculator is called | Result should be `2`                                     |
| TC009        | Custom Delimiter (Single Character)                   | `"//;\n1;2"`                  | Calculator is called | Result should be `3`                                     |
| TC010        | Custom Delimiter (Multi-Character)                    | `"//[***]\n12***3"`           | Calculator is called | Result should be `15`                                    |
| TC011        | Ignoring Large Numbers with Custom Delimiter           | `"//;\n2;1001;3"`             | Calculator is called | Result should be `5`                                     |
| TC012        | Negative Number with Custom Delimiter                  | `"//;\n1;-2"`                 | Calculator is called | Exception message should be "negatives not allowed: -2" |
| TC013        | Mixture of Delimiters                                  | `"//;\n1;2\n3,1001"`          | Calculator is called | Result should be `6`                                     |
| TC014        | Custom Delimiter Only (Without Numbers)               | `"//;\n"`                     | Calculator is called | Result should be `0`                                     |

Start Test-driven approach

1. Write the smallest possible failing test: give input `"" assert output to be 0 ` .
2. Write the minimum amount of code that'll make it pass.
3. Refactor any assumptions, continue to pass this test. Do not add any code without a corresponding test.
