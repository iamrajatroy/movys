FROM python:3.6.8
COPY . /recommender
WORKDIR /recommender
ENV FLASK_APP=recommender.py
ENV FLASK_RUN_HOST=0.0.0.0
COPY requirements.txt requirements.txt
RUN pip install -r requirements.txt
EXPOSE 5000
CMD ["flask", "run"]

