import nltk
from pathlib import Path
import urllib.request as request
import tarfile

IMDB_REVIEW_DATA_URL = "http://ai.stanford.edu/~amaas/data/sentiment/aclImdb_v1.tar.gz"

def get_imdb_data():
    data_pathname = "data/aclImdb_v1.tar.gz"
    data_tar = Path(data_pathname)
    if not data_tar.exists():
        # fetch and unpack data
        download_file(IMDB_REVIEW_DATA_URL, data_pathname)

    if not Path("data/imdb").exists():
        extract_tar(data_tar, "data/imdb")

def download_report_hook(byte_transferred, block_size, total):
    print("{:d} MB out of {:d} MB downloaded...".format(
        int(byte_transferred / (1024 * 1024)),
        int(total/(1024 * 1024))))

def extract_tar(source_path, target_path):
    extractor = tarfile.open(source_path)
    extractor.extractall(path=target_path)

def download_file(url, save_path):
    request.urlretrieve(IMDB_REVIEW_DATA_URL, save_path, download_report_hook)



if __name__ == "__main__":
    get_imdb_data()