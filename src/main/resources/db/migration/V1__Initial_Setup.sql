CREATE TABLE IF NOT EXISTS code_diseases (
    id varchar(255) not null,
    title_disease varchar(300),
    primary key (id)
);
CREATE TABLE IF NOT EXISTS patients (
    id bigint generated by default as identity,
    first_name varchar(255),
    last_name varchar(255),
    middle_name varchar(255),
    sex varchar(255) check (sex in ('F','M','OTHER')),
    birthday date,
    number_policy bigint,
    primary key (id)
);
CREATE TABLE IF NOT EXISTS diseases (
    id bigint generated by default as identity,
    number_of_disease varchar(255),
    start_disease date,
    end_disease date,
    prescription varchar(1024),
    patient_id bigint,
    primary key (id),
    foreign key (patient_id) references patients(id)
);