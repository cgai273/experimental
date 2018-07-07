import sklearn.feature_extraction as fe
import pandas as pd

tfidf =  fe.text.TfidfVectorizer(min_df=0.2, max_df=0.5, ngram_range=(1, 2))
train_data = pd.read_pickle('../common/data/imdb/train_feather')
texts = list(train_data['text'])
vectorized_text = tfidf.fit_transform(texts)

with pd.option_context('display.max_rows', 10, 'display.max_columns', 10):
    print(pd.DataFrame(vectorized_text.todense(), columns=tfidf.get_feature_names()))