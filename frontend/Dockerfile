FROM node:14-alpine as builder
WORKDIR /app

COPY package*.json ./
RUN npm install
COPY . .
RUN npx quasar build

FROM nginx:stable-alpine
RUN rm -rf /etc/nginx/conf.d/default.conf
#COPY --from=build-stage /app/nginx/default.conf /etc/nginx/conf.d/default.conf
#COPY ./nginx/default.conf /etc/nginx/conf.d/default.conf
#COPY --from=builder /app/nginx/default.conf /etc/nginx/conf.d/default.conf
COPY --from=builder /app/nginx /etc/nginx/conf.d
RUN rm -rf /var/www/html/*
COPY --from=builder /app/dist/spa /var/www/html

EXPOSE 80
ENTRYPOINT ["nginx", "-g", "daemon off;"]