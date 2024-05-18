import sys
import logging
import requests
from config_file import config



def main():
    logging.info("START")
    google_api_key = config['google_api_key']
    youtube_playlist_id = config['youtube_playlist_id']

    response = requests.get("https://www.googleapis.com/youtube/v3/playlistItems", params={
        "key": google_api_key,
        "playlistId": youtube_playlist_id
    })

    logging.debug("GOT %s", response.text)


if __name__ == '__main__':
    logging.basicConfig(level=logging.DEBUG)
    sys.exit(main())

