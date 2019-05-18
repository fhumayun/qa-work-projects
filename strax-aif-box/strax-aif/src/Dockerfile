FROM node:10.15.0-stretch

LABEL GroupCare Technologies LLC. <support@groupcaretech.com>

ENV NODE_ENV DEV

RUN npm install sails -g
# WORKDIR /strax-aif-box

## mongodb package to reference database schema
COPY mongodb /usr/src/mongodb
WORKDIR /usr/src/mongodb
RUN yarn

# COPY strax-aif/database.json /usr/src/app/database.json
# COPY strax-aif/migrations /usr/src/app/migrations
COPY src /usr/src/app/src
COPY src/package.json /usr/src/app/package.json

WORKDIR /usr/src/app
# WORKDIR /usr/src

# RUN npm install -g db-migrate db-migrate-mongodb
# RUN npm install
RUN yarn

EXPOSE 1337

CMD ["sails", "lift"]
