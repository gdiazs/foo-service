CREATE TABLE FOO
(
    ID          UUID primary key,
    DESCRIPTION VARCHAR(255) NOT NULL
);
INSERT INTO FOO (ID, DESCRIPTION) VALUES
('86e2a19e-9c4e-4e4a-93ef-1ad7557ef51a', 'foo service');

INSERT INTO FOO (ID, DESCRIPTION) VALUES
('3d80341e-ae50-4631-83e6-9d5199fd5aaa', 'foo service 2');