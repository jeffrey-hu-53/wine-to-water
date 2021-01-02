# wine-to-water
TestCaseGenerator class contains one method generateTests. It takes in the YAML configuration path as a String. The configuration should have the seed path and the test case 
output file path. Below is a sample seed.csv and a sample YAML configuration file.

Sample seed.csv
-------------------------------------
Field1,Field2,Field3,Field4,Field5
Value1,Value2,Value3,Value4,Value5

   
YAML FILE FORMAT
seed: Path of seed goes here
output: Path of output goes here
number: Number of test cases you want to generate goes here
instructions: A list of instructions for each field you want to manipulate goes under here
  - fieldName: Name of the field you want to produce a random value
    fieldType: The type of value you want to produce (**this field is NOT cap sensitive**)
    values: Values vary depending on the fieldType. Read more below.
 
There are 4 field types you can enter. Double, Integer, PickOne, and String.
If fieldType is Double, values will hold 3 numbers seperated by commas and surrounded with parentheses. The first two numbers indicate the range of the double. The range will
include the first number, but not the second. The third value represents the number of digits after the decimal place. For example, values: (10, 200, 5) will produce a random 
double from 10 to 200, including 10 and excluding 200, with 5 digits after the decimal point. It could output 10.00000, 167.37592, or 199.99999.

If fieldType is Integer, values will hold 2 numbers seperated by commas and surrounded with parentheses. The two numbers indicate the range of the integer. The range will
include the first number, but not the second. For example, values: (5, 17) will produce a random integer from 5 to 17, including 5 and excluding 17.

If fieldType is PickOne, values can hold any number of values seperated by commas and surrounded with parentheses. The generator will randomly choose a value from the group.
Each value has the same probability of being chosen. For example, values: (Adam, Ben, 148, 39.756) could output any 4 of these values.

If fieldType is String, values will hold 2 numbers seperated by commas and surrounded with parentheses. The two numbers indicate the range for the length of the randomly
generated string. The range will include the first number, but not the second. For example, values: (4, 9) will generate a random string with length 4, 5, 6, 7, or 8, but not
9. It could output pshc, ucnjeo, or mwovywbt.

Sample Yaml configuration file
-------------------------------------
seed: "src/test/resources/seed.csv"
output: "src/test/resources/output.csv"
number: 15000
instructions:
  - fieldName: Field1
    fieldType: string
    values: (3, 5)
  - fieldName: Field2
    fieldType: integer
    values: (10, 100)
  - fieldName: Field3
    fieldType: pickOne
    values: (Hello, Hey, Hi, Greetings)
  - fieldName: Field4
    fieldType: double
    values: (100, 1000, 2)
    
 
