# Endpoints
The below endpoints generally follow a RESTful pattern. Where the URI describes the relevant resource and the HTTP Method describes the action to perform. Path variables (e.g. `/:userId`) are used to identify specific resources as part of the URI. These are placeholders, such as for a userId. If not otherwise specified, the response status code should be `200 OK`.
## Security
  Security should be handled through session storage.
  If a user does not have permission to access a particular endpoint it should return the following:
  * **Status Code:** `401 UNAUTHORIZED`
    **Content:**
    ```json
    {
      "message": "The requested action is not permitted"
    }
    ```
    Occurs if they do not have the appropriate permissions.

## RPC Endpoints
These endpoints are not RESTful, but are included to more conveniently simulate user actions

### **Login**
* **URL:** `/login`

* **Method:** `POST`

* **Request:**
  ```json
  {
    "username": String,
    "password": String
  }
  ```

* **Response:**
  ```json
  User
  ```

* **Error Response:**
  * **Status Code:** `400 BAD REQUEST`

  ```json
  {
    "message": "Invalid Credentials"
  }
  ```

### **Logout**
* **URL:** `/logout`

* **Method:** `POST`

* **Response:**
  ```json
  {
    "message": "You have successfully logged out {username}"
  }
  ```
* **Error Response:**
  * **Status Code:** `400 BAD REQUEST`

  ```json
  {
    "message": "There was no user logged into the session"
  }
  ```

### **Register**
* **URL:** `/register`

* **Method:** `POST`

* **Allowed Roles:** `Admin`

* **Request:**
  Note: All fields must be included and the userId should be zero
  ```json
  User
  ```

* **Response:**
  Note: The userId should be updated
  * **Status Code:** `201 CREATED`
  ```json
  User
  ```

* **Error Response:**
  Note: In case username or email is already used
  * **Status Code:** `400 BAD REQUEST`
  ```json
  {
    "message": "Invalid fields"
  }
  ```

### **Withdraw**
* **URL:** `/accounts/withdraw`

* **Method:** `POST`

* **Allowed Roles:** `Admin` or if the account belongs to the current user

* **Request:**
  ```json
  {
    "accountId": int,
    "amount": double
  }
  ```

* **Response:**
  ```json
  {
    "message": "${amount} has been withdrawn from Account #{accountId}"
  }
  ```

### **Deposit**
* **URL:** `/accounts/deposit`

* **Method:** `POST`

* **Allowed Roles:** `Admin` or if the account belongs to the current user

* **Request:**
  ```json
  {
    "accountId": int,
    "amount": double
  }
  ```

* **Response:**
  ```json
  {
    "message": "${amount} has been deposited to Account #{accountId}"
  }
  ```

### **Transfer**
* **URL:** `/accounts/transfer`

* **Method:** `POST`

* **Allowed Roles:** `Admin` or if the source account belongs to the current user

* **Request:**
  ```json
  {
    "sourceAccountId": int,
    "targetAccountId": int,
    "amount": double
  }
  ```

* **Response:**
  ```json
  {
    "message": "${amount} has been transferred from Account #{sourceAccountId} to Account #{targetAccountId}"
  }
  ```

## RESTful Endpoints
These endpoints *are* RESTful, and generally provide basic CRUD operations for Employees/Admins

### **Find Users**
* **URL:** `/users`

* **Method:** `GET`

* **Allowed Roles:** `Employee` or `Admin`

* **Response:**
  ```json
  [
    User
  ]
  ```

### **Find Users By Id**
* **URL:** `/users/:id`

* **Method:** `GET`

* **Allowed Roles:** `Employee` or `Admin` or if the id provided matches the id of the current user

* **Response:**
  ```json
  User
  ```

### **Update User**
* **URL:** `/users`

* **Method:** `PUT`

* **Allowed Roles:** `Admin` or if the id provided matches the id of the current user

* **Request:**
  Note: All fields must be included
  ```json
  User
  ```

* **Response:**
  ```json
  User
  ```

### **Find Accounts**
* **URL:** `/accounts`

* **Method:** `GET`

* **Allowed Roles:** `Employee` or `Admin`

* **Response:**
  ```json
  [
    Account
  ]
  ```

### **Find Accounts By Id**
* **URL:** `/accounts/:id`

* **Method:** `GET`

* **Allowed Roles:** `Employee` or `Admin` or if the account belongs to the current user

* **Response:**
  ```json
  Account
  ```

### **Find Accounts By Status**
* **URL:** `/accounts/status/:statusId`

* **Method:** `GET`

* **Allowed Roles:** `Employee` or `Admin`

* **Response:**
  ```json
  [
    Account
  ]
    ```

### **Find Accounts By User**
* **URL:** `/accounts/owner/:userId`
  For a challenge you could do this instead: `/accounts/owner/:userId?accountType=type`

* **Method:** `GET`

* **Allowed Roles:** `Employee` or `Admin` or if the id provided matches the id of the current user

* **Response:**
  ```json
  [
    Account
  ]
  ```

### **Submit Account**
* **URL:** `/accounts`

* **Method:** `POST`

* **Allowed Roles:** `Employee` or `Admin` or if the account belongs to the current user

* **Request:**
  The accountId should be 0
  ```json
  Account
  ```

* **Response:**
  * **Status Code:** `201 CREATED`

  ```json
  Account
  ```


### **Update Account**
* **URL:** `/accounts`

* **Method:** `PUT`

* **Allowed Roles:** `Admin`

* **Request:**
  Note: All fields must be included
  ```json
  Account
  ```

* **Response:**
  ```json
  Account
  ```



***Project Description***
This Banking API will manage the bank accounts of its users . The Bank has three types of users: Admins, Employees, and Standard users. Admins and Employees count as Standard users with additional abilities. All users are able to log in and out using their username and password. All users are able to update their personal information, such as username, password, first name, last name, and email. Standard users are only able to see their accounts. Employees are able to view customer information, but not modify it in any way. Admins can view information as well as modify it. 

***Features***
•Admins can successfully withdraw, deposit, and transfer funds; Employees are not able to do this as it is not in their role's range.
•Standard users can view their account information, but not anyone else's.
•Employees can view all users and accounts, but not make any changes.
•When users succesfully log in or out, personalized messages are displayed for them.

***To-do List:***
•Create ability for users to withdraw, deposit, and transfer from their own account.
•Allow admits to add accounts to already existing users.
•Be able to create a user and a corresponding account at the same time.
•Allow Admins to delete users and their corresponding accounts. 

***Getting Started***
**Include Windows and Unix commands**

Prior to starting this application, you first need to make sure:
1. You have successfully created a connection between your relational database (for me, I used AWS) and the Java application. The host URL I have in my application is specific to my IP address from AWS. 
2. Have both a server (such as Tomcat) and a GUI (such as Postman) downloaded on your device.

After opening the Java application and checking the database connection, first start the Tomcat server by right clicking the project and selecting "Run As Server". This will respond with an link that we will copy and past into Postman. 

***Usage***



***License***
This project using the following license: Java 8 