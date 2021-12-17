DROP TABLE group_permission;
DROP TABLE groups_role;
DROP TABLE permissions;

CREATE TABLE permissions
(
    id   numeric     NOT NULL,
    name varchar(20) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE groups_role
(
    id   numeric     NOT NULL,
    name varchar(20) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE group_permission
(
    id            numeric NOT NULL,
    group_id      numeric NOT NULL,
    permission_id numeric NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT unq_group_id_permission_id UNIQUE (group_id, permission_id),
    CONSTRAINT fk_group_id_for_group_permission FOREIGN KEY (group_id) REFERENCES groups (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_permission_id_for_group_permission FOREIGN KEY (permission_id) REFERENCES permissions (id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

