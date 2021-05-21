### **Project Description**
* This Banking API will manage the bank accounts of its users . The Bank has three types of users: Admins, Employees, and Standard users. Admins and Employees count as Standard users with additional abilities. All users are able to log in and out using their username and password. All users are able to update their personal information, such as username, password, first name, last name, and email. Standard users are only able to see their accounts. Employees are able to view customer information, but not modify it in any way. Admins can view information as well as modify it. 

### **Features**
•Admins can successfully withdraw, deposit, and transfer funds; Employees are not able to do this as it is not in their role's range.
•Standard users can view their account information, but not anyone else's.
•Employees can view all users and accounts, but not make any changes.
* **When** users succesfully log in or out, personalized messages are displayed for them.
•Admins and Employees can search for an account by account id, user id, and status id. 

### **To-do List:**
•Create ability for users to withdraw, deposit, and transfer from their own account.
•Allow Admins to add accounts to already existing users.
•Be able to create a user and a corresponding account at the same time.
•Allow Admins to delete users and their corresponding accounts. 

### **Getting Started**

Prior to starting this application, you first need to make sure:
1. You have successfully created a connection between your relational database (for me, I used AWS) and the Java application. The host URL I have in my application is specific to my IP address as declared in AWS. 
2. Have both a server (such as Tomcat) and a GUI (such as Postman) downloaded on your device.

After opening the Java application and checking the database connection, first start the Tomcat server by right clicking the project and selecting "Run As... Run on Server". This will open a tab with a link that you will copy and paste into Postman. 

### **Usage**

After copying the link into Postman or your related GUI, you are able to add specific endpoints. Follow the following structures for the endpoints as well as inputs.
When inputing data into the parameters, select the Body option, raw, and JSON as the language it will be written in.

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
* **URL:** `/accounts/withdraw/{accountId}`

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
* **URL:** `/accounts/deposit/{accountId}`

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
* **URL:** `/accounts/transfer/{accountId1}`

* **Method:** `POST`

* **Allowed Roles:** `Admin` or if the source account belongs to the current user

* **Request:**
  ```json
  {
    "accountId1": int,
    "accountId2": int,
    "amount": double
  }
  ```

* **Response:**
  ```json
  {
    "message": "${amount} has been withdrawn from Account #{accountId1}. ${amount} has been
	deposited to Account #{accountId2}"
  }
  ```  

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

####Find Accounts####
* **URL:** `/accounts`

* **Method:** `GET`

* **Allowed Roles:** `Employee` or `Admin`

* **Response:**
  [
    Account
  ]

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
  
* **Method:** `GET`

* **Allowed Roles:** `Employee` or `Admin` or if the id provided matches the id of the current user

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

### **License**
This project using the following license: Java 8 