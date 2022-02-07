from flask import Flask, request, jsonify
import sys
import numpy as np
import pickle
import requests
from lxml import html
application = Flask(__name__)

@application.route("/")
@application.route('/home')
def home():
    return "''<h3>하이염!</h3>''"

url = 'https://finance.naver.com/'
headers = {'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.99 Safari/537.36'}

@application.route('/exchange', methods=['POST'])
def exchange():
    
    html_req = requests.get(url, headers = headers)
    
    tree = html.fromstring(html_req.text)
    body = tree.xpath('//div[@id ="content"]/div[2]/div/div[1]/table/tbody')[0]

    exchange_name = body.xpath('.//th/a/text()')
    exchange_prices = body.xpath('.//td[1]/text()')
    exchange_updown = body.xpath('.//td[2]/em/span/text()')
    exchange_fluctuation = body.xpath('.//td[2]/text()')
    
    exchange_name_1 = exchange_name[0]
    exchange_prices_1 = exchange_prices[0]
    exchange_updown_1 = exchange_updown[0]
    exchange_fluctuation_1 = exchange_fluctuation[0]
    
    exchange_name_2 = exchange_name[1]
    exchange_prices_2 = exchange_prices[1]
    exchange_updown_2 = exchange_updown[1]
    exchange_fluctuation_2 = exchange_fluctuation[1]
    
    exchange_name_3 = exchange_name[2]
    exchange_prices_3 = exchange_prices[2]
    exchange_updown_3 = exchange_updown[2]
    exchange_fluctuation_3 = exchange_fluctuation[2]
    
    exchange_name_4 = exchange_name[3]
    exchange_prices_4 = exchange_prices[3]
    exchange_updown_4 = exchange_updown[3]
    exchange_fluctuation_4 = exchange_fluctuation[3]
    
    dataSend = {
      "version": "2.0",
      "template": {
        "outputs": [
          {
              "simpleText": {
                  "text": "금일 환전 고시 환율을 알려드리겠습니다."
              }
          },
          {
            "listCard": {
              "header": {
                "title": "현재 환전 고시 환율 입니다."
              },
              "items": [
                {
                  "title": exchange_name_1,
                  "description": "현재가 : "+exchange_prices_1+" 등락폭 : "+exchange_fluctuation_1+" "+exchange_updown_1,
                  "link": {
                    "web": "https://finance.naver.com/marketindex/exchangeDetail.naver?marketindexCd=FX_USDKRW"
                  }
                },
                {
                  "title": exchange_name_2,
                  "description": "현재가 : "+exchange_prices_2+" 등락폭 : "+exchange_fluctuation_2+" "+exchange_updown_2,
                  "link": {
                    "web": "https://finance.naver.com/marketindex/exchangeDetail.naver?marketindexCd=FX_JPYKRW"
                  }
                },
                {
                  "title": exchange_name_3,
                  "description": "현재가 : "+exchange_prices_3+" 등락폭 : "+exchange_fluctuation_3+" "+exchange_updown_3,
                  "link": {
                    "web": "https://finance.naver.com/marketindex/exchangeDetail.naver?marketindexCd=FX_EURKRW"
                  }
                },
                {
                  "title": exchange_name_4,
                  "description": "현재가 : "+exchange_prices_4+" 등락폭 : "+exchange_fluctuation_4+" "+exchange_updown_4,
                  "link": {
                    "web": "https://finance.naver.com/marketindex/exchangeDetail.naver?marketindexCd=FX_CNYKRW"
                  }
                }
            ],
              "buttons": [
                {
                  "label": "자세히 보기",
                  "action": "webLink",
                  "webLinkUrl": "https://finance.naver.com/marketindex/?tabSel=exchange#tab_section"
                }
              ]
            }
          },
         {
             "simpleText": {
                   "text": "⚠ 제공되는 정보는 오류 및 지연이 발생될 수 있으므로 유의하시기 바랍니다."
             }
         },
        ]
      }
    }
    return jsonify(dataSend)

if __name__ == "__main__":
    application.run(host='0.0.0.0', port=int(sys.argv[1]), debug=True)