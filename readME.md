# Reward Calculator
* ``Prerequisite``
  * `Good understanding of Java`
  * `Knowledge of RESTful Web Services`
  * `Knowledge of Maven`
  * `Knowledge of Spring Boot`
  * `Idea on Unit Test`

* ``Tools``
  * `java 1.8 version installed`
  * `maven 3+ version installed`
  * `IntelliJ IDEA, Eclipse, NetBeans or Spring Tool Suite`


## steps to run in your machine

    1. Either download or clone this project in your machine
    2. Once clone open it in any IDE and then run or run via commands
    3. mvn clean , mvn install, java -jar <jarName>.jar  Find jar in target

Once the application runs, it injects data to database using DataLoader Component
  `

      private void loadCustomer() {
        Customer ironman = new Customer(null, "ironman", "8147851654", "ironman@yopmail.com");
        Customer hulk = new Customer(null, "hulk", "8147851655", "hulk@yopmail.com");
        Customer thor = new Customer(null, "thor", "8147851656", "thor@yopmail.com");
        ironman = customerRepo.save(ironman);
        loadTransaction(ironman, 120F);
        hulk = customerRepo.save(hulk);
        loadTransaction(hulk, 90F);
        thor = customerRepo.save(thor);
        loadTransaction(thor, 40F);
    }
  `
  `     

    private void loadTransaction(Customer customer, Float amount) {
        Transaction transaction = new Transaction(null, customer, amount);
        transactionRepo.save(transaction);
    }`

Open any browser
    
http://localhost:8080/customer-id/1

  `
  {
  "status": true,
  "message": "Point calculated successfully",
  "data": {
  "customerId": 1,
  "totalPoints": 90
  }
  }`

http://localhost:8080/customer-id/2

`
{
"status": true,
"message": "Point calculated successfully",
"data": {
"customerId": 2,
"totalPoints": 40
}
}`
## PointControllerTest
    1. MockMvc is used and TransactionRepo is being mocked
    2. Controller level testing is done
