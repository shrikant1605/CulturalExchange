create table if not exists users (
    id bigserial primary key,
    name varchar(100) not null,
    email varchar(255) not null unique,
    password_hash varchar(255) not null,
    role varchar(20) not null,
    created_at timestamp not null
);

create table if not exists posts (
    id bigserial primary key,
    title varchar(200) not null,
    content clob not null,
    country varchar(255),
    author_id bigint not null references users(id) on delete cascade,
    created_at timestamp not null
);

create table if not exists tags (
    id bigserial primary key,
    name varchar(50) not null unique
);

create table if not exists post_tags (
    post_id bigint not null references posts(id) on delete cascade,
    tag_id bigint not null references tags(id) on delete cascade,
    primary key(post_id, tag_id)
);

create table if not exists comments (
    id bigserial primary key,
    post_id bigint not null references posts(id) on delete cascade,
    author_id bigint not null references users(id) on delete cascade,
    text varchar(2000) not null,
    created_at timestamp not null
);

create table if not exists post_likes (
    id bigserial primary key,
    post_id bigint not null references posts(id) on delete cascade,
    user_id bigint not null references users(id) on delete cascade,
    created_at timestamp not null,
    constraint unique_post_user_like unique (post_id, user_id)
);

