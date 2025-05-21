use nomnom_db;

CREATE TABLE user (
    user_no INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(50) UNIQUE NOT NULL,
    user_email VARCHAR(100) UNIQUE NOT NULL,
    user_pw VARCHAR(255) NOT NULL,
    user_created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    user_birthday DATE NOT NULL,
    user_gender ENUM('MALE', 'FEMALE'),
    user_role ENUM('USER', 'ADMIN') NOT NULL DEFAULT 'USER',
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
    food_amount INT NOT NULL
);

CREATE TABLE attachment (
    file_no INT PRIMARY KEY AUTO_INCREMENT,
    file_target ENUM('MEAL', 'BOARD') NOT NULL,
    target_no INT NOT NULL,
    file_name VARCHAR(255) NOT NULL,
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
    mission_no INT PRIMARY KEY AUTO_INCREMENT,
    mission_name VARCHAR(100) NOT NULL,
    mission_description TEXT,
    challenge_duration INT DEFAULT 30
);

CREATE TABLE challenge (
    challenge_no INT PRIMARY KEY AUTO_INCREMENT,
    user_no INT NOT NULL,
    mission_no INT NOT NULL,
    challenge_start_date DATE NOT NULL,
    challenge_current_streak INT DEFAULT 0,
    challenge_status ENUM('IN_PROGRESS', 'COMPLETED', 'FAILED', 'DROPPED')
);

CREATE TABLE badge (
    badge_no INT PRIMARY KEY AUTO_INCREMENT,
    user_no INT NOT NULL,
    mission_no INT NOT NULL,
    badge_reg_date DATE NOT NULL,
    UNIQUE (user_no, mission_no, badge_reg_date)
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

CREATE TABLE disease (
    disease_no    INT PRIMARY KEY AUTO_INCREMENT,
    name          VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE allergy (
    allergy_no    INT PRIMARY KEY AUTO_INCREMENT,
    name          VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE user_disease (
    user_no       INT,
    disease_no    INT,
    PRIMARY KEY (user_no, disease_no),
    FOREIGN KEY (user_no) REFERENCES user(user_no),
    FOREIGN KEY (disease_no) REFERENCES disease(disease_no)
);

CREATE TABLE user_allergy (
    user_no       INT,
    allergy_no    INT,
    PRIMARY KEY (user_no, allergy_no),
    FOREIGN KEY (user_no) REFERENCES user(user_no),
    FOREIGN KEY (allergy_no) REFERENCES allergy(allergy_no)
);

CREATE TABLE food (
    food_code VARCHAR(20) PRIMARY KEY,   -- D015039
    food_name VARCHAR(255) NOT NULL,      -- "짬뽕덮밥"
    food_upper_group VARCHAR(100),
	food_sub_group VARCHAR(100),
	food_weight DOUBLE,
    nutri_energy DOUBLE,
    nutri_water DOUBLE,
    nutri_protein DOUBLE,
    nutri_nitrogen DOUBLE,
    nutri_ash DOUBLE,
    nutri_carbohydrate DOUBLE,
    nutri_sugar DOUBLE,
    nutri_dietary_fiber DOUBLE,
    nutri_vitamin_a_carotene DOUBLE,
    nutri_vitamin_a_retinol DOUBLE,
    nutri_niacin_eq DOUBLE,
    nutri_total_fatty_acids DOUBLE,
    nutri_essential_fatty_acids DOUBLE,
    nutri_saturated_fats DOUBLE,
    nutri_monounsaturated_fats DOUBLE,
    nutri_polyunsaturated_fats DOUBLE,
    nutri_calcium DOUBLE,
    nutri_iron DOUBLE,
    nutri_magnesium DOUBLE,
    nutri_phosphorus DOUBLE,
    nutri_potassium DOUBLE,
    nutri_sodium DOUBLE,
    nutri_zinc DOUBLE,
    nutri_copper DOUBLE,
    nutri_manganese DOUBLE,
    nutri_selenium DOUBLE,
    nutri_molybdenum DOUBLE,
    nutri_iodine DOUBLE,
    nutri_retinol DOUBLE,
    nutri_carotene DOUBLE,
    nutri_vitamin_d DOUBLE,
    nutri_vitamin_e DOUBLE,
    nutri_vitamin_k1 DOUBLE,
    nutri_vitamin_b1 DOUBLE,
    nutri_vitamin_b2 DOUBLE,
    nutri_niacin DOUBLE,
    nutri_pantothenic_acid DOUBLE,
    nutri_pyridoxine DOUBLE,
    nutri_biotin DOUBLE,
    nutri_folate DOUBLE,
    nutri_vitamin_b12 DOUBLE,
    nutri_vitamin_c DOUBLE,
    nutri_cholesterol DOUBLE,
    nutri_salt_equivalent DOUBLE,
    nutri_refuse DOUBLE
);

-- 재료 테이블
CREATE TABLE ingredient (
    ingredient_code VARCHAR(20) PRIMARY KEY,       -- F00017, F02825 등
    ingredient_name VARCHAR(255) NOT NULL,
    ingredient_group_name VARCHAR(100),
	allergy_info TEXT,
	image_url TEXT
);

-- 요리-재료 매핑
CREATE TABLE food_ingredient (
    food_ingredient_no INT PRIMARY KEY AUTO_INCREMENT,
    food_code VARCHAR(20),
    ingredient_code VARCHAR(20),
    ingredient_weight DOUBLE,
    nutri_energy DOUBLE,
    nutri_water DOUBLE,
    nutri_protein DOUBLE,
    nutri_nitrogen DOUBLE,
    nutri_ash DOUBLE,
    nutri_carbohydrate DOUBLE,
    nutri_sugar DOUBLE,
    nutri_dietary_fiber DOUBLE,
    nutri_vitamin_a_carotene DOUBLE,
    nutri_vitamin_a_retinol DOUBLE,
    nutri_niacin_eq DOUBLE,
    nutri_total_fatty_acids DOUBLE,
    nutri_essential_fatty_acids DOUBLE,
    nutri_saturated_fats DOUBLE,
    nutri_monounsaturated_fats DOUBLE,
    nutri_polyunsaturated_fats DOUBLE,
    nutri_calcium DOUBLE,
    nutri_iron DOUBLE,
    nutri_magnesium DOUBLE,
    nutri_phosphorus DOUBLE,
    nutri_potassium DOUBLE,
    nutri_sodium DOUBLE,
    nutri_zinc DOUBLE,
    nutri_copper DOUBLE,
    nutri_manganese DOUBLE,
    nutri_selenium DOUBLE,
    nutri_molybdenum DOUBLE,
    nutri_iodine DOUBLE,
    nutri_retinol DOUBLE,
    nutri_carotene DOUBLE,
    nutri_vitamin_d DOUBLE,
    nutri_vitamin_e DOUBLE,
    nutri_vitamin_k1 DOUBLE,
    nutri_vitamin_b1 DOUBLE,
    nutri_vitamin_b2 DOUBLE,
    nutri_niacin DOUBLE,
    nutri_pantothenic_acid DOUBLE,
    nutri_pyridoxine DOUBLE,
    nutri_biotin DOUBLE,
    nutri_folate DOUBLE,
    nutri_vitamin_b12 DOUBLE,
    nutri_vitamin_c DOUBLE,
    nutri_cholesterol DOUBLE,
    nutri_salt_equivalent DOUBLE,
    nutri_refuse DOUBLE,
    FOREIGN KEY (food_code) REFERENCES food(food_code),
    FOREIGN KEY (ingredient_code) REFERENCES ingredient(ingredient_code)
);


ALTER TABLE food_ingredient
ADD CONSTRAINT uq_mainfood_food UNIQUE (food_code, ingredient_code);
