# RutaExpress API

REST API for managing deliveries in the RutaExpress delivery system.

## Description

This API provides endpoints for managing users and delivery requests (rides). It allows restaurants to create delivery requests and delivery personnel to accept and complete them.

## Base URL

**Local Development:**
```
http://localhost:5001
```

**Production:**
```
https://rutaexpress-api.onrender.com
```

---

## API Endpoints

### Users

#### Register User
**POST** `/users/register`

Create a new user account.

**Request Body:**
```json
{
  "name": "Juan Perez",
  "email": "juan@example.com",
  "password": "123456",
  "role": "delivery"
}
```

**Response:**
```json
{
  "data": {
    "id": "1733512345678",
    "name": "Juan Perez",
    "email": "juan@example.com",
    "role": "delivery",
    "isActive": true,
    "createdAt": "2025-12-06T18:30:45.678Z"
  },
  "responseCode": "SUCCESSFUL",
  "message": "User registered successfully"
}
```

#### Authenticate User
**POST** `/users/auth`

Login with email and password.

**Request Body:**
```json
{
  "email": "juan@example.com",
  "password": "123456"
}
```

**Response:**
```json
{
  "data": {
    "id": "1733512345678",
    "name": "Juan Perez",
    "email": "juan@example.com",
    "role": "delivery"
  },
  "responseCode": "SUCCESSFUL",
  "message": "Authentication successful"
}
```

#### List All Users
**GET** `/users`

Get all registered users.

**Response:**
```json
{
  "data": [
    {
      "id": "1733512345678",
      "name": "Juan Perez",
      "email": "juan@example.com",
      "role": "delivery",
      "isActive": true,
      "createdAt": "2025-12-06T18:30:45.678Z"
    }
  ],
  "responseCode": "SUCCESSFUL",
  "message": "Users retrieved successfully"
}
```

#### Get User by ID
**GET** `/users/:id`

Get a specific user by ID.

**Response:**
```json
{
  "data": {
    "id": "1733512345678",
    "name": "Juan Perez",
    "email": "juan@example.com",
    "role": "delivery",
    "isActive": true
  },
  "responseCode": "SUCCESSFUL",
  "message": "User found"
}
```

---

### Rides (Deliveries)

#### Create Ride
**POST** `/rides`

Create a new delivery request.

**Request Body:**
```json
{
  "restaurantName": "Pizza Hut",
  "customerAddress": "Av. Central, San Jose"
}
```

**Response:**
```json
{
  "data": {
    "id": "1733512456789",
    "restaurantName": "Pizza Hut",
    "customerAddress": "Av. Central, San Jose",
    "status": "pending",
    "deliveryPersonId": null,
    "createdAt": "2025-12-06T18:35:56.789Z"
  },
  "responseCode": "SUCCESSFUL",
  "message": "Ride created successfully"
}
```

#### List All Rides
**GET** `/rides`

Get all delivery requests.

**Response:**
```json
{
  "data": [
    {
      "id": "1733512456789",
      "restaurantName": "Pizza Hut",
      "customerAddress": "Av. Central, San Jose",
      "status": "pending",
      "deliveryPersonId": null,
      "createdAt": "2025-12-06T18:35:56.789Z"
    }
  ],
  "responseCode": "SUCCESSFUL",
  "message": "Rides retrieved successfully"
}
```

#### Get Ride by ID
**GET** `/rides/:id`

Get a specific ride by ID.

**Response:**
```json
{
  "data": {
    "id": "1733512456789",
    "restaurantName": "Pizza Hut",
    "customerAddress": "Av. Central, San Jose",
    "status": "pending",
    "deliveryPersonId": null
  },
  "responseCode": "SUCCESSFUL",
  "message": "Ride found"
}
```

#### Accept Ride
**PUT** `/rides/:id/accept`

Delivery person accepts a ride.

**Request Body:**
```json
{
  "deliveryPersonId": "1733512345678"
}
```

**Response:**
```json
{
  "data": {
    "id": "1733512456789",
    "status": "accepted",
    "deliveryPersonId": "1733512345678"
  },
  "responseCode": "SUCCESSFUL",
  "message": "Ride accepted successfully"
}
```

#### Reject Ride
**PUT** `/rides/:id/reject`

Delivery person rejects a ride.

**Response:**
```json
{
  "data": {
    "id": "1733512456789",
    "status": "rejected"
  },
  "responseCode": "SUCCESSFUL",
  "message": "Ride rejected successfully"
}
```

#### Mark as Delivered
**PUT** `/rides/:id/deliver`

Mark a ride as delivered.

**Response:**
```json
{
  "data": {
    "id": "1733512456789",
    "status": "delivered"
  },
  "responseCode": "SUCCESSFUL",
  "message": "Ride marked as delivered"
}
```

#### Delete Ride
**DELETE** `/rides/:id`

Delete a ride.

**Response:**
```json
{
  "responseCode": "SUCCESSFUL",
  "message": "Ride deleted successfully"
}
```

#### Get Rides by Status
**GET** `/rides/status/:status`

Get rides filtered by status.

**Status values:** `pending`, `accepted`, `rejected`, `delivered`

**Example:** `GET /rides/status/pending`

**Response:**
```json
{
  "data": [
    {
      "id": "1733512456789",
      "restaurantName": "Pizza Hut",
      "customerAddress": "Av. Central, San Jose",
      "status": "pending"
    }
  ],
  "responseCode": "SUCCESSFUL",
  "message": "Rides retrieved successfully"
}
```

---

## Installation

### Requirements
- Node.js v14 or higher
- npm

### Steps

1. Install dependencies:
```bash
npm install
```

2. Start the server:
```bash
npm start
```

The server will run on port 5001.

For development with auto-reload:
```bash
npm run dev
```

---

## Testing the API

### Using cURL

Register a user:
```bash
curl -X POST http://localhost:5001/users/register \
  -H "Content-Type: application/json" \
  -d '{"name":"Juan Perez","email":"juan@example.com","password":"123456","role":"delivery"}'
```

Create a ride:
```bash
curl -X POST http://localhost:5001/rides \
  -H "Content-Type: application/json" \
  -d '{"restaurantName":"Pizza Hut","customerAddress":"Av. Central, San Jose"}'
```

List all rides:
```bash
curl http://localhost:5001/rides
```

Accept a ride:
```bash
curl -X PUT http://localhost:5001/rides/RIDE_ID/accept \
  -H "Content-Type: application/json" \
  -d '{"deliveryPersonId":"USER_ID"}'
```

---

## Project Structure

```
RutaExpress-API/
├── server.js              # Main server file
├── routes/
│   └── apiRoutes.js      # API routes definition
├── controllers/
│   ├── UserController.js # User logic
│   └── RideController.js # Ride logic
├── models/
│   ├── User.js          # User model
│   └── Ride.js          # Ride model
├── data/
│   ├── users.json       # User data storage
│   └── rides.json       # Ride data storage
└── package.json         # Dependencies
```

---

## Technologies Used

- Node.js - Runtime environment
- Express - Web framework
- body-parser - Parse request bodies
- cors - Enable CORS
- underscore - Utility functions

---

## Data Models

### User
```javascript
{
  id: string,
  name: string,
  email: string,
  role: string,        // 'restaurant', 'delivery', 'customer'
  isActive: boolean,
  createdAt: string
}
```

### Ride
```javascript
{
  id: string,
  restaurantName: string,
  customerAddress: string,
  status: string,      // 'pending', 'accepted', 'rejected', 'delivered'
  deliveryPersonId: string,
  createdAt: string,
  updatedAt: string
}
```

---

## Deployment

### Deploy to Render

1. Create a Render account
2. Create a new Web Service
3. Connect your GitHub repository
4. Configure build settings:
   - Build Command: `npm install`
   - Start Command: `npm start`
5. Deploy

---

## Notes

- Data is stored in JSON files (users.json, rides.json)
- Data persists between server restarts
- CORS is enabled for all origins
- No authentication tokens (consider adding JWT for production)

---

## License

ISC
