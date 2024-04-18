DROP DATABASE IF EXISTS BinaryBrosHotel;
CREATE DATABASE BinaryBrosHotel;
USE BinaryBrosHotel;

-- Table for Users
CREATE TABLE Users (
    userID INT PRIMARY KEY AUTO_INCREMENT,
    fullName VARCHAR(255) NOT NULL,
    userUsername VARCHAR(255) NOT NULL,
    userPassword VARCHAR(255) NOT NULL,
    userEmail VARCHAR(255) NOT NULL UNIQUE,
    Role ENUM('user', 'admin') NOT NULL
);

-- Table for Storing User Accounts
CREATE TABLE Accounts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fullName VARCHAR(255) NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL, 
    dateOfBirth DATE NOT NULL,
    address VARCHAR(255) NOT NULL
);

SELECT * FROM Accounts WHERE username = 'newUsername';

-- Table for Storing Room Information
CREATE TABLE Rooms (
    roomID INT PRIMARY KEY AUTO_INCREMENT,
    roomNumber VARCHAR(10) NOT NULL,
    roomType VARCHAR(50) NOT NULL,
    bedType VARCHAR(50),
    maxOccupancy INT,
    pricePerNight DECIMAL(10, 2),
    status ENUM('available', 'occupied', 'under maintenance') DEFAULT 'available'
);

INSERT INTO Rooms (roomNumber, roomType, bedType, maxOccupancy, pricePerNight, status) VALUES
('101', 'Single', 'Queen', 1, 100.00, 'available'),
('102', 'Double', 'Two Twins', 2, 120.00, 'available'),
('103', 'Suite', 'King', 4, 250.00, 'available'),
('104', 'Double', 'Queen', 2, 130.00, 'occupied'),
('105', 'Single', 'Single', 1, 90.00, 'available'),
('201', 'Suite', 'King', 4, 300.00, 'available'),
('202', 'Single', 'Queen', 1, 110.00, 'under maintenance');

-- Table for Storing Reservations
CREATE TABLE Reservations (
    reservationID INT PRIMARY KEY AUTO_INCREMENT,
    userID INT NOT NULL,
    roomID INT NOT NULL,
    checkInDate DATE NOT NULL,
    checkOutDate DATE NOT NULL,
    status ENUM('confirmed', 'canceled', 'checked_in', 'checked_out') NOT NULL DEFAULT 'confirmed',
    FOREIGN KEY (userID) REFERENCES Accounts(id),
    FOREIGN KEY (roomID) REFERENCES Rooms(roomID)
);

-- Table for Reservation Modifications (If Needed)
CREATE TABLE ReservationModifications (
    modificationID INT PRIMARY KEY AUTO_INCREMENT,
    reservationID INT NOT NULL,
    previousCheckInDate DATE NOT NULL,
    previousCheckOutDate DATE NOT NULL,
    newCheckInDate DATE NOT NULL,
    newCheckOutDate DATE NOT NULL,
    modificationDate DATE NOT NULL,
    FOREIGN KEY (reservationID) REFERENCES Reservations(reservationID)
);

-- Table for Generating Reports (If Needed)
CREATE TABLE Reports (
    reportID INT PRIMARY KEY AUTO_INCREMENT,
    reportType ENUM('occupancy', 'revenue') NOT NULL,
    period VARCHAR(255) NOT NULL,
    details TEXT NOT NULL
);