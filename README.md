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
    Empty String Input
        Input: ""
        Expected Output: 0
        Description: Verify that the calculator returns 0 for an empty string.

    Single Number Input
        Input: "1"
        Expected Output: 1
        Description: Verify that the calculator returns the same number when only one number is provided.

    Two Number Input (Comma Separated)
        Input: "1,2"
        Expected Output: 3
        Description: Verify that the calculator correctly sums two comma-separated numbers.

    Multiple Numbers Input (Comma and New Line Separated)
        Input: "1\n2,3"
        Expected Output: 6
        Description: Verify that the calculator correctly sums numbers separated by both commas and new lines.

    Input with New Line Only
        Input: "1\n2\n3"
        Expected Output: 6
        Description: Verify that the calculator correctly sums numbers separated only by new lines.

    Negative Number Input
        Input: "1,-2"
        Expected Output: Exception with message: "negatives not allowed: -2"
        Description: Verify that the calculator throws an exception when a negative number is included in the input.

    Multiple Negative Numbers Input
        Input: "1,-2,-3"
        Expected Output: Exception with message: "negatives not allowed: -2, -3"
        Description: Verify that the calculator throws an exception listing all negative numbers.

    Numbers Greater than 1000
        Input: "2,1001"
        Expected Output: 2
        Description: Verify that numbers greater than 1000 are ignored in the sum.

    Custom Delimiter (Single Character)
        Input: "//;\n1;2"
        Expected Output: 3
        Description: Verify that the calculator accepts a custom delimiter specified at the beginning.

    Custom Delimiter (Multi-Character)
        Input: "//[***]\n12***3"
        Expected Output: 15
        Description: Verify that the calculator accepts a multi-character custom delimiter.

    Ignoring Large Numbers with Custom Delimiter
        Input: "//;\n2;1001;3"
        Expected Output: 5
        Description: Verify that numbers greater than 1000 are ignored while using a custom delimiter.

    Negative Number with Custom Delimiter
        Input: "//;\n1;-2"
        Expected Output: Exception with message: "negatives not allowed: -2"
        Description: Verify that the calculator throws an exception when a negative number is included with a custom delimiter.

    Mixture of Delimiters
        Input: "//;\n1;2\n3,1001"
        Expected Output: 6
        Description: Verify that the calculator handles a mixture of delimiters correctly.

    Custom Delimiter Only (Without Numbers)
        Input: "//;\n"
        Expected Output: 0
        Description: Verify that the calculator returns 0 when only a custom delimiter is provided without numbers.
 

Start Test-driven approach

1. Write the smallest possible failing test: give input `"" assert output to be 0 ` .
2. Write the minimum amount of code that'll make it pass.
3. Refactor any assumptions, continue to pass this test. Do not add any code without a corresponding test.
