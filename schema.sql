DROP SCHEMA IF EXISTS hospital_db;
CREATE SCHEMA hospital_db;
USE hospital_db;

CREATE TABLE IF NOT EXISTS hospital(
    id INT(11) UNSIGNED AUTO_INCREMENT NOT NULL,
    name VARCHAR(100) NOT NULL,
    created_date DATETIME NOT NULL,
    modified_date DATETIME NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS doctor(
    id INT(11) UNSIGNED AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR (100) NOT NULL,
    birth_date DATETIME NOT NULL,
    address VARCHAR(255) NOT NULL,
    hospital_id INT(11) UNSIGNED NOT NULL,
    created_date DATETIME NOT NULL,
    modified_date DATETIME NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE doctor ADD FOREIGN KEY (hospital_id) REFERENCES hospital(id);

CREATE TABLE IF NOT EXISTS patient(
    id INT(11) UNSIGNED AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR (100) NOT NULL,
    birth_date DATETIME NOT NULL,
    address VARCHAR(255) NOT NULL,
    hospital_id INT UNSIGNED NOT NULL,
    created_date DATETIME NOT NULL,
    modified_date DATETIME NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE patient ADD FOREIGN KEY (hospital_id) REFERENCES hospital(id);

CREATE TABLE IF NOT EXISTS speciality(
    id INT(11) UNSIGNED AUTO_INCREMENT NOT NULL,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255) NOT NULL,
    created_date DATETIME NOT NULL,
    modified_date DATETIME NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS visit_note(
    id INT(11) UNSIGNED AUTO_INCREMENT NOT NULL,
    description VARCHAR (255) NOT NULL,
    date_visit DATETIME NOT NULL,
    patient_id INT(11) UNSIGNED NOT NULL,
    doctor_id INT(11) UNSIGNED NOT NULL,
    created_date DATETIME NOT NULL,
    modified_date DATETIME NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE visit_note ADD FOREIGN KEY (patient_id) REFERENCES patient(id);
ALTER TABLE visit_note ADD FOREIGN KEY (doctor_id) REFERENCES doctor(id);

CREATE TABLE IF NOT EXISTS doctor_speciality(
    doctor_id INT(11) UNSIGNED NOT NULL,
    speciality_id INT(11) UNSIGNED NOT NULL,
    PRIMARY KEY(doctor_id, speciality_id)
);

ALTER TABLE doctor_speciality ADD FOREIGN KEY (doctor_id) REFERENCES doctor(id);
ALTER TABLE doctor_speciality ADD FOREIGN KEY (speciality_id) REFERENCES speciality(id);

INSERT INTO hospital VALUE (NULL, 'hospital del sur', NOW(), NOW());
SET @hospital_id = LAST_INSERT_ID();
INSERT INTO hospital VALUE (NULL, 'hospital obrero', NOW(), NOW());
INSERT INTO hospital VALUE (NULL, 'clinica alemana', NOW(), NOW());


INSERT INTO doctor VALUE (NULL, 'juan', 'perez', '1960-7-04', 'miraflores', @hospital_id, NOW(), NOW());
SET @doctor_id = LAST_INSERT_ID();
INSERT INTO doctor VALUE (NULL, 'carlos', 'torrez', '1993-12-04', 'san pedro', @hospital_id, NOW(), NOW());

INSERT INTO speciality VALUE(NULL, 'cirujano', 'hace cirujias', NOW(), NOW());
SET @speciality_id = LAST_INSERT_ID();
INSERT INTO speciality VALUE(NULL, 'pediatra', 'se encarga de los menores', NOW(), NOW());
INSERT INTO speciality VALUE(NULL, 'neurologo', 'sobre la cabeza', NOW(), NOW());

INSERT INTO doctor_speciality VALUE (@doctor_id, @speciality_id);

INSERT INTO patient VALUE (NULL, 'oscar', 'carvajal', '1995-10-04', 'san antonio', @hospital_id, NOW(), NOW());
SET @patient_id = LAST_INSERT_ID();


INSERT INTO visit_note VALUE (NULL, 'dolor de cabeza', '2020-01-04', @patient_id, @doctor_id, NOW(), NOW());
INSERT INTO visit_note VALUE (NULL, 'solo fue una gripe', '2020-07-23', @patient_id, @doctor_id, NOW(), NOW());
