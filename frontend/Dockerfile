FROM node:18-alpine
ARG HOST
ARG PORT 
RUN echo $HOST
RUN echo $PORT
ENV VITE_BACKEND_HOST=$HOST
ENV VITE_BACKEND_PORT=$PORT
RUN echo $VITE_BACKEND_HOST
RUN echo $VITE_BACKEND_PORT
WORKDIR /app
COPY package.json .
RUN npm install
RUN npm i -g serve
COPY . .
RUN npm run build
CMD [ "serve", "-s", "dist" ]
