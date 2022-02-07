#환전고시환율

import requests
from lxml import html
import time

url = 'https://finance.naver.com/'
headers = {'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.99 Safari/537.36'}
html_req = requests.get(url, headers = headers)

tree = html.fromstring(html_req.text)
body = tree.xpath('//div[@id ="content"]/div[2]/div/div[1]/table/tbody')[0]

results = []

overseas_stock_name = body.xpath('.//th/a/text()')
overseas_stock_prices = body.xpath('.//td[1]/text()')
overseas_stock_updown = body.xpath('.//td[2]/em/span/text()')
overseas_stock_fluctuation = body.xpath('.//td[2]/text()')

for overseas_stock_name, overseas_stock_prices, overseas_stock_updown, overseas_stock_fluctuation in zip(overseas_stock_name, overseas_stock_prices, overseas_stock_updown, overseas_stock_fluctuation):
  results.append([overseas_stock_name, overseas_stock_prices, overseas_stock_updown, overseas_stock_fluctuation])

for result in results:
      print(result)