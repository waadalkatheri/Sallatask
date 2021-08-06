# Coding Challenge
##### You will be given 2 images for the design and 2 endpoints, first one to get brand details and second one to get product details. Your task is implemented brand details and product details as the design
# Rules:

- you will need to use the MVVM design pattern to implement this functionality. 
  ##### Done

- Use jetpack navigation to navigate between fragments
  ##### Done
- Don’t use any library to implement images slider in the product details
  ##### Done
- Don’t use XML file to add background on views

   I think we can't set color or themes dynamic without an XML file, you can set the color from Backend and then you set it as a variable and show it your in-app 
   
   or use this way: 
   https://www.androprogrammer.com/2015/08/change-android-application-theme-runtime.html?fbclid=IwAR2aa_K9v7XNHJ2Nk3NyMlQbso1eAIQzqhlmxXaO3cFuutI2T2PkMtBo8bw
- Use any dependency injection library you like it
  ##### Done
- Use data binding & binding view
  ##### Done
- Add custom font for all text in the project without use style file -
  ##### Done

#### GET https://salla.sa/api/v1/brands/259940351?page=1&per_page=5 in the response, if cursor.next == null it’s mean this page last one
  - *no need to use paging because the data is too small so the paging is over engineering* 
  
#### GET https://salla.sa/api/v1/products/{product_id}/details

Headers
Currency: SAR
AppVersion: 3.0.0 
Store-Identifier: 1328842359





