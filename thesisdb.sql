CREATE TABLE chat_group
(
  time         TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  id_topic_sem INT                                 NOT NULL,
  id_user      INT                                 NOT NULL,
  content      VARCHAR(50)                         NOT NULL,
  PRIMARY KEY (time, id_topic_sem, id_user),
  CONSTRAINT chat_group_id_topic_sem_uindex
  UNIQUE (id_topic_sem),
  CONSTRAINT chat_group_id_user_uindex
  UNIQUE (id_user)
)
  ENGINE = InnoDB;

CREATE TABLE comment_task
(
  time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  id_task INT                                 NOT NULL,
  id_user INT                                 NOT NULL,
  content VARCHAR(150)                        NOT NULL,
  PRIMARY KEY (time, id_task, id_user)
)
  COMMENT 'Comment on each task'
  ENGINE = InnoDB;

CREATE INDEX comment_task_task_id_task_fk
  ON comment_task (id_task);

CREATE INDEX comment_task_user_id_user_fk
  ON comment_task (id_user);

CREATE TABLE faculty
(
  id_faculty INT AUTO_INCREMENT
    PRIMARY KEY,
  name       VARCHAR(45) NOT NULL,
  CONSTRAINT faculty_id_faculty_uindex
  UNIQUE (id_faculty),
  CONSTRAINT faculty_name_uindex
  UNIQUE (name)
)
  ENGINE = InnoDB;

CREATE TABLE join_per_meeting
(
  id_student INT NOT NULL,
  id_meeting INT NOT NULL,
  PRIMARY KEY (id_student, id_meeting)
)
  ENGINE = InnoDB;

CREATE INDEX join_per_meeting_meeting_id_meeting_fk
  ON join_per_meeting (id_meeting);

CREATE TABLE meeting
(
  id_meeting    INT                                 NOT NULL
    PRIMARY KEY,
  note          VARCHAR(150)                        NULL,
  content       VARCHAR(150)                        NOT NULL,
  student_count INT                                 NULL,
  meeting_time  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  approve       INT DEFAULT '0'                     NOT NULL,
  location      VARCHAR(50)                         NULL,
  id_topic_sem  INT                                 NULL
)
  ENGINE = InnoDB;

CREATE INDEX meeting_topic_per_semester_id_topic_semester_fk
  ON meeting (id_topic_sem);

CREATE TABLE professor
(
  id_professor INT         NOT NULL
    PRIMARY KEY,
  id_user      INT         NOT NULL,
  degree       VARCHAR(45) NULL,
  skills       VARCHAR(45) NULL,
  CONSTRAINT professor_id_professor_uindex
  UNIQUE (id_professor),
  CONSTRAINT professor_id_user_uindex
  UNIQUE (id_user)
)
  ENGINE = InnoDB;

CREATE TABLE semester
(
  semester_no      INT                                     NOT NULL
    PRIMARY KEY,
  apply_open_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP     NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  apply_close_date INT                                     NULL,
  end_date         TIMESTAMP DEFAULT '0000-00-00 00:00:00' NOT NULL,
  start_date       TIMESTAMP DEFAULT '0000-00-00 00:00:00' NOT NULL
)
  ENGINE = InnoDB;

CREATE TABLE standard
(
  id_standard INT NOT NULL
    PRIMARY KEY,
  st_name     INT NULL,
  id_prof     INT NULL
)
  COMMENT 'Standard for Professors'
  ENGINE = InnoDB;

CREATE INDEX standard_professor_id_professor_fk
  ON standard (id_prof);

CREATE TABLE student
(
  id_student INT NOT NULL
    PRIMARY KEY,
  id_user    INT NOT NULL,
  CONSTRAINT id_student_UNIQUE
  UNIQUE (id_student),
  CONSTRAINT student_id_user_uindex
  UNIQUE (id_user)
)
  ENGINE = InnoDB;

CREATE TABLE student_task
(
  id_task     INT                                 NOT NULL,
  id_student  INT                                 NOT NULL,
  archive     VARCHAR(100)                        NULL,
  upload_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id_student, id_task)
)
  COMMENT 'Student On Each Task'
  ENGINE = InnoDB;

CREATE TABLE student_topic_sem
(
  id_student   INT             NOT NULL,
  id_topic_sem INT             NOT NULL
    PRIMARY KEY,
  team_lead    INT DEFAULT '0' NOT NULL
  COMMENT 'team lead: 1
		other member 0'
)
  COMMENT 'List of student belong to each topic per semester'
  ENGINE = InnoDB;

CREATE INDEX student_topic_sem_student_id_student_fk
  ON student_topic_sem (id_student);

CREATE TABLE task
(
  id_task      INT AUTO_INCREMENT
    PRIMARY KEY,
  title        VARCHAR(150)                        NOT NULL,
  description  VARCHAR(200)                        NULL,
  deadline     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  id_topic_sem INT                                 NULL
)
  ENGINE = InnoDB;

CREATE INDEX task_topic_per_semester_id_topic_semester_fk
  ON task (id_topic_sem);

CREATE TABLE topic
(
  id_top       INT AUTO_INCREMENT
    PRIMARY KEY,
  title        VARCHAR(150)    NOT NULL,
  st_num_limit INT DEFAULT '0' NOT NULL,
  sumary       VARCHAR(200)    NULL,
  id_prof      INT             NOT NULL,
  id_faculty   INT             NOT NULL,
  CONSTRAINT topic_id_top_uindex
  UNIQUE (id_top)
)
  ENGINE = InnoDB;

CREATE INDEX topic_professor_id_professor_fk
  ON topic (id_prof);

CREATE TABLE topic_mission
(
  id_mission INT AUTO_INCREMENT
    PRIMARY KEY,
  topic_id   INT         NOT NULL,
  detail     VARCHAR(60) NOT NULL,
  CONSTRAINT mission_topic_mission_id_uindex
  UNIQUE (id_mission)
)
  ENGINE = InnoDB;

CREATE TABLE topic_per_semester
(
  id_topic_semester INT AUTO_INCREMENT
    PRIMARY KEY,
  score             INT DEFAULT '0' NOT NULL,
  semester_no       VARCHAR(15)     NULL,
  id_topic          INT             NOT NULL,
  CONSTRAINT topic_per_semester_id_topic_semester_uindex
  UNIQUE (id_topic_semester),
  CONSTRAINT topic_per_semester_id_topic_uindex
  UNIQUE (id_topic)
)
  COMMENT 'Topic on each semester'
  ENGINE = InnoDB;

CREATE TABLE topic_requirement
(
  id_req   INT AUTO_INCREMENT
    PRIMARY KEY,
  id_topic INT         NOT NULL,
  detail   VARCHAR(50) NULL,
  CONSTRAINT topic_requirement_req_id_uindex
  UNIQUE (id_req)
)
  ENGINE = InnoDB;

CREATE TABLE topic_sem_standard
(
  id_topic_sem INT             NOT NULL,
  id_standard  INT             NOT NULL,
  score        INT DEFAULT '0' NOT NULL,
  PRIMARY KEY (id_standard, id_topic_sem)
)
  COMMENT 'Standard foreach topic per semester'
  ENGINE = InnoDB;

CREATE INDEX topic_sem_standard_topic_per_semester_id_topic_semester_fk
  ON topic_sem_standard (id_topic_sem);

CREATE TABLE user
(
  id_user    INT AUTO_INCREMENT
    PRIMARY KEY,
  user_name  VARCHAR(50) NOT NULL,
  password   VARCHAR(50) NOT NULL,
  first_name VARCHAR(50) NULL,
  last_name  VARCHAR(50) NULL,
  email      VARCHAR(50) NULL,
  photo      VARCHAR(45) NULL,
  gender     VARCHAR(45) NOT NULL
  COMMENT '1: male
	0: female',
  CONSTRAINT user_id_user_uindex
  UNIQUE (id_user),
  CONSTRAINT user_user_name_uindex
  UNIQUE (user_name),
  CONSTRAINT user_password_uindex
  UNIQUE (password),
  CONSTRAINT user_email_uindex
  UNIQUE (email)
)
  ENGINE = InnoDB;


