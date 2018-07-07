import nltk
import os
import glob
from pathlib import Path
from os.path import basename
import urllib.request as request
import tarfile
import pandas as pd

IMDB_REVIEW_DATA_URL = "http://ai.stanford.edu/~amaas/data/sentiment/aclImdb_v1.tar.gz"

def download_report_hook(byte_transferred, block_size, total):
    print("{:d} MB out of {:d} MB downloaded...".format(
        int(byte_transferred / (1024 * 1024)),
        int(total/(1024 * 1024))))

def extract_tar(source_path, target_path):
    extractor = tarfile.open(source_path)
    extractor.extractall(path=target_path)

def download_file(url, save_path):
    request.urlretrieve(IMDB_REVIEW_DATA_URL, save_path, download_report_hook)


class Imdb:
    def __init__(self):
        pass

    def load(self):
        data_pathname = "data/aclImdb_v1.tar.gz"
        data_tar = Path(data_pathname)
        if not data_tar.exists():
            # fetch and unpack data
            download_file(IMDB_REVIEW_DATA_URL, data_pathname)

        if not Path("data/imdb").exists():
            extract_tar(data_tar, "data/imdb")

        if not Path("data/imdb/train_feather").exists():
            train_pos = self.create_dataframe_("data/imdb/aclImdb/train/pos/*.txt")
            train_neg = self.create_dataframe_("data/imdb/aclImdb/train/neg/*.txt")
            train_data =train_pos.append(train_neg)
            train_data.to_pickle("data/imdb/train_feather")

        if not Path("data/imdb/test_feather").exists():
            test_pos = self.create_dataframe_("data/imdb/aclImdb/test/pos/*.txt")
            test_neg = self.create_dataframe_("data/imdb/aclImdb/test/neg/*.txt")
            test_data = test_pos.append(test_neg)
            test_data.to_pickle("data/imdb/test_feather")

    def create_dataframe_(self, globpath):
        files = glob.glob(globpath)
        rows = []
        for filepath in files:
            filename, _ = os.path.splitext(basename(filepath))
            id, rating = filename.split('_')
            # It is better to use async read such as asyncio
            with open(filepath, encoding="utf8") as f:
                text = f.read()
                rows.append((id, text, rating))

        dataframe = pd.DataFrame(rows, columns=['id', 'text', 'rating'])

        return dataframe


if __name__ == "__main__":
    data = Imdb()
    data.load()