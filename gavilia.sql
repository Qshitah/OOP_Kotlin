drop database gavilia;
create database gavilia collate utf8_general_ci;
use gavilia;

create table Categories(
	CategoryID bigint auto_increment primary key,
    nomCategory varchar(100) ,
    parentCategoryId bigint ,
	FOREIGN KEY (parentCategoryId) REFERENCES Categories(CategoryID)
    );
    
CREATE TABLE Products (
    ProductID bigint AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    Description TEXT,
    Price DECIMAL(10, 2) NOT NULL,
    PromotionPrice DECIMAL(10, 2),
    StockQuantity INT default 0 check (StockQuantity >=0),
    CategoryID bigint,
    Barcode VARCHAR(50),
    Images TEXT,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)
);
CREATE INDEX idx_name ON Products (Name);

CREATE TABLE ProductVariants (
    VariantID INT AUTO_INCREMENT PRIMARY KEY,
    ProductID bigint,
    Color VARCHAR(50) ,
    Size Varchar(50),
    StockQuantity INT default 0 check (StockQuantity >=0),
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID) on delete cascade
);

DELIMITER //
CREATE TRIGGER after_insert_variant
AFTER INSERT ON ProductVariants
FOR EACH ROW
BEGIN
    -- Update the StockQuantity of the corresponding product in Products table
    UPDATE Products
    SET StockQuantity = StockQuantity + NEW.StockQuantity
    WHERE ProductID = NEW.ProductID;
END //

DELIMITER //
CREATE TRIGGER after_update_variant
AFTER UPDATE ON ProductVariants
FOR EACH ROW
BEGIN
    -- Update the StockQuantity of the corresponding product in Products table
    IF NEW.StockQuantity > OLD.StockQuantity THEN
        UPDATE Products
        SET StockQuantity = StockQuantity + (NEW.StockQuantity - OLD.StockQuantity)
        WHERE ProductID = NEW.ProductID;
    ELSE
        UPDATE Products
        SET StockQuantity = StockQuantity - (OLD.StockQuantity - NEW.StockQuantity)
        WHERE ProductID = NEW.ProductID;
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER before_delete_variant
Before DELETE ON ProductVariants
FOR EACH ROW
BEGIN
    -- Update the StockQuantity of the corresponding product in Products table
    UPDATE Products
	SET StockQuantity = StockQuantity - OLD.StockQuantity
	WHERE ProductID = OLD.ProductID;
END //
DELIMITER ;

CREATE TABLE Users (
    UserID bigint AUTO_INCREMENT PRIMARY KEY,
    Password VARCHAR(255) , -- Use a secure hashing algorithm for storing passwords
    Email VARCHAR(255) NOT NULL,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Phone VARCHAR(20),
    Profile_Image varchar(255),
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

create table Roles (
	Role_Name varchar(50) primary key,
    description text
    );
    
INSERT INTO Roles (Role_Name, Description) VALUES ('ROLE_ADMIN', 'Administrator Role');
INSERT INTO Roles (Role_Name, Description) VALUES ('ROLE_CLIENT', 'Client Role');
INSERT INTO Roles (Role_Name, Description) VALUES ('ROLE_EMPLOYEE', 'Employee Role');

create table UserRoles(
	UserID bigint,
    Role_Name varchar(50),
    primary key(UserId,Role_Name),
	constraint fk_userRoles_users foreign key(UserId) references Users(UserID),
	constraint fk_userRoles_roles foreign key(Role_Name) references Roles(Role_Name)
    );

CREATE TABLE Cart (
    CartID INT AUTO_INCREMENT PRIMARY KEY,
    UserID bigint not null,
    ProductID bigint not null,
    VariantID INT,
    Quantity INT NOT NULL,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID),
	FOREIGN KEY (VariantID) REFERENCES ProductVariants(VariantID)
);

DELIMITER //
CREATE TRIGGER after_update_product_quantity
AFTER UPDATE ON Products
FOR EACH ROW
BEGIN
    IF NEW.StockQuantity = 0 THEN
        DELETE FROM Cart
        WHERE ProductID = NEW.ProductID;
    END IF;
END //
DELIMITER ;

-- Create Orders table
CREATE TABLE Orders (
    OrderID bigint AUTO_INCREMENT PRIMARY KEY,
    UserID bigint not null,
    OrderDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    TotalAmount DECIMAL(10, 2) NOT NULL,
    ShippingPrice DECIMAL(10,2) default 0,
    ShippingStatus VARCHAR(50) default "en cours",
    PaymentStatus Varchar(50) default "en cours",
    Status varchar(50) default "en cours",
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

CREATE TABLE Shipping (
    ShippingID INT AUTO_INCREMENT PRIMARY KEY,
    OrderID bigint not null,
    ShippingDate TIMESTAMP DEFAULT (CURRENT_TIMESTAMP + INTERVAL 2 DAY),
    Address VARCHAR(255) NOT NULL,
    City VARCHAR(50),
    State VARCHAR(50),
    ZipCode VARCHAR(20),
    MethodShipping varchar(50) default "AMANA",
    TrackNumber varchar(20),
    Country VARCHAR(50) default "Maroc",
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID)
);

-- Create OrderDetails table
CREATE TABLE OrderDetails (
    OrderDetailID INT AUTO_INCREMENT PRIMARY KEY,
    OrderID bigint not null,
    ProductID bigint not null,
    VariantID int,
    Quantity INT default 1 check(Quantity >=1),
    Price DECIMAL(10, 2) NOT NULL,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID),
	FOREIGN KEY (VariantID) REFERENCES ProductVariants(VariantID)
);

DELIMITER //
CREATE TRIGGER after_insert_orderdetail
AFTER INSERT ON OrderDetails
FOR EACH ROW
BEGIN
    DECLARE variant_stock INT;
    -- Check if VariantID is not null
    IF NEW.VariantID IS NOT NULL THEN
        -- Get the stock quantity of the variant
        SELECT StockQuantity INTO variant_stock
        FROM ProductVariants
        WHERE VariantID = NEW.VariantID;
        
        -- Update the stock quantity of the variant in ProductVariants table
		UPDATE ProductVariants
		SET StockQuantity = variant_stock - NEW.Quantity
		WHERE VariantID = NEW.VariantID;
    else
		-- Get the stock quantity of the variant
        SELECT StockQuantity INTO variant_stock
        FROM Products
        WHERE ProductID = NEW.ProductID;
        
        -- Update the stock quantity of the variant in ProductVariants table
		UPDATE Products
		SET StockQuantity = variant_stock - NEW.Quantity
		WHERE ProductID = NEW.ProductID;
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER after_update_orderdetail
AFTER UPDATE ON OrderDetails
FOR EACH ROW
BEGIN
    DECLARE old_variant_stock INT;
    DECLARE new_variant_stock INT;

    -- Check if VariantID is not null
    IF OLD.VariantID IS NOT NULL THEN
        -- Get the old stock quantity of the variant
        SELECT StockQuantity INTO old_variant_stock
        FROM ProductVariants
        WHERE VariantID = OLD.VariantID;

        -- Update the old stock quantity of the variant in ProductVariants table
        UPDATE ProductVariants
        SET StockQuantity = old_variant_stock + OLD.Quantity
        WHERE VariantID = OLD.VariantID;
    ELSE
        -- Get the old stock quantity of the product
        SELECT StockQuantity INTO old_variant_stock
        FROM Products
        WHERE ProductID = OLD.ProductID;

        -- Update the old stock quantity of the product in Products table
        UPDATE Products
        SET StockQuantity = old_variant_stock + OLD.Quantity
        WHERE ProductID = OLD.ProductID;
    END IF;

    -- Check if VariantID is not null
    IF NEW.VariantID IS NOT NULL THEN
        -- Get the new stock quantity of the variant
        SELECT StockQuantity INTO new_variant_stock
        FROM ProductVariants
        WHERE VariantID = NEW.VariantID;

        -- Update the new stock quantity of the variant in ProductVariants table
        UPDATE ProductVariants
        SET StockQuantity = new_variant_stock - NEW.Quantity
        WHERE VariantID = NEW.VariantID;
    ELSE
        -- Get the new stock quantity of the product
        SELECT StockQuantity INTO new_variant_stock
        FROM Products
        WHERE ProductID = NEW.ProductID;

        -- Update the new stock quantity of the product in Products table
        UPDATE Products
        SET StockQuantity = new_variant_stock - NEW.Quantity
        WHERE ProductID = NEW.ProductID;
    END IF;
END //
DELIMITER ;

DELIMITER //

CREATE TRIGGER after_update_order_status
AFTER UPDATE ON Orders
FOR EACH ROW
BEGIN
    DECLARE variant_id int;
    DECLARE product_id bigint;
	DECLARE quantity int;
-- Iterate through order details to return stock to products or variants
	DECLARE done INT DEFAULT FALSE;
	DECLARE c1 CURSOR FOR 
		SELECT ProductID, VariantID, Quantity
		FROM OrderDetails
		WHERE OrderID = NEW.OrderID;
        
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    -- Check if the order status is updated to "annulé"
    IF NEW.Status = 'annulé' AND OLD.Status != 'annulé' THEN
        OPEN c1;

        read_loop: LOOP
            FETCH c1 INTO product_id, variant_id,quantity;

            IF done THEN
                LEAVE read_loop;
            END IF;

            -- Return stock to ProductVariants table
            IF variant_id IS NOT NULL THEN
                UPDATE ProductVariants
                SET StockQuantity = StockQuantity + quantity
                WHERE VariantID = variant_id;
            else 
				UPDATE Products
				SET StockQuantity = StockQuantity + quantity
				WHERE ProductID = product_id;
            END IF;
        END LOOP;
        CLOSE c1;
    END IF;
END //
DELIMITER ;