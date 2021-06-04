SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema taxi
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema taxi
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `taxi`;

CREATE SCHEMA IF NOT EXISTS `taxi` DEFAULT CHARACTER SET utf8 ;
USE `taxi` ;

-- -----------------------------------------------------
-- Table `taxi`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taxi`.`address` (
  `address_id` INT NOT NULL,
  `street` VARCHAR(45) NULL,
  `house_number` VARCHAR(45) NULL,
  PRIMARY KEY (`address_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `taxi`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taxi`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  `email` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `password_UNIQUE` (`password` ASC) VISIBLE,
  UNIQUE INDEX `phone_number_UNIQUE` (`phone_number` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `taxi`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taxi`.`role` (
  `role_name` VARCHAR(45) NOT NULL,
  `user_id` INT NOT NULL,
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fkuser_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `taxi`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `taxi`.`car`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taxi`.`car` (
  `car_id` INT NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(45) NOT NULL,
  `model` VARCHAR(45) NOT NULL,
  `color` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `num_seats` INT NOT NULL,
  PRIMARY KEY (`car_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `taxi`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taxi`.`order` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `order_status` VARCHAR(45) NOT NULL,
  `client_id` INT NOT NULL,
  `driver_id` INT NOT NULL,
  `dest_address_id` INT NOT NULL,
  `arr_address_id` INT NOT NULL,
  `cost` DECIMAL(10) NOT NULL,
  `discount_cost` DECIMAL(10) NULL,
  `car_id` INT NOT NULL,
  `creation_date` DATE NOT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `fkclient_id_idx` (`client_id` ASC) VISIBLE,
  INDEX `fkdriver_id_idx` (`driver_id` ASC) VISIBLE,
  INDEX `fkdest_address_id_idx` (`dest_address_id` ASC) VISIBLE,
  INDEX `fkarr_address_id_idx` (`arr_address_id` ASC) VISIBLE,
  INDEX `fkcar_id_idx` (`car_id` ASC) VISIBLE,
  CONSTRAINT `fkclient_id`
    FOREIGN KEY (`client_id`)
    REFERENCES `taxi`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkdriver_id`
    FOREIGN KEY (`driver_id`)
    REFERENCES `taxi`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkdest_address_id`
    FOREIGN KEY (`dest_address_id`)
    REFERENCES `taxi`.`address` (`address_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fkarr_address_id`
    FOREIGN KEY (`arr_address_id`)
    REFERENCES `taxi`.`address` (`address_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fkcar_id`
    FOREIGN KEY (`car_id`)
    REFERENCES `taxi`.`car` (`car_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `taxi`.`discount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taxi`.`discount` (
  `dis_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `discout_rate` INT NULL,
  `total_sum` DECIMAL(10) NULL,
  PRIMARY KEY (`dis_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fkdisc_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `taxi`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `taxi`.`car_driver`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taxi`.`car_driver` (
  `car_id` INT NULL,
  `driver_id` INT NULL,
  INDEX `fk_car_id_idx` (`car_id` ASC) VISIBLE,
  INDEX `fk_driver_id_idx` (`driver_id` ASC) VISIBLE,
  CONSTRAINT `fk_car_id`
    FOREIGN KEY (`car_id`)
    REFERENCES `taxi`.`car` (`car_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_driver_id`
    FOREIGN KEY (`driver_id`)
    REFERENCES `taxi`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
