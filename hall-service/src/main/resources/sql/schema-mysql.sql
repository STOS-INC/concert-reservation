DROP TABLE IF EXISTS scheduled_seat;
CREATE TABLE scheduled_seat
(
    id       bigint NOT NULL AUTO_INCREMENT,
    status     varchar(20) DEFAULT NULL,
    schedule_id bigint DEFAULT NULL,
    PRIMARY KEY (id)
);