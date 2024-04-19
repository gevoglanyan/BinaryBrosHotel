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
    Role ENUM('User', 'Admin') NOT NULL
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
    status ENUM('Available', 'Occupied', 'Under Maintenance') DEFAULT 'Available'
);

--Can Delete This Later On (Will Leave Here For Now)
INSERT INTO Rooms (roomNumber, roomType, bedType, maxOccupancy, pricePerNight, status) VALUES
('100', 'Single', 'Twin', 1, 50.00, 'Available'),
('101', 'Single', 'Twin', 1, 50.00, 'Available'),
('102', 'Single', 'Twin', 1, 50.00, 'Available'), 
('103', 'Single', 'Twin', 1, 100.00, 'Available'),
('104', 'Single', 'Twin', 1, 100.00, 'Available'),
('105', 'Single', 'Twin', 1, 100.00, 'Available'),
('200', 'Double', 'Full', 1, 200.00, 'Available'),
('201', 'Double', 'Full', 1, 200.00, 'Available'),
('202', 'Double', 'Full', 1, 200.00, 'Available'),
('203', 'Double', 'Full', 2, 275.00, 'Available'),
('204', 'Double', 'Full', 2, 275.00, 'Available'), 
('205', 'Double', 'Full', 2, 275.00, 'Available'),
('300', 'Queen', 'Queen', 1, 500.00, 'Available'),
('301', 'Queen', 'Queen', 1, 500.00, 'Available'),
('302', 'Queen', 'Queen', 1, 500.00, 'Available'),
('303', 'Queen', 'Queen', 2, 575.00, 'Available'), 
('304', 'Queen', 'Queen', 2, 575.00, 'Available'),
('305', 'Queen', 'Queen', 2, 575.00, 'Available'),
('400', 'Suite', 'King', 4, 750.00, 'Available'),
('401', 'Suite', 'King', 4, 750.00, 'Available'),
('402', 'Suite', 'King', 4, 750.00, 'Available'),
('403', 'Suite', 'King', 6, 1000.00, 'Available'),  
('404', 'Suite', 'King', 6, 1000.00, 'Available'),
('405', 'Suite', 'King', 6, 1000.00, 'Available'),
('500', 'Penthouse', 'Super King', 8, 1500.00, 'Available'),
('501', 'Penthouse', 'Super King', 8, 1500.00, 'Available'),
('502', 'Penthouse', 'Super King', 8, 1500.00, 'Available'),  
('503', 'Penthouse', 'Super King', 10, 2000.00, 'Available'),
('504', 'Penthouse', 'Super King', 10, 2000.00, 'Available'),
('505', 'Penthouse', 'Super King', 10, 2000.00, 'Available');

-- Table for Storing Reservations
CREATE TABLE Reservations (
    reservationID INT PRIMARY KEY AUTO_INCREMENT,
    userID INT NOT NULL,
    roomID INT NOT NULL,
    checkInDate DATE NOT NULL,
    checkOutDate DATE NOT NULL,
    status ENUM('Confirmed', 'Canceled', 'Checked In', 'Checked Out') NOT NULL DEFAULT 'Confirmed',
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
    reportType ENUM('Occupancy', 'Revenue') NOT NULL,
    period VARCHAR(255) NOT NULL,
    details TEXT NOT NULL
);