# LibraryCrud
Month 2 Week 3 Task

### Descriptions
You are to Create a simple CRUD task of Library system with JPA using hibernate, use H2 in-memory as database
The Library should have record of all it's book in the db under book table
There should be student record in the dB under student table
A student is allowed to borrow one book and can only borrow another when he/she returns the previous and there should be a table mapping the students to the book borrowed
If a student tries to borrow another book while holding the previously borrowed book, he/she should get a response telling he/she to return the previous borrowed book but if there is no borrowed book record he/she would be given the book


### Required methods
* Create student(and persist to the dB)
* Create Books (and persist to the dB)
* Borrow book(you must be a student to borrow a book, so you have to accept the student's name and validate that he is present in the student's dB before issuing the requested book)
* Return the borrowed book

Write test cases for your implementations


##### NB:
1.	Your project must be a maven project
2.	 Write tests to cover all the methods to be written, before development begins
3.	Use Log4j to log all your outputs on the console.
4. 	Make logical assumptions where necessary.