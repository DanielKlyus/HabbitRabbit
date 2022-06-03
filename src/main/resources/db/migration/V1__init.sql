CREATE TABLE players
(
    "id"              serial PRIMARY KEY NOT NULL,
    "name"            TEXT               NOT NULL,
    "password"        TEXT               NOT NULL,
    "email"           TEXT               NOT NULL,
    "is_active"       boolean            NOT NULL,
    "created_at"      TIMESTAMP          NOT NULL DEFAULT NOW(),
    "updated_at"      TIMESTAMP          NOT NULL DEFAULT NOW(),
    "count_of_rabbits" INTEGER           DEFAULT 0,
    "is_admin" boolean NOT NULL
);

CREATE TABLE rooms
(
    "id"                  serial PRIMARY KEY NOT NULL,
    "name"                TEXT               NOT NULL,
    "description"         TEXT               NOT NULL,
    "creator_id"          INTEGER            NOT NULL,
    "created_at"          TIMESTAMP          NOT NULL DEFAULT NOW(),
    "updated_at"          TIMESTAMP          NOT NULL DEFAULT NOW(),
    "finished_at"         TIMESTAMP          NOT NULL,
    "rabbits_for_failure" INTEGER            NOT NULL,
    "rabbits_for_success" INTEGER            NOT NULL,

    CONSTRAINT fk_rooms_creators FOREIGN KEY ("creator_id") REFERENCES players ("id")
);

CREATE TABLE members
(
    "room_id"   INTEGER NOT NULL,
    "player_id" INTEGER NOT NULL,
    PRIMARY KEY ("room_id", "player_id"),

    CONSTRAINT fk_members_rooms FOREIGN KEY ("room_id") REFERENCES rooms ("id"),
    CONSTRAINT fk_members_player FOREIGN KEY ("player_id") REFERENCES players ("id")
);

CREATE TABLE visits
(
    "player_id"  INTEGER PRIMARY KEY NOT NULL,
    "activities" TEXT,

    CONSTRAINT fk_visits_player FOREIGN KEY ("player_id") REFERENCES players ("id")
);