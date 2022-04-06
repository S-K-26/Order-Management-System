
# Order Management System
It is a simple Spring Boot project to maintain order details.



## Items
### List Of All Items
#### Request

```http
  http://localhost:8080/items
```
#### Response
<img src ="Screenshots/Items.PNG" width="700">

### Edit Items
#### Request
```http
  http://localhost:8080/items/edit
```
#### Response
<img src ="Screenshots/ItemsEdit.PNG" width="700">

### New item
#### Request
```http
   http://localhost:8080/items/new
```
#### Response
<img src ="Screenshots/ItemAdd.PNG" width="700">

### Delete item
#### Request
```http
  DELETE /items/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `itemId`      | `Integer` | Deletes items from database with specified Id. |


## Orders
### List of all orders
#### Request
```http
  http://localhost:8080/orders
```

#### Response

<img src ="Screenshots/Orders.PNG" width="700">
