CREATE TABLE CUSTOMER
(
    customer_id   BIGINT AUTO_INCREMENT NOT NULL COMMENT '고객 ID',
    customer_name VARCHAR(255) NOT NULL COMMENT '고객 이름',
    PRIMARY KEY (customer_id)
) comment '고객 정보 테이블';

CREATE TABLE EVENT
(
    event_id      BIGINT AUTO_INCREMENT NOT NULL COMMENT '이벤트 ID',
    event_name    VARCHAR(255) NOT NULL COMMENT '이벤트 이름',
    quantity      INT          NOT NULL COMMENT '이벤트 티켓 전체 수량',
    rest_quantity INT          NOT NULL COMMENT '이벤트 티켓 잔여 수량',
    PRIMARY KEY (event_id)
) comment '이벤트 정보 테이블';

CREATE TABLE TICKET
(
    ticket_id     BIGINT AUTO_INCREMENT NOT NULL COMMENT '티켓 ID',
    customer_id   INT           NOT NULL COMMENT '고객 ID',
    event_id      INT           NOT NULL COMMENT '이벤트 ID',
    created_at    DATE          NOT NULL COMMENT '티켓 발급 날짜',
    serial_number VARCHAR(1000) NOT NULL COMMENT '티켓 시리얼 넘버',
    PRIMARY KEY (ticket_id),
    FOREIGN KEY (customer_id) REFERENCES `CUSTOMER` (customer_id),
    FOREIGN KEY (event_id) REFERENCES `EVENT` (event_id)
) comment '이벤트 티켓 정보 테이블';