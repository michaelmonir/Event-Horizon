CREATE TABLE IF NOT EXISTS AdsOption (
    id INT PRIMARY KEY AUTO_INCREMENT,
    priority INT,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Location (
    id INT PRIMARY KEY AUTO_INCREMENT,
    country VARCHAR(255),
    city VARCHAR(255),
    address VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Event (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    description VARCHAR(2550),
    event_category VARCHAR(255),
    event_date datetime(6),
    location_id INT,
    ads_id INT,
    FOREIGN KEY (location_id) REFERENCES Location(id) ON DELETE SET NULL,
    FOREIGN KEY (ads_id) REFERENCES AdsOption(id)
);