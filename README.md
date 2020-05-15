# BankingSystem-Backend

## Accounting System


Technology Used:
Java, Spring, Spring Batch, Spring Security, PostgreSQL, Microservices, Maven, JUnit, Liquibase,Fast2SMS


Functionalities:

⦁	Spring: Forms the Outline of Whole project.

⦁	Spring Security: Basic Authentication mechanism on login and Token Based Authentication for every subsequent call from the User Interface.

⦁	Junit: Unit Testing and Integration Testing available for Online Customer System.

⦁	Spring Batch: As there are scenarios where payments needs to be done on any scheduled date and multiple Transaction to be done in a single day(Eg. payment of Salaries), I have used Spring batch to ease up the Stress on the application environment.

⦁	Liquibase: Used for Managing database Entries.


Working of Every Module:

## Backffice System:

1.  Admin would have two roles -Capturer and Authoriser.
```bash

  a.  Capturer would be responsible for feeding the details of prospect customer into the System,
      and change the Details of Customer whenever requested by the Authoriser.
  
  b.  Authoriser would be able to approve or decline the request of Account creation submitted
      by the capturer. Once Approved, the Customer account will be created along with 
      Unique Account Number and credentials for login would be sent to the Phone number of 
      Account holder.If Authoriser Decline the Request providing the reason for 
      Disapproval of account, it would be sent back to Capturer for getting the data updated.
  
  c.  Both the Capturer and Authhoriser would be able to login using their own crentials,
      which needs to be manually entered into the database at this point of time.
  
  ```
 
 ## BankData:
 
 ```bash
 1.   BankData is a jar which which is used in other three projects. 
 
      It contains the Common functionalities across the project and thus,  
      
      It can be used in all the project as a library.
      
      BankData is based on following DRY(Do not repeat Yourself) principle.
  ```
  
 ## OnlineBanking:
 
 1.   This Module is for Customer Facing Application. In this Customer would be able to perform many functions.Eg.
 
    a. Login into their own account using credentials received through SMS.
    
    b. Check the Balance on their Account.
    
    c. View Transaction History on all the Transaction Done till the current date.
    
    d. Schedule a Transaction(Future-Dated)
    
 ## TransactionScheduling:
 
```
 1.   This Module is a Spring Batch Application which runs in the background, 
 
      and process the future dated Transaction. There is a Cron Job running every 40 seconds
 
      which which the data which needs to be processed. It will complete the job and
      
      write the transaction log to the database.
```
 
 Thank You!
