import json
import requests
# In a real scenario, you would use BeautifulSoup or similar to scrape data.
# For this example, I'll provide a mock data aggregator.

def fetch_ipo_data():
    # Mock data representing what would be scraped
    ipos = [
        {
            "id": "1",
            "name": "Tech Solutions Ltd",
            "logoUrl": "https://example.com/logo1.png",
            "category": "Mainboard",
            "status": "Open",
            "offerPriceRange": "₹450 - ₹475",
            "lotSize": 30,
            "latestGmpAmount": 120.0,
            "gmpPercentage": 25.0,
            "expectedListingGain": "30%",
            "openDate": "2024-05-20",
            "closeDate": "2024-05-22",
            "listingDate": "2024-05-27",
            "allotmentDate": "2024-05-23",
            "issueSize": "₹1200 Cr",
            "registrarName": "Link Intime",
            "allotmentLink": "https://linkintime.co.in"
        },
        {
            "id": "2",
            "name": "SME Growth Corp",
            "logoUrl": "https://example.com/logo2.png",
            "category": "SME",
            "status": "Upcoming",
            "offerPriceRange": "₹80 - ₹85",
            "lotSize": 1600,
            "latestGmpAmount": 15.0,
            "gmpPercentage": 18.0,
            "expectedListingGain": "20%",
            "openDate": "2024-06-01",
            "closeDate": "2024-06-03",
            "listingDate": "2024-06-10",
            "allotmentDate": "2024-06-07",
            "issueSize": "₹50 Cr",
            "registrarName": "Bigshare Services",
            "allotmentLink": "https://bigshareonline.com"
        }
    ]
    
    with open('ipos.json', 'w') as f:
        json.dump(ipos, f, indent=4)
    print("Successfully fetched and saved IPO data to ipos.json")

if __name__ == "__main__":
    fetch_ipo_data()
