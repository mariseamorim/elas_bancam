SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `elas_bancam` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `elas_bancam` ;




-- -----------------------------------------------------
-- Table `elas_bancam`.`conta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `elas_bancam`.`conta` (
  `id` VARCHAR(255) NOT NULL,
  `agencia` INT NOT NULL,
  `numero_conta` INT NOT NULL,
  `operacao` VARCHAR(255) NOT NULL,
  `saldo` DECIMAL(19,2) NOT NULL,
  `status` BIT(1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `elas_bancam`.`endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `elas_bancam`.`endereco` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `rua` VARCHAR(255) NOT NULL,
  `numero` INT NOT NULL,
  `complemento` VARCHAR(20) NULL DEFAULT NULL,
  `cidade` VARCHAR(255) NULL DEFAULT NULL,
  `bairro` VARCHAR(255) NULL DEFAULT NULL,
  `cep` VARCHAR(10) NOT NULL,
  `regiao` VARCHAR(2) NULL DEFAULT NULL,
  `uf` VARCHAR(2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;





-- -----------------------------------------------------
-- Table `elas_bancam`.`pessoa_fisica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `elas_bancam`.`pessoa_fisica` (
  `id` BIGINT NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `cpf` VARCHAR(20) NOT NULL,
  `rg` VARCHAR(20) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `dt_nascimento` DATE NOT NULL,
  `genero` VARCHAR(20) NOT NULL,
  `nome_mae` VARCHAR(255) NOT NULL,
  `telefone` VARCHAR(20) NOT NULL,
  `celular` VARCHAR(20) NOT NULL,
  `alterado_em` DATETIME(6) NOT NULL,
  `criado_em` DATETIME(6) NOT NULL,
  `status` BIT(1) NOT NULL,
  `conta_id` VARCHAR(255) NULL DEFAULT NULL,
  `endereco_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_p3d8co8s4y5h7y18fpqco1wv6` (`cpf` ASC) VISIBLE,
  UNIQUE INDEX `UK_g3ce8301m7h9hv5rfjtvv3kcy` (`rg` ASC) VISIBLE,
  INDEX `FKfng3ukmu6a6vdadkr2fleg3cw` (`conta_id` ASC) VISIBLE,
  INDEX `FKmndghl3xolrj6x9pwd40rilim` (`endereco_id` ASC) VISIBLE,
  CONSTRAINT `FKfng3ukmu6a6vdadkr2fleg3cw`
    FOREIGN KEY (`conta_id`)
    REFERENCES `elas_bancam`.`conta` (`id`),
  CONSTRAINT `FKmndghl3xolrj6x9pwd40rilim`
    FOREIGN KEY (`endereco_id`)
    REFERENCES `elas_bancam`.`endereco` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `elas_bancam`.`pessoa_juridica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `elas_bancam`.`pessoa_juridica` (
  `id` BIGINT NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `nome_fantasia` VARCHAR(100) NOT NULL,
  `inscricao_estadual` VARCHAR(50) NOT NULL,
  `cnpj` VARCHAR(14) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `nome_contato` VARCHAR(50) NOT NULL,
  `telefone` VARCHAR(20) NOT NULL,
  `celular` VARCHAR(20) NOT NULL,
  `alterado_em` DATETIME(6) NOT NULL,
  `criado_em` DATETIME(6) NOT NULL,
  `status` BIT(1) NOT NULL,
  `conta_id` VARCHAR(255) NULL DEFAULT NULL,
  `endereco_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_3h78rtw3ei11cb43k77af5nhl` (`cnpj` ASC) VISIBLE,
  UNIQUE INDEX `UK_t9fugaodr2k9s1btuuk6xes0f` (`inscricao_estadual` ASC) VISIBLE,
  INDEX `FKpqxxe2esc9ts8wkgwpniwr9n` (`conta_id` ASC) VISIBLE,
  INDEX `FKip7vkxsxxoxc2osb6y42oxc4w` (`endereco_id` ASC) VISIBLE,
  CONSTRAINT `FKip7vkxsxxoxc2osb6y42oxc4w`
    FOREIGN KEY (`endereco_id`)
    REFERENCES `elas_bancam`.`endereco` (`id`),
  CONSTRAINT `FKpqxxe2esc9ts8wkgwpniwr9n`
    FOREIGN KEY (`conta_id`)
    REFERENCES `elas_bancam`.`conta` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `elas_bancam`.`transacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `elas_bancam`.`transacao` (
  `id` VARCHAR(255) NOT NULL,
  `data` DATETIME(6) NOT NULL,
  `descricao` VARCHAR(255) NULL DEFAULT NULL,
  `tipo_transacao` VARCHAR(255) NOT NULL,
  `valor` DECIMAL(19,2) NOT NULL,
  `conta_destino_id` VARCHAR(255) NOT NULL,
  `conta_origem_id` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKkut4s9v98tg538xk99tqhncvj` (`conta_destino_id` ASC) VISIBLE,
  INDEX `FKh9v7wctkl2o918xt7houdbgqh` (`conta_origem_id` ASC) VISIBLE,
  CONSTRAINT `FKh9v7wctkl2o918xt7houdbgqh`
    FOREIGN KEY (`conta_origem_id`)
    REFERENCES `elas_bancam`.`conta` (`id`),
  CONSTRAINT `FKkut4s9v98tg538xk99tqhncvj`
    FOREIGN KEY (`conta_destino_id`)
    REFERENCES `elas_bancam`.`conta` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
