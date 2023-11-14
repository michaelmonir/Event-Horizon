CREATE TABLE IF NOT EXISTS AdsOption (
    id INT PRIMARY KEY AUTO_INCREMENT,
    priority INT NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Location (
    id INT PRIMARY KEY AUTO_INCREMENT,
    country VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    address VARCHAR(1000)
);

CREATE TABLE IF NOT EXISTS Event (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(2550),
    event_category VARCHAR(255),
    event_date datetime(6),
    location_id INT,
    ads_id INT NOT NULL,
    FOREIGN KEY (location_id) REFERENCES Location(id) ON DELETE SET NULL,
    FOREIGN KEY (ads_id) REFERENCES AdsOption(id)
);