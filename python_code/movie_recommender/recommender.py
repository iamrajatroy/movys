from flask import Flask, jsonify
import pandas as pd
import numpy as np

indices = pd.read_csv('./indices_may20.csv')
sim_mat = np.load('./movies_sim_may20.npy')

app = Flask(__name__)

@app.route('/', methods=['GET'])
def health_check():
	return "OK"


@app.route('/get_recommendation/<movie_title>', methods=['GET'])
def get_recommendation(movie_title):
	idx_arr = indices[indices['original_title'] == movie_title]['index'].values
	rec_movies = []
	if len(idx_arr > 0):
		idx = idx_arr[0]
		sim_scores = list(enumerate(sim_mat[idx]))
		sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)
		sim_scores = sim_scores[1:6]
		movie_indices = list([i[0] for i in sim_scores])
		# FIXME: some problem with saved index. check it out. currently taking series index instead of saved index
		# rec_movies = list(indices[indices['index'].isin(movie_indices)]['original_title'].values)
		rec_movies = list(indices.loc[movie_indices]['original_title'].values)
	return jsonify({'similar_movies' : rec_movies})


if __name__ == '__main__':
	app.run(debug=True)