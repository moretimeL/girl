CREATE TABLE comment
(
  id           INT          NOT NULL
    PRIMARY KEY,
  comment_date VARCHAR(255) NULL,
  content      VARCHAR(255) NULL,
  dt           VARCHAR(255) NULL,
  icon         VARCHAR(255) NULL,
  type         VARCHAR(255) NULL,
  username     VARCHAR(255) NULL
)
  ENGINE = MyISAM;

CREATE TABLE hibernate_sequence
(
  next_val BIGINT NULL
)
  ENGINE = MyISAM;

CREATE TABLE meeting
(
  id                     INT          NOT NULL
    PRIMARY KEY,
  conference_picture_url VARCHAR(255) NULL,
  login_password         VARCHAR(255) NULL,
  meeting_count          INT          NULL,
  meeting_id             INT          NULL,
  play_name              VARCHAR(255) NULL,
  meeting_stream         VARCHAR(255) NULL
)
  ENGINE = MyISAM;


