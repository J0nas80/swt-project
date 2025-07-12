FROM node:18-alpine
WORKDIR /app
COPY package.json .
RUN npm install
ARG BACKEND_HOST \
BACKEND_PORT 
ENV BACKEND_HOST $BACKEND_HOST
ENV BACKEND_PORT $BACKEND_PORT
RUN npm i -g serve
COPY . .
RUN npm run build
CMD [ "serve", "-s", "dist" ]
