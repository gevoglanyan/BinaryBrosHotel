CREATE DATABASE BinaryBrosHotelManagement;
USE BinaryBrosHotelManagement;

CREATE TABLE Users (
    userID INT PRIMARY KEY AUTO_INCREMENT,
    fullName VARCHAR(255) NOT NULL,
    userUsername VARCHAR(255) NOT NULL,
    userPassword VARCHAR(255) NOT NULL,
    userEmail VARCHAR(255) NOT NULL UNIQUE,
    Role ENUM('user', 'admin') NOT NULL
);

CREATE TABLE Rooms (
    roomID INT PRIMARY KEY AUTO_INCREMENT,
    roomNumber INT NOT NULL UNIQUE,
    roomType VARCHAR(255) NOT NULL,
    availableRooms BOOLEAN DEFAULT true,
    totalPrice DECIMAL (10,2) NOT NULL,
    Type ENUM('single', 'double', 'king') NOT NULL
);

CREATE TABLE Reservations (
    reservationID INT PRIMARY KEY AUTO_INCREMENT,
    userID INT,
    roomID INT,
    checkInDate DATE NOT NULL,
    checkOutDate DATE NOT NULL,
    Status ENUM('confirmed', 'canceled') NOT NULL,
    FOREIGN KEY (userID) REFERENCES Users(userID),
    FOREIGN KEY (roomID) REFERENCES Rooms(roomID)
);

CREATE TABLE ReservationModification (
    modificationID INT PRIMARY KEY AUTO_INCREMENT,
    reservationID INT,
    previousCheckInDate DATE NOT NULL,
    previousCheckOutDate DATE NOT NULL,
    newCheckInDate DATE NOT NULL,
    newCheckOutDate DATE NOT NULL,
    modificationDate DATE NOT NULL,
    FOREIGN KEY (reservationID) REFERENCES Reservations(reservationID)
);

CREATE TABLE Reports (
    reportID INT PRIMARY KEY AUTO_INCREMENT,
    reportType ENUM('occupancy', 'revenue') NOT NULL,
    period VARCHAR(255) NOT NULL,
    details TEXT NOT NULL
);