/*Permissions*/
INSERT INTO USER_PERMISSION (permission_id, uri, permission_key, permission_method) VALUES
    ('ccc7ff73-1989-413a-ab52-9bec7a049e31', '/records', 'filter.records', 'GET');

/*Admin*/

DELETE FROM USERS WHERE id='00000000-0000-0000-0000-000000000000';

INSERT INTO USER_ROLE (role_id, role_name, description) VALUES
    ('2e72ed53-f5e2-4f7a-bd86-8aadcadeb4ea','ADMIN', 'Base role for administrators with the highest permissions');

INSERT INTO ROLE_PERMISSION (role_id, permission_id) VALUES
    ('2e72ed53-f5e2-4f7a-bd86-8aadcadeb4ea', 'ccc7ff73-1989-413a-ab52-9bec7a049e31');

INSERT INTO USERS (id, role_id, email, first_name, last_name, hashed_password) VALUES
    ('00000000-0000-0000-0000-000000000000', '2e72ed53-f5e2-4f7a-bd86-8aadcadeb4ea', 'admin@perficient.org', 'Root',
     'Admin', '01433d49a15e192f53a6365db0f53564984c52cdeebde9aa54f12685c2627937');

/*User*/
INSERT INTO USER_ROLE (role_id, role_name, description) VALUES
    ('2e72ed53-f5e2-4f7a-bd86-8aadcadeb4eb','USER', 'Base role for users');

INSERT INTO ROLE_PERMISSION (role_id, permission_id) VALUES
    ('2e72ed53-f5e2-4f7a-bd86-8aadcadeb4eb', 'ccc7ff73-1989-413a-ab52-9bec7a049e31');

INSERT INTO RECORDS (record_id, first_name, last_name, monthly_income, monthly_outcome, has_children, is_married)
    VALUES ('00000000-0000-1111-0000-000000000000', 'Jhon', 'Doe', 300, 200, false, true)