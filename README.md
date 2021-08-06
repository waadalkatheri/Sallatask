# Coding Challenge
You will be given 2 images for the design and 2 endpoints, first one to get brand details and second one to get product details. Your task is implemented brand details and product details as the design
Rules
- you will need to use the MVVM design pattern to implement this functionality.
- Use jetpack navigation to navigate between fragments
- Don’t use any library to implement images slider in the product details
- Don’t use XML file to add background on views
- Use any dependency injection library you like it
- Use data binding & binding view
- Add custom font for all text in the project without use style file -
Before you start, you will need to create a public repository (Github) and then send me an email so I can confirm when the repository was created.
Once completed please push everything to the public Github repository and then I can check your code.
APIs
GET https://salla.sa/api/v1/brands/259940351?page=1&per_page=5 in the response, if cursor.next == null it’s mean this page last one
GET https://salla.sa/api/v1/products/{product_id}/details
Headers
Currency: SAR
AppVersion: 3.0.0 Store-Identifier: 1328842359
