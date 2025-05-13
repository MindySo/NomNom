CREATE TABLE user (
    user_no INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(50) NOT NULL,
    user_email VARCHAR(100) NOT NULL,
    user_pw VARCHAR(255) NOT NULL,
    user_reg_date DATE NOT NULL,
    user_birthday DATE,
    user_gender ENUM('MALE', 'FEMALE'),
    user_role ENUM('USER', 'ADMIN'),
    user_height INT,
    user_weight INT,
    user_point INT DEFAULT 0
);

CREATE TABLE meal (
    meal_no INT PRIMARY KEY AUTO_INCREMENT,
    user_no INT NOT NULL,
    meal_reg_date DATE NOT NULL,
    meal_time ENUM('BREAKFAST', 'LUNCH', 'DINNER', 'SNACK') NOT NULL
);

CREATE TABLE meal_food (
    meal_food_no INT PRIMARY KEY AUTO_INCREMENT,
    meal_no INT NOT NULL,
    food_no INT NOT NULL,
    food_amount INT NOT NULL,
    calories DOUBLE,
    carbohydrates DOUBLE,
    protein DOUBLE,
    fat DOUBLE,
    saturated_fat DOUBLE,
    unsaturated_fat DOUBLE,
    cholesterol DOUBLE,
    sodium DOUBLE,
    sugar DOUBLE,
    calcium DOUBLE,
    vitamin DOUBLE,
    iron DOUBLE,
    mineral DOUBLE
);

CREATE TABLE file (
    file_no INT PRIMARY KEY AUTO_INCREMENT,
    file_target ENUM('MEAL', 'BOARD') NOT NULL,
    target_no INT NOT NULL,
    file_name VARCHAR(255) NOT NULL,
    file_path VARCHAR(500) NOT NULL,
    file_reg_date DATE NOT NULL
);

CREATE TABLE board (
    board_no INT PRIMARY KEY AUTO_INCREMENT,
    board_type ENUM('FREE', 'TIP', 'REVIEW') NOT NULL,
    user_no INT NOT NULL,
    board_title VARCHAR(255) NOT NULL,
    board_content TEXT NOT NULL,
    board_reg_date DATETIME NOT NULL,
    board_view_cnt INT DEFAULT 0
);

CREATE TABLE reaction (
    reaction_no INT PRIMARY KEY AUTO_INCREMENT,
    board_no INT NOT NULL,
    user_no INT NOT NULL
);

CREATE TABLE mission (
    mission_id INT PRIMARY KEY AUTO_INCREMENT,
    mission_name VARCHAR(100) NOT NULL,
    mission_description TEXT,
    challenge_duration INT DEFAULT 30
);

CREATE TABLE challenge (
    challenge_no INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    mission_id INT NOT NULL,
    challenge_start_date DATE NOT NULL,
    challenge_current_streak INT DEFAULT 0,
    challenge_status ENUM('IN_PROGRESS', 'COMPLETED', 'FAILED', 'DROPPED')
);

CREATE TABLE badge (
    badge_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    mission_id INT NOT NULL,
    badge_reg_date DATE NOT NULL,
    UNIQUE (user_id, mission_id, badge_reg_date)
);

CREATE TABLE comment (
    comment_no INT PRIMARY KEY AUTO_INCREMENT,
    board_no INT NOT NULL,
    user_no INT NOT NULL,
    comment_content TEXT NOT NULL,
    comment_parent_no INT DEFAULT -1,
    comment_reg_date DATETIME NOT NULL
);

CREATE TABLE point_log (
    point_log_no INT PRIMARY KEY AUTO_INCREMENT,
    user_no INT NOT NULL,
    point_amount INT NOT NULL,
    point_type ENUM('EARN', 'USE') NOT NULL,
    point_target ENUM('BADGE', 'CHALLENGE', 'MEAL_INSERT'),
    point_reg_date DATETIME NOT NULL
);
