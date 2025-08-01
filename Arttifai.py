import requests

def convert_currency(amount, from_currency, to_currency, api_key):
    url = f"https://v6.exchangerate-api.com/v6/{api_key}/pair/{from_currency.upper()}/{to_currency.upper()}/{amount}"
    
    try:
        response = requests.get(url)
        data = response.json()
        
        if data.get("result") == "success":
            converted_amount = data["conversion_result"]
            print(f" Converted Amount: {converted_amount:.2f} {to_currency.upper()}")
        else:
            print(" API Error:", data.get("error-type", "Unknown issue"))
    except Exception as e:
        print(" Network or parsing error:", str(e))

def main():
    try:
        amount = float(input("Enter amount: "))
        from_currency = input("Convert from : ").strip()
        to_currency = input("Convert to : ").strip()
        api_key = "2f059ed04083ab8360b88b82"  

        convert_currency(amount, from_currency, to_currency, api_key)
    except ValueError:
        print(" Please enter a valid number.")

if __name__ == "__main__":
    print('Example currency codes: USD, INR, EUR, JPY, GBP, AUD, CAD, CHF, CNY, RUB')

    main()
