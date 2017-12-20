### STAGE 1: Build ###
FROM node:8-alpine as builder

COPY package.json package-lock.json ./

RUN npm set progress=false && npm config set depth 0 && npm cache clean --force

RUN npm i && mkdir /app && cp -R ./node_modules ./app

WORKDIR /app

COPY . .

RUN $(npm bin)/ng build --prod --build-optimizer

### STAGE 2: Setup ###
FROM alexsuch/angular-cli:1.5.5

COPY --from=builder /app /app

WORKDIR /app

EXPOSE 4200

CMD ["ng", "serve", "--host", "0.0.0.0"]
