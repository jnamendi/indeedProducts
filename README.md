# README #

This README would normally document whatever steps are necessary to get your application up and running.

### What is this repository for? ###

This is a demo application for indeed that has 2 REST services that perform filtering and sorting
operations on a collection of products.

### How do I get set up? ###

Clone the git repository from https://github.com/jnamendi/indeedProducts master branch, this project uses java 17. Your IDE should automatically recognize the maven setup.

### REST API Documentation ###
This application uses OpenAPI library to show the documentation related to REST APIs, to view it go to http://localhost:8080/swagger-ui/index.html

### Solution explanation ###

* #### Data Source
    It was not clear to me whether to use a plain json file or create a database for the data. So I decided to use H2 in memory feature adding the table creation scripts and data inserts into the project.
* #### Service and data access layer
    In this case I decide to use JPARepository built in methods for basic filtering and sorting scenarios. This way I just had to define findByPriceBetween and getSortedProductsByPrice in my repository Interface.
  * #### filtering and sorting other options
      Another option for this would have been to get all the records or having a preset array of objects and filter/Sort them using Collections API:
    * FILTER
      ```java
         List<Product> filteredByPrice = productRepository.findAll().stream()
                  .filter(product -> product.getPrice() >= initialRange && product.getPrice() <= finalRange)
                  .toList();
        ```
    * SORT
    ```java
          List<Product> sortedByPrice = productRepository.findAll().stream()
                .sorted(Comparator.comparingInt(Product::getPrice))
                .toList();
        ```
  * Another option is to use the @query annotation to define a custom JPQL query, but this is generally used for complex operations.
  * One more option is to use Specifications, these allow to build powerful dynamic queries, especially when multiple filters are needed.   
* * #### HTTP code handle
   To handle the HTTP codes I decide to create a global exception handler with the @RestControllerAdvice annotation because this provides a centralized exception handling from all the controllers and allows dynamic scaling. 
