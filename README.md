# Generate-tokens-and-validate-them

## How to start
script should bring up all services and make FE available on localhost:8080
>start.sh

### RESTful service generator
 - port 8081
 - Request POST:
   - localhost:8081/generator
   - {
     "digits":[1,2,3,4]
     }
 - Response:
   - {
     "token": "3123-2111-4123-1341"
     }

## Requirements
React web application and RESTful service for tokens generation and validation them using Luhn algorithm

A web-based application that allows you to generate tokens and validate them.

Token has a format of XXXX-XXXX-XXXX-XXXX, and consists of the 0-9 digits.

User is able to choose which digits are available for token generation in the UI, e.g. available digits are: 2,4,7,9,0 - it means that token can consist only of them, e.g. 2249-4472-0279-9420

User is able to initiate token creation via UI, in this case available digits are being passed to the GeneratorService and created token is displayed on UI. Generator creates a token randomly based on available digits provided.

User is able to validate the created token via UI, in this case the token is being passed to ValidatorService and the result of validation is displayed in UI. Validator uses Lunh algorithm to check if token is valid or not: https://en.wikipedia.org/wiki/Luhn_algorithm 

