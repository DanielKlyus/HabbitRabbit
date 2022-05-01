CREATE TABLE users
(
	"id"        serial PRIMARY KEY NOT NULL,
	"name"      TEXT               NOT NULL,
	"login"     TEXT               NOT NULL,
	"password"  TEXT               NOT NULL,
	"email"     TEXT               NOT NULL,
	"isActive"  boolean            NOT NULL,
	"createdAt" TIMESTAMP          NOT NULL DEFAULT NOW(),
	"updatedAt" TIMESTAMP          NOT NULL DEFAULT NOW()
);

CREATE TABLE rooms
(
	"id"                serial PRIMARY KEY NOT NULL,
	"name"              TEXT               NOT NULL,
	"creatorId"         INTEGER            NOT NULL,
	"createdAt"         TIMESTAMP          NOT NULL DEFAULT NOW(),
	"updatedAt"         TIMESTAMP          NOT NULL DEFAULT NOW(),
	"finishedAt"        TIMESTAMP          NOT NULL,
	"rabbitsForFailure" INTEGER            NOT NULL,
	"rabbitsForSuccess" INTEGER            NOT NULL,
	
	CONSTRAINT fk_rooms_creators FOREIGN KEY ("creatorId") REFERENCES users (id)
);

CREATE TABLE members
(
	"roomId" INTEGER NOT NULL,
	"userId" INTEGER NOT NULL,
	PRIMARY KEY(roomId, userId),
	
	CONSTRAINT fk_members_rooms FOREIGN KEY ("roomId") REFERENCES rooms (id),
	CONSTRAINT fk_members_users FOREIGN KEY ("userId") REFERENCES users (id)
);
